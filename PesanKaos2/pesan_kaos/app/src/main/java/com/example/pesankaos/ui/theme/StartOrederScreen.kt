import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pesankaos.R
import com.example.pesankaos.data.DataSource
import com.example.pesankaos.ui.theme.PesanKaosTheme

@Composable
fun StartOrderScreen(
    quantityOptions: List<Pair<Int, Int>>,
    onNextButtonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement =

            Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            Spacer(modifier =

            Modifier.height(dimensionResource(R.dimen.padding_medium)))

            Image(
                painter = painterResource(R.drawable.kaos_coklat),
                contentDescription = null,
                modifier = Modifier.size(300.dp)
            )
            Spacer(modifier =

            Modifier.height(dimensionResource(R.dimen.padding_medium)))

            Text(
                text = stringResource(R.string.pemesanan_kaos),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier =

            Modifier.height(dimensionResource(R.dimen.padding_small)))
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.padding_medium)
            )
        ) {
            quantityOptions.forEach { item ->
                SelectQuantityButton(
                    labelResourceId = item.first,
                    onClick = { },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
fun SelectQuantityButton(
    @StringRes labelResourceId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.widthIn(min = 250.dp)
    ) {
        Text(stringResource(labelResourceId))
    }
}
@Preview
@Composable
fun StartOrderPreview() {
    PesanKaosTheme {
        StartOrderScreen(
            quantityOptions = DataSource.quantityOptions,
            onNextButtonClicked = {},
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.padding_medium))
        )
    }
}