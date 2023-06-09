package fr.gmarquette.guesswho;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

import fr.gmarquette.guesswho.databinding.GameScreenBinding;
import fr.gmarquette.guesswho.datas.Characters;
import fr.gmarquette.guesswho.datas.DataBase;
import fr.gmarquette.guesswho.datas.SearchCharacters;
import fr.gmarquette.guesswho.gui.FirstAnswerFirstColumn;
import fr.gmarquette.guesswho.gui.GameScreenViewModel;

public class GameScreenActivity extends AppCompatActivity {

    private GameScreenBinding binding;
    private GameScreenViewModel gameScreenViewModel;
    private SearchCharacters searchCharacters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.game_screen);
        binding.setViewmodel(this.gameScreenViewModel);
        binding.setLifecycleOwner(this);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        DataBase.getInstance(this).CreateDatabase();
        searchCharacters = new SearchCharacters(this);


        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.EnterTextAutoCompleted);

        new AsyncTask<Void, Void, List<String>>() {
            @Override
            protected List<String> doInBackground(Void... voids) {
                return DataBase.getInstance(getApplicationContext()).dao().getNames();
            }

            @Override
            protected void onPostExecute(List<String> names) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, DataBase.getNames());
                autoCompleteTextView.setAdapter(adapter);
            }
        }.execute();

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
    }

}