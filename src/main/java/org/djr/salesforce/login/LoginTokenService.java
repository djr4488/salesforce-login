package org.djr.salesforce.login;

import org.djr.cdi.properties.Config;
import org.djr.salesforce.login.SalesforceLoginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class LoginTokenService {
    private static final Logger log = LoggerFactory.getLogger(LoginTokenService.class);
    @Inject
    @LoginClientQualifier
    private LoginClient loginClient;
    @Inject
    @Config(propertyName = "LoginTokenService_username", defaultValue = "username")
    private String username;
    @Inject
    @Config(propertyName = "LoginTokenService_password", defaultValue = "password")
    private String password;
    @Inject
    @Config(propertyName = "LoginTokenService_userToken", defaultValue = "userToken")
    private String userToken;
    @Inject
    @Config(propertyName = "LoginTokenService_clientId", defaultValue = "clientId")
    private String clientId;
    @Inject
    @Config(propertyName = "LoginTokenService_clientSecret", defaultValue = "clientSecret")
    private String clientSecret;
    @Inject
    @Config(propertyName = "LoginTokenService_grantType", defaultValue = "grantType")
    private String grantType;
    private Map<String, String> queryMap;
    private Map<String, String> headerMap;

    @PostConstruct
    private void getQueryMap() {
        queryMap = new HashMap<>();
        queryMap.put("username", username);
        queryMap.put("password", password + userToken);
        queryMap.put("client_id", clientId);
        queryMap.put("client_secret", clientSecret);
        queryMap.put("grant_type", grantType);
        headerMap = new HashMap<>();
        headerMap.put("Content-Type", MediaType.APPLICATION_JSON);
        headerMap.put("Accept", MediaType.APPLICATION_JSON);
    }

    public LoginToken login() {
        log.debug("login() begin");
        LoginToken loginToken = null;
        try {
            Response<LoginToken> response = loginClient.getLoginToken(headerMap, queryMap).execute();
            if (response.isSuccessful()) {
                loginToken = response.body();
            } else {
                log.debug("login() failed with response ", response.message());
                throw new SalesforceLoginException("Failed to login with ", response.message(), response.code());
            }
        } catch (IOException ioEx) {
            throw new SalesforceLoginException("Failed to login with io exception", ioEx);
        }
        return loginToken;
    }
}
