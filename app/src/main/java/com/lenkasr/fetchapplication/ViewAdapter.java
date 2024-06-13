package com.lenkasr.fetchapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter class for the Recycler View to display the list of items
 */
public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> {
    private static final String TAG = ViewAdapter.class.getName();
    private List<Hiring> data;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout layout;
        private final LinearLayout innerLayout;
        private final TextView id;
        private final TextView listId;
        private final TextView name;


        public ViewHolder(View view) {
            super(view);
            layout = (LinearLayout) view.findViewById(R.id.layout);
            innerLayout = (LinearLayout) view.findViewById(R.id.innerLayout);
            id = (TextView) view.findViewById(R.id.fid);
            listId = (TextView) view.findViewById(R.id.listId);
            name = (TextView) view.findViewById(R.id.name);
        }

        public LinearLayout getLayout() {
            return layout;
        }

        public TextView getId() {
            return id;
        }

        public TextView getListID() {
            return listId;
        }

        public TextView getName() {
            return name;
        }
    }


    public ViewAdapter(List<Hiring> dataSet) {
        data = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        if (data.get(position).getName() != null && !data.get(position).getName().isEmpty()) {
            //setting visibility
            viewHolder.layout.setVisibility(View.VISIBLE);
            viewHolder.innerLayout.setVisibility(View.VISIBLE);

            viewHolder.id.setVisibility(View.VISIBLE);
            viewHolder.listId.setVisibility(View.VISIBLE);
            viewHolder.name.setVisibility(View.VISIBLE);
            //setting data
            viewHolder.getId().setText(String.valueOf(data.get(position).getId()));
            viewHolder.getListID().setText(String.valueOf(data.get(position).getListId()));
            viewHolder.getName().setText(data.get(position).getName());
        } else {
            //making the layout GONE incase there's no name for the item
            viewHolder.layout.setVisibility(View.GONE);
            viewHolder.innerLayout.setVisibility(View.GONE);

            viewHolder.id.setVisibility(View.GONE);
            viewHolder.listId.setVisibility(View.GONE);
            viewHolder.name.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public void filterList(List<Hiring> filterList) {
        //updating the data in adapter
        data = filterList;
        notifyDataSetChanged();
    }

    public List<Hiring> getData() {
        return data;
    }
}

