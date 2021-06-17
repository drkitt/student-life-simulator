package comp3350.studentlifesimulator.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentlifesimulator.R;

import java.util.Locale;

import comp3350.studentlifesimulator.business.DatabaseManager;
import comp3350.studentlifesimulator.business.StudentPerformingActions;
import comp3350.studentlifesimulator.objects.Action;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Time;

public class ApartmentActivity extends AppCompatActivity {

    private static final int MINUTES_PER_TIME_UNIT = 15;
    private Time time;
    private Student student;
    private StudentPerformingActions studentPerformingActions;
    private Action studyAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apartment_activity);

        time = new Time(4 * 8, 60 * 24 / MINUTES_PER_TIME_UNIT);
        student = DatabaseManager.getStudent();
        studentPerformingActions = new StudentPerformingActions();
        studyAction = new Action("Study", 1, 4);

        Button studyButton = findViewById(R.id.studyButton);
        studyButton.setOnClickListener(view -> doAction(studyAction));
        ProgressBar energyBar = findViewById(R.id.energyBar);
        energyBar.setMax(Student.getMaxEnergy());

        displayCurrentTime();
        displayCurrentEnergy();
    }

    private void doAction(Action action) {
        boolean result = studentPerformingActions.makeStudentPerformAction(student, action, time);

        if (!result) {
            Toast.makeText(this, "You're out of energy!", Toast.LENGTH_SHORT).show();
        }

        displayCurrentTime();
        displayCurrentEnergy();
    }

    private void displayCurrentTime() {
        TextView timeView = findViewById(R.id.currentTime);
        int hour = time.getCurrentTime() * MINUTES_PER_TIME_UNIT / 60;
        String suffix;
        if (hour < 12) {
            suffix = "AM";
        }
        else {
            if (hour > 12) {
                hour -= 12;
            }
            suffix = "PM";
        }
        int minute = time.getCurrentTime() * MINUTES_PER_TIME_UNIT % 60;
        String displayedTime = String.format(Locale.getDefault(), "%d:%02d %s\nDay %d", hour, minute, suffix, time.getDays());

        timeView.setText(displayedTime);
    }

    private void displayCurrentEnergy() {
        ProgressBar energyBar = findViewById(R.id.energyBar);
        energyBar.setProgress(student.getCurrentEnergy());
    }
}
