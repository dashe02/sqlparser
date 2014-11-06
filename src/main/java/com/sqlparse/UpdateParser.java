package com.sqlparse;

import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 14-11-6
 * Time: 下午5:05
 * To change this template use File | Settings | File Templates.
 */
public class UpdateParser {
        Update update=null;
        String dbTableStr=null;
        String whereCondition=null;
        List selectItems=null;
        List valueItems=null;
        DBEntity dbEntity=new DBEntity();
     public DBEntity parseUpdate(Statement statement){
         update=(Update)statement;
         if(update.getTable()!=null){
             dbTableStr=update.getTable().toString();
             if(dbTableStr.contains(".")){
                 dbEntity.setDbName(dbTableStr.substring(0,dbTableStr.indexOf(".")));
                 dbEntity.setTableName(dbTableStr.substring(dbTableStr.indexOf(".")+1,dbTableStr.length()));
             }else{
                 throw new RuntimeException("sql没有写明数据库!");
             }
         }
         if(update.getWhere()!=null){
             whereCondition=update.getWhere().toString();
             dbEntity.setWhereCondition(whereCondition);
         }
         if(update.getColumns()!=null){
             selectItems=update.getColumns();
             dbEntity.setResultList(selectItems);
         }
         if(update.getExpressions()!=null){
             valueItems=update.getExpressions();
             dbEntity.setValueItems(valueItems);
         }
         return dbEntity;
     }
}
