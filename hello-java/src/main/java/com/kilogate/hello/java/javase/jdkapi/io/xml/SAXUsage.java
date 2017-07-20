package com.kilogate.hello.java.javase.jdkapi.io.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * SAX 流解析器用法
 *
 * @author fengquanwei
 * @create 2017/7/19 15:46
 **/
public class SAXUsage {
    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException {
        String url = "http://www.w3c.org";

        DefaultHandler defaultHandler = new DefaultHandler() {
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                if (localName.equals("a") && attributes != null) {
                    for (int i = 0; i < attributes.getLength(); i++) {
                        String attributesLocalName = attributes.getLocalName(i);
                        if (attributesLocalName.equals("href")) {
                            System.out.println(attributes.getValue(i));
                        }
                    }
                }
            }
        };

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setNamespaceAware(true);
        saxParserFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

        SAXParser saxParser = saxParserFactory.newSAXParser();

        InputStream inputStream = new URL(url).openStream();
        saxParser.parse(inputStream, defaultHandler);
    }
}
