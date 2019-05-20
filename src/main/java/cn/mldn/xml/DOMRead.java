package cn.mldn.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DOMRead {
    public static void main(String[] args) throws Exception {
        // 1、获取文档构建类工厂类实例
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance() ;
        // 2、创建文档构建类的实例
        DocumentBuilder builder = factory.newDocumentBuilder() ;
        // 3、根据指定的路径来实现XML文档的加载
        String filePath = (new DOMRead().getClass().getResource("/") + "members.xml").replace("file:/","") ;
        File file = new File(filePath) ;    // XML文件所在路径
        // 4、根据文档构建类实例通过解析的方式来实现文档的读取
        Document document = builder.parse(file) ; // 解析文件，在内存中将形成DOM树
        // 5、根据文档实现内容的加载，查询所有的<member>元素的内容
//        NodeList memberList = document.getElementsByTagName("member") ; // 通过指定的元素标记获取全部与之匹配的元素列表
        NodeList nameList = document.getElementsByTagName("name");
        NodeList ageList = document.getElementsByTagName("age");
        NodeList telList = document.getElementsByTagName("tel");
        for (int x = 0 ; x < nameList.getLength() ; x ++) {
            Element memberElement = (Element) nameList.item(x).getParentNode();
            String id = memberElement.getAttribute("id");
            // 在W3C定义的标准之中，每一个获取的节点都是Node描述，但是实际上现在获取的是元素
//            Element memberElement = (Element) memberList.item(x) ; // 获取指定索引的节点
//            String id = memberElement.getAttribute("id") ; // 属性可以直接获取
//            NodeList nameList = memberElement.getElementsByTagName("name") ; // 全部Name节点
//            NodeList ageList = memberElement.getElementsByTagName("age") ; // 全部Age节点
//            NodeList telList = memberElement.getElementsByTagName("tel") ; // 全部tel节点
//            String name = nameList.item(0).getFirstChild().getTextContent() ;
//            String age = ageList.item(0).getFirstChild().getTextContent() ;
//            String tel = telList.item(0).getFirstChild().getTextContent() ;
            String name = nameList.item(x).getFirstChild().getTextContent();
            String age = ageList.item(x).getFirstChild().getNodeValue();
            String tel = telList.item(x).getFirstChild().getNodeValue();
            System.out.printf("ID：%s、姓名：%s、年龄：%s、电话：%s\n", id, name, age, tel);
        }
    }
}