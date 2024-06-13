package com.lenkasr.fetchapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HiringUnitTest {

    @Test
    public void testEmptyList() {
        List<Hiring> items = new ArrayList<>();

        List<Hiring> finalItems = HiringUtils.sortAndFilter(items);

        assertTrue(finalItems.isEmpty());
    }

    @Test
    public void testSingleItem() {
        List<Hiring> items = new ArrayList<>();
        items.add(new Hiring(1, 1, "Item 1"));

        List<Hiring> sortedItems = HiringUtils.sortAndFilter(items);

        assertEquals(1, sortedItems.size());
        assertEquals(1, sortedItems.get(0).getListId());
        assertEquals("Item 1", sortedItems.get(0).getName());
        assertEquals(1, sortedItems.get(0).getId());
    }

    @Test
    public void testMultipleItems() {
        List<Hiring> items = new ArrayList<>();
        items.add(new Hiring(1, 2, ""));
        items.add(new Hiring(2, 1, "C"));
        items.add(new Hiring(3, 1, null));
        items.add(new Hiring(4, 1, "A"));
        items.add(new Hiring(5, 2, "B"));

        List<Hiring> sortedItems = HiringUtils.sortAndFilter(items);


        assertEquals(3, sortedItems.size());
        assertEquals(1, sortedItems.get(0).getListId());
        assertEquals("A", sortedItems.get(0).getName());
        assertEquals("C", sortedItems.get(1).getName());
        assertEquals(2, sortedItems.get(2).getListId());
    }


}


