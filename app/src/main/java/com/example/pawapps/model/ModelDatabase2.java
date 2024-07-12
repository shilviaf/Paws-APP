package com.example.pawapps.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "tbl_barang")
public class ModelDatabase2 implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "tipe")
    public String tipe;

    @ColumnInfo(name = "nama_barang")
    public String namaBarang;

    @ColumnInfo(name = "kategori")
    public String kategori;

    @ColumnInfo(name = "stok")
    public int stok;

    @ColumnInfo(name = "hrg_beli")
    public int hrgBeli;

    @ColumnInfo(name = "hrg_jual")
    public int hrgJual;

    public ModelDatabase2() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.uid);
        dest.writeString(this.tipe);
        dest.writeString(this.namaBarang);
        dest.writeString(this.kategori);
        dest.writeInt(this.stok);
        dest.writeInt(this.hrgBeli);
        dest.writeInt(this.hrgJual);
    }

    protected ModelDatabase2(Parcel in) {
        this.uid = in.readInt();
        this.tipe = in.readString();
        this.namaBarang = in.readString();
        this.kategori = in.readString();
        this.stok = in.readInt();
        this.hrgBeli = in.readInt();
        this.hrgJual = in.readInt();
    }

    public static final Creator<ModelDatabase2> CREATOR = new Creator<ModelDatabase2>() {
        @Override
        public ModelDatabase2 createFromParcel(Parcel source) {
            return new ModelDatabase2(source);
        }

        @Override
        public ModelDatabase2[] newArray(int size) {
            return new ModelDatabase2[size];
        }
    };

}
