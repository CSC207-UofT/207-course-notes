import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Tests for {@link Extractor}. Do NOT modify this file. */
class ExtractorTest {

  @Test
  void findsASingleCourseCode() {
    assertEquals(List.of("CSC207H1F"), Extractor.findCourseCodes("Welcome to CSC207H1F!"));
  }

  @Test
  void findsSeveralCourseCodesInOrder() {
    assertEquals(
        List.of("CSC207H1F", "CSC236H1S", "MAT137Y1Y"),
        Extractor.findCourseCodes("Take CSC207H1F, then CSC236H1S; MAT137Y1Y helps too."));
  }

  @Test
  void findsNoCourseCodesInPlainText() {
    List<String> found = Extractor.findCourseCodes("There are no course codes here at all.");
    assertTrue(found.isEmpty(), "expected an empty list, got " + found);
  }

  @Test
  void findsNoCourseCodesInAnEmptyString() {
    assertTrue(Extractor.findCourseCodes("").isEmpty());
  }

  @Test
  void findsCourseNumbersUsingTheCapturingGroup() {
    assertEquals(
        List.of("207", "236", "137"),
        Extractor.findCourseNumbers("Take CSC207H1F, then CSC236H1S; MAT137Y1Y helps too."));
  }

  @Test
  void findsNoCourseNumbersWhenThereAreNoCodes() {
    assertTrue(Extractor.findCourseNumbers("csc207 is lowercase, so it does not match").isEmpty());
  }

  @Test
  void masksASingleEmail() {
    assertEquals("Contact *** for help.", Extractor.maskEmails("Contact user@example.com for help."));
  }

  @Test
  void masksSeveralEmails() {
    assertEquals(
        "From *** to ***, cc ***.",
        Extractor.maskEmails(
            "From a.b_c+tag@sub.domain.org to bob@example.com, cc carol@utoronto.ca."));
  }

  @Test
  void leavesTextWithoutEmailsUnchanged() {
    String text = "No addresses here, not even an @ sign on its own.";
    assertEquals(text, Extractor.maskEmails(text));
  }

  @Test
  void leavesAnEmptyStringUnchanged() {
    assertEquals("", Extractor.maskEmails(""));
  }
}
