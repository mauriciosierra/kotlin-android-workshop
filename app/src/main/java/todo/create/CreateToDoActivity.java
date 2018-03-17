package todo.create;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import todo.R;

public class CreateToDoActivity extends AppCompatActivity implements CreateToDoView {

    private CreateToDoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_to_do);

        viewModel = ViewModelProviders.of(this).get(CreateToDoViewModel.class);
        viewModel.setView(this);

        setUpTitleTextWatcher();
        setUpSaveClickListener();
        setUpDueDateControl();
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

    private void setUpTitleTextWatcher() {
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

    private void setUpSaveClickListener() {
        findViewById(R.id.create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClicked();
            }
        });
    }

    private void setUpDueDateControl() {
        findViewById(R.id.due_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(viewModel.getDueDate());
                DatePickerDialog datePicker = new DatePickerDialog(CreateToDoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, dayOfMonth);
                        viewModel.setDueDate(calendar.getTime());
                        ((EditText)findViewById(R.id.due_date)).setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                datePicker.show();
            }
        });
    }

    private void onSaveClicked() {
        final EditText title = findViewById(R.id.title);
        final EditText description = findViewById(R.id.description);
        final Spinner categorySpinner = findViewById(R.id.categories);
        final String category = categorySpinner.getSelectedItem().toString();
        viewModel.addToDo(title.getText().toString(), description.getText().toString(), category);
    }

}
