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
import com.cxl.bookworm.SearchFragment;

public class SearchActivity extends AppCompatActivity
        implements BooksFragment.OnBooksFragmentInteractionListener , SearchFragment.OnSearchFragmentInteractionListener {

    private final String LOG_TAG = "Search Activity Log:";
    private SearchFragment searchFragment;
    private BooksFragment booksFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if(searchFragment == null){
            searchFragment = new SearchFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .add(R.id.search_page,searchFragment)
                .commit();
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

    @Override
    public void onSearchKeyInput(String name) {
        Log.i(LOG_TAG,"onSearchKeyInput "+name);
        if(booksFragment == null){
            booksFragment = new BooksFragment();
        }
        if(searchFragment.isAdded()){
            getSupportFragmentManager().beginTransaction().hide(searchFragment).commit();
        }
        getSupportFragmentManager().beginTransaction()
                .add(R.id.search_page,booksFragment)
                .commit();
    }
}
