package com.example.basketball_quiz.UserInterface;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


import com.example.basketball_quiz.MainActivity;
import com.example.basketball_quiz.R;
import com.example.basketball_quiz.database.Question;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TableFragmentQ#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TableFragmentQ extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TableFragmentQ() {
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
    public static TableFragmentQ newInstance(String param1, String param2) {
        TableFragmentQ fragment = new TableFragmentQ();
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
    TextView textView;
    TextView textView1;

    TextView textView2;
    TextView textView3;

    TextView textView4;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table_q, container, false);
        textView = view.findViewById(R.id.textView3TQ);
        textView1 = view.findViewById(R.id.textView4TQ);
        textView2 = view.findViewById(R.id.textView6TQ);
        textView3 = view.findViewById(R.id.textView7TQ);
        textView4 = view.findViewById(R.id.textView8TQ);

        String sqid = "";
        String sText = "";
        String sAnswer = "";
        String scid = "";
        String said = "";


        List<Question> questions = MainActivity.myAppDatabase.myDao().getQuestions();

        for (Question i: questions) {

            int qid = i.getId();
            String Text = i.getQ_text();
            String answer = i.getSl();
            int cid = i.getCid();
            int aid = i.getAid();


            sqid = sqid  + qid + "\n\n\n" ;
            sText = sText  + Text + "\n\n\n" ;
            sAnswer = sAnswer  + answer + "\n\n\n" ;
            scid = scid  + cid + "\n\n\n" ;
            said = said  + aid + "\n\n\n" ;
        }

        textView.setText(sqid);
        textView1.setText(sText);
        textView2.setText(sAnswer);
        textView3.setText(scid);
        textView4.setText(said);


        return view;
    }}
