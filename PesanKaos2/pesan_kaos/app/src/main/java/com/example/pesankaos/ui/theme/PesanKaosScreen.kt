package com.example.pesankaos.ui.theme
import StartOrderScreen
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pesankaos.R
import com.example.pesankaos.ui.theme.components.OrderViewModel
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.pesankaos.data.DataSource


enum class PesanKaosScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Color(title = R.string.pilihan_warna),
    Pickup(title = R.string.pilihan_tanggal_pickup),
    Summary(title = R.string.ringkasan_pesanan)
}

@Composable
fun PesanKaosApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = PesanKaosScreen.valueOf(
        backStackEntry?.destination?.route ?: PesanKaosScreen.Start.name
    )
    Scaffold(
        topBar = {
            PesanKaosAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != 
                        null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = PesanKaosScreen.Start.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = PesanKaosScreen.Start.name) {
                StartOrderScreen(
                    quantityOptions = DataSource.quantityOptions,
                    onNextButtonClicked = {
                        viewModel.setQuantity(it)

                        navController.navigate(PesanKaosScreen.Color.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }
            composable(route = PesanKaosScreen.Color.name) {
                val context = LocalContext.current
                SelectOptionScreen(
                    subtotal = uiState.price,
                    options = DataSource.colors.map { id ->
                        context.resources.getString(id) },

                    onSelectionChanged = { viewModel.setColor(it) },
                    modifier = Modifier.fillMaxHeight()
                )
            }
            composable(route = PesanKaosScreen.Pickup.name) {
                SelectOptionScreen(
                    subtotal = uiState.price,
                    options = uiState.pickupOptions,
                    onSelectionChanged = { viewModel.setDate(it) },
                    modifier = Modifier.fillMaxHeight()
                )
            }
            composable(route = PesanKaosScreen.Summary.name) {
                val context = LocalContext.current
                OrderSummaryScreen(
                    orderUiState = uiState,
                    modifier = Modifier.fillMaxHeight()
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PesanKaosAppBar(
    currentScreen: PesanKaosScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        // Menambahkan windowInsets 0 dp di atas dan bawah untuk menghilangkan jarak
                windowInsets = WindowInsets(
            top = dimensionResource(R.dimen.size_0),
            bottom = dimensionResource(R.dimen.size_0)
        ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector =
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription =
                        stringResource(R.string.back_button)

                    )
                }
            }
        }
    )
}
