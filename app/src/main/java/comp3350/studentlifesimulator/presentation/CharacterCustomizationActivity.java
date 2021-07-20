package comp3350.studentlifesimulator.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.studentlifesimulator.R;

import java.util.Arrays;

public class CharacterCustomizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_customization);
    }

    public void setEyes(View view) {
        System.out.println(view.getTag());

        // TODO: Business logic for saving attribute choice (use the tag?)
        // Also TODO: Actually set the correct image :) The details of how to do this will probably become clearer when we have the functionality for loading attribute choices
        ImageView eyesImage = findViewById(R.id.eyesImage);
        eyesImage.setImageResource(R.drawable.eyes_blue);
    }

    public void setHair(View view) {
        System.out.println(view.getTag());

    }

    public void setSkinColour(View view) {
        System.out.println(view.getTag());

    }

    public void setShirt(View view) {
        System.out.println(view.getTag());

    }
}