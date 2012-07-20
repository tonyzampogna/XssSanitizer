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

	// TODO Fill in these fields
	def title = "Xss Sanitizer Plugin" // Headline display name of the plugin
	def author = "Tony Zampogna"
	def authorEmail = "tony.zampogna@gmail.com"
	def description = '''\
This plugin adds filters that parse every request parameter and excludes
any possible XSS that has been injected.
'''

	// URL to the plugin's documentation
	def documentation = "http://grails.org/plugin/xss-sanitizer"

	// Extra (optional) plugin metadata

	// License: one of 'APACHE', 'GPL2', 'GPL3'
//    def license = "APACHE"

	// Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

	// Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

	// Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

	// Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.grails-plugins.codehaus.org/browse/grails-plugins/" ]

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
