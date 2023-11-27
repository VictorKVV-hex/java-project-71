package hexlet.code;

import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.Objects;
public class TreeDiff {
    private static final String UNCHANGED = "UNCHANGED";
    private static final String UPDATED = "UPDATED";
    private static final String ADDED = "ADDED";
    private static final String REMOVED = "REMOVED";
    public static List<Node>  getDifferList(Map<String, Object> map1, Map<String, Object> map2) {
        List<Node> diffList = new ArrayList<>();
        diffList.clear();
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());
        for (String key : allKeys) {
            Object valueMap1 = map1.get(key);
            Object valueMap2 = map2.get(key);

            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (Objects.equals(valueMap1, valueMap2)) {
                    diffList.add(new Node(UNCHANGED, key, valueMap1, valueMap2));
                } else {
                    diffList.add(new Node(UPDATED, key, valueMap1, valueMap2));
                }
            } else {
                if (!(map1.containsKey(key))) {
                    diffList.add(new Node(ADDED, key, valueMap2, null));
                } else {
                    diffList.add(new Node(REMOVED, key, valueMap1, null));
                }
            }
        }
        return diffList;
    }
}
