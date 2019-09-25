/**
 *  Link Switches - Locks
 *
 *  Link state between a switch and a lock
 *

 */

definition(
	name: "Link Switches - Locks",
	namespace: "jasonrwise77",
	author: "Jason Wise",
	parent: "jasonrwise77:Link Switches",
	description: "Link state between a switch and a lock",
	category: "Convenience",
    	iconUrl: "https://raw.githubusercontent.com/jasonrwise77/My-SmartThings/master/icons/Push%20Events.png",
    	iconX2Url: "https://raw.githubusercontent.com/jasonrwise77/My-SmartThings/master/icons/Push%20Events.png",
    	iconX3Url: "https://raw.githubusercontent.com/jasonrwise77/My-SmartThings/master/icons/Push%20Events.png"
)

preferences {
	section("When This Switch Changes") {
		input "switch1", "capability.switch", multiple: false, required: false
	}
	section("Lock This Lock") {
		input "lock1", "capability.lock", multiple: false, required: false
     }
}    

def installed()
{   
	subscribe(switch1, "switch.on", onHandler)
	subscribe(switch1, "switch.off", offHandler)
	subscribe(lock1, "lock.locked", lockedHandler)
	subscribe(lock1, "lock.unlocked", unlockedHandler)
}

def updated()
{
	unsubscribe()
	subscribe(switch1, "switch.on", onHandler)
	subscribe(switch1, "switch.off", offHandler)
	subscribe(lock1, "lock.locked", lockedHandler)
	subscribe(lock1, "lock.unlocked", unlockedHandler)

}

def onHandler(evt) {
	log.debug evt.value
	log.debug "Locking lock: $lock1"
	lock1.lock()
}

def offHandler(evt) {
	log.debug evt.value
	log.debug "Unlocking lock: $lock1"
	lock1.unlock()
}

def lockedHandler(evt) {
	log.debug evt.value
	log.debug "Turning on switch: $switch1"
   	switch1.on()
}

def unlockedHandler(evt) {
	log.debug evt.value
	log.debug "Turning off switch: $switch1"
   	switch1.off()
}
