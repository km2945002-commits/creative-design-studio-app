package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.StudioRepository
import com.example.model.ProcessStep
import com.example.model.StudioService
import com.example.model.TechTool
import com.example.ui.StudioTab
import com.example.ui.StudioViewModel
import com.example.ui.components.DesignLabWidget
import com.example.ui.components.GradientButton
import com.example.ui.components.MetricBadge
import com.example.ui.components.SectionTag
import com.example.ui.components.StatusPill
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

@Composable
fun HomeScreen(
    viewModel: StudioViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val previewViewport by viewModel.previewViewport.collectAsState()
    val previewTheme by viewModel.previewTheme.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(StudioDarkBg)
            .testTag("home_screen"),
        contentPadding = PaddingValues(bottom = 90.dp)
    ) {
        // Hero Header & Location
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.app_icon_symbol),
                            contentDescription = "Studio Logo",
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Creative Design Studio",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = TextPrimary
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.LocationOn,
                                    contentDescription = null,
                                    tint = AmberGold,
                                    modifier = Modifier.size(11.dp)
                                )
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = StudioRepository.studioLocation,
                                    fontFamily = PlusJakartaSans,
                                    fontSize = 11.sp,
                                    color = TextSecondary
                                )
                            }
                        }
                    }

                    StatusPill()
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Hero Card with visual banner
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(StudioCardBg)
                        .border(1.dp, StudioCardBorder, RoundedCornerShape(20.dp))
                ) {
                    Column {
                        // Hero Image Banner
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16f / 9f)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.studio_hero_banner),
                                contentDescription = "Studio Showcase Banner",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                            // Subtle gradient overlay
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        Brush.verticalGradient(
                                            listOf(
                                                Color.Transparent,
                                                StudioCardBg.copy(alpha = 0.95f)
                                            )
                                        )
                                    )
                            )
                        }

                        // Headline & Copy
                        Column(modifier = Modifier.padding(18.dp)) {
                            Text(
                                text = "Creative Ideas. Smart AI.\nPowerful Digital Experiences.",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 21.sp,
                                lineHeight = 27.sp,
                                color = TextPrimary
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "We transform your ideas into professional websites, apps, logos, and brand identities using modern AI tools, WordPress, Hostinger, CorelDRAW, and Adobe Illustrator.",
                                fontFamily = PlusJakartaSans,
                                fontSize = 13.sp,
                                lineHeight = 19.sp,
                                color = TextSecondary
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // CTAs
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                GradientButton(
                                    text = "Get a Quote",
                                    onClick = { viewModel.selectTab(StudioTab.ESTIMATOR) },
                                    testTag = "hero_quote_button",
                                    modifier = Modifier.weight(1f),
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.Calculate,
                                            contentDescription = null,
                                            tint = Color(0xFF08090E),
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                )

                                OutlinedButton(
                                    onClick = {
                                        openWhatsApp(context, StudioRepository.studioWhatsappClean, "Salam Creative Design Studio! I saw your portfolio and would like to discuss a new design commission.")
                                    },
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("hero_whatsapp_button"),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        contentColor = EmeraldActive
                                    ),
                                    border = ButtonDefaults.outlinedButtonBorder.copy(
                                        brush = Brush.horizontalGradient(listOf(Color(0xFF1B4D36), EmeraldActive))
                                    )
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Chat,
                                        contentDescription = "WhatsApp",
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "WhatsApp",
                                        fontFamily = PlusJakartaSans,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Metrics Row
        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item { MetricBadge(value = "6+", label = "Core Disciplines") }
                item { MetricBadge(value = "100%", label = "Vector Math") }
                item { MetricBadge(value = "300 DPI", label = "Print Calibration") }
                item { MetricBadge(value = "< 24h", label = "Guaranteed Reply") }
                item { MetricBadge(value = "PKR", label = "Transparent Pricing") }
            }
        }

        // Interactive Design Lab Widget
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                DesignLabWidget(
                    viewport = previewViewport,
                    selectedTheme = previewTheme,
                    onSelectViewport = { viewModel.setPreviewViewport(it) },
                    onSelectTheme = { viewModel.setPreviewTheme(it) }
                )
            }
        }

        // Featured Disciplines Header
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    SectionTag(title = "Disciplines")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Our Design Services",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = TextPrimary
                    )
                }

                Text(
                    text = "View All →",
                    fontFamily = JetBrainsMono,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = AmberGold,
                    modifier = Modifier
                        .clickable { viewModel.selectTab(StudioTab.SERVICES) }
                        .padding(4.dp)
                )
            }
        }

        // Featured Services Horizontal Carousel
        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(StudioRepository.services) { service ->
                    ServiceHighlightCard(
                        service = service,
                        onOpen = { viewModel.openServiceDetail(service) },
                        onQuote = {
                            viewModel.selectCalcService(service)
                            viewModel.selectTab(StudioTab.ESTIMATOR)
                        }
                    )
                }
            }
        }

        // The 4-Step Process Section
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                SectionTag(title = "Workflow")
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Our 4-Step Creative Process",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = TextPrimary
                )
                Text(
                    text = "From initial brief to production-ready vectors and live hosting setup.",
                    fontFamily = PlusJakartaSans,
                    fontSize = 12.sp,
                    color = TextSecondary
                )

                Spacer(modifier = Modifier.height(14.dp))

                StudioRepository.processSteps.forEach { step ->
                    ProcessStepCard(step = step)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

        // Production Tech & Tools Matrix
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                SectionTag(title = "Tech Stack")
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Software & Infrastructure",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(12.dp))

                StudioRepository.techTools.forEach { tool ->
                    TechToolCard(tool = tool)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@Composable
fun ServiceHighlightCard(
    service: StudioService,
    onOpen: () -> Unit,
    onQuote: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(260.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(StudioCardBg)
            .border(1.dp, StudioCardBorder, RoundedCornerShape(16.dp))
            .clickable(onClick = onOpen)
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(service.accentColorHex).copy(alpha = 0.15f))
                        .border(1.dp, Color(service.accentColorHex).copy(alpha = 0.4f), RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(service.accentColorHex),
                        modifier = Modifier.size(18.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF141724))
                        .padding(horizontal = 7.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = service.shortTag,
                        fontFamily = JetBrainsMono,
                        fontSize = 10.sp,
                        color = AmberGold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = service.name,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = service.description,
                fontFamily = PlusJakartaSans,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                color = TextSecondary,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(StudioDarkBg)
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "STARTING FROM",
                        fontFamily = JetBrainsMono,
                        fontSize = 9.sp,
                        color = TextMuted
                    )
                    Text(
                        text = service.startingPrice,
                        fontFamily = JetBrainsMono,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = AmberGold
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(AmberGold)
                        .clickable(onClick = onQuote)
                        .padding(horizontal = 8.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = "Quote",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = Color(0xFF08090E)
                    )
                }
            }
        }
    }
}

