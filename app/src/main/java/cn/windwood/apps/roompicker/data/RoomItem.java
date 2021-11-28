package cn.windwood.apps.roompicker.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "rooms")
public class RoomItem {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "number")
    public final String number;

    @Ignore
    public final Integer floor;

    public RoomItem(String number) {
        this.number = number;
        this.floor = 3;
    }

    @Override
    public String toString() {
        return number;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof RoomItem) {
            return this.number.equals(((RoomItem) obj).number);
        } else if (obj instanceof String) {
            return this.number.equals((String) obj);
        } else {
            return false;
        }
    }

}
