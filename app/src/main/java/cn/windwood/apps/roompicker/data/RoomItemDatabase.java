package cn.windwood.apps.roompicker.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {RoomItem.class}, version = 1, exportSchema = false)
abstract class RoomItemDatabase extends RoomDatabase {

    public abstract RoomDao roomDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile RoomItemDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
        Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static RoomItemDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomItemDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                        Room.databaseBuilder(context.getApplicationContext(),
                                             RoomItemDatabase.class, "rooms")
//                        .addCallback()
                            .build();
                }
            }
        }
        return INSTANCE;

    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                RoomDao dao = INSTANCE.roomDao();
                dao.deleteAll();
            });
        }
    };
}
