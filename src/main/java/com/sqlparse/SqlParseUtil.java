package com.sqlparse;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 14-11-5
 * Time: 下午5:18
 * To change this template use File | Settings | File Templates.
 */
public class SqlParseUtil {
    public static DBEntity parse(Statement statement){
        DBEntity dbEntity=new DBEntity();
        if(statement instanceof Select){
            dbEntity=new SelectParser().parseSelect(statement);
        }else if(statement instanceof Update){
            dbEntity=new UpdateParser().parseUpdate(statement);
        }else if(statement instanceof Insert){
            dbEntity=new InsertParser().parseInsert(statement);
        }else if(statement instanceof Delete){
            dbEntity=new DeleteParser().parseDelete(statement);
        }
        return dbEntity;
    }
    public static Statement getStatementFromSql(String sql){
        Statement statement=null;
        try{
            statement=CCJSqlParserUtil.parse(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return statement;
    }
    public static void executeSQL(String sql){
            Statement statement=getStatementFromSql(sql);
            DBEntity dbEntity=null;
            if(statement instanceof Select){
            dbEntity=parse(statement);
            System.out.println(dbEntity.getDbName()); //数据库名
            System.out.println(dbEntity.getTableName()); //表名
            //根据数据库名和表名,ip配置可以找到分库
            System.out.println(dbEntity.getWhereCondition()); //where条件,根据这个可以定位到水平分库
            System.out.println(dbEntity.getOrderByCondition());//结果集处理
            System.out.println(dbEntity.getLimitValue());   //结果集处理或者定位水平分库
            System.out.println(dbEntity.getGroupByCondition());//结果集处理
            System.out.println(dbEntity.getResultList());//结果集
        }else if(statement instanceof Update){
            dbEntity=parse(getStatementFromSql(sql));
            System.out.println(dbEntity.getDbName());
            System.out.println(dbEntity.getTableName());
         //根据数据库名和表名,ip配置可以找到分库
            System.out.println(dbEntity.getWhereCondition());   //where条件,根据这个可以定位到水平分库
            System.out.println(dbEntity.getResultList());       //要update的列
            System.out.println(dbEntity.getItemsList());       //要update的列的值
        }else if(statement instanceof Insert){
            dbEntity=parse(getStatementFromSql(sql)) ;
            System.out.println(dbEntity.getDbName());
            System.out.println(dbEntity.getTableName());
         //根据数据库名和表名,ip配置可以找到分库
            System.out.println(dbEntity.getWhereCondition());   //where条件,根据这个可以定位到水平分库
            System.out.println(dbEntity.getResultList());       //要update的列
            System.out.println(dbEntity.getItemsList());       //要update的列的值
        }else if(statement instanceof Delete){
            dbEntity=parse(getStatementFromSql(sql)) ;
            System.out.println(dbEntity.getDbName());
            System.out.println(dbEntity.getTableName());
         //根据数据库名和表名,ip配置可以找到分库
            System.out.println(dbEntity.getWhereCondition());   //where条件,根据这个可以定位到水平分库
        }
    }
    public static void main(String[] args){
         //String sql="select id,name from ins.user order by id,name";
         //String sql="update ins.user set username=?,usersex=? where id=?";
         //String sql="insert into ins.user(id,username,usersex) value(?,?,?)";
         String sql="delete from ins.user where id=?";
         executeSQL(sql);
    }
}
