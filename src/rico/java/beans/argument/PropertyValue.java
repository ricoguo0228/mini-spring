package beans.argument;
/**
 * Bean 的属性定义
 */
public class PropertyValue {
    private final String name;
    private final Object value;
    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }
    // region getter
    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
    // endregion
}
