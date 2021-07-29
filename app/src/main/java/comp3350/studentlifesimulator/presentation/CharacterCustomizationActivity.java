package comp3350.studentlifesimulator.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.studentlifesimulator.R;

import comp3350.studentlifesimulator.application.Main;
import comp3350.studentlifesimulator.business.DatabaseManager;

public class CharacterCustomizationActivity extends CharacterActivity {
    boolean fromNewGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_customization);
        fromNewGame = getIntent().getBooleanExtra("fromNewGame", false);
        loadCharacter();
    }

    public void setEyes(View view) {
        ImageView eyesImage = findViewById(R.id.eyesImage);
        selectedEyes = view.getTag().toString();
        eyesImage.setImageDrawable(((ImageView) view).getDrawable());
    }

    public void setHair(View view) {
        ImageView hairImage = findViewById(R.id.hairImage);
        selectedHair = view.getTag().toString();
        hairImage.setImageDrawable(((ImageView) view).getDrawable());
    }

    public void setSkinColour(View view) {
        ImageView skinColourImage = findViewById(R.id.skinColourImage);
        selectedSkinColour = view.getTag().toString();
        skinColourImage.setImageDrawable(((ImageView) view).getDrawable());
    }

    public void setShirt(View view) {
        ImageView shirtImage = findViewById(R.id.shirtImage);
        selectedShirt = view.getTag().toString();
        shirtImage.setImageDrawable(((ImageView) view).getDrawable());
    }

    public void onSaveClick(View view) {
        Intent nextIntent;

        saveCharacter();

        if (fromNewGame) {
            nextIntent = new Intent(this, CoursesActivity.class);
            nextIntent.addFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME);
            startActivity(nextIntent);
        }

        finish();
    }
}