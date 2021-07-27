package comp3350.studentlifesimulator.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.studentlifesimulator.R;

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
        System.out.println(view.getTag().toString());

        ImageView eyesImage = findViewById(R.id.eyesImage);
//        eyesImage.setImageDrawable(((ImageView) view).getDrawable());
        // TODO: Change all butttons' tags to their file names, uncomment the above to restore the correct behaviour, and use the below as a base to save the resource name and load the image based on it later

        String filename = view.getTag().toString();
        int resID = getResources().getIdentifier(filename, "drawable", getPackageName());

        selectedEyes = filename;

        System.out.println(resID);

        eyesImage.setImageResource(resID);
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
        saveCharacter();

        Intent nextActivity;
        if (fromNewGame) {
            nextActivity = new Intent(this, CoursesActivity.class);
        }
        else {
            nextActivity = new Intent(this, ApartmentActivity.class);
        }
        startActivity(nextActivity);
    }
}