package oms.repository;

/**
 * Database Connection Configuration.
 * (Requirement F15).
 *
 * Every repository implementation reads its connection details from this
 * class, so moving the system to a different host, schema or credential set is
 * a one-file change. In the legacy design the connection details were implicit
 * inside {@code save()}, which meant a configuration change required editing
<<<<<<< HEAD
 * business logic &mdash; a classic <em>Shotgun Surgery</em> smell.</p>

=======
 * business logic ; a classic Shotgun Surgery smell.
 *
 * @author Stanley
>>>>>>> 8126633b0cafaccd14caea4afac5f740356ad2c2
 */
public final class DatabaseConfig {

    /** JDBC connection URL. */
    private static final String URL = "jdbc:postgresql://localhost:5432/oms_db";

    /** Database account used by the application. */
    private static final String USERNAME = "oms_user";

    /** Password for the application account. */
    private static final String PASSWORD = "oms_password";

    /** Utility class: never instantiated. */
    private DatabaseConfig() {
        throw new AssertionError("DatabaseConfig must not be instantiated");
    }

    /**
     * @return the JDBC connection URL
     */
    public static String url() {
        return URL;
    }

    /**
     * @return the database user name
     */
    public static String username() {
        return USERNAME;
    }

    /**
     * @return the database password
     */
    public static String password() {
        return PASSWORD;
    }
}