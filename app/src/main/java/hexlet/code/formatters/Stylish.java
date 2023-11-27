package hexlet.code.formatters;

import hexlet.code.Node;
import java.util.ArrayList;
import java.util.List;

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

}
