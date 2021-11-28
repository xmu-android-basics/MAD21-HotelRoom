package cn.windwood.apps.roompicker.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cn.windwood.apps.roompicker.R;
import cn.windwood.apps.roompicker.data.RoomItem;

public class RoomViewHolder extends RecyclerView.ViewHolder {
    public final TextView numberView;
    public final TextView floorView;

    public RoomViewHolder(@NonNull View itemView) {
        super(itemView);
        numberView = itemView.findViewById(R.id.room_number);
        floorView = itemView.findViewById(R.id.room_floor);
    }

    public void bindTo(RoomItem roomItem) {
        numberView.setText(roomItem.number);
        floorView.setText(String.valueOf(roomItem.floor));
    }

    static RoomViewHolder create(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.recyclerview_item,
                                           parent,
                                           false);

        return new RoomViewHolder(view);

    }
}
