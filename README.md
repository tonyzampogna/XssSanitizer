XssSanitizer
============

Grails plugin for sanitizing XSS from the user input.

This plugin uses OWASP ESAPI library to sanitize request parameters. This reduces the risk of dangerous XSS request parameters possibly being rendered on the client.

Installation
----------

In a grails project, add the following to your BuildConfig.groovy in the plugins section:

<code>
runtime ':xss-sanitizer:0.1'
</code>
