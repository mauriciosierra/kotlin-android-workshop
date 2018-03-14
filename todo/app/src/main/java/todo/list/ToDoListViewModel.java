package todo.list;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import todo.model.ToDo;
import todo.persistence.ToDoDatabase;

public class ToDoListViewModel extends AndroidViewModel {

    private final LiveData<List<ToDo>> toDoList;

    private ToDoDatabase database;

    public ToDoListViewModel(Application application) {
        super(application);

        database = ToDoDatabase.getDatabase(application);
        toDoList = database.toDoDao().getAll();
    }

    public LiveData<List<ToDo>> getToDoList() {
        return toDoList;
    }
}
