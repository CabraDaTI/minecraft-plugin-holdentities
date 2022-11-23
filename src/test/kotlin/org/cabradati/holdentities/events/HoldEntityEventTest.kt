package org.cabradati.holdentities.events

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.Sheep
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.inventory.ItemStack
import org.cabradati.holdentities.DIContainer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class HoldEntityEventTest {

    @MockK
    lateinit var evento: PlayerInteractEntityEvent

    @MockK
    lateinit var diContainer: DIContainer

    @MockK
    lateinit var player: Player

    @MockK
    lateinit var entidade: Sheep

    @Test
    @DisplayName("deve conseguir segurar quando o slot esta vazio e sem passageiros")
    fun slotSemItemESemPassageiros() {
        every { evento.player } returns player
        every { player.passengers } returns listOf()
        every { player.equipment.itemInMainHand } returns ItemStack(Material.AIR, 0)
        every { player.addPassenger(any()) } returns true

        every { evento.rightClicked } returns entidade
        every { entidade.type } returns EntityType.SHEEP

        every { diContainer.config.getBoolean(any()) } returns true

        val listener = HoldEntityEvent(diContainer)
        listener.onHoldEntity(evento)

        verify(exactly = 1) { player.addPassenger(any()) }

    }

    @Test
    @DisplayName("nao deve conseguir segurar quando o slot esta com item")
    fun slotComItem() {

        every { evento.player } returns player
        every { player.passengers } returns listOf()
        every { player.equipment.itemInMainHand } returns ItemStack(Material.SALMON, 1)
        every { player.addPassenger(any()) } returns true

        every { evento.rightClicked } returns entidade
        every { entidade.type } returns EntityType.SHEEP

        every { diContainer.config.getBoolean(any()) } returns true

        val listener = HoldEntityEvent(diContainer)
        listener.onHoldEntity(evento)

        verify(exactly = 0) { player.addPassenger(any()) }

    }

    @Test
    @DisplayName("nao deve conseguir segurar quando se tem um passageiro")
    fun comPassageiro() {

        every { evento.player } returns player
        every { player.passengers } returns listOf(entidade)
        every { player.equipment.itemInMainHand } returns ItemStack(Material.SALMON, 1)
        every { player.addPassenger(any()) } returns true

        every { evento.rightClicked } returns entidade
        every { entidade.type } returns EntityType.SHEEP

        every { diContainer.config.getBoolean(any()) } returns true

        val listener = HoldEntityEvent(diContainer)
        listener.onHoldEntity(evento)

        verify(exactly = 0) { player.addPassenger(any()) }

    }

    @Test
    @DisplayName("nao deve conseguir segurar quando a entidade esta desabilitada")
    fun toggleDesativado() {

        every { evento.player } returns player
        every { player.passengers } returns listOf()
        every { player.equipment.itemInMainHand } returns ItemStack(Material.AIR, 0)
        every { player.addPassenger(any()) } returns true

        every { evento.rightClicked } returns entidade
        every { entidade.type } returns EntityType.SHEEP

        every { diContainer.config.getBoolean(any()) } returns false

        val listener = HoldEntityEvent(diContainer)
        listener.onHoldEntity(evento)

        verify(exactly = 0) { player.addPassenger(any()) }

    }

}