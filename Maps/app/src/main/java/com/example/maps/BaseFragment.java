package com.example.maps;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    public MainActivity getContainerActivity(){

        MainActivity actividadContenedora;
        actividadContenedora = (MainActivity) getActivity();
        return actividadContenedora;
    }
}
