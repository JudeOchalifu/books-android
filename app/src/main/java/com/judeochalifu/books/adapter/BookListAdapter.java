package com.judeochalifu.books.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.judeochalifu.books.R;
import com.judeochalifu.books.activity.BookViewActivity;
import com.judeochalifu.books.model.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {

  private List<Book> bookList;
  private Context context;

  public BookListAdapter(List<Book> bookList, Context context) {
    this.bookList = bookList;
    this.context = context;
  }


  public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView title, subtitle, isbn13, price;
    private ImageView thumbnail;

    public BookViewHolder(View view) {
      super(view);
      title = (TextView) view.findViewById(R.id.title);
      isbn13 = (TextView) view.findViewById(R.id.isbn13);
      subtitle = (TextView) view.findViewById(R.id.subtitle);
      price = (TextView) view.findViewById(R.id.price);
      thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
      view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      Intent navigationIntent = new Intent(context, BookViewActivity.class);
      Book book = bookList.get(getAdapterPosition());
      navigationIntent.putExtra("selectedBook", book);
      context.startActivity(navigationIntent);
    }
  }

  @NonNull
  @Override
  public BookListAdapter.BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.book_list_item, parent, false);

    return new BookViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull BookListAdapter.BookViewHolder holder, int position) {
    Book book = bookList.get(position);
    holder.title.setText(book.getTitle());
    if (book.getSubtitle().length() == 0) {
      holder.subtitle.setText("-");
    } else {
      holder.subtitle.setText(book.getSubtitle());
    }

    holder.isbn13.setText(bookList.get(position).getIsbn13());
    holder.price.setText(bookList.get(position).getPrice());
    Picasso.get().load(bookList.get(position).getImage()).into(holder.thumbnail);

  }

  @Override
  public int getItemCount() {
    return bookList.size();
  }
}
