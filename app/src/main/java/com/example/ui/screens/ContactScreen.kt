package com.example.ui.screens

import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.example.model.SavedQuote
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ContactScreen(
    viewModel: StudioViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val savedQuotes by viewModel.savedQuotes.collectAsState()

    val clientName by viewModel.contactName.collectAsState()
    val clientPhone by viewModel.contactPhone.collectAsState()
    val clientEmail by viewModel.contactEmail.collectAsState()
    val clientService by viewModel.contactService.collectAsState()
    val clientBudget by viewModel.contactBudget.collectAsState()
    val clientDescription by viewModel.contactDescription.collectAsState()
    val isSubmitted by viewModel.contactSubmitted.collectAsState()
    val errorMessage by viewModel.contactError.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(StudioDarkBg)
            .testTag("contact_screen"),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        item {
            Column {
                SectionTag(title = "Get In Touch")
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Studio Inquiries",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    color = TextPrimary
                )
                Text(
                    text = "Ready to build your next brand identity or web platform? Reach out directly via WhatsApp, phone, or our project brief form.",
                    fontFamily = PlusJakartaSans,
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }
        }

        // Contact Information Card
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(StudioCardBg)
                    .border(1.dp, StudioCardBorder, RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF261D12)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = AmberGold,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "STUDIO LOCATION",
                                fontFamily = JetBrainsMono,
                                fontSize = 9.sp,
                                color = TextMuted
                            )
                            Text(
                                text = StudioRepository.studioLocation,
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = TextPrimary
                            )
                        }
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF0F241A)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Chat,
                                contentDescription = null,
                                tint = EmeraldActive,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "CALL / WHATSAPP",
                                fontFamily = JetBrainsMono,
                                fontSize = 9.sp,
                                color = TextMuted
                            )
                            Text(
                                text = StudioRepository.studioContactNumber,
                                fontFamily = JetBrainsMono,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = EmeraldActive
                            )
                        }
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF201633)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = null,
                                tint = ElectricViolet,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "RESPONSE WINDOW",
                                fontFamily = JetBrainsMono,
                                fontSize = 9.sp,
                                color = TextMuted
                            )
                            Text(
                                text = StudioRepository.responseTime,
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = TextPrimary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // 1-Tap Quick Action Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        GradientButton(
                            text = "WhatsApp Chat",
                            onClick = {
                                openWhatsApp(context, StudioRepository.studioWhatsappClean, "Salam Creative Design Studio! I would like to consult on a design project.")
                            },
                            testTag = "contact_whatsapp_btn",
                            modifier = Modifier.weight(1f),
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
                                try {
                                    val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${StudioRepository.studioContactNumber.replace(" ", "")}"))
                                    context.startActivity(dialIntent)
                                } catch (e: Exception) {
                                    Toast.makeText(context, "Could not launch dialer: ${StudioRepository.studioContactNumber}", Toast.LENGTH_SHORT).show()
                                }
                            },
                            modifier = Modifier
                                .weight(1f)
                                .testTag("contact_call_btn"),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = AmberGold),
                            border = ButtonDefaults.outlinedButtonBorder.copy(
                                brush = androidx.compose.ui.graphics.SolidColor(AmberGold)
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = null,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Direct Call",
                                fontFamily = PlusJakartaSans,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        // Project Brief Submission Form
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(StudioCardBg)
                    .border(1.dp, StudioCardBorder, RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "SUBMIT PROJECT BRIEF",
                        fontFamily = JetBrainsMono,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = AmberGold
                    )

                    if (isSubmitted) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFF0F241A))
                                .border(1.dp, Color(0xFF1B4D36), RoundedCornerShape(10.dp))
                                .padding(14.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = EmeraldActive,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = "Brief Generated Successfully!",
                                        fontFamily = PlusJakartaSans,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp,
                                        color = EmeraldActive
                                    )
                                    Text(
                                        text = "Our studio team has prepared your project requirements. You can also send them directly via WhatsApp.",
                                        fontFamily = PlusJakartaSans,
                                        fontSize = 11.sp,
                                        color = TextSecondary
                                    )
                                }
                            }
                        }
                    }

                    if (errorMessage != null) {
                        Text(
                            text = errorMessage ?: "",
                            fontFamily = PlusJakartaSans,
                            fontSize = 11.sp,
                            color = NeonRose
                        )
                    }

                    OutlinedTextField(
                        value = clientName,
                        onValueChange = { viewModel.contactName.value = it },
                        label = { Text("Your Name / Brand Name *", fontSize = 12.sp) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("inquiry_name_input"),
                        shape = RoundedCornerShape(10.dp),
                        colors = studioTextFieldColors(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = clientPhone,
                        onValueChange = { viewModel.contactPhone.value = it },
                        label = { Text("WhatsApp / Phone Number *", fontSize = 12.sp) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("inquiry_phone_input"),
                        shape = RoundedCornerShape(10.dp),
                        colors = studioTextFieldColors(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = clientEmail,
                        onValueChange = { viewModel.contactEmail.value = it },
                        label = { Text("Email Address (Optional)", fontSize = 12.sp) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("inquiry_email_input"),
                        shape = RoundedCornerShape(10.dp),
                        colors = studioTextFieldColors(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = clientDescription,
                        onValueChange = { viewModel.contactDescription.value = it },
                        label = { Text("Project Goals & Vision", fontSize = 12.sp) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("inquiry_desc_input"),
                        shape = RoundedCornerShape(10.dp),
                        colors = studioTextFieldColors(),
                        maxLines = 4,
                        minLines = 2
                    )

                    GradientButton(
                        text = "Submit & Launch WhatsApp Brief",
                        onClick = {
                            viewModel.submitContactInquiry { encodedBrief ->
                                openWhatsApp(context, StudioRepository.studioWhatsappClean, encodedBrief)
                            }
                        },
                        testTag = "submit_brief_btn",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        // Saved Quotes Section
        item {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "SAVED ESTIMATES (${savedQuotes.size})",
                        fontFamily = JetBrainsMono,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = AmberGold
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                if (savedQuotes.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(StudioCardBg)
                            .border(1.dp, StudioCardBorder, RoundedCornerShape(12.dp))
                            .padding(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No saved quotes yet. Configure a custom scope in the Calculator tab to save estimates.",
                            fontFamily = PlusJakartaSans,
                            fontSize = 12.sp,
                            color = TextMuted,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }
            }
        }

        items(savedQuotes) { quote ->
            SavedQuoteCard(
                quote = quote,
                onDelete = { viewModel.removeSavedQuote(quote.id) },
                onSend = {
                    val msg = "Salam Creative Design Studio! Inquiring about my saved quote: ${quote.serviceName} (${quote.tierName}) estimated at PKR %,d.".format(quote.totalPkr)
                    val encoded = java.net.URLEncoder.encode(msg, java.nio.charset.StandardCharsets.UTF_8.toString())
                    openWhatsApp(context, StudioRepository.studioWhatsappClean, encoded)
                }
            )
        }
    }
}

@Composable
fun SavedQuoteCard(
    quote: SavedQuote,
    onDelete: () -> Unit,
    onSend: () -> Unit
) {
    val dateStr = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(quote.timestamp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(StudioCardBg)
            .border(1.dp, StudioCardBorder, RoundedCornerShape(12.dp))
            .padding(14.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = quote.serviceName,
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = TextPrimary
                    )
                    Text(
                        text = "${quote.tierName} · $dateStr",
                        fontFamily = JetBrainsMono,
                        fontSize = 10.sp,
                        color = TextMuted
                    )
                }

                Text(
                    text = "PKR %,d".format(quote.totalPkr),
                    fontFamily = JetBrainsMono,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = AmberGold
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            if (quote.addOns.isNotEmpty()) {
                Text(
                    text = "Add-ons: " + quote.addOns.joinToString(", "),
                    fontFamily = PlusJakartaSans,
                    fontSize = 11.sp,
                    color = TextSecondary
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Quote",
                        tint = TextMuted,
                        modifier = Modifier.size(16.dp)
                    )
                }

                OutlinedButton(
                    onClick = onSend,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = EmeraldActive),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = androidx.compose.ui.graphics.SolidColor(EmeraldActive)
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Chat,
                        contentDescription = null,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Send via WhatsApp",
                        fontFamily = PlusJakartaSans,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun studioTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = AmberGold,
    unfocusedBorderColor = StudioCardBorder,
    focusedContainerColor = StudioDarkBg,
    unfocusedContainerColor = StudioDarkBg,
    focusedTextColor = TextPrimary,
    unfocusedTextColor = TextPrimary,
    focusedLabelColor = AmberGold,
    unfocusedLabelColor = TextMuted
)
