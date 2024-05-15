package com.p3.offices.carappservice.screen

import androidx.car.app.CarContext
import androidx.car.app.CarToast
import androidx.car.app.Screen
import androidx.car.app.connection.CarConnection
import androidx.car.app.model.Action
import androidx.car.app.model.CarIcon
import androidx.car.app.model.MessageTemplate
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import androidx.core.graphics.drawable.IconCompat
import com.example.places.carappservice.R
import com.p3.offices.data.PlacesRepository
import com.p3.offices.data.model.toIntent

/**
 * Screen used to display additional info for a specific place (P3 Office)
 */
class DetailScreen(
    carContext: CarContext,
    private val placeId: Int,
    private val currentCarConnectionType: Int
) : Screen(carContext) {

    override fun onGetTemplate(): Template {
        val place = PlacesRepository().getPlace(placeId)
            ?: return MessageTemplate.Builder(
                carContext.resources.getString(R.string.warning_office_not_found)
            )
                .setHeaderAction(Action.BACK)
                .build()


        val navigateAction = Action.Builder()
            .setTitle(carContext.resources.getString(com.example.android.cars.data.R.string.go_to))
            .setIcon(
                CarIcon.Builder(
                    IconCompat.createWithResource(
                        carContext,
                        R.drawable.navigation_icon
                    )
                ).build()
            )
            .setOnClickListener {
                // for emulator we don't have a navigation app
                if (currentCarConnectionType == CarConnection.CONNECTION_TYPE_NATIVE) {
                    CarToast.makeText(
                        carContext,
                        R.string.warning_emulator_navigation,
                        CarToast.LENGTH_LONG
                    ).show()
                } else {
                    carContext.startCarApp(place.toIntent(CarContext.ACTION_NAVIGATE))
                }
            }
            .build()

        return PaneTemplate.Builder(
            Pane.Builder()
                .addAction(navigateAction)
                .addRow(
                    Row.Builder()
                        .setTitle(carContext.resources.getString(R.string.location))
                        .addText("${place.latitude}, ${place.longitude}")
                        .build()
                ).addRow(
                    Row.Builder()
                        .setTitle(carContext.resources.getString(R.string.description))
                        .addText(place.description)
                        .build()
                ).build()
        )
            .setTitle(place.name)
            .setHeaderAction(Action.BACK)
            .build()
    }

}