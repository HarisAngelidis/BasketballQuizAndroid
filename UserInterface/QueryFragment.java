package com.example.basketball_quiz.UserInterface;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.example.basketball_quiz.MainActivity;
import com.example.basketball_quiz.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QueryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QueryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QueryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QueryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QueryFragment newInstance(String param1, String param2) {
        QueryFragment fragment = new QueryFragment();
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
    EditText editText,editText1;

    Button B1,B2,B3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_query, container, false);

        editText = view.findViewById(R.id.QInsertCat);
        editText1 = view.findViewById(R.id.QInsertΤext);

        B1 = view.findViewById(R.id.buttonQCat);
        B2 = view.findViewById(R.id.buttonQCount);
        B3 = view.findViewById(R.id.buttonQText);


        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editText.getText().toString().equals("")){}

                else{
                int ACid = Integer.parseInt(editText.getText().toString());

                editText.setText("");

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                        new QueryResultFragment(1,ACid)).addToBackStack(null).commit();}



            }
        });

        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                            new QueryResultFragment(2)).addToBackStack(null).commit();}




        });

        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText1.getText().toString().equals("")) {
                } else {

                    String text = editText1.getText().toString();

                    editText1.setText("");

                    MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                            new QueryResultFragment(3,text)).addToBackStack(null).commit();
                }
            }



        });


                return view;
    }
}