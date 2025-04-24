package com.auto.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 标记该类型处理器处理的 Java 类型为 JSONObject
@MappedTypes(JSONObject.class)
// 标记该类型处理器处理的 JDBC 类型为 VARCHAR
@MappedJdbcTypes(JdbcType.VARCHAR)
public class JSONObjectTypeHandler extends BaseTypeHandler<JSONObject>{

    // 设置非空参数，将 JSONObject 序列化为字符串存入数据库
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JSONObject jsonObject, JdbcType jdbcType) throws SQLException {
        ps.setString(i, jsonObject.toJSONString());
    }

    // 根据列名从结果集中获取可空结果，将数据库中的字符串反序列化为 JSONObject
    @Override
    public JSONObject getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String jsonStr = resultSet.getString(s);
        return jsonStr != null ? JSONObject.parseObject(jsonStr) : null;
    }

    // 根据列索引从结果集中获取可空结果，将数据库中的字符串反序列化为 JSONObject
    @Override
    public JSONObject getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String jsonStr = resultSet.getString(i);
        return jsonStr != null ? JSONObject.parseObject(jsonStr) : null;
    }

    // 根据列索引从调用语句中获取可空结果，将数据库中的字符串反序列化为 JSONObject
    @Override
    public JSONObject getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String jsonStr = callableStatement.getString(i);
        return jsonStr != null ? JSONObject.parseObject(jsonStr) : null;
    }
}
