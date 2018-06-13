package permissions.dispatcher.samplekotlin.sensor

import android.annotation.SuppressLint

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout

import permissions.dispatcher.samplekotlin.R

import android.hardware.SensorEvent
import android.content.Context.SENSOR_SERVICE
import android.hardware.SensorEventListener



/**
 * Displays a [CameraPreview] of the first [sensor].
 * An error message is displayed if the sensor is not available.
 *
 *
 * This Fragment is only used to illustrate that access to the sensor API has been granted (or
 * denied) as part of the runtime permissions model. It is not relevant for the use of the
 * permissions API.
 *
 *
 * Implementation is based directly on the documentation at
 * http://developer.android.com/guide/topics/media/sensor.html
 */

 class SensorPreviewFragment :  SensorEventListener {
        private var mSensorManager: SensorManager? = null
        private var mLight: Sensor? = null
        private val sensor: Sensor? = null

        @SuppressLint("InflateParams")
 /*
        fun onCreate(inflater: LayoutInflater,
                     container: ViewGroup?,
                // savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_sensor, null){
                //super.onCreate(savedInstanceState)
                //setContentView(R.layout.fragment_sensor)

                // mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
                //  mLight = mSensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
}
*/

     fun onViewCreated(view: View, savedInstanceState: Bundle?) {

         // super.onViewCreated(view, savedInstanceState)
        val button: Button? = view.findViewById(R.id.back)
        button?.setOnClickListener {
          //  fragmentManager?.popBackStack()
        }
        //initSensor()
    }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            // Do something here if sensor accuracy changes.
        }

        override fun onSensorChanged(event: SensorEvent) {
            // The light sensor returns a single value.
            // Many sensors return 3 values, one for each axis.
            val lux = event.values[0]
            // Do something with this sensor value.
        }

    public fun onResume() {
            //super.onResume()
            mSensorManager!!.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL)
        }

    public  fun onPause() {
           // super.onPause()
            mSensorManager!!.unregisterListener(this)
        }



    }