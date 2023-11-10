package hexlet.code;

import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Plain;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static hexlet.code.Differ.diffList;
import static hexlet.code.formatters.Stylish.stylish;

public class Formatter {
    public static List<String> formatter(Set<String> allKeys, Map<String, Object> map1, Map<String, Object>map2, String extension, String format) {
        return switch (format.toLowerCase()) {
            case ("stylish") -> Stylish.stylish(allKeys, map1, map2, extension);
            case ("plain") -> Plain.plain(allKeys, map1, map2, extension);
            default -> throw new IllegalArgumentException(
                    String.format("Unsupported format. Supported: %s, %s", "stylish", "plain")
            );
        };
    }
}
