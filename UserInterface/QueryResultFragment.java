package com.example.basketball_quiz.UserInterface;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


import com.example.basketball_quiz.MainActivity;
import com.example.basketball_quiz.R;
import com.example.basketball_quiz.database.ResultIntString;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QueryResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QueryResultFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int Acid;

    private int Sunth;

    private String Text;

    public QueryResultFragment() {
        // Required empty public constructor
    }

    public QueryResultFragment(int sunth) {
        Sunth = sunth;
    }

    public QueryResultFragment(int sunth, int acid) {

        Sunth = sunth;

        Acid = acid;
    }

    public QueryResultFragment(int sunth, String text) {

        Sunth = sunth;

        Text = text;
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
    public static QueryResultFragment newInstance(String param1, String param2) {
        QueryResultFragment fragment = new QueryResultFragment();
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




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_query_result, container, false);
        textView = view.findViewById(R.id.textView3QR);
        textView1 = view.findViewById(R.id.textView4QR);

if(Sunth == 1){
        String said = "";
        String sTitle = "";



        List<ResultIntString> ArtByCid = MainActivity.myAppDatabase.myDao().getArticlesByCid(Acid);

        for (ResultIntString i: ArtByCid) {

            int aid = i.getField1();
            String Title = i.getField2();


            said = said  + aid + "\n\n\n" ;
            sTitle = sTitle  + Title + "\n\n\n" ;

        }


        textView.setText(said);
        textView1.setText(sTitle);}

else if (Sunth == 2){


    textView2 = view.findViewById(R.id.textViewQR);
    textView3 = view.findViewById(R.id.textView2QR);

    textView2.setText("Count");
    textView3.setText("Category Name");


    String scount = "";
    String sName = "";





    List<ResultIntString> CountCat = MainActivity.myAppDatabase.myDao().getCountCid();

    for (ResultIntString i: CountCat) {

        int count = i.getField1();
        String Name = i.getField2();


        scount = scount  + count + "\n\n\n" ;
        sName = sName  + Name + "\n\n\n" ;

    }

    textView.setText(scount);
    textView1.setText(sName);}

else if (Sunth == 3){


    textView2 = view.findViewById(R.id.textViewQR);
    textView3 = view.findViewById(R.id.textView2QR);

    textView2.setText("Question Id");
    textView3.setText("Question Text");


    String sid = "";
    String sText = "";


    List<ResultIntString> Qtext = MainActivity.myAppDatabase.myDao().getQuestionText(Text);

    for (ResultIntString i: Qtext) {

        int id = i.getField1();
        String text = i.getField2();


        sid = sid  + id + "\n\n\n" ;
        sText = sText  + text + "\n\n\n" ;

    }

    textView.setText(sid);
    textView1.setText(sText);}





        return view;
    }}
