package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formatter(List<Map<String, Object>> diffList, String format) throws IOException {
        return switch (format.toLowerCase()) {
            case ("stylish") -> Stylish.stylish(diffList);
            case ("plain") -> Plain.plain(diffList);
            case ("json") -> Json.json(diffList);
            default -> throw new IllegalArgumentException(
                    String.format("Wrong format. Supported: %s, %s, %s", "stylish", "plain", "json")
            );
        };
    }
}
