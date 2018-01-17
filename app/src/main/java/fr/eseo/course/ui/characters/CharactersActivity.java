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
import fr.eseo.course.data.models.Character;
import fr.eseo.course.data.models.CharactersList;
import fr.eseo.course.data.models.HttpError;
import fr.eseo.course.data.models.ImageCharactersModel;
import fr.eseo.course.data.models.PageCharacter;
import fr.eseo.course.data.models.SaveDataRequest;
import fr.eseo.course.data.remote.ApiService;
import fr.eseo.course.ui.adapters.CharacterAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Devices activity - Display a list of devices in range
 */
public class CharactersActivity extends AppCompatActivity {

    private final ApiService apiService = ApiService.Builder.getInstance();

    private final List<ImageCharactersModel> listOfCharacter = new ArrayList<>();
    public List<Character []> charTabList = new ArrayList<>();
    public Character [] charTabListToFill;
    private CharacterAdapter devicesAdapter;
    public PageCharacter myPage = new PageCharacter();
    public boolean isRequestDone = false;
    public ListView characters;
    private ProgressBar myloader;
    public int j =0;


    public static Intent getStartIntent(final Context context) {
        return new Intent(context, CharactersActivity.class);
    }




    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        myloader = findViewById(R.id.loaderCharacter);

        setTitle("Characters Available");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        characters = findViewById(R.id.imgCharactersListView);

        listOfCharacter.add(new ImageCharactersModel(R.drawable.chewbacca,"Chewbacca"));
        listOfCharacter.add(new ImageCharactersModel(R.drawable.dark_vador,"Darth Vader"));
        listOfCharacter.add(new ImageCharactersModel(R.drawable.master_yoda, "Yoda"));
        listOfCharacter.add(new ImageCharactersModel(R.drawable.jabba_the_hutt,"Jabba Desilijic Tiure"));
        listOfCharacter.add(new ImageCharactersModel(R.drawable.han_solo,"Han Solo"));
        listOfCharacter.add(new ImageCharactersModel(R.drawable.jango_fett, "Jango Fett"));
        listOfCharacter.add(new ImageCharactersModel(R.drawable.leia_organa, "Leia Organa"));
        listOfCharacter.add(new ImageCharactersModel(R.drawable.luke_skywalker,"Luke Skywalker"));
        listOfCharacter.add(new ImageCharactersModel(R.drawable.beru_whitesun_lars, "Beru Whitesun lars"));
        listOfCharacter.add(new ImageCharactersModel(R.drawable.owen_lars,"Owen Lars"));
        listOfCharacter.add(new ImageCharactersModel(R.drawable.obi_wan_kenobi,"Obi-Wan Kenobi"));
        listOfCharacter.add(new ImageCharactersModel(R.drawable.biggs_darklighter,"Biggs Darklighter"));
        listOfCharacter.add(new ImageCharactersModel(R.drawable.r5d4, "R5-D4"));
        listOfCharacter.add(new ImageCharactersModel(R.drawable.r2_d2, "R2-D2"));


        devicesAdapter = new CharacterAdapter(this, listOfCharacter, deviceSelectedListener);
        characters.setAdapter(devicesAdapter);

        j=0;
        charTabListToFill = new Character[250];
        if(SaveDataRequest.getInstance().getCharactersList().isEmpty())
        {
            ask4CharacterOnWeb();
            myloader.setVisibility(View.VISIBLE);
            characters.setVisibility(View.GONE);
        }else
        {
            myloader.setVisibility(View.GONE);
            characters.setVisibility(View.VISIBLE);
        }


    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private final void ask4CharacterOnWeb()
    {

        isRequestDone=false;
        apiService.readCharactersResult(myPage).enqueue(new Callback<PageCharacter>() {
            @Override
            public void onResponse(final Call<PageCharacter> call, final Response<PageCharacter> response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        handleResponse(response);
                    }
                });
            }

            @Override
            public void onFailure(final Call<PageCharacter> call, final Throwable t) {
                t.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        isRequestDone=true;
                        Toast.makeText(CharactersActivity.this, R.string.status_error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    /**
     * Triggered when a device is selected
     */
    private final CharacterAdapter.OnCharacterSelectedListener deviceSelectedListener = new CharacterAdapter.OnCharacterSelectedListener() {
        @Override
        public void handle(ImageCharactersModel character) {

                Character characterToDisplay = getCharacterTosend(character.getName());

                if (characterToDisplay != null) {
                    Intent intent;
                    intent = CharacterDetailsShowActivity.getStartIntent(CharactersActivity.this);

                    intent.putExtra("ImgName", character.getName());
                    intent.putExtra("ImgSrc", character.getSrc());

                    SaveDataRequest.getInstance().setSelectedCharacter(characterToDisplay);

                    startActivity(intent);
                } else {
                    Toast.makeText(CharactersActivity.this, character.getName() + "\n Not Available on https://swapi.co", Toast.LENGTH_LONG).show();
                }
        }
    };

    private Character getCharacterTosend(String name)
    {
        for(Character listeChar : SaveDataRequest.getInstance().getCharactersList())
        {
            if(listeChar.getName().contains(name))
            {
                return new Character(listeChar.getName(), listeChar.getHeight(),listeChar.getMass(),listeChar.getHaircolor() , listeChar.getGender());
            }
        }
        return null;
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void ask4NextPage(int i)
    {
        apiService.readNextPageCharacterByIndex(i).enqueue(new Callback<PageCharacter>() {
            @Override
            public void onResponse(final Call<PageCharacter> call, final Response<PageCharacter> response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        handleResponse(response);
                    }
                });
            }

            @Override
            public void onFailure(final Call<PageCharacter> call, final Throwable t) {
                t.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        isRequestDone=true;
                        characters.setVisibility(View.VISIBLE);
                        //refreshButton.setEnabled(true);
                        Toast.makeText(CharactersActivity.this, R.string.status_error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    /**
     * Handle default response for both GET/POST methods
     */
    private void handleResponse(final Response<PageCharacter> response) {
        if (response.isSuccessful()) {
            charTabList.add(response.body().getCharactersTab());

            if(response.body().getNext()!=null)
            {
                for(int i = 0 ; i<response.body().getCharactersTab().length;i++)
                {
                    SaveDataRequest.getInstance().getCharactersList().add(response.body().getCharactersTab()[i]);
                }
                ask4NextPage(charTabList.size());
            }else{
                myloader.setVisibility(View.GONE);
                characters.setVisibility(View.VISIBLE);
            }
        } else { // error HTTP
            isRequestDone=true;
            Toast.makeText(CharactersActivity.this,"mauvaise ", Toast.LENGTH_SHORT).show();
            try {

                final HttpError error = new Gson().fromJson(response.errorBody().string(), HttpError.class);
                Toast.makeText(CharactersActivity.this,"mauvaise reception des chararc", Toast.LENGTH_SHORT).show();
            } catch (final IOException e) {
                e.printStackTrace();
                Toast.makeText(CharactersActivity.this,"Une Erreur sur l'erreur", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
