package gov.iti.career.hub.authroizationserver.entities.enums;

public enum ClientAuthenticationMethod {

    CLIENT_SECRET_BASIC("client_secret_basic"),
    CLIENT_SECRET_POST("client_secret_post"),
    CLIENT_SECRET_JWT("client_secret_jwt"),
    PRIVATE_KEY_JWT("private_key_jwt"),
    NONE("none");

    private final String method;

    ClientAuthenticationMethod(String method) {
        this.method = method;
    }

    public String getValue() {
        return method;
    }

}
