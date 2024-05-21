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
 * Use the {@link DeleteFragmentA#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeleteFragmentA extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DeleteFragmentA() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeleteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeleteFragmentA newInstance(String param1, String param2) {
        DeleteFragmentA fragment = new DeleteFragmentA();
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
    EditText deleditText;
    Button deletebutton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_a, container, false);
        deleditText = view.findViewById(R.id.editTextdelA);
        deletebutton = view.findViewById(R.id.delart);
        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!deleditText.getText().toString().isEmpty()) {

                    int aid = 0;
                    try {
                        aid = Integer.parseInt(deleditText.getText().toString());
                    } catch (NumberFormatException ex) {
                        System.out.println("Could not parse " + ex);
                    }

                    List<Integer> aids = MainActivity.myAppDatabase.myDao().getAIDS();

                    if (!aids.contains(aid)) {

                        MainActivity.makeNotif(getContext(), "Αποτυχία Διαγραφής Άρθρου", "Tο id του άρθρου δεν υπάρχει");

                    } else {

                        Article art = new Article();
                        art.setId(aid);
                        MainActivity.myAppDatabase.myDao().deleteArt(art);
                        MainActivity.makeNotif(getContext(), "Διαγραφή Άρθρου", "Το άρθρο διαγράφηκε");
                        deleditText.setText("");
                    }}

                else{

                        MainActivity.makeNotif(getContext(), "Αποτυχία Διαγραφής Άρθρου", "Πρέπει να εισάγετε το id του άρθρου");
                    }

                }
            });
        return view;
    }
}