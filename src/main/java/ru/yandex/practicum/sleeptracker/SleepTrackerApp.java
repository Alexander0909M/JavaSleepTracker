import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SleepTrackerApp {
    public static final String ANSI_RED = "\033[4;31m";
    public static final String ANSI_RESET = "\033[0m";

    public static void main(String[] args) {
        Map<String, SleepQuality> qualities = Map.of(
                "GOOD", SleepQuality.GOOD,
                "NORMAL", SleepQuality.NORMAL,
                "BAD", SleepQuality.BAD
        );
        System.out.println(ANSI_RED + "Введите путь до файла:" + ANSI_RESET);
        Scanner scanner = new Scanner(System.in);
        String enteredPath = scanner.nextLine();
        Path path = Paths.get(enteredPath);
        if (path.toFile().exists() && path.toFile().isFile()) {
            try (
                    Reader fileReader = new FileReader(enteredPath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader)
            ) {
                List<SleepingSession> sessions = bufferedReader
                        .lines()
                        .map(line -> {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
                            LocalDateTime start = LocalDateTime.parse(line.split(";")[0], formatter);
                            line = line.substring(line.indexOf(";") + 1);
                            LocalDateTime end = LocalDateTime.parse(line.split(";")[0], formatter);
                            ;
                            SleepQuality quality = qualities.get(line.split(";")[1]);
                            SleepingSession session = new SleepingSession(
                                    start,
                                    end,
                                    quality
                            );
                            return session;
                        })
                        .toList();
                SleepAnalysisResult sleepAnalysisResult = new SleepAnalysisResult(sessions);
                System.out.println(sleepAnalysisResult.toString());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
