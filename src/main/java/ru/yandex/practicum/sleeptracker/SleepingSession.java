import java.time.LocalDateTime;

public class SleepingSession {
    LocalDateTime startSleep;
    LocalDateTime endSleep;
    SleepQuality sleepQuality;
    TimePeriod timePeriod;

    public SleepingSession(LocalDateTime startSleep, LocalDateTime endSleep, SleepQuality sleepQuality) {
        this.startSleep = startSleep;
        this.endSleep = endSleep;
        this.sleepQuality = sleepQuality;
        this.timePeriod = new TimePeriod(startSleep.toLocalTime(), endSleep.toLocalTime());
    }

    public LocalDateTime getStartSleep() {
        return startSleep;
    }

    public void setStartSleep(LocalDateTime startSleep) {
        this.startSleep = startSleep;
    }

    public LocalDateTime getEndSleep() {
        return endSleep;
    }

    public void setEndSleep(LocalDateTime endSleep) {
        this.endSleep = endSleep;
    }

    public SleepQuality getSleepQuality() {
        return sleepQuality;
    }

    public void setSleepQuality(SleepQuality sleepQuality) {
        this.sleepQuality = sleepQuality;
    }

    public TimePeriod getPeriod() {
        return new TimePeriod(startSleep.toLocalTime(), endSleep.toLocalTime());
    }

    @Override
    public String toString() {
        return "SleepingSession{" +
                "startSleep=" + startSleep +
                ", endSleep=" + endSleep +
                ", sleepQuality=" + sleepQuality +
                '}';
    }
}
