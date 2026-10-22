package com.example.ui.screens

import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.ui.CalculatorTier
import com.example.ui.StudioViewModel
import com.example.ui.components.GradientButton
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

@Composable
fun EstimatorScreen(
    viewModel: StudioViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val selectedService by viewModel.selectedCalcService.collectAsState()
    val selectedTier by viewModel.selectedTier.collectAsState()
    val isRush by viewModel.isRush.collectAsState()
    val selectedAddOnIds by viewModel.selectedAddOnIds.collectAsState()
    val totalPkr = viewModel.calculateTotalPkr()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(StudioDarkBg)
            .testTag("estimator_screen"),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        item {
            Column {
                SectionTag(title = "Transparent Estimation")
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Project Cost Calculator",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    color = TextPrimary
                )
                Text(
                    text = "Customize your scope, turnaround speed, and deliverables for an itemized PKR quote.",
                    fontFamily = PlusJakartaSans,
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }
        }

        // Step 1: Select Service
        item {
            Column {
                Text(
                    text = "1. SELECT CORE DISCIPLINE",
                    fontFamily = JetBrainsMono,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = AmberGold
                )
                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(StudioRepository.services) { service ->
                        val isSelected = service.id == selectedService.id
                        Box(
                            modifier = Modifier
                                .testTag("calc_service_${service.id}")
                                .clip(RoundedCornerShape(10.dp))
                                .background(if (isSelected) AmberGold else StudioCardBg)
                                .border(1.dp, if (isSelected) AmberGold else StudioCardBorder, RoundedCornerShape(10.dp))
                                .clickable { viewModel.selectCalcService(service) }
                                .padding(horizontal = 12.dp, vertical = 9.dp)
                        ) {
                            Column {
                                Text(
                                    text = service.shortTag,
                                    fontFamily = PlusJakartaSans,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    color = if (isSelected) Color(0xFF08090E) else TextPrimary
                                )
                                Text(
                                    text = service.startingPrice,
                                    fontFamily = JetBrainsMono,
                                    fontSize = 10.sp,
                                    color = if (isSelected) Color(0xFF08090E).copy(alpha = 0.8f) else TextSecondary
                                )
                            }
                        }
                    }
                }
            }
        }

        // Step 2: Choose Project Scope / Tier
        item {
            Column {
                Text(
                    text = "2. SELECT SCOPE TIER",
                    fontFamily = JetBrainsMono,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = AmberGold
                )
                Spacer(modifier = Modifier.height(8.dp))

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalculatorTier.values().forEach { tier ->
                        val isSelected = selectedTier == tier
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("tier_${tier.name}")
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (isSelected) Color(0xFF261D12) else StudioCardBg)
                                .border(1.dp, if (isSelected) AmberGold else StudioCardBorder, RoundedCornerShape(12.dp))
                                .clickable { viewModel.selectTier(tier) }
                                .padding(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .size(20.dp)
                                            .clip(RoundedCornerShape(5.dp))
                                            .background(if (isSelected) AmberGold else Color(0xFF141724)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        if (isSelected) {
                                            Icon(
                                                imageVector = Icons.Default.Check,
                                                contentDescription = null,
                                                tint = Color(0xFF08090E),
                                                modifier = Modifier.size(14.dp)
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            text = tier.title,
                                            fontFamily = PlusJakartaSans,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 13.sp,
                                            color = TextPrimary
                                        )
                                        Text(
                                            text = tier.badge,
                                            fontFamily = JetBrainsMono,
                                            fontSize = 10.sp,
                                            color = if (isSelected) AmberGold else TextMuted
                                        )
                                    }
                                }

                                Text(
                                    text = "${tier.multiplier}x Scale",
                                    fontFamily = JetBrainsMono,
                                    fontSize = 11.sp,
                                    color = if (isSelected) AmberGold else TextSecondary
                                )
                            }
                        }
                    }
                }
            }
        }

        // Step 3: Production Add-ons
        item {
            Column {
                Text(
                    text = "3. PRODUCTION ADD-ONS",
                    fontFamily = JetBrainsMono,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = AmberGold
                )
                Spacer(modifier = Modifier.height(8.dp))

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    viewModel.availableAddOns.forEach { addOn ->
                        val isChecked = selectedAddOnIds.contains(addOn.id)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("addon_${addOn.id}")
                                .clip(RoundedCornerShape(12.dp))
                                .background(StudioCardBg)
                                .border(1.dp, if (isChecked) ElectricViolet else StudioCardBorder, RoundedCornerShape(12.dp))
                                .clickable { viewModel.toggleAddOn(addOn.id) }
                                .padding(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    modifier = Modifier.weight(1f),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Checkbox(
                                        checked = isChecked,
                                        onCheckedChange = { viewModel.toggleAddOn(addOn.id) },
                                        colors = CheckboxDefaults.colors(
                                            checkedColor = ElectricViolet,
                                            uncheckedColor = TextSecondary,
                                            checkmarkColor = TextPrimary
                                        )
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Column {
                                        Text(
                                            text = addOn.title,
                                            fontFamily = PlusJakartaSans,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 13.sp,
                                            color = TextPrimary
                                        )
                                        Text(
                                            text = addOn.subtitle,
                                            fontFamily = PlusJakartaSans,
                                            fontSize = 11.sp,
                                            color = TextMuted
                                        )
                                    }
                                }

                                Text(
                                    text = "+PKR %,d".format(addOn.pkrCost),
                                    fontFamily = JetBrainsMono,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = ElectricViolet
                                )
                            }
                        }
                    }
                }
            }
        }

        // Step 4: Priority Rush Option
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF1E172B))
                    .border(1.dp, Color(0xFF38235A), RoundedCornerShape(12.dp))
                    .padding(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.FlashOn,
                            contentDescription = null,
                            tint = NeonRose,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Priority Rush Timeline (+20%)",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = TextPrimary
                            )
                            Text(
                                text = "Dedicated design focus, halves standard turnaround time.",
                                fontFamily = PlusJakartaSans,
                                fontSize = 11.sp,
                                color = TextSecondary
                            )
                        }
                    }

                    Switch(
                        checked = isRush,
                        onCheckedChange = { viewModel.toggleRush(it) },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = NeonRose
                        ),
                        modifier = Modifier.testTag("rush_switch")
                    )
                }
            }
        }

        // Total & WhatsApp Action Card
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(
                        androidx.compose.ui.graphics.Brush.verticalGradient(
                            listOf(
                                Color(0xFF1F1C16),
                                StudioCardBg
                            )
                        )
                    )
                    .border(1.5.dp, AmberGold, RoundedCornerShape(18.dp))
                    .padding(18.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "ESTIMATED INVESTMENT",
                                fontFamily = JetBrainsMono,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = AmberGold
                            )
                            Text(
                                text = "PKR %,d".format(totalPkr),
                                fontFamily = JetBrainsMono,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 28.sp,
                                color = Color.White
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(StudioDarkBg)
                                .padding(horizontal = 8.dp, vertical = 5.dp)
                        ) {
                            Text(
                                text = if (isRush) "Rush Priority" else "Standard Timeline",
                                fontFamily = JetBrainsMono,
                                fontSize = 10.sp,
                                color = if (isRush) NeonRose else EmeraldActive
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Includes: ${selectedService.name} · ${selectedTier.title} · ${selectedAddOnIds.size} Add-ons",
                        fontFamily = PlusJakartaSans,
                        fontSize = 11.sp,
                        color = TextSecondary
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        GradientButton(
                            text = "WhatsApp Brief",
                            onClick = {
                                val text = viewModel.generateWhatsAppText()
                                openWhatsApp(context, StudioRepository.studioWhatsappClean, text)
                            },
                            testTag = "calc_whatsapp_button",
                            modifier = Modifier.weight(1.3f),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Chat,
                                    contentDescription = null,
                                    tint = Color(0xFF08090E),
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        )

                        OutlinedButton(
                            onClick = {
                                viewModel.saveCurrentQuote()
                                Toast.makeText(context, "Quote saved to your device!", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier
                                .weight(1f)
                                .testTag("save_quote_button"),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = TextPrimary),
                            border = ButtonDefaults.outlinedButtonBorder.copy(
                                brush = androidx.compose.ui.graphics.SolidColor(StudioCardBorder)
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.BookmarkAdd,
                                contentDescription = null,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Save",
                                fontFamily = PlusJakartaSans,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
