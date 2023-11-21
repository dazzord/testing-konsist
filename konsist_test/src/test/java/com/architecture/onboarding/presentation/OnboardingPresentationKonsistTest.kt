package com.architecture.onboarding.presentation

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

open class OnboardingPresentationKonsistTest: UseCaseKonsistTest(){

   private val onboardingPresentationModule = Konsist.scopeFromDirectory("com.tydev.onboarding.presentation")

   private val appArchitecture = architecture{
        val layerOnboardingPresentation = Layer(Global.ModuleNames.onboardingPresentation, Global.ModulePackages.onboardingPresentation)

        val onboardingDomain = Layer(
            Global.ModuleNames.onboardingDomain,
            Global.ModulePackages.onboardingDomain
        )

       layerOnboardingPresentation.dependsOn(onboardingDomain)
       onboardingDomain.dependsOnNothing()
   }

    @Test
    fun `architecture layers of app module have dependencies correct`(){
        onboardingPresentationModule.assertArchitecture(appArchitecture)
    }
}