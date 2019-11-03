package ru.geekbrains.room;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.geekbrains.room.model.Email;

public class EmailRecyclerAdapter extends RecyclerView.Adapter<EmailRecyclerAdapter.ViewHolder> {

    private List<Email> emails;

    public EmailRecyclerAdapter(List<Email> emails){
        this.emails = emails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView textEmail = new TextView(parent.getContext());
        textEmail.setTextSize(20);
        return new ViewHolder(textEmail);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textEmail.setText(emails.get(position).email);
    }

    @Override
    public int getItemCount() {
        return emails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textEmail = (TextView) itemView;
        }
    }
}
