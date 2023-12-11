package com.architecture.app

import com.architecture.generics.UseCasesKonsistTestTemplate
import com.architecture.generics.usecases.UseCasesKonsistTestTemplateImpl
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import org.junit.jupiter.api.Test

class AppArchKonsistTest: UseCasesKonsistTestTemplate by UseCasesKonsistTestTemplateImpl(){

   /*private val appModuleScope = Konsist.scopeFromProduction()

   private val appArchitecture = architecture{


   }

    @Test
    fun `architecture layers of app module have dependencies correct`(){
        appModuleScope.assertArchitecture(appArchitecture)
    }*/


}