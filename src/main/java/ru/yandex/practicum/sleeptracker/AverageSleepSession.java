import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class AverageSleepSession implements Function<List<SleepingSession>, Duration> {
    @Override
    public Duration apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()) return Duration.ZERO;
        double averageDouble = sleepingSessions.stream()
                .map(session -> Duration.between(session.startSleep, session.endSleep))
                .mapToLong(Duration::toMinutes)
                .average()
                .orElse(0.0);
        return Duration.ofMinutes((long) averageDouble);
    }
}