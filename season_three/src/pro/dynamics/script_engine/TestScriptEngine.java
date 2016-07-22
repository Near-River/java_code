package pro.dynamics.script_engine;

import org.junit.Before;
import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by near on 2015/12/8.
 */
public class TestScriptEngine {
    ScriptEngineManager scriptEngineManager = null;
    ScriptEngine scriptEngine = null;

    @Before
    public void before() {
        // 获得脚本引擎对象
        scriptEngineManager = new ScriptEngineManager();
        scriptEngine = scriptEngineManager.getEngineByName("javascript");
    }

    @Test
    public void test() {
        scriptEngine.put("msg", "杨萧");
        String str = "var user = {name: 'near', age: 21};";
        str += "print(user.name);";
        try {
            scriptEngine.eval(str);

            scriptEngine.eval("var msg = 'haha'");
            System.out.println(scriptEngine.get("msg"));

            // 执行 js 函数
            scriptEngine.eval("function add(a, b){return a+b;}");
            Invocable invocable = (Invocable) scriptEngine;

            Object obj = invocable.invokeFunction("add", new Object[]{10, 20});
            System.out.println(obj);

            // 在 js 中引用 Java 语言包
            /*String jsCode = "importPackage(java.util); var list = Arrays.asList(['杨萧', '北京', '天帝']);";
            scriptEngine.eval(jsCode);
            List<String> list = (List<String>) scriptEngine.get("list");
            for(String s : list){
                System.out.println(s);
            }*/
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        // InputStream inputStream = TestScriptEngine.class.getClassLoader().getResourceAsStream("/note.txt");
        URL url = TestScriptEngine.class.getClassLoader().getResource("demo.js");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(new File(url.getPath()));
            Object object = scriptEngine.eval(fileReader);
            System.out.println(object);
        } catch (ScriptException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
