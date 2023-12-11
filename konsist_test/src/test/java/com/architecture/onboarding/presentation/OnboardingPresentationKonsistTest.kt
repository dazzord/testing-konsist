package com.architecture.onboarding.presentation

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import org.junit.jupiter.api.Test

open class OnboardingPresentationKonsistTest{


   private val PRESENTATION_LAYER = "com.tydev.onboarding.presentation.."
   private val DOMAIN_LAYER = "com.tydev.onboarding.domain.."

   private val onboardingPresentationModule = Konsist.scopeFromProduction()

   private val appArchitecture = architecture{
        val layerOnboardingPresentation = Layer("PRESENTATION",PRESENTATION_LAYER)

        val onboardingDomain = Layer("DOMAIN",DOMAIN_LAYER)

       layerOnboardingPresentation.dependsOn(onboardingDomain)
       onboardingDomain.dependsOnNothing()
   }

    @Test
    fun `architecture layers of app module have dependencies correct`(){
        onboardingPresentationModule.assertArchitecture(appArchitecture)
    }
}