package com.zy.seckill.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.zy.seckill.common.listener.MarshallerListener;
import lombok.SneakyThrows;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class XmlUtil {

    /**
     * 将json字符串转换成xml
     *
     * @param json
     *            json字符串
     * @param parentElement
     *            xml根节点
     * @throws Exception
     */
    public static Element jsonToXml(String json, Element parentElement) throws Exception {
        JsonObject jsonObject = new JsonObject();
        Element ee = toXml(jsonObject, parentElement, null);
        return ee;
    }

    /**
     * 将json字符串转换成xml
     *
     * @param jsonElement
     *            待解析json对象元素
     * @param parentElement
     *            上一层xml的dom对象
     * @param name
     *            父节点
     */
    public static Element toXml(JsonElement jsonElement, Element parentElement, String name) {
        if (jsonElement instanceof JsonArray) {
            //是json数据，需继续解析
            JsonArray sonJsonArray = (JsonArray)jsonElement;
            for (int i = 0; i < sonJsonArray.size(); i++) {
                JsonElement arrayElement = sonJsonArray.get(i);
                toXml(arrayElement, parentElement, name);
            }
        }else if (jsonElement instanceof JsonObject) {
            //说明是一个json对象字符串，需要继续解析
            /*JsonObject sonJsonObject = (JsonObject) jsonElement;
            Element currentElement = null;
            if (name != null) {
                currentElement = parentElement.addElement(name);
            }
            Set<Map.Entry<String, JsonElement>> set = sonJsonObject.entrySet();
            for (QNameMap.Entry<String, JsonElement> s : set) {
                toXml(s.getValue(), currentElement != null ? currentElement : parentElement, s.getKey());
            }*/
        } else {
            //说明是一个键值对的key,可以作为节点插入了
            addAttribute(parentElement, name, jsonElement.getAsString());
        }
        return parentElement;
    }

    /**
     *
     * @param element  	父节点
     * @param name		子节点的名字
     * @param value		子节点的值
     */
    public static void addAttribute(Element element, String name, String value) {
        //增加子节点，并为子节点赋值
        Element el = element.addElement(name);
        el.addText(value);

    }



    //------------------------------------------///--------------------------------------------///

    /**
     * 将对象直接转换成String类型的 XML输出
     *
     * @param obj
     * @return
     */
    public static String convertToXml(Object obj) {
        // 创建输出流
        StringWriter sw = new StringWriter();
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            //去掉xml的头
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            //字符编码
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            //属性为null 也保留标签
            marshaller.setListener(new MarshallerListener());

            // 将对象转换成输出流形式的xml
            marshaller.marshal(obj,sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    /**
     * 将对象根据路径转换成xml文件
     *
     * @param obj
     * @param path
     * @return
     */
    public static void convertToXml(Object obj, String path) {
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);

            // 将对象转换成输出流形式的xml
            FileWriter fw = null;
            //OutputStreamWriter osw = null;
            try {
                //osw = new OutputStreamWriter(new FileOutputStream(path, true),"UTF-8");
                fw = new FileWriter(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            marshaller.marshal(obj, fw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    /**
     * 将String类型的xml转换成对象
     */
    public static Object convertXmlStrToObject(Class clazz, String xmlStr) {
        Object xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            // 进行将Xml转成对象的核心接口
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader sr = new StringReader(xmlStr);
            xmlObject = unmarshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlObject;
    }

    @SuppressWarnings("unchecked")
    /**
     * 将file类型的xml转换成对象
     */
    public static Object convertXmlFileToObject(Class clazz, String xmlPath) {
        Object xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            FileReader fr = null;
            try {
                fr = new FileReader(xmlPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            xmlObject = unmarshaller.unmarshal(fr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlObject;
    }


public static Map<String, String> readStringXmlOut(String xml) {
    Map<String, String> map = new HashMap<String, String>();
    Document doc = null;
    try {

        doc = DocumentHelper.parseText(xml); // 将字符串转为XML
        Element rootElt = doc.getRootElement(); // 获取根节点
        @SuppressWarnings("unchecked")
        List<Element> list = rootElt.elements();// 获取根节点下所有节点

        for (Element element : list) { // 遍历节点
            map.put(element.getName(), element.getText()); // 节点的name为map的key，text为map的value }
        }
        }catch(DocumentException e){
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
        return map;
}

    //单层xml转为map (xml ---> map)
    public static Map<String, String> xmlToMap(String xml) {
        try {
            Map<String, String> data = new HashMap<>();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            org.w3c.dom.Document doc = documentBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }
            stream.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    //单层map转为xml
    public static String mapToXml(Map<String, String> data) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
            org.w3c.dom.Document document = documentBuilder.newDocument();
            org.w3c.dom.Element root = document.createElement("xml");
            document.appendChild(root);
            for (String key: data.keySet()) {
                String value = data.get(key);
                if (value == null) {
                    value = "";
                }
                value = value.trim();
                org.w3c.dom.Element filed = document.createElement(key);
                filed.appendChild(document.createTextNode(value));
                root.appendChild(filed);
            }
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(document);
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
            String output = writer.getBuffer().toString(); //.replaceAll("\n|\r", "");
            writer.close();
            return output;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    /**
     * (多层)xml格式字符串转换为map
     *
     * @param xml xml字符串
     * @return 第一个为Root节点，Root节点之后为Root的元素，如果为多层，可以通过key获取下一层Map
     */
    public static Map<String, Object> multilayerXmlToMap(String xml) {
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            System.out.println("xml字符串解析，失败 --> :"+ e);
        }
        Map<String, Object> map = new HashMap<>();
        if (null == doc) {
            return map;
        }
        // 获取根元素
        Element rootElement = doc.getRootElement();
        recursionXmlToMap(rootElement,map);
        return map;
    }

    /**
     * multilayerXmlToMap核心方法，递归调用
     *
     * @param element 节点元素
     * @param outmap 用于存储xml数据的map
     */
    @SuppressWarnings("unchecked")
    private static void recursionXmlToMap(Element element, Map<String, Object> outmap) {
        // 得到根元素下的子元素列表
        List<Element> list = element.elements();
        int size = list.size();
        if (size == 0) {
            // 如果没有子元素,则将其存储进map中
            outmap.put(element.getName(), element.getTextTrim());
        } else {
            // innermap用于存储子元素的属性名和属性值
            Map<String, Object> innermap = new HashMap<>();
            // 遍历子元素
            list.forEach(childElement -> recursionXmlToMap(childElement, innermap));
            outmap.put(element.getName(), innermap);
        }
    }

    /**
     * (多层)map转换为xml格式字符串
     *
     * @param map 需要转换为xml的map
     * @param isCDATA 是否加入CDATA标识符 true:加入 false:不加入
     * @return xml字符串
     */
    public static String multilayerMapToXml(Map<String, Object> map, boolean isCDATA){
        String parentName = "xml";
        Document doc = DocumentHelper.createDocument();
        doc.addElement(parentName);
        String xml = recursionMapToXml(doc.getRootElement(), parentName, map, isCDATA);
        return formatXML(xml);
    }



    /**
     * 格式化xml,显示为容易看的XML格式
     *
     * @param xml 需要格式化的xml字符串
     * @return
     */
    public static String formatXML(String xml) {
        String requestXML = null;
        try {
            // 拿取解析器
            SAXReader reader = new SAXReader();
            Document document = reader.read(new StringReader(xml));
            if (null != document) {
                StringWriter stringWriter = new StringWriter();
                // 格式化,每一级前的空格
                OutputFormat format = new OutputFormat("    ", true);
                // xml声明与内容是否添加空行
                format.setNewLineAfterDeclaration(false);
                // 是否设置xml声明头部
                format.setSuppressDeclaration(false);
                // 是否分行
                format.setNewlines(true);
                XMLWriter writer = new XMLWriter(stringWriter, format);
                writer.write(document);
                writer.flush();
                writer.close();
                requestXML = stringWriter.getBuffer().toString();
            }
            return requestXML;
        } catch (Exception e) {
            System.out.println("格式化xml，失败 --> {}"+ e);
            return null;
        }
    }

    /**
     * multilayerMapToXml核心方法，递归调用
     *
     * @param element 节点元素
     * @param parentName 根元素属性名
     * @param map 需要转换为xml的map
     * @param isCDATA 是否加入CDATA标识符 true:加入 false:不加入
     * @return xml字符串
     */
    @SuppressWarnings("unchecked")
    private static String recursionMapToXml(Element element, String parentName, Map<String, Object> map, boolean isCDATA) {
        Element xmlElement = element.addElement(parentName);
        map.keySet().forEach(key -> {
            Object obj = map.get(key);
            if (obj instanceof Map) {
                recursionMapToXml(xmlElement, key, (Map<String, Object>)obj, isCDATA);
            } else {
                String value = obj == null ? "" : obj.toString();
                if (isCDATA) {
                    xmlElement.addElement(key).addCDATA(value);
                } else {
                    xmlElement.addElement(key).addText(value);
                }
            }
        });
        return xmlElement.asXML();
    }


    /**
     16      * 转换一个xml格式的字符串到json格式
     17      *
     18      * @param xml
     19      *            xml格式的字符串
     20      * @return 成功返回json 格式的字符串;失败反回null
     21      */
    @SuppressWarnings("unchecked")
    public static  String xml2JSON(String xml) {
        JSONObject obj = new JSONObject();
            try {
                     Document doc = DocumentHelper.parseText(xml);
                     Element root = doc.getRootElement();
                     obj.put(root.getName(), iterateElement(root));
                     return obj.toString();
                     } catch (Exception e) {
                         e.printStackTrace();
                         return null;
                     }
             }

              /**
  37      * 一个迭代方法
  38      *
  39      * @param element
  40      * @return java.util.Map 实例
  41      */
              @SuppressWarnings("unchecked")
      private static Map  iterateElement(Element element) {
                 List jiedian = element.elements();
                 Element et = null;
                 Map obj = new HashMap();
                 Object temp;
                 List list = null;
                 for (int i = 0; i < jiedian.size(); i++) {
                         list = new LinkedList();
                         et = (Element) jiedian.get(i);
                         if (et.getTextTrim().equals("")) {
                                 if (et.elements().size() == 0)
                                         continue;
                                 if (obj.containsKey(et.getName())) {
                                         temp = obj.get(et.getName());
                                         if(temp instanceof List){
                                                 list = (List)temp;
                                                 list.add(iterateElement(et));
                                             }else if(temp instanceof Map){
                                                 list.add((HashMap)temp);
                                                 list.add(iterateElement(et));
                                             }else{
                                                 list.add((String)temp);
                                                 list.add(iterateElement(et));
                                             }
                                         obj.put(et.getName(), list);
                                     }else{
                                         obj.put(et.getName(), iterateElement(et));
                                     }
                             } else {
                                 if (obj.containsKey(et.getName())) {
                                         temp = obj.get(et.getName());
                                         if(temp instanceof List){
                                                 list = (List)temp;
                                                 list.add(et.getTextTrim());
                                             }else if(temp instanceof Map){
                                                 list.add((HashMap)temp);
                                                 list.add(iterateElement(et));
                                             }else{
                                                 list.add((String)temp);
                                                 list.add(et.getTextTrim());
                                             }
                                         obj.put(et.getName(), list);
                                     }else{
                                         obj.put(et.getName(), et.getTextTrim());
                                     }

                             }

                     }
                 return obj;
             }

    //3.修改XML配置文件中的某个Key的值
    @SneakyThrows
    public static void updateXml(String xmlFilePath,String key,String value){
          String xmlFile = "/mybatis/generatorConfig.xml";

        //1.将xml文件读取为Document对象
        Document doc = new SAXReader().read(new File(xmlFilePath));

        //2.修改javaClientGenerator标签的属性值
        //(1).通过修改属性值的方法修改属性值
        Element javaClientGeneratorElement=doc.getRootElement().element("context").element("javaClientGenerator");
        javaClientGeneratorElement.attribute(key).setValue(value);

        /*//(2).通过增加同名属性的方法,覆盖修改属性值
        javaClientGeneratorElement.addAttribute("key",value);*/

        //3.修改table标签的属性值
        Element tableNameElement=doc.getRootElement().element("context").element("table");
        tableNameElement.addAttribute("tableName",value);


        //4.修改jdbcConnection标签的属性值
        //(1).通过修改属性值的方法修改属性值
        Element jdbcConnectionElement=doc.getRootElement().element("context").element("jdbcConnection");
        jdbcConnectionElement.attribute("connectionURL").setValue("");


        //5.创建输出流并写入到文件
        FileOutputStream out=new FileOutputStream(xmlFilePath);
        OutputFormat format=OutputFormat.createPrettyPrint();//设置contact.xml文件格式（美观格式）
        format.setEncoding("utf-8");//设置编码格式
        XMLWriter write=new XMLWriter(out,format);
        write.write(doc);
        write.close();
    }


/*  public static void main(String[] args) throws Exception {
        *//*Map<String, String> reqParam = new LinkedHashMap<>();;
        reqParam.put("dldcode","xxx");
        reqParam.put("eventid","vvvv");
        Map<String,Object> reqParamMap = new HashMap<>();
        reqParamMap.put("querymsg",reqParam);
        String toXml = XmlUtil.multilayerMapToXml(reqParamMap,false);
        System.out.println("toXml:"+toXml);
        *//*


*//*      XmlCaseQueryVo xmlCaseQueryVo = XmlCaseQueryVo.builder()
              .dldcode("cccc")
              .eventid("bbbb")
              .build();
      String toXml = XmlUtil.convertToXml(xmlCaseQueryVo);
      System.out.println("toXml:"+toXml);*//*


    }*/

}
