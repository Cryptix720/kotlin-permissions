package permissions.dispatcher.samplekotlin.sensor

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEventListener2
import android.hardware.SensorManager
import android.util.Log
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView

import java.io.IOException
import kotlin.properties.Delegates
/*
/**
 * sensor preview that displays a [sensor].

 * Handles basic lifecycle methods to display and stop the preview.
 *
 *
 * Implementation is based directly on the documentation at
 * http://developer.android.com/guide/topics/media/sensor.html
 */
@SuppressLint("ViewConstructor")
class SensorPreview(context: Context,
                    sensor: Sensor,
                    sensorInfo: sensor.SensorInfo,
                    displayOrientation: Int) : SurfaceView(context), SurfaceHolder.Callback {
    private val mHolder: SurfaceHolder
    private var mSensor: sensor by Delegates.notNull<Sensor>()
    private val mSensorManager: SensorManager? = null
    private var mSensorInfo: sensor.SensorInfo by Delegates.notNull<Sensor>()
    private var mDisplayOrientation: Int = 0

    init {
        setSensor(sensor, sensorInfo, displayOrientation)

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = holder
        mHolder.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        // The Surface has been created, now tell the sensor where to draw the preview.
        try {
            mSensor.setPreviewDisplay(holder)
            mSensor.startPreview()

            Log.d(TAG, "sensor preview started.")
        } catch (e: IOException) {
            Log.d(TAG, "Error setting sensor preview: " + e.message)
        }

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        // empty. Take care of releasing the sensor preview in your activity.
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, w: Int, h: Int) {
        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.

        mHolder.takeIf { it.surface != null }?.let{
            // stop preview before making changes
            try {
                mSensor.stopPreview()
                Log.d(TAG, "Preview stopped.")
            } catch (e: Exception) {
                // ignore: tried to stop a non-existent preview
                Log.d(TAG, "Error starting sensor preview: " + e.message)
            }

            val orientation = calculatePreviewOrientation(mSensorInfo, mDisplayOrientation)
            mSensor.setDisplayOrientation(orientation)

            try {
                mSensor.setPreviewDisplay(it)
                mSensor.startPreview()
                Log.d(TAG, "sensor preview started.")
            } catch (e: Exception) {
                Log.d(TAG, "Error starting sensor preview: " + e.message)
            }
        } ?: let {
            // preview surface does not exist
            Log.d(TAG, "Preview surface does not exist")
        }
    }

    fun setSensor(sensor: sensor,
                  sensorInfo: sensor.SensorInfo,
                  displayOrientation: Int) {
        mSensor = sensor
        mSensorInfo = SensorInfo
        mDisplayOrientation = displayOrientation
    }



    companion object {
        private val TAG = "SensorPreview"

        /**
         * Calculate the correct orientation for a [sensor] preview that is displayed on screen.

         * Implementation is based on the sample code provided in
         * [sensor.setDisplayOrientation].
         */
        fun calculatePreviewOrientation(info: sensor.SensorInfo, rotation: Int) = when {
                info.facing == sensor.TYPE_AMBIENT_TEMPERATURE ->
                    info.facing == sensor.T
                    // compensate the mirror
                    (360 - (info.orientation + degrees(rotation)) % 360) % 360
                // back-facing
                else -> (info.orientation - degrees(rotation) + 360) % 360
            }

        private fun degrees(rotation: Int) = when (rotation) {
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> 0
        }
    }
}
*/