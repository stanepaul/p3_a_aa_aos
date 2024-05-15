package com.p3.offices.carappservice.screen

import android.text.Spannable
import android.text.SpannableString
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.connection.CarConnection
import androidx.car.app.model.CarLocation
import androidx.car.app.model.Distance
import androidx.car.app.model.DistanceSpan
import androidx.car.app.model.ItemList
import androidx.car.app.model.Metadata
import androidx.car.app.model.Place
import androidx.car.app.model.PlaceListMapTemplate
import androidx.car.app.model.PlaceMarker
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import com.example.places.carappservice.R
import com.p3.offices.data.PlacesRepository

/**
 * Main screen when the app is opened
 */
class MainScreen(carContext: CarContext) : Screen(carContext) {

    private var carConnectionType = -1

    override fun onGetTemplate(): Template {
        val carConnection = CarConnection(carContext)
        carConnection.type.observe(this) { connectionType ->
            carConnectionType = connectionType
        }

        val placesRepository = PlacesRepository()
        val itemListBuilder = ItemList.Builder()
            .setNoItemsMessage(carContext.resources.getString(R.string.warning_no_places_available))

        placesRepository.getPlaces()
            .forEach {
                itemListBuilder.addItem(
                    Row.Builder()
                        .setTitle(it.name)
                        // Each item in the list *must* have a DistanceSpan applied to either the title
                        // or one of the its lines of text (to help drivers make decisions)
                        .addText(SpannableString(" ").apply {
                            setSpan(
                                DistanceSpan.create(
                                    Distance.create(Math.random() * 100, Distance.UNIT_KILOMETERS)
                                ), 0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE
                            )
                        })
                        .setOnClickListener {
                            screenManager.push(DetailScreen(carContext, it.id, carConnectionType))
                        }
                        // Setting Metadata is optional, but is required to automatically show the
                        // item's location on the provided map
                        .setMetadata(
                            Metadata.Builder().setPlace(
                                Place.Builder(CarLocation.create(it.latitude, it.longitude))
                                    // Using the default PlaceMarker indicates that the host should
                                    // decide how to style the pins it shows on the map/in the list
                                    .setMarker(PlaceMarker.Builder().build())
                                    .build()
                            ).build()
                        ).build()
                )
            }

        return PlaceListMapTemplate.Builder()
            .setTitle(carContext.resources.getString(R.string.title))
            .setItemList(itemListBuilder.build())
            .build()
    }
}