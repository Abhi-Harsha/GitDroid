package com.abhishek.gitdriod.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abhishek.gitdriod.Model.GitHubRepositoriesResponseBody;
import com.abhishek.gitdriod.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RepoDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RepoDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RepoDetailsFragment extends Fragment {

    private TextView repoNameTextView;
    private TextView repoDescriptionTextView;
    private TextView watcherLabelTextView;
    private TextView watcherNumberTextView;
    private TextView forkLabelTextView;
    private TextView forkNumberTextView;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "com.repo.info";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private GitHubRepositoriesResponseBody _repository;

    private OnFragmentInteractionListener mListener;

    public RepoDetailsFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment RepoDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RepoDetailsFragment newInstance(GitHubRepositoriesResponseBody repository) {
        RepoDetailsFragment fragment = new RepoDetailsFragment();
        fragment.setRepositoryDetails(repository);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, "some string");
        fragment.setArguments(args);
        return fragment;
    }

    private void setRepositoryDetails(GitHubRepositoriesResponseBody repository) {
        this._repository = repository;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            //mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_repo_details, container, false);
        repoNameTextView = (TextView)view.findViewById(R.id.repoNameTextView);
        repoDescriptionTextView = (TextView)view.findViewById(R.id.repoDescriptionTextView);
        watcherLabelTextView = (TextView)view.findViewById(R.id.watcherLabelTextView);
        watcherNumberTextView = (TextView)view.findViewById(R.id.watchersNumberTextView);
        forkLabelTextView = (TextView)view.findViewById(R.id.forkLabelTextView);
        forkNumberTextView = (TextView)view.findViewById(R.id.forksNumberTextView);

        repoNameTextView.setText(_repository.getName());
        repoDescriptionTextView.setText(_repository.getDescription());
        if (_repository.getWatchers() != null) {
            watcherNumberTextView.setText(String.valueOf(_repository.getWatchers()));
        }
        if(_repository.getFork() != null) {
            forkNumberTextView.setText(String.valueOf(_repository.getForks()));
        }

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
