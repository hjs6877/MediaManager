package com.soom.mediamanager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static String type = "TYPE";
    public static final int TYPE_IMAGE = 0;
    public static final int TYPE_VIDEO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button imageButton = (Button) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE
                }, 100);
                Toast.makeText(this, "안드로이드 6.0(마시멜로우)부터 일부 권한에 대해 사용자에게 동의가 필요합니다", Toast.LENGTH_LONG).show();
                imageButton.setEnabled(false);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case 100:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Button imageButton = (Button) findViewById(R.id.imageButton);
                    imageButton.setEnabled(true);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imageButton:
                openImageViewer();
                break;
            default:
                break;
        }
    }

    private void openImageViewer() {
        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivity(intent);
    }
}
