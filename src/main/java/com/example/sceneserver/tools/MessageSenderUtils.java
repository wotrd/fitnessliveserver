package com.example.sceneserver.tools;


import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * Created by wkj_pc on 2017/6/7.
 */
public class MessageSenderUtils {

  public static boolean sendMessage(String content,String number){
      HttpClient client = new HttpClient();
      PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
      post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
      NameValuePair[] data ={ new NameValuePair("Uid", "wangkaijin"),new NameValuePair("Key",
              "b27892d5805686b0e780"),new NameValuePair("smsMob",number),
              new NameValuePair("smsText",content)};
      post.setRequestBody(data);
      try{
          client.executeMethod(post);
          Header[] headers = post.getResponseHeaders();
          int statusCode = post.getStatusCode();
          //System.out.println("statusCode:"+statusCode);
          for(Header h : headers)
          {
              System.out.println(h.toString());
          }
          String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
          //System.out.println(result); //打印返回消息状态
          if (statusCode!=200){
              post.releaseConnection();
              return false;
          }
      }catch (Exception e){
          return false;
      }
      post.releaseConnection();
      return true;
  }
}
