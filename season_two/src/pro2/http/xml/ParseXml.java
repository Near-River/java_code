package pro2.http.xml;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

/**
 * Created by Near on 2016/1/26.
 */
public class ParseXml {
    @Test
    public void test() throws ParserConfigurationException, SAXException {
        // 获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // 从解析工厂中获取解析器
        SAXParser parser = factory.newSAXParser();
        // 加载文档 Document 注册并编写处理器
        PersonHandler handler = new PersonHandler();

        try {
            parser.parse(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("pro2/http/xml/person.xml"), handler);

            List<Person> list = handler.getPersons();
            for (Person person : list) {
                System.out.println(person);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
