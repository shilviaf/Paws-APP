package com.example.pawapps.view.fragment.stock.add;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.example.pawapps.R;
import com.example.pawapps.model.ModelDatabase2;
import com.example.pawapps.view.MainActivity;
import com.google.android.material.textfield.TextInputEditText;

public class AddStockActivity extends AppCompatActivity {

    private static String KEY_IS_EDIT = "key_is_edit";
    private static String KEY_DATA = "key_data";

    public static void startActivity(Context context, boolean isEdit, ModelDatabase2 stock) {
        Intent intent = new Intent(new Intent(context, AddStockActivity.class));
        intent.putExtra(KEY_IS_EDIT, isEdit);
        intent.putExtra(KEY_DATA, stock);
        context.startActivity(intent);
    }

    private AddStockViewModel addStockViewModel;

    private boolean mIsEdit = false;
    private int strId = 0;

    Toolbar toolbar;
    TextInputEditText etBarang, etKategori, etStok, etBeli, etJual;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data2);

        toolbar = findViewById(R.id.toolbar);
        etBarang = findViewById(R.id.etBarang);
        etKategori = findViewById(R.id.etKategori);
        etStok = findViewById(R.id.etStok);
        etBeli = findViewById(R.id.etBeli);
        etJual = findViewById(R.id.etJual);
        btnSimpan = findViewById(R.id.btnSimpan);

        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        addStockViewModel = ViewModelProviders.of(this).get(AddStockViewModel.class);

        loadData();
        initAction();
    }

    private void loadData() {
        mIsEdit = getIntent().getBooleanExtra(KEY_IS_EDIT, false);
        if (mIsEdit) {
            ModelDatabase2 stock = getIntent().getParcelableExtra(KEY_DATA);
            if (stock != null) {
                strId = stock.uid;
                String nama_barang = stock.namaBarang;
                String kategori = stock.kategori;
                int stok = stock.stok;
                int hrg_beli = stock.hrgBeli;
                int hrg_jual = stock.hrgJual;

                etBarang.setText(nama_barang);
                etKategori.setText(kategori);
                etStok.setText(String.valueOf(stok));
                etBeli.setText(String.valueOf(hrg_beli));
                etJual.setText(String.valueOf(hrg_jual));
            }
        }
    }

    private void initAction() {
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strTipe = "stok";
                String strBarang = etBarang.getText().toString();
                String strKategori = etKategori.getText().toString();
                String strStok = etStok.getText().toString();
                String strBeli = etBeli.getText().toString();
                String strJual = etJual.getText().toString();

                if (strBarang.isEmpty() || strKategori.isEmpty() || strStok.isEmpty() || strBeli.isEmpty() || strJual.isEmpty()) {
                    Toast.makeText(AddStockActivity.this, "Ups, form tidak boleh kosong!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (mIsEdit) {
                        addStockViewModel.updateStock(strId, strBarang, strKategori,
                                Integer.parseInt(strStok), Integer.parseInt(strBeli), Integer.parseInt(strJual));
                    } else {
                        addStockViewModel.addStock(strTipe, strBarang, strKategori,
                                Integer.parseInt(strStok), Integer.parseInt(strBeli), Integer.parseInt(strJual));
                    }
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(AddStockActivity.this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
