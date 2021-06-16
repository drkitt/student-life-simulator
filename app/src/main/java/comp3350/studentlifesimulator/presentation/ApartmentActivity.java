package comp3350.studentlifesimulator.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.studentlifesimulator.R;

import java.util.Locale;

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
        student = new Student("Player");
        studentPerformingActions = new StudentPerformingActions();
        studyAction = new Action("Study", 1, 4);
        Button studyButton = findViewById(R.id.studyButton);
        studyButton.setOnClickListener(view -> doAction(studyAction));

        displayCurrentTime(time.getCurrentTime());
    }

    private void doAction(Action action) {
        studentPerformingActions.makeStudentPerformAction(student, action, time);

        displayCurrentTime(time.getCurrentTime());
    }

    private void displayCurrentTime(int timeUnit) {
        TextView timeView = findViewById(R.id.currentTime);
        int hour = timeUnit * MINUTES_PER_TIME_UNIT / 60;
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
        int minute = timeUnit * MINUTES_PER_TIME_UNIT % 60;
        String displayedTime = String.format(Locale.getDefault(), "%d:%02d %s\nDay %d", hour, minute, suffix, time.getDays());

        timeView.setText(displayedTime);
    }
}
