package cn.mldn.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class DOM4JReadXMLContent {
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File("E:" + File.separator + "my-books.xml");
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(file);
        Element rootElement = document.getRootElement();
        List<Element> bookElement = rootElement.elements("book");
        Iterator<Element> iterator = bookElement.iterator();
        while (iterator.hasNext()){
            Element element =iterator.next();
            String id = element.attributeValue("id");
            String name  = element.elementText("name");
            String price = element.elementText("price");
            String author = element.elementText("author");
            System.out.printf("ID：%s、姓名：%s、价格：%s、作者：%s\n", id, name, price, author);
        }
    }
}
