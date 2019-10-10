package com.judeochalifu.books.ui.home;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.judeochalifu.books.model.Book;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {


    private MutableLiveData<List<Book>> books;
    private static final String TAG = HomeViewModel.class.getSimpleName();
    private Context applicationContext;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.applicationContext = application.getApplicationContext();
        getBooks();
    }



    public LiveData<List<Book>> getBooks() {
        if (books == null) {
            books = new MutableLiveData<List<Book>>();
            getListOfBooks();
        }
        return books;
    }

    private void getListOfBooks() {

        RequestQueue queue = Volley.newRequestQueue(applicationContext);
        String url = "http://192.168.1.138:8079/books/all";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray booksJsonArray = new JSONArray(response);
                            Log.e(TAG, "Length of array: " + booksJsonArray.length());
                            List<Book> bookList = new ArrayList<>();
                            for (int i = 0; i < booksJsonArray.length(); i++) {
                                JSONObject bookJsonObj = booksJsonArray.getJSONObject(i);
                                Book book = new Book();
                                book.setTitle(bookJsonObj.getString("title"));
                                book.setSubtitle(bookJsonObj.getString("subtitle"));
                                book.setIsbn13(bookJsonObj.getString("isbn13"));
                                book.setPrice(bookJsonObj.getString("price"));
                                book.setImage(bookJsonObj.getString("image"));
                                book.setUrl(bookJsonObj.getString("url"));

                                bookList.add(book);
                                books.setValue(bookList);
                            }
                        } catch (Exception exception) {
                            Log.e(TAG, exception.getMessage());
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "An error occurred while retrieving data: cause \n" + error.getMessage());
                Log.e(TAG, "An error occurred while retrieving data: cause \n" + error.getCause());
                Log.e(TAG, "An error occurred while retrieving data: cause \n" + error.getLocalizedMessage());
            }
        });


        queue.add(stringRequest);

    }

}