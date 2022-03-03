package com.gmail.illobikol.htcemployerslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployerAdapter  extends RecyclerView.Adapter<EmployerAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Employer> employers;

    EmployerAdapter(Context context, List<Employer> states) {
        this.employers = states;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public EmployerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployerAdapter.ViewHolder holder, int position) {
        Employer employer = employers.get(position);
        holder.phone_numberView.setText(employer.getPhone_number());
        holder.nameView.setText(employer.getName());
        holder.skillsView.setText(employer.getSkills().replaceAll("\"|^\\[|\\]$", ""));
    }

    @Override
    public int getItemCount() {
        return employers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView, phone_numberView, skillsView;
        ViewHolder(View view){
            super(view);
            nameView = view.findViewById(R.id.name);
            phone_numberView = view.findViewById(R.id.email);
            skillsView = view.findViewById(R.id.skills);
        }
    }
}
