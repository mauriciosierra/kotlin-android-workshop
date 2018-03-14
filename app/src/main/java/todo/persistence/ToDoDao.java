package todo.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import todo.model.ToDo;

@Dao
public interface ToDoDao {

    @Query("SELECT * FROM todo")
    LiveData<List<ToDo>> getAll();

    @Insert
    void insertAll(ToDo... toDos);

    @Delete
    void delete(ToDo toDo);
}
