package com.example.basketball_quiz.UserInterface;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.basketball_quiz.MainActivity;
import com.example.basketball_quiz.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataProcessFragmentQC#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataProcessFragmentQC extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DataProcessFragmentQC() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataProcessFragmentQC newInstance(String param1, String param2) {
        DataProcessFragmentQC fragment = new DataProcessFragmentQC();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Button ibutton;
    Button ubutton;

    Button dbutton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_process_data_qc, container, false);

        ibutton = view.findViewById(R.id.buttonPQC);
        ibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                        new InsertFragmentQC()).addToBackStack(null).commit();



            }
        });

        ubutton = view.findViewById(R.id.buttonPQC2);
        ubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                        new UpdateFragmentQC()).addToBackStack(null).commit();



            }
        });

        dbutton = view.findViewById(R.id.buttonPQC3);
        dbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                        new DeleteFragmentQC()).addToBackStack(null).commit();



            }
        });

        return view;
    }
}