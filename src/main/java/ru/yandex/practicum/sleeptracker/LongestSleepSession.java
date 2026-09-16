import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class LongestSleepSession implements Function<List<SleepingSession>, Duration> {
    @Override
    public Duration apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()) return Duration.ZERO;
        return sleepingSessions.stream()
                .map(session -> Duration.between(session.startSleep, session.endSleep))
                .max(Duration::compareTo)
                .orElse(null);
    }
}
