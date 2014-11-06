package com.sqlparse;

import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 14-11-6
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
public class DeleteParser {
    Delete delete=null;
    String dbTableStr=null;
    String whereCondition=null;
    DBEntity dbEntity=new DBEntity();
    public DBEntity parseDelete(Statement statement){
        delete=(Delete)statement;
        if(delete.getTable()!=null){
            dbTableStr=delete.getTable().toString();
            if(dbTableStr.contains(".")){
                dbEntity.setDbName(dbTableStr.substring(0,dbTableStr.indexOf(".")));
                dbEntity.setTableName(dbTableStr.substring(dbTableStr.indexOf(".")+1,dbTableStr.length()));
            }else{
                throw new RuntimeException("sql没有写明数据库!");
            }
        }
        if(delete.getWhere()!=null){
            whereCondition=delete.getWhere().toString();
            dbEntity.setWhereCondition(whereCondition);
        }
        return dbEntity;
    }
}
