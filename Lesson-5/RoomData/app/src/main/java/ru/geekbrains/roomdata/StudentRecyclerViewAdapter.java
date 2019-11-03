package ru.geekbrains.roomdata;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentRecyclerViewAdapter extends RecyclerView.Adapter<StudentRecyclerViewAdapter.ViewHolder> {

    private StudentSource studentSource;

    public StudentRecyclerViewAdapter(StudentSource studentSource){
        this.studentSource = studentSource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView textView = new TextView(parent.getContext());
        textView.setTextSize(30);
        return new ViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = studentSource.getStudentByPosition(position);
        String name = student.getFirstName() + " " + student.getLastName();
        holder.setText(name);
    }

    @Override
    public int getItemCount() {
        return studentSource.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        public ViewHolder(@NonNull TextView itemView) {
            super(itemView);
            textView = itemView;
        }

        public void setText(String text){
            textView.setText(text);
        }
    }
}
