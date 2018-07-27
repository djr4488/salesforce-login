package org.djr.salesforce.login;

import org.djr.cdi.logs.LoggerProducer;
import org.djr.cdi.properties.Config;
import org.djr.cdi.properties.PropertyResolver;
import org.djr.cdi.properties.database.DatabasePropertiesLoader;
import org.djr.cdi.properties.database.EntityManagerProducer;
import org.djr.cdi.properties.decrypt.DefaultDecryptor;
import org.djr.cdi.properties.environment.EnvironmentPropertiesLoader;
import org.djr.cdi.properties.file.FileProperties;
import org.djr.cdi.properties.file.FilePropertiesLoader;
import org.djr.retrofit2ee.RetrofitProperties;
import org.djr.retrofit2ee.RetrofitPropertyLoader;
import org.djr.retrofit2ee.jackson.JsonRetrofitProducer;
import org.djr.salesforce.AlternativePropertiesProducer;
import org.djr.salesforce.login.SalesforceLoginException;
import org.djr.salesforce.login.alternatives.AlternativeLoginClientProducer;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import retrofit2.Call;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.io.IOException;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.when;

@RunWith(CdiRunner.class)
@AdditionalClasses({ PropertyResolver.class, Config.class, FilePropertiesLoader.class, LoggerProducer.class,
        RetrofitProperties.class, FileProperties.class, EnvironmentPropertiesLoader.class, DatabasePropertiesLoader.class,
        EntityManagerProducer.class, DefaultDecryptor.class })
public class LoginClientIOExTest {
    @Mock
    private LoginClient loginClient;
    @Mock
    private Call<LoginToken> mockCall;
    @Inject
    private LoginTokenService loginTokenService;

    @Produces
    @LoginClientQualifier
    public LoginClient produceLoginClient() {
        return loginClient;
    }

    @Test(expected = SalesforceLoginException.class)
    public void testWhenIOExceptionOccurs() {
        try {
            when(loginClient.getLoginToken(anyMap(), anyMap())).thenReturn(mockCall);
            when(mockCall.execute()).thenThrow(new IOException("test"));
        } catch (IOException ioEx) {
            fail("Failed with IOEx during test, not expected");
        }
        loginTokenService.login();
    }
}
