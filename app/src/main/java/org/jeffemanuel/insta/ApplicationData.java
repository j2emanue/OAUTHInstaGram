package org.jeffemanuel.insta;

import BaseClasses.BaseConstants;

public class ApplicationData extends BaseConstants{
	public static final String CLIENT_ID = "63fc61e8b09b442f97019aab49da5af4";
	public static final String CLIENT_SECRET = "d96e2f43936940c692cac98b2ec2acba";//TODO: probably should hide this
	public static final String CALLBACK_URL = "http://www.org5433.ca";


    //uri segments for access token request
    public static final String AUTHORITY = "instagram.com";
    public static final String PATH = "/oauth/authorize";
    public static final String CLIENTID_QUERY= "client_id";
    public static final String URL_REDIRECT = "http://www.org5433.ca";
public static final String REDIRECT_QUERY = "redirect_uri";
    public static final String REQUEST_TYPE_QUERY = "response_type";
    public static final String REQUEST_TYPE = "token";
    public static final String SCHEME = "https";
    public static final String ACCESS_TOKEN_QUERY = "access_token";

    //uri segments for retrieving most recents tags of #selfie



}
