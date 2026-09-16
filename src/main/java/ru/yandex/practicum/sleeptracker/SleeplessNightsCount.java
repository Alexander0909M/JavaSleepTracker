import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class SleeplessNightsCount implements Function<List<SleepingSession>, Integer> {
    @Override
    public Integer apply(List<SleepingSession> sleepingSessions) {
        TimePeriod nt = new TimePeriod(LocalTime.of(0,0), LocalTime.of(6, 0));
        SessionsBatch sleepingSessionsBatch = new SessionsBatch(sleepingSessions);
        long result = sleepingSessionsBatch.getNightDates().stream()
                .map(night -> {
                    List<SleepingSession> ss = sleepingSessionsBatch.getSessionsByDate().get(night).stream()
                            .filter(sleepingSession -> !nt.contains(sleepingSession.getPeriod()))
                            .toList();
                    return !ss.isEmpty() ? 1 : 0;
                })
                .filter(night -> night == 1)
                .count();
        return (int) result;
    }
}
