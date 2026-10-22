package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.StudioRepository
import com.example.model.IndustrySolution
import com.example.model.StudioService
import com.example.ui.StudioTab
import com.example.ui.StudioViewModel
import com.example.ui.components.SectionTag
import com.example.ui.theme.AmberGold
import com.example.ui.theme.ElectricViolet
import com.example.ui.theme.EmeraldActive
import com.example.ui.theme.JetBrainsMono
import com.example.ui.theme.NeonRose
import com.example.ui.theme.PlusJakartaSans
import com.example.ui.theme.StudioCardBg
import com.example.ui.theme.StudioCardBorder
import com.example.ui.theme.StudioDarkBg
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun ServicesScreen(
    viewModel: StudioViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var selectedSectionIndex by remember { mutableStateOf(0) }
    val sectionTabs = listOf("Core Disciplines", "Industry Solutions")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(StudioDarkBg)
            .testTag("services_screen")
    ) {
        // Top Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            SectionTag(title = "Our Capabilities")
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Services & Solutions",
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                color = TextPrimary
            )
            Text(
                text = "Six premium creative disciplines with strict vector precision and transparent PKR pricing.",
                fontFamily = PlusJakartaSans,
                fontSize = 12.sp,
                color = TextSecondary
            )
        }

        // Section Tabs
        TabRow(
            selectedTabIndex = selectedSectionIndex,
            containerColor = StudioCardBg,
            contentColor = AmberGold,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedSectionIndex]),
                    color = AmberGold
                )
            },
            modifier = Modifier.padding(horizontal = 16.dp).clip(RoundedCornerShape(10.dp))
        ) {
            sectionTabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedSectionIndex == index,
                    onClick = { selectedSectionIndex = index },
                    modifier = Modifier.testTag("services_tab_$index"),
                    text = {
                        Text(
                            text = title,
                            fontFamily = PlusJakartaSans,
                            fontWeight = if (selectedSectionIndex == index) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 13.sp,
                            color = if (selectedSectionIndex == index) AmberGold else TextSecondary
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Content
        if (selectedSectionIndex == 0) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 90.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(StudioRepository.services) { service ->
                    FullServiceCard(
                        service = service,
                        onOpenDetail = { viewModel.openServiceDetail(service) },
                        onConfigure = {
                            viewModel.selectCalcService(service)
                            viewModel.selectTab(StudioTab.ESTIMATOR)
                        }
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 90.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(StudioRepository.industrySolutions) { solution ->
                    IndustrySolutionCard(
                        solution = solution,
                        onWhatsApp = {
                            val msg = "Salam Creative Design Studio! I am interested in your ${solution.title} package (${solution.subtitle}) for my business."
                            val encoded = URLEncoder.encode(msg, StandardCharsets.UTF_8.toString())
                            openWhatsApp(context, StudioRepository.studioWhatsappClean, encoded)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FullServiceCard(
    service: StudioService,
    onOpenDetail: () -> Unit,
    onConfigure: () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(StudioCardBg)
            .border(1.dp, StudioCardBorder, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(service.accentColorHex).copy(alpha = 0.15f))
                            .border(1.dp, Color(service.accentColorHex).copy(alpha = 0.4f), RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(service.accentColorHex),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = service.name,
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = TextPrimary
                        )
                        Text(
                            text = service.shortTag,
                            fontFamily = JetBrainsMono,
                            fontSize = 11.sp,
                            color = AmberGold
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(StudioDarkBg)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = service.estimatedTurnaround,
                        fontFamily = JetBrainsMono,
                        fontSize = 10.sp,
                        color = ElectricViolet
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = service.description,
                fontFamily = PlusJakartaSans,
                fontSize = 12.sp,
                lineHeight = 17.sp,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Pricing & Tech row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(StudioDarkBg)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "STARTING INVESTMENT",
                        fontFamily = JetBrainsMono,
                        fontSize = 9.sp,
                        color = TextMuted
                    )
                    Text(
                        text = service.startingPrice,
                        fontFamily = JetBrainsMono,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = AmberGold
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    service.techOrMaterials.take(2).forEach { tech ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color(0xFF1E2235))
                                .padding(horizontal = 6.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = tech,
                                fontFamily = JetBrainsMono,
                                fontSize = 9.sp,
                                color = TextSecondary
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Deliverables list (expandable or preview)
            Column(modifier = Modifier.fillMaxWidth()) {
                val previewDeliverables = if (isExpanded) service.deliverables else service.deliverables.take(3)
                previewDeliverables.forEach { item ->
                    Row(
                        modifier = Modifier.padding(vertical = 2.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = EmeraldActive,
                            modifier = Modifier.size(13.dp).padding(top = 2.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = item,
                            fontFamily = PlusJakartaSans,
                            fontSize = 11.sp,
                            color = TextPrimary,
                            lineHeight = 15.sp
                        )
                    }
                }

                if (service.deliverables.size > 3) {
                    Text(
                        text = if (isExpanded) "Show Less ↑" else "+ ${service.deliverables.size - 3} more deliverables ↓",
                        fontFamily = JetBrainsMono,
                        fontSize = 11.sp,
                        color = AmberGold,
                        modifier = Modifier
                            .clickable { isExpanded = !isExpanded }
                            .padding(top = 4.dp, bottom = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Action row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onOpenDetail,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = TextPrimary),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = androidx.compose.ui.graphics.SolidColor(StudioCardBorder)
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Full Specs",
                        fontFamily = PlusJakartaSans,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                OutlinedButton(
                    onClick = onConfigure,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = AmberGold),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = androidx.compose.ui.graphics.SolidColor(AmberGold)
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Calculate,
                        contentDescription = null,
                        tint = AmberGold,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Calculate",
                        fontFamily = PlusJakartaSans,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun IndustrySolutionCard(
    solution: IndustrySolution,
    onWhatsApp: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(StudioCardBg)
            .border(1.dp, StudioCardBorder, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = solution.title,
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = TextPrimary
                    )
                    Text(
                        text = solution.subtitle,
                        fontFamily = JetBrainsMono,
                        fontSize = 11.sp,
                        color = NeonRose
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF261D12))
                        .padding(horizontal = 7.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = solution.badge,
                        fontFamily = JetBrainsMono,
                        fontSize = 10.sp,
                        color = AmberGold
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = solution.description,
                fontFamily = PlusJakartaSans,
                fontSize = 12.sp,
                lineHeight = 17.sp,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(StudioDarkBg)
                    .padding(10.dp)
            ) {
                Text(
                    text = "KEY ARCHITECTURE & FEATURES:",
                    fontFamily = JetBrainsMono,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextMuted
                )
                Spacer(modifier = Modifier.height(4.dp))
                solution.keyFeatures.forEach { feature ->
                    Text(
                        text = "• $feature",
                        fontFamily = PlusJakartaSans,
                        fontSize = 11.sp,
                        color = TextPrimary,
                        modifier = Modifier.padding(vertical = 1.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Recommended: ${solution.recommendedTech}",
                fontFamily = JetBrainsMono,
                fontSize = 10.sp,
                color = ElectricViolet
            )
            Text(
                text = "Ideal for: ${solution.idealFor}",
                fontFamily = PlusJakartaSans,
                fontSize = 11.sp,
                color = TextMuted
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = onWhatsApp,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = EmeraldActive),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = androidx.compose.ui.graphics.SolidColor(EmeraldActive)
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Chat,
                    contentDescription = null,
                    modifier = Modifier.size(15.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Inquire on WhatsApp for ${solution.badge}",
                    fontFamily = PlusJakartaSans,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
