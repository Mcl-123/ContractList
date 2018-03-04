package com.jackie.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    private List<String> items;

    public ListAdapter(List<String> items) {
        this.items = items;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ListViewHolder holder = new ListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.textView.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ListViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.item);
        }
    }
}
