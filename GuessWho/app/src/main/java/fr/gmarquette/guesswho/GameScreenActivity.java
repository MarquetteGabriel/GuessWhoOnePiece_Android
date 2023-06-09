package fr.gmarquette.guesswho;

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
import fr.gmarquette.guesswho.datas.Characters;
import fr.gmarquette.guesswho.datas.DataBase;
import fr.gmarquette.guesswho.datas.DataBaseSingleton;
import fr.gmarquette.guesswho.datas.SearchCharacters;
import fr.gmarquette.guesswho.game.GameManager;
import fr.gmarquette.guesswho.gui.FirstAnswerFirstColumn;
import fr.gmarquette.guesswho.gui.GameScreenViewModel;

public class GameScreenActivity extends AppCompatActivity {

    private GameScreenBinding binding;
    private GameScreenViewModel gameScreenViewModel;
    private SearchCharacters searchCharacters;
    List<String> suggestions;
    Characters test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.game_screen);
        binding.setViewmodel(this.gameScreenViewModel);
        binding.setLifecycleOwner(this);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        DataBaseSingleton.getInstance().getDataBase(this).CreateDatabase();
        searchCharacters = new SearchCharacters(this);
        this.getSuggestions();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.EnterTextAutoCompleted);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, suggestions);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener((adapterView, view, position, id) -> {
            String selectedValue = autoCompleteTextView.getAdapter().getItem(position).toString();
            autoCompleteTextView.setText(selectedValue);
            updateFragments(selectedValue);
        });

    }

    public void updateFragments(String value)
    {
        FirstAnswerFirstColumn fragments = (FirstAnswerFirstColumn) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);
        assert fragments != null;
        fragments.updateText(value);
        Characters character;
        try {
            character = (Characters) searchCharacters.getCharacterAsync(value).get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Met a jour le fragment 1
        //this.updateText(GameManager.hasEatenDevilFruit(character, test));
        this.updateText(GameManager.whatBounty(character, test), findViewById(R.id.textView));
        //this.updateText(GameManager.getAppearanceDiff(character, test));
        GameManager.getType(character, test);
        GameManager.getAge(character, test);
        GameManager.getAge(character, test);
        GameManager.getCrew(character, test);

        if(GameManager.isSameName(character, test))
        {

        }

    }

    void getSuggestions()
    {
        try {
            suggestions = searchCharacters.getNamesAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void updateText(String newText, TextView textView)
    {
        if(textView != null)
        {
            textView.setText(newText);
        }
    }

}