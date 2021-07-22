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

        ImageView eyesImage = findViewById(R.id.eyesImage);
//        eyesImage.setImageDrawable(((ImageView) view).getDrawable());
        // TODO: Change all butttons' tags to their file names, uncomment the above to restore the correct behaviour, and use the below as a base to save the resource name and load the image based on it later

        int resID = getResources().getIdentifier("eyes_blue", "drawable", getPackageName());
        System.out.println(resID);
        resID = getResources().getIdentifier("eyes_blue", "drawable", getPackageName());
        System.out.println(resID);

        eyesImage.setImageResource(resID);

        int funnyID = getResources().getIdentifier("hgeuilhgukrehhgksu", "drawable", getPackageName());
        System.out.println(funnyID);
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