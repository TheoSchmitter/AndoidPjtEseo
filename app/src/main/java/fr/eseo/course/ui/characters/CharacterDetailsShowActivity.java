package fr.eseo.course.ui.characters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.TextView;

import fr.eseo.course.R;
import fr.eseo.course.data.models.SaveDataRequest;

/**
 * Created by amin on 11/01/2018.
 */

public class CharacterDetailsShowActivity extends AppCompatActivity {


    public static Intent getStartIntent(final Context context) {
        return new Intent(context, CharacterDetailsShowActivity.class);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters_details);
        final ImageButton myImgButton = findViewById(R.id.characterImageButton);
        final TextView myCharNameTextView = findViewById(R.id.charName);
        final TextView myCharGenderTextView = findViewById(R.id.charGender);
        final TextView myCharHairColorTextView = findViewById(R.id.charHairColor);
        final TextView myCharHeightTextView = findViewById(R.id.charHeight);
        final TextView myCharMassTextView = findViewById(R.id.charMass);

        final Intent intent = getIntent();

        int src = intent.getIntExtra("ImgSrc",-1);
        String name = intent.getStringExtra("characterName");
        setTitle(name+" Details");

        myCharNameTextView.setText(SaveDataRequest.getInstance().getSelectedCharacter().getName());
        myCharGenderTextView.setText(SaveDataRequest.getInstance().getSelectedCharacter().getGender());
        myCharHairColorTextView.setText(SaveDataRequest.getInstance().getSelectedCharacter().getHaircolor());
        myCharHeightTextView.setText(SaveDataRequest.getInstance().getSelectedCharacter().getHeight());
        myCharMassTextView.setText(SaveDataRequest.getInstance().getSelectedCharacter().getMass());

        myImgButton.setImageResource(src);

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
