package com.example.basketball_quiz.UserInterface;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.basketball_quiz.MainActivity;
import com.example.basketball_quiz.R;
import com.example.basketball_quiz.database.Category;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateFragmentC#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateFragmentC extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpdateFragmentC() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateFragmentC newInstance(String param1, String param2) {
        UpdateFragmentC fragment = new UpdateFragmentC();
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
    Button upbutton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_c, container, false);
        editText1 = view.findViewById(R.id.upeditText1C);
        editText2 = view.findViewById(R.id.upeditText2C);


        upbutton = view.findViewById(R.id.updatecat);
        upbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editText1.getText().toString().isEmpty()) {

                int cid = 0;

                try{
                    cid = Integer.parseInt(editText1.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }

                    List<Integer> cids = MainActivity.myAppDatabase.myDao().getCIDS();

                    if (!cids.contains(cid)) {

                        MainActivity.makeNotif(getContext(), "Αποτυχία Ενημέρωσης Κατηγορίας", "Tο id της κατηγορίας δεν υπάρχει");

                    } else {

                String ctext = editText2.getText().toString();


                Category cat = new Category();

                cat.setId(cid);
                cat.setCName(ctext);

                MainActivity.myAppDatabase.myDao().updateCategory(cat);
                MainActivity.makeNotif(getContext(),"Ενημέρωση Κατηγορίας Ερωτήσεων","Η κατηγορία ενημερώθηκε");

                editText1.setText("");
                editText2.setText("");}}

                else { MainActivity.makeNotif(getContext(), "Αποτυχία Ενημέρωσης Κατηγορίας",
                        "Πρέπει να εισάγετε id για την κατηγορία");}

            }
        });
        return view;
    }
}