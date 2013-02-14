package org.tonyzampogna.xss.sanitizer.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;
import org.tonyzampogna.xss.sanitizer.filter.wrapper.XssRequestWrapper;

/**
 * Taken from http://ricardozuasti.com/2012/stronger-anti-cross-site-scripting-xss-filter-for-java-web-apps/
 */
public class XssFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // Wrap the filter with the new filter.
        // Any requests to the HttpRequest or HttpResponse will go through the wrapper.
        chain.doFilter(new XssRequestWrapper(request), response);
    }
}
