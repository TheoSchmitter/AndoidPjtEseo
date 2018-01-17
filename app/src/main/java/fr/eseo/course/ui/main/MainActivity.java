package fr.eseo.course.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import fr.eseo.course.R;
import fr.eseo.course.data.remote.ApiService;
import fr.eseo.course.ui.characters.CharactersActivity;
import fr.eseo.course.ui.characters.PlanetsActivity;

/**
 * Configuration activity
 */
public class MainActivity extends AppCompatActivity {

    private Button characterButton;
    private Button planetButton;
    private Button vehiclesButton;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_starwars);

        characterButton = findViewById(R.id.characters_button);
        planetButton = findViewById(R.id.planet_button);
        vehiclesButton = findViewById(R.id.vehicles_button);

        characterButton.setOnClickListener(onCharactersButtonClicked);
        planetButton.setOnClickListener(onPlanetsButtonClicked);
        vehiclesButton.setOnClickListener(onTrapButtonClicked);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Go to characters activity
     */
    private final View.OnClickListener onCharactersButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            startActivity(CharactersActivity.getStartIntent(MainActivity.this)); // start config activity
        }
    };


    private final View.OnClickListener onPlanetsButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            startActivity(PlanetsActivity.getStartIntent(MainActivity.this)); // start config activity
        }
    };

    private final View.OnClickListener onTrapButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            Toast.makeText(MainActivity.this, "NOT IMPLEMENTED", Toast.LENGTH_SHORT).show();
            RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                    0.5f);
            rotate.setDuration(2000);
            rotate.setRepeatCount(0);
            vehiclesButton.setAnimation(rotate);
            vehiclesButton.startAnimation(rotate);
        }
    };



}
