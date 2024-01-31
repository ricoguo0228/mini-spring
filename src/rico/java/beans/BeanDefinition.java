package beans;

public class BeanDefinition {

    private String id;
    private String className;

    public BeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }
    // region getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    // endregion
}
