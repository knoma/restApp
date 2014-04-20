package kmap.web.dataConfig;

/**
 * Created by msaidi on 20.04.14.
 */
public class DataConfig {

    private String name;
    private String value;

    public DataConfig() {
    }

    public DataConfig(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataConfig that = (DataConfig) o;

        if (!name.equals(that.name)) return false;
        if (!value.equals(that.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DataConfig{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
