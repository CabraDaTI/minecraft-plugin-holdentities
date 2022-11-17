package org.cabradati.holdentites

import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent

class HoldEntityEvent(
    private val entityType: EntityType
) : Listener {

    @EventHandler(priority = EventPriority.LOW)
    fun onHoldEntity(event: PlayerInteractEntityEvent) {
        if (event.rightClicked.type != entityType && isHoldable(event.player)) return

        val player = event.player
        val entity = event.rightClicked
        player.addPassenger(entity)
    }

    private fun isHoldable(player: Player): Boolean {
        return player.passengers.isEmpty() &&
                player.equipment.itemInMainHand.amount == 0
    }

}