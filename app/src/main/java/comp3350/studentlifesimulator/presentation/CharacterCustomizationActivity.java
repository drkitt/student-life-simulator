package comp3350.studentlifesimulator.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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

    public void setAttribute(View view) {
        System.out.println(Arrays.toString(view.getTag().toString().split("/")));
        System.out.println(view.getTag());

        System.out.println(getAttributeGroup(view.getTag().toString()));
        System.out.println(getAttributeValue(view.getTag().toString()));

        Button example = findViewById(R.id.startButton);
        example.setVisibility(Button.INVISIBLE);
    }

    private String getAttributeGroup(String attributeTag) {
        return attributeTag.split("/")[0];
    }

    private String getAttributeValue(String attributeTag) {
        return attributeTag.split("/")[1];
    }

    private void onTabClick() {
        Button example = findViewById(R.id.startButton);
        example.setVisibility(Button.INVISIBLE);
    }
}