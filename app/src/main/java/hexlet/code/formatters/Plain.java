package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {


    public static String plain(List<Map<String,Object>> diffList) {
        StringBuilder resultStr = new StringBuilder();

        for (var node : diffList) {
            Object valOne = typeValue(node.get("value"));
            Object valTwo = typeValue(node.get("updatedValue"));
            Object type = node.get("type");
            if (type.equals("UPDATED")) {
                resultStr.append("Property '" + node.get("key") + "' was updated. From "
                        + valOne + " to " + valTwo).append('\n');
            } else if (type.equals("ADDED")) {
                resultStr.append("Property '" + node.get("key") + "' was added with value: "
                        + valTwo).append('\n');
            } else if (type.equals("REMOVED")) {
                resultStr.append("Property '" + node.get("key") + "' was removed").append('\n');
            } else if (type.equals("UNCHANGED")) {
                resultStr.append("");
            } else {
                throw new IllegalArgumentException(
                        String.format("Unsupported status. Supported: %s, %s, %s, %s",
                                "UNCHANGED", "UPDATED", "ADDED", "REMOVED"));
            }
        }
        return resultStr.toString().trim();
    }

    public static Object typeValue(Object object) {
        Object value = object;
        if (object instanceof Map || object instanceof List) {
            value = "[complex value]";
        } else if (object instanceof String) {
            value = "'" + object + "'";
        }
        return value;
    }

}
