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
	iconUrl: "https://s3.amazonaws.com/smartapp-icons/Solution/doors-locks.png",
	iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Solution/doors-locks@2x.png"
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
}
