package todo.list;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import todo.R;
import todo.model.ToDo;

public class ToDoListFragment extends Fragment {

    private ToDoListViewModel viewModel;

    public ToDoListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        final RecyclerView recyclerView = view.findViewById(R.id.todo_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ToDoRecyclerViewAdapter(Collections.<ToDo>emptyList()));

        viewModel = ViewModelProviders.of(this).get(ToDoListViewModel.class);
        viewModel.getToDoList().observe(this, observer);

        return view;
    }

    private Observer<List<ToDo>> observer = new Observer<List<ToDo>>() {
        @Override
        public void onChanged(@Nullable List<ToDo> toDos) {
            updateToDoItems((RecyclerView) getView().findViewById(R.id.todo_list), toDos);
        }
    };

    private void updateToDoItems(RecyclerView recyclerView, List<ToDo> toDoItems) {
        ((ToDoRecyclerViewAdapter) recyclerView.getAdapter()).setToDoList(toDoItems);
    }
}
