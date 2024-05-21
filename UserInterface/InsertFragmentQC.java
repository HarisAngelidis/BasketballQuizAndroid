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

import com.example.basketball_quiz.database.Quiz_Category;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.example.basketball_quiz.R;
import com.example.basketball_quiz.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertFragmentQC#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertFragmentQC extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InsertFragmentQC() {
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
    public static InsertFragmentQC newInstance(String param1, String param2) {
        InsertFragmentQC fragment = new InsertFragmentQC();
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
    EditText editText1, editText2;
    Button bn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insert_qc, container, false);
        editText1 = view.findViewById(R.id.EditTextQC1);
        editText2 = view.findViewById(R.id.EditTextQC2);

        bn = view.findViewById(R.id.insertQcButton);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qcid = 0;
                try {
                    qcid = Integer.parseInt(editText1.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                String QCname = editText2.getText().toString();


                try {
                    Quiz_Category qc = new Quiz_Category();
                    qc.setQcid(qcid);
                    qc.setQCname(QCname);

                    MainActivity.db.
                            collection("QuizC").
                            document(""+qcid).
                            set(qc).
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    MainActivity.makeNotif(getContext(),"Εγγραφή Κατηγορίας Κουίζ","Η εγγραφή κατηγορίας κουίζ καταχωρήθηκε");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    MainActivity.makeNotif(getContext(),"Αποτυχία Εγγραφής Κατηγορίας Κουίζ","Η εγγραφή απέτυχε");
                                }
                            });
                } catch (Exception e) {
                    String message = e.getMessage();
                    Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                }
                editText1.setText("");
                editText2.setText("");

            }
        });
        return view;     }
}