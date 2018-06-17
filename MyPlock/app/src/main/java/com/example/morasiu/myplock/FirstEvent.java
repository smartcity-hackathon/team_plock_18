package com.example.morasiu.myplock;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapFragment;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.search.ErrorCode;
import com.here.android.mpa.search.GeocodeRequest;
import com.here.android.mpa.search.Location;
import com.here.android.mpa.search.ResultListener;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class FirstEvent extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = "MAP";

    private Map map;

    private MapFragment mapFragment;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        assert b != null;
        id = b.getInt("id");
        setContentView(id);

        findViewById(R.id.navigationBtn).setOnClickListener(this);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.parseColor(getString(R.string.main_color))));
        initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_details_question:
                Intent intent = new Intent(this, SendQuestion.class);
                startActivity(intent);
                return true;
            case R.id.menu_details_add_schedule:
                Toast.makeText(this, "Dodano do harmonogramu", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_details_report:
                Toast.makeText(this, "Zgłoszono wydarzenie", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_details_add_google_calendar:
                Toast.makeText(this, "Dodano do Kalendarza Google", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initialize() {
        // Search for the map fragment to finish setup by calling init().
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapfragment);
        mapFragment.init(new OnEngineInitListener() {
            @Override
            public void onEngineInitializationCompleted(OnEngineInitListener.Error error) {
                if (error == OnEngineInitListener.Error.NONE) {
                    // retrieve a reference of the map from the map fragment
                    map = mapFragment.getMap();
                    map.setMapScheme(map.getMapSchemes().get(2));

                    GeoCoordinate geoCoordinate = null;
                    // Create a custom marker image
                   Image myImage = new Image();

                    try {
                        switch (id){
                            case R.layout.activity_first_event:
                                myImage.setImageResource(R.drawable.buisness64);
                                geoCoordinate = new GeoCoordinate(52.5440773, 19.6885648);
                                break;
                            case R.layout.activity_second_event:
                                myImage.setImageResource(R.drawable.tennis_ball64);
                                geoCoordinate = new GeoCoordinate(52.5447418,19.6918254);
                                break;
                            case R.layout.activity_third_event:
                                myImage.setImageResource(R.drawable.social64);
                                geoCoordinate = new GeoCoordinate(52.5504536,19.6715772);
                                break;
                            case R.layout.activity_fourth_event:
                                myImage.setImageResource(R.drawable.culture64);
                                geoCoordinate = new GeoCoordinate(52.5487067,19.6901366);
                                break;
                            case R.layout.activity_fifth_event:
                                myImage.setImageResource(R.drawable.eventicon64);
                                geoCoordinate = new GeoCoordinate(52.5487063,19.6835705);
                                break;
                            case R.layout.activity_sixth_event:
                                myImage.setImageResource(R.drawable.culture64);
                                geoCoordinate = new GeoCoordinate(52.5356524,19.7545656);
                                break;
                            case R.layout.activity_seventh_event:
                                myImage.setImageResource(R.drawable.buisness64);
                                geoCoordinate = new GeoCoordinate(52.534783,19.7554403);
                                break;

                        }
                    } catch (IOException e) {
                        finish();
                    }

                    map.setCenter(geoCoordinate,
                            Map.Animation.NONE);
                    // Set the zoom level to the average between min and max
                    map.setZoomLevel(16);

                    // Create the MapMarker
                    MapMarker myMapMarker =
                            new MapMarker(geoCoordinate, myImage);

                    map.addMapObject(myMapMarker);
                } else {
                    Log.e(LOG_TAG, "Cannot initialize MapFragment (" + error + ")");
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.navigationBtn)
            Toast.makeText(this, "Przed wyruszeniem w podróż należy zebrać drużynę, wędrowcze", Toast.LENGTH_SHORT).show();
    }
}
