package comp3350.studentlifesimulator.business;

import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Action;

public class StudentPerformingActions {
    public boolean makeStudentPerformAction(Student student, Action action) {
        boolean canDoAction = student.canDoAction(action);
        if (canDoAction) {
            student.doAction(action);
            // That's all for now. TODO: Integrate this with the other layers! Show something in presentation, maybe log it in persistence, I dunno
        }
        return canDoAction;
    }
}
