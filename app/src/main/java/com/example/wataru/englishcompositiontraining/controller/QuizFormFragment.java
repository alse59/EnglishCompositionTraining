package com.example.wataru.englishcompositiontraining.controller;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.wataru.englishcompositiontraining.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuizFormFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuizFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizFormFragment extends Fragment {
    Spinner sp_translation;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SECTION_NUMBER = "section_number";

    private OnFragmentInteractionListener mListener;
    // コールバック用のinterfaceを定義
    public interface OnMenuItemSelectedListener {
        public void onMenuItemSelected(int position, String text);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param sectionNumber Parameter 1.
     * @return A new instance of fragment QuizFormFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizFormFragment newInstance(int sectionNumber) {
        QuizFormFragment fragment = new QuizFormFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public QuizFormFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz_form, container, false);
        //Spinner translationの設定
        sp_translation = (Spinner)view.findViewById(R.id.sp_translation);

        ArrayAdapter<String> adapterTranslation = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
        adapterTranslation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterTranslation.addAll(getResources().getStringArray(R.array.sp_translations));
        sp_translation.setAdapter(adapterTranslation);

        //Spinner ReadWriteTyの設定
        Spinner sp_read_write_ty = (Spinner)view.findViewById(R.id.sp_read_write_ty);

        ArrayAdapter<String> adapterReadWriteTy = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
        adapterReadWriteTy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterReadWriteTy.addAll(getResources().getStringArray(R.array.sp_read_write_tys));
        sp_read_write_ty.setAdapter(adapterReadWriteTy);

        // Button Startの設定
        Button btn_start = (Button)view.findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStart();

            }
        });
        return view;
    }

    private void onClickStart() {
        QuizFragment fragment = QuizFragment.newInstance(1, sp_translation.getSelectedItemPosition());
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(
                R.id.container, fragment).commit();
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
