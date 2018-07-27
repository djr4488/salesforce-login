package org.djr.salesforce.login.alternatives;

import org.djr.retrofit2ee.jackson.RetrofitJson;
import org.djr.salesforce.login.LoginClient;
import org.djr.salesforce.login.LoginClientQualifier;
import org.djr.salesforce.login.mockclients.MockSalesforceClient;
import retrofit2.Retrofit;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class AlternativeLoginClientProducer {
    private NetworkBehavior networkBehavior;
    private MockRetrofit mockRetrofit;
    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "SalesForceLoginClientProducer_captureTraffic",
                  baseUrlPropertyName = "SalesForceLoginClientProducer_baseUrl")
    private Retrofit retrofit;


    @Produces
    @LoginClientQualifier
    public LoginClient loginClientProducer() {
        networkBehavior = NetworkBehavior.create();
        networkBehavior.setErrorPercent(0);
        networkBehavior.setFailurePercent(0);
        mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(networkBehavior)
                .build();
        BehaviorDelegate<LoginClient> behaviorDelegate = mockRetrofit.create(LoginClient.class);
        LoginClient mockLoginClient = new MockSalesforceClient(behaviorDelegate);
        return mockLoginClient;
    }
}
