/**
* Switches link
*/
definition(
name: "Switches link",
namespace: "Lightnjac",
author: "Jason Craig",
description: "Link one switch to another or one switch to many.",
category: "Convenience",
iconUrl: "https://s3.amazonaws.com/smartapp-icons/Meta/light_outlet.png",
iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Meta/light_outlet@2x.png"
)

/**********************************************************************************************************************************************/
preferences {
	page(name: "main")
}

page name: "main"
            def main() {
                dynamicPage (name: "main", title: "", install: true, uninstall: true) { 
                section("Switches",  uninstall: false){
                app(name: "profiles", appName: "Switches", namespace: "Lightnjac", title: "Create a new Switch Profile", multiple: true,  uninstall: false, image:"https://s3.amazonaws.com/smartapp-icons/Meta/light_outlet.png")
                 }
		 }
}

/************************************************************************************************************
		Base Process
************************************************************************************************************/
def installed() {
	if (debug) log.debug "Installed with settings: ${settings}"
    initialize()
}
def updated() { 
	if (debug) log.debug "Updated with settings: ${settings}"
    unsubscribe()
    initialize()
}
def initialize() {
		state.esProfiles = state.esProfiles ? state.esProfiles : []
        def children = getChildApps()
}

def getProfileList(){
		return getChildApps()*.label
}
