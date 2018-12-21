package org.tonyzampogna.xss.sanitizer.util;

import org.owasp.esapi.ESAPI;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Taken from http://ricardozuasti.com/2012/stronger-anti-cross-site-scripting-xss-filter-for-java-web-apps/
 */
public class XssSanitizerUtil {

	private static List<Pattern> XSS_INPUT_PATTERNS = new ArrayList<Pattern>();

	static {
			// Avoid anything between script tags
			XSS_INPUT_PATTERNS.add(Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE));

			// avoid iframes
			XSS_INPUT_PATTERNS.add(Pattern.compile("<iframe(.*?)>(.*?)</iframe>", Pattern.CASE_INSENSITIVE));

			// avoid inputs
			XSS_INPUT_PATTERNS.add(Pattern.compile("<input(.*?)>(.*?)</input>", Pattern.CASE_INSENSITIVE));

			// Avoid anything in a src='...' type of expression
			XSS_INPUT_PATTERNS.add(Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));

			XSS_INPUT_PATTERNS.add(Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));

			XSS_INPUT_PATTERNS.add(Pattern.compile("src[\r\n]*=[\r\n]*([^>]+)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));

			// Remove any lonesome </script> tag
			XSS_INPUT_PATTERNS.add(Pattern.compile("</script>", Pattern.CASE_INSENSITIVE));

			// Remove any lonesome <script ...> tag
			XSS_INPUT_PATTERNS.add(Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));

			// Remove any lonesome <input ...> tag
			XSS_INPUT_PATTERNS.add(Pattern.compile("<input(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));

			// Avoid eval(...) expressions
			XSS_INPUT_PATTERNS.add(Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));

			// Avoid expression(...) expressions
			XSS_INPUT_PATTERNS.add(Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));

			// Avoid javascript:... expressions
			XSS_INPUT_PATTERNS.add(Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE));

			// Avoid vbscript:... expressions
			XSS_INPUT_PATTERNS.add(Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE));

			// Avoid onload= expressions
			XSS_INPUT_PATTERNS.add(Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));
			 
			// Avoid onfocus= expressions
			XSS_INPUT_PATTERNS.add(Pattern.compile("onfocus(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));

			// Avoid any form injection with <...form ...> ... </form ...> tag
			XSS_INPUT_PATTERNS.add(Pattern.compile("<(.*?)form(.*?)>(.*?)</(.*?)form(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));
	}

	/**
	 * This method takes a string and strips out any potential script injections.
	 *
	 * @param value
	 * @return String - the new "sanitized" string.
	 */
	public static String stripXSS(String value) {

		try {

			if (value != null) {
				// NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
				// avoid encoded attacks.
				value = ESAPI.encoder().canonicalize(value);

				// Avoid null characters
				value = value.replaceAll("\0", "");

				// test against known XSS input patterns
				for (Pattern xssInputPattern : XSS_INPUT_PATTERNS) {
					value = xssInputPattern.matcher(value).replaceAll("");
				}
				
				// If the encoded incoming value is not malicious, then reverting it to the original text
				// this is required for text containing &PI or &XI which is getting evaluated to the respective symbol.
				
			}

		} catch (Exception ex) {
			System.out.println("Could not strip XSS from value = " + value + " | ex = " + ex.getMessage());
		}

		return value;
	}

}
