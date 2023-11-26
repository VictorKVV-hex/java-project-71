package hexlet.code.formatters;

import hexlet.code.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Stylish {


    public static String stylish(List<Node> differList) {
        List<String> diffList = new ArrayList<>();
        for (Node node : differList) {
            switch (node.getType()) {
                case "UNCHANGED" -> diffList.add(String.format("    %s: %s\n", node.getKey(), node.getValue()));
                case "UPDATED" -> {
                    diffList.add(String.format("  - %s: %s\n", node.getKey(), node.getValue()));
                    diffList.add(String.format("  + %s: %s\n", node.getKey(), node.getUpdatedValue()));
                }
                case "ADDED" -> diffList.add(String.format("  + %s: %s\n", node.getKey(), node.getValue()));
                case "REMOVED" -> diffList.add(String.format("  - %s: %s\n", node.getKey(), node.getValue()));
                default -> throw new IllegalArgumentException(
                        String.format("Unsupported status. Supported: %s, %s, %s, %s",
                                "UNCHANGED", "UPDATED", "ADDED", "REMOVED"));

            }
        }
        String result = String.join("", diffList);
        return String.format("{\n%s}", result);
    }

    public static void differOfMap(List<String> diffList, Map<String, Object> data1,
                                   Map<String, Object> data2, String key) {
        Object valueMap1 = data1.get(key);
        Object valueMap2 = data2.get(key);
        if (data1.containsKey(key) && data2.containsKey(key)) {
            if (Objects.equals(valueMap1, valueMap2)) {
                diffList.add(String.format("    %s: %s", key, valueMap2));
            } else {
                diffList.add(String.format("  - %s: %s", key, valueMap1));
                diffList.add(String.format("  + %s: %s", key, valueMap2));
            }
        } else {
            if (!(data1.containsKey(key))) {
                diffList.add(String.format("  + %s: %s", key, valueMap2));
            } else {
                diffList.add(String.format("  - %s: %s", key, valueMap1));
            }
        }
    }
}
