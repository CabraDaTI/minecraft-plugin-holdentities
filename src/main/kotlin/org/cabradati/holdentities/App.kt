package org.cabradati.holdentities

import org.bukkit.entity.EntityType
import org.bukkit.plugin.java.JavaPlugin
import org.cabradati.holdentities.events.HoldEntityEvent
import org.cabradati.holdentities.events.LeaveEntity

class App : JavaPlugin() {

    companion object {
        const val ATIVAR_PLUGIN = "plugin.enabled"
        const val PREFIXO_ENTIDADE_SEGURAVEL = "entities."
    }

    override fun onEnable() {

        logger.info("registrando configurações")
        config.addDefault(ATIVAR_PLUGIN, true)

        EntityType.values().filter { entityType ->
            entityType != EntityType.UNKNOWN
        }.forEach { entityType ->
            config.addDefault(PREFIXO_ENTIDADE_SEGURAVEL + entityType, false)
        }

        config.options().copyDefaults(true)
        saveConfig()

        val diContainer = DIContainer(
            this,
            server,
            config,
            logger
        )

        if (config.getBoolean(ATIVAR_PLUGIN)) {
            logger.info("registrando eventos")
            server.pluginManager.registerEvents(
                HoldEntityEvent(diContainer),
                this
            )

            server.pluginManager.registerEvents(
                LeaveEntity(),
                this
            )
        } else {
            logger.info("plugin está desabilitado, nenhum evento será registrado")
        }

        super.onEnable()

    }

}