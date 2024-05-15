/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.p3.offices

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.car.app.connection.CarConnection
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.android.cars.places.R
import com.p3.offices.data.PlacesRepository
import com.p3.offices.data.model.Place
import com.p3.offices.data.model.toIntent
import com.p3.offices.ui.theme.P3Color
import com.p3.offices.ui.theme.PlacesTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val carConnectionType by CarConnection(this).type.observeAsState(
                initial = CONNECTION_TYPE_UNKNOWN
            )
            PlacesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Text(
                            text = stringResource(id = R.string.app_name),
                            style = MaterialTheme.typography.displayLarge,
                            modifier = Modifier.padding(8.dp)
                        )
                        ProjectionState(
                            carConnectionType = carConnectionType,
                            modifier = Modifier.padding(8.dp)
                        )
                        PlaceList(places = PlacesRepository().getPlaces())
                    }
                }
            }
        }
    }

    private companion object {
        const val CONNECTION_TYPE_UNKNOWN = -1
    }
}

@Composable
fun PlaceList(places: List<Place>) {
    val context = LocalContext.current

    LazyColumn {
        items(places.size) {
            val place = places[it]
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(
                        2.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        context.startActivity(place.toIntent(Intent.ACTION_VIEW))
                    }
                    .padding(8.dp)
            ) {
                Icon(
                    Icons.Default.Place,
                    "Place icon",
                    modifier = Modifier.align(CenterVertically),
                    tint = P3Color
                )
                Column {
                    Text(
                        text = place.name,
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = place.description,
                        style = MaterialTheme.typography.bodyMedium,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }

            }
        }
    }
}

@Composable
fun ProjectionState(carConnectionType: Int, modifier: Modifier = Modifier) {
    val text = when (carConnectionType) {
        CarConnection.CONNECTION_TYPE_NOT_CONNECTED -> stringResource(id = R.string.current_projection_status, "Not projecting")
        CarConnection.CONNECTION_TYPE_NATIVE -> stringResource(id = R.string.current_projection_status,"Running on Android Automotive OS")
        CarConnection.CONNECTION_TYPE_PROJECTION -> stringResource(id = R.string.current_projection_status,"Projecting")
        else -> stringResource(id = R.string.current_projection_status,"Unknown connection type")
    }

    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}