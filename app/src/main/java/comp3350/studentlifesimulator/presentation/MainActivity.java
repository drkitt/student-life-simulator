package comp3350.studentlifesimulator.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.Button;

import com.example.studentlifesimulator.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import comp3350.studentlifesimulator.application.Main;

public class MainActivity extends AppCompatActivity {
    Button newGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        copyDatabaseToDevice();
        Main.openDBAccess();

        newGame = findViewById(R.id.newGameButton);
        newGame.setOnClickListener(view -> switchActivity());
    }

    private void switchActivity() {
        Intent nextActivity;

        if (Main.checkPreviousData()) {
            nextActivity = new Intent(this, ApartmentActivity.class);
        }
        else {
            nextActivity = new Intent(this, CoursesActivity.class);
        }

        // Making the new game button go to the character customization activity temporarily, for testing purposes
        nextActivity = new Intent(this, CharacterCustomizationActivity.class);

        startActivity(nextActivity);
    }

    private void copyDatabaseToDevice() {
        final String PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File targetDirectory = context.getDir(PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {
            assetNames = assetManager.list(PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, targetDirectory);

            Main.setDBPath(targetDirectory.toString() + "/" + Main.getDBName());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyAssetsToDirectory(String[] assets, File targetDirectory) throws IOException {
        AssetManager assetManager = getAssets();
        String[] components;
        String copyPath;
        char[] buffer;
        File outFile;
        InputStreamReader in;
        FileWriter out;
        int count;

        for (String asset : assets) {
            components = asset.split("/");
            copyPath = targetDirectory.toString() + "/" + components[components.length - 1];
            buffer = new char[1024];
            outFile = new File(copyPath);

            if (!outFile.exists()) {
                in = new InputStreamReader(assetManager.open(asset));
                out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
}
