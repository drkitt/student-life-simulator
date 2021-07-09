package comp3350.studentlifesimulator.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentlifesimulator.R;

import java.util.ArrayList;
import java.util.Locale;

import comp3350.studentlifesimulator.business.DatabaseManager;
import comp3350.studentlifesimulator.business.StateManager;
import comp3350.studentlifesimulator.business.StudentPerformingActions;
import comp3350.studentlifesimulator.objects.Action;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Time;

import comp3350.studentlifesimulator.objects.ActionStates;

public class ApartmentActivity extends AppCompatActivity {
    private Time time;
    private Student student;
    private StudentPerformingActions studentPerformingActions;
    private static final int MINUTES_PER_TIME_UNIT = 15;

    private Button marathonButton;
    private Button studyButton;
    private Button quickStudyButton;
    private Button hibernateButton;
    private Button sleepButton;
    private Button napButton;
    private Button listenButton;
    private Button talkButton;
    private Button attendButton;
    private Button skipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apartment_activity);

        StateManager.initialize();
        time = StateManager.getTime();
        student = StateManager.getCurrentStudent();
        studentPerformingActions = new StudentPerformingActions();

        ProgressBar energyBar = findViewById(R.id.energyBar);
        energyBar.setMax(Student.getMaxEnergy());

        marathonButton = findViewById(R.id.marathonButton);
        studyButton = findViewById(R.id.studyButton);
        quickStudyButton = findViewById(R.id.quickStudyButton);
        hibernateButton = findViewById(R.id.hibernateButton);
        sleepButton = findViewById(R.id.sleepButton);
        napButton = findViewById(R.id.napButton);
//        listenButton = findViewById(R.id.listenButton);
//        talkButton = findViewById(R.id.talkButton);
//        attendButton = findViewById(R.id.attendButton);
//        skipButton = findViewById(R.id.skipButton);

        setActionButtons();
        displayActions(ActionStates.IN_CLASS_HIGH);
        displayCurrentTime();
        displayCurrentEnergy();
    }

    private void setActionButtons(){
        Action marathonAction, studyAction, quickStudyAction;
        Action hibernateAction, sleepAction, napAction;
        Action listenAction, talkAction;
        Action attendAction, skipAction;

//        marathonButton.setOnClickListener(view -> doAction(marathonAction));
//        studyButton.setOnClickListener(view -> doAction(studyAction));
//        quickStudyButton.setOnClickListener(view -> doAction(quickStudyAction));
//
//        hibernateButton.setOnClickListener(view -> doAction(hibernateAction));
//        sleepButton.setOnClickListener(view -> doAction(sleepAction));
//        napButton.setOnClickListener(view -> doAction(napAction));
//
//        listenButton.setOnClickListener(view -> doAction(listenAction));
//        talkButton.setOnClickListener(view -> doAction(talkAction));
//
//        attendButton.setOnClickListener(view -> doAction(attendAction));
//        skipButton.setOnClickListener(view -> doAction(skipAction));
    }

    private void displayActions(ActionStates curState){ // FOR DEVS: Action values are in the google doc!
        switch (curState){
            case IN_CLASS_LOW:
                marathonButton.setVisibility(View.INVISIBLE);
                studyButton.setVisibility(View.INVISIBLE);
                quickStudyButton.setVisibility(View.INVISIBLE);
                hibernateButton.setVisibility(View.INVISIBLE);
                sleepButton.setVisibility(View.INVISIBLE);
                napButton.setVisibility(View.VISIBLE);
                listenButton.setVisibility((View.INVISIBLE));
                talkButton.setVisibility(View.INVISIBLE);
                attendButton.setVisibility(View.INVISIBLE);
                skipButton.setVisibility(View.INVISIBLE);
                break;

            case IN_CLASS_HIGH:
                marathonButton.setVisibility(View.INVISIBLE);
                studyButton.setVisibility(View.INVISIBLE);
                quickStudyButton.setVisibility(View.VISIBLE);
                hibernateButton.setVisibility(View.INVISIBLE);
                sleepButton.setVisibility(View.INVISIBLE);
                napButton.setVisibility(View.VISIBLE);
                listenButton.setVisibility((View.VISIBLE));
                talkButton.setVisibility(View.VISIBLE);
                attendButton.setVisibility(View.INVISIBLE);
                skipButton.setVisibility(View.INVISIBLE);
                break;

            case LOW_ENERGY:
                marathonButton.setVisibility(View.INVISIBLE);
                studyButton.setVisibility(View.INVISIBLE);
                quickStudyButton.setVisibility(View.INVISIBLE);
                hibernateButton.setVisibility(View.VISIBLE);
                sleepButton.setVisibility(View.VISIBLE);
                napButton.setVisibility(View.VISIBLE);
                listenButton.setVisibility((View.INVISIBLE));
                talkButton.setVisibility(View.INVISIBLE);
                attendButton.setVisibility(View.INVISIBLE);
                skipButton.setVisibility(View.INVISIBLE);
                break;

            case FREE_TIME:
                marathonButton.setVisibility(View.VISIBLE);
                studyButton.setVisibility(View.VISIBLE);
                quickStudyButton.setVisibility(View.VISIBLE);
                hibernateButton.setVisibility(View.VISIBLE);
                sleepButton.setVisibility(View.VISIBLE);
                napButton.setVisibility(View.VISIBLE);
                listenButton.setVisibility((View.INVISIBLE));
                talkButton.setVisibility(View.INVISIBLE);
                attendButton.setVisibility(View.INVISIBLE);
                skipButton.setVisibility(View.INVISIBLE);
                break;

            case HAS_CLASS:
                marathonButton.setVisibility(View.VISIBLE);
                studyButton.setVisibility(View.VISIBLE);
                quickStudyButton.setVisibility(View.VISIBLE);
                hibernateButton.setVisibility(View.VISIBLE);
                sleepButton.setVisibility(View.VISIBLE);
                napButton.setVisibility(View.VISIBLE);
                listenButton.setVisibility((View.INVISIBLE));
                talkButton.setVisibility(View.INVISIBLE);
                attendButton.setVisibility(View.VISIBLE);
                skipButton.setVisibility(View.VISIBLE);
                break;
        }
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
