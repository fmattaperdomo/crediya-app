package net.fmattaperdomo.model.user.gateways;

public interface LoggerRepository {
    void info(String message, Object... args);
    void error(String message, Object... args);
}
