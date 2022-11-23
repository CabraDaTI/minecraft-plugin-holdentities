package org.cabradati.holdentities.events

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.cabradati.holdentities.App
import org.cabradati.holdentities.DIContainer

class HoldEntityEvent(
    private val diContainer: DIContainer
) : Listener {

    @EventHandler(priority = EventPriority.LOW)
    fun onHoldEntity(event: PlayerInteractEntityEvent) {

        val toggleDaEntidade = diContainer.config.getBoolean(App.PREFIXO_ENTIDADE_SEGURAVEL + event.rightClicked.type)
        if (!(isHoldable(event.player) && toggleDaEntidade)) return

        val player = event.player
        val entity = event.rightClicked
        player.addPassenger(entity)

    }

    private fun isHoldable(player: Player): Boolean {
        return player.passengers.isEmpty() &&
                player.equipment.itemInMainHand.amount == 0
    }

}