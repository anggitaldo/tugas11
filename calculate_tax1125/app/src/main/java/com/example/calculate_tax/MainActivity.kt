package com.example.calculate_tax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculate_tax.ui.theme.Calculate_taxTheme
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Calculate_taxTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TaxLayout(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun TaxLayout(modifier: Modifier=Modifier){
    var amountInput by remember { mutableStateOf("") }
    val amount = amountInput.toDoubleOrNull()?:0.0
    val tax = calculateTax(amount)

    Column (
        modifier = modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
        Text(
            text = stringResource(R.string.calculate_Tax),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        EditTextNumber(
            label = R.string.bill_amount,
            placeholder = R.string.ex_amount,
            value = amountInput,
            onValueChange ={amountInput=it} ,
            modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth())
        Text(
            text = stringResource(R.string.tax_amount, tax),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(150.dp))
    }




}





@Composable
fun EditTextNumber(
    @StringRes label:Int,
    @StringRes placeholder: Int,
    value:String,
    onValueChange : (String)-> Unit,
    modifier: Modifier = Modifier
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(label))},
        placeholder = { Text(stringResource(placeholder)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(label))},
        placeholder = { Text(stringResource(placeholder)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
}

private fun calculateTax(amount:Double,taxPercent:Double=10.0): String{
    val tax = taxPercent / 100 * amount

    val rupiah = NumberFormat.getCurrencyInstance(Locale("id","ID")).format(tax)
    return rupiah
}


@Preview(showBackground = true)
@Composable
fun CalculateTaxPreview(){
    TaxLayout()
}
