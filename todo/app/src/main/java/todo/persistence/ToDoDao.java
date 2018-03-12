package todo.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import todo.model.ToDo;

@Dao
public interface ToDoDao {

    @Query("SELECT * FROM todo")
    LiveData<List<ToDo>> getAll();

    @Query("SELECT * FROM todo WHERE item_id IN (:id) LIMIT 1")
    ToDo findById(int id);

    @Insert
    void insertAll(ToDo... toDos);

    @Delete
    void delete(ToDo toDo);

    @Update
    void update(ToDo toDo);
}
