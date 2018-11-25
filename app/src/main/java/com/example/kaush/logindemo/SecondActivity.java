package com.example.kaush.logindemo;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.ConnectionService;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";
    private static final int ERROR_DIALOG_REQUEST = 90001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);
        if(isServicesAvailable()){
            init();
        }

    }

    private void init(){
        Button btnMap = (Button)findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this,MapActivity.class );
                startActivity(intent);
            }
        });


    }
    public boolean isServicesAvailable(){
        Log.d(TAG,"isServiceOk : Checking google play services");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(SecondActivity.this);
        if(available == ConnectionResult.SUCCESS){
            Log.d(TAG,"isServiceOk : Google play services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){

            Log.d(TAG,"isServiceOk : An error occurred, we will fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(SecondActivity.this,available,ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else{
            Toast.makeText(this,"you can't make map requests",Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}
