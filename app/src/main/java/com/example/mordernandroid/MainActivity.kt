package com.example.mordernandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mordernandroid.ui.theme.MordernAndroidTheme
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.text.font.FontWeight
import com.example.mordernandroid.ui.components.AlignYourBodyRow
import com.example.mordernandroid.ui.components.FavoriteCollectionsGrid
import com.example.mordernandroid.ui.components.SearchBar


class MainActivity : ComponentActivity() {

//    private val commentsViewModel: CommentsViewModel by viewModels()
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MordernAndroidTheme {
                MainApp(modifier = Modifier.fillMaxSize())
            }
//            val windowSizeClass = calculateWindowSizeClass(activity = this)
//            MySootheApp(windowSize = windowSizeClass)
        }
    }
}

/**
 * Resuing composables
 */
@Composable
private fun MainApp(
    modifier: Modifier = Modifier
) {
    var shouldShowOnboarding by remember { mutableStateOf(true) }
    Surface (modifier) {
       CommentScreen()
    }
}

@Composable
fun MySootheApp(windowSize: WindowSizeClass) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
//            MySootheAppPortrait()
        }
        WindowWidthSizeClass.Expanded -> {
//            MySootheAppLandscape()
        }
    }
}

@Composable
fun OnBoardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    // TODO: This state should be hoisted

    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text(text = "Continue")
        }
    }
}




@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem (
           icon = {
               Icon(
                   imageVector = Icons.Default.Email,
                   contentDescription = null
               )
           },
            label = {
                Text(
                    text = stringResource(id = R.string.bottom_navigation_home)
                )
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem (
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(id = R.string.bottom_navigation_profile)
                )
            },
            selected = true,
            onClick = {}
        )
    }
}

@Composable
private fun SootheNavigationRail(
    modifier: Modifier = Modifier
) {
    NavigationRail (
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ){
        Column(

        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = stringResource(id = R.string.bottom_navigation_home))
                },
                selected = true,
                onClick = {}
            )

            NavigationRailItem(
                selected = false ,
                onClick = { },
                label = {
                    Text(text = stringResource(id = R.string.bottom_navigation_profile))
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                }
            )
        }
    }
}



//@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
//@Composable
//fun HomeSectionPreview() {
//    MySootheTheme {
//        HomeSection(title = R.id.align_your_body) {
//            AlignYourBodyRow()
//        }
//    }
//}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.app_name) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.fc2_nature_meditations) {
            FavoriteCollectionsGrid()
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun CommentScreen(
    modifier: Modifier = Modifier
) {
    Column (modifier = Modifier.padding(20.dp)){
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            SearchBar(Modifier.weight(1f))
            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {  }
            ) {
                Text("Search")
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        InfoRow("CommentId", "")
        InfoRow("Name", "")
        InfoRow("Email", "")
        InfoRow("Comment", "")

    }
}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = listOf("World", "Compose")
) {
    SootheBottomNavigation()
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = label, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = value)
    }
}


@Composable
fun Greeting(name: String) {
    // Use remember func to stay value of var
    val expanded = remember { mutableStateOf(false) }

    val extraPadding = if (expanded.value) 48.dp else 0.dp
    Surface (
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
        Row (modifier = Modifier.padding(24.dp)) {
            Column (modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello,")
                Text(text = name)
            }
            // Adding a button
            ElevatedButton(onClick = { expanded.value = !expanded.value }
            ) {
                Text(if (expanded.value) "Show less" else "Show more")
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    MordernAndroidTheme {
        MainApp(Modifier.fillMaxSize())
    }
}

//@Preview
//@Composable
//fun DefaultPreview() {
//    MordernAndroidTheme {
//        MainApp()
//    }
//}