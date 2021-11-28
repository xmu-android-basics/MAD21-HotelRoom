package cn.windwood.apps.roompicker.list;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import cn.windwood.apps.roompicker.data.RoomItem;

public class RoomListAdapter extends ListAdapter<RoomItem, RoomViewHolder> {

    public static final DiffUtil.ItemCallback<RoomItem> DIFF_CALLBACK =
        new DiffUtil.ItemCallback<RoomItem>() {

            @Override
            public boolean areItemsTheSame(@NonNull RoomItem oldItem, @NonNull RoomItem newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull RoomItem oldItem, @NonNull RoomItem newItem) {
                return oldItem.equals(newItem);
            }
        };

    public RoomListAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return RoomViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

}
