package tokotlin.todo.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import tokotlin.todo.model.ToDo;

@Database(entities = {ToDo.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class ToDoDatabase extends RoomDatabase {

    private static ToDoDatabase INSTANCE;

    public static ToDoDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ToDoDatabase.class, "todo_db").build();
        }
        return INSTANCE;
    }

    public abstract ToDoDao toDoDao();
}
