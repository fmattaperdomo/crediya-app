package net.fmattaperdomo.model.application.gateways;

public interface LoggerRepository {
    void info(String message, Object... args);
    void error(String message, Object... args);
}
