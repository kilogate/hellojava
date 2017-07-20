package com.kilogate.hello.java.javase.jdkapi.io.xml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * SAX 流解析器用法
 *
 * @author fengquanwei
 * @create 2017/7/19 15:46
 **/
public class SAXUsage {
    public static void main(String[] args) throws ParserConfigurationException, SAXException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();


    }
}
