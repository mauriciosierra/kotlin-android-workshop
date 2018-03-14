package todo.create;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import tokotlin.todo.R;

public class CreateToDoActivity extends AppCompatActivity implements CreateToDoView {

    private CreateToDoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_to_do);

        viewModel = ViewModelProviders.of(this).get(CreateToDoViewModel.class);
        viewModel.setView(this);

        setUpClickListener();
        setUpTextWatchers();
    }

    @Override
    public void setSaveEnabled(boolean enabled) {
        findViewById(R.id.create).setEnabled(enabled);
    }

    @Override
    public void onCreateSuccess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        });
    }

    private void setUpTextWatchers() {
        final EditText title = findViewById(R.id.title);
        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.onTitleChanged(s.toString());
            }
        });
        title.setText("");
    }

    private void setUpClickListener() {
        findViewById(R.id.create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClicked();
            }
        });
    }

    private void onSaveClicked() {
        final EditText title = findViewById(R.id.title);
        final EditText description = findViewById(R.id.description);
        viewModel.addToDo(title.getText().toString(), description.getText().toString());
    }

}
