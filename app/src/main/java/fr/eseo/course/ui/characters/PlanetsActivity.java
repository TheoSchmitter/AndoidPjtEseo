package fr.eseo.course.ui.characters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.eseo.course.R;
import fr.eseo.course.data.models.HttpError;
import fr.eseo.course.data.models.PagePlanet;
import fr.eseo.course.data.models.Planet;
import fr.eseo.course.data.models.SaveDataRequest;
import fr.eseo.course.data.remote.ApiService;
import fr.eseo.course.ui.adapters.PlanetsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amin on 17/01/2018.
 */

public class PlanetsActivity extends AppCompatActivity {

    private final ApiService apiService = ApiService.Builder.getInstance();
    public Planet[] planeteTabListToFill;
    private PlanetsAdapter planetAdapter;
    public PagePlanet myPage = new PagePlanet();
    public boolean isRequestDone = false;
    public ListView planetsListView;

    private ProgressBar myloader;
    public int j = 0;
    public int pageCounter=0;


    public static Intent getStartIntent(final Context context) {
        return new Intent(context, PlanetsActivity.class);
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planets);
        myloader = findViewById(R.id.loaderPlanets);

        setTitle("Planets Available");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        planetsListView = findViewById(R.id.imgPlanetsListView);



        planetAdapter = new PlanetsAdapter(this, SaveDataRequest.getInstance().getPlanetList(), planetSelectedListener);
        planetsListView.setAdapter(planetAdapter);
        //planetsListView.setVisibility(View.GONE);
        j = 0;

        planeteTabListToFill = new Planet[250];

        //myResp.setCharactersTab(new Character[10]);
        if(SaveDataRequest.getInstance().getPlanetList().isEmpty())
        {
            ask4PlanetsOnWeb();
            planetsListView.setVisibility(View.GONE);
            myloader.setVisibility(View.VISIBLE);

        }else
        {
            planetsListView.setVisibility(View.VISIBLE);
            myloader.setVisibility(View.GONE);
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        // checkAndUpdateCurrentLedStatusName();
    }

    private final void ask4PlanetsOnWeb() {

        isRequestDone = false;
        //apiService.readCharactersResult(myResp.getCharactersTab()).enqueue(new Callback<CharactersList>() {
        apiService.readPlanetsResult(myPage).enqueue(new Callback<PagePlanet>() {
            @Override
            public void onResponse(final Call<PagePlanet> call, final Response<PagePlanet> response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        handleResponse(response);
                        planetsListView.setVisibility(View.VISIBLE);
                        //refreshButton.setEnabled(true);
                    }
                });
            }

            @Override
            public void onFailure(final Call<PagePlanet> call, final Throwable t) {
                t.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        isRequestDone = true;
                        planetsListView.setVisibility(View.VISIBLE);
                        //refreshButton.setEnabled(true);
                        Toast.makeText(PlanetsActivity.this, R.string.status_error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    /**
     * Triggered when a device is selected
     */
    private final PlanetsAdapter.OnPlanetSelectedListener planetSelectedListener = new PlanetsAdapter.OnPlanetSelectedListener() {
        @Override
        public void handle(Planet planet) {

            Planet planetToDisplay ;

            planetToDisplay = getPlanetToSend(planet.getName());

            if(planetToDisplay!=null)
            {
                SaveDataRequest.getInstance().setSelectedPlanet(planetToDisplay);
                Intent intent = PlanetDetailsShowActivity.getStartIntent(PlanetsActivity.this);
                startActivity(intent);
            }
        }
    };

    private Planet getPlanetToSend(String name) {
        for (Planet liste :  SaveDataRequest.getInstance().getPlanetList()) {
            if (liste.getName().contains(name))
            {
                return new Planet(liste.getName(), liste.getDiameter(), liste.getRotation_period(),liste.getOrbital_period(), liste.getGravity(), liste.getPopulation(), liste.getClimate(), liste.getTerrain(), liste.getSurface_water());
            }
        }
        return null;
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void ask4NextPage(int i) {
        apiService.readNextPagePlanetsByIndex(i).enqueue(new Callback<PagePlanet>() {
            @Override
            public void onResponse(final Call<PagePlanet> call, final Response<PagePlanet> response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        handleResponse(response);
                        //refreshButton.setEnabled(true);
                    }
                });
            }

            @Override
            public void onFailure(final Call<PagePlanet> call, final Throwable t) {
                t.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        isRequestDone = true;
                        planetsListView.setVisibility(View.VISIBLE);
                        //refreshButton.setEnabled(true);
                        Toast.makeText(PlanetsActivity.this, R.string.status_error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    /**
     * Handle default response for both GET/POST methods
     */
    private void handleResponse(final Response<PagePlanet> response) {
        if (response.isSuccessful()) {

            //planetTabList.add(response.body().getPlanetsTab());
            pageCounter++;
            for(int i=0 ; i <response.body().getPlanetsTab().length;i++)
            {
                SaveDataRequest.getInstance().getPlanetList().add(response.body().getPlanetsTab()[i]);
                planetAdapter.notifyDataSetChanged();
            }

            if (response.body().getNext() != null) {
                ask4NextPage(pageCounter);
            } else {

                //Toast.makeText(CharactersActivity.this,"voici i : "+j, Toast.LENGTH_SHORT).show();
                myloader.setVisibility(View.GONE);
                planetsListView.setVisibility(View.VISIBLE);
            }

            // myResp.setCharactersTab(respon);
            // setCharList(response.body());
           /* myPage.setNext(response.body().getNext());
            myPage.setCharactersTab(response.body().getCharactersTab());
            myPage.setPrevious(response.body().getPrevious());
            myPage.setCount(response.body().getCount());
            isRequestDone=true;*/

        } else { // error HTTP
            isRequestDone = true;
            Toast.makeText(PlanetsActivity.this, "mauvaise ", Toast.LENGTH_SHORT).show();
            // characters.setVisibility(View.VISIBLE);

            try {

                final HttpError error = new Gson().fromJson(response.errorBody().string(), HttpError.class);
                // Toast.makeText(CharactersActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(PlanetsActivity.this, "mauvaise reception des chararc", Toast.LENGTH_SHORT).show();
            } catch (final IOException e) {
                e.printStackTrace();
                Toast.makeText(PlanetsActivity.this, "Une Erreur sur l'erreur", Toast.LENGTH_SHORT).show();
                //Toast.makeText(CharactersActivity.this, R.string.unknown_error, Toast.LENGTH_SHORT).show();
            }
        }
    }
}