package apis;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Fetches food products from the <a href="https://world.openfoodfacts.org/">Open Food Facts</a> web
 * API.
 *
 * <p>Open Food Facts is a free, open database of food products from around the world. It needs
 * <strong>no API key</strong>, which makes it a convenient service to practise against.
 *
 * <p>This class is the <em>data access</em> part of the program: it knows about URLs, HTTP, and
 * JSON, and it hands back plain {@link Product} objects. Nothing else in the program needs to know
 * that Open Food Facts exists.
 *
 * <p>Please be considerate when using a free public API: ask for the fields you need, don't call it
 * in a tight loop, and identify your program with a real User-Agent header (as we do below) so the
 * maintainers can tell who is making requests.
 */
public class OpenFoodFactsClient {

  /** Base URL of the v2 "get one product" endpoint; the barcode is appended to it. */
  private static final String PRODUCT_URL = "https://world.openfoodfacts.org/api/v2/product/";

  /** Endpoint for searching, which returns an array of products. */
  private static final String SEARCH_URL = "https://world.openfoodfacts.org/api/v2/search";

  /**
   * Only these fields are requested. Asking for what you need keeps the response small and fast —
   * the full record for a product can be hundreds of fields.
   */
  private static final String FIELDS = "code,product_name,brands,nutriscore_grade,nutriments";

  /** Open Food Facts asks that every caller identify itself. */
  private static final String USER_AGENT = "CSC207-CourseNotes/1.0 (teaching example)";

  private final OkHttpClient client = new OkHttpClient();

  /**
   * Looks up a single product by barcode.
   *
   * @param barcode the product's barcode, e.g. "3017620422003" for Nutella
   * @return the product
   * @throws IOException if the request fails, or if no product has that barcode
   */
  public Product fetchByBarcode(String barcode) throws IOException {
    String url = PRODUCT_URL + barcode + ".json?fields=" + FIELDS;
    Request request = new Request.Builder().url(url).header("User-Agent", USER_AGENT).build();

    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        throw new IOException("Request failed with HTTP status " + response.code());
      }
      return parseProduct(response.body().string(), barcode);
    }
  }

  /**
   * Turns an Open Food Facts JSON response into a {@link Product}.
   *
   * <p>This is separated from {@link #fetchByBarcode(String)} on purpose: parsing is pure string
   * handling, so it can be tested without touching the network. Save one real response to a file
   * and you can develop against it all day without making a single request.
   *
   * @param json the raw JSON text of the response
   * @param barcode the barcode that was requested
   * @return the product described by the JSON
   * @throws IOException if the response says no product was found
   */
  public static Product parseProduct(String json, String barcode) throws IOException {
    JSONObject root = new JSONObject(json);

    // Open Food Facts reports success in the body, not only in the HTTP status:
    // status 1 means "product found", 0 means it is not in the database.
    if (root.optInt("status", 0) != 1) {
      throw new IOException("No product found for barcode " + barcode);
    }

    JSONObject product = root.getJSONObject("product");
    JSONObject nutriments = product.optJSONObject("nutriments");

    return new Product(
        product.optString("code", barcode),
        product.optString("product_name", "(unknown)"),
        product.optString("brands", "(unknown)"),
        product.optString("nutriscore_grade", "?"),
        readNutriment(nutriments, "energy-kcal_100g"),
        readNutriment(nutriments, "sugars_100g"));
  }

  /**
   * Reads one value out of the "nutriments" object, tolerating missing data.
   *
   * <p>Real-world data is patchy: not every product records every nutrient. Using {@code optDouble}
   * with a default keeps a missing field from crashing the program.
   *
   * @param nutriments the nutriments object, which may be null
   * @param key the nutriment to read, e.g. "sugars_100g"
   * @return the value, or NaN if it is not present
   */
  private static double readNutriment(JSONObject nutriments, String key) {
    if (nutriments == null) {
      return Double.NaN;
    }
    return nutriments.optDouble(key, Double.NaN);
  }

  /**
   * Searches for products in a category, e.g. "chocolate" or "breakfast-cereals".
   *
   * <p>This endpoint returns a JSON <em>array</em> of products rather than a single one, so it is a
   * good place to practise {@link org.json.JSONArray}.
   *
   * @param category the English category tag to search for, e.g. "chocolate"
   * @param limit how many products to ask for
   * @return the products found, in the order the API returned them
   * @throws IOException if the request fails
   */
  public List<Product> searchByCategory(String category, int limit) throws IOException {
    String url =
        SEARCH_URL
            + "?categories_tags_en="
            + URLEncoder.encode(category, StandardCharsets.UTF_8)
            + "&fields="
            + FIELDS
            + "&page_size="
            + limit;
    Request request = new Request.Builder().url(url).header("User-Agent", USER_AGENT).build();

    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        throw new IOException("Search failed with HTTP status " + response.code());
      }
      return parseSearchResults(response.body().string());
    }
  }

  /**
   * Turns a search response into a list of products, skipping any entry that has no name.
   *
   * @param json the raw JSON text of a search response
   * @return the products it describes
   */
  public static List<Product> parseSearchResults(String json) {
    JSONArray products = new JSONObject(json).getJSONArray("products");

    List<Product> results = new ArrayList<>();
    for (int i = 0; i < products.length(); i++) {
      JSONObject product = products.getJSONObject(i);
      String name = product.optString("product_name", "");
      if (name.isEmpty()) {
        continue;
      }
      JSONObject nutriments = product.optJSONObject("nutriments");
      results.add(
          new Product(
              product.optString("code", "?"),
              name,
              product.optString("brands", "(unknown)"),
              product.optString("nutriscore_grade", "?"),
              readNutriment(nutriments, "energy-kcal_100g"),
              readNutriment(nutriments, "sugars_100g")));
    }
    return results;
  }

  /**
   * Saves a raw API response to a file, so you can develop against it later without calling the API
   * again. See the "Rate limits" section of the chapter.
   *
   * @param barcode the product to fetch
   * @param file where to write the response
   * @throws IOException if the request or the write fails
   */
  public void saveResponseToFile(String barcode, Path file) throws IOException {
    String url = PRODUCT_URL + barcode + ".json?fields=" + FIELDS;
    Request request = new Request.Builder().url(url).header("User-Agent", USER_AGENT).build();
    try (Response response = client.newCall(request).execute()) {
      Files.writeString(file, response.body().string());
    }
  }
}
