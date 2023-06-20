package gov.iti.career.hub.authroizationserver.entities.enums;

public enum AuthorizationGrantType {

    AUTHORIZATION_CODE("authorization_code"),
    REFRESH_TOKEN("refresh_token"),
    CLIENT_CREDENTIALS("client_credentials"),
    JWT_BEARER("urn:ietf:params:oauth:grant-type:jwt-bearer"),
    DEVICE_CODE("urn:ietf:params:oauth:grant-type:device_code");
    private final String type;

    AuthorizationGrantType(String type) {
        this.type = type;
    }

    public String getValue() {
        return type;
    }
}
