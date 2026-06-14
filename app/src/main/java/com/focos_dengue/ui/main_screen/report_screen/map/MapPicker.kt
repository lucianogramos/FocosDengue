package com.focos_dengue.ui.main_screen.report_screen.map

import androidx.compose.runtime.*
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun MapPicker(
    onLocationSelected: (Double, Double) -> Unit
) {

    var markerPosition by remember {
        mutableStateOf<LatLng?>(null)
    }

    GoogleMap(
        onMapClick = { latLng ->

            markerPosition = latLng

            onLocationSelected(
                latLng.latitude,
                latLng.longitude
            )
        }
    ) {

        markerPosition?.let {

            Marker(
                state = MarkerState(
                    position = it
                )
            )
        }
    }
}