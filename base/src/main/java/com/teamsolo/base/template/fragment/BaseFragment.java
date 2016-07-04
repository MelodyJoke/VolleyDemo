package com.teamsolo.base.template.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.View;

import com.teamsolo.base.utility.log.LogUtility;

import org.jetbrains.annotations.NotNull;

/**
 * description: base fragment
 * author: Melody
 * date: 2016/6/25
 * version: 0.0.0.1
 */
@SuppressWarnings("unused, WeakerAccess")
public abstract class BaseFragment extends Fragment {

    protected Context mContext;

    protected View mLayoutView;

    protected OnFragmentInteractionListener mListener;

    public BaseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtility.i(getClass().getSimpleName(), "onCreate");
        getBundle(getArguments());
    }

    protected abstract void getBundle(@NotNull Bundle bundle);

    protected abstract void initViews();

    protected abstract void bindListeners();

    @LayoutRes
    protected abstract int getLayoutRes();

    public void onInteraction(Uri uri) {
        LogUtility.i(getClass().getSimpleName(), "onInteraction");
        if (mListener != null) mListener.onFragmentInteraction(uri);
    }

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);

        LogUtility.i(getClass().getSimpleName(), "onAttach");
        if (context instanceof OnFragmentInteractionListener)
            mListener = (OnFragmentInteractionListener) context;
        else throw new RuntimeException(context.toString()
                + " must implement OnFragmentInteractionListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        LogUtility.i(getClass().getSimpleName(), "onDetach");
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
