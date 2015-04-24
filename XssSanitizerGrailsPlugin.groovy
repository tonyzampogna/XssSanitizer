import org.tonyzampogna.xss.sanitizer.filter.XssFilter

class XssSanitizerGrailsPlugin {
	def version = "0.3"
	def grailsVersion = "2.0 > *"
	def pluginExcludes = [
			"grails-app/controllers/**",
			"grails-app/views/**",
	]

	def title = "Xss Sanitizer Plugin"
	def description = '''\
Grails plugin for sanitizing XSS from the user input.

This plugin uses OWASP ESAPI library to sanitize request parameters. This reduces the risk of dangerous XSS request parameters possibly being rendered on the client.
'''

	def documentation = "http://grails.org/plugin/xss-sanitizer"
	def license = "APACHE"
	def developers = [[name: "Tony Zampogna", email: "tony.zampogna@gmail.com"]]
	def issueManagement = [system: "GitHub", url: "https://github.com/tonyzampogna/XssSanitizer/issues"]
	def scm = [url: "https://github.com/tonyzampogna/XssSanitizer"]

	def doWithWebDescriptor = { xml ->
		/**
		 * Add XssFilter
		 */
		def contextParam = xml.'context-param'
		contextParam[contextParam.size() - 1] + {
			'filter' {
				'filter-name'('xssFilter')
				'filter-class'(XssFilter.name)
			}
		}
		// Add this to the beginning of the filters.
		def filter = xml.'filter'
		filter[filter.size() - 1] + {
			'filter-mapping' {
				'filter-name'('xssFilter')
				'url-pattern'('/*')
			}
		}
		//println(xml)
	}

}