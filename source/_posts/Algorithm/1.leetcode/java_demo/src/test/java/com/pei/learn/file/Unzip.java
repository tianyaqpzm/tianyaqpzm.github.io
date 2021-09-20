package com.pei.learn.file;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


/**
 * 1、解压出对标准化路径文件在解压目标目录之外
 * 2、解压对文件消耗过多对系统资源，导致zip炸弹
 */
public class Unzip {
    static final int BUFFER = 12;
    static final int MAX_DATA = 0x6400000; // 100MB
    static final int MAX_ITEM = 1024; // 最大条数


    @Test
    @DisplayName("未对解压对文件名验证，未检查解压文件资源消耗情况")
    public void test_error() throws IOException {

        String filname = "";
        FileInputStream fis = new FileInputStream(filname);
        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
        ZipEntry entery;
//         赋值+ 判断
        while ((entery = zis.getNextEntry()) != null) {
            int count;
            byte data[] = new byte[BUFFER];
            FileOutputStream fos = new FileOutputStream(entery.getName());
            BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);
            while ((count = zis.read(data, 0, BUFFER)) != -1) {
                dest.write(data, 0, count);
            }
            dest.flush();
            dest.close();
            zis.closeEntry();
        }
        zis.close();
    }


    private String can(String filname, String intendeDir) throws IOException {

        File file = new File(intendeDir, filname);
        String canonicalPath = file.getCanonicalPath();

        File intend = new File(intendeDir);
        String canonicalIntend = intend.getCanonicalPath();
        if (canonicalPath.startsWith(canonicalIntend)) {
            return canonicalPath;
        } else {
            throw new IllegalStateException("文件不在目录内");

        }
    }

    @Test
    @DisplayName("未对解压对文件名验证，未检查解压文件资源消耗情况")
    public void test_right() throws IOException {

        FileInputStream fis = new FileInputStream("");
        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
        ZipEntry entery;
        int sum = 0;
        int item = 0;
        while ((entery = zis.getNextEntry()) != null) {
            int count;
            byte data[] = new byte[BUFFER];
            FileOutputStream fos = new FileOutputStream(entery.getName());
            BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);
            while ((count = zis.read(data, 0, BUFFER)) != -1) {
                sum += count;
                if (sum > MAX_DATA) {
                    break;
                }
                dest.write(data, 0, count);
            }
            item++;
            if (item > MAX_ITEM) {
                // TODO: 2021-08-08
            }
            if (sum > MAX_DATA) {
                break;
            }

            dest.flush();
            dest.close();
            zis.closeEntry();
        }
        zis.close();
    }


}
