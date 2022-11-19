package org.cabradati.holdentities.events

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerToggleSneakEvent

class LeaveEntity : Listener {

    @EventHandler(priority = EventPriority.LOW)
    fun onLeaveEntity(event: PlayerToggleSneakEvent) {

        if (event.player.passengers.isEmpty()) return

        event.player.removePassenger(event.player.passengers[0])

    }

}