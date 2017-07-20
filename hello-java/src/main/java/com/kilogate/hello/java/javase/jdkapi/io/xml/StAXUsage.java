package com.kilogate.hello.java.javase.jdkapi.io.xml;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.net.URL;

/**
 * StAX 拉解析器用法
 *
 * @author fengquanwei
 * @create 2017/7/20 23:29
 **/
public class StAXUsage {
    public static void main(String[] args) throws IOException, XMLStreamException {
        String url = "http://www.w3c.org";

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new URL(url).openStream());

        while (xmlStreamReader.hasNext()) {
            int event = xmlStreamReader.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                if (xmlStreamReader.getLocalName().equals("a")) {
                    String href = xmlStreamReader.getAttributeValue(null, "href");
                    if (href != null) {
                        System.out.println(href);
                    }
                }
            }
        }
    }
}
