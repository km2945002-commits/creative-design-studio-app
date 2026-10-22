package com.example.ui

import androidx.lifecycle.ViewModel
import com.example.data.StudioRepository
import com.example.model.PortfolioProject
import com.example.model.SavedQuote
import com.example.model.StudioService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.UUID

enum class StudioTab(val title: String) {
    HOME("Studio"),
    SERVICES("Services"),
    PORTFOLIO("Portfolio"),
    ESTIMATOR("Calculator"),
    CONTACT("Contact")
}

enum class PreviewViewport {
    MOBILE, TABLET, DESKTOP
}

enum class CalculatorTier(val title: String, val multiplier: Double, val badge: String) {
    STARTER("Essential Starter", 1.0, "Core Essentials"),
    PROFESSIONAL("Professional Growth", 1.5, "Most Popular"),
    FLAGSHIP("Flagship Enterprise", 2.2, "Full Production")
}

data class AddOnOption(
    val id: String,
    val title: String,
    val pkrCost: Int,
    val subtitle: String
)

class StudioViewModel : ViewModel() {

    // Tab Navigation
    private val _currentTab = MutableStateFlow(StudioTab.HOME)
    val currentTab: StateFlow<StudioTab> = _currentTab.asStateFlow()

    fun selectTab(tab: StudioTab) {
        _currentTab.value = tab
    }

    // Portfolio Filtering
    private val _selectedPortfolioCategory = MutableStateFlow("all")
    val selectedPortfolioCategory: StateFlow<String> = _selectedPortfolioCategory.asStateFlow()

    private val _searchPortfolioQuery = MutableStateFlow("")
    val searchPortfolioQuery: StateFlow<String> = _searchPortfolioQuery.asStateFlow()

    private val _activeProjectDetail = MutableStateFlow<PortfolioProject?>(null)
    val activeProjectDetail: StateFlow<PortfolioProject?> = _activeProjectDetail.asStateFlow()

    fun setPortfolioCategory(category: String) {
        _selectedPortfolioCategory.value = category
    }

    fun setSearchQuery(query: String) {
        _searchPortfolioQuery.value = query
    }

    fun openProjectDetail(project: PortfolioProject) {
        _activeProjectDetail.value = project
    }

    fun closeProjectDetail() {
        _activeProjectDetail.value = null
    }

    // Active Service Detail Dialog/Sheet
    private val _activeServiceDetail = MutableStateFlow<StudioService?>(null)
    val activeServiceDetail: StateFlow<StudioService?> = _activeServiceDetail.asStateFlow()

    fun openServiceDetail(service: StudioService) {
        _activeServiceDetail.value = service
    }

    fun closeServiceDetail() {
        _activeServiceDetail.value = null
    }

    // Interactive Design Lab (Hero preview)
    private val _previewViewport = MutableStateFlow(PreviewViewport.MOBILE)
    val previewViewport: StateFlow<PreviewViewport> = _previewViewport.asStateFlow()

    private val _previewTheme = MutableStateFlow("Obsidian Dark")
    val previewTheme: StateFlow<String> = _previewTheme.asStateFlow()

    fun setPreviewViewport(viewport: PreviewViewport) {
        _previewViewport.value = viewport
    }

    fun setPreviewTheme(theme: String) {
        _previewTheme.value = theme
    }

    // Calculator / Estimator
    private val _selectedCalcService = MutableStateFlow(StudioRepository.services.first())
    val selectedCalcService: StateFlow<StudioService> = _selectedCalcService.asStateFlow()

    private val _selectedTier = MutableStateFlow(CalculatorTier.PROFESSIONAL)
    val selectedTier: StateFlow<CalculatorTier> = _selectedTier.asStateFlow()

    private val _isRush = MutableStateFlow(false)
    val isRush: StateFlow<Boolean> = _isRush.asStateFlow()

    val availableAddOns = listOf(
        AddOnOption("master_files", "Master Source Files (.CDR & .AI)", 15000, "Layered editable vector files"),
        AddOnOption("3d_mockups", "3D Photorealistic Mockup Pack", 20000, "High-res presentation graphics for social/pitch"),
        AddOnOption("hostinger_setup", "Hostinger Cloud & Domain Linkage", 25000, "SSL security, DNS, business email setup"),
        AddOnOption("seo_speed", "SEO & Speed Optimization (Sub 1s)", 30000, "Schema markup, image compression, CDN")
    )

    private val _selectedAddOnIds = MutableStateFlow<Set<String>>(setOf("master_files"))
    val selectedAddOnIds: StateFlow<Set<String>> = _selectedAddOnIds.asStateFlow()

