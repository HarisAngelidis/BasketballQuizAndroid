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
import com.example.basketball_quiz.database.Article;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertFragmentA#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertFragmentA extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InsertFragmentA() {
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
    public static InsertFragmentA newInstance(String param1, String param2) {
        InsertFragmentA fragment = new InsertFragmentA();
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
    EditText editText1,editText2;
    Button submit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insert_a, container, false);
        editText1 = view.findViewById(R.id.editTextA);
        editText2 = view.findViewById(R.id.editText2A);

        submit = view.findViewById(R.id.submitarticle);
        submit.setOnClickListener(view1 -> {

            if(!editText1.getText().toString().isEmpty()) {
                int aid = 0;
                try {
                    aid = Integer.parseInt(editText1.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }

                List<Integer> aids = MainActivity.myAppDatabase.myDao().getAIDS();

                if(aids.contains(aid)){

                    MainActivity.makeNotif(getContext(), "Αποτυχία Εγγραφής Άρθρου", "Το id άρθρου υπάρχει ήδη");

                }
                else{
                String Title = editText2.getText().toString();

                Article article = new Article();
                article.setId(aid);
                article.setATitle(Title);

                MainActivity.myAppDatabase.myDao().addArticle(article);
                MainActivity.makeNotif(getContext(), "Εγγραφή Άρθρου", "Η εγγραφή άρθρου καταχωρήθηκε");
                editText1.setText("");
                editText2.setText("");

            }}

            else { MainActivity.makeNotif(getContext(), "Αποτυχία Εγγραφής Άρθρου", "Πρέπει να εισάγετε id για το άρθρο");}

        });
        return view;
    }
}