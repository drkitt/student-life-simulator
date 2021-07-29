package comp3350.studentlifesimulator.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.studentlifesimulator.R;

import java.util.ArrayList;

import comp3350.studentlifesimulator.business.DatabaseManager;
import comp3350.studentlifesimulator.objects.Course;

public class CoursesActivity extends AppCompatActivity {
    ListView courseList;
    Button registerButton;
    ArrayList<Course> courseArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courses_activity);

        courseList = findViewById(R.id.courses);

        registerButton = findViewById(R.id.registerButton);

        courseArray = DatabaseManager.getAvailableCourses();
        courseList.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                courseArray)
        );

        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(view -> completeRegistration());
    }

    private void completeRegistration() {
        Intent apartmentActivity = new Intent(this, ApartmentActivity.class);
        int numOfCoursesSelected = 0;

        apartmentActivity.setFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME);

        for (int index = 0; index < courseArray.size(); index++) {
            if (courseList.isItemChecked(index)) {
                numOfCoursesSelected++;
            }
        }

        if (numOfCoursesSelected <= 4) {
            for (int index = 0; index < courseArray.size(); index++) {
                if (courseList.isItemChecked(index)) {
                    DatabaseManager.addCourse(courseArray.get(index));
                }
            }
        }

        if (numOfCoursesSelected == 0) {
            Toast.makeText(this, "Pick a course!", Toast.LENGTH_SHORT).show();
        }
        else if(numOfCoursesSelected > 4){
            Toast.makeText(this, "You can only select a maximum of 4 courses!", Toast.LENGTH_SHORT).show();
        }
        else {
            startActivity(apartmentActivity);
            this.finish();
        }
    }
}
