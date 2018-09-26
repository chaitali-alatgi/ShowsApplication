package com.example.chaitali.showsapplication.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chaitali.showsapplication.R;
import com.example.chaitali.showsapplication.model.Show;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowsAdapter extends RecyclerView.Adapter<ShowsAdapter.ViewHolder> {

    private List<Show> showList;
    Context mContext;

    public ShowsAdapter(Context context) {
        this.mContext = context;
        showList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item,viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Show show = showList.get(i);
        Picasso.with(mContext).load(show.getImage().getMedium()).into(viewHolder.posterView);
        viewHolder.name.setText(show.getName());
        viewHolder.description.setText(""+show.getRuntime());
    }

    @Override
    public int getItemCount() {
        return showList.size();
    }

    public void setShowList(List<Show> showList) {
        this.showList.clear();
        this.showList.addAll(showList);
        notifyDataSetChanged();
    }

    public void setShowChanged(Show showChanged, int position) {
        showList.add(position, showChanged);
        notifyItemInserted(position);
        notifyItemChanged(showChanged.getId());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.poster)
        ImageView posterView;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.description)
        TextView description;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
