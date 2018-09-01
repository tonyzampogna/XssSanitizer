grails.project.work.dir = 'target'
grails.project.target.level = 1.6

grails.project.dependency.resolver = "maven"
grails.project.dependency.resolution = {

	inherits 'global'
	log 'warn'

	repositories {
		grailsPlugins()
		grailsHome()
		mavenLocal()
		grailsCentral()
		mavenCentral()
	}

	dependencies {
		compile('org.owasp.esapi:esapi:2.1.0.1') {
			excludes 'antisamy', 'bsh-core', 'commons-beanutils-core', 'commons-collections', 'commons-configuration',
			         'commons-fileupload', 'commons-io', 'jsp-api', 'junit', 'log4j', 'servlet-api', 'xom'
		}
	}

	plugins {
		build ':tomcat:7.0.54', ':release:3.1.1', ':rest-client-builder:2.1.1', {
			export = false
		}
	}
	
}
