import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day1 {
  public static void main(String[] args) {
    String filePath = "src/input/Day1.txt"; // Relative path to the file
    System.out.println("Current working directory: " + System.getProperty("user.dir"));

    try {
      List<Integer> list1 = new ArrayList<>();
      List<Integer> list2 = new ArrayList<>();

      Files.readAllLines(Paths.get(filePath)).stream()
          .map(line -> line.split("\\s+"))
          .forEach(strings -> {
                list1.add(Integer.parseInt(strings[0]));
                list2.add(Integer.parseInt(strings[1]));
              }

          );

      Collections.sort(list1);
      Collections.sort(list2);
      int diff = 0;
      for (int i = 0; i < list1.size(); i++) {
        diff += Math.abs(list1.get(i) - list2.get(i));
      }

      System.out.println(diff);

      AtomicInteger similarityScore = new AtomicInteger();
      list1.forEach(it -> similarityScore.updateAndGet(v -> v + it * Collections.frequency(list2, it)));
      System.out.println(similarityScore.get());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}