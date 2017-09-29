package cn.com.ecict.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.ProcessingInstruction;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * XML操作类
 * @author cyq
 * 备注：JDOM 在性能测试时表现不佳，在测试 10M 文档时内存溢出
 *      目前许多开源项目中也大量采用 DOM4J
 *      DOM4J 是一个非常非常优秀的Java XML API，具有性能优异、功能强大和极端易用使用的特点，同时它也是一个开放源代码的软件。
 */
public class XMLUtil {
    private String[][] data = null;
    private SAXBuilder builder = new SAXBuilder();
    private InputStream file;
    private String path=this.getClass().getResource("/config/").getPath();
    private String tmpDir="/tmp/";
    /**
     * 读取XML文件数据，XML property元素只有name和value
     * @author cyq
     * @param fileName
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String,String> readXML(String fileName) {
        Map<String,String> data=new HashMap<String,String>();
        try {
            file = new FileInputStream(fileName);
            Document document = builder.build(file);
            Element root = document.getRootElement();
            List<Element> properties = root.getChildren();
            for (int i = 0; i < properties.size(); i++) {
                String name = properties.get(i).getChild("name").getText();
                String value = properties.get(i).getChild("value").getText();
                data.put(name.trim(), value.trim());
            }
            document.clone();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    //读取指定的XML文件，返回Map(无序！)
    @SuppressWarnings("unchecked")
    public Map<String,List<String>> readXMLFile(String fileName) {
        Map<String,List<String>> data=new HashMap<String,List<String>>();
        try {
            file = new FileInputStream(fileName);
            Document document = builder.build(file);// 获得文档对象
            Element root = document.getRootElement();// 获得根节点
            // System.out.println("根元素："+root.getName());
            List<Element> properties = root.getChildren();
            // 第一维是3，第2维是变量；[0]:name,[1]:value,[2]:final-value
            //data = new String[properties.size()][3];
            for (int i = 0; i < properties.size(); i++) {
                List<String> list=new ArrayList<String>();
                //name是key，List[value,final]是value
                String name = properties.get(i).getChild("name").getText();
                String value = properties.get(i).getChild("value").getText();
                list.add(value);
                Element _final=properties.get(i).getChild("final");
                if(null!=_final)
                    list.add(_final.getText());
                else
                    list.add(null);
                data.put(name, list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    //读取指定的XML文件，返回二维数组(有序！)
    @SuppressWarnings("unchecked")
    public String[][] readXMLData(String fileName) {
        String name;
        String value;
        try {
            file = new FileInputStream(fileName);
            Document document = builder.build(file);// 获得文档对象
            Element root = document.getRootElement();// 获得根节点
            // System.out.println("根元素："+root.getName());
            List<Element> properties = root.getChildren();
            // 第一维是3，第2维是变量；[0]:name,[1]:value,[2]:final-value
            data = new String[properties.size()][3];
            for (int i = 0; i < properties.size(); i++) {
                name = properties.get(i).getChild("name").getText();
                value = properties.get(i).getChild("value").getText();
                data[i][0] = name;
                data[i][1] = value;
                Element _final=properties.get(i).getChild("final");
                if(null!=_final)
                    data[i][2] = _final.getText();
                else
                    data[i][2]=null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    //将Map数据保存为XML文件
    public void saveXMLFile(Map<String,List<String>> data,String targetFile) {
        Document document=new Document();
        //加入一条处理指令
        ProcessingInstruction pi=new ProcessingInstruction("xml-stylesheet","type=\"text/xsl\" href=\"configuration.xsl\"");
        document.addContent(pi);
        //创建根节点<configuration>
        Element root =new Element("configuration");
        //把根节点添加到文档中
        Set<String> key=data.keySet();
        document.addContent(root);
        for(String k:key){
            List<String> v=data.get(k);
            //创建根节点的子节点
            Element property=new Element("property");
            Element name=new Element("name");
            Element value=new Element("value");
            Element _final=new Element("final");
            name.addContent(k);
            value.addContent(v.get(0));
            if(v.get(1)!=null){
                _final.addContent(v.get(1));
                property.addContent(_final);
            }
            property.addContent(name);
            property.addContent(value);
            //将子元素添加根元素
            root.addContent(property);
        }
        XMLOutputter out = new XMLOutputter(formatXML());
        try {
            out.output(document, new FileOutputStream(targetFile));
        } catch (Exception e) {
            System.out.println("生成XML文件失败："+targetFile);
            e.printStackTrace();

        }
    }
    //targetFile的父目录是config
    //简化：public void saveXMLFile(Map<String,List<String>> data,String targetFile)
    public String saveXMLfile(Map<String,String> data,String targetFile) {
        Document document=new Document();
        //加入一条处理指令
        ProcessingInstruction pi=new ProcessingInstruction("xml-stylesheet","type=\"text/xsl\" href=\"configuration.xsl\"");
        document.addContent(pi);
        //创建根节点<configuration>
        Element root =new Element("configuration");
        //把根节点添加到文档中
        Set<String> key=data.keySet();
        document.addContent(root);
        for(String k:key){
            String v=data.get(k);
            //创建根节点的子节点
            Element property=new Element("property");
            Element name=new Element("name");
            Element value=new Element("value");
            name.addContent(k);
            value.addContent(v);
            property.addContent(name);
            property.addContent(value);
            //将子元素添加根元素
            root.addContent(property);
        }
        targetFile=tmpDir+targetFile;
        XMLOutputter out = new XMLOutputter(formatXML());
        try {
            out.output(document, new FileOutputStream(targetFile));
        } catch (Exception e) {
            System.out.println("生成XML文件失败："+targetFile);
            e.printStackTrace();

        }
        return targetFile;
    }
    //将二维数组保存为XML文件,targetFile的父目录是config
    @SuppressWarnings("unchecked")
    public void saveXMLFile(String[][] data,String targetFile) {
        Document document=new Document();
        //加入一条处理指令
        ProcessingInstruction pi=new ProcessingInstruction("xml-stylesheet","type=\"text/xsl\" href=\"configuration.xsl\"");
        document.addContent(pi);
        //创建根节点<configuration>
        Element root =new Element("configuration");
        //把根节点添加到文档中
        document.addContent(root);
        for (int i = 0;i < data.length; i++) {
            //创建根节点的子节点
            Element property=new Element("property");
            Element name=new Element("name");
            Element value=new Element("value");
            Element _final=new Element("final");
            name.addContent(data[i][0]);
            value.addContent(data[i][1]);
            if(data[i][2]!=null){
                _final.addContent(data[i][2]);
                property.addContent(_final);
            }
            property.addContent(name);
            property.addContent(value);
            //将子元素添加根元素
            root.addContent(property);
        }
        XMLOutputter out = new XMLOutputter(formatXML());
        try {
            out.output(document, new FileOutputStream(targetFile));
        } catch (Exception e) {
            System.out.println("生成XML文件失败："+targetFile);
            e.printStackTrace();

        }
    }
    public Format formatXML(){
        Format format=Format.getCompactFormat();
        format.setEncoding("utf-8");
        format.setLineSeparator("\n");
        format.setIndent("    ");
        //2016.11.2添加
        format.setExpandEmptyElements(true);
        return format;
    }
    //修改XML文件,targetFile的父目录是config
    @SuppressWarnings("unchecked")
    public String updateXMLFile(Map<String,String> data,String targetFile) {
        //String path="";
        try {
            //path=this.getClass().getResource("/config/").getPath();
            //System.out.println(path);
            file = new FileInputStream(path+targetFile);
            Document document = builder.build(file);// 获得文档对象
            Element root = document.getRootElement();// 获得根节点
            List<Element> properties = root.getChildren();

            for(String k:data.keySet()){
                boolean flag=false;
                for(Element p:properties)
                    if(k.equals(p.getChild("name").getTextTrim())){
                        p.getChild("value").setText(data.get(k));
                        flag=true;
                    }
                if(!flag){//没有找到，添加该属性
                    Element property=new Element("property");
                    Element name=new Element("name");
                    Element value=new Element("value");
                    name.addContent(k);
                    value.addContent(data.get(k));
                    property.addContent(name);
                    property.addContent(value);
                    //将子元素添加根元素
                    root.addContent(property);
                }
            }
            //输出保存
            XMLOutputter out=new XMLOutputter(formatXML());
            targetFile=tmpDir+targetFile;
            out.output(document, new FileOutputStream(targetFile));
        } catch (Exception e) {
            System.out.println("生成XML文件失败："+targetFile);
            e.printStackTrace();
        }
        return targetFile;
    }

    @SuppressWarnings("unchecked")
    public String updateFile(Map<String,String> data,String filePath) {
        //String path="";
        try {
            //path=this.getClass().getResource("/config/").getPath();
            //System.out.println(path);
            file = new FileInputStream(filePath);
            Document document = builder.build(file);// 获得文档对象
            Element root = document.getRootElement();// 获得根节点
            List<Element> properties = root.getChildren();

            for(String k:data.keySet()){
                boolean flag=false;
                for(Element p:properties)
                    if(k.equals(p.getChild("name").getTextTrim())){
                        p.getChild("value").setText(data.get(k));
                        flag=true;
                    }
                if(!flag){//没有找到，添加该属性
                    Element property=new Element("property");
                    Element name=new Element("name");
                    Element value=new Element("value");
                    name.addContent(k);
                    value.addContent(data.get(k));
                    property.addContent(name);
                    property.addContent(value);
                    //将子元素添加根元素
                    root.addContent(property);
                }
            }
            //输出保存
            XMLOutputter out=new XMLOutputter(formatXML());
            filePath="/tmp"+filePath.substring(filePath.lastIndexOf('/'));
            out.output(document, new FileOutputStream(filePath));
        } catch (Exception e) {
            System.out.println("生成XML文件失败："+filePath);
            e.printStackTrace();
        }
        return filePath;
    }

    @SuppressWarnings("unchecked")
    public String updateFile(String key,String value,String filePath) {
        //String path="";
        try {
            //path=this.getClass().getResource("/config/").getPath();
            //System.out.println(path);
            file = new FileInputStream(filePath);
            Document document = builder.build(file);// 获得文档对象
            Element root = document.getRootElement();// 获得根节点
            List<Element> properties = root.getChildren();

            boolean flag=false;
            for(Element p:properties){
                //找到该元素，更新值
                if(key.equals(p.getChild("name").getTextTrim())){
                    p.getChild("value").setText(value);
                    flag=true;
                }
            }
            if(!flag){//没有找到，添加该属性
                Element property=new Element("property");
                Element name=new Element("name");
                Element val=new Element("value");
                name.addContent(key);
                val.addContent(value);
                property.addContent(name);
                property.addContent(value);
                //将子元素添加根元素
                root.addContent(property);
            }
            //输出保存
            XMLOutputter out=new XMLOutputter(formatXML());
            filePath="/tmp"+filePath.substring(filePath.lastIndexOf('/'));
            out.output(document, new FileOutputStream(filePath));
        } catch (Exception e) {
            System.out.println("生成XML文件失败："+filePath);
            e.printStackTrace();
        }
        return filePath;
    }

    //修改XML文件,targetFile的父目录是config
    @SuppressWarnings("unchecked")
    public String updatetmpXMLFile(Map<String,String> data,String targetFile) {
        //String path="";
        try {
            //path=this.getClass().getResource("/config/").getPath();
            //System.out.println(path);
            file = new FileInputStream(tmpDir+targetFile);
            Document document = builder.build(file);// 获得文档对象
            Element root = document.getRootElement();// 获得根节点
            List<Element> properties = root.getChildren();

            for(String k:data.keySet()){
                boolean flag=false;
                for(Element p:properties)
                    if(k.equals(p.getChild("name").getTextTrim())){
                        p.getChild("value").setText(data.get(k));
                        flag=true;
                    }
                if(!flag){//没有找到，添加该属性
                    Element property=new Element("property");
                    Element name=new Element("name");
                    Element value=new Element("value");
                    name.addContent(k);
                    value.addContent(data.get(k));
                    property.addContent(name);
                    property.addContent(value);
                    //将子元素添加根元素
                    root.addContent(property);
                }
            }
            //输出保存
            XMLOutputter out=new XMLOutputter(formatXML());
            targetFile=tmpDir+targetFile;
            out.output(document, new FileOutputStream(targetFile));
        } catch (Exception e) {
            System.out.println("生成XML文件失败："+targetFile);
            e.printStackTrace();
        }
        return targetFile;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        XMLUtil xml=new XMLUtil();
		/*
		String[][] data=xml.readXMLData("/root/core.xml");
		Map<String,List<String>> map=xml.readXMLFile("/root/hdfs-default.xml");
		for(String[] s:data)
			System.out.println(s[0]+":"+s[1]+":"+s[2]);
		xml.saveXMLFile(data, "/root/copy.xml");
		System.out.println("---------------------------------------------");
		for(String k:map.keySet())
			System.out.println(k+":"+"["+map.get(k).get(0)+","+map.get(k).get(1)+"]");
		xml.saveXMLFile(map, "/root/copy2.xml");
		*/
        Map<String,String> data=new HashMap<String,String>();
        data.put("yarn.nodemanager.resource.memory-mb","100");
        data.put("yarn.nodemanager.resource.cpu-vcores","100");
        String filePath=xml.updateFile(data, "/tmp/yarn-site.xml");
        System.out.println(filePath);
		/*data.put("fs.defaultFS", "hdfs://master137:8020");
		data.put("default.heap.size", "5000");
		data.put("io.file.buffer.size", "6200");
		String filepath=xml.updateXMLFile(data,"core-site.xml");
		System.out.println(filepath);*/
    }
}
