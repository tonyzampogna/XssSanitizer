import org.tonyzampogna.xss.sanitizer.filter.XssFilter

class XssSanitizerGrailsPlugin {
	// the plugin version
	def version = "0.1"
	// the version or versions of Grails the plugin is designed for
	def grailsVersion = "2.0 > *"
	// the other plugins this plugin depends on
	def dependsOn = [:]
	// resources that are excluded from plugin packaging
	def pluginExcludes = [
			"grails-app/views/error.gsp"
	]

	def title = "Xss Sanitizer Plugin" // Headline display name of the plugin
	def author = "Tony Zampogna"
	def authorEmail = "tony.zampogna@gmail.com"
	def description = '''\
Grails plugin for sanitizing XSS from the user input.

This plugin uses OWASP ESAPI library to sanitize request parameters. This reduces the risk of dangerous XSS request parameters possibly being rendered on the client.
'''

	// URL to the plugin's documentation
	def documentation = "http://grails.org/plugin/xss-sanitizer"

	// License: one of 'APACHE', 'GPL2', 'GPL3'
	def license = "APACHE"

	// Any additional developers beyond the author specified above.
	def developers = [ [ name: "Tony Zampogna", email: "tony.zampogna@gmail.com" ]]

	// Location of the plugin's issue tracker.
	def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPXSSSANITIZER" ]

	// Online location of the plugin's browseable source code.
	def scm = [ url: "https://github.com/tonyzampogna/XssSanitizer" ]

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

	def doWithSpring = {
	}

	def doWithDynamicMethods = { ctx ->
	}

	def doWithApplicationContext = { applicationContext ->
	}

	def onChange = { event ->
	}

	def onConfigChange = { event ->
	}

	def onShutdown = { event ->
	}
}
