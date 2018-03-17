package todo.create;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.Date;

import todo.model.ToDo;
import todo.persistence.ToDoDatabase;

public class CreateToDoViewModel extends AndroidViewModel {

    final private ToDoDatabase database;
    private CreateToDoView view;
    private Date dueDate;

    public CreateToDoViewModel(@NonNull Application application) {
        super(application);

        dueDate = new Date();
        database = ToDoDatabase.getDatabase(application);
    }

    public void setView(CreateToDoView view) {
        this.view = view;
    }

    public void onTitleChanged(String title) {
        if (view != null) {
            view.setSaveEnabled(!title.isEmpty());
        }
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void addToDo(final String title, final String description, final String category) {
        final ToDo newToDo = new ToDo();
        newToDo.setTitle(title);
        newToDo.setDescription(description);
        newToDo.setCreatedAt(new Date());
        newToDo.setDueBy(dueDate);
        newToDo.setCategory(category);
        newToDo.setCompleted(false);

        // Prevent multiple calls to create
        view.setSaveEnabled(false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                database.toDoDao().insertAll(newToDo);
                view.onCreateSuccess();
            }
        }).start();
    }

}
