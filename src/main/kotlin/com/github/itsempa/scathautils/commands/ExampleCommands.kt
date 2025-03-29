package com.github.itsempa.scathautils.commands

import at.hannibal2.skyhanni.SkyHanniMod
import at.hannibal2.skyhanni.api.event.HandleEvent
import at.hannibal2.skyhanni.config.commands.CommandBuilder
import at.hannibal2.skyhanni.config.commands.CommandCategory
import at.hannibal2.skyhanni.data.GuiEditManager
import at.hannibal2.skyhanni.deps.moulconfig.gui.GuiScreenElementWrapper
import com.github.itsempa.scathautils.ScathaUtils
import com.github.itsempa.scathautils.events.ExampleCommandRegistrationEvent
import com.github.itsempa.scathautils.modules.Module

@Module
object ExampleCommands {

    private fun getOpenMainMenu(args: Array<String>) {
        if (args.isNotEmpty()) {
            if (args[0].lowercase() == "gui") {
                GuiEditManager.openGuiPositionEditor(hotkeyReminder = true)
            } else openConfigGui(args.joinToString(" "))
        } else openConfigGui()
    }

    val commandsList = mutableListOf<CommandBuilder>()

    @HandleEvent
    fun onCommandRegistration(event: ExampleCommandRegistrationEvent) {
        event.register("scathautils") {
            this.aliases = listOf("scathautilsconfig")
            this.category = CommandCategory.MAIN
            this.description = "Opens the main ${ScathaUtils.MOD_NAME} config"
            callback(::getOpenMainMenu)
        }
        event.register("scathautilscommands") {
            this.description = "Shows this list"
            this.category = CommandCategory.MAIN
            callback(ExampleHelpCommand::onCommand)
        }
        event.register("scathautilssaveconfig") {
            this.description = "Saves the config"
            this.category = CommandCategory.DEVELOPER_TEST
            callback { ScathaUtils.managedConfig.saveToFile() }
        }
    }

    private fun openConfigGui(search: String? = null) {
        val editor = ScathaUtils.managedConfig.getEditor()

        search?.let { editor.search(search) }
        SkyHanniMod.screenToOpen = GuiScreenElementWrapper(editor)
    }
}
