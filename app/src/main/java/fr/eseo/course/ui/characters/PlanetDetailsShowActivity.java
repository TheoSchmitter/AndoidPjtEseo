package fr.eseo.course.ui.characters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import fr.eseo.course.R;
import fr.eseo.course.data.models.SaveDataRequest;

/**
 * Created by amin on 17/01/2018.
 */

public class PlanetDetailsShowActivity extends AppCompatActivity {



    public static Intent getStartIntent(final Context context) {
        return new Intent(context, PlanetDetailsShowActivity.class);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planets_details);

        final TextView myPlanetName = findViewById(R.id.planetName);
        final TextView myPlanetDiameter = findViewById(R.id.planetDiameter);
        final TextView myPlanetRotationPeriod = findViewById(R.id.planetRotationPeriod);
        final TextView myPlanetOrbitalPeriod = findViewById(R.id.planetOrbitalPeriod);
        final TextView myPlanetGravity = findViewById(R.id.planetGravity);
        final TextView myPlanetPopulation = findViewById(R.id.planetPopulation);
        final TextView myPlanetClimate = findViewById(R.id.planetClimate);
        final TextView myPlanetTerrain = findViewById(R.id.planetTerrain);
        final TextView myPlanetSurfaceWater = findViewById(R.id.planetSurfaceWater);


        myPlanetName.setText(SaveDataRequest.getInstance().getSelectedPlanet().getName());
        myPlanetDiameter.setText(SaveDataRequest.getInstance().getSelectedPlanet().getDiameter());
        myPlanetRotationPeriod.setText(SaveDataRequest.getInstance().getSelectedPlanet().getRotation_period());
        myPlanetOrbitalPeriod.setText(SaveDataRequest.getInstance().getSelectedPlanet().getOrbital_period());
        myPlanetGravity.setText(SaveDataRequest.getInstance().getSelectedPlanet().getGravity());
        myPlanetPopulation.setText(SaveDataRequest.getInstance().getSelectedPlanet().getPopulation());
        myPlanetClimate.setText(SaveDataRequest.getInstance().getSelectedPlanet().getClimate());
        myPlanetTerrain.setText(SaveDataRequest.getInstance().getSelectedPlanet().getTerrain());
        myPlanetSurfaceWater.setText(SaveDataRequest.getInstance().getSelectedPlanet().getSurface_water());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
