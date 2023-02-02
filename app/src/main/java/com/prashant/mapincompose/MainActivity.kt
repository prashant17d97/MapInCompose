package com.prashant.mapincompose

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.*
import com.google.gson.Gson
import com.google.maps.android.compose.*
import com.prashant.mapincompose.model.ResponseItem
import com.prashant.mapincompose.ui.theme.MapInComposeTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapInComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

fun latLng(): List<LatLng> {
    val latLng = arrayListOf<LatLng>()
    val jsonString = "[\n" +
            "  {\n" +
            "    \"name\": \"KR Market (Kalasipalya)\",\n" +
            "    \"latitude\": 12.9602,\n" +
            "    \"longitude\": 77.5772,\n" +
            "    \"distance\": 0.0,\n" +
            "    \"seq\": 1,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"Y\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Makkala Koota\",\n" +
            "    \"latitude\": 12.9578,\n" +
            "    \"longitude\": 77.5739,\n" +
            "    \"distance\": 0.44653288,\n" +
            "    \"seq\": 4,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Mahila Seva Samaja\",\n" +
            "    \"latitude\": 12.9536,\n" +
            "    \"longitude\": 77.5739,\n" +
            "    \"distance\": 0.8165801,\n" +
            "    \"seq\": 5,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"Y\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"National College\",\n" +
            "    \"latitude\": 12.9493,\n" +
            "    \"longitude\": 77.5738,\n" +
            "    \"distance\": 1.2669264,\n" +
            "    \"seq\": 6,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Basavanagudi Police Station\",\n" +
            "    \"latitude\": 12.9413,\n" +
            "    \"longitude\": 77.5738,\n" +
            "    \"distance\": 2.1336792,\n" +
            "    \"seq\": 7,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"Y\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Gunasheela Hospital\",\n" +
            "    \"latitude\": 12.9406,\n" +
            "    \"longitude\": 77.5763,\n" +
            "    \"distance\": 2.1815934,\n" +
            "    \"seq\": 8,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"South End Circle\",\n" +
            "    \"latitude\": 12.9362,\n" +
            "    \"longitude\": 77.5802,\n" +
            "    \"distance\": 2.6884046,\n" +
            "    \"seq\": 9,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"Y\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Nanda Talkies\",\n" +
            "    \"latitude\": 12.9325,\n" +
            "    \"longitude\": 77.5803,\n" +
            "    \"distance\": 3.0983837,\n" +
            "    \"seq\": 10,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"6th Block Jayanagara\",\n" +
            "    \"latitude\": 12.9309,\n" +
            "    \"longitude\": 77.5781,\n" +
            "    \"distance\": 3.2595484,\n" +
            "    \"seq\": 11,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Yediyuru\",\n" +
            "    \"latitude\": 12.9308,\n" +
            "    \"longitude\": 77.5764,\n" +
            "    \"distance\": 3.2702692,\n" +
            "    \"seq\": 12,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Deepak Nursing Home\",\n" +
            "    \"latitude\": 12.9259,\n" +
            "    \"longitude\": 77.5772,\n" +
            "    \"distance\": 3.8139694,\n" +
            "    \"seq\": 13,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Hunasemara (Towards Banashankari TTMC)\",\n" +
            "    \"latitude\": 12.9197,\n" +
            "    \"longitude\": 77.5742,\n" +
            "    \"distance\": 4.515173,\n" +
            "    \"seq\": 14,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Banashankari TTMC\",\n" +
            "    \"latitude\": 12.9174,\n" +
            "    \"longitude\": 77.573,\n" +
            "    \"distance\": 4.7808924,\n" +
            "    \"seq\": 15,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"Y\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Sarakki\",\n" +
            "    \"latitude\": 12.9095,\n" +
            "    \"longitude\": 77.573,\n" +
            "    \"distance\": 5.6559815,\n" +
            "    \"seq\": 16,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Jaraganahalli Cross\",\n" +
            "    \"latitude\": 12.9014,\n" +
            "    \"longitude\": 77.5735,\n" +
            "    \"distance\": 6.5506253,\n" +
            "    \"seq\": 17,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"Y\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Yelachenahalli\",\n" +
            "    \"latitude\": 12.8975,\n" +
            "    \"longitude\": 77.5716,\n" +
            "    \"distance\": 6.9983172,\n" +
            "    \"seq\": 18,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Metro\",\n" +
            "    \"latitude\": 12.8932,\n" +
            "    \"longitude\": 77.5676,\n" +
            "    \"distance\": 7.5224814,\n" +
            "    \"seq\": 19,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Konanakunte Cross\",\n" +
            "    \"latitude\": 12.8904,\n" +
            "    \"longitude\": 77.5645,\n" +
            "    \"distance\": 7.8826017,\n" +
            "    \"seq\": 20,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"Y\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Doddakallasandra\",\n" +
            "    \"latitude\": 12.8858,\n" +
            "    \"longitude\": 77.5564,\n" +
            "    \"distance\": 8.574683,\n" +
            "    \"seq\": 21,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Gubbalala Gate\",\n" +
            "    \"latitude\": 12.8841,\n" +
            "    \"longitude\": 77.5512,\n" +
            "    \"distance\": 8.918849,\n" +
            "    \"seq\": 22,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Raghuvanahalli Cross\",\n" +
            "    \"latitude\": 12.8809,\n" +
            "    \"longitude\": 77.5462,\n" +
            "    \"distance\": 9.436071,\n" +
            "    \"seq\": 23,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"Y\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Bayyanapalya\",\n" +
            "    \"latitude\": 12.8755,\n" +
            "    \"longitude\": 77.5436,\n" +
            "    \"distance\": 10.097772,\n" +
            "    \"seq\": 24,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Thalagattapura\",\n" +
            "    \"latitude\": 12.8693,\n" +
            "    \"longitude\": 77.5368,\n" +
            "    \"distance\": 11.015552,\n" +
            "    \"seq\": 25,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"Y\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Vajramuneshwara Gate\",\n" +
            "    \"latitude\": 12.8647,\n" +
            "    \"longitude\": 77.5332,\n" +
            "    \"distance\": 11.640716,\n" +
            "    \"seq\": 26,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Jyothi Farm\",\n" +
            "    \"latitude\": 12.8599,\n" +
            "    \"longitude\": 77.529,\n" +
            "    \"distance\": 12.31584,\n" +
            "    \"seq\": 27,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"NICE Road\",\n" +
            "    \"latitude\": 12.8568,\n" +
            "    \"longitude\": 77.5267,\n" +
            "    \"distance\": 12.733876,\n" +
            "    \"seq\": 28,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Chinnayyanapalya Silk Farm\",\n" +
            "    \"latitude\": 12.8536,\n" +
            "    \"longitude\": 77.5234,\n" +
            "    \"distance\": 13.210221,\n" +
            "    \"seq\": 29,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"Y\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Agara Gate Kanakapura Road\",\n" +
            "    \"latitude\": 12.8489,\n" +
            "    \"longitude\": 77.5219,\n" +
            "    \"distance\": 13.7513275,\n" +
            "    \"seq\": 30,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Chowdeshwarinagara Thataguni\",\n" +
            "    \"latitude\": 12.8499,\n" +
            "    \"longitude\": 77.5188,\n" +
            "    \"distance\": 13.802011,\n" +
            "    \"seq\": 31,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Thataguni BWSSB\",\n" +
            "    \"latitude\": 12.8485,\n" +
            "    \"longitude\": 77.5161,\n" +
            "    \"distance\": 14.075875,\n" +
            "    \"seq\": 32,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Chikka Thataguni\",\n" +
            "    \"latitude\": 12.8454,\n" +
            "    \"longitude\": 77.5142,\n" +
            "    \"distance\": 14.476992,\n" +
            "    \"seq\": 33,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Thataguni\",\n" +
            "    \"latitude\": 12.8437,\n" +
            "    \"longitude\": 77.5092,\n" +
            "    \"distance\": 14.904184,\n" +
            "    \"seq\": 34,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"Y\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Basappanapalya Cross\",\n" +
            "    \"latitude\": 12.8425,\n" +
            "    \"longitude\": 77.5023,\n" +
            "    \"distance\": 15.401416,\n" +
            "    \"seq\": 35,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Stone Factory\",\n" +
            "    \"latitude\": 12.8443,\n" +
            "    \"longitude\": 77.4977,\n" +
            "    \"distance\": 15.502701,\n" +
            "    \"seq\": 36,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Sri Swananda Ashrama\",\n" +
            "    \"latitude\": 12.8441,\n" +
            "    \"longitude\": 77.4943,\n" +
            "    \"distance\": 15.728957,\n" +
            "    \"seq\": 37,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"N\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Agara (Towards Gangasandra)\",\n" +
            "    \"latitude\": 12.8445,\n" +
            "    \"longitude\": 77.4871,\n" +
            "    \"distance\": 16.152168,\n" +
            "    \"seq\": 38,\n" +
            "    \"time\": null,\n" +
            "    \"fareStage\": \"Y\"\n" +
            "  }\n" +
            "]"
    val testModel = Gson().fromJson(jsonString, Array<ResponseItem>::class.java)

    testModel.forEach {
        latLng.add(LatLng(it.latitude ?: 0.0, it.longitude ?: 0.0))
    }
    return latLng
}

