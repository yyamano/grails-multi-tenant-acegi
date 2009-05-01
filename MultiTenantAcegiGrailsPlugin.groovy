import com.infusion.tenant.acegi.AcegiCurrentTenant
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class MultiTenantAcegiGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.1 > *"
    // the other plugins this plugin depends on
    //def dependsOn = [acegi:"0.5.1", multiTenant:"0.6"]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def author = "Eric Martineau"
    def authorEmail = ""
    def title = "Integrates the multi-tenant plugin with acegi, so the current tenant can be determined from the authenticated principal"
    def description = '''\\
Provides a custom CurrentTenant implementation that uses the security context to identify the current tenant.  This allows all users to login from 
one url instead of having to remember a special url to log in to
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/MultiTenantAcegi+Plugin"

    def doWithSpring = {
      if (ConfigurationHolder.config.tenant.resolver.type == "acegi") {
        currentTenant(AcegiCurrentTenant)
      }
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
}
