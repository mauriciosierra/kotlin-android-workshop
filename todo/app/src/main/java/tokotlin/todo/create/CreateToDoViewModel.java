package tokotlin.todo.create;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import tokotlin.todo.model.ToDo;
import tokotlin.todo.persistence.ToDoDatabase;

public class CreateToDoViewModel extends AndroidViewModel {

    final private ToDoDatabase database;
    private CreateToDoView view;

    public CreateToDoViewModel(@NonNull Application application) {
        super(application);

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

    public void addToDo(final String title, final String description) {
        final ToDo newToDo = new ToDo();
        newToDo.setTitle(title);
        newToDo.setDescription(description);

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
