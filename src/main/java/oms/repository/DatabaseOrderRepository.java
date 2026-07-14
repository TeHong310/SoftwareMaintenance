package oms.repository;

import java.util.Objects;

import oms.model.Order;

/**
 * Database-backed {@link OrderRepository} 
 * (Requirements F6, F7, F15).
 *
 * Connection details are never hard-coded here; they are obtained from
 * {@link DatabaseConfig}, the single location in which configuration changes
 * are made (F15). The JDBC call itself is stubbed with the same console output
 * produced by the legacy {@code save()} method, so the observable behaviour of
<<<<<<< HEAD
<<<<<<< HEAD
 * the system is preserved by the refactoring.</p>

=======
 * the system is preserved by the refactoring.
 *
 * @author Stanley
>>>>>>> 8126633b0cafaccd14caea4afac5f740356ad2c2
=======
 * the system is preserved by the refactoring.
 *
 * @author Stanley
>>>>>>> 8126633b0cafaccd14caea4afac5f740356ad2c2
 */
public final class DatabaseOrderRepository implements OrderRepository {

    /** The connection URL this repository writes to. */
    private final String connectionUrl;

    /**
     * Creates a repository using the application-wide configuration (F15).
     */
    public DatabaseOrderRepository() {
        this(DatabaseConfig.url());
    }

    /**
     * Creates a repository against an explicit URL, which allows an integration
     * test to point at a different schema without altering production settings.
     *
     * @param connectionUrl the JDBC URL to use; must not be {@code null}
     */
    public DatabaseOrderRepository(final String connectionUrl) {
        this.connectionUrl = Objects.requireNonNull(connectionUrl, "connectionUrl is required");
    }

    @Override
    public void save(final Order order) {
        Objects.requireNonNull(order, "order is required");
        // A real implementation would open a JDBC connection to connectionUrl
        // and execute an INSERT here. The console output below preserves the
        // observable behaviour of the legacy save() method.
        System.out.println("Saved to DB");
    }

    /**
     * @return the connection URL in use; exposed so tests can prove that
     *         configuration is resolved from {@link DatabaseConfig} (F15)
     */
    public String connectionUrl() {
        return connectionUrl;
    }
}