import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day3 {
    public static void main(String[] args) {
        String filePath = "src/input/Day3.txt";

        try {
            String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));

            // Calculate the sum of all mul(x, y) results
            int amount = Pattern.compile("mul\\(([0-9]+),([0-9]+)\\)")
                    .matcher(fileContent).results()
                    .mapToInt(it -> Integer.parseInt(it.group(1)) * Integer.parseInt(it.group(2)))
                    .sum();

            // Process the file content for the second calculation
            Matcher matcher = Pattern.compile("mul\\(([0-9]+),([0-9]+)\\)|(do\\(\\))|(don't\\(\\))")
                    .matcher(fileContent);
            List<MatchResult> matchResults = matcher.results().collect(Collectors.toList());

            boolean isOn = true;
            int amount2 = 0;

            for (MatchResult matchResult : matchResults) {
                if (matchResult.group(1) != null && matchResult.group(2) != null) {
                    if (isOn) {
                        amount2 += Integer.parseInt(matchResult.group(1)) * Integer.parseInt(matchResult.group(2));
                    }
                } else if (matchResult.group(3) != null) {
                    isOn = true;
                } else if (matchResult.group(4) != null) {
                    isOn = false;
                }
            }

            System.out.println("Amount: " + amount);
            System.out.println("Amount2: " + amount2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
