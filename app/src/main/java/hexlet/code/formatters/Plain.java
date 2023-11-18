package hexlet.code.formatters;

import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Plain {


    public static String plain(Set<String> allKeys, Map<String, Object> map1, Map<String,
            Object> map2, String extension) {
        List<String> diffList = new ArrayList<>();
        diffList.clear();
        for (String key : allKeys) {
            differOfMap(diffList, map1, map2, key);
        }
        return String.join("\n", diffList);
    }
    public static void differOfMap(List<String> diffList, Map<String, Object> data1,
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
    private static String typeValue(Object object) {
        String value = String.valueOf(object);
        if (value.contains("[") || value.contains("{")) {
            return "[complex value]";
        } else if (object instanceof String) {
            return "'" + value + "'";
        }
        return value;

    }
}
