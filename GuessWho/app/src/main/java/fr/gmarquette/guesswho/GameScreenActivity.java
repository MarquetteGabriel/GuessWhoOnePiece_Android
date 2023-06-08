package fr.gmarquette.guesswho;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import fr.gmarquette.guesswho.datas.Database;

public class GameScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Database.getInstance(getApplicationContext()).CreateDatabase();
    }
}