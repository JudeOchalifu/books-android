package com.judeochalifu.books.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class BaseActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d("TAG", "BaseActivity called=================================>");
  }

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    Log.d("TAG", "Attach base called====================================>");
  }
}
