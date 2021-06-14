package comp3350.studentlifesimulator.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.studentlifesimulator.R;

import comp3350.studentlifesimulator.presentation.ApartmentActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent apartmentActivity = new Intent(this, ApartmentActivity.class);
        startActivity(apartmentActivity);
    }
}