package com.pei.learn.sql;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sql {

    @Test
    @DisplayName("参数化查询")
    public void test_binary_error() {
        Connection ct = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sqltring = "SELECT * from T_item Where username = ? AND age = ?";
        try {
            stmt = ct.prepareStatement(sqltring);
//            从下标1开始
            stmt.setString(1, "userName");
            stmt.setInt(2, 18);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    @DisplayName("mybatis")
    public void test_mybatis() {
//        使用# 号，而不是 $符号

    }


}
