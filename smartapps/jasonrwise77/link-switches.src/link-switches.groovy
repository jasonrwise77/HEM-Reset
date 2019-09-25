/**
 *  Link Switches
 *
 *  Link state between a switches and a locks and switches with presence
 *
 *  
 *  
 */

definition(	
    name: "Link Switches",
	namespace: "jasonrwise77",
	author: "Jason Wise",
	description: "Link state between a switches and a locks and switches with presence",
	category: "Convenience",
/**
 *  Link Switches - Presence
 *
 *  Link state between a switch and a presence sensor
 *
 *
 */

definition(
	name: "Link Switches - Presence",
	namespace: "jasonrwise77",
	author: "Jason Wise",
	parent: "jasonrwise77:Link Switches",
	description: "Link state between a switch and a presence sensor",
	category: "Convenience",
    	iconUrl: "https://raw.githubusercontent.com/jasonrwise77/My-SmartThings/master/icons/Push%20Events.png",
   	iconX2Url: "https://raw.githubusercontent.com/jasonrwise77/My-SmartThings/master/icons/Push%20Events.png",
   	iconX3Url: "https://raw.githubusercontent.com/jasonrwise77/My-SmartThings/master/icons/Push%20Events.png"
)

preferences {
	section("When This Switch Changes") {
		input "switch1", "capability.switch", multiple: false, required: false
     }
    section("Change This Presence") {
		input "presence1", "capability.presence sensor", multiple: false, required: false
        }
}    

def installed()
{   
	subscribe(switch1, "switch.on", onHandler)
	subscribe(switch1, "switch.off", offHandler)
    subscribe(presence1, "presence.present", arrivedHandler)
	subscribe(presence1, "presence.not present", departedHandler)

}

def updated()
{
	unsubscribe()
	subscribe(switch1, "switch.on", onHandler)
	subscribe(switch1, "switch.off", offHandler)
    subscribe(presence1, "presence.present", arrivedHandler)
	subscribe(presence1, "presence.not present", departedHandler)

}

def onHandler(evt) {
	log.debug evt.value
	log.debug "Changing presence to arrived: $presence1"
    presence1.arrived()
}

def offHandler(evt) {
	log.debug evt.value
	log.debug "Changing presence to departed: $presence1"
    presence1.departed()
}
def arrivedHandler(evt) {
	log.debug evt.value
	log.debug "Turning on switch: $switch1"
   	switch1.on()
}
def departedHandler(evt) {
	log.debug evt.value
	log.debug "Turning off switch: $switch1"
   	switch1.off()
}"
)

preferences {
	page(name: "main")
}

page name: "main"
            def main() {
                dynamicPage (name: "main", title: "", install: true, uninstall: true) { 
                section("Links",  uninstall: false){
               app(name: "links", appName: "Link Switches - Locks", namespace: "jasonrwise77", title: "Create a new lock link", multiple: true,  uninstall: false)
               app(name: "link", appName: "Link Switches - Presence", namespace: "jasonrwise77", title: "Create a new presence link", multiple: true,  uninstall: false)
                        }
                    }
}
