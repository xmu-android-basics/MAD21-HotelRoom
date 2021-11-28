package cn.windwood.apps.roompicker.data;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

import cn.windwood.apps.roompicker.data.RoomItem;
import cn.windwood.apps.roompicker.data.RoomItemDatabase;

public class RoomRepository {
    private RoomDao roomDao;

    public RoomRepository(Application application) {
        RoomItemDatabase db = RoomItemDatabase.getDatabase(application);
        roomDao = db.roomDao();
    }

    public LiveData<List<RoomItem>> getAllRooms() {
        return roomDao.getRooms();
    }

    public void add(RoomItem roomItem) {
        RoomItemDatabase.databaseWriteExecutor.execute(() -> {
            roomDao.insert(roomItem);
        });
    }
}
