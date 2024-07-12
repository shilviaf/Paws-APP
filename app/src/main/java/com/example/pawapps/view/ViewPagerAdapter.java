package com.example.pawapps.view;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.pawapps.view.fragment.pemasukan.PemasukanFragment;
import com.example.pawapps.view.fragment.pengeluaran.PengeluaranFragment;
import com.example.pawapps.view.fragment.stock.StockFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new StockFragment();
                break;
            case 1:
                fragment = new PemasukanFragment();
                break;
            case 2:
                fragment = new PengeluaranFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String strTitle = "";
        switch (position) {
            case 0:
                strTitle = "Stok";
                break;
            case 1:
                strTitle = "Masuk";
                break;
            case 2:
                strTitle = "Keluar";
                break;
        }
        return strTitle;
    }

}
