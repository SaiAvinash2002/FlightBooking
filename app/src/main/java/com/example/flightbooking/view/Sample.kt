package com.example.flightbooking.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightBookingScreen() {
    var selectedTripType by remember { mutableStateOf("One way") }
    var fromCity by remember { mutableStateOf("Delhi") }
    var toCity by remember { mutableStateOf("Kolkata") }
    var departureDate by remember { mutableStateOf("15/07/2022") }
    var travellers by remember { mutableStateOf("1 Adult") }
    var travelClass by remember { mutableStateOf("Economy") }

    val gradientOrange = Brush.horizontalGradient(
        colors = listOf(Color(0xFFFF5722), Color(0xFFFF6347))
    )

    Scaffold(
        topBar = {
            Column {

                // Top App Bar
                TopAppBar(
                    title = {
                        Text(
                            "Book Flight",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E293B)
                        )
                    },
                    actions = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White
                    )
                )
            }
        },
        bottomBar = {
            BottomNavigationBar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFAFAFA))
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Trip Type Selector
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TripTypeButton("One way", selectedTripType == "One way") {
                    selectedTripType = "One way"
                }
                TripTypeButton("Round", selectedTripType == "Round") {
                    selectedTripType = "Round"
                }
                TripTypeButton("Multicity", selectedTripType == "Multicity") {
                    selectedTripType = "Multicity"
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Booking Form Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // From
                    LocationField(
                        label = "From",
                        cityName = fromCity,
                        cityCode = "DEL",
                        airportName = "Indira Gandhi International Airport",
                        icon = Icons.Default.Flight,
                        iconRotation = 45f
                    )

                    // Swap Button
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        FloatingActionButton(
                            onClick = {
                                val temp = fromCity
                                fromCity = toCity
                                toCity = temp
                            },
                            modifier = Modifier.size(48.dp),
                            containerColor = Color(0xFFFF5722),
                            shape = CircleShape
                        ) {
                            Icon(
                                Icons.Default.SwapVert,
                                contentDescription = "Swap",
                                tint = Color.White
                            )
                        }
                    }

                    // To
                    LocationField(
                        label = "To",
                        cityName = toCity,
                        cityCode = "CCU",
                        airportName = "Subhash Chandra International Airport",
                        icon = Icons.Default.Flight,
                        iconRotation = -45f
                    )

                    // Date Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Departure",
                                fontSize = 12.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            DateField(departureDate)
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Return",
                                fontSize = 12.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            AddReturnDateButton()
                        }
                    }

                    // Traveller and Class Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Traveller",
                                fontSize = 12.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            InfoField(travellers)
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Class",
                                fontSize = 12.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            InfoField(travelClass)
                        }
                    }

                    // Search Button
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(gradientOrange),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Search",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Hot Offers Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Hot offer",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B)
                )
                Text(
                    "See all",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFFF5722),
                    modifier = Modifier.clickable { }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    OfferCard(
                        title = "15% discount",
                        subtitle = "with mastercard",
                        discount = "15%OFF",
                        backgroundColor = Color(0xFFFFF3E0),
                        accentColor = Color(0xFFFF6F00)
                    )
                }
                item {
                    OfferCard(
                        title = "23% discount",
                        subtitle = "with visa",
                        discount = "23%OFF",
                        backgroundColor = Color(0xFFE8EAF6),
                        accentColor = Color(0xFF3F51B5)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun TripTypeButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) {
        Brush.horizontalGradient(colors = listOf(Color(0xFFFF5722), Color(0xFFFF6347)))
    } else {
        Brush.horizontalGradient(colors = listOf(Color.White, Color.White))
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .background(backgroundColor)
            .then(
                if (!isSelected) Modifier.border(
                    1.dp,
                    Color(0xFFE0E0E0),
                    RoundedCornerShape(24.dp)
                ) else Modifier
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (isSelected) Color.White else Color(0xFF64748B)
        )
    }
}

@Composable
fun LocationField(
    label: String,
    cityName: String,
    cityCode: String,
    airportName: String,
    icon: ImageVector,
    iconRotation: Float
) {
    Column {
        Text(
            label,
            fontSize = 12.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF8FAFC), RoundedCornerShape(12.dp))
                .clickable { }
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .rotate(iconRotation),
                tint = Color(0xFF64748B)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        cityName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        cityCode,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
                Text(
                    airportName,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Icon(
                Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun DateField(date: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF8FAFC), RoundedCornerShape(12.dp))
            .clickable { }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Default.CalendarToday,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = Color(0xFF64748B)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            date,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1E293B)
        )
    }
}

@Composable
fun AddReturnDateButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF8FAFC), RoundedCornerShape(12.dp))
            .clickable { }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "+ Add Return Date",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFFFF5722)
        )
    }
}

@Composable
fun InfoField(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF8FAFC), RoundedCornerShape(12.dp))
            .clickable { }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1E293B)
        )
    }
}

@Composable
fun OfferCard(
    title: String,
    subtitle: String,
    discount: String,
    backgroundColor: Color,
    accentColor: Color
) {
    Card(
        modifier = Modifier
            .width(220.dp)
            .height(180.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Card Icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.White, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                if (title.contains("mastercard")) {
                    Row {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(Color.Red, CircleShape)
                        )
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .offset(x = (-8).dp)
                                .background(Color(0xFFFF9800), CircleShape)
                        )
                    }
                } else {
                    Text(
                        "VISA",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Black,
                        color = Color(0xFF1565C0)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                discount,
                fontSize = 28.sp,
                fontWeight = FontWeight.Black,
                color = Color(0xFF1E293B)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E293B)
            )

            Text(
                subtitle,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF475569)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                "Lorem ipsum dolor sit am et adip",
                fontSize = 12.sp,
                color = Color.Gray,
                maxLines = 2
            )
        }
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(
        containerColor = Color(0xFFFF5722),
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.White) },
            label = { Text("Home", color = Color.White, fontSize = 12.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                indicatorColor = Color.White.copy(alpha = 0.2f),
                unselectedIconColor = Color.White.copy(alpha = 0.7f),
                unselectedTextColor = Color.White.copy(alpha = 0.7f)
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Default.BookOnline, contentDescription = "Booking", tint = Color.White.copy(alpha = 0.7f)) },
            label = { Text("Booking", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                indicatorColor = Color.White.copy(alpha = 0.2f),
                unselectedIconColor = Color.White.copy(alpha = 0.7f),
                unselectedTextColor = Color.White.copy(alpha = 0.7f)
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Default.Explore, contentDescription = "Inbox", tint = Color.White.copy(alpha = 0.7f)) },
            label = { Text("Inbox", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                indicatorColor = Color.White.copy(alpha = 0.2f),
                unselectedIconColor = Color.White.copy(alpha = 0.7f),
                unselectedTextColor = Color.White.copy(alpha = 0.7f)
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Default.Email, contentDescription = "Mail", tint = Color.White.copy(alpha = 0.7f)) },
            label = { Text("Mail", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                indicatorColor = Color.White.copy(alpha = 0.2f),
                unselectedIconColor = Color.White.copy(alpha = 0.7f),
                unselectedTextColor = Color.White.copy(alpha = 0.7f)
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile", tint = Color.White.copy(alpha = 0.7f)) },
            label = { Text("Profile", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                indicatorColor = Color.White.copy(alpha = 0.2f),
                unselectedIconColor = Color.White.copy(alpha = 0.7f),
                unselectedTextColor = Color.White.copy(alpha = 0.7f)
            )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FlightBookingScreenPrev() {
    FlightBookingScreen()
}