package hexlet.code;

public class Node {
    private final String type;
    private final String key;
    private Object value;
    private final Object updatedValue;

    public String getType() {
        return type;
    }
    public String getKey() {
        return key;
    }
    public Object getValue() {
        return value;
    }
    public Object getUpdatedValue() {
        return updatedValue;
    }

    public Node(String type, String key, Object value, Object updatedValue) {
        this.type = type;
        this.key = key;
        this.value = value;
        this.updatedValue = updatedValue;
    }
}
