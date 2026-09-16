import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SessionsBatch {
    List<SleepingSession> sleepingSessionList;
    List<LocalDate> nightDates;
    Map<LocalDate, List<SleepingSession>> sessionsByDate;

    public SessionsBatch(List<SleepingSession> sleepingSessionList) {
        this.sleepingSessionList = sleepingSessionList;
        this.nightDates = new ArrayList<>(this.sleepingSessionList.stream()
                .map(sleepingSession -> sleepingSession.startSleep.toLocalDate())
                .toList());
        this.sessionsByDate = this.sleepingSessionList.stream()
                .collect(Collectors.groupingBy(session -> session.startSleep.toLocalDate()));
        if (this.sleepingSessionList.getFirst().startSleep.toLocalTime().isAfter(LocalTime.of(12,0))) {
            this.nightDates.removeFirst();
        }
    }

    public List<SleepingSession> getSleepingSessionList() {
        return sleepingSessionList;
    }

    public void setSleepingSessionList(List<SleepingSession> sleepingSessionList) {
        this.sleepingSessionList = sleepingSessionList;
    }

    public List<LocalDate> getNightDates() {
        return nightDates;
    }

    public void setNightDates(List<LocalDate> dates) {
        this.nightDates = dates;
    }

    public Map<LocalDate, List<SleepingSession>> getSessionsByDate() {
        return sessionsByDate;
    }

    public void setSessionsByDate(Map<LocalDate, List<SleepingSession>> sessionsByDate) {
        this.sessionsByDate = sessionsByDate;
    }
}
