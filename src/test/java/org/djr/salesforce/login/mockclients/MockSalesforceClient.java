package org.djr.salesforce.login.mockclients;

import org.djr.salesforce.login.LoginClient;
import org.djr.salesforce.login.LoginToken;
import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

import java.util.HashMap;
import java.util.Map;

public class MockSalesforceClient implements LoginClient {
    private BehaviorDelegate<LoginClient> behaviorDelegate;

    public MockSalesforceClient(BehaviorDelegate<LoginClient> behaviorDelegate) {
        this.behaviorDelegate = behaviorDelegate;
    }

    public Call<LoginToken> getLoginToken(Map<String, String> headerMap, Map<String, String> queryMap) {
        LoginToken loginToken = new LoginToken("test_token", "https://instance.salesforce.com", "test_id",
                "test_token_type", "123456", "test_signature");
        return behaviorDelegate.returningResponse(loginToken).getLoginToken(new HashMap<>(), new HashMap<>());
    }
}
