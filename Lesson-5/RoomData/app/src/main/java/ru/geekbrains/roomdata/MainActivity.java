package ru.geekbrains.roomdata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private StudentSource studentSource;
    private StudentRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        initButtons();
    }

    private void initButtons() {
        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student(0, "Сидор", "Сидоров");
                studentSource.insert(student);
                adapter.notifyItemInserted(studentSource.getCount()-1);
            }
        });
        findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = studentSource.getStudentByPosition(0);
                studentSource.delete(student);
                adapter.notifyItemRemoved(0);
            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        studentSource = new StudentSource(getContentResolver());
        studentSource.query();
        adapter = new StudentRecyclerViewAdapter(studentSource);
        recyclerView.setAdapter(adapter);
    }
}
