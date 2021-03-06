/**
 *  Ceiling Fan Guru
 *
 *  Copyright 2016, 2017 Thomas Lawson
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
/**********************************************************************************************************************************************/
definition(
    name        : "Ceiling Fan Guru",
    namespace   : "LawsonAutomation",
    author      : "Tom Lawson",
    description : "Control your fan, create new applications within this app",
    category	: "My Apps",
    iconUrl     : "https://raw.githubusercontent.com/lawsonautomation/icons/master/guru-60.png",
    iconX2Url   : "https://raw.githubusercontent.com/lawsonautomation/icons/master/guru-120.png",
    iconX3Url   : "https://raw.githubusercontent.com/lawsonautomation/icons/master/guru-120.png")

/**********************************************************************************************************************************************/
preferences {
	page(name: "main")
}

page name: "main"
            def main() {
                dynamicPage (name: "main", title: "", install: true, uninstall: true) { 
                section("Fan Profiles",  uninstall: false){
               app(name: "profiles", appName: "Ceiling Fans", namespace: "LawsonAutomation", title: "Create a new Fan Profile", multiple: true,  uninstall: false)
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
