package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A demo of Java regular expressions.
 */
public class Demo {

    public static void main(String[] args) {

        //using the String method "matches" to check if a String matches a regex
        String str = "Hello_World11";
        boolean isSame = str.matches("(Hello)\\w.*(1)\\2");
        System.out.println(isSame);

        // You can do an individual match in one easy line:
        System.out.println(Pattern.matches("a*b", "aaaaab"));

        // Notice that it automatically anchors
        // That is, it is equivalent to ^a*b$
        System.out.println(Pattern.matches("a*b", "baaaaab"));
        System.out.println();


        // If you plan to reuse a pattern, it's more efficient
        // to let Java build the matching infrastructure once and
        // reuse it for each match against that pattern.
        Pattern p = Pattern.compile("CSC[0-9][0-9][0-9]H1([FS])");
        Matcher m = p.matcher("CSC207H1S");
        System.out.println("Does CSC207H1S match " + p + " ?");
        System.out.println(m.matches());

        // Here we reuse that (under the hood) infrastructure.
        System.out.println("Does CSC199H1Y match " + p + " ?");
        System.out.println(p.matcher("CSC199H1Y").matches() + "\n");

        // The matcher has other methods that let you find out
        // which substrings matched with which "group"
        // of the pattern. Group 0 is the whole pattern.

        // Here we add more brackets to the pattern.
        // This will allow us to capture the group of characters
        // that is the course number.
        p = Pattern.compile("CSC([0-9][0-9][0-9])H1([FS])");
        m = p.matcher("CSC207H1S");
        if (m.matches()) { // need to run the matches method before calling the group method!
            System.out.println("Groups for pattern CSC([0-9][0-9][0-9])H1([FS]) and input CSC207H1S");
            System.out.println("group 0: " + m.group(0));  // the entire string
            System.out.println("group 1: " + m.group(1));  // the first group: 207
            System.out.println("group 2: " + m.group(2));  // the second group: S
        }
        else {
            System.out.println("group example failed");
        }

        // Example of using a back reference.
        p = Pattern.compile("(\\d\\d\\d)ABC\\1");
        m = p.matcher("123ABC123");
        System.out.println("Example using a back reference to match group later");
        System.out.println("123ABC123 matches " + p.pattern() + " ...");
        System.out.println(m.matches());
        m = p.matcher("123ABC456");
        System.out.println("123ABC456 matches " + p.pattern() + " ...");
        System.out.println(m.matches());


        //using a Matcher object to find matching substrings
        Pattern pattern = Pattern.compile("[a-c]\\+{5}\""); // \\+{5} becomes +{5} in the regex
        Matcher matcher = pattern.matcher("a1bb\\\\c2a4\\\\b+++++\"6c89\\\\c+++++\"");
        while (matcher.find()) { // looping to find all matches!
            System.out.println(matcher.group());
        }

    }
}