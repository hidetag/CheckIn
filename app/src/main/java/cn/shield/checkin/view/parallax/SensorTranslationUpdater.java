package cn.shield.checkin.view.parallax;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Surface;
import android.view.WindowManager;

public class SensorTranslationUpdater
        implements ParallaxLayerLayout.TranslationUpdater, SensorEventListener {

    private static final int DEFAULT_SAMPLING_PERIOD = 100;
    private final SensorManager sensorManager;
    private float[] mTiltVector = new float[3];
    private boolean mTargeted = false;
    private float[] mTargetMatrix = new float[16];
    private float[] mRotationMatrix = new float[16];
    private float[] mOrientedRotationMatrix = new float[16];
    private float[] mTruncatedRotationVector;
    private float mTiltSensitivity = 2.0f;
    private ParallaxLayerLayout parallax;

    public SensorTranslationUpdater(Context context) {
        this((SensorManager) context.getSystemService(Context.SENSOR_SERVICE));
    }

    public SensorTranslationUpdater(SensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    @Override
    public void subscribe(ParallaxLayerLayout parallaxLayerLayout) {
        parallax = parallaxLayerLayout;
    }

    @Override
    public void unSubscribe() {
        parallax = null;
    }

    public void registerSensorManager() {
        if (sensorManager != null) {
            sensorManager.registerListener(this,
                    sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR), DEFAULT_SAMPLING_PERIOD);
        }
    }

    public void unregisterSensorManager() {
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (parallax == null) {
            return;
        }

        final float[] vectors = interpretSensorEvent(parallax.getContext(), event);
        if (vectors == null) {
            return;
        }

        float roll = vectors[2];
        float pitch = -vectors[1];

        parallax.updateTranslations(new float[]{roll, pitch});
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Nullable
    @SuppressWarnings("SuspiciousNameCombination")
    private float[] interpretSensorEvent(@NonNull Context context, @Nullable SensorEvent event) {
        if (event == null) {
            return null;
        }

        float[] rotationVector = getRotationVectorFromSensorEvent(event);

        if (!mTargeted) {
            setTargetVector(rotationVector);
            return null;
        }

        SensorManager.getRotationMatrixFromVector(mRotationMatrix, rotationVector);

        final int rotation =
                ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                        .getRotation();

        if (rotation == Surface.ROTATION_0) {
            SensorManager.getAngleChange(mTiltVector, mRotationMatrix, mTargetMatrix);
        } else {
            switch (rotation) {
                case Surface.ROTATION_90:
                    SensorManager.remapCoordinateSystem(mRotationMatrix, SensorManager.AXIS_Y,
                            SensorManager.AXIS_MINUS_X, mOrientedRotationMatrix);
                    break;

                case Surface.ROTATION_180:
                    SensorManager.remapCoordinateSystem(mRotationMatrix, SensorManager.AXIS_MINUS_X,
                            SensorManager.AXIS_MINUS_Y, mOrientedRotationMatrix);
                    break;

                case Surface.ROTATION_270:
                    SensorManager.remapCoordinateSystem(mRotationMatrix, SensorManager.AXIS_MINUS_Y,
                            SensorManager.AXIS_X, mOrientedRotationMatrix);
                    break;
                default:
                    break;
            }

            SensorManager.getAngleChange(mTiltVector, mOrientedRotationMatrix, mTargetMatrix);
        }

        for (int i = 0; i < mTiltVector.length; i++) {
            mTiltVector[i] /= Math.PI;

            mTiltVector[i] *= mTiltSensitivity;

            if (mTiltVector[i] > 1) {
                mTiltVector[i] = 1f;
            } else if (mTiltVector[i] < -1) {
                mTiltVector[i] = -1f;
            }
        }

        return mTiltVector;
    }

    @NonNull
    private float[] getRotationVectorFromSensorEvent(@NonNull SensorEvent event) {
        if (event.values.length > 4) {
            if (mTruncatedRotationVector == null) {
                mTruncatedRotationVector = new float[4];
            }
            System.arraycopy(event.values, 0, mTruncatedRotationVector, 0, 4);
            return mTruncatedRotationVector;
        } else {
            return event.values;
        }
    }

    private void setTargetVector(float[] values) {
        SensorManager.getRotationMatrixFromVector(mTargetMatrix, values);
        mTargeted = true;
    }

    public void reset() {
        mTargeted = false;
    }

    public void setTiltSensitivity(float tiltSensitivity) {
        if (tiltSensitivity <= 0) {
            throw new IllegalArgumentException("Tilt sensitivity must be positive");
        }

        mTiltSensitivity = tiltSensitivity;
    }
}