package fr.gmarquette.guesswho;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.gmarquette.guesswho.databinding.GameScreenBinding;
import fr.gmarquette.guesswho.database.Characters;
import fr.gmarquette.guesswho.datas.DataBaseSingleton;
import fr.gmarquette.guesswho.datas.SearchCharacters;
import fr.gmarquette.guesswho.game.GameInit;
import fr.gmarquette.guesswho.game.GameManager;
import fr.gmarquette.guesswho.gui.FirstAnswerFirstColumn;
import fr.gmarquette.guesswho.gui.GameScreenViewModel;

public class GameScreenActivity extends AppCompatActivity {

    public static int NUMBER_GUESSED = 0;
    private GameScreenViewModel gameScreenViewModel;
    private GameInit gameInit;
    private SearchCharacters searchCharacters;
    List<String> suggestions;
    Characters characterToFind;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameScreenBinding binding = DataBindingUtil.setContentView(this, R.layout.game_screen);
        binding.setViewmodel(this.gameScreenViewModel);
        binding.setLifecycleOwner(this);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        DataBaseSingleton.getInstance().getDataBase(this).CreateDatabase();
        searchCharacters = new SearchCharacters(this);
        this.getSuggestions();
        gameInit = new GameInit(this);
        characterToFind = gameInit.getCharacterToFound();
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.EnterTextAutoCompleted);


        getSuggestions();


        autoCompleteTextView.setOnItemClickListener((adapterView, view, position, id) -> {
            String selectedValue = autoCompleteTextView.getAdapter().getItem(position).toString();
            autoCompleteTextView.setText(selectedValue);
            updateFragments(selectedValue);
        });

        NUMBER_GUESSED = 0;

    }

    public void updateFragments(String value)
    {
        FirstAnswerFirstColumn fragments = (FirstAnswerFirstColumn) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);
        assert fragments != null;
        fragments.updateText(value);
        Characters character;
        try {
            character = searchCharacters.getCharacterAsync(value).get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Met a jour le fragment 1
        this.updateText(GameManager.hasEatenDevilFruit(character, characterToFind), findViewById(R.id.alive1));
        this.updateText(GameManager.lookingForBounty(character, characterToFind), findViewById(R.id.alive2));
        this.updateText(GameManager.getAppearanceDiff(character, characterToFind), findViewById(R.id.alive3));
        this.updateText(GameManager.getType(character, characterToFind), findViewById(R.id.alive4));
        this.updateText(GameManager.getAge(character, characterToFind), findViewById(R.id.alive5));
        this.updateText(GameManager.isAlive(character, characterToFind), findViewById(R.id.alive6));
        this.updateText(GameManager.getCrew(character, characterToFind), findViewById(R.id.alive7));

        if(GameManager.isSameName(character, characterToFind))
        {
            // TODO : Show POPUP WIN/LOOSE
        }

    }

    void updateText(String newText, TextView textView)
    {
        if(textView != null)
        {
            textView.setText(newText);
        }
    }

    void getSuggestions() {
        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, List<String>> task = new AsyncTask<Void, Void, List<String>>() {
            @Override
            protected List<String> doInBackground(Void... voids) {
                try {
                    return searchCharacters.getNamesAsync().get();
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            protected void onPostExecute(List<String> result) {
                suggestions = result;
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, suggestions);
                AutoCompleteTextView autoCompleteTextView = findViewById(R.id.EnterTextAutoCompleted);
                autoCompleteTextView.setAdapter(adapter);
            }
        };
        task.execute();
    }

}