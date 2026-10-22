package com.example

import com.example.data.StudioRepository
import com.example.ui.CalculatorTier
import com.example.ui.StudioViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun studioRepository_containsAllCoreServices() {
        assertEquals(6, StudioRepository.services.size)
        val serviceIds = StudioRepository.services.map { it.id }
        assertTrue(serviceIds.contains("web-apps"))
        assertTrue(serviceIds.contains("logo-design"))
        assertTrue(serviceIds.contains("business-cards"))
        assertTrue(serviceIds.contains("products-packaging"))
        assertTrue(serviceIds.contains("flex-banner"))
        assertTrue(serviceIds.contains("brand-identity"))
    }

    @Test
    fun studioRepository_containsCaseStudies() {
        assertTrue(StudioRepository.portfolioProjects.isNotEmpty())
        val firstProject = StudioRepository.portfolioProjects.first()
        assertNotNull(firstProject.title)
        assertNotNull(firstProject.client)
        assertTrue(firstProject.deliverables.isNotEmpty())
    }

    @Test
    fun studioViewModel_calculatesTotalAccurately() {
        val vm = StudioViewModel()
        val logoService = StudioRepository.services.first { it.id == "logo-design" }
        vm.selectCalcService(logoService) // base: 45,000
        vm.selectTier(CalculatorTier.STARTER) // 1.0x -> 45,000

        // remove default master_files add-on
        vm.toggleAddOn("master_files")
        vm.toggleRush(false)

        assertEquals(45000, vm.calculateTotalPkr())

        // add master files (+15,000) -> 60,000
        vm.toggleAddOn("master_files")
        assertEquals(60000, vm.calculateTotalPkr())

        // rush +20% -> 60,000 * 1.2 = 72,000
        vm.toggleRush(true)
        assertEquals(72000, vm.calculateTotalPkr())
    }

    @Test
    fun studioViewModel_generatesWhatsAppQuery() {
        val vm = StudioViewModel()
        val text = vm.generateWhatsAppText()
        assertTrue(text.isNotEmpty())
        assertTrue(text.contains("Salam") || text.contains("Creative"))
    }
}
