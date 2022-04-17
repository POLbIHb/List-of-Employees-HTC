package com.gmail.illobikol.htcemployerslist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EmployerAdapter  extends RecyclerView.Adapter<EmployerAdapter.ViewHolder>{

    private List<Employer> employers;

    EmployerAdapter(List<Employer> states) { this.employers = states; }
    public void setEmployer(List<Employer> employers) {
        this.employers = employers;
    }

    @Override
    public EmployerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employer_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Employer employer = employers.get(position);
        holder.bindData(employer);
    }

    @Override
    public int getItemCount() {
        return employers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView;
        private final TextView phoneNumberView;
        private final TextView skillsView;

        public ViewHolder(View view){
            super(view);
            nameView = (TextView) view.findViewById(R.id.employerName);
            phoneNumberView = (TextView) view.findViewById(R.id.employerPhone);
            skillsView = (TextView) view.findViewById(R.id.employerSkills);
        }

        public void bindData(Employer employer){
            nameView.setText(employer.getName());
            phoneNumberView.setText(employer.getPhoneNumber());
            skillsView.setText(employer.getSkills().replaceAll("\\[|\\]$", ""));
        }
    }
}
