package com.example.android.materialdesigncodelab.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.materialdesigncodelab.R;

/**
 * Created by JR HARI NANDHA on 21-12-2016.
 */

public class ListFragment extends Fragment {
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        recyclerView = (RecyclerView) inflater.inflate(R.layout.recyclerview,container,false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView dp;
        public TextView name;
        public TextView description;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_list, parent, false));
            dp = (ImageView) itemView.findViewById(R.id.list_avatar);
            name = (TextView) itemView.findViewById(R.id.list_title);
            description = (TextView) itemView.findViewById(R.id.list_desc);
        }
    }
    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 18;
        private final String[] games;
        private final String[] games_desc;
        private final Drawable[] gameAvators;
        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            games = resources.getStringArray(R.array.games);
            games_desc = resources.getStringArray(R.array.game_desc);
            TypedArray a = resources.obtainTypedArray(R.array.game_avatar);
            gameAvators = new Drawable[a.length()];
            for (int i = 0; i < gameAvators.length; i++) {
                gameAvators[i] = a.getDrawable(i);
            }
            a.recycle();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.dp.setImageDrawable(gameAvators[position % gameAvators.length]);
            holder.name.setText(games[position % games.length]);
            holder.description.setText(games_desc[position % games_desc.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}
