package comp3350.studentlifesimulator.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.studentlifesimulator.R;

import java.util.ArrayList;

import comp3350.studentlifesimulator.business.DatabaseManager;
import comp3350.studentlifesimulator.objects.Course;

public class CoursesActivity extends AppCompatActivity {
    ListView courseList;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courses_activity);

        courseList = findViewById(R.id.courses);

        registerButton = findViewById(R.id.register_button);

        ArrayList <Course> courseArray = DatabaseManager.getAvailableCourses();
        courseList.setAdapter(new ArrayAdapter<>(this , android.R.layout.simple_list_item_multiple_choice , courseArray));

        registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(view -> completeRegistration());
    }

    private void completeRegistration() {
        Intent apartmentActivity = new Intent(this, ApartmentActivity.class);

        startActivity(apartmentActivity);
    }
}