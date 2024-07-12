package com.example.pawapps.view.fragment.stock.add;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.pawapps.database.DatabaseClient2;
import com.example.pawapps.database.dao.DatabaseDao2;
import com.example.pawapps.model.ModelDatabase2;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AddStockViewModel extends AndroidViewModel {

    private DatabaseDao2 databaseDao2;

    public AddStockViewModel(@NonNull Application application) {
        super(application);

        databaseDao2 = DatabaseClient2.getInstance(application).getAppDatabase2().databaseDao2();
    }

    public void addStock(final String type, final String name, final String category, final int items, final int purchprice, final int sellprice) {
        Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        ModelDatabase2 stock = new ModelDatabase2();
                        stock.tipe = type;
                        stock.namaBarang = name;
                        stock.kategori = category;
                        stock.stok = items;
                        stock.hrgBeli = purchprice;
                        stock.hrgJual = sellprice;
                        databaseDao2.insertStock(stock);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void updateStock(final int uid, final String name, final String category, final int items, final int purchprice, final int sellprice) {
        Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        databaseDao2.updateDataStock(name, category, items, purchprice, sellprice, uid);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

}
