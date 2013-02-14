package filters

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.tonyzampogna.xss.sanitizer.util.XssSanitizerUtil

class XssSanitizerFilters {

	def filters = {
		all(controller: '*', action: '*') {
			before = {
				// Call the sanitize method for each request.
				sanitizeParameters(params, request, response)
			}
		}
	}

	/**
	 * Loop through each of the Grails request parameters
	 * and "sanitize" each value.
	 */
	private void sanitizeParameters(parameters, HttpServletRequest request, HttpServletResponse response) {
		// Sanitize Header Parameters
		//Enumeration<String> headerNames = request.getHeaderNames();
		//while (headerNames.hasMoreElements()) {
		//	String headerName = (String) headerNames.nextElement();
		//	String headerValue = request.getHeader(headerName);
		//	int headerValueLength = headerValue.length();
		//	String newHeaderValue = stripXSS(headerValue);
		//	int newHeaderValueLength = newHeaderValue.length();
		//	// Throw an exception. This is illegal.
		//	if (headerValueLength != newHeaderValueLength) {
		//		response.sendError 500
		//	}
		//}

		// Sanitize Request Parameters
		parameters.each { entry ->
			if (entry.value instanceof String) {
				entry.value = XssSanitizerUtil.stripXSS(entry.value)
			}
		}
	}

}
