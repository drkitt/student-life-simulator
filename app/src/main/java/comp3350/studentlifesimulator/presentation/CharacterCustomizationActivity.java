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

        ImageButton button = findViewById(R.id.hair4DarkButton);
        button.setOnClickListener(view -> onTabClick());
    }

    public void setEyes(View view) {
        System.out.println(view.getTag());

        // TODO: Business logic for saving attribute choice (use the tag?)

        ImageView eyesImage = findViewById(R.id.eyesImage);
        eyesImage.setImageResource(R.drawable.eyes_blue);
    }

    private void onTabClick() {
        Button example = findViewById(R.id.startButton);
        example.setVisibility(Button.INVISIBLE);
    }
}