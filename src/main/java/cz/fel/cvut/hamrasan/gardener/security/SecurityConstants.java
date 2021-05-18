package cz.fel.cvut.hamrasan.gardener.security;

public class SecurityConstants {

    private SecurityConstants() {
        throw new AssertionError();
    }

    public static final String SESSION_COOKIE_NAME = "BC_JSESSIONID";

    public static final String REMEMBER_ME_COOKIE_NAME = "remember-me";

    //    public static final String USERNAME_PARAM = "username";
    public static final String USERNAME_PARAM = "email";


    public static final String PASSWORD_PARAM = "password";

    public static final String SECURITY_CHECK_URI = "/j_spring_security_check";

    public static final String LOGOUT_URI = "/logout";

    public static final String COOKIE_URI = "/";

    public static final String ORIGIN_URI = "http://localhost:3000";

    /**
     * Session timeout in seconds.
     */
    public static final int SESSION_TIMEOUT = 30 * 60;
}
