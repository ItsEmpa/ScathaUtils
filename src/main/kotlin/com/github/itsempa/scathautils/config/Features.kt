package com.github.itsempa.scathautils.config

import at.hannibal2.skyhanni.deps.moulconfig.Config
import at.hannibal2.skyhanni.deps.moulconfig.annotations.Category
import com.github.itsempa.scathautils.ScathaUtils
import com.github.itsempa.scathautils.ScathaUtils.managedConfig

class Features : Config() {
    override fun shouldAutoFocusSearchbar(): Boolean = true

    override fun getTitle(): String = "${ScathaUtils.MOD_NAME} ${ScathaUtils.VERSION}"

    override fun saveNow() = managedConfig.saveToFile()

    @Category(name = "Example", desc = "")
    var exampleCategory: ExampleCategory = ExampleCategory()
}
