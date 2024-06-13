package com.lenkasr.fetchapplication;

import static com.lenkasr.fetchapplication.HiringUtils.sortAndFilter;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    List<Hiring> itemList = null;
    private ViewAdapter adapter;
    RecyclerView recyclerView;
    FloatingActionButton floatingBtn;
    LinearLayout linearLayout;
    protected EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://fetch-hiring.s3.amazonaws.com/hiring.json";

        //Function to make the API call and get the JSON data
        getJsonData(url);

        floatingBtn = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingBtn.setAlpha(0.8f);

        floatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.smoothScrollToPosition(0);
            }
        });

        searchBar = (EditText) findViewById(R.id.search_bar);
//        searchBar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                searchBar.requestFocus();
//            }
//        });

        searchBar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyBoard(v);
                }
            }
        });

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });


    }

    private void getJsonData(String url) {
        // Make the API call
        NetworkClient.fetchJson(url, new NetworkClient.NetworkCallback() {
            @Override
            public void onSuccess(String json) {
                // Handle the JSON response
                Log.d(TAG, "JSON Response: " + json);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        parseJsonData(json);
                    }
                });

            }

            @Override
            public void onFailure(Exception e) {
                // Handle the failure
                Log.e(TAG, "API call failed", e);
            }
        });
    }

    private void parseJsonData(String jsonData) {
        try {
            Log.d(TAG, "parseJson");
            Type itemListType = new TypeToken<List<Hiring>>() {
            }.getType();
            itemList = new Gson().fromJson(jsonData, itemListType);
            //Calling Function to sort and filter the items from the json
            itemList = sortAndFilter(itemList);
            //Calling Function to set the adapter of the Recycler View
            setAdapter(itemList);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
        }

    }


    public void filter(String text) {
        List<Hiring> filteredList = new ArrayList<Hiring>();

        if (itemList != null) {
            for (Hiring item : itemList) {
                if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }

        if (filteredList.isEmpty()) {

            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
        }

        adapter.filterList(filteredList);

    }

    private void setAdapter(List<Hiring> sortedItems) {
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new ViewAdapter(sortedItems);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        if (searchBar.getText() != null && searchBar.getText().length() > 0) {
            searchBar.setText("");
        } else {
            super.onBackPressed();
        }
    }

    public ViewAdapter getAdapter() {
        return adapter;
    }

    public void setItemList(List<Hiring> items) {
        this.itemList = items;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    hideKeyBoard(v);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    private void hideKeyBoard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}