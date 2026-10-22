package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.model.PortfolioProject
import com.example.model.StudioService
import com.example.ui.StudioTab
import com.example.ui.StudioViewModel
import com.example.ui.components.GradientButton
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectDetailSheet(
    project: PortfolioProject,
    onDismiss: () -> Unit,
    sheetState: SheetState,
    viewModel: StudioViewModel
) {
    val context = LocalContext.current

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = StudioDarkBg,
        dragHandle = null
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
                .testTag("project_detail_sheet")
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = project.client.uppercase(),
                        fontFamily = JetBrainsMono,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = Color(project.accentColorHex)
                    )
                    Text(
                        text = project.categoryLabel,
                        fontFamily = PlusJakartaSans,
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }

                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = TextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = project.title,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = project.tagline,
                fontFamily = PlusJakartaSans,
                fontSize = 13.sp,
                lineHeight = 18.sp,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Specs Matrix
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(StudioCardBg)
                    .border(1.dp, StudioCardBorder, RoundedCornerShape(12.dp))
                    .padding(14.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("PRODUCTION FORMAT", fontFamily = JetBrainsMono, fontSize = 10.sp, color = TextMuted)
                        Text(project.format, fontFamily = JetBrainsMono, fontSize = 11.sp, color = AmberGold)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("FINISH / STACK", fontFamily = JetBrainsMono, fontSize = 10.sp, color = TextMuted)
                        Text(project.finishOrStack, fontFamily = JetBrainsMono, fontSize = 11.sp, color = TextPrimary)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("PRODUCTION TIMELINE", fontFamily = JetBrainsMono, fontSize = 10.sp, color = TextMuted)
                        Text(project.timeline, fontFamily = JetBrainsMono, fontSize = 11.sp, color = EmeraldActive)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("SOFTWARE ENGINE", fontFamily = JetBrainsMono, fontSize = 10.sp, color = TextMuted)
                        Text(project.toolsUsed, fontFamily = JetBrainsMono, fontSize = 11.sp, color = ElectricViolet)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Master Deliverables
            Text(
                text = "PRODUCTION DELIVERABLES",
                fontFamily = JetBrainsMono,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = AmberGold
            )
            Spacer(modifier = Modifier.height(8.dp))

            project.deliverables.forEach { item ->
                Row(
                    modifier = Modifier.padding(vertical = 3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = EmeraldActive,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = item,
                        fontFamily = PlusJakartaSans,
                        fontSize = 12.sp,
                        color = TextPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Actions
            GradientButton(
                text = "Request Similar Commission",
                onClick = {
                    onDismiss()
                    val msg = "Salam Creative Design Studio! I saw your ${project.title} (${project.client}) case study and would like to commission a similar project."
                    val encoded = URLEncoder.encode(msg, StandardCharsets.UTF_8.toString())
                    openWhatsApp(context, StudioRepository.studioWhatsappClean, encoded)
                },
                testTag = "project_whatsapp_btn",
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Chat,
                        contentDescription = null,
                        tint = Color(0xFF08090E),
                        modifier = Modifier.size(16.dp)
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceDetailSheet(
    service: StudioService,
    onDismiss: () -> Unit,
    sheetState: SheetState,
    viewModel: StudioViewModel
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = StudioDarkBg,
        dragHandle = null
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
                .testTag("service_detail_sheet")
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = service.shortTag.uppercase(),
                        fontFamily = JetBrainsMono,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = AmberGold
                    )
                    Text(
                        text = service.name,
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = TextPrimary
                    )
                }

                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = TextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = service.description,
                fontFamily = PlusJakartaSans,
                fontSize = 13.sp,
                lineHeight = 18.sp,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Pricing & Timeline
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(StudioCardBg)
                    .border(1.dp, StudioCardBorder, RoundedCornerShape(12.dp))
                    .padding(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("STARTING INVESTMENT", fontFamily = JetBrainsMono, fontSize = 9.sp, color = TextMuted)
                        Text(service.startingPrice, fontFamily = JetBrainsMono, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = AmberGold)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text("STANDARD TURNAROUND", fontFamily = JetBrainsMono, fontSize = 9.sp, color = TextMuted)
                        Text(service.estimatedTurnaround, fontFamily = JetBrainsMono, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = ElectricViolet)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Features Breakdown
            Text(
                text = "SPECIALIZED FEATURES",
                fontFamily = JetBrainsMono,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = AmberGold
            )
            Spacer(modifier = Modifier.height(8.dp))

            service.features.forEach { feat ->
                Column(modifier = Modifier.padding(vertical = 4.dp)) {
                    Text(feat.title, fontFamily = PlusJakartaSans, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = TextPrimary)
                    Text(feat.description, fontFamily = PlusJakartaSans, fontSize = 12.sp, color = TextSecondary, lineHeight = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Deliverables Checklist
            Text(
                text = "STANDARD MASTER DELIVERABLES",
                fontFamily = JetBrainsMono,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = AmberGold
            )
            Spacer(modifier = Modifier.height(8.dp))

            service.deliverables.forEach { deliv ->
                Row(modifier = Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = EmeraldActive, modifier = Modifier.size(13.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(deliv, fontFamily = PlusJakartaSans, fontSize = 12.sp, color = TextPrimary)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            GradientButton(
                text = "Open in Price Calculator",
                onClick = {
                    onDismiss()
                    viewModel.selectCalcService(service)
                    viewModel.selectTab(StudioTab.ESTIMATOR)
                },
                testTag = "service_calc_btn",
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Calculate,
                        contentDescription = null,
                        tint = Color(0xFF08090E),
                        modifier = Modifier.size(16.dp)
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
