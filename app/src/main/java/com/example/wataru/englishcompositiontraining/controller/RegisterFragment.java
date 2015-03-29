package com.example.wataru.englishcompositiontraining.controller;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.wataru.englishcompositiontraining.R;
import com.example.wataru.englishcompositiontraining.dao.QuizDao;
import com.example.wataru.englishcompositiontraining.model.SentenceDto;

import org.apache.commons.lang.StringUtils;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {
    private Spinner spLevel;
    private EditText etEnWord;
    private EditText etJaWord;


    private static final String ARG_SECTION_NUMBER = "section_number";


    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param sectionNumber Parameter 1.
     * @return A new instance of fragment RegisterFragment.
     */
    public static RegisterFragment newInstance(int sectionNumber) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);

        etEnWord = (EditText)view.findViewById(R.id.et_en_word);
        etJaWord = (EditText) view.findViewById(R.id.et_ja_word);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.addAll(getResources().getStringArray(R.array.sp_levels));

        spLevel = (Spinner) view.findViewById(R.id.sp_level);
        spLevel.setAdapter(adapter);

        view.findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View buttonView) {
                onClickRegister();
            }
        });;

        return view;
    }

    private void onClickRegister() {

        final String UNSAFE_EN_WORD = etEnWord.getText().toString();
        final String UNSAFE_JA_WORD = etJaWord.getText().toString();
        if (StringUtils.isEmpty(UNSAFE_EN_WORD.trim())) {
            Toast.makeText(getActivity(), getString(R.string.english_word) + " " + getString(R.string.not_entered), Toast.LENGTH_SHORT).show();
            return;
        }
        if (!UNSAFE_EN_WORD.matches("[a-zA-Z]")) {
            Toast.makeText(getActivity(), getString(R.string.english_word) + " " + getString(R.string.infelicity), Toast.LENGTH_SHORT).show();
            return;
        }
        final String TRUSTED_EN_WORD = UNSAFE_EN_WORD;
        if (StringUtils.isEmpty(UNSAFE_JA_WORD.trim())) {
            Toast.makeText(getActivity(), getString(R.string.japanese_word) + " " + getString(R.string.not_entered), Toast.LENGTH_SHORT).show();
            return;
        }
        final String TRUSTED_JA_WORD = UNSAFE_JA_WORD;
        final int TRUSTED_LEVEL = spLevel.getSelectedItemPosition();
        QuizDao dao = new QuizDao(getActivity());
        SentenceDto dto = new SentenceDto();
        dto.setEnWord(TRUSTED_EN_WORD);
        dto.setJaWord(TRUSTED_JA_WORD);
        dto.setLevel(TRUSTED_LEVEL);
//        TODO: TenseとCategoryを追加する
        dao.insert(dto);
        Toast.makeText(getActivity(), getString(R.string.not_entered), Toast.LENGTH_SHORT).show();

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
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public static interface FragmentCallbacks {
        public void onHogehoge();
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
