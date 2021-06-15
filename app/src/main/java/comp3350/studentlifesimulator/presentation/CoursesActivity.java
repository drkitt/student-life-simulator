package comp3350.studentlifesimulator.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.studentlifesimulator.R;

import java.util.ArrayList;

import comp3350.studentlifesimulator.business.DatabaseManager;
import comp3350.studentlifesimulator.objects.Course;

public class CoursesActivity extends AppCompatActivity {

    ListView courseList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courses_activity);

        courseList = findViewById(R.id.courses);

        ArrayList <Course> courseArray = DatabaseManager.getAvailableCourses();
        courseList.setAdapter(new ArrayAdapter<>(this , android.R.layout.simple_list_item_multiple_choice , courseArray));
    }



}