package cn.mldn.xml;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DOM4JCreateXML {
    public static void main(String[] args) throws IOException {
        String names[] = new String[]{"三国演义","西游记","水浒传","红楼梦"};
        String authors[] = new String[]{"罗贯中","吴承恩","施耐庵","曹雪芹"};
        double prices[] = new double[]{11.2,33.4,55.6,77.7};
        //1.不管采用什么处理模式，所有内容一定要追加到文档之中
        Document document = DocumentHelper.createDocument();  //创建一个空的文档
        //2.进行根元素的创建，根元素可以直接通过Document设置
        Element booksElement = document.addElement("books");  //定义根元素并返回Element元素
        //3.进行books元素下所有子元素的配置
        for(int x = 0; x < names.length; x ++){
            Element bookElement = booksElement.addElement("book");  //创建并设置子元素
            bookElement.addAttribute("id", String.valueOf(x));
            bookElement.addElement("name").setText(names[x]);  //创建并设置子元素
            bookElement.addElement("author").setText(authors[x]);  //创建并设置子元素内容
            bookElement.addElement("price").setText(String.valueOf(prices[x]));
        }
        //4.定义输出的格式化控制
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        //4.将文档的内容进行输出显示
        XMLWriter writer = new XMLWriter(new FileOutputStream(new File("E:" + File.separator + "my-books.xml")),format);
        writer.write(document);  //输出文档内容
        writer.close();  //关闭输出操作
    }
}
