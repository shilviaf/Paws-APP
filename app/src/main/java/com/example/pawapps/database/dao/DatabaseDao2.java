package com.example.pawapps.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.pawapps.model.ModelDatabase2;

import java.util.List;

@Dao
public interface DatabaseDao2 {
    //Data Stock
    @Query("SELECT * FROM tbl_barang WHERE tipe = 'stok'")
    LiveData<List<ModelDatabase2>> getAllStock();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStock(ModelDatabase2... stock);

    @Query("DELETE FROM tbl_barang WHERE tipe = 'stok'")
    void deleteAllStock();

    @Query("DELETE FROM tbl_barang WHERE uid= :uid and tipe = 'stok'")
    void deleteSingleStock(int uid);

    @Query("SELECT SUM(stok) FROM tbl_barang WHERE tipe = 'stok'")
    LiveData<Integer> getTotalStock();

    @Query("UPDATE tbl_barang SET nama_barang = :namaBarang, kategori = :kategori, stok = :stok, hrg_beli = :hrgBeli, hrg_jual = :hrgJual WHERE uid = :uid and tipe = 'stok'")
    void updateDataStock(String namaBarang, String kategori, int stok, int hrgBeli, int hrgJual, int uid);

}
