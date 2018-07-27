package org.djr.salesforce;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.Properties;

@ApplicationScoped
public class AlternativePropertiesProducer {
    private Properties props = new Properties();

    @Produces
    public String producesString(InjectionPoint ip) {
        String simpleClassName = ip.getMember().getDeclaringClass().getSimpleName();
        String field = ip.getMember().getName();
        String value = null;
        if (simpleClassName.equalsIgnoreCase("LoginTokenService")) {
            value = handleLoginTokenService(field);
        }
        return value;
    }

    private String handleLoginTokenService(String field) {
        String value = null;
        switch(field) {
            case "username": {
                value = "username@email.com";
                break;
            }
            case "password": {
                value = "password";
                break;
            }
            case "userToken": {
                value = "token";
                break;
            }
            case "clientId": {
                value = "clientId\n";
                break;
            }
            case "clientSecret": {
                value = "clientSecret";
                break;
            }
            case "grantType": {
                value = "password";
                break;
            }
        }
        return value;
    }
}
