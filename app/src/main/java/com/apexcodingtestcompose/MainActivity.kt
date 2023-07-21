package com.apexcodingtestcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.apex.codeassesment.data.model.User
import com.apexcodingtestcompose.ui.theme.ApexCodingTestComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApexCodingTestComposeTheme {
                MainActivityScreen()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainActivityScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            ProgressBarView()
            Spacer(modifier = Modifier.height(4.dp))
            UserDetailView(user = User.createRandom())
            Spacer(modifier = Modifier.height(4.dp))
            RefreshUserButton({ User.createRandom()  })
            Spacer(modifier = Modifier.height(4.dp))
            ShowUserListButton()
            Spacer(modifier = Modifier.height(4.dp))
            UserListView( users = listOf(
               User.createRandom(),
                User.createRandom(),
                User.createRandom(),
                User.createRandom(),
                User.createRandom(),
                User.createRandom(),
                User.createRandom(),
                User.createRandom(),
                User.createRandom(),
                User.createRandom(),
                // Add more users as needed
            ),
            onItemClick = {})
        }
    }
}

@Composable
fun ProgressBarView() {
    val isVisible = remember { mutableStateOf(false) }
    if (isVisible.value) {
        LinearProgressIndicator(
            modifier = Modifier
                .size(50.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun UserDetailView(user: User) {

    val context= LocalContext.current
    Row {
        ImageFromUrl("https://randomuser.me/api/portraits/thumb/men/55.jpg")
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
        ) {
            Text(
                text = "Name",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = "${user.name?.first}  ${user.name?.last} }" ,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Email",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = "${user.email}",
                style = MaterialTheme.typography.bodyMedium
            )

            Button(onClick = {
                    Toast.makeText(context,"Details Page",Toast.LENGTH_LONG).show()
            }, modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth()) {
                Text(text = "Details")
            }
        }
    }
}

@Composable
fun RefreshUserButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Refresh Random User")
    }
}

@Composable
fun ShowUserListButton() {
    Button(
        onClick = { /* TODO: Implement the logic to show the list of users */ },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Show 10 Users")
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainActivityScreen()
}

@Composable
fun UserListView(users: List<User>, onItemClick: (User) -> Unit) {
    LazyColumn {
        items(users) { user ->
            UserItem(user = user, onItemClick = onItemClick)
        }
    }
}

@Composable
fun UserItem(user: User, onItemClick: (User) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp, horizontal = 4.dp)
            .clickable { onItemClick(user) },
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(
                    data = User.randomImage() ?: "",
                    builder = {
                        crossfade(true)
                    }
                ),
                contentDescription = "User Image",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "${user.name?.first} ${user.name?.last}",
                    style = MaterialTheme.typography.bodyMedium,
                )
                // Add other user details if needed
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserItemPreview() {
    UserItem(
        user = User.createRandom(),
        onItemClick = {}
    )
}

@Composable
fun ImageFromUrl(imageUrl: String) {
    val painter: Painter = rememberImagePainter(
        data = imageUrl,
        builder = {
            crossfade(true) // Optional: Apply crossfade animation when loading the image
        }
    )
    Image(
        painter = painter,
        contentDescription = "Image from URL",
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape),

        contentScale = ContentScale.Fit

    )
}
