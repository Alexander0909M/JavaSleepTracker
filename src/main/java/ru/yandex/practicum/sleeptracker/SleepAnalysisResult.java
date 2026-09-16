import java.time.Duration;
import java.util.List;

public class SleepAnalysisResult {
    public static final String ANSI_RED = "\033[4;31m";
    public static final String ANSI_GREEN = "\033[32m";
    public static final String ANSI_RESET = "\033[0m";
    final SessionsCounter sleepingSessionsCounter = new SessionsCounter();
    final ShortestSleepSession shortestSleepSession = new ShortestSleepSession();
    final LongestSleepSession longestSleepSession = new LongestSleepSession();
    final AverageSleepSession averageSleepSession = new AverageSleepSession();
    final BadSleepCount badSleepQualityCount = new BadSleepCount();
    final SleeplessNightsCount sleeplessNightsCount = new SleeplessNightsCount();
    final SleepingProfile countSleepingProfile = new SleepingProfile();
    private final int sessionsCount;
    private final Duration shortestSession;
    private final Duration longestSession;
    private final Duration averageSession;
    private final int badSessionsCount;
    private final int sleeplessCount;
    private final String profile;

    public SleepAnalysisResult(List<SleepingSession> sessions) {
        this.sessionsCount = sleepingSessionsCounter.apply(sessions);
        this.shortestSession = shortestSleepSession.apply(sessions);
        this.longestSession = longestSleepSession.apply(sessions);
        this.averageSession = averageSleepSession.apply(sessions);
        this.badSessionsCount = badSleepQualityCount.apply(sessions);
        this.sleeplessCount = sleeplessNightsCount.apply(sessions);
        this.profile = countSleepingProfile.apply(sessions);
    }

    @Override
    public String toString() {
        return ANSI_RED + "\nРезультаты анализа:\n" + ANSI_RESET + ANSI_GREEN +
                "Всего сессий сна: " + sessionsCount +
                "\nКротчайшая сессия: " + shortestSession.toMinutes() + " мин." +
                "\nСредняя длинна сессии: " + averageSession.toMinutes() + " мин." +
                "\nДлиннейшая сессия: " + longestSession.toMinutes() + " мин." +
                "\nКоличество плохих сессий: " + badSessionsCount +
                "\nБессонных ночей: " + sleeplessCount +
                "\nТип пользователя: " + profile + ANSI_RESET;
    }
}
