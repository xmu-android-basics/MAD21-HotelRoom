package cn.windwood.apps.roompicker.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RoomDao {

    @Query("SELECT * FROM rooms")
    LiveData<List<RoomItem>> getRooms();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(RoomItem roomItem);

    @Query("DELETE FROM rooms")
    void deleteAll();
}
