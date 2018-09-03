XssSanitizer
============

> WARNING!
> 
> This repository is for Grails 2.0 support only. For Grails 3, see [@rpalcolea's|https://github.com/rpalcolea] plugin page below.
> 
> https://github.com/rpalcolea/grails-xss-sanitizer

Grails plugin for sanitizing XSS from the user input.

This plugin uses OWASP ESAPI library to sanitize request parameters. This reduces the risk of dangerous XSS request parameters possibly being rendered on the client.

Installation
----------

To use the plugin, add this to your BuildConfig.groovy:
	
<code>
	runtime ":xss-sanitizer:0.4.1"
</code>

Description
----------

This plugin will add the automatic ability to strip / clean out unwanted XSS code in the browser. The plugin strips code that comes in via the request object. Also, any Servlets will use an extend HttpServletRequest so that request parameters used from that servlet will be stripped as well.

Just adding this plugin to you project with the installation instructions above will activate it. No other actions are needed.

There is an XssSanitizerUtil class that can also be used to strip strings out.

Also, you can enable or disable it by adding a key in your Config.groovy like this:

<code>
	xssSanitizer.enabled = true
</code>
