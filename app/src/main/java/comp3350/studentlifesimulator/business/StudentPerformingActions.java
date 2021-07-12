package comp3350.studentlifesimulator.business;

import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Action;
import comp3350.studentlifesimulator.objects.Time;

public class StudentPerformingActions {
    public static boolean makeStudentPerformAction(Student student, Action action, Time time) {
        boolean canDoAction = student.canDoAction(action);

        if (canDoAction) {
            student.doAction(action);
            time.addToTime(action.getTimeUnit());
            student.addToScore(action.getPointsUnit());
        }

        return canDoAction;
    }
}
