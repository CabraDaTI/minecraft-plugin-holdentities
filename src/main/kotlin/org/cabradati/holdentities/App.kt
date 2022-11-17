package org.cabradati.holdentities

import org.bukkit.entity.EntityType
import org.bukkit.plugin.java.JavaPlugin

class App : JavaPlugin() {

    override fun onEnable() {

        val config = ConfigEntity(config)
        config.addDefaults(EntityType.values().toList())
        config.copyDefaults(true)
        saveConfig()

        config.holdableEntitys.forEach(this::registerHoldEvent)
        server.pluginManager.registerEvents(LeaveEntity(), this)

        super.onEnable()
    }

    private fun registerHoldEvent(entityType: EntityType) {
        server.pluginManager.registerEvents(HoldEntityEvent(entityType), this)
    }
}