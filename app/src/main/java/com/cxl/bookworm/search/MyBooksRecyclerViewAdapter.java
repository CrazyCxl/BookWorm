package com.cxl.bookworm.search;

import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cxl.bookbase.Book;
import com.cxl.bookworm.R;
import com.cxl.bookworm.search.BooksFragment.OnBooksFragmentInteractionListener;
import com.cxl.manager.web.WebVisiter;
import com.cxl.webbase.Website;

import java.util.ArrayList;


/**
 * {@link RecyclerView.Adapter} that can display a {@link Book} and makes a call to the
 * specified {@link OnBooksFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyBooksRecyclerViewAdapter extends RecyclerView.Adapter<MyBooksRecyclerViewAdapter.ViewHolder> implements Website.WebsiteListener {

    private ArrayList<Book> mBooks;
    private final OnBooksFragmentInteractionListener mListener;
    private WebVisiter webVisiter;
    private Handler handler;

    private final int UPDATE_ITEM = 0;

    public MyBooksRecyclerViewAdapter(String name, OnBooksFragmentInteractionListener listener) {
        webVisiter = new WebVisiter(this);
        //mBooks = items;
        mListener = listener;
        mBooks = new ArrayList<>();
        webVisiter.searchBooks(name);
        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message inputMessage) {
                Book book = (Book) inputMessage.obj;
                switch (inputMessage.what) {
                    case UPDATE_ITEM:
                        notifyItemInserted(mBooks.size() - 1);
                        break;
                    default:
                        super.handleMessage(inputMessage);
                }
            }
        };
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_books, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mBooks.get(position);
        holder.mNameView.setText(mBooks.get(position).getName());
        holder.mContentView.setText(mBooks.get(position).getWebsit().getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onBooksSelect(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    @Override
    public void onBookSearched(Book book) {
        mBooks.add(book);
        Message completeMessage = handler.obtainMessage(UPDATE_ITEM, book);
        completeMessage.sendToTarget();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final TextView mContentView;
        public Book mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.item_name);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
