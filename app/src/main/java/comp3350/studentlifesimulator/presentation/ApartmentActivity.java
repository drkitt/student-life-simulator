package comp3350.studentlifesimulator.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentlifesimulator.R;

import java.util.Dictionary;
import java.util.Locale;

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
        listenButton = findViewById(R.id.listenButton);
        talkButton = findViewById(R.id.talkButton);
        attendButton = findViewById(R.id.attendButton);
        skipButton = findViewById(R.id.skipClassButton);

        setActionButtons(StateManager.getState(), StateManager.getCurrentPossibleActions(StateManager.getState()));
        displayActions(StateManager.getState());
        displayCurrentTime();
        displayCurrentEnergy();
    }

    private void setActionButtons(ActionStates curState, Dictionary<String, Action> actionList) {
        Action marathonAction, studyAction, quickStudyAction;
        Action hibernateAction, sleepAction, napAction;
        Action listenAction, talkAction;

        switch (curState) {
            case IN_CLASS_LOW:
                napAction = actionList.get("Nap");
                napButton.setOnClickListener(view -> doAction(napAction));
                break;

            case IN_CLASS_HIGH:
                listenAction = actionList.get("Listen to Instructor");
                napAction = actionList.get("Nap");
                quickStudyAction = actionList.get("Quick Study");
                talkAction = actionList.get("Talk with Friends");
                listenButton.setOnClickListener(view -> doAction((listenAction)));
                napButton.setOnClickListener(view -> doAction(napAction));
                quickStudyButton.setOnClickListener(view -> doAction(quickStudyAction));
                talkButton.setOnClickListener(view -> doAction(talkAction));
                break;

            case LOW_ENERGY:
                hibernateAction = actionList.get("Hibernate");
                napAction = actionList.get("Nap");
                sleepAction = actionList.get("Sleep");
                hibernateButton.setOnClickListener(view -> doAction(hibernateAction));
                napButton.setOnClickListener(view -> doAction(napAction));
                sleepButton.setOnClickListener(view -> doAction(sleepAction));
                break;

            case FREE_TIME:
                hibernateAction = actionList.get("Hibernate");
                marathonAction = actionList.get("Marathon Study");
                napAction = actionList.get("Nap");
                quickStudyAction = actionList.get("Quick Study");
                sleepAction = actionList.get("Sleep");
                studyAction = actionList.get("Study");
                hibernateButton.setOnClickListener(view -> doAction(hibernateAction));
                marathonButton.setOnClickListener(view -> doAction(marathonAction));
                napButton.setOnClickListener(view -> doAction(napAction));
                quickStudyButton.setOnClickListener(view -> doAction(quickStudyAction));
                sleepButton.setOnClickListener(view -> doAction(sleepAction));
                studyButton.setOnClickListener((view -> doAction(studyAction)));
                break;

            case HAS_CLASS:
                attendButton.setOnClickListener(view -> {
                    StateManager.switchInClass();
                    setActionButtons(StateManager.getState(), StateManager.getCurrentPossibleActions(StateManager.getState()));
                    displayActions(StateManager.getState());
                });
                skipButton.setOnClickListener(view -> {
                    StateManager.switchSkipped();
                    setActionButtons(StateManager.getState(), StateManager.getCurrentPossibleActions(StateManager.getState()));
                    displayActions(StateManager.getState());
                });
                break;
        }
    }

    private void displayActions(ActionStates curState) {
        switch (curState) {
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
                marathonButton.setVisibility(View.INVISIBLE);
                studyButton.setVisibility(View.INVISIBLE);
                quickStudyButton.setVisibility(View.INVISIBLE);
                hibernateButton.setVisibility(View.INVISIBLE);
                sleepButton.setVisibility(View.INVISIBLE);
                napButton.setVisibility(View.INVISIBLE);
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

        if (StateManager.getInClass()) {
            StateManager.switchInClass();
        }
        else if (StateManager.getSkipped()) {
            StateManager.switchSkipped();
        }

        displayCurrentTime();
        displayCurrentEnergy();
        setActionButtons(StateManager.getState(), StateManager.getCurrentPossibleActions(StateManager.getState()));
        displayActions(StateManager.getState());
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
        String displayedTime = String.format(Locale.getDefault(),
                "%d:%02d %s\nDay %d", hour, minute, suffix, time.getDays());

        timeView.setText(displayedTime);
    }

    private void displayCurrentEnergy() {
        ProgressBar energyBar = findViewById(R.id.energyBar);
        energyBar.setProgress(student.getCurrentEnergy());
    }
}
