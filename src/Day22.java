import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day22 {
    public static void main(String[] args) {
        String filePath = "src/input/Day2.txt";

        try {
            List<List<Integer>> levels = Files.readAllLines(Paths.get(filePath)).stream()
                    .map(line -> line.split("\\s+"))
                    .map(strings -> Arrays.stream(strings).map(Integer::parseInt).toList())
                    .toList();

            System.out.println(levels.stream().filter(Day22::isReportSafeOrCanBeMadeSafe).count());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isReportSafeOrCanBeMadeSafe(List<Integer> levels) {
        if (isReportSafe(levels)) {
            return true; // Already safe
        }

        // Check if removing one level makes it safe
        for (int i = 0; i < levels.size(); i++) {
            List<Integer> modifiedLevels = new java.util.ArrayList<>(levels);
            modifiedLevels.remove(i);
            if (isReportSafe(modifiedLevels)) {
                return true; // Can be made safe
            }
        }

        return false; // Not safe, even with one level removed
    }

    private static boolean isReportSafe(List<Integer> integers) {
        if (integers.size() < 2) {
            return true; // Less than two levels is trivially safe
        }

        boolean isIncreasing = isIncreasing(integers.get(0) - integers.get(1));

        for (int i = 1; i < integers.size(); i++) {
            int diff = integers.get(i - 1) - integers.get(i);
            boolean increasingCurrently = isIncreasing(diff);

            int abs = Math.abs(diff);
            if (isIncreasing != increasingCurrently || abs > 3 || abs < 1) {
                return false;
            }
        }

        return true;
    }

    private static boolean isIncreasing(int diff) {
        return diff > 0;
    }
}
