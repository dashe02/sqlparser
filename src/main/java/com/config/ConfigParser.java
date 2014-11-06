package com.config;
import com.util.Constant;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 14-11-6
 * Time: 下午6:41
 * To change this template use File | Settings | File Templates.
 */
public class ConfigParser {
    public List<Map<String,String>> parseDBServerXml(){
            SAXReader reader=new SAXReader();
            List<Map<String,String>> ruleEntity=new ArrayList<Map<String, String>>();
            InputStream in=getInputStream(Constant.serverConf);
            try{
                Document document=reader.read(in);
                Element root=document.getRootElement();
                Iterator it=root.elementIterator(Constant.dbServer);
                while (it.hasNext()){
                    Element e=(Element)it.next();
                    String server=e.attribute(Constant.id).getValue();
                    String ip=e.element(Constant.ip).getStringValue();
                    String port=e.element(Constant.port).getStringValue();
                    Map<String,String> m=new HashMap<String, String>();
                    m.put(server,ip+","+port);
                    ruleEntity.add(m);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        return ruleEntity;
    }
    public List<RuleEntity> parseRuleXml(){
        //解析rule.xml的时候,将server，ip，port,数据库,切分规则都放到一个实体中
        SAXReader reader=new SAXReader();
        List<RuleEntity> ruleEntity=new ArrayList<RuleEntity>();
        InputStream in=getInputStream(Constant.ruleConf);
        try{
            Document document=reader.read(in);
            Element root=document.getRootElement();
            Iterator it=root.elementIterator(Constant.rule);
            while (it.hasNext()){
                Element e=(Element)it.next();
                String servers=e.attribute(Constant.db).getValue();
                List<String> serverList=transferStringToList(servers);
                Iterator schema=e.elementIterator(Constant.schema);
                while(schema.hasNext()){
                RuleEntity r=new RuleEntity();
                Element ee=(Element)schema.next();
                String schemaName=ee.attribute(Constant.name).getValue();
                String tableName=ee.attribute(Constant.tableName).getValue();
                List<String> tableList=transferStringToList(tableName);
                String parameter=ee.element(Constant.parameter).getStringValue();
                String expression=ee.element(Constant.expression).getStringValue();
                r.setServerList(serverList);
                r.setSchema(schemaName);
                r.setTableName(tableList);
                r.setParameter(parameter);
                r.setExpression(expression);
                ruleEntity.add(r);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return ruleEntity;
    }
     public List<String> transferStringToList(String str){
         List<String> l=new ArrayList<String>();
         String[] s=str.split(",");
         for(int i=0;i<s.length;i++){
             l.add(s[i]);
         }
         return l;
     }
    public InputStream getInputStream(String flag){
        InputStream in=null;
        try{
            if(flag.equals(Constant.serverConf)){
            in=new FileInputStream(Constant.dbServerConfigPath);
            }else if(flag.equals(Constant.ruleConf)){
            in=new FileInputStream(Constant.ruleConfigPath);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return in;
    }
    public static void main(String[] args){
        ConfigParser configParser=new ConfigParser();
        List<RuleEntity> l=configParser.parseRuleXml();
        System.out.println(l.get(0).getServerList());   //serverList
        for(RuleEntity rr:l){
            System.out.println(rr.getSchema());
            System.out.println(rr.getTableName());
            System.out.println(rr.getParameter());
            System.out.println(rr.getExpression());
        }
    }
}
