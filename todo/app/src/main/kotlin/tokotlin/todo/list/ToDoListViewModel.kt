package tokotlin.todo.list

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel

import tokotlin.todo.model.ToDo
import tokotlin.todo.persistence.ToDoDatabase

class ToDoListViewModel(application: Application) : AndroidViewModel(application) {

    val toDoList: LiveData<List<ToDo>>

    private val database: ToDoDatabase = ToDoDatabase.getDatabase(application)

    init {
        toDoList = database.toDoDao().all
    }
}
