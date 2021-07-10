package comp3350.studentlifesimulator.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.studentlifesimulator.R;

public class CharacterCustomizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_customization);

        ImageButton button = findViewById(R.id.hair4DarkButton);
        button.setOnClickListener(view -> onTabClick());
    }

    private void onTabClick() {
        ConstraintLayout example = findViewById(R.id.daConstraintLayout);
        example.setVisibility(ConstraintLayout.INVISIBLE);
    }
}