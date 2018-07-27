package org.djr.salesforce.login.mockclients;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.djr.salesforce.login.LoginClient;
import org.djr.salesforce.login.LoginToken;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.Calls;

import java.util.HashMap;
import java.util.Map;

public class MockSalesforceClientFailure implements LoginClient {
    private BehaviorDelegate<LoginClient> behaviorDelegate;

    public MockSalesforceClientFailure(BehaviorDelegate<LoginClient> behaviorDelegate) {
        this.behaviorDelegate = behaviorDelegate;
    }

    public Call<LoginToken> getLoginToken(Map<String, String> headerMap, Map<String, String> queryMap) {
        Response response = Response.error(404, ResponseBody.create(MediaType.parse("application/jackson") ,"{ }"));
        return behaviorDelegate.returning(Calls.response(response)).getLoginToken(new HashMap<>(), new HashMap<>());
    }
}
