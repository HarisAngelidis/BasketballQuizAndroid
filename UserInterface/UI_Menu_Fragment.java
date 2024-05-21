package com.example.basketball_quiz.UserInterface;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.example.basketball_quiz.MainActivity;
import com.example.basketball_quiz.R;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UI_Menu_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UI_Menu_Fragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    DrawerLayout drawerLayout ;

    public UI_Menu_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UI_Menu_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UI_Menu_Fragment newInstance(String param1, String param2) {
        UI_Menu_Fragment fragment = new UI_Menu_Fragment();
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
    private Button button,button1;
    private Spinner spinner;

    private Map<String, Integer> sizeMap;
    private Map<String, Integer> QCatMap;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ui_menu, container, false);

        button1 = view.findViewById(R.id.createQuiz);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                        new InsertFragmentQU()).addToBackStack(null).commit();



            }
        });



        button = view.findViewById(R.id.newQuiz);
        button.setOnClickListener(this);

        spinner = view.findViewById(R.id.QuizSpinner);


        sizeMap = new HashMap<>();

        QCatMap = new HashMap<>();

        retrieveDocumentIds();




        return view;
    }

    private void retrieveDocumentIds() {
        MainActivity.db.collection("Quiz")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<String> documentIds = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String docId = document.getId();
                                documentIds.add(document.getId());

                                DocumentReference sizeRef = (DocumentReference) document.get("qsid");
                                retrieveQuizSize(docId, sizeRef);

                                int questCat = document.getLong("questCat").intValue();
                                QCatMap.put(docId, questCat);
                            }

                            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(requireContext(),
                                    R.layout.spinner, documentIds);
                            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(spinnerAdapter);


                        } else {
                            Toast.makeText(requireContext(), "Failed to retrieve quiz data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }




   private void retrieveQuizSize(final String docId, DocumentReference quizSizeRef) {
       quizSizeRef.get()
               .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                       if (task.isSuccessful()) {
                           DocumentSnapshot document = task.getResult();
                           if (document.exists()) {
                               int size = document.getLong("size").intValue();


                               sizeMap.put(docId, size);
                           } else {

                               sizeMap.put(docId, 0);

                           }
                       } else {

                           sizeMap.put(docId, 0);
                       }
                   }
               });}





    @Override

    public void onClick(View v) {

        String selectedDocId = spinner.getSelectedItem().toString();

        int size = sizeMap.get(selectedDocId);
        int questCat = QCatMap.get(selectedDocId);

        MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                new QuizFragment(size,questCat)).addToBackStack(null).commit();


        }






    }
