package pro.commons;

/**
 * 等级类：根据雇员的工资来划分等级
 */
class Level{
    private String name;
    private String level;

    public Level() {
    }

    public Level(String name, String level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Level{" +
                "name='" + name + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
