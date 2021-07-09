package comp3350.studentlifesimulator.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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
    private Time time;
    private Student student;
    private StudentPerformingActions studentPerformingActions;
    private Action marathonAction, studyAction, quickStudyAction;
    private Action hibernateAction, sleepAction, napAction;
    private Action attendAction, listenToInstructorAction, talkToFriendsAction, skipClassAction;
    private static final int MINUTES_PER_TIME_UNIT = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apartment_activity);

        time = new Time(4 * 8, 60 * 24 / MINUTES_PER_TIME_UNIT);
        student = DatabaseManager.getStudent();
        studentPerformingActions = new StudentPerformingActions();

        ProgressBar energyBar = findViewById(R.id.energyBar);
        energyBar.setMax(Student.getMaxEnergy());

        displayActions();
        displayCurrentTime();
        displayCurrentEnergy();
    }

    private void displayActions(){ // FOR DEVS: Action values are in the google doc!
        //=============== STUDY ACTIONS ==============
        //TODO: Figure our how to get remaining time or re-visit with a decision from the team
        //currently, marathon takes 1/4 of the day = 24 units out of 96 total




        marathonAction = new Action("Marathon Study", student.getCurrentEnergy(), 24, 1);
        Button marathonButton = findViewById(R.id.marathonButton);
        marathonButton.setOnClickListener(view -> doAction(marathonAction));

        studyAction = new Action("Study", -5, 5, 1);
        Button studyButton = findViewById(R.id.studyButton);
        studyButton.setOnClickListener(view -> doAction(studyAction));

        quickStudyAction = new Action("Quick Study", -1, 1, 1);
        Button quickStudyButton = findViewById(R.id.quickStudyButton);
        quickStudyButton.setOnClickListener(view -> doAction(quickStudyAction));

        //=============== REST ACTIONS ==============
        hibernateAction = new Action("Hibernate", Student.getMaxEnergy(), 48, 1);
        Button hibernateButton = findViewById(R.id.hibernateButton);
        hibernateButton.setOnClickListener(view -> doAction(hibernateAction));

        sleepAction = new Action("Sleep", 5, 5, 1);
        Button sleepButton = findViewById(R.id.sleepButton);
        sleepButton.setOnClickListener(view -> doAction(sleepAction));

        napAction = new Action("Nap", 1, 1, 1);
        Button napButton = findViewById(R.id.napButton);
        napButton.setOnClickListener(view -> doAction(napAction));

        //attendAction = new Action("Attend", -1, 4, 5);
        //Attend class buttons starts
        Button attendButton = findViewById(R.id.attendButton);
        Button listenButton = findViewById(R.id.listenButton);
        Button talkToFriendsButton = findViewById(R.id.talkButton);
        Button skipClassButton = findViewById(R.id.skipClassButton);


        attendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenButton.setVisibility(v.VISIBLE);
                talkToFriendsButton.setVisibility(v.VISIBLE);
                attendButton.setVisibility(v.INVISIBLE);
                skipClassButton.setVisibility(v.INVISIBLE);
            }
        });

        listenToInstructorAction = new Action("Listen To Instructor", -2, 1, 12);
        listenButton.setOnClickListener(view -> doAction(listenToInstructorAction));

        talkToFriendsAction = new Action("Talk To Friends", -1, 1, 3);
        talkToFriendsButton.setOnClickListener(view -> doAction(talkToFriendsAction));


        skipClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attendButton.setVisibility(v.INVISIBLE);
                skipClassButton.setVisibility(v.INVISIBLE);
            }
        });


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
