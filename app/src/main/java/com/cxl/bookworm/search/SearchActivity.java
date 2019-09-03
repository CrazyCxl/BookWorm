package com.cxl.bookworm.search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.cxl.bookbase.Book;
import com.cxl.bookworm.R;

public class SearchActivity extends AppCompatActivity implements BooksFragment.OnBooksFragmentInteractionListener {

    private final String LOG_TAG = "Search Activity Log:";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // TODO do something
                    Log.i(LOG_TAG,v.getText().toString());
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.search_page,new BooksFragment())
                            .commit();
                    handled = true;
                }
                return handled;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                finish();
                return true;
        default:
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBooksSelect(Book book) {
        Log.i(LOG_TAG,"onBooksSelect "+book.getName());
    }
}
