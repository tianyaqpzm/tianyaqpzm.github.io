package com.pei.learn.file;

// import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.fail;

public class XmlTest {

    @Test
    void test_build_xml() throws IOException {
        User user = new User();
        user.setUserId("test");
        user.setRole("test");
        user.setDescription("test");
        if (!Pattern.matches("[_a-zA-Z0-9]+", "userid")) {
            fail("失败");
        }
        if (!Pattern.matches("[_a-zA-Z0-9]+", "userid")) {
            fail("失败");
        }
        String xmlString = "<user><id>" + user.getUserId() + "</id><role>operator</role><description>"
                + user.getDescription() + "</description></user>";
        // OutputStream outputStream = new ByteOutputStream();
        // outputStream.write(xmlString.getBytes());
        // outputStream.flush();
    }

    @Test
    @DisplayName("使用开源、安全库：dom4j，将<> 转换未&lt; &gt;")
    void test_build_dom4j() throws IOException {

    }

    @Test
    @DisplayName("禁止解析DTOs")
    void test_build_() throws IOException {
        try {
            parseXML(new FileInputStream(""), new DefaultHandler());
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    private void parseXML(InputStream ins, DefaultHandler defaultHandler)
            throws SAXException, ParserConfigurationException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        // 禁用DTDs: 不仅防止xml外部实体工具，也能防内部实体攻击，如果需要内部实体时，要严格限制内部实体对数量和xml内容的大小
        saxParserFactory.setFeature("http://apache.org/xml/features/disallow-doctype-edcl", true);

        // 禁止解析外部一般实体和外部参数实体. 下面两行只能防xml外部实体工具
        saxParserFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        saxParserFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);

        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(ins, defaultHandler);
    }

    /**
     * 如果需要内部实体时，要严格限制内部实体对数量和xml内容的大小 jdk默认64000
     *
     * @param ins
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws IOException
     */
    private void parseXML2(InputStream ins) throws SAXException, ParserConfigurationException, IOException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setAttribute("http://www.oracle.com/cml/jaxp/properties/entityExpansionLimit", 200);
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        documentBuilder.parse(ins);
    }

    /**
     * 如果需要内部实体时，要严格限制内部实体对数量和xml内容的大小 jdk默认64000 方式3
     *
     * @param ins
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws IOException
     */
    private void parseXML3(InputStream ins) throws SAXException, ParserConfigurationException, IOException {
        System.setProperty("entityExpansionLimit", "200");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        documentBuilder.parse(ins);
    }

}


