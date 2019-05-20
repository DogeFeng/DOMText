package cn.mldn.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DOMDeleteXML {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        File file = new File("E:" + File.separator + "books.xml");
        Document document = builder.parse(file);
        //2.设置每本书的作者
        NodeList priceList = document.getElementsByTagName("price");  //获取要删除的元素
        int count = priceList.getLength();  //获取要删除的个数
        for(int x = 0; x < count; x ++){
            priceList.item(0).getParentNode().removeChild(priceList.item(0));
        }
        //4.将内存中的DOM树输出到文件之中进行存储
        TransformerFactory factory1 = TransformerFactory.newInstance();
        Transformer transformer = factory1.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
        transformer.transform(new DOMSource(document),new StreamResult(new File("E:" + File.separator + "books.xml")));
    }
}
