package pro2.sorm.bean;

/**
 * 封装了Java属性和相应的getter、setter方法
 * Created by near on 2015/12/15.
 *
 * @author near
 * @version 1.0
 */
public class FieldGetSetInfo {
    /**
     * 属性的源码信息
     * 例：private int userId;
     */
    private String fieldInfo;
    /**
     * getter方法的源码信息
     * 例：public int getUserId(){return userId;}
     */
    private String getterInfo;
    /**
     * setter方法的源码信息
     * 例：public void setUserId(int userId){this.userId = userId;}
     */
    private String setterInfo;

    public String getFieldInfo() {
        return fieldInfo;
    }

    public void setFieldInfo(String fieldInfo) {
        this.fieldInfo = fieldInfo;
    }

    public String getGetterInfo() {
        return getterInfo;
    }

    public void setGetterInfo(String getterInfo) {
        this.getterInfo = getterInfo;
    }

    public String getSetterInfo() {
        return setterInfo;
    }

    public void setSetterInfo(String setterInfo) {
        this.setterInfo = setterInfo;
    }

    public FieldGetSetInfo() {
    }

    public FieldGetSetInfo(String fieldInfo, String getterInfo, String setterInfo) {

        this.fieldInfo = fieldInfo;
        this.getterInfo = getterInfo;
        this.setterInfo = setterInfo;
    }
}
