package com.example.pawapps.view.fragment.stock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawapps.R;
import com.example.pawapps.model.ModelDatabase2;
import com.example.pawapps.view.fragment.stock.add.AddStockActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class StockFragment extends Fragment implements StockAdapter.StockAdapterCallback {

    private StockAdapter stockAdapter;
    private StockViewModel stockViewModel;
    private List<ModelDatabase2> modelDatabase2List = new ArrayList<>();
    TextView tvTotal, tvNotFound;
    Button btnHapus;
    FloatingActionButton fabAdd;
    RecyclerView rvListData;

    public StockFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stock, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTotal = view.findViewById(R.id.tvTotal);
        tvNotFound = view.findViewById(R.id.tvNotFound);
        btnHapus = view.findViewById(R.id.btnHapus);
        fabAdd = view.findViewById(R.id.fabAdd);
        rvListData = view.findViewById(R.id.rvListData);

        tvNotFound.setVisibility(View.GONE);

        initAdapter();
        observeData();
        initAction();
    }

    private void initAdapter() {
        stockAdapter = new StockAdapter(requireContext(), modelDatabase2List, this);
        rvListData.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvListData.setItemAnimator(new DefaultItemAnimator());
        rvListData.setAdapter(stockAdapter);
    }

    private void observeData() {
        stockViewModel = ViewModelProviders.of(this).get(StockViewModel.class);
        stockViewModel.getStock().observe(requireActivity(),
                new Observer<List<ModelDatabase2>>() {
                    @Override
                    public void onChanged(List<ModelDatabase2> stock) {
                        if (stock.isEmpty()) {
                            btnHapus.setVisibility(View.GONE);
                            tvNotFound.setVisibility(View.VISIBLE);
                            rvListData.setVisibility(View.GONE);
                        } else {
                            btnHapus.setVisibility(View.VISIBLE);
                            tvNotFound.setVisibility(View.GONE);
                            rvListData.setVisibility(View.VISIBLE);
                        }
                        stockAdapter.addData(stock);
                    }
                });

        stockViewModel.getTotalStock().observe(requireActivity(),
                new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        if (integer == null) {
                            int totalStock = 0;
                            String initStock = String.valueOf(totalStock);
                            tvTotal.setText(initStock);
                        } else {
                            int totalStock = integer;
                            String initStock = String.valueOf(totalStock);
                            tvTotal.setText(initStock);
                        }
                    }
                });
    }

    private void initAction() {
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddStockActivity.startActivity(requireActivity(), false, null);
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stockViewModel.deleteAllData();
                tvTotal.setText("0");
            }
        });
    }

    @Override
    public void onEdit(ModelDatabase2 modelDatabase2) {
        AddStockActivity.startActivity(requireActivity(), true, modelDatabase2);
    }

    @Override
    public void onDelete(ModelDatabase2 modelDatabase2) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(requireContext());
        alertDialogBuilder.setMessage("Hapus data ini?");
        alertDialogBuilder.setPositiveButton("Ya, Hapus", (dialogInterface, i) -> {
            int uid = modelDatabase2.uid;
            String sNamaBarang = stockViewModel.deleteSingleData(uid);
            if (sNamaBarang.equals("OK")) {
                Toast.makeText(requireContext(), "Data yang dipilih sudah dihapus",
                        Toast.LENGTH_SHORT).show();
            }
        });

        alertDialogBuilder.setNegativeButton("Batal", (dialogInterface, i) -> dialogInterface.cancel());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
