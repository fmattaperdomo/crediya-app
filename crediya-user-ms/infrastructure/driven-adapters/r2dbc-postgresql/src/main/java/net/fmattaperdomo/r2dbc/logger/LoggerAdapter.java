package net.fmattaperdomo.r2dbc.logger;

import lombok.extern.slf4j.Slf4j;
import net.fmattaperdomo.model.user.gateways.LoggerRepository;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoggerAdapter implements LoggerRepository {

    @Override
    public void info(String message, Object... args) {
        log.info(message, args);
    }

    @Override
    public void error(String message, Object... args) {
        log.error(message, args);
    }
}
