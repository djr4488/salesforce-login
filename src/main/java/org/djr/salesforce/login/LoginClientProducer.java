package org.djr.salesforce.login;

import org.djr.retrofit2ee.jackson.RetrofitJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

@ApplicationScoped
public class LoginClientProducer {
    private static final Logger log = LoggerFactory.getLogger(LoginClientProducer.class);
    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "SalesForceLoginClientProducer_captureTraffic",
            baseUrlPropertyName = "SalesForceLoginClientProducer_baseUrl")
    private Retrofit retrofit;

    @Produces
    @LoginClientQualifier
    public LoginClient produceLoginClient(InjectionPoint ip) {
        log.trace("produceLoginClient() producing login client for class:{}", ip.getMember().getDeclaringClass().getCanonicalName());
        return retrofit.create(LoginClient.class);
    }
}
