package org.djr.salesforce.login;

import retrofit2.Call;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface LoginClient {
    @POST("services/oauth2/token")
    Call<LoginToken> getLoginToken(@HeaderMap Map<String, String> headerMap, @QueryMap Map<String, String> queryMap);
}
