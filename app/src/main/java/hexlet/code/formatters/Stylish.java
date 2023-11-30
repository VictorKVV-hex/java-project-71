package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Stylish {

    public static String stylish(List<Map<String,Object>> differList) {
        List<String> diffList = new ArrayList<>();
        for (var node : differList) {
            Object status = node.get("status");
            if (status.equals("UNCHANGED")) {
                diffList.add(String.format("    %s: %s\n", node.get("key"), node.get("value")));
            } else if (status.equals("UPDATED")) {
                diffList.add(String.format("  - %s: %s\n", node.get("key"), node.get("value")));
                diffList.add(String.format("  + %s: %s\n", node.get("key"), node.get("updatedValue")));
            } else if (status.equals("ADDED")) {
                diffList.add(String.format("  + %s: %s\n", node.get("key"), node.get("value")));
            } else if (status.equals("REMOVED")) {
                diffList.add(String.format("  - %s: %s\n", node.get("key"), node.get("value")));
            } else {
                throw new IllegalArgumentException(
                        String.format("Unsupported status. Supported: %s, %s, %s, %s",
                                "UNCHANGED", "UPDATED", "ADDED", "REMOVED"));
            }
        }
        String result = String.join("", diffList);
        return String.format("{\n%s}", result);
    }

}
