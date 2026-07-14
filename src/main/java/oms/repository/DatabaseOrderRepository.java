package oms.repository;

import java.util.Objects;

import oms.model.Order;

/**
 * Database-backed {@link OrderRepository}.
 * (F6, F7, F15).
 *
 * Connection detail get from {@link DatabaseConfig}, not hardcoded here.
 * JDBC part still stub, just print same output as old save() method, so
 * behaviour still same after refactor.
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
        // real version will open JDBC connection and do INSERT here,
        // for now just print same output as legacy save()
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