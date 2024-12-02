import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day2 {
  public static void main(String[] args) {
    String filePath = "src/input/Day2.txt";

    try {

      List<List<Integer>> levels = Files.readAllLines(Paths.get(filePath)).stream()
          .map(line -> line.split("\\s+"))
          .map(strings -> Arrays.stream(strings).map(Integer::parseInt).toList())
          .toList();

      System.out.println(levels.stream().filter(it -> isReportSafe(it, false)).count());
      System.out.println(levels.stream().filter(it -> isReportSafe(it, true)).count());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static boolean isReportSafe(List<Integer> integers, boolean isPart2) {
    boolean isSafe = true;
    boolean isIncreasing = isIncreasing(integers.get(0) - integers.get(1));
    int unsafeCount = 0;

    for (int i = 1; i < integers.size(); i++) {
      int diff = integers.get(i - 1 - unsafeCount) - integers.get(i);
      boolean increasingCurrently = isIncreasing(diff);

      int abs = Math.abs(diff);

      if (isIncreasing != increasingCurrently || abs > 3 || abs < 1) {
        if (!isPart2 || (isPart2 && unsafeCount > 0)) {
          return false;
        }

        unsafeCount++;
        co
      } else {
        isIncreasing = increasingCurrently;
      }
    }
    return isSafe;
  }

  private static boolean isIncreasing(Integer diff) {
    return diff > 0;
  }
}