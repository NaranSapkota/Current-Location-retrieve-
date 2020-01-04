package com.real.currentlocation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_LOCATION=1;
    Button btn ;
    TextView tv;
    LocationManager locationManager;
    String latitude, longitutde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add permission
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);

        btn =findViewById(R.id.btn);
        tv=findViewById(R.id.tv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
//                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0,MainActivity.this);

                //check gps is enable or not

                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {


                    //Write Function To enable gps

                    OnGps();

                }else {
                    //GPS is already enable
                    getLocation();
                    System.out.println("3333333333333333333333333333");



                }

            }
        });

    }

    private void getLocation() {

        if(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {

            System.out.println("444444444444444444");


            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);

        }else{

            Location locationGPS=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location locationNetwork=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location locationPassive=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);



            System.out.println("555555555555555555555");


            if(locationGPS !=null){
               double lat=locationGPS.getLatitude();
               double lon =locationGPS.getLongitude();
               latitude=String.valueOf(lat);
               longitutde=String.valueOf(lon);
               tv.setText("Your Location:"+"\n"+"Latitude "+ latitude +"\n"+"Longitutde "+ longitutde);

                System.out.println("6666666666666666666666666666");


            }else if(locationNetwork !=null){
                double lat=locationNetwork.getLatitude();
                double lon =locationNetwork.getLongitude();
                latitude=String.valueOf(lat);
                longitutde=String.valueOf(lon);
                tv.setText("Your Location:"+"\n"+"Latitude "+ latitude +"\n"+"Longitutde "+ longitutde);

                System.out.println("77777777777777777");


            }else if(locationPassive !=null){
                double lat=locationPassive.getLatitude();
                double lon =locationPassive.getLongitude();
                latitude=String.valueOf(lat);
                longitutde=String.valueOf(lon);
                tv.setText("Your Location:"+"\n"+"Latitude "+ latitude +"\n"+"Longitutde "+ longitutde);

                System.out.println("8888888888888888888888888888");


            }else{

                Toast.makeText(getApplicationContext(),"Cannot get location",Toast.LENGTH_SHORT).show();
                System.out.println("9999999999999999999 ");

            }


        }

    }

    private void OnGps(){

    final AlertDialog.Builder builder=new AlertDialog.Builder(this);
    builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }
    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
           dialogInterface.cancel();
        }
    });

    final AlertDialog alertDialog = builder.create();

    alertDialog.show();
    }
}
