package com.example.applistas.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.applistas.Fragments.Buscador_Personajes_fragment;
import com.example.applistas.Fragments.ListaPersonajeFragment;
import com.example.applistas.Fragments.ListaPlanetaFragment;

public class DBPagerAdapter extends FragmentStateAdapter {
    public DBPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new Buscador_Personajes_fragment();
            case 1: return new ListaPersonajeFragment();
            default: return new ListaPlanetaFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3; //Coleccion de 3 elementos
    }
}
