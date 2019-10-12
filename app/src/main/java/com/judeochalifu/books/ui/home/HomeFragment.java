package com.judeochalifu.books.ui.home;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.judeochalifu.books.R;
import com.judeochalifu.books.adapter.BookListAdapter;
import com.judeochalifu.books.model.Book;

import java.util.List;

public class HomeFragment extends Fragment {

  private HomeViewModel homeViewModel;
  private RecyclerView booksRecyclerView;
  private BookListAdapter bookListAdapter;
  private SwipeRefreshLayout swipeRefreshLayout;


  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    homeViewModel =
      ViewModelProviders.of(this).get(HomeViewModel.class);
    View root = inflater.inflate(R.layout.fragment_home, container, false);
    booksRecyclerView = root.findViewById(R.id.bookListRecyclerView);

    swipeRefreshLayout = root.findViewById(R.id.swipeRefreshLayout);
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        if (homeViewModel.getBooks().hasActiveObservers()) {
          swipeRefreshLayout.setRefreshing(false);
        }
      }
    });
    getBooks();
    return root;
  }

  private void getBooks() {
    swipeRefreshLayout.setRefreshing(true);
    homeViewModel.getBooks().observe(this, new Observer<List<Book>>() {
      @Override
      public void onChanged(@Nullable List<Book> bookList) {
        swipeRefreshLayout.setRefreshing(false);
        buildRecyclerView(bookList);

      }
    });
  }

  private void buildRecyclerView(List<Book> bookList) {
    bookListAdapter = new BookListAdapter(bookList, getActivity());
    if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
      booksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    } else {
      booksRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));

    }
    booksRecyclerView.setItemAnimator(new DefaultItemAnimator());
    booksRecyclerView.setAdapter(bookListAdapter);
    bookListAdapter.notifyDataSetChanged();


  }



  @Override
  public void onStop() {
    super.onStop();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    if (homeViewModel != null) {
      homeViewModel.onCleared();
    }
  }
}
