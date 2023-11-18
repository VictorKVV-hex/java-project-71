package hexlet.code.formatters;

import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Stylish {


    public static String stylish(Set<String> allKeys, Map<String, Object> map1, Map<String,
            Object> map2, String extension) {
        List<String> diffList = new ArrayList<>();
        diffList.clear();
        diffList.add("{");
        for (String key : allKeys) {
            differOfMap(diffList, map1, map2, key);
        }
        diffList.add("}");

        return String.join("\n", diffList);
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
