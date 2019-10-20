package com.judeochalifu.books.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.judeochalifu.books.MainActivity;
import com.judeochalifu.books.R;
import com.judeochalifu.books.model.Book;
import com.judeochalifu.books.ui.home.HomeViewModel;

import java.util.List;

public class BookViewActivity extends BaseActivity {

  private HomeViewModel homeViewModel;
  private Book currentBook;
  private int itemPosition;
  private static final String TAG = BookViewActivity.class.getSimpleName();


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_book_view);

    Intent intent = getIntent();
    currentBook = (Book) intent.getSerializableExtra("selectedBook");
    Log.d(TAG, "TTTTTTTTTTTTTT" + currentBook.getTitle());


    drawView();

  }

  public void drawView(){



  }
}
