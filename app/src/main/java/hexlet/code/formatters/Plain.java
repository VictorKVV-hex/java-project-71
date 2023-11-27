package hexlet.code.formatters;

import hexlet.code.Node;
import java.util.List;
import java.util.Map;

public class Plain {


    public static String plain(List<Node> diffList) {
        StringBuilder resultStr = new StringBuilder();

        for (Node node : diffList) {
            Object valOne = typeValue(node.getValue());
            Object valTwo = typeValue(node.getUpdatedValue());

            switch (node.getType()) {
                case "UPDATED" -> resultStr.append("Property '" + node.getKey() + "' was updated. From "
                        + valOne + " to " + valTwo).append('\n');
                case "ADDED" -> resultStr.append("Property '" + node.getKey() + "' was added with value: "
                        + valOne).append('\n');
                case "REMOVED" -> resultStr.append("Property '" + node.getKey() + "' was removed").append('\n');
                case "UNCHANGED" -> resultStr.append("");
                default -> throw new IllegalArgumentException(
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
