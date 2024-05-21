package com.example.basketball_quiz.UserInterface;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.basketball_quiz.MainActivity;
import com.example.basketball_quiz.R;

import com.example.basketball_quiz.database.Question;

import java.util.Collections;


import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizFragment extends Fragment {


    private int qsize;

    int qcat;

    private int currentQuestionIndex = 0;

    private int sosta = 0;

    private int sunolo = 0;

    int a =5;

    private boolean apantisi = false;

    List<Question> questions;

   private String answer ;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuizFragment() {
        // Required empty public constructor
    }

    public QuizFragment(int size) {

        qsize = size;
    }

    public QuizFragment(int size,int cat) {

        qsize = size;
        qcat = cat;

    }
        // Required empty public constructor


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InsertFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizFragment newInstance(String param1, String param2) {
        QuizFragment fragment = new QuizFragment();
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
    TextView editText1;

  int b = qcat;









    Button submit;
    Button sosto;
    Button lathos;

    Button telos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        {
            questions = MainActivity.myAppDatabase.myDao().getQuestionsCat(qcat);}

        int qav = questions.size();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);


        editText1 = view.findViewById(R.id.textViewQuiz);

        submit = view.findViewById(R.id.buttonQ3);
        sosto = view.findViewById(R.id.buttonQ1);
        lathos = view.findViewById(R.id.buttonQ2);
        telos = view.findViewById(R.id.buttonQ4);

        Collections.shuffle(questions);

        if (qav > 0) {
            displayQuestion();
        } else {
            editText1.setText("No questions available.");
            telos.setVisibility(View.VISIBLE);
            sosto.setVisibility(View.GONE);
            lathos.setVisibility(View.GONE);
            submit.setVisibility(View.GONE);
        }


        submit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if(!apantisi){

                    Toast.makeText(requireContext(), "Επιλέξτε Απάντηση", Toast.LENGTH_SHORT).show();

                    return;}

                // Check if there are more questions available
                if (currentQuestionIndex < questions.size() - 1 && currentQuestionIndex < qsize-1) {
                    // Increment the current question index
                    currentQuestionIndex++;
                    // Display the next question
                   displayQuestion();
                } else {

                    telos.setVisibility(View.VISIBLE);
                    editText1.setText("Τέλος Κουίζ\n\nΣκόρ: " + sosta + "/" + sunolo);
                    sosto.setVisibility(View.GONE);
                    lathos.setVisibility(View.GONE);
                    submit.setVisibility(View.GONE);

                }
            }
        });

        sosto.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

               if(apantisi){return;}

               else{

                   apantisi = true;

                   if(answer.equals("Σωστό")){

                       sosto.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                       sosta++;


                   }
                   else{

                       sosto.setBackgroundColor(getResources().getColor(R.color.Salmon));

                   }


                   sunolo++;
               }

            }
        });

        lathos.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if(apantisi){return;}

                else{

                    apantisi = true;

                    if(answer.equals("Λάθος")){

                        lathos.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                        sosta++;


                    }
                    else{

                        lathos.setBackgroundColor(getResources().getColor(R.color.Salmon));

                    }


                }

                sunolo++;

            }
        });

        telos.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                        new UI_Menu_Fragment()).addToBackStack(null).commit();

            }
        });

        return view;
    }
    private void displayQuestion() {

        Question currentQuestion = questions.get(currentQuestionIndex);

        editText1.setText(currentQuestion.getQ_text());

        answer = currentQuestion.getSl();

        apantisi = false;

        sosto.setBackgroundColor(getResources().getColor(R.color.LightPurple));
        lathos.setBackgroundColor(getResources().getColor(R.color.LightPurple));


    }
}
