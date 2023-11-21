package com.architecture.app

import com.architecture.common.Global
import com.architecture.usecases.UseCaseKonsistTest
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import java.util.stream.Stream

class AppArchKonsistTest: UseCaseKonsistTest(){

   private val appModuleScope = Konsist.scopeFromProject()

   private val appArchitecture = architecture{
        val layerApp = Layer(Global.ModuleNames.appModule, Global.ModulePackages.appPackage)

        val layerCore = Layer(
            Global.ModuleNames.coreModule,
            Global.ModulePackages.corePackage
        )

        val layerCoreUi = Layer(
            Global.ModuleNames.coreUiModule,
            Global.ModulePackages.coreUiPackage
        )

       val layerAny = Layer(
           "doos",
           "com.tydev.core.datastore.."
       )

        layerApp.dependsOn(layerAny)
        layerApp.dependsOn(layerCoreUi)
        layerCore.dependsOnNothing()
   }

    @Test
    fun `architecture layers of app module have dependencies correct`(){
        appModuleScope.assertArchitecture(appArchitecture)
    }
}