package org.jeffemanuel.insta;

import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import BaseClasses.BaseActivity;


public class MainActivity extends BaseActivity implements OAuthFragment.OAuthDialogListener {

	private Button btnConnect;
	private TextView tvSummary;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
 {


     if (!isNetworkAvailable())
         showToast(getString(R.string.no_network));

     if (savedInstanceState == null) {

         Log.d("jeff",buildURL());
         OAuthFragment wv = OAuthFragment.newInstance(buildURL());
         getFragmentManager().beginTransaction()
                 .add(R.id.container, wv)
                 .commit();


     }

				}

	}



    private String buildURL(){
//e.g. https://instagram.com/oauth/authorize/?client_id=63fc61e8b09b442f97019aab49da5af4&redirect_uri=REDIRECT-URI&response_type=token

        return new Uri.Builder()
        	.scheme("https")
        	.authority(ApplicationData.AUTHORITY)
        	.path(ApplicationData.PATH)
        	.appendQueryParameter(ApplicationData.CLIENTID_QUERY,ApplicationData.CLIENT_ID)
                .appendQueryParameter(ApplicationData.REQUEST_TYPE_QUERY,ApplicationData.REQUEST_TYPE)
            .appendQueryParameter(ApplicationData.REDIRECT_QUERY, ApplicationData.URL_REDIRECT).build().toString();


    }

    @Override
    public void onComplete(String accessToken) {
        //this toast is to make evident i have acquired a token
        Toast.makeText(this,"Token Acquired:"+accessToken,Toast.LENGTH_LONG).show();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
        HashTagResultsFragment frag = HashTagResultsFragment.newInstance(accessToken);
        transaction.replace(R.id.container, frag);
        transaction.commit();

    }

    @Override
    public void onError(String error) {

    }
}