import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SleepingProfile implements Function<List<SleepingSession>, String> {
    private final String owl = "Сова";
    private final String lark = "Жаворонок";
    private final String pigeon = "Голубь";

    @Override
    public String apply(List<SleepingSession> sleepingSessions) {
        SessionsBatch sleepingSessionsBatch = new SessionsBatch(sleepingSessions);
        List<String> nightTypes = sleepingSessionsBatch.getNightDates().stream()
                .map(night -> String.valueOf(sleepingSessionsBatch.getSessionsByDate().get(night).stream()
                        .map(sleepingSession -> {
                            if (
                                    sleepingSession.getStartSleep().toLocalTime().isAfter(LocalTime.of(23, 0)) &&
                                            sleepingSession.getEndSleep().toLocalTime().isAfter(LocalTime.of(9, 0))
                            ) {
                                return owl;
                            }
                            else if (
                                    sleepingSession.getStartSleep().toLocalTime().isBefore(LocalTime.of(22, 0)) &&
                                            sleepingSession.getEndSleep().toLocalTime().isBefore(LocalTime.of(7, 0))
                            ) {
                                return lark;
                            } else {
                                return pigeon;
                            }
                        })
                        .collect(Collectors.joining())))
                .toList();
        long owlNights = nightTypes.stream()
                .filter(nightType -> nightType.equals(owl))
                .count();
        long pigeonNights = nightTypes.stream()
                .filter(nightType -> nightType.equals(pigeon))
                .count();
        long larkNights = nightTypes.stream()
                .filter(nightType -> nightType.equals(lark))
                .count();
        if (owlNights > larkNights && owlNights > pigeonNights) {
            return owl;
        } else if (larkNights > owlNights && larkNights > pigeonNights) {
            return lark;
        } else {
            return pigeon;
        }
    }
}
