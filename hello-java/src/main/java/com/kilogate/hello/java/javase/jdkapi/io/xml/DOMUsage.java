package com.kilogate.hello.java.javase.jdkapi.io.xml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * DOM 用法
 *
 * @author fengquanwei
 * @create 2017/7/9 22:26
 **/
public class DOMUsage {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        File xmlFile = new File("");
        Document document = documentBuilder.parse(xmlFile);

        Element rootElement = document.getDocumentElement();

        // 遍历子节点
        NodeList childNodes = rootElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            if (childNode instanceof Element) {
                Element childElement = (Element) childNode;
                Node firstChildNode = childElement.getFirstChild();
                if (firstChildNode instanceof Text) {
                    Text firstChildText = (Text) firstChildNode;
                    String text = firstChildText.getData().trim();
                    if (childElement.getTagName().equals("name")) {
                        System.out.println("name=" + text);
                    }
                }
            }
        }

        // 另一种遍历子节点的方法
        for (Node childNode = rootElement.getFirstChild(); childNode != null; childNode = childNode.getNextSibling()) {
            // 枚举节点属性
            NamedNodeMap attributes = childNode.getAttributes();
            for(int i =0;i<attributes.getLength();i++){
                Node attribute = attributes.item(i);
                String name = attribute.getNodeName();
                String value = attribute.getNodeValue();
            }

        }
    }
}