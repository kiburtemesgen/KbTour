package com.example.kbtour

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.kbtour.destinations.PlaceDetailDestination
import com.example.kbtour.models.place.Place
//import com.example.kbtour.navigation.NavigationView
import com.example.kbtour.ui.theme.KbTourTheme
import com.example.kbtour.ui.theme.MainBackgroundColor
import com.example.kbtour.ui.theme.TextBlue
import com.example.kbtour.utils.Size
import com.example.kbtour.utils.network.DataState
import com.example.kbtour.viewModels.HomeViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KbTourTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MainBackgroundColor
                ) {
//                    BottomNavigationExample()
//                    NavigationView()
                    DestinationsNavHost(navGraph = NavGraphs.root)


                }
            }
        }
    }
}

@RootNavGraph(start=true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val placeList = homeViewModel.places


   Column(modifier= Modifier
       .padding(start = 15.dp)
       .verticalScroll(rememberScrollState())) {
       WelcomeWidget()
       Spacer(modifier = Modifier.height(20.dp))
       SearchWidget(onSearch = {
           Log.d("Search Query: ", it)
           homeViewModel.searchPlace(it)
       })
       Spacer(modifier = Modifier.height(20.dp))
       Spacer(modifier = Modifier.height(20.dp))
       Column() {

           placeList.value?.let { it ->
               when (it){
                   is DataState.Success -> LazyRow() {
                       items(it.data.place){pl ->
                           TourWidget(onOpen = {
                               navigator.navigate(PlaceDetailDestination(placeId=pl.imageUrl))
                           }, title = pl.title, imageUrl = pl.imageUrl)
                       }
                   }
                   is DataState.Error -> {}
                   is DataState.Loading -> Column(
                       modifier = Modifier.fillMaxWidth(),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally
                   ) {
                       CircularProgressIndicator()
                   }
           }
       }

       }
//       Row(modifier= Modifier.horizontalScroll(rememberScrollState())) {
//           TourWidget(onOpen = {
//               navigator.navigate(PlaceDetailDestination(placeId="got"))
//           })
//           TourWidget {navigator.navigate(PlaceDetailDestination(placeId="alot"))
//           }
//           TourWidget{navigator.navigate(PlaceDetailDestination(placeId="fut"))
//           }
//       }
       Spacer(modifier = Modifier.height(20.dp))
       Text(text = "Group Tours", fontSize = 25.sp, fontWeight = FontWeight.SemiBold)
       Spacer(modifier = Modifier.height(20.dp))
       Row(modifier= Modifier.horizontalScroll(rememberScrollState())) {
           GroupTourWidget()
           GroupTourWidget()
           GroupTourWidget()
       }
       }
   }

@Composable
fun WelcomeWidget(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column() {
            Text(text = "Welcome Kibur",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Location icon")
                Spacer(modifier = Modifier.width(6.dp))
                Text("Sanfransisco, USA",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
        Row() {
            Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notifications icon")
            Spacer(modifier = Modifier.width(8.dp))
           Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Shopping cart icon")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchWidget(onSearch: (searchVal: String)-> Unit){
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 10.dp),
        value = text, onValueChange = {
            text = it
            onSearch(it)
        },
    shape = RoundedCornerShape(100),
        placeholder = {Text("Search Destination...")},
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Shopping cart icon")
        }

    )
}

@Composable
fun CategoryWidget(){
Button(
    colors = ButtonDefaults.buttonColors(containerColor = MainBackgroundColor),
    shape = RoundedCornerShape(100),
    border = BorderStroke(1.dp, Color.Gray),
    onClick = { /*TODO*/ }, modifier = Modifier
        .width(150.dp)
        .height(50.dp)
        .padding(horizontal = 10.dp)
        ) {
    Row() {
        Text("")
        Text("Lakes", fontSize=14.sp, color = Color.Black)
    }
}
}

@Composable
fun GroupTourWidget(){
    val size = Size()
    val width = size.width()
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .padding(6.dp)
            .width((width * 0.8).dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(6.dp)
                .clip(RoundedCornerShape(12.dp))
                .height(120.dp)
                .width(100.dp),
            model = "https://images.pexels.com/photos/1940038/pexels-photo-1940038.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Column(
            verticalArrangement = Arrangement.Top,
            modifier=Modifier.padding(10.dp)) {
            Row(
                modifier=Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Sea of Peace", fontSize=16.sp, fontWeight = FontWeight.Bold)
                    Text("Portic Team",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray)
                }
                Text("H")
            }
            Row(
                modifier=Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Alabama",
                    fontSize=15.sp,
                    color=Color.Gray,
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = "Alaska",
                    color = TextBlue
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row() {
                Box() {
                    Box(
                        modifier = Modifier.align(Alignment.TopStart)
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .clip(RoundedCornerShape(100))
                                .height(30.dp)
                                .width(30.dp),
                            model = "https://images.pexels.com/photos/1940038/pexels-photo-1940038.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Box(modifier = Modifier
                        .padding(start = 25.dp)
                        .align(Alignment.TopStart)
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .clip(RoundedCornerShape(100))
                                .height(30.dp)
                                .width(30.dp),
                            model = "https://images.pexels.com/photos/1940038/pexels-photo-1940038.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "63+",
                    fontSize = 14.sp,
                    color=Color.Gray,
                    fontWeight = FontWeight.SemiBold
                )
            }

        }

    }
}

@Composable
fun TourWidget(onOpen: ()-> Unit, title: String, imageUrl: String){
        Column(verticalArrangement = Arrangement.Top, modifier = Modifier
            .width(200.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)) {
                AsyncImage(
                    modifier = Modifier
                        .padding(6.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .height(120.dp)
                        .fillMaxWidth()
                        .clickable {
                            onOpen()
                        }
                    ,
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            Column(
                modifier = Modifier.padding(horizontal = 20.dp, vertical=10.dp)
            ) {
                Row(
                    modifier=Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text( text = title, fontSize = 16.sp, fontWeight= FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.width(120.dp))
                    Text("4.5", fontSize = 12.sp)

                }
                Row(
                    modifier=Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Australia", fontSize = 14.sp, color=Color.Gray)
                    Text("*", fontSize = 18.sp)
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier=Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$35",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextBlue
                        )
                        Text(text = " /Person", fontSize = 12.sp, color= Color.Gray)
                        
                    }
                   
                    Text("->", fontSize = 18.sp)
                }
            }

        }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KbTourTheme {
//        HomeScreen()
//        NavigationView()
    }
}




