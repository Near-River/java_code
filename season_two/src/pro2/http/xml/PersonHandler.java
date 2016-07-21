package pro2.http.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Near on 2016/1/26.
 */
public class PersonHandler extends DefaultHandler {
    private List<Person> persons;
    private Person person;
    //记录当前读入的标签名称
    private String tag;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("开始处理文档");
        persons = new ArrayList<Person>();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        System.out.println("开始处理一个元素" + qName);
        if (null != qName) {
            tag = qName;
            if (qName.equals("person")) {
                person = new Person();
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        String str = new String(ch, start, length);
        if (null != tag && tag.equals("name")) {
            // System.out.println(new String(ch,start,length));
            person.setName(str);
        } else if (null != tag && tag.equals("age")) {
            // System.out.println(new String(ch,start,length));
            Integer age = Integer.valueOf(str);
            person.setAge(age);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        System.out.println("结束处理一个元素" + qName);
        if (qName.equals("person")) {
            this.persons.add(person);
        }
        // 置空标签
        tag = null;
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("结束处理文档");
    }

    public List<Person> getPersons() {
        return persons;
    }
}
