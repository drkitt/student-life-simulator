package comp3350.studentlifesimulator.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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

public class CharacterCustomizationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_customization);
    }

    public void setEyes(View view) {
        System.out.println(view.getTag());

        // TODO: Business logic for saving attribute choice (use the tag?)
        ImageView eyesImage = findViewById(R.id.eyesImage);
        eyesImage.setImageDrawable(((ImageView) view).getDrawable());
    }

    public void setHair(View view) {
        System.out.println(view.getTag());

        ImageView hairImage = findViewById(R.id.hairImage);
        hairImage.setImageDrawable(((ImageView) view).getDrawable());
    }

    public void setSkinColour(View view) {
        System.out.println(view.getTag());

        ImageView skinColourImage = findViewById(R.id.skinColourImage);
        skinColourImage.setImageDrawable(((ImageView) view).getDrawable());
    }

    public void setShirt(View view) {
        System.out.println(view.getTag());

        ImageView shirtImage = findViewById(R.id.shirtImage);
        shirtImage.setImageDrawable(((ImageView) view).getDrawable());
    }
}