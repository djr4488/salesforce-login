package org.djr.salesforce.login;

import org.djr.retrofit2ee.RetrofitPropertyLoader;
import org.djr.retrofit2ee.jackson.JsonRetrofitProducer;
import org.djr.salesforce.AlternativePropertiesProducer;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static junit.framework.Assert.assertNotNull;

@RunWith(CdiRunner.class)
@AdditionalClasses( { LoginClient.class, LoginClientProducer.class, JsonRetrofitProducer.class,
        AlternativePropertiesProducer.class, RetrofitPropertyLoader.class } )
public class LoginClientProducerTest {
    @Inject
    @LoginClientQualifier
    private LoginClient loginClient;

    @Test
    public void testLoginClientNotNull() {
        assertNotNull(loginClient);
    }
}
