package org.tonyzampogna.xss.sanitizer.filter;

import org.tonyzampogna.xss.sanitizer.filter.wrapper.XssRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * Taken from http://ricardozuasti.com/2012/stronger-anti-cross-site-scripting-xss-filter-for-java-web-apps/
 */
public class XssFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

	    // Wrap the filter with the new filter.
	    // Any requests to the HttpRequest or HttpResponse will go through the wrapper.
        chain.doFilter(new XssRequestWrapper((HttpServletRequest) request), response);
    }

}
