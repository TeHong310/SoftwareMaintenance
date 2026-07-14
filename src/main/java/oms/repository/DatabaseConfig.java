package oms.repository;

/**
 * Database connection config.
 * (F15).
 *
 * All repository read connection detail from here, so change host or
 * credential just need edit one file. Old code hardcode this inside
 * save(), so any config change need touch business logic too. That's
 * Shotgun Surgery smell, now fixed.
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