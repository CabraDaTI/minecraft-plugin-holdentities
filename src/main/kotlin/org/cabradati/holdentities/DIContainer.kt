package org.cabradati.holdentities

import org.bukkit.Server
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.java.JavaPlugin

data class DIContainer(
    val plugin: JavaPlugin,
    val server: Server,
    val config: FileConfiguration
)
