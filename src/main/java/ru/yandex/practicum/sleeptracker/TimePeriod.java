import java.time.LocalTime;

public class TimePeriod {
    private final LocalTime start;
    private final LocalTime end;

    private final boolean isTimeTransition;

    public TimePeriod(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
        this.isTimeTransition = this.end.isBefore(this.start);
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public boolean isTimeTransition() {
        return isTimeTransition;
    }

    public boolean contains(TimePeriod otherPeriod) {
        if (isTimeTransition || otherPeriod.isTimeTransition) {
            TimePeriod periodOffset = new TimePeriod(
                    this.start.minusHours(12),
                    this.end.minusHours(12)
            );
            TimePeriod otherPeriodOffset = new TimePeriod(
                    otherPeriod.start.minusHours(12),
                    otherPeriod.end.minusHours(12)
            );
            return periodOffset.contains(otherPeriodOffset);
        }
        return ((this.start.isAfter(otherPeriod.start) || this.start.equals(otherPeriod.start)) &&
                this.start.isBefore(otherPeriod.end)) ||
                ((this.end.isBefore(otherPeriod.end) || this.end.equals(otherPeriod.end)) &&
                        this.end.isAfter(otherPeriod.start));
    }

    @Override
    public String toString() {
        return "TimePeriod{" +
                "start=" + start +
                ", end=" + end +
                ", isTimeTransition=" + isTimeTransition +
                '}';
    }
}
