package com.sqlparse;

import net.sf.jsqlparser.expression.operators.relational.ItemsList;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 14-11-6
 * Time: 下午2:28
 * To change this template use File | Settings | File Templates.
 */
public class DBEntity {
    private String dbName;             //数据库名
    private String tableName;          //表名
    private List   resultList;         //查询结果
    private String whereCondition;     //where条件
    private List groupByCondition;   //GroupBy条件
    private long limitValue;           //limit的值
    private List orderByCondition;     //orderBy条件
    private List valueItems;            //相应的值
    private ItemsList itemsList;        //insert中的一个List
    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List getResultList() {
        return resultList;
    }

    public void setResultList(List resultList) {
        this.resultList = resultList;
    }

    public String getWhereCondition() {
        return whereCondition;
    }

    public void setWhereCondition(String whereCondition) {
        this.whereCondition = whereCondition;
    }

    public long getLimitValue() {
        return limitValue;
    }

    public void setLimitValue(long limitValue) {
        this.limitValue = limitValue;
    }

    public List getGroupByCondition() {
        return groupByCondition;
    }

    public void setGroupByCondition(List groupByCondition) {
        this.groupByCondition = groupByCondition;
    }

    public List getOrderByCondition() {
        return orderByCondition;
    }

    public void setOrderByCondition(List orderByCondition) {
        this.orderByCondition = orderByCondition;
    }

    public List getValueItems() {
        return valueItems;
    }

    public void setValueItems(List valueItems) {
        this.valueItems = valueItems;
    }

    public ItemsList getItemsList() {
        return itemsList;
    }

    public void setItemsList(ItemsList itemsList) {
        this.itemsList = itemsList;
    }
}
