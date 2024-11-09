package com.example.chessleaderboardandsearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private List<Player> playerList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Player player);
    }

    public PlayerAdapter(List<Player> playerList, OnItemClickListener listener) {
        this.playerList = playerList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player player = playerList.get(position);
        holder.rankTextView.setText(String.valueOf(position + 1));
        holder.usernameTextView.setText(player.getUsername());
        holder.scoreTextView.setText(String.valueOf(player.getScore()));
        Glide.with(holder.avatarImageView.getContext())
                .load(player.getAvatar())
                .into(holder.avatarImageView);
        holder.bind(player, listener);
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    static class PlayerViewHolder extends RecyclerView.ViewHolder {
        TextView rankTextView;
        TextView usernameTextView;
        TextView scoreTextView;
        ImageView avatarImageView;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            rankTextView = itemView.findViewById(R.id.rankTextView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            scoreTextView = itemView.findViewById(R.id.scoreTextView);
            avatarImageView = itemView.findViewById(R.id.avatarImageView);
        }

        public void bind(final Player player, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(player);
                }
            });
        }
    }
}