package comp3350.studentlifesimulator.business;

import comp3350.studentlifesimulator.application.DatabaseServices;
import comp3350.studentlifesimulator.objects.Action;
import comp3350.studentlifesimulator.objects.Time;
import comp3350.studentlifesimulator.persistence.DatabaseAccessInterface;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Course;

import java.util.ArrayList;
import java.util.Dictionary;

public class DatabaseManager {
    private enum CharacterAssetTypes {
        EYES,
        HAIR,
        SKIN_COLOUR,
        SHIRT
    }

    private static DatabaseAccessInterface database;

    public static void setDatabase() {
        database = DatabaseServices.getDatabaseAccess();
    }

    public static void updateStudent(Student student) {
        database.updateStudent(student);
    }

    public static void addCourse(Course course) {
        database.addSelectedCourse(course);
    }

    public static boolean removeCourse(Course course) {
        return database.removeSelectedCourse(course);
    }

    public static Student getStudent() {
        return database.getStudent();
    }

    public static ArrayList<Course> getAvailableCourses() {
        return database.getCourses();
    }

    public static ArrayList<Course> getSelectedCourses() {
        return database.getSelectedCourses();
    }

    public static Dictionary<String, Action> getActions(int key) {
        return database.getActions(key);
    }

    public static Time getTime() {
        return database.getTime();
    }

    public static void updateTime(Time time) {
        database.updateTime(time);
    }

    public static String getEyes() {
        return database.getCharacterAsset(CharacterAssetTypes.EYES.ordinal());
    }

    public static String getHair() {
        return database.getCharacterAsset(CharacterAssetTypes.HAIR.ordinal());
    }

    public static String getSkinColour() {
        return database.getCharacterAsset(CharacterAssetTypes.SKIN_COLOUR.ordinal());
    }

    public static String getShirt() {
        return database.getCharacterAsset(CharacterAssetTypes.SHIRT.ordinal());
    }

    public static void updateEyes(String eyesTag) {
        database.updateCharacterAsset(CharacterAssetTypes.EYES.ordinal(), eyesTag);
    }

    public static void updateHair(String hairTag) {
        database.updateCharacterAsset(CharacterAssetTypes.HAIR.ordinal(), hairTag);
    }

    public static void updateSkinColour(String skinColourTag) {
        database.updateCharacterAsset(CharacterAssetTypes.SKIN_COLOUR.ordinal(), skinColourTag);
    }

    public static void updateShirt(String shirtTag) {
        database.updateCharacterAsset(CharacterAssetTypes.SHIRT.ordinal(), shirtTag);
    }
}
