package com.example.qr_code_zxing3;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private TextView QRResult;
    ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btn_scan, btn_stop_scan;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_scan=(Button)findViewById(R.id.btn_scan);
        btn_stop_scan=(Button)findViewById(R.id.btn_stop_scan);
        QRResult=(TextView)findViewById(R.id.txv_result);
        scannerView=findViewById(R.id.scannerView);

        requestPermissions(new String[]{Manifest.permission.CAMERA},1);

        btn_scan.setOnClickListener(view->{

            if(checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.CAMERA},1);
            }
            else {
                QRResult.setText("開始掃描");
                scannerView.startCamera();
                scannerView.setResultHandler(this);
            }
        });
        btn_stop_scan.setOnClickListener(view->{
            QRResult.setText("已停止");
            scannerView.stopCamera();
        });
    }

    @Override
    public void handleResult(Result result) {
        Log.d("Result",""+result.getText());
        QRResult.setText(result.getText());
        scannerView.stopCamera();
    }
}