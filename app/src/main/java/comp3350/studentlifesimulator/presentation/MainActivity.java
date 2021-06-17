package comp3350.studentlifesimulator.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.studentlifesimulator.R;

public class MainActivity extends AppCompatActivity {
    Button newGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newGame = findViewById(R.id.newGameButton);
        newGame.setOnClickListener(view -> switchActivity());
    }

    private void switchActivity() {
        Intent coursesActivity = new Intent(this, CoursesActivity.class);

        startActivity(coursesActivity);
    }
}
