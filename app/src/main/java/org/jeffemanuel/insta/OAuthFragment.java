package org.jeffemanuel.insta;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import BaseClasses.BaseFragment;

/**
 * contacts instagram to request access token.  with proper client ID on the redirect we over ride the url before its loaded
 * and parse the access token and notify listeners of the OAuthDialogListener interface.
 */
public class OAuthFragment extends BaseFragment {

    private WebView mWebView;
    private String mUrl;
    private String mTitle;
    private OAuthDialogListener mListener;
    private ProgressDialog mSpinner;
    private String TAG = this.getClass().getSimpleName();



    public static OAuthFragment newInstance(String url) {
        OAuthFragment fragment = new OAuthFragment();
        Bundle args = new Bundle();
        args.putSerializable("URL", url);
        fragment.setArguments(args);
        return fragment;
    }

    public OAuthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LayoutInflater mInflater = (LayoutInflater) getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        LinearLayout view =  (LinearLayout) mInflater.inflate(R.layout.webview_frag,null);
        mWebView = (WebView) view.findViewById(R.id.wv_instagram);
        mWebView.setWebViewClient(new OAuthWebViewClient());
        mUrl = this.getArguments().getString("URL");
        mWebView.loadUrl(mUrl);
        if(getActivity()!=null)
        mSpinner = new ProgressDialog(getActivity());
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OAuthDialogListener)activity;
        } catch (ClassCastException e){
        throw new ClassCastException(activity.toString() + " must implement  OAuthDialogListener");
    }

    }


    public void loadUrl(String url) {
        mWebView.loadUrl(url);
    }

    public void loadUrl(){
        if(mUrl!=null)
        mWebView.loadUrl(mUrl);}

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }


    private class OAuthWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d(TAG, "Redirecting URL " + url);

            if (url.startsWith(ApplicationData.CALLBACK_URL)) {
                String urls[] = url.split("=");
                mListener.onComplete(urls[1]);

                if(mSpinner!=null && mSpinner.isShowing())
                    mSpinner.dismiss();
                return true;
            }
            return false;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            Log.d(TAG, "Page error: " + description);

            super.onReceivedError(view, errorCode, description, failingUrl);
            mListener.onError(description);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.d(TAG, "Loading URL: " + url);
            mSpinner.show();
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d(TAG, "onPageFinished URL: " + url);
            mSpinner.dismiss();
        }
    }


        public interface OAuthDialogListener {
            public abstract void onComplete(String accessToken);
            public abstract void onError(String error);
        }

}
