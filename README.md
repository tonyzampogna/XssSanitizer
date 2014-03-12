XssSanitizer
============

Grails plugin for sanitizing XSS from the user input.

This plugin uses OWASP ESAPI library to sanitize request parameters. This reduces the risk of dangerous XSS request parameters possibly being rendered on the client.

Installation
----------

To use the plugin, add this to your BuildConfig.groovy:
	
<code>
	plugins {
		runtime ":xss-sanitizer:1.2.2"
	}
</code>

