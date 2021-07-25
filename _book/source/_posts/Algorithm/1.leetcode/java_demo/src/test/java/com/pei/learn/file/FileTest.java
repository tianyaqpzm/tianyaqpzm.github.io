package com.pei.learn.file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FileTest {
    private static String tmpDir;
    private static String testDir;
    private static String userFilePath;


    @BeforeAll
    static void beforeAll() throws IOException {
        final URL userFileUrl = FileTest.class.getClassLoader().getResource("user.txt");
//        unix 系统和window系统区别
//        userFilePath = userFileUrl.getFile().replaceFirst("^/", "");
        userFilePath = userFileUrl.getFile();
        tmpDir = new File(userFilePath).getParent() + "/tmp";
//        testDir = FileTest.class.getClassLoader().getResource("testDir").getFile().replaceFirst("^/", "");
        testDir = FileTest.class.getClassLoader().getResource("testDir").getFile();
        FileUtils.deleteDirectory(new File(tmpDir));
        Files.createDirectories(Paths.get(tmpDir));

    }

    @AfterAll
    static void aftetAll() throws IOException {
        FileUtils.deleteDirectory(new File(tmpDir));
    }

    @Test
    @DisplayName("读取所有行")
    public void test_read_file_lines() throws IOException {
        final List<String> lines = Files.readAllLines(Paths.get(userFilePath));
        assertThat(lines).containsExactly("alpha", "beta", "gamma", "prod");
    }

    @Test
    @DisplayName("将所有行写入文件")
    void test_write_into_file() throws IOException {
        final List<String> lines = Lists.newArrayList("alpha", "beta", "gamma", "prod");
        String tmpFilePath = tmpDir + "/target.user.txt";
        Files.write(Paths.get(tmpFilePath), lines);
        final List<String> actualLines = Files.readAllLines(Paths.get(tmpFilePath));
        assertThat(actualLines).containsExactly("alpha", "beta", "gamma", "prod");
    }

    @Test
    @DisplayName("遍历目录")
    void test_walk_file() throws IOException {
        final Path rootDir = Paths.get(testDir);
        List<String> files = Files.walk(rootDir)
                .map(path -> StringUtils.removeStart(path.toString(), rootDir.toString()))
                .collect(Collectors.toList());
        final List<String> lines = Files.readAllLines(Paths.get(userFilePath));
        assertThat(files).containsExactly("", "/12.txt", "/1", "/1/123");
    }
}
