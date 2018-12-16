package com.spitslide.recommendationapp;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SuggestionsAdapter extends RecyclerView.Adapter<SuggestionsAdapter.SuggestionsViewHolder> {

    private final List<String> data;

    public SuggestionsAdapter(List<String> data) {
        this.data = data;
    }

    public class SuggestionsViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public SuggestionsViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.text_view);
        }
    }

    @NonNull
    @Override
    public SuggestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new SuggestionsViewHolder(layoutInflater.inflate(R.layout.suggestions_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestionsViewHolder suggestionsViewHolder, int position) {
        suggestionsViewHolder.textView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
