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
 * Use the {@link DataProcessFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataProcessFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DataProcessFragment() {
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
    public static DataProcessFragment newInstance(String param1, String param2) {
        DataProcessFragment fragment = new DataProcessFragment();
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

    Button qbutton;
    Button cbutton;

    Button abutton;

    Button Qbutton;

    Button QSbutton;

    Button QCbutton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_process_data, container, false);

        qbutton = view.findViewById(R.id.buttonP);
        qbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                        new DataProcessFragmentQ()).addToBackStack(null).commit();



            }
        });

        cbutton = view.findViewById(R.id.buttonP2);
        cbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                        new DataProcessFragmentC()).addToBackStack(null).commit();



            }
        });

        abutton = view.findViewById(R.id.buttonP3);
        abutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                        new DataProcessFragmentA()).addToBackStack(null).commit();



            }
        });




    Qbutton = view.findViewById(R.id.buttonP4);
        Qbutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                    new DataProcessFragmentQU()).addToBackStack(null).commit();



        }
    });

    QCbutton = view.findViewById(R.id.buttonP5);

        QCbutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                    new DataProcessFragmentQC()).addToBackStack(null).commit();



        }
    });

    QSbutton = view.findViewById(R.id.buttonP6);
        QSbutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                    new DataProcessFragmentQS()).addToBackStack(null).commit();



        }
    });

        return view;
}


}