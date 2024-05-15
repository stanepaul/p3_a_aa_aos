package com.p3.offices.carappservice

import android.content.Intent
import androidx.car.app.CarAppService
import androidx.car.app.Screen
import androidx.car.app.Session
import androidx.car.app.validation.HostValidator
import com.p3.offices.carappservice.screen.MainScreen

/**
 * Created by stane on 21.04.2024.
 */
class PlacesCarAppService : CarAppService() {

    override fun createHostValidator(): HostValidator {
        return HostValidator.ALLOW_ALL_HOSTS_VALIDATOR
    }

    override fun onCreateSession(): Session {
        // PlacesSession will be an unresolved reference until the next step
        return PlacesSession()
    }
}

class PlacesSession : Session() {
    override fun onCreateScreen(intent: Intent): Screen {
        // MainScreen will be an unresolved reference until the next step
        return MainScreen(carContext)
    }
}