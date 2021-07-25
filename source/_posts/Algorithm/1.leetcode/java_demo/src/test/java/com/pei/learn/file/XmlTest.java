package com.pei.learn.file;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
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
        String xmlString = "<user><id>" + user.getUserId()
                + "</id><role>operator</role><description>"
                + user.getDescription() + "</description></user>";
        OutputStream outputStream = new OutputStream();
        outputStream.write(xmlString.getBytes());
        outputStream.flush();
    }

}

@Data
class User {
    private String userId;
    private String role;
    private String description;
}
