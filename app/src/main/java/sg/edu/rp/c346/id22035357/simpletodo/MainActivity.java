package sg.edu.rp.c346.id22035357.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spnToDo;
    EditText etInput;
    Button btnAdd;
    Button btnDelete;
    Button btnClear;
    ListView lvToDo;

    ArrayList<String> todoList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spnToDo = findViewById(R.id.spnToDo);
        etInput = findViewById(R.id.etToDo);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnClear = findViewById(R.id.btnCLear);
        lvToDo = findViewById(R.id.lvToDo);

        ArrayAdapter aaToDo = new ArrayAdapter<> (this, android.R.layout.simple_list_item_1, todoList);
        lvToDo.setAdapter(aaToDo);

        spnToDo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        etInput.setHint(R.string.etInput);
                        etInput.setText("");
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        break;

                    case 1:
                        etInput.setHint(R.string.etRemove);
                        etInput.setText("");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        if(todoList.isEmpty()){
                            Toast.makeText(MainActivity.this, "You don't have any task to remove",Toast.LENGTH_LONG).show();
                            btnDelete.setEnabled(false);
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoList.add(etInput.getText().toString());
                aaToDo.notifyDataSetChanged();
                etInput.setText("");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
                if(etInput.getText().toString().matches("[a-zA-Z]") || etInput.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Please Input an index",Toast.LENGTH_LONG).show();
                    etInput.setText("");
                }else if(Integer.parseInt(etInput.getText().toString()) >= todoList.size() ){
                    Toast.makeText(MainActivity.this,"Wrong index number",Toast.LENGTH_LONG).show();
                    etInput.setText("");
                }else{
                    int position = Integer.parseInt(etInput.getText().toString());
                    todoList.remove(position);
                    aaToDo.notifyDataSetChanged();
                    etInput.setText("");
                    if (todoList.isEmpty()){
                        btnDelete.setEnabled(false);
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoList.clear();
                etInput.setText("");
                aaToDo.notifyDataSetChanged();
            }
        });
    }
}