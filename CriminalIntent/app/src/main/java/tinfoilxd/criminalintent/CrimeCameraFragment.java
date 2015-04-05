package tinfoilxd.criminalintent;


import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.IOException;

/**
 * Created by TinfoilxD on 4/4/15.
 */
public class CrimeCameraFragment extends Fragment
{
    private static final String TAG = "CrimeCameraFragment";

    private Camera mCamera;
    private SurfaceView mSurfaceView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    @SuppressWarnings("depreciation")
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_crime_camera,container,false);
        Button takePictureButton = (Button)v.findViewById(R.id.crime_camera_takePictureButton);
        takePictureButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getActivity().finish();
            }
        });
        mSurfaceView = (SurfaceView)v.findViewById(R.id.crime_camera_surfaceView);
        SurfaceHolder holder = mSurfaceView.getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


        //callback method
        holder.addCallback(new SurfaceHolder.Callback()
        {
            @Override
            public void surfaceCreated(SurfaceHolder holder)
            {
                try
                {
                    if(mCamera != null)
                        mCamera.setPreviewDisplay(holder);
                }
                catch(IOException e)
                {
                    Log.e(TAG, "Error setting up preview display in surfaceCreated()", e);
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
            {
                if(mCamera == null) return;


                Camera.Parameters parameters = mCamera.getParameters();
                Camera.Size s = null;
                //resume here later to determine proper Size s
                parameters.setPreviewSize(s.width, s.height);
                mCamera.setParameters(parameters);

                try
                {
                    mCamera.startPreview();
                }
                catch(Exception e)
                {
                    Log.e(TAG,"Could not start preview", e);
                    mCamera.release();
                    mCamera = null;
                }
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder)
            {
                if(mCamera != null)
                    mCamera.stopPreview();
            }
        });
        return v;
    }

    @Override
    @SuppressWarnings("depreciation")
    public void onResume()
    {
        super.onResume();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD)
            mCamera = Camera.open(0);
        else
            mCamera = Camera.open();

    }

    @Override
    public void onPause()
    {
        super.onPause();
        if(mCamera != null)
            mCamera.release();
        mCamera = null;
    }
}
