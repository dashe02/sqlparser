package com.config;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 14-11-6
 * Time: 下午7:04
 * To change this template use File | Settings | File Templates.
 */
public class RuleEntity {
     private List<Map<String,String>> listMap;
     private List<String> serverList;
     private String schema;
     private List<String> tableName;
     private String ip;
     private String port;
     private String parameter;
     private String expression;

    public List<Map<String, String>> getListMap() {
        return listMap;
    }

    public void setListMap(List<Map<String, String>> listMap) {
        this.listMap = listMap;
    }

    public List<String> getServerList() {
        return serverList;
    }

    public void setServerList(List<String> serverList) {
        this.serverList = serverList;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public List<String> getTableName() {
        return tableName;
    }

    public void setTableName(List<String> tableName) {
        this.tableName = tableName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
