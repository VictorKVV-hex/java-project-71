package hexlet.code.formatters;

import hexlet.code.Node;

import java.util.List;
import java.util.Map;



public class Plain {


    public static String plain(List<Node> diffList){
/*        List<String> diffList = new ArrayList<>();
        diffList.clear();
        for (String key : allKeys) {
            differOfMap(diffList, map1, map2, key);
        }*/

        StringBuilder resultStr = new StringBuilder();

        for (Node node : diffList) {
            Object valOne = typeValue(node.getValue());
            Object valTwo = typeValue(node.getUpdatedValue());

            switch (node.getType()) {
                case "UPDATED" -> /*resultStr.append("Property ").append("'").append(node.getKey()).append("' ").
                        append("was updated. ").append("From ").append(valueMap1).
                        append(" to ").append(valueMap2).append('\n');*/
                resultStr.append("Property '" + node.getKey() + "' was updated. From " + valOne + " to " + valTwo).append('\n');
                case "ADDED" -> /*resultStr.append("Property ").append("'").append(node.getKey()).
                        append("'").append(" was added with value: ").append(valueMap1).append('\n');*/
                resultStr.append("Property '" + node.getKey() + "' was added with value: " + valOne).append('\n');
                case "REMOVED" -> /*resultStr.append("Property ").append("'").
                        append(node.getKey()).append("'").append(" was removed").append('\n');*/
                resultStr.append("Property '" + node.getKey() + "' was removed").append('\n');
                case "UNCHANGED" -> resultStr.append("");
                default -> throw new IllegalArgumentException(
                        String.format("Unsupported status. Supported: %s, %s, %s, %s",
                                "UNCHANGED", "UPDATED", "ADDED", "REMOVED"));
            }
        }
//        return String.join("\n", diffList);
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
/*    public static void differOfMap(List<String> diffList, Map<String, Object> data1,
                                   Map<String, Object> data2, String key) {
        Object valueMap1 = data1.get(key);
        Object valueMap2 = data2.get(key);
        String valOne = typeValue(data1.get(key));
        String valTwo = typeValue(data2.get(key));
        StringBuilder resultStr = null;
        if (data1.containsKey(key) && data2.containsKey(key)) {
            if (!Objects.equals(valueMap1, valueMap2)) {
                diffList.add("Property '" + key + "' was updated. From " + valOne + " to " + valTwo);
            }
        } else {
            if (!(data1.containsKey(key))) {
                diffList.add("Property '" + key + "' was added with value: " + valTwo);
            } else {
                diffList.add("Property '" + key + "' was removed");
            }
        }
    }

    public static Objects typeValue(Object object) {
        String value = String.valueOf(object);
        if (value.contains("[") || value.contains("{")) {
            return "[complex value]";
        } else if (object instanceof String) {
            return "'" + value + "'";
        }return value;
    }*/
}
