package com.example.morasiu.myplock;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.ViewObject;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapFragment;
import com.here.android.mpa.mapping.MapGesture;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    // permissions request code
    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;

    /**
     * Permissions that need to be explicitly requested from end user.
     */
    private static final String[] REQUIRED_SDK_PERMISSIONS = new String[] {
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE };

    // map embedded in the map fragment
    private Map map = null;

    // map fragment embedded in this activity
    private MapFragment mapFragment = null;

    private LinearLayout mapContainer;

    private ScrollView eventList;

    private LinearLayout otherView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_list:
                    eventList.setVisibility(View.VISIBLE);
                    mapContainer.setVisibility(View.GONE);
                    otherView.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_map:
                    otherView.setVisibility(View.GONE);
                    eventList.setVisibility(View.GONE);
                    initialize();
                    mapContainer.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_other:
                    eventList.setVisibility(View.GONE);
                    mapContainer.setVisibility(View.GONE);
                    otherView.setVisibility(View.VISIBLE);

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermissions();
        mapContainer = findViewById(R.id.mapcontainer);
        eventList = findViewById(R.id.eventlist);
        otherView = findViewById(R.id.otherview);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.parseColor(getString(R.string.main_color))));

        Button aboutBtn = findViewById(R.id.about_btn);
        aboutBtn.setOnClickListener(this);

        Button reportBtn = findViewById(R.id.report_btn);
        reportBtn.setOnClickListener(this);

        Button informatorBtn = findViewById(R.id.informator_btn);
        informatorBtn.setOnClickListener(this);

        Button addEventBtn = findViewById(R.id.addEvent);
        addEventBtn.setOnClickListener(this);

        findViewById(R.id.atractionsBtn).setOnClickListener(this);
        findViewById(R.id.userAccountBtn).setOnClickListener(this);

        RelativeLayout layout1 = findViewById(R.id.firstLayout);
        layout1.setOnClickListener(this);

        findViewById(R.id.secondLayout).setOnClickListener(this);
        findViewById(R.id.thirdLayout).setOnClickListener(this);
        findViewById(R.id.fourthLayout).setOnClickListener(this);
        findViewById(R.id.fiveLayout).setOnClickListener(this);
        findViewById(R.id.sixLayout).setOnClickListener(this);
        findViewById(R.id.sevenLayout).setOnClickListener(this);
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
                    map.setCenter(LocalizationPlock.get,
                            Map.Animation.NONE);
                    // Set the zoom level to the average between min and max
                    map.setZoomLevel(12);

                    // Create a custom marker image
                    Image imageBusiness = new Image();
                    Image imageSport = new Image();
                    Image imageSocial = new Image();
                    Image imageCulture = new Image();
                    Image imageCalendar = new Image();

                    try {
                        imageBusiness.setImageResource(R.drawable.buisness64);
                        imageSport.setImageResource(R.drawable.tennis_ball64);
                        imageSocial.setImageResource(R.drawable.social64);
                        imageCulture.setImageResource(R.drawable.culture64);
                        imageCalendar.setImageResource(R.drawable.eventicon64);

                    } catch (IOException e) {
                        finish();
                    }

                    // Create the MapMarker
                    GeoCoordinate geoCoordinateFirst = new GeoCoordinate(52.5440773, 19.6885648);
                    GeoCoordinate geoCoordinateSecond = new GeoCoordinate(52.544912,19.685143);
                    GeoCoordinate geoCoordinateThird = new GeoCoordinate(52.5504536,19.6715772);
                    GeoCoordinate geoCoordinateForth = new GeoCoordinate(52.5487067,19.6901366);
                    GeoCoordinate geoCoordinateFifth = new GeoCoordinate(52.5487063,19.6835705);
                    GeoCoordinate geoCoordinateSixth = new GeoCoordinate(52.5356524,19.7545656);
                    GeoCoordinate geoCoordinateSeventh = new GeoCoordinate(52.534783,19.7554403);

                    List<MapObject> mapMarkers = new ArrayList<>();

                    //Tumska
                    mapMarkers.add(new MapMarker(geoCoordinateFirst, imageBusiness).setTitle("Otwarcie siłowni TooStrong"));
                    mapMarkers.add(new MapMarker(geoCoordinateSecond, imageSport).setTitle("Przejazd na rolkach po Płocku"));
                    mapMarkers.add(new MapMarker(geoCoordinateThird, imageSocial).setTitle("Hackathon City Coders 2018"));
                    mapMarkers.add(new MapMarker(geoCoordinateForth, imageCulture).setTitle("Dzień Dziecka w teatrze"));
                    mapMarkers.add(new MapMarker(geoCoordinateFifth, imageCalendar).setTitle("Koncert społeczny dla dzieci"));
                    mapMarkers.add(new MapMarker(geoCoordinateSixth, imageCulture).setTitle("Cyrk w Płocku"));
                    mapMarkers.add(new MapMarker(geoCoordinateSeventh, imageBusiness).setTitle("Otwarcie centrum handlowego"));
                    // ???
                    mapMarkers.add(new MapMarker(new GeoCoordinate(52.5587063,19.6835705), imageSport));
                    mapMarkers.add(new MapMarker(new GeoCoordinate(52.5447418,19.6918254), imageSport));
                    mapMarkers.add(new MapMarker(new GeoCoordinate(52.5487063,19.6935705), imageSport));
                    mapMarkers.add(new MapMarker(new GeoCoordinate(52.5417063,19.6935705), imageCalendar));
                    mapMarkers.add(new MapMarker(new GeoCoordinate(52.5357063,19.7035705), imageSport));
                    mapMarkers.add(new MapMarker(new GeoCoordinate(52.5257063,19.7135705), imageBusiness));
                    mapMarkers.add(new MapMarker(new GeoCoordinate(52.5127063,19.7235705), imageSocial));
                    mapMarkers.add(new MapMarker(new GeoCoordinate(52.5197063,19.7435705), imageSport));
                    mapMarkers.add(new MapMarker(new GeoCoordinate(52.5157063,19.7335705), imageBusiness));
                    mapMarkers.add(new MapMarker(new GeoCoordinate(52.549099,19.7116393), imageBusiness));
                    mapMarkers.add(new MapMarker(new GeoCoordinate(52.524903,19.6729573), imageSocial));
                    mapMarkers.add(new MapMarker(new GeoCoordinate(52.539972,19.7247563), imageCalendar));

                    map.addMapObjects(mapMarkers);

                    // Create a gesture listener and add it to the MapFragment
                    MapGesture.OnGestureListener listener =
                            new MapGesture.OnGestureListener.OnGestureListenerAdapter() {
                                Boolean clicked = false;
                                MapMarker activeMapMarker;
                                @Override
                                public boolean onMapObjectsSelected(List<ViewObject> objects) {
                                    if (activeMapMarker != null) activeMapMarker.hideInfoBubble();
                                    for (ViewObject viewObj : objects) {
                                        if (viewObj.getBaseType() == ViewObject.Type.USER_OBJECT) {
                                            if (((MapObject)viewObj).getType() == MapObject.Type.MARKER) {
                                                MapMarker mapMarker = (MapMarker) viewObj;

                                                if (!clicked) {
                                                    mapMarker.showInfoBubble();
                                                    activeMapMarker = mapMarker;
                                                    clicked = true;
                                                } else {
                                                    clicked = false;
                                                    activeMapMarker = null;
                                                    String title = mapMarker.getTitle();

                                                    if (title == null) return true;

                                                    if (title.contains("TooStrong")) {
                                                        Intent intent = new Intent(MainActivity.this, FirstEvent.class);
                                                        intent.putExtra("id", R.layout.activity_first_event);
                                                        startActivity(intent);
                                                    } else if (title.contains("na rolkach")) {
                                                        Intent intent = new Intent(MainActivity.this, FirstEvent.class);
                                                        intent.putExtra("id", R.layout.activity_second_event);
                                                        startActivity(intent);
                                                    } else if (title.contains("Hackathon")) {
                                                        Intent intent = new Intent(MainActivity.this, FirstEvent.class);
                                                        intent.putExtra("id", R.layout.activity_third_event);
                                                        startActivity(intent);
                                                    } else if (title.contains("Dzień dziecka")) {
                                                        Intent intent = new Intent(MainActivity.this, FirstEvent.class);
                                                        intent.putExtra("id", R.layout.activity_fourth_event);
                                                        startActivity(intent);
                                                    } else if (title.contains("Koncert społeczny")) {
                                                        Intent intent = new Intent(MainActivity.this, FirstEvent.class);
                                                        intent.putExtra("id", R.layout.activity_fifth_event);
                                                        startActivity(intent);
                                                    } else if (title.contains("Cyrk")) {
                                                        Intent intent = new Intent(MainActivity.this, FirstEvent.class);
                                                        intent.putExtra("id", R.layout.activity_sixth_event);
                                                        startActivity(intent);
                                                    } else if (title.contains("Otwarcie")) {
                                                        Intent intent = new Intent(MainActivity.this, FirstEvent.class);
                                                        intent.putExtra("id", R.layout.activity_seventh_event);
                                                        startActivity(intent);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    // return false to allow the map to handle this callback also
                                    return false;
                                }
                            };
                    mapFragment.getMapGesture().addOnGestureListener(listener);
                } else {
                    Log.e(LOG_TAG, "Cannot initialize MapFragment (" + error + ")");
                }
            }
        });
    }

    /**
     * Checks the dynamically controlled permissions and requests missing permissions from end user.
     */
    protected void checkPermissions() {
        final List<String> missingPermissions = new ArrayList<>();
        // check all required dynamic permissions
        for (final String permission : REQUIRED_SDK_PERMISSIONS) {
            final int result = ContextCompat.checkSelfPermission(this, permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(permission);
            }
        }
        if (!missingPermissions.isEmpty()) {
            // request all missing permissions
            final String[] permissions = missingPermissions
                    .toArray(new String[missingPermissions.size()]);
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_ASK_PERMISSIONS);
        } else {
            final int[] grantResults = new int[REQUIRED_SDK_PERMISSIONS.length];
            Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED);
            onRequestPermissionsResult(REQUEST_CODE_ASK_PERMISSIONS, REQUIRED_SDK_PERMISSIONS,
                    grantResults);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                for (int index = permissions.length - 1; index >= 0; --index) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                        // exit the app if one permission is not granted
                        Toast.makeText(this, "Required permission '" + permissions[index]
                                + "' not granted, exiting", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                }
                // all permissions were granted
                break;
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();

        switch (view.getId()) {
            // Buttons
            case R.id.report_btn:
                intent = new Intent(this, Report.class);
                break;
            case R.id.about_btn:
                intent = new Intent(this, About.class);
                break;
            case R.id.informator_btn:
                intent = new Intent(this, Informator.class);
                break;
            case R.id.addEvent:
                intent =  new Intent(this, AddEvent.class);
                break;
            case R.id.atractionsBtn:
                intent = new Intent(this, ListAntiques.class);
                break;
            case R.id.userAccountBtn:
                intent = new Intent(this, MyAccount.class);
                break;
            // Layout
            case R.id.firstLayout:
                intent = new Intent(this, FirstEvent.class);
                intent.putExtra("id", R.layout.activity_first_event);
                break;
            case R.id.secondLayout:
                intent = new Intent(this, FirstEvent.class);
                intent.putExtra("id", R.layout.activity_second_event);
                break;
            case R.id.thirdLayout:
                intent = new Intent(this, FirstEvent.class);
                intent.putExtra("id", R.layout.activity_third_event);
                break;
            case R.id.fourthLayout:
                intent = new Intent(this, FirstEvent.class);
                intent.putExtra("id", R.layout.activity_fourth_event);
                break;
            case R.id.fiveLayout:
                intent = new Intent(this, FirstEvent.class);
                intent.putExtra("id", R.layout.activity_fifth_event);
                break;
            case R.id.sixLayout:
                intent = new Intent(this, FirstEvent.class);
                intent.putExtra("id", R.layout.activity_sixth_event);
                break;
            case R.id.sevenLayout:
                intent = new Intent(this, FirstEvent.class);
                intent.putExtra("id", R.layout.activity_seventh_event);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
