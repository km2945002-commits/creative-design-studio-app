package com.example.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Tablet
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.PreviewViewport
import com.example.ui.theme.AmberGold
import com.example.ui.theme.ElectricViolet
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
fun DesignLabWidget(
    viewport: PreviewViewport,
    selectedTheme: String,
    onSelectViewport: (PreviewViewport) -> Unit,
    onSelectTheme: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .testTag("design_lab_widget")
            .clip(RoundedCornerShape(18.dp))
            .background(StudioCardBg)
            .border(1.dp, StudioCardBorder, RoundedCornerShape(18.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF261D12)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Palette,
                        contentDescription = "Design Lab",
                        tint = AmberGold,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Studio Live Canvas",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = TextPrimary
                )
            }

            Text(
                text = "INTERACTIVE",
                fontFamily = JetBrainsMono,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = AmberGold
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Viewport selector buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ViewportButton(
                title = "Mobile",
                icon = Icons.Default.PhoneAndroid,
                isSelected = viewport == PreviewViewport.MOBILE,
                onClick = { onSelectViewport(PreviewViewport.MOBILE) },
                modifier = Modifier.weight(1f)
            )
            ViewportButton(
                title = "Tablet",
                icon = Icons.Default.Tablet,
                isSelected = viewport == PreviewViewport.TABLET,
                onClick = { onSelectViewport(PreviewViewport.TABLET) },
                modifier = Modifier.weight(1f)
            )
            ViewportButton(
                title = "Desktop",
                icon = Icons.Default.Computer,
                isSelected = viewport == PreviewViewport.DESKTOP,
                onClick = { onSelectViewport(PreviewViewport.DESKTOP) },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Theme chips
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Palette:",
                fontFamily = JetBrainsMono,
                fontSize = 11.sp,
                color = TextMuted
            )
            ThemeChip(
                name = "Obsidian",
                color = AmberGold,
                isSelected = selectedTheme == "Obsidian Dark",
                onClick = { onSelectTheme("Obsidian Dark") }
            )
            ThemeChip(
                name = "Neon Rose",
                color = NeonRose,
                isSelected = selectedTheme == "Neon Rose",
                onClick = { onSelectTheme("Neon Rose") }
            )
            ThemeChip(
                name = "Violet Pulse",
                color = ElectricViolet,
                isSelected = selectedTheme == "Violet Pulse",
                onClick = { onSelectTheme("Violet Pulse") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Live Simulated Artboard
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(StudioDarkBg)
                .border(1.dp, StudioCardBorder, RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                        Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFFEF4444)))
                        Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFFF59E0B)))
                        Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFF10B981)))
                    }
                    Text(
                        text = when (viewport) {
                            PreviewViewport.MOBILE -> "390px · iPhone Canvas"
                            PreviewViewport.TABLET -> "820px · iPad Air Grid"
                            PreviewViewport.DESKTOP -> "1440px · Retina Ultrawide"
                        },
                        fontFamily = JetBrainsMono,
                        fontSize = 10.sp,
                        color = TextMuted
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Wireframe Mockup representation
                val accentColor = when (selectedTheme) {
                    "Neon Rose" -> NeonRose
                    "Violet Pulse" -> ElectricViolet
                    else -> AmberGold
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    accentColor.copy(alpha = 0.18f),
                                    Color(0xFF0D0F18)
                                )
                            )
                        )
                        .border(1.dp, accentColor.copy(alpha = 0.35f), RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(accentColor)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "CorelDRAW Vector Engine & WordPress Cloud",
                                fontFamily = JetBrainsMono,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                color = accentColor
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Calibrated for 300 DPI CMYK offset press bleeds & 0.72s ultra-fast mobile DOM rendering.",
                            fontFamily = PlusJakartaSans,
                            fontSize = 12.sp,
                            color = TextPrimary,
                            lineHeight = 16.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            SpecBadge("Bezier Vector")
                            SpecBadge("3mm Bleed")
                            SpecBadge("Hostinger SSL")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ViewportButton(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bg = if (isSelected) AmberGold else Color(0xFF1B1D2B)
    val fg = if (isSelected) Color(0xFF08090E) else TextSecondary
    val border = if (isSelected) AmberGold else StudioCardBorder

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(bg)
            .border(1.dp, border, RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp, horizontal = 4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = fg,
            modifier = Modifier.size(15.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = title,
            fontFamily = PlusJakartaSans,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = fg
        )
    }
}

@Composable
private fun ThemeChip(
    name: String,
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(if (isSelected) color.copy(alpha = 0.2f) else Color.Transparent)
            .border(1.dp, if (isSelected) color else StudioCardBorder, RoundedCornerShape(6.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(color)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = name,
                fontFamily = JetBrainsMono,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                color = if (isSelected) color else TextSecondary
            )
        }
    }
}

@Composable
private fun SpecBadge(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFF141724))
            .border(0.5.dp, StudioCardBorder, RoundedCornerShape(4.dp))
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(
            text = text,
            fontFamily = JetBrainsMono,
            fontSize = 9.sp,
            color = TextSecondary
        )
    }
}
