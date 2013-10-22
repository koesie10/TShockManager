package co.tshock.manager.data.models;

/**
 * @author koesie10
 */
public class User {
    private String group;
    private int id;
    private String name;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String group, int id, String name) {
        this.group = group;
        this.id = id;
        this.name = name;
    }
}
