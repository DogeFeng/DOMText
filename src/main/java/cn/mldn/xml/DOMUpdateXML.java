package cn.mldn.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DOMUpdateXML {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        File file = new File("E:" + File.separator + "books.xml");
        Document document = builder.parse(file);
        //2.设置每本书的作者
        Map<String,String> authors = new HashMap<>();
        authors.put("三国演义","罗贯中");
        authors.put("西游记","吴承恩");
        authors.put("水浒传","施耐庵");
        authors.put("红楼梦","曹雪芹");
        //3.获取book元素，追加相应的子节点
        NodeList bookList = document.getElementsByTagName("book");  //获取所有的book节点
        for(int x = 0; x < bookList.getLength(); x ++){
            Element bookElement = (Element) bookList.item(x);
            String key = bookElement.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
            Element authorElement = document.createElement("author");
            authorElement.appendChild(document.createTextNode(authors.get(key)));
            Element pubElement = document.createElement("pub");
            pubElement.appendChild(document.createTextNode("中国古典文学出版社"));
            bookElement.appendChild(authorElement);
            bookElement.appendChild(pubElement);
        }
        //4.将内存中的DOM树输出到文件之中进行存储
        TransformerFactory factory1 = TransformerFactory.newInstance();
        Transformer transformer = factory1.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
        transformer.transform(new DOMSource(document),new StreamResult(new File("E:" + File.separator + "books.xml")));
    }
}
