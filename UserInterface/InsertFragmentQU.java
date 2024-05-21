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
import com.example.basketball_quiz.database.Quiz;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.example.basketball_quiz.R;
import com.example.basketball_quiz.MainActivity;
import com.google.firebase.firestore.DocumentReference;

public class InsertFragmentQU extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InsertFragmentQU() {
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
    public static InsertFragmentQU newInstance(String param1, String param2) {
        InsertFragmentQU fragment = new InsertFragmentQU();
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
    EditText editText1, editText2, editText3, editText4;
    Button bn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insert_qu, container, false);
        editText1 = view.findViewById(R.id.EditText1QU);
        editText2 = view.findViewById(R.id.EditText2QU);
        editText3 = view.findViewById(R.id.EditText3QU);
        editText4 = view.findViewById(R.id.EditText4QU);
        bn = view.findViewById(R.id.insertButtonQU);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String QName = editText1.getText().toString();

                int questId = 0;
                try {
                    questId = Integer.parseInt(editText2.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }

                String qsid = editText3.getText().toString();

                DocumentReference Qsid = MainActivity.db.document("/QuizS/" + qsid);

                int qcid = 0;
                try {
                    qcid = Integer.parseInt(editText4.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }

                DocumentReference Qcid = MainActivity.db.document("/QuizC/" + qcid);

                try {
                    Quiz qu = new Quiz();
                    qu.setQuizName(QName);
                    qu.setQuestCat(questId);
                    qu.setQsid(Qsid);
                    qu.setQcid(Qcid);
                    MainActivity.db.
                            collection("Quiz").
                            document(""+QName).
                            set(qu).
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    MainActivity.makeNotif(getContext(),"Εγγραφή Κουίζ","Η εγγραφή κουίζ καταχωρήθηκε");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    MainActivity.makeNotif(getContext(),"Αποτυχίας Εγγραφής Κουίζ","Η εγγραφή κουίζ απέτυχε");
                                }
                            });

                } catch (Exception e) {
                    String message = e.getMessage();
                    Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                }
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                editText4.setText("");
            }
        });
        return view;     }






}
