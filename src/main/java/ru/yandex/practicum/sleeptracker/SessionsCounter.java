import java.util.List;
import java.util.function.Function;

public class SessionsCounter implements Function<List<SleepingSession>, Integer> {

    @Override
    public Integer apply(List<SleepingSession> sleepingSessions) {
        return sleepingSessions.size();
    }
}
