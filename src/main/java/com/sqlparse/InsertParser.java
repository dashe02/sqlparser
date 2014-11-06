package com.sqlparse;

import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.update.Update;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 14-11-6
 * Time: 下午5:08
 * To change this template use File | Settings | File Templates.
 */
public class InsertParser {
    Insert insert=null;
    String dbTableStr=null;
    List valueItems=null;
    DBEntity dbEntity=new DBEntity();
    public DBEntity parseInsert(Statement statement){
        insert=(Insert)statement;
        if(insert.getTable()!=null){
            dbTableStr=insert.getTable().toString();
            if(dbTableStr.contains(".")){
                dbEntity.setDbName(dbTableStr.substring(0,dbTableStr.indexOf(".")));
                dbEntity.setTableName(dbTableStr.substring(dbTableStr.indexOf(".")+1,dbTableStr.length()));
            }else{
                throw new RuntimeException("sql没有写明数据库!");
            }
        }
        if(insert.getItemsList()!=null){
            ItemsList seItems=insert.getItemsList();
            dbEntity.setItemsList(seItems);
        }
        if(insert.getColumns()!=null){
            valueItems=insert.getColumns();
            dbEntity.setResultList(valueItems);
        }
        dbEntity.setWhereCondition(dbEntity.getResultList().get(0)+"=?");
        return dbEntity;
    }
}
