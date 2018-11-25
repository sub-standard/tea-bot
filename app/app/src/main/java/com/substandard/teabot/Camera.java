package com.substandard.teabot;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;


public class Camera extends Fragment {
    CameraView camera;
    Button button;

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            buttonClick();
        }
    };

    private CameraListener cameraListener = new CameraListener() {
        @Override
        public void onPictureTaken(byte[] picture) {
            getFaceAnalysis(picture);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.camera, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        camera = getActivity().findViewById(R.id.camera);
        camera.setLifecycleOwner(getViewLifecycleOwner());
        camera.addCameraListener(cameraListener);


        button = getActivity().findViewById(R.id.btn_takepicture);
        button.setOnClickListener(buttonClickListener);
    }

    private void buttonClick() {
        camera.capturePicture();
    }

    private void getFaceAnalysis(byte[] picture) {
        FaceAnalysis.analyse(picture);
    }
}