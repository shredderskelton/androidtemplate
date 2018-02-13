package com.shredder.shellproject.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.shredder.shellproject.R;
import com.shredder.shellproject.base.BackButtonSupportFragment;
import com.shredder.shellproject.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class BookListFragment extends BaseFragment implements BackButtonSupportFragment {

    @BindView(R.id.book_list_view)
    ListView listView;

    private String[] titlesArray;
    private boolean consumingBackPress = true;
    private Toast toast;

    public static BookListFragment newInstance() {
        return new BookListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_list, container, false);
        ButterKnife.bind(this, view);

        titlesArray = getResources().getStringArray(R.array.book_titles);
        ArrayAdapter<String> simpleAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, titlesArray);
        listView.setAdapter(simpleAdapter);
        return view;
    }

    @OnItemClick(R.id.book_list_view)
    public void onListItemSelected(android.widget.AdapterView<?> adapterView, android.view.View view, int index, long id) {
        add(BookDetailsFragment.newInstance(titlesArray[index]));
    }

    @Override
    protected String getTitle() {
        return "Choose a book";
    }

    @Override
    public boolean onBackPressed() {
        //return true when handled by yourself
        if (consumingBackPress) {
            //This is actually a terrible thing to do and totally against the guidelines
            // Ideally you shouldn't handle the backpress ever, so really think twice about what
            // you are doing and whether you are getting hacky
            toast = Toast.makeText(getActivity(), "Press back once more to quit the application", Toast.LENGTH_LONG);
            toast.show();
            consumingBackPress = false;
            return true; //consumed
        }
        toast.cancel();
        return false; //delegated
    }
}