    fun selectCalcService(service: StudioService) {
        _selectedCalcService.value = service
    }

    fun selectTier(tier: CalculatorTier) {
        _selectedTier.value = tier
    }

    fun toggleRush(enabled: Boolean) {
        _isRush.value = enabled
    }

    fun toggleAddOn(id: String) {
        val current = _selectedAddOnIds.value.toMutableSet()
        if (current.contains(id)) {
            current.remove(id)
        } else {
            current.add(id)
        }
        _selectedAddOnIds.value = current
    }

    fun calculateTotalPkr(): Int {
        val base = _selectedCalcService.value.startingPriceNumeric
        val tierMult = _selectedTier.value.multiplier
        val addOnsCost = availableAddOns
            .filter { _selectedAddOnIds.value.contains(it.id) }
            .sumOf { it.pkrCost }
        val subtotal = (base * tierMult).toInt() + addOnsCost
        return if (_isRush.value) (subtotal * 1.20).toInt() else subtotal
    }

    val savedQuotes = StudioRepository.savedQuotes

    fun saveCurrentQuote() {
        val service = _selectedCalcService.value
        val tier = _selectedTier.value
        val addOnNames = availableAddOns
            .filter { _selectedAddOnIds.value.contains(it.id) }
            .map { it.title }
        val total = calculateTotalPkr()
        val turnaround = if (_isRush.value) "${service.estimatedTurnaround} (Priority Rush)" else service.estimatedTurnaround

        val quote = SavedQuote(
            id = UUID.randomUUID().toString().take(8),
            serviceName = service.name,
            tierName = tier.title,
            addOns = addOnNames,
            totalPkr = total,
            turnaround = turnaround
        )
        StudioRepository.addQuote(quote)
    }

    fun removeSavedQuote(id: String) {
        StudioRepository.removeQuote(id)
    }

    fun generateWhatsAppText(customNote: String = ""): String {
        val service = _selectedCalcService.value
        val tier = _selectedTier.value
        val total = calculateTotalPkr()
        val addOnNames = availableAddOns
            .filter { _selectedAddOnIds.value.contains(it.id) }
            .map { it.title }
            .joinToString(", ")

        val sb = StringBuilder()
        sb.appendLine("Salam Creative Design Studio!")
        sb.appendLine("I am interested in starting a project:")
        sb.appendLine("• Service: ${service.name} (${service.shortTag})")
        sb.appendLine("• Scope Tier: ${tier.title}")
        if (addOnNames.isNotEmpty()) {
            sb.appendLine("• Selected Add-ons: $addOnNames")
        }
        if (_isRush.value) {
            sb.appendLine("• Timeline: Priority Rush Delivery")
        } else {
            sb.appendLine("• Standard Turnaround: ${service.estimatedTurnaround}")
        }
        sb.appendLine("• Estimated Investment: PKR %,d".format(total))
        if (customNote.isNotBlank()) {
            sb.appendLine("• Note: $customNote")
        }
        sb.appendLine("Can we schedule a consultation?")
        return URLEncoder.encode(sb.toString(), StandardCharsets.UTF_8.toString())
    }

    // Contact Form
    var contactName = MutableStateFlow("")
    var contactPhone = MutableStateFlow("")
    var contactEmail = MutableStateFlow("")
    var contactService = MutableStateFlow(StudioRepository.services.first().name)
    var contactBudget = MutableStateFlow("PKR 50,000 - 100,000")
    var contactDescription = MutableStateFlow("")
    var contactSubmitted = MutableStateFlow(false)
    var contactError = MutableStateFlow<String?>(null)

    fun submitContactInquiry(onSuccess: (String) -> Unit) {
        if (contactName.value.isBlank()) {
            contactError.value = "Please provide your name or business name."
            return
        }
        if (contactPhone.value.isBlank() && contactEmail.value.isBlank()) {
            contactError.value = "Please provide your Phone/WhatsApp or Email address."
            return
        }

        contactError.value = null
        contactSubmitted.value = true

        val brief = """
            Salam Creative Design Studio!
            New Project Inquiry:
            • Name: ${contactName.value}
            • Contact: ${contactPhone.value}
            • Email: ${contactEmail.value}
            • Service: ${contactService.value}
            • Budget: ${contactBudget.value}
            • Project Vision: ${contactDescription.value}
        """.trimIndent()

        onSuccess(URLEncoder.encode(brief, StandardCharsets.UTF_8.toString()))
    }
}
