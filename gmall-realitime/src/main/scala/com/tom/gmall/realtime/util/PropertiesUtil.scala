package com.tom.gmall.realtime.util

import java.io.InputStreamReader
import java.util.Properties

/**
 * @author Tom 
 * @create 2020-07-19 14:02 
 */
object PropertiesUtil {

  def main(args: Array[String]): Unit = {
    //通过配置文件名，得到properties对象
    val properties: Properties = PropertiesUtil.load("config.properties")

    //打印配置文件中的"kafka.broker.list"
    println(properties.getProperty("kafka.broker.list"))
  }
  //这个方法的作用将传入的一个文件加载成properties对象
  def load(propertieName:String): Properties ={
    val prop=new Properties();
    prop.load(new InputStreamReader(Thread.currentThread().getContextClassLoader.getResourceAsStream(propertieName) , "UTF-8"))
    prop
  }



}
