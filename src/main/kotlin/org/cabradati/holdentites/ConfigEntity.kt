package org.cabradati.holdentites

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.EntityType

class ConfigEntity(private val config: FileConfiguration) {

    private val entitysSaved: ArrayList<EntityType> = ArrayList()
    val holdableEntitys: List<EntityType>
        get() = entitysSaved.filter { entityType ->
            config.getBoolean(entityType.key.toString())
        }

    fun addDefaults(entitys: List<EntityType>) {
        entitys
            .filter { entityType -> entityType != EntityType.UNKNOWN }
            .forEach { entityType ->
                config.addDefault(entityType.key.toString(), false)
                entitysSaved.add(entityType)
            }
    }

    fun copyDefaults(bol: Boolean) {
        config.options().copyDefaults(bol)
    }

}