package com.example.pawapps.view.fragment.stock;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pawapps.database.DatabaseClient2;
import com.example.pawapps.database.dao.DatabaseDao2;
import com.example.pawapps.model.ModelDatabase2;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StockViewModel extends AndroidViewModel {

    private LiveData<List<ModelDatabase2>> mStock;
    private DatabaseDao2 databaseDao2;
    private LiveData<Integer> mTotalStock;

    public StockViewModel(@NonNull Application application) {
        super(application);

        databaseDao2 = DatabaseClient2.getInstance(application).getAppDatabase2().databaseDao2();
        mStock = databaseDao2.getAllStock();
        mTotalStock = databaseDao2.getTotalStock();
    }

    public LiveData<List<ModelDatabase2>> getStock() {
        return mStock;
    }

    public LiveData<Integer> getTotalStock() {
        return mTotalStock;
    }

    public void deleteAllData() {
        Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        databaseDao2.deleteAllStock();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public String deleteSingleData(final int uid) {
        String sNamaBarang;
        try {
            Completable.fromAction(() -> databaseDao2.deleteSingleStock(uid))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
            sNamaBarang = "OK";
        } catch (Exception e) {
            sNamaBarang = "NO";
        }
        return sNamaBarang;
    }

}
