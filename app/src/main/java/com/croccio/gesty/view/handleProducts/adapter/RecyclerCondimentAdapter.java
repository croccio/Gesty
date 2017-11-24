package com.croccio.gesty.view.handleProducts.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.croccio.gesty.R;
import com.croccio.gesty.model.Condiment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerCondimentAdapter extends RecyclerView.Adapter<RecyclerCondimentAdapter.CondimentViewHolder> {

    private final Context context;
    private List<Condiment> condiments = new ArrayList<>();


    public RecyclerCondimentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CondimentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_condiment_list_item, parent, false);

        return new CondimentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CondimentViewHolder holder, final int position) {
        final Condiment condiment = condiments.get(position);

        holder.condimentCategoryTextView.setText(condiment.getCategory().getName());
        holder.nameTextView.setText(condiment.getName());
        holder.priceToAddTextView.setText(context.getString(R.string.priceToAdd, condiment.getPriceToAdd()));
        holder.priceToRemoveTextView.setText(context.getString(R.string.priceToAdd, condiment.getPriceToRemove()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return condiments.size();
    }

    public void add(List<Condiment> condiments) {
        int previousDataSize = this.condiments.size();
        this.condiments.addAll(condiments);
        notifyItemRangeInserted(previousDataSize, condiments.size());
    }

    public void add(Condiment condiment) {
        int previousDataSize = this.condiments.size();
        this.condiments.add(condiment);
        notifyItemRangeInserted(previousDataSize, condiments.size());
    }

    public void remove(Condiment condiment) {
        int previousDataSize = this.condiments.size();
        this.condiments.remove(condiment);
        notifyItemRangeInserted(previousDataSize, condiments.size());
    }

    public class CondimentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nameTextView)
        TextView nameTextView;
        @BindView(R.id.condimentCategoryTextView)
        TextView condimentCategoryTextView;
        @BindView(R.id.priceToAddTextView)
        TextView priceToAddTextView;
        @BindView(R.id.priceToRemoveTextView)
        TextView priceToRemoveTextView;

        public CondimentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}