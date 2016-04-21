package com.shredder.shellproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shredder.shellproject.R;
import com.shredder.shellproject.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookDetailsFragment extends BaseFragment {

    private static final String BOOKING_TITLE = "BOOKING_TITLE";

    private String title;

    @Bind(R.id.book_details_text)
    TextView textView;

    public static BookDetailsFragment newInstance(String title) {
        BookDetailsFragment newFragment = new BookDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(BOOKING_TITLE, title);
        newFragment.setArguments(args);
        return newFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        title = (String) args.getSerializable(BOOKING_TITLE);
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Must supply a book title");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_details, container, false);
        ButterKnife.bind(this, view);
        textView.setText(title + " is a great book because of the blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah ");
        return view;
    }

    @Override
    protected String getTitle() {
        return title;
    }

    @OnClick(R.id.bool_details_button)
    public void onClick() {
        showRandom();
    }

    private void showRandom() {
        String[] titles = getResources().getStringArray(R.array.book_titles);
        long randomIndex = System.currentTimeMillis() % (long) (titles.length - 1);
        String newTitle = titles[(int) randomIndex];

        if(newTitle.equalsIgnoreCase(title)){
            showRandom();
            return;
        }

        add(BookDetailsFragment.newInstance(newTitle));
    }
}
