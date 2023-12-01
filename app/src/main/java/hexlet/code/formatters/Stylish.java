package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Stylish {

    public static String stylish(List<Map<String,Object>> differList) {
        List<String> diffList = new ArrayList<>();
        for (Map<String,Object> node : differList) {
            Object type = node.get("type");
            Object key = node.get("key");
            Object value = node.get("value");
            if (type.equals("UNCHANGED")) {
                diffList.add(String.format("    %s: %s\n", node.get("key"), node.get("value")));
            } else if (type.equals("UPDATED")) {
                diffList.add(String.format("  - %s: %s\n", node.get("key"), node.get("value")));
                diffList.add(String.format("  + %s: %s\n", node.get("key"), node.get("updatedValue")));
            } else if (type.equals("ADDED")) {
                diffList.add(String.format("  + %s: %s\n", node.get("key"), node.get("updatedValue")));
            } else if (type.equals("REMOVED")) {
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
