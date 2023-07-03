package com.ics342.labs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ics342.labs.data.DataItem

private val dataItems = listOf(
    DataItem(1, "Item 1", "Description 1"),
    DataItem(2, "Item 2", "Description 2"),
    DataItem(3, "Item 3", "Description 3"),
    DataItem(4, "Item 4", "Description 4"),
    DataItem(5, "Item 5", "Description 5"),
    DataItem(6, "Item 6", "Description 6"),
    DataItem(7, "Item 7", "Description 7"),
    DataItem(8, "Item 8", "Description 8"),
    DataItem(9, "Item 9", "Description 9"),
    DataItem(10, "Item 10", "Description 10"),
    DataItem(11, "Item 11", "Description 11"),
    DataItem(12, "Item 12", "Description 12"),
    DataItem(13, "Item 13", "Description 13"),
    DataItem(14, "Item 14", "Description 14"),
    DataItem(15, "Item 15", "Description 15"),
    DataItem(16, "Item 16", "Description 16"),
    DataItem(17, "Item 17", "Description 17"),
    DataItem(18, "Item 18", "Description 18"),
    DataItem(19, "Item 19", "Description 19"),
    DataItem(20, "Item 20", "Description 20"),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "Start") {
                this.composable("Start") {
                    DataItemList(dataItems, navController)
                }
                composable("2nd Screen/{id}",
                    arguments = listOf(
                        navArgument(name = "id"){
                            type = NavType.StringType
                        }
                    )
                ) {
                   val idNum = it.arguments?.getString("id")?.toInt()
                    if (idNum != null) {
                        DetailsScreen(dataItems[idNum-1])
                    }
                }
            }
        }
    }
}

@Composable
fun DetailsScreen(item: DataItem) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = "${item.id}",
            fontSize = 48.sp
        )
        Text(
            text = "${item.name}",
            fontSize = 48.sp
        )
        Text(
            text = "${item.description}",
            fontSize = 48.sp
        )
    }
}

@Composable
fun DataItemView(dataItem: DataItem, navController: NavController) {
    Row(
        Modifier
            .background(Color.White)
            .clickable {
                navController.navigate(
                    "2nd Screen/${dataItem.id}"
                )
            }) {
        Text(
            text = "${dataItem.id}",
            style = MaterialTheme.typography.headlineLarge,
        )
        Spacer(modifier = Modifier.size(4.dp))
        Column {
            Text(
                text = "${dataItem.name}",
                style = MaterialTheme.typography.headlineLarge,
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = "${dataItem.description}",
                style = MaterialTheme.typography.bodySmall,
            )

        }

    }
}

@Composable
fun DataItemList(dataItems: List<DataItem>, navController: NavController) {
    LazyColumn {
        items(dataItems.size) { item ->
            DataItemView(dataItems[item], navController)
        }
    }
}

//@Preview
//@Composable
//fun GreetingPreview() {
//    LabsTheme {
//        DataItemList(dataItems)
//    }
//}
