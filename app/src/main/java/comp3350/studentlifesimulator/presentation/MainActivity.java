package comp3350.studentlifesimulator.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.studentlifesimulator.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import comp3350.studentlifesimulator.application.Main;

public class MainActivity extends AppCompatActivity {
    Button newGame, continueGame, credits, howToPlay;
    boolean newGamePressed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    @Override
    protected void onResume() {
        super.onResume();

        initialize();
    }

    private void initialize() {
        newGamePressed = false;

        copyDatabaseToDevice();
        Main.openDBAccess();

        newGame = findViewById(R.id.newGameButton);
        continueGame = findViewById(R.id.continueButton);
        credits = findViewById(R.id.creditsButton);
        howToPlay = findViewById(R.id.howToPlayButton);

        if (Main.checkPreviousData()) {
            continueGame.setVisibility(View.VISIBLE);
        }
        else {
            continueGame.setVisibility(View.INVISIBLE);
        }

        newGame.setOnClickListener(v -> {
            newGamePressed = true;
            Main.closeDBAccess();
            copyDatabaseToDevice();
            Main.openDBAccess();
            switchActivity();
        });
        continueGame.setOnClickListener(View -> switchActivity());

        credits.setOnClickListener(v -> {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create(); //Read Update
            alertDialog.setTitle("Credits");
            alertDialog.setMessage(getString(R.string.dialog_credits));

            alertDialog.setButton("Close", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                }
            });
            alertDialog.show();
        });

        howToPlay.setOnClickListener(v ->{
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("How To Play");
            alertDialog.setMessage(getString(R.string.how_to_play));

            alertDialog.setButton("Close", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                }
            });
            alertDialog.show();

        });
    }

    private void switchActivity() {
        Intent nextActivity;

        if (!Main.checkPreviousData() || newGamePressed) {
            nextActivity = new Intent(this, CharacterCustomizationActivity.class);
            nextActivity.putExtra("fromNewGame", true);
        }
        else {
            nextActivity = new Intent(this, ApartmentActivity.class);
        }
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

            if (outFile.exists() && newGamePressed){
                outFile.delete();
            }

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
