package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DesignServices
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.StudioTab
import com.example.ui.StudioViewModel
import com.example.ui.screens.ContactScreen
import com.example.ui.screens.EstimatorScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.PortfolioScreen
import com.example.ui.screens.ProjectDetailSheet
import com.example.ui.screens.ServiceDetailSheet
import com.example.ui.screens.ServicesScreen
import com.example.ui.theme.AmberGold
import com.example.ui.theme.CreativeDesignStudioTheme
import com.example.ui.theme.PlusJakartaSans
import com.example.ui.theme.StudioCardBg
import com.example.ui.theme.StudioCardBorder
import com.example.ui.theme.StudioDarkBg
import com.example.ui.theme.StudioDarkSurface
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

class MainActivity : ComponentActivity() {
    private val viewModel: StudioViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CreativeDesignStudioTheme {
                StudioMainApp(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudioMainApp(viewModel: StudioViewModel) {
    val currentTab by viewModel.currentTab.collectAsState()
    val activeProject by viewModel.activeProjectDetail.collectAsState()
    val activeService by viewModel.activeServiceDetail.collectAsState()

    val projectSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val serviceSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    // Handle back button smoothly
    BackHandler(enabled = activeProject != null || activeService != null || currentTab != StudioTab.HOME) {
        when {
            activeProject != null -> viewModel.closeProjectDetail()
            activeService != null -> viewModel.closeServiceDetail()
            currentTab != StudioTab.HOME -> viewModel.selectTab(StudioTab.HOME)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = StudioDarkBg,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            StudioBottomNavBar(
                currentTab = currentTab,
                onTabSelect = { viewModel.selectTab(it) }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Crossfade(
                targetState = currentTab,
                label = "TabCrossfade"
            ) { tab ->
                when (tab) {
                    StudioTab.HOME -> HomeScreen(viewModel = viewModel)
                    StudioTab.SERVICES -> ServicesScreen(viewModel = viewModel)
                    StudioTab.PORTFOLIO -> PortfolioScreen(viewModel = viewModel)
                    StudioTab.ESTIMATOR -> EstimatorScreen(viewModel = viewModel)
                    StudioTab.CONTACT -> ContactScreen(viewModel = viewModel)
                }
            }

            // Project Case Study Modal Sheet
            if (activeProject != null) {
                ProjectDetailSheet(
                    project = activeProject!!,
                    onDismiss = { viewModel.closeProjectDetail() },
                    sheetState = projectSheetState,
                    viewModel = viewModel
                )
            }

            // Service Detailed Specs Modal Sheet
            if (activeService != null) {
                ServiceDetailSheet(
                    service = activeService!!,
                    onDismiss = { viewModel.closeServiceDetail() },
                    sheetState = serviceSheetState,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun StudioBottomNavBar(
    currentTab: StudioTab,
    onTabSelect: (StudioTab) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
            .testTag("studio_bottom_nav")
            .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp))
            .border(1.dp, StudioCardBorder, RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp)),
        containerColor = StudioDarkSurface,
        contentColor = TextPrimary,
        tonalElevation = 8.dp
    ) {
        val tabs = listOf(
            StudioTab.HOME to Icons.Default.Home,
            StudioTab.SERVICES to Icons.Default.DesignServices,
            StudioTab.PORTFOLIO to Icons.Default.Work,
            StudioTab.ESTIMATOR to Icons.Default.Calculate,
            StudioTab.CONTACT to Icons.Default.Call
        )

        tabs.forEach { (tab, icon) ->
            val isSelected = currentTab == tab
            NavigationBarItem(
                selected = isSelected,
                onClick = { onTabSelect(tab) },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = tab.title,
                        modifier = Modifier.size(20.dp)
                    )
                },
                label = {
                    Text(
                        text = tab.title,
                        fontFamily = PlusJakartaSans,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF08090E),
                    selectedTextColor = AmberGold,
                    indicatorColor = AmberGold,
                    unselectedIconColor = TextSecondary,
                    unselectedTextColor = TextMuted
                ),
                modifier = Modifier.testTag("tab_${tab.name.lowercase()}")
            )
        }
    }
}
