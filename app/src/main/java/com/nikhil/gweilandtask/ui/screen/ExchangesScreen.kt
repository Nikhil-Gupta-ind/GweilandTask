package com.nikhil.gweilandtask.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.nikhil.gweilandtask.R
import com.nikhil.gweilandtask.formatAsPercentage
import com.nikhil.gweilandtask.model.CoinsResponse
import com.nikhil.gweilandtask.ui.theme.GweilandTaskTheme
import com.nikhil.gweilandtask.viewmodel.ExchangesViewModel

// todo
// string, color file, filter button color, bottom nav color
// api call list, 24h graph, filter
// github, video, form

@Composable
fun Exchanges(exchangesViewModel: ExchangesViewModel = viewModel()) {
    val coins: State<List<CoinsResponse.Data>> = exchangesViewModel.coins.collectAsState()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        val (screenTitle, notificationIcon, notificationBadge, settingsIcon, searchField, filterButton, filterOptions, pagerHeading, pagerHeading2, cryptoCard, topCurrenciesTitle, viewAllText, currencyList, progress) = createRefs()

        Text(
            text = "EXCHANGES",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.constrainAs(screenTitle) {
                top.linkTo(parent.top, margin = 46.dp)
                start.linkTo(parent.start)
            }
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_circle),
            contentDescription = "New notification badge",
            tint = Color(0xFFFAE24C),
            modifier = Modifier.constrainAs(notificationBadge) {
                bottom.linkTo(notificationIcon.top)
                end.linkTo(notificationIcon.end, margin = (-4).dp)
            }
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_bell),
            contentDescription = "Notification",
            modifier = Modifier.constrainAs(notificationIcon) {
                top.linkTo(screenTitle.top)
                end.linkTo(settingsIcon.start, margin = 16.dp)
            }
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_gear),
            contentDescription = "Settings",
            modifier = Modifier.constrainAs(settingsIcon) {
                top.linkTo(screenTitle.top)
                end.linkTo(parent.end)
            }
        )
        SearchField(
            modifier = Modifier
                .constrainAs(searchField) {
                    top.linkTo(screenTitle.bottom, margin = 25.dp)
                    start.linkTo(parent.start)
                    end.linkTo(filterButton.start, margin = 9.dp)
                    width = Dimension.fillToConstraints
                }
        )

        var expanded by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .constrainAs(filterButton) {
                    top.linkTo(searchField.top)
                    end.linkTo(parent.end)
                }
        ) {
            OutlinedButton(
                onClick = { expanded = true },
                contentPadding = PaddingValues(16.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0x4D000000))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = "Filter"
                )
                Spacer(modifier = Modifier.size(5.dp))
                Text(text = "Filter")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(text = { Text("CMC Rank") }, onClick = {
                    exchangesViewModel.updateSelectedOption("market_cap")
                    expanded = false
                })
                DropdownMenuItem(text = { Text("Price") }, onClick = {
                    exchangesViewModel.updateSelectedOption("price")
                    expanded = false
                })
                DropdownMenuItem(text = { Text("Volume 24h") }, onClick = {
                    exchangesViewModel.updateSelectedOption("volume_24h")
                    expanded = false
                })
            }
        }
        // require horizontal pager
        Text(
            text = "CryptoCurrency",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.constrainAs(pagerHeading) {
                top.linkTo(searchField.bottom, margin = 22.dp)
                start.linkTo(parent.start)
            }
        )
        Text(
            text = "NFT",
            color = Color(0x4D000000),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.constrainAs(pagerHeading2) {
                top.linkTo(pagerHeading.top)
                start.linkTo(pagerHeading.end, margin = 20.dp)
            }
        )
        if (coins.value.isEmpty()) {
            CircularProgressIndicator(
                modifier = Modifier.constrainAs(progress) {
                    top.linkTo(pagerHeading.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            )
        } else {
            BitcoinCard(
                data = coins.value[0],
                modifier = Modifier.constrainAs(cryptoCard) {
                    top.linkTo(pagerHeading.bottom, margin = 15.dp)
                    start.linkTo(parent.start)
                }
            )
            Text(
                text = "Top Cryptocurrencies",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.constrainAs(topCurrenciesTitle) {
                    top.linkTo(cryptoCard.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                }
            )
            Text(
                text = "View All",
                fontSize = 13.sp,
                color = Color(0x80000000),
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .constrainAs(viewAllText) {
                        top.linkTo(topCurrenciesTitle.top)
                        end.linkTo(parent.end)
                    }
                    .clickable {

                    }
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp),
//                contentPadding = PaddingValues(bottom = 100.dp),
                modifier = Modifier
//                    .padding(bottom = 300.dp)
                    .constrainAs(currencyList) {
                        top.linkTo(topCurrenciesTitle.bottom, margin = 22.dp)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                    }
            ) {
                items(coins.value.drop(1)) { item ->
                    CurrencyListItem(item)
                }
                // Add Spacer to create extra scrolling at the end
                item {
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(75.dp))
                }
            }
        }
    }
}

@Composable
fun CurrencyListItem(
    data: CoinsResponse.Data,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {

        val (logoImage, titleText, nameText, graphImage, priceText, percentText) = createRefs()
        Image(
            painter = rememberAsyncImagePainter(data.logoUrl),
            contentDescription = data.name,
            modifier = Modifier
                .size(48.dp)
                .clip(shape = CircleShape)
                .constrainAs(logoImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )
        Text(
            text = data.symbol, fontSize = 18.sp, fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .constrainAs(titleText) {
                    top.linkTo(logoImage.top)
                    start.linkTo(logoImage.end, margin = 8.dp)
                }
        )
        Text(
            text = data.name,
            fontSize = 13.sp,
            color = Color(0x80000000),
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .constrainAs(nameText) {
                    top.linkTo(titleText.bottom, margin = 4.dp)
                    start.linkTo(logoImage.end, margin = 8.dp)
                    bottom.linkTo(parent.bottom)
                }
        )
        Image(
            painter = painterResource(
                id = if (data.quote.USD.percent_change_24h > 0) {
                    R.drawable.ic_green_graph
                } else {
                    R.drawable.ic_red_graph
                }
            ), contentDescription = "graph",
            modifier = Modifier
                .constrainAs(graphImage) {
                    top.linkTo(parent.top)
                    start.linkTo(titleText.start, margin = 64.dp)
                }
        )
        Text(
            text = "$" + "%,d".format(data.quote.USD.price.toInt()) + " USD"/*"$3800 USD"*/,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .constrainAs(priceText) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
        )
        Text(
            text = data.quote.USD.percent_change_24h.formatAsPercentage()/*"-3.2%"*/,
            fontSize = 13.sp,
            color = if (data.quote.USD.percent_change_24h > 0) Color(0xFF00CE08) else Color.Red,
            modifier = Modifier
                .constrainAs(percentText) {
                    top.linkTo(priceText.bottom, margin = 4.dp)
                    end.linkTo(priceText.end)
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ExchangesPreview() {
    GweilandTaskTheme {
        Exchanges()
    }
}