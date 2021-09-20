package com.pei.learn.file;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 禁用使用不安全的XSLT转为XML文件
 */
public class Xslt {

    @Test
    @DisplayName("安全使用xslt")
    void test_build_xslt() throws IOException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            // 安全要求:  java对xslt 内置黑名单， 设为true 开启防护，禁用不安全对方法
            transformerFactory.setFeature("http://java.xml.XMLConstants/feature/secure-processing", true);

//            获取转换器实例
            Transformer xslt = transformerFactory.newTransformer(new StreamSource("xslt"));

//            去掉<?xml verison=1.0 encoding="UTF-8">
            xslt.setOutputProperty("omit-xml-declaration", "yes");

            xslt.transform(new StreamSource("src"), new StreamResult(new FileOutputStream("dst")));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
