package com.example.wataru.englishcompositiontraining.controller;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.wataru.englishcompositiontraining.R;
import com.example.wataru.englishcompositiontraining.common.CommonConstants;
import com.example.wataru.englishcompositiontraining.dao.QuizDao;
import com.example.wataru.englishcompositiontraining.model.SentenceDto;

import java.util.List;


/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuizFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class QuizFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_QUIZ_FORM = "quiz_form";
    private TextView textView;
    private List<SentenceDto> safeList;
    private SentenceDto quiz;
    private int listIndex;
    private Button quizButton;
    private int quizForm;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @param sectionNumber Parameter 1.
     * @return A new instance of fragment WritingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizFragment newInstance(int sectionNumber, int quizForm) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putInt(ARG_QUIZ_FORM, quizForm);
        fragment.setArguments(args);
        return fragment;
    }
    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public boolean validateList(List<SentenceDto> unSafeList) {
        if (unSafeList == null || unSafeList.size() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quiz, container, false);
        textView = (TextView)v.findViewById(R.id.textView);
        QuizDao dao = new QuizDao(getActivity());
        //問題を取得して存在するか確認する
        List<SentenceDto>unSafeList = dao.searchQuiz(null);
        if (validateList(unSafeList)) {
            safeList = unSafeList;
        } else {
            textView.setText("問題が存在しないという画面を出力する");
        }
        //問題を1問取得する
        quiz = safeList.get(listIndex++);
        quizForm = getArguments().getInt(ARG_QUIZ_FORM);
        if (quizForm == CommonConstants.READING) {
            String enWord = quiz.getEnWord();
            textView.setText(enWord);
        } else if (quizForm == CommonConstants.SPEAKING) {
            String jaWord = quiz.getJaWord();
            textView.setText(jaWord);
        }


        quizButton = (Button)v.findViewById(R.id.quiz_button);
        quizButton.setOnClickListener( new View.OnClickListener() {
            private static final String ANSWER_BUTTON = "Answer";
            private static final String NEXT_BUTTON = "Next";

            @Override
            public void onClick(View view) {
                if (quizForm == CommonConstants.READING) {
                    if (quizButton.getText().toString().equals(ANSWER_BUTTON)) {
                        textView.setText(quiz.getJaWord());
                        quizButton.setText(NEXT_BUTTON);
                    } else if (quizButton.getText().toString().equals(NEXT_BUTTON)) {
                        if (safeList.size() > listIndex) {
                            quiz = safeList.get(listIndex++);
                            textView.setText(quiz.getEnWord());
                            quizButton.setText(ANSWER_BUTTON);
                        } else {
                            textView.setText("終了画面を表示する");
                        }

                    }
                } else if (quizForm == CommonConstants.SPEAKING) {
                    if (quizButton.getText().toString().equals(ANSWER_BUTTON)) {
                        textView.setText(quiz.getEnWord());
                        quizButton.setText(NEXT_BUTTON);
                    } else if (quizButton.getText().toString().equals(NEXT_BUTTON)) {
                        if (safeList.size() > listIndex) {
                            quiz = safeList.get(listIndex++);
                            textView.setText(quiz.getJaWord());
                            quizButton.setText(ANSWER_BUTTON);
                        } else {
                            textView.setText("終了画面を表示する");
                        }

                    }
                }
            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached( getArguments().getInt(ARG_SECTION_NUMBER));
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
