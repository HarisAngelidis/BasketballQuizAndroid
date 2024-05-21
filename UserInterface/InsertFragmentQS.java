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
import com.example.basketball_quiz.database.Quiz_Size;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.example.basketball_quiz.R;
import com.example.basketball_quiz.MainActivity;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertFragmentQS#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertFragmentQS extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InsertFragmentQS() {
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
        View view = inflater.inflate(R.layout.fragment_insert_qs, container, false);
        editText1 = view.findViewById(R.id.EditTextQS1);
        editText2 = view.findViewById(R.id.EditTextQS2);

        bn = view.findViewById(R.id.insertQsButton);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String qsid = editText1.getText().toString();

                int Size = 0;
                try {
                    Size = Integer.parseInt(editText2.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }


                try {
                    Quiz_Size qs = new Quiz_Size();
                    qs.setQsid(qsid);
                    qs.setSize(Size);

                    MainActivity.db.
                            collection("QuizS").
                            document(""+qsid).
                            set(qs).
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    MainActivity.makeNotif(getContext(),"Εγγραφή Μεγέθους Κουίζ","Η εγγραφή μεγέθους κουίζ καταχωρήθηκε");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    MainActivity.makeNotif(getContext(),"Αποτυχία Εγγραφής Μεγέθους Κουίζ","Η εγγραφή απέτυχε");
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