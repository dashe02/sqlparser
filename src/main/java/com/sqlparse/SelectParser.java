package com.sqlparse;

import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Distinct;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 14-11-6
 * Time: 下午5:01
 * To change this template use File | Settings | File Templates.
 */
public class SelectParser {
     public DBEntity parseSelect(Statement statement){
         Select select=(Select)statement;
         Distinct distinct=null;
         List groupByItems=null;
         long limitValue=0;
         List orderByCondition=null;
         String dbTableStr=null;
         String whereCondition=null;
         List selectItems=null;
         DBEntity dbEntity=new DBEntity();
         if(((PlainSelect)select.getSelectBody()).getFromItem()!=null){
             dbTableStr=(((PlainSelect)select.getSelectBody()).getFromItem()).toString();
             if(dbTableStr.contains(".")){
                 dbEntity.setDbName(dbTableStr.substring(0,dbTableStr.indexOf(".")));
                 dbEntity.setTableName(dbTableStr.substring(dbTableStr.indexOf(".")+1,dbTableStr.length()));
             }else{
                 throw new RuntimeException("sql没有写明数据库!");
             }
         }
         if((PlainSelect) select.getSelectBody()!=null){
             selectItems=((PlainSelect) select.getSelectBody()).getSelectItems();
             dbEntity.setResultList(selectItems);
         }
         if(null!=(((PlainSelect) select.getSelectBody()).getWhere())){
             whereCondition=(((PlainSelect) select.getSelectBody()).getWhere()).toString();
         }
         dbEntity.setWhereCondition(whereCondition);
         if(((PlainSelect) select.getSelectBody()).getDistinct()!=null){
             distinct=((PlainSelect) select.getSelectBody()).getDistinct();
         }
         if(((PlainSelect) select.getSelectBody()).getGroupByColumnReferences()!=null){
             groupByItems=((PlainSelect) select.getSelectBody()).getGroupByColumnReferences();
             dbEntity.setGroupByCondition(groupByItems);
         }
         if(((PlainSelect)select.getSelectBody()).getLimit()!=null){
             limitValue=((PlainSelect)select.getSelectBody()).getLimit().getRowCount();
             dbEntity.setLimitValue(limitValue);
         }
         if(((PlainSelect) select.getSelectBody()).getOrderByElements()!=null){
             orderByCondition=((PlainSelect) select.getSelectBody()).getOrderByElements();
             dbEntity.setOrderByCondition(orderByCondition);
         }
         return dbEntity;
     }
}
