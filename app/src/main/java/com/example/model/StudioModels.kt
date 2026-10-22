package com.example.model

data class StudioService(
    val id: String,
    val name: String,
    val shortTag: String,
    val category: String,
    val tagline: String,
    val description: String,
    val heroHighlight: String,
    val deliverables: List<String>,
    val techOrMaterials: List<String>,
    val estimatedTurnaround: String,
    val startingPrice: String,
    val startingPriceNumeric: Int,
    val features: List<ServiceFeature>,
    val accentColorHex: Long = 0xFFF59E0B
)

data class ServiceFeature(
    val title: String,
    val description: String
)

data class PortfolioProject(
    val id: String,
    val title: String,
    val client: String,
    val category: String,
    val categoryLabel: String,
    val tagline: String,
    val toolsUsed: String,
    val deliverables: List<String>,
    val format: String,
    val finishOrStack: String,
    val timeline: String,
    val accentColorHex: Long = 0xFFF59E0B
)

data class IndustrySolution(
    val id: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val keyFeatures: List<String>,
    val recommendedTech: String,
    val idealFor: String,
    val badge: String
)

data class ProcessStep(
    val number: String,
    val title: String,
    val tagline: String,
    val description: String,
    val inclusions: List<String>
)

data class TechTool(
    val name: String,
    val category: String,
    val badge: String,
    val description: String,
    val workflowUse: List<String>
)

data class SavedQuote(
    val id: String,
    val serviceName: String,
    val tierName: String,
    val addOns: List<String>,
    val totalPkr: Int,
    val turnaround: String,
    val timestamp: Long = System.currentTimeMillis()
)