@Composable
fun Greeting() {
    val context = LocalContext.current
    val latLng = latLng()
    var latLong by remember {
        mutableStateOf(latLng[0])
    }
    var destination by remember {
        mutableStateOf(latLng[0])
    }
    val initialZoom = 10f
    val finalZoom = 15f
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latLong, initialZoom)
    }

    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = true) {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newCameraPosition(
                CameraPosition(destination, finalZoom, 0f, 0f)
            ),
            durationMs = 1500
        )
    }
    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapLongClick = {
                latLong = it
            }
        ) {
            Marker(
                state = MarkerState(position = latLong),
                title = "Singapore",
                snippet = "Marker in Singapore",
                icon = bitmapDescriptorResource(context, R.drawable.icon)
            )
            
            /*val latLngBounds = LatLngBounds.builder().include(LatLng(37.418436, -122.085099)).include(LatLng(37.7749, -122.4194)).build()
              nbPolygon(points =latLng)*/

            Polyline(
                points = latLng,
                jointType = JointType.ROUND,
                geodesic = false,
                clickable = true/*,
            pattern = listOf(
                Dot(),
                Gap(10f)
            )*/,
                color = Color.Blue,
                onClick = {
                    Log.e("TAG", "Polyline: $it")
                }
            )
        }
        Column {
            Button(onClick = {
                destination = latLng[0]
                coroutineScope.launch {
                    cameraPositionState.animate(
                        update = CameraUpdateFactory.newCameraPosition(
                            CameraPosition(destination, finalZoom, 0f, 0f)
                        ),
                        durationMs = 1500
                    )
                }
            }) {
                Text(text = "1")
            }

            Button(onClick = {
                destination = latLng[10]
                coroutineScope.launch {
                    cameraPositionState.animate(
                        update = CameraUpdateFactory.newCameraPosition(
                            CameraPosition(destination, finalZoom, 0f, 0f)
                        ),
                        durationMs = 1500
                    )
                }
            }) {
                Text(text = "2")
            }
            Button(onClick = {
                destination = latLng[20]
                coroutineScope.launch {
                    cameraPositionState.animate(
                        update = CameraUpdateFactory.newCameraPosition(
                            CameraPosition(destination, finalZoom, 0f, 0f)
                        ),
                        durationMs = 1500
                    )
                }
            }) {
                Text(text = "3")
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MapInComposeTheme {
        Greeting()
    }
}

fun bitmapDescriptorResource(context: Context, icon: Int): BitmapDescriptor {
    val drawable = ContextCompat.getDrawable(context, icon)
    val bitmap = Bitmap.createBitmap(
        drawable!!.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}