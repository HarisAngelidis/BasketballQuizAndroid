package com.example.basketball_quiz.UserInterface;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.example.basketball_quiz.R;
import com.example.basketball_quiz.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeleteFragmentQS#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeleteFragmentQS extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DeleteFragmentQS() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     */
    // TODO: Rename and change types and number of parameters
    public static DeleteFragmentQS newInstance(String param1, String param2) {
        DeleteFragmentQS fragment = new DeleteFragmentQS();
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
    EditText editText1;
    Button bn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete_qs, container, false);
        editText1 = view.findViewById(R.id.editTextdelQS);


        bn = view.findViewById(R.id.delQS);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String size = editText1.getText().toString();



                try {

                    MainActivity.db.document("QuizS/" + size).
                            delete().
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    MainActivity.makeNotif(getContext(),"Διαγραφή Μεγέθους Κουίζ","Το μέγεθος διαγράφηκε");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    MainActivity.makeNotif(getContext(),"Αποτυχία Διαγραφής Μεγέθους Κουίζ","Κάτι πήγε στραβά");
                                }
                            });
                } catch (Exception e) {
                    String message = e.getMessage();
                    Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                }
                editText1.setText("");


            }
        });
        return view;     }
}