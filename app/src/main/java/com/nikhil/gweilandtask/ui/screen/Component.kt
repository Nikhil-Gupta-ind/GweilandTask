package com.nikhil.gweilandtask.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.nikhil.gweilandtask.R
import com.nikhil.gweilandtask.formatAsPercentage
import com.nikhil.gweilandtask.model.CoinsResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(modifier: Modifier = Modifier) {
    var searchText by rememberSaveable {
        mutableStateOf("")
    }
    TextField(
        value = searchText,
        singleLine = true,
        onValueChange = { searchText = it },
        modifier = modifier,
        shape = RoundedCornerShape(50),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search Cryptocurrency"
            )
        },
        placeholder = {
            Text(
                text = "Search Cryptocurrency",
                maxLines = 1,
                color = Color(0x4D000000),
                fontWeight = FontWeight.Normal,
            )
        }
    )
}

@Composable
fun BitcoinCard(
    data: CoinsResponse.Data,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = Color(0x1A00CE08))
    ) {
        val (logoImage, titleText, nameText, graphImage, priceText, percentText) = createRefs()
        Image(
            painter = rememberAsyncImagePainter(data.logoUrl),
//            painter = painterResource(id = R.drawable.ic_bitcoin),
            contentDescription = data.name,
            modifier = Modifier
                .size(48.dp).clip(shape = CircleShape)
                .constrainAs(logoImage) {
                    top.linkTo(parent.top, margin = 26.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                }
        )
        Text(
            text = data.symbol, color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(titleText) {
                    top.linkTo(logoImage.top)
                    start.linkTo(logoImage.end, margin = 8.dp)
                }
        )
        Text(
            text = data.name,
            color = Color.Black,
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .constrainAs(nameText) {
                    top.linkTo(titleText.bottom, margin = 4.dp)
                    start.linkTo(logoImage.end, margin = 8.dp)
                    bottom.linkTo(logoImage.bottom)
                }
        )
        Text(
            text = "$" + "%,d".format(data.quote.USD.price.toInt()) + " USD",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(priceText) {
                    top.linkTo(parent.top, margin = 26.dp)
                    end.linkTo(parent.end, margin = 20.dp)
                }
        )
        Text(
            text = data.quote.USD.percent_change_24h.formatAsPercentage()/*"+2.5%"*/,
            fontSize = 13.sp,
            color = Color(0xFF00CE08),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(percentText) {
                    top.linkTo(priceText.bottom, margin = 4.dp)
                    end.linkTo(priceText.end)
                }
        )
        Image(
            painter = painterResource(id = R.drawable.ic_wave),
            contentDescription = "", contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(graphImage) {
                    top.linkTo(logoImage.bottom, margin = 10.dp)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}


@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Black, shape = RoundedCornerShape(25.dp))
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_shop),
                contentDescription = "€-\$hop", tint = Color.White,
                modifier = Modifier.size(20.dp).padding(bottom = 4.dp)
            )
            Text(
                text = "€-\$hop",
                color = Color(0x66FFFFFF),
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_exchange),
                contentDescription = "Exchange", tint = Color.White,
                modifier = Modifier.size(20.dp).padding(bottom = 4.dp)
            )
            Text(
                text = "Exchange",
                color = Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Image(
            painter = painterResource(id = R.drawable.golden_earth),
            contentDescription = "Discover",
            modifier = Modifier.scale(2.5f)
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launchpads),
                contentDescription = "Launchpads", tint = Color.White,
                modifier = Modifier.size(20.dp).padding(bottom = 4.dp)
            )
            Text(
                text = "Launchpads",
                color = Color(0x66FFFFFF),
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, end = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_wallet),
                contentDescription = "Wallet", tint = Color.White,
                modifier = Modifier.size(20.dp).padding(bottom = 4.dp)
            )
            Text(
                text = "Wallet",
                color = Color(0x66FFFFFF),
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}