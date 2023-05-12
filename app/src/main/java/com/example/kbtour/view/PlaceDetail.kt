package com.example.kbtour.view

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.kbtour.ui.theme.MainBackgroundColor
import com.example.kbtour.ui.theme.TextBlue
import com.example.kbtour.utils.Size
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Destination
@Composable
fun PlaceDetail(navigator: DestinationsNavigator, placeId: String){
    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ExtendedFloatingActionButton(
                    modifier = Modifier
                        .width(140.dp)
                        .height(50.dp)
                        .border(
                            border = BorderStroke(width = 1.5.dp, TextBlue),
                            shape = RoundedCornerShape(15.dp)
                        ),
                    onClick = { /* ... */ },
                    containerColor = MainBackgroundColor,
                    icon = {},
                    text = { Row() {
                        Text("890$",
                            fontSize = 15.sp,
                            color= TextBlue,
                            fontWeight = FontWeight.Bold)
                        Text("/Person", fontSize = 12.sp, color = TextBlue)
                    } }

                )
                ExtendedFloatingActionButton(
                    modifier = Modifier
                        .width(140.dp)
                        .height(50.dp)
                        .border(
                            border = BorderStroke(width = 1.5.dp, TextBlue),
                            shape = RoundedCornerShape(15.dp)
                        ),
                    onClick = { /* ... */ },
                    containerColor = TextBlue,
                    icon = {},
                    text = { Row() {
                        Text(
                            "Book Now",
                            fontSize = 15.sp,
                            color= Color.White,
                            fontWeight = FontWeight.Bold)
                    } }

                )
            }
        }
    ) {

    }
Column(
    verticalArrangement = Arrangement.Top
) {
    DetailImage(navigator, placeId)
}
}

@Composable
fun DetailImage(navigator: DestinationsNavigator, placeId: String){
    val size = Size()
    val height = size.height()
    Column(
        modifier = Modifier.padding(horizontal = 15.dp)
    ) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp)
                .clickable {
                    navigator.popBackStack()
                }
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "" )
            Text(
               "Back"
                )
        }
        AsyncImage(
            modifier = Modifier
                .padding(6.dp)
                .clip(RoundedCornerShape(15.dp))
                .height((height / 2.5).dp)
                .fillMaxWidth(),
            model = placeId,
//            model = "https://cdn1.parksmedia.wdprapps.disney.com/resize/mwImage/1/1600/1600/90/media/abd/refresh/europe/norway-vacation/adventures-by-disney-europe-norway-hero-03-flam_1x1.jpg?cb=5",
            contentDescription = null,
        contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.height(15.dp))
       Row(
           horizontalArrangement = Arrangement.SpaceBetween,
           modifier = Modifier.fillMaxWidth()
       ) {
           Column() {
               Text("Mount bromo",
                   fontSize = 22.sp,
                   fontWeight = FontWeight.ExtraBold,
                   fontFamily = FontFamily.SansSerif
               )
               Row() {
                   Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Notifications icon")
                   Text(text = "Italia",
                       fontSize = 13.sp,
                       color = Color.Gray
                   )
               }
           }
           Column() {
               Row() {
                   Text(text = "*")
                   Text(text = "4.7(9k Review)",
                       color = Color.Gray
                   )
               }
               Text(text = "Map Direction",
                   color = TextBlue,
                   fontWeight = FontWeight.SemiBold,
                   fontFamily = FontFamily.Serif
               )
           }
       }
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Description",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "The Role pass is a high mountain pass in Trentino in italy. it connects the Fiemme and Premiero valleys",
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(10.dp))



    }


}