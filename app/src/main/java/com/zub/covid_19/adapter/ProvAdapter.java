package com.zub.covid_19.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.zub.covid_19.R;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import static com.android.volley.VolleyLog.TAG;

public class ProvAdapter extends RecyclerView.Adapter<ProvAdapter.ViewHolder> {

    private ArrayList<String> provName = new ArrayList<>();
    private ArrayList<Integer> provCase = new ArrayList<>();
    private ArrayList<Integer> provDeath = new ArrayList<>();
    private ArrayList<Integer> provHealed = new ArrayList<>();
    private ArrayList<Integer> provTreated = new ArrayList<>();

    private ListClickedListener listClickedListener;

    public ProvAdapter(ArrayList<String> provName, ArrayList<Integer> provCase, ArrayList<Integer> provDeath,
                       ArrayList<Integer> provHealed, ArrayList<Integer> provTreated,
                       ListClickedListener listClickedListener) {
        this.provName = provName;
        this.provCase = provCase;
        this.provDeath = provDeath;
        this.provHealed = provHealed;
        this.provTreated = provTreated;
        this.listClickedListener = listClickedListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stats_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, listClickedListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.mProvName.setText(provName.get(position));
        holder.mProvCase.setText(numberSeparator(provCase.get(position)));
        holder.mProvDeath.setText(numberSeparator(provDeath.get(position)));
        holder.mProvHealed.setText(numberSeparator(provHealed.get(position)));
        holder.mProvTreated.setText(numberSeparator(provTreated.get(position)));

        holder.mProvListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), provName.get(position), Toast.LENGTH_LONG).show();
                holder.mListClickedListener.onListClicked(position);
            }
        });

    }

    @Override
    public int getItemCount() {

        return provName.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mProvListLayout;

        TextView mProvName, mProvCase, mProvDeath, mProvHealed, mProvTreated;

        ListClickedListener mListClickedListener;

        public ViewHolder(@NonNull View itemView, ListClickedListener listClickedListener) {
            super(itemView);

            mProvListLayout = itemView.findViewById(R.id.prov_list_layout);

            mProvName = itemView.findViewById(R.id.prov_name);
            mProvCase = itemView.findViewById(R.id.prov_case);
            mProvDeath = itemView.findViewById(R.id.prov_death);
            mProvHealed = itemView.findViewById(R.id.prov_healed);
            mProvTreated = itemView.findViewById(R.id.prov_treated);

            mListClickedListener = listClickedListener;

        }
    }

    public interface ListClickedListener {
        void onListClicked(int position);
    }

    private String numberSeparator(int value) {
        return String.valueOf(NumberFormat.getNumberInstance(Locale.ITALY).format(value));
    }

}
