package comp3350.studentlifesimulator.presentation;

import android.app.Activity;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentlifesimulator.R;

import comp3350.studentlifesimulator.business.DatabaseManager;

public abstract class CharacterActivity extends AppCompatActivity {
    protected String selectedEyes, selectedHair, selectedSkinColour, selectedShirt;

    protected void loadCharacter() {
        selectedEyes = DatabaseManager.getEyes();
        ImageView eyesImage = findViewById(R.id.eyesImage);
        eyesImage.setImageResource(getResources().getIdentifier(selectedEyes, "drawable", getPackageName()));

        selectedHair = DatabaseManager.getHair();
        ImageView hairImage = findViewById(R.id.hairImage);
        hairImage.setImageResource(getResources().getIdentifier(selectedHair, "drawable", getPackageName()));

        selectedSkinColour = DatabaseManager.getSkinColour();
        ImageView skinColourImage = findViewById(R.id.skinColourImage);
        skinColourImage.setImageResource(getResources().getIdentifier(selectedSkinColour, "drawable", getPackageName()));

        selectedShirt = DatabaseManager.getShirt();
        ImageView shirtImage = findViewById(R.id.shirtImage);
        shirtImage.setImageResource(getResources().getIdentifier(selectedShirt, "drawable", getPackageName()));
    }

    protected void saveCharacter() {
        DatabaseManager.updateEyes(selectedEyes);
        DatabaseManager.updateHair(selectedHair);
        DatabaseManager.updateSkinColour(selectedSkinColour);
        DatabaseManager.updateShirt(selectedShirt);
    }
}
