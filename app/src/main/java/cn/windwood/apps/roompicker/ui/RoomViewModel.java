package cn.windwood.apps.roompicker.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import cn.windwood.apps.roompicker.data.RoomItem;
import cn.windwood.apps.roompicker.data.RoomRepository;

public class RoomViewModel extends AndroidViewModel {

    private final RoomRepository roomRepository;

    private final LiveData<List<RoomItem>> allRooms;

    public RoomViewModel(@NonNull Application application) {
        super(application);
        roomRepository = new RoomRepository(application);
        allRooms = roomRepository.getAllRooms();
    }

    public LiveData<List<RoomItem>> getAllRooms() {
        return allRooms;
    }

    public void add(String roomNumber) {
        roomRepository.add(new RoomItem(roomNumber));
    }
}