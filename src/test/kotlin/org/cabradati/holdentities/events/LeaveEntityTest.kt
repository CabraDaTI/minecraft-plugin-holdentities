package org.cabradati.holdentities.events

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.bukkit.entity.Player
import org.bukkit.entity.Sheep
import org.bukkit.event.player.PlayerToggleSneakEvent
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class LeaveEntityTest {

    @MockK
    lateinit var evento: PlayerToggleSneakEvent

    @MockK
    lateinit var player: Player

    @MockK
    lateinit var ovelha: Sheep

    @Test
    @DisplayName("deve remover entidade caso tenha alguma")
    fun deveRemoverEntidade() {

        every { evento.player } returns player
        every { player.passengers } returns listOf(ovelha)
        every { player.removePassenger(any()) } returns true

        val listener = LeaveEntity()
        listener.onLeaveEntity(evento)

        verify(exactly = 1) { player.removePassenger(any()) }

    }

    @Test
    @DisplayName("deve dispensar remocao quando nao ha nenhuma entidade sendo carregada")
    fun dispensarRemoverEntidade() {

        every { evento.player } returns player
        every { player.passengers } returns listOf()
        every { player.removePassenger(any()) } returns true

        val listener = LeaveEntity()
        listener.onLeaveEntity(evento)

        verify(exactly = 0) { player.removePassenger(any()) }

    }

}