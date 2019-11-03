package ru.geekbrains.room;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.geekbrains.room.model.StudentEmail;

public class StudentCardDialogFragment extends DialogFragment {

    private final static String STUDENT_CARD = "STUDENT_CARD";

    public static StudentCardDialogFragment create(StudentEmail student){
        StudentCardDialogFragment fragment = new StudentCardDialogFragment();

        Bundle args = new Bundle();
        args.putSerializable(STUDENT_CARD, student);
        fragment.setArguments(args);
        return fragment;
    }

    private StudentEmail getStudent(){
        return (StudentEmail) getArguments().getSerializable(STUDENT_CARD);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_card_dialog, null);

        StudentEmail student = getStudent();
        initViews(view, student);
        initRecycler(view, student);

        return view;
    }

    private void initRecycler(View view, StudentEmail student) {
        RecyclerView recyclerView = view.findViewById(R.id.studentRecycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        EmailRecyclerAdapter adapter = new EmailRecyclerAdapter(student.emails);
        recyclerView.setAdapter(adapter);
    }

    private void initViews(View view, StudentEmail student) {
        TextView textFirstName = view.findViewById(R.id.textStudentFirstName);
        TextView textLastName = view.findViewById(R.id.textStudentLastName);
        TextView textBirthDay = view.findViewById(R.id.textStudentBirthday);
        TextView textCity = view.findViewById(R.id.textStudentCity);

        textFirstName.setText(student.student.firstName);
        textLastName.setText(student.student.lastName);
        textBirthDay.setText(student.student.dateBirth.toString());
        textCity.setText(student.student.address.city);
    }
}
