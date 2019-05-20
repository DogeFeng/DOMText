package cn.mldn.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMWriterXML {
    public static void main(String[] args) throws Exception {
        //1.不管DOM操作如何处理，首先一定要创建解析工厂类以及相关的文档，输出的唯一区别创建的是空文档
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();  //创建一个空文档
        //2.DOM的处理是需要进行节点嵌套配置的
        String names[] = new String[]{"三国演义","西游记","水浒传","红楼梦"};
        double prices[] = new double[]{11.2,33.4,55.6,77.7};
        Element booksElement = document.createElement("books");  //创建根节点
        //3.对于子节点的数据可以采用循环的模式来完成
        for(int x = 0; x < names.length; x ++){
            Element bookElement = document.createElement("book");  //创建节点
            Element nameElememt = document.createElement("name");
            Element priceElement = document.createElement("price");
            nameElememt.appendChild(document.createTextNode(names[x]));  //创建文本节点并将其设置为name子元素
            priceElement.appendChild(document.createTextNode(String.valueOf(prices[x])));  //创建文本节点并将其设置为price子元素
            booksElement.appendChild(bookElement);  //向根节点追加子节点
            bookElement.appendChild(nameElememt);  //追加节点关系
            bookElement.appendChild(priceElement);  //追加节点关系
        }
        document.appendChild(booksElement);  //向文档中追加根节点
        //4.将内存中的DOM树输出到文件之中进行存储
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");  //设置编码
        transformer.transform(new DOMSource(document),new StreamResult(new File("E:" + File.separator + "books.xml")));
    }
}
