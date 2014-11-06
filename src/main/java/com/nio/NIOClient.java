package com.nio;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 14-11-6
 * Time: 下午5:51
 * To change this template use File | Settings | File Templates.
 */
public class NIOClient {
    //NIOClient的任务就是按照配置文件中的ip,切分规则将相应的sql语句发送到NIOServer，然后NIOServer那里会重新拼接sql,然后调用Jdbc与mysql集群里
    //进行交互
}
