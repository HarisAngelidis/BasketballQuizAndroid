package com.example.basketball_quiz.UserInterface;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.basketball_quiz.MainActivity;
import com.example.basketball_quiz.R;
import com.example.basketball_quiz.database.Question;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertFragmentQ#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertFragmentQ extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InsertFragmentQ() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InsertFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InsertFragmentQ newInstance(String param1, String param2) {
        InsertFragmentQ fragment = new InsertFragmentQ();
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
    EditText editText1,editText2,editText3,editText4,editText5;
    Button submit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insert_q, container, false);
        editText1 = view.findViewById(R.id.editText);
        editText2 = view.findViewById(R.id.editText2);
        editText3 = view.findViewById(R.id.editText3);
        editText4 = view.findViewById(R.id.editText4);
        editText5 = view.findViewById(R.id.editText5);

        submit = view.findViewById(R.id.submitquestion);
        submit.setOnClickListener(view1 -> {

            if(!editText1.getText().toString().isEmpty()) {
                int qid=0;
                try {
                    qid = Integer.parseInt(editText1.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }

                List<Integer> qids = MainActivity.myAppDatabase.myDao().getQIDS();

                if(qids.contains(qid)){

                    MainActivity.makeNotif(getContext(), "Αποτυχία Εγγραφής Ερώτησης", "Το id ερώτησης υπάρχει ήδη");

                }
                else {
                String qText = editText2.getText().toString();
                String sl = editText3.getText().toString();

                    if(!editText4.getText().toString().isEmpty()){

                int cid = 0;
                try {
                    cid = Integer.parseInt(editText4.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }

                List<Integer> cids = MainActivity.myAppDatabase.myDao().getCIDS();

                if(!cids.contains(cid)){

                    MainActivity.makeNotif(getContext(), "Αποτυχία Εγγραφής Ερώτησης", "Το id κατηγορίας δεν υπάρχει");

                }
                else{

                    if(!editText5.getText().toString().isEmpty()){

                int aid = 0;
                try {
                    aid = Integer.parseInt(editText5.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }

                    List<Integer> aids = MainActivity.myAppDatabase.myDao().getAIDS();

                    if(!aids.contains(aid)){

                        MainActivity.makeNotif(getContext(), "Αποτυχία Εγγραφής Ερώτησης", "Το id άρθρου δεν υπάρχει");

                    }

                    else{

                try {
                    Question question = new Question();
                    question.setId(qid);
                    question.setQ_text(qText);
                    question.setSl(sl);
                    question.setCid(cid);
                    question.setAid(aid);

                    MainActivity.myAppDatabase.myDao().addQuestion(question);

                    MainActivity.makeNotif(getContext(), "Εγγραφή Ερώτησης", "Η εγγραφή ερώτησης καταχωρήθηκε");
                } catch (Exception e) {

                    MainActivity.makeNotif(getContext(), "Αποτυχία Εγγραφής Ερώτησης", "Κάτι πήγε στραβά");

                }


                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                editText4.setText("");
                editText5.setText("");
            }}

                    else { MainActivity.makeNotif(getContext(), "Αποτυχία Εγγραφής Ερώτησης",
                            "Πρέπει να εισάγετε id για το άρθρο");}

                }}
                    else { MainActivity.makeNotif(getContext(), "Αποτυχία Εγγραφής Ερώτησης",
                            "Πρέπει να εισάγετε id για την κατηγορία ερώτησης");}



                }}
            else { MainActivity.makeNotif(getContext(), "Αποτυχία Εγγραφής Ερώτησης",
                    "Πρέπει να εισάγετε id για την ερώτηση");}
        });
        return view;
    }
}