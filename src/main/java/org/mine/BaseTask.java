package org.mine;

import org.mine.util.LineReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;

public abstract class BaseTask {
    protected static final Logger _LOGGER = LoggerFactory.getLogger(BaseTask.class.getSimpleName());

    protected final LineReader _reader;
    protected long _result = 0L;

    public BaseTask() {
        _reader = LineReader.readerForDefaultFile(this.getClass().getSimpleName());
    }

    public final void solveMeasured() {
        if(!getDisabledReason().isBlank()) {
            _LOGGER.warn(STR."\{this.getClass().getSimpleName()} is disabled | \{getDisabledReason()}");
            return;
        }
        Instant start = Instant.now();
        solve();
        Instant finish = Instant.now();
        _LOGGER.info(STR."\{this.getClass().getSimpleName()} | \{_result} | \{Duration.between(start, finish).toMillis()}ms");
        _reader.cleanup();
    }

    protected abstract void solve();

    protected String getDisabledReason() {
        return "";
    }
}
