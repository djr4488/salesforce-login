package org.djr.salesforce.login;

import org.djr.cdi.logs.LoggerProducer;
import org.djr.cdi.properties.Config;
import org.djr.cdi.properties.PropertyResolver;
import org.djr.cdi.properties.database.DatabasePropertiesLoader;
import org.djr.cdi.properties.database.DatabasePropertyRetrievalService;
import org.djr.cdi.properties.database.EntityManagerProducer;
import org.djr.cdi.properties.decrypt.DefaultDecryptor;
import org.djr.cdi.properties.environment.EnvironmentPropertiesLoader;
import org.djr.cdi.properties.file.FileProperties;
import org.djr.cdi.properties.file.FilePropertiesLoader;
import org.djr.retrofit2ee.RetrofitProperties;
import org.djr.retrofit2ee.RetrofitPropertyLoader;
import org.djr.retrofit2ee.jackson.JsonRetrofitProducer;
import org.djr.salesforce.AlternativePropertiesProducer;
import org.djr.salesforce.login.alternatives.AlternativeLoginClientProducer;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(CdiRunner.class)
@AdditionalClasses( { LoginClient.class, AlternativeLoginClientProducer.class, JsonRetrofitProducer.class,
        PropertyResolver.class, Config.class, FilePropertiesLoader.class, LoggerProducer.class, RetrofitPropertyLoader.class,
        RetrofitProperties.class, FileProperties.class, EnvironmentPropertiesLoader.class, DatabasePropertiesLoader.class,
        EntityManagerProducer.class, DefaultDecryptor.class } )
public class LoginTest {
    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
    @Inject
    private LoginTokenService loginTokenService;

    @Test
    public void testLoginToSalesforce() {
        LoginToken loginToken = loginTokenService.login();
        assertNotNull(loginToken);
        assertEquals("https://instance.salesforce.com", loginToken.getInstanceUrl());
        assertEquals("test_token", loginToken.getAccessToken());
        assertEquals("test_id", loginToken.getId());
        assertEquals("123456", loginToken.getIssuedAt());
        assertEquals("test_signature", loginToken.getSignature());
        assertEquals("test_token_type", loginToken.getTokenType());
        log.debug("testLoginToSalesforce response:{}", loginToken);
    }
}
