package com.example.pawapps.view.fragment.stock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pawapps.R;
import com.example.pawapps.model.ModelDatabase2;
import com.example.pawapps.utils.FunctionHelper;

import java.util.List;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.ViewHolder> {

    private Context context;
    private List<ModelDatabase2> list;
    private StockAdapterCallback mAdapterCallback;

    public StockAdapter(Context context, List<ModelDatabase2> list,
                        StockAdapterCallback adapterCallback) {
        this.context = context;
        this.list = list;
        this.mAdapterCallback = adapterCallback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_data2,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ModelDatabase2 modelDatabase2 = list.get(position);
        holder.bindData(modelDatabase2);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        int size = this.list.size();
        this.list.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addData(List<ModelDatabase2> stocks) {
        this.list = stocks;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvCategory, tvStock, tvPurchPrice, tvSellPrice;
        public ImageView ivDelete;

        ViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvStock = itemView.findViewById(R.id.tvStock);
            tvPurchPrice = itemView.findViewById(R.id.tvPurchPrice);
            tvSellPrice = itemView.findViewById(R.id.tvSellPrice);
            ivDelete = itemView.findViewById(R.id.ivDelete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ModelDatabase2 stock = list.get(getAdapterPosition());
                    mAdapterCallback.onEdit(stock);
                }
            });

            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ModelDatabase2 stock = list.get(getAdapterPosition());
                    mAdapterCallback.onDelete(stock);
                }
            });
        }

        void bindData(ModelDatabase2 item) {
            String name = item.namaBarang;
            tvName.setText(name);

            String category = item.kategori;
            tvCategory.setText(category);

            int stock = item.stok;
            String initStock = String.valueOf(stock);
            tvStock.setText(initStock);

            int purchprice = item.hrgBeli;
            String initPurchPrice = FunctionHelper.rupiahFormat(purchprice);
            tvPurchPrice.setText(initPurchPrice);

            int sellprice = item.hrgJual;
            String initSellPrice = FunctionHelper.rupiahFormat(sellprice);
            tvSellPrice.setText(initSellPrice);

        }
    }

    public interface StockAdapterCallback {
        void onEdit(ModelDatabase2 modelDatabase2);

        void onDelete(ModelDatabase2 modelDatabase2);
    }

}
