package com.example.applistas.Fragments;

//1. Herencia

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.applistas.R;

public class Buscador_Personajes_fragment extends Fragment {

    Button btnsaludar;

    private void loadUI(@NonNull View view){
        btnsaludar = view.findViewById(R.id.btnsaludar);
    }

    private void saludar(){
        Toast.makeText(requireContext(), "Hola esta es la accion", Toast.LENGTH_SHORT).show();
    }

    public Buscador_Personajes_fragment(){}

    //3. Implementar 2 metodos ( constructir > ejecutor)


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_buscador_personaje, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.loadUI(view); //Fragment
        btnsaludar.setOnClickListener(v -> { saludar(); });
    }
}
