import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SleepTrackerAppTest {
    @Test
    public void WhenTwoSessionsInListThenTwo() {
        SessionsCounter sleepingSessionsCounter = new SessionsCounter();
        LocalDateTime timeStart = LocalDateTime.now();

        SleepingSession session1 = new SleepingSession(
                timeStart,
                timeStart.plusHours(8),
                SleepQuality.GOOD
        );
        SleepingSession session2 = new SleepingSession(
                timeStart,
                timeStart.plusHours(4),
                SleepQuality.BAD
        );
        List<SleepingSession> sessions = List.of(session1, session2);
        Assertions.assertEquals(2, sleepingSessionsCounter.apply(sessions));
    }

    @Test
    public void WhenListEmptyThenZero() {
        SessionsCounter sleepingSessionsCounter = new SessionsCounter();
        ArrayList<SleepingSession> sessions = new ArrayList<>();
        Assertions.assertEquals(0, sleepingSessionsCounter.apply(sessions));
    }

    @Test
    public void MinDurationIsTen() {
        ShortestSleepSession shortestSleepSession = new ShortestSleepSession();
        LocalDateTime timeStart = LocalDateTime.now();

        SleepingSession session1 = new SleepingSession(
                timeStart,
                timeStart.plusHours(8),
                SleepQuality.GOOD
        );
        SleepingSession session2 = new SleepingSession(
                timeStart,
                timeStart.plusHours(4),
                SleepQuality.BAD
        );
        SleepingSession session3 = new SleepingSession(
                timeStart,
                timeStart.plusMinutes(10),
                SleepQuality.NORMAL
        );
        List<SleepingSession> sessions = List.of(session1, session2, session3);
        Assertions.assertEquals(Duration.ofMinutes(10), shortestSleepSession.apply(sessions));
    }

    @Test
    public void MaxDurationIsEightHours() {
        LongestSleepSession longestSleepSession = new LongestSleepSession();
        LocalDateTime timeStart = LocalDateTime.now();

        SleepingSession session1 = new SleepingSession(
                timeStart,
                timeStart.plusHours(8),
                SleepQuality.GOOD
        );
        SleepingSession session2 = new SleepingSession(
                timeStart,
                timeStart.plusHours(4),
                SleepQuality.BAD
        );
        SleepingSession session3 = new SleepingSession(
                timeStart,
                timeStart.plusMinutes(10),
                SleepQuality.NORMAL
        );
        List<SleepingSession> sessions = List.of(session1, session2, session3);
        Assertions.assertEquals(Duration.ofHours(8), longestSleepSession.apply(sessions));
    }

    @Test
    public void AverageDurationIsFoutHoursAndFortyMinutes() {
        AverageSleepSession averageSleepSession = new AverageSleepSession();
        LocalDateTime timeStart = LocalDateTime.now();

        SleepingSession session1 = new SleepingSession(
                timeStart,
                timeStart.plusHours(8),
                SleepQuality.GOOD
        );
        SleepingSession session2 = new SleepingSession(
                timeStart,
                timeStart.plusHours(4),
                SleepQuality.NORMAL
        );
        SleepingSession session3 = new SleepingSession(
                timeStart,
                timeStart.plusHours(2),
                SleepQuality.BAD
        );
        List<SleepingSession> sessions = List.of(session1, session2, session3);
        Assertions.assertEquals(Duration.ofMinutes(280), averageSleepSession.apply(sessions));
    }

    @Test
    public void WhenOneSleepIsBadResultOne() {
        BadSleepCount badSleepQualityCount = new BadSleepCount();
        LocalDateTime timeStart = LocalDateTime.now();

        SleepingSession session1 = new SleepingSession(
                timeStart,
                timeStart.plusHours(8),
                SleepQuality.GOOD
        );
        SleepingSession session2 = new SleepingSession(
                timeStart,
                timeStart.plusHours(4),
                SleepQuality.NORMAL
        );
        SleepingSession session3 = new SleepingSession(
                timeStart,
                timeStart.plusHours(2),
                SleepQuality.BAD
        );
        List<SleepingSession> sessions = List.of(session1, session2, session3);
        Assertions.assertEquals(1, badSleepQualityCount.apply(sessions));
    }

    @Test
    public void WhenTwoSleeplessNightsResultTwo() {
        SleeplessNightsCount sleeplessNightsCount = new SleeplessNightsCount();
        LocalDateTime timeStart = LocalDateTime.of(
                LocalDate.of(2026, 9, 16),
                LocalTime.of(0, 0));

        SleepingSession session1 = new SleepingSession(
                timeStart,
                timeStart.plusHours(8),
                SleepQuality.GOOD
        );
        SleepingSession session2 = new SleepingSession(
                timeStart.plusDays(1).plusHours(7),
                timeStart.plusDays(1).plusHours(8),
                SleepQuality.NORMAL
        );
        SleepingSession session3 = new SleepingSession(
                timeStart.plusDays(2).plusHours(8),
                timeStart.plusDays(2).plusHours(9),
                SleepQuality.BAD
        );
        List<SleepingSession> sessions = List.of(session1, session2, session3);
        Assertions.assertEquals(2, sleeplessNightsCount.apply(sessions));
    }

    @Test
    public void WhenZeroSleeplessNightsResultZero() {
        SleeplessNightsCount sleeplessNightsCount = new SleeplessNightsCount();
        LocalDateTime timeStart = LocalDateTime.of(
                LocalDate.of(2026, 9, 16),
                LocalTime.of(0, 0));

        SleepingSession session1 = new SleepingSession(
                timeStart,
                timeStart.plusHours(8),
                SleepQuality.GOOD
        );
        SleepingSession session2 = new SleepingSession(
                timeStart.plusDays(1).plusHours(2),
                timeStart.plusDays(1).plusHours(8),
                SleepQuality.NORMAL
        );
        SleepingSession session3 = new SleepingSession(
                timeStart.plusDays(2).plusHours(1),
                timeStart.plusDays(2).plusHours(9),
                SleepQuality.BAD
        );
        List<SleepingSession> sessions = List.of(session1, session2, session3);
        Assertions.assertEquals(0, sleeplessNightsCount.apply(sessions));
    }

    @Test
    public void WhenFirstDauStartsAfter12ItArentCount() {
        SleeplessNightsCount sleeplessNightsCount = new SleeplessNightsCount();
        LocalDateTime timeStart = LocalDateTime.of(
                LocalDate.of(2026, 9, 16),
                LocalTime.of(13, 0));

        SleepingSession session1 = new SleepingSession(
                timeStart,
                timeStart.plusHours(8),
                SleepQuality.GOOD
        );
        SleepingSession session2 = new SleepingSession(
                timeStart.plusDays(1).minusHours(10),
                timeStart.plusDays(1).minusHours(8),
                SleepQuality.NORMAL
        );
        SleepingSession session3 = new SleepingSession(
                timeStart.plusDays(2).minusHours(2),
                timeStart.plusDays(2),
                SleepQuality.BAD
        );
        List<SleepingSession> sessions = List.of(session1, session2, session3);
        Assertions.assertEquals(2, sleeplessNightsCount.apply(sessions));
    }

    @Test
    public void ifOwlNightsMoreThenOwl() {
        SleepingProfile countSleepingProfile = new SleepingProfile();
        LocalDateTime timeStart = LocalDateTime.of(
                LocalDate.of(2026, 9, 16),
                LocalTime.of(23, 1));

        SleepingSession session1 = new SleepingSession(
                timeStart,
                timeStart.plusHours(10),
                SleepQuality.GOOD
        );
        SleepingSession session2 = new SleepingSession(
                timeStart.plusDays(1),
                timeStart.plusDays(1).plusHours(10),
                SleepQuality.NORMAL
        );
        SleepingSession session3 = new SleepingSession(
                timeStart.plusDays(2),
                timeStart.plusDays(2).plusHours(10),
                SleepQuality.BAD
        );
        List<SleepingSession> sessions = List.of(session1, session2, session3);
        Assertions.assertEquals("Сова", countSleepingProfile.apply(sessions));
    }
}
