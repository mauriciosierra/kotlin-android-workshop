package tokotlin.todo.details

interface ToDoDetailsView {
    fun displayTitle(title: String)
    fun displayDescription(description: String)
    fun onMarkedAsDone()
}