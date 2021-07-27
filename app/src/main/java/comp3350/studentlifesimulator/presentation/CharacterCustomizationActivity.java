package comp3350.studentlifesimulator.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.studentlifesimulator.R;

import java.util.Arrays;
import java.util.Dictionary;

import comp3350.studentlifesimulator.application.Main;
import comp3350.studentlifesimulator.business.DatabaseManager;

public class CharacterCustomizationActivity extends AppCompatActivity {
    String selectedEyes, selectedHair, selectedSkinColour, selectedShirt;
    boolean fromMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_customization);
        fromMenu = getIntent().getBooleanExtra("fromMenu", false);
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
        System.out.println(view.getTag());

        ImageView hairImage = findViewById(R.id.hairImage);
        selectedHair = view.getTag().toString();
        hairImage.setImageDrawable(((ImageView) view).getDrawable());
    }

    public void setSkinColour(View view) {
        System.out.println(view.getTag());

        ImageView skinColourImage = findViewById(R.id.skinColourImage);
        selectedSkinColour = view.getTag().toString();
        skinColourImage.setImageDrawable(((ImageView) view).getDrawable());
    }

    public void setShirt(View view) {
        System.out.println(view.getTag());

        ImageView shirtImage = findViewById(R.id.shirtImage);
        selectedShirt = view.getTag().toString();
        shirtImage.setImageDrawable(((ImageView) view).getDrawable());
    }

    public void onSaveClick() {
        // TODO: Save each selected variable to database

        Intent nextActivity;

        if (fromMenu) {
            nextActivity = new Intent(this, MainActivity.class);
        }
        else {
            nextActivity = new Intent(this, CoursesActivity.class);
        }

        startActivity(nextActivity);
    }

    private void loadCharacter() {
        ImageView eyesImage = findViewById(R.id.eyesImage);
        eyesImage.setImageResource(getResources().getIdentifier(DatabaseManager.getEyes(), "drawable", getPackageName()));

        ImageView hairImage = findViewById(R.id.hairImage);
        hairImage.setImageResource(getResources().getIdentifier(DatabaseManager.getHair(), "drawable", getPackageName()));

        ImageView skinColourImage = findViewById(R.id.skinColourImage);
        skinColourImage.setImageResource(getResources().getIdentifier(DatabaseManager.getSkinColour(), "drawable", getPackageName()));

        ImageView shirtImage = findViewById(R.id.shirtImage);
        shirtImage.setImageResource(getResources().getIdentifier(DatabaseManager.getShirt(), "drawable", getPackageName()));
    }
}