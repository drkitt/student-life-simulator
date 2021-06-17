package comp3350.studentlifesimulator.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;

import com.example.studentlifesimulator.R;

import comp3350.studentlifesimulator.presentation.CoursesActivity;

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