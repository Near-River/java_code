package pro2.sorm.bean;

/**
 * 封装配置文件信息
 * Created by near on 2015/12/14.
 *
 * @author near
 * @version 1.0
 */
public class Configuration {
    /**
     * 数据库的驱动类
     */
    private String driver;

    /**
     * JDBC的URL
     */
    private String url;

    /**
     * 数据库的用户名
     */
    private String userName;

    /**
     * 数据库的用户密码
     */
    private String password;

    /**
     * 项目的源码路径
     */
    private String srcPath;

    /**
     * 所使用的数据库类型
     */
    private String dbType;

    /**
     * 扫描生成PO对象的包
     */
    private String poPackage;

    /**
     * 最小连接池数量
     */
    private String poolMinSize;

    /**
     * 最大连接池数量
     */
    private String poolMaxSize;

    public Configuration() {
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSrcPath() {
        return srcPath;
    }

    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getPoPackage() {
        return poPackage;
    }

    public void setPoPackage(String poPackage) {
        this.poPackage = poPackage;
    }

    public String getPoolMinSize() {
        return poolMinSize;
    }

    public void setPoolMinSize(String poolMinSize) {
        this.poolMinSize = poolMinSize;
    }

    public String getPoolMaxSize() {
        return poolMaxSize;
    }

    public void setPoolMaxSize(String poolMaxSize) {
        this.poolMaxSize = poolMaxSize;
    }
}
