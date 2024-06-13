package com.lenkasr.fetchapplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is a Util class to sort and filter the hiring items.
 */
public class HiringUtils {
    // Function sorts and filters and returns the updated list.
    public static List<Hiring> sortAndFilter(List<Hiring> items) {

        //Filter out any items where "name" is blank or null.
        List<Hiring> filteredItems = new ArrayList<Hiring>();
        if (items != null) {
            for (Hiring item : items) {
                if (item.getName() != null && !item.getName().isEmpty()) {
                    filteredItems.add(item);
                }
            }
        }
        //Display all the items grouped by "listId"
        Map<Integer, List<Hiring>> groupedItems = new HashMap<Integer, List<Hiring>>();
        for (Hiring item : filteredItems) {
            int listId = item.getListId();
            if (!groupedItems.containsKey(listId)) {
                groupedItems.put(listId, new ArrayList<Hiring>());
            }
            groupedItems.get(listId).add(item);
        }

        // Sort the groups by listId and then sort the items by name
        List<Hiring> sortedItems = new ArrayList<Hiring>();
        List<Integer> sortedKeys = new ArrayList<Integer>(groupedItems.keySet());
        Collections.sort(sortedKeys);
        for (int listId : sortedKeys) {
            List<Hiring> group = groupedItems.get(listId);
            if (group != null) {
                Collections.sort(group, new Comparator<Hiring>() {
                    @Override
                    public int compare(Hiring o1, Hiring o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
                sortedItems.addAll(group);
            }

        }

        return sortedItems;

    }
}