@Composable
fun ProcessStepCard(step: ProcessStep) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(StudioCardBg)
            .border(1.dp, StudioCardBorder, RoundedCornerShape(14.dp))
            .padding(14.dp)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF261D12)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = step.number,
                    fontFamily = JetBrainsMono,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = AmberGold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = step.title,
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = TextPrimary
                    )
                    Text(
                        text = step.tagline,
                        fontFamily = JetBrainsMono,
                        fontSize = 10.sp,
                        color = ElectricViolet
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = step.description,
                    fontFamily = PlusJakartaSans,
                    fontSize = 12.sp,
                    lineHeight = 17.sp,
                    color = TextSecondary
                )

                Spacer(modifier = Modifier.height(8.dp))

                step.inclusions.forEach { inc ->
                    Row(
                        modifier = Modifier.padding(vertical = 1.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = EmeraldActive,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = inc,
                            fontFamily = PlusJakartaSans,
                            fontSize = 11.sp,
                            color = TextPrimary
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TechToolCard(tool: TechTool) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(StudioCardBg)
            .border(1.dp, StudioCardBorder, RoundedCornerShape(14.dp))
            .padding(14.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = tool.name,
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = TextPrimary
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF201633))
                        .padding(horizontal = 7.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = tool.badge,
                        fontFamily = JetBrainsMono,
                        fontSize = 10.sp,
                        color = NeonRose
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = tool.description,
                fontFamily = PlusJakartaSans,
                fontSize = 12.sp,
                color = TextSecondary,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            tool.workflowUse.forEach { item ->
                Text(
                    text = "• $item",
                    fontFamily = JetBrainsMono,
                    fontSize = 10.sp,
                    color = TextMuted,
                    modifier = Modifier.padding(vertical = 1.dp)
                )
            }
        }
    }
}

fun openWhatsApp(context: Context, phoneClean: String, messageText: String) {
    try {
        val uri = Uri.parse("https://api.whatsapp.com/send?phone=$phoneClean&text=$messageText")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "Could not open WhatsApp: +$phoneClean", Toast.LENGTH_SHORT).show()
    }
}
