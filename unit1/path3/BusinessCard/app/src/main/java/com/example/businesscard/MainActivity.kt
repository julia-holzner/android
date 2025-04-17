package com.example.businesscard

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BusinessCard(
                        name = "Julia Holzner",
                        modifier = Modifier.padding(innerPadding),
                        title = "Bachelor of Science",
                        telefoneNr = "+49 157 33908143",
                        email = "julia.holzner@gmx.net",
                        photo = R.drawable.android_logo,
                        linkedInUrl = "https://www.linkedin.com/in/julia-holzner-694095239/",
                        linkedInName = "julia-holzner-694095239"
                    )
                }
            }
        }
    }
}

@Composable
fun BusinessCard(
    name: String,
    modifier: Modifier = Modifier,
    title: String,
    telefoneNr: String,
    email: String,
    photo: Int,
    linkedInUrl: String,
    linkedInName: String
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(Color.Cyan)
            .fillMaxSize()
            .padding(30.dp)
    ) {
        NameCard(
            name = name,
            title = title,
            photo = photo
        )
        Spacer(
            modifier = modifier.padding(100.dp)
        )
        ContactCard(
            linkedInName = linkedInName,
            telefoneNr = telefoneNr,
            email = email,
            linkedInUrl = linkedInUrl
        )
    }
}

@Composable
fun NameCard(modifier: Modifier = Modifier, name: String, title: String, photo: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .height(150.dp)
                .background(Color.Blue)
        ) {
            Image(
                painter = painterResource(photo),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
            )
        }
        Text(
            name,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Text(
            title
        )
    }
}

@Composable
fun ContactCard(
    telefoneNr: String,
    email: String,
    modifier: Modifier = Modifier,
    linkedInUrl: String,
    linkedInName: String
) {
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconCard(
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Phone,
                    contentDescription = "Phone"
                )
            },
            text = telefoneNr
        )
        Box(
            modifier = modifier.clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkedInUrl))
                context.startActivity(intent)
            }
        )
        {
            IconCard(
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.linkedin_logo),
                        contentDescription = "LinkedIn",
                        modifier = Modifier.height(20.dp)
                    )
                },
                text = linkedInName
            )
        }
        IconCard(
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Email,
                    contentDescription = "Email"
                )
            },
            text = email
        )
    }
}

@Composable
fun IconCard(text: String, icon: @Composable () -> Unit, modifier: Modifier = Modifier) {
    Row {
        icon()
        Spacer(
            modifier = modifier.padding(start = 10.dp)
        )
        Text(
            text,
            modifier = modifier.width(200.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
        BusinessCard(
            name = "Julia Holzner",
            title = "Bachelor of Science",
            telefoneNr = "+49 157 33908143",
            email = "julia.holzner@gmx.net",
            photo = R.drawable.android_logo,
            linkedInName = "julia-holzner-694095239",
            linkedInUrl = "https://www.linkedin.com/in/julia-holzner-694095239/"
        )
    }
}