package comp3350.studentlifesimulator.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.studentlifesimulator.R;

public class ApartmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apartment_activity);

        TextView currentTime = findViewById(R.id.currentTime);
        currentTime.setText("7:30 PM\nDay 1");
    }
}
