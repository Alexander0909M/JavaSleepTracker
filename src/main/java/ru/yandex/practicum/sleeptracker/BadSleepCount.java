import java.util.List;
import java.util.function.Function;

public class BadSleepCount implements Function<List<SleepingSession>, Integer> {
    @Override
    public Integer apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()) return 0;
        return sleepingSessions.stream()
                .filter(sleepingSession -> sleepingSession.getSleepQuality() == SleepQuality.BAD)
                .toList().size();
    }
}
