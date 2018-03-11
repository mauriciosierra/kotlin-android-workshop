package tokotlin.todo.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tokotlin.todo.R;
import tokotlin.todo.model.ToDo;

public class ToDoRecyclerViewAdapter extends RecyclerView.Adapter<ToDoRecyclerViewAdapter.ToDoViewHolder> {

    private List<ToDo> toDoList;

    public ToDoRecyclerViewAdapter(List<ToDo> todoList) {
        this.toDoList = todoList;
    }

    public void setToDoList(List<ToDo> toDoList) {
        this.toDoList = toDoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ToDoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        holder.bindToDoItem(toDoList.get(position));
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }

    static class ToDoViewHolder extends RecyclerView.ViewHolder {

        ToDoViewHolder(View itemView) {
            super(itemView);
        }

        void bindToDoItem(ToDo toDo) {
            ((TextView)itemView.findViewById(R.id.title)).setText(toDo.getTitle());
            ((TextView)itemView.findViewById(R.id.description)).setText(toDo.getDescription());
        }
    }
}
