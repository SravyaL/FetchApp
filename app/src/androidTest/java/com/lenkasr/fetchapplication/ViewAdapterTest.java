package com.lenkasr.fetchapplication;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ViewAdapterTest {

    private ViewAdapter adapter;
    private List<Hiring> sampleData;
    private Context context;

    @Before
    public void setUp() {
        sampleData = new ArrayList<>();
        context = ApplicationProvider.getApplicationContext();
        sampleData.add(new Hiring(1, 1, "Item 1"));
        sampleData.add(new Hiring(2, 1, "Item 2"));
        sampleData.add(new Hiring(3, 2, "Item A"));
        sampleData.add(new Hiring(4, 2, "Item B"));
        sampleData.add(new Hiring(5, 3, ""));  // Empty name

        adapter = new ViewAdapter(sampleData);
    }

    @Test
    public void testGetItemCount() {
        assertEquals(5, adapter.getItemCount());
    }

    @Test
    public void testOnBindViewHolder() {
        ViewGroup parent = new LinearLayout(context);
        ViewAdapter.ViewHolder viewHolder = adapter.onCreateViewHolder(parent, 0);

        adapter.onBindViewHolder(viewHolder, 0);
        assertEquals(View.VISIBLE, viewHolder.getLayout().getVisibility());
        assertEquals("Item 1", viewHolder.getName().getText().toString());
        assertEquals("1", viewHolder.getId().getText().toString());
        assertEquals("1", viewHolder.getListID().getText().toString());
        adapter.onBindViewHolder(viewHolder, 4);
        assertEquals(View.GONE, viewHolder.getLayout().getVisibility());
    }

    @Test
    public void testFilterList() {
        List<Hiring> filteredList = new ArrayList<>();
        filteredList.add(new Hiring(1, 1, "Item 1"));

        adapter.filterList(filteredList);
        assertEquals(1, adapter.getItemCount());
    }
}

