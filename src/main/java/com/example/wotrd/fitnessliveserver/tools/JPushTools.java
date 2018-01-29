package com.example.wotrd.fitnessliveserver.tools;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.*;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class JPushTools {
    @Autowired
    Environment env;

    @Bean
    public JPushClient getJpushClient() {

        JPushClient jpushClient = new JPushClient(env.getProperty("jpush_secret"),
                env.getProperty("jpush_app_kay"), null,
                ClientConfig.getInstance());
        return jpushClient;
    }
     /**
      * 推送给android全部设备
      * @param content
      */
    public static PushPayload buildAndroidAllMessage(String content){
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setMessage(Message.newBuilder()
                .setTitle("sysmessage")
                .setMsgContent(content).build())
                .build();
    }
     /**
      * 通过别名推送给android设备
      * @param alias
      * @param content
      */
     public static PushPayload buildAndroidAllMessageWithAlias(String content,String alias){
         return PushPayload.newBuilder()
                 .setPlatform(Platform.android())
                 .setMessage(Message.newBuilder()
                         .setTitle("sysmessage")
                         .setMsgContent(content).build())
                 .setAudience(Audience.alias(alias))
                 .build();
     }

    /**
     * 快捷地构建推送对象：所有平台，所有设备，内容为 ALERT 的通知。
     */
    public static PushPayload buildPushObject_all_all_alert(String content) {
        return PushPayload.alertAll(content);
    }

    /**
     * 构建推送对象：所有平台，推送目标是别名为 alias，通知内容为 content。
     */
    public static PushPayload buildPushObject_all_alias_alert(String alias, String content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(content))
                .build();
    }

    /**
     * 构建推送对象：平台是 Android，目标是 tag 为 "tag" 的设备，
     * 内容是 Android 通知 content，并且标题为 TITLE。
     */
    public static PushPayload buildPushObject_android_tag_alertWithTitle(String tag, String title,
                                                                         String content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag(tag))
                .setNotification(Notification.android(content, title, null))
                .build();
    }

    /**
     * 构建推送对象：平台是 iOS，推送目标是 "tag1", "tag_all" 的交集，
     * 推送内容同时包括通知与消息 - 通知信息是 ALERT，角标数字为 5，通知声音为 "happy"，
     * 并且附加字段 from = "JPush"；消息内容是 MSG_CONTENT。通知是 APNs 推送通道的，
     * 消息是 JPush 应用内消息通道的。APNs 的推送环境是“生产”（
     * 如果不显式设置的话，Library 会默认指定为开发）
     */
    public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage(
            String content, String message) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag_and("tag1", "tag_all"))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(content)
                                .setBadge(5)
                                .setSound("happy")
                                .addExtra("from", "JPush")
                                .build())
                        .build())
                .setMessage(Message.content(message))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .build();
    }
     /**
      *      构建推送对象：平台是 Andorid 与 iOS，推送目标是
      *      （"tag1" 与 "tag2" 的并集）交（"alias1" 与 "alias2" 的并集），
      *      推送内容是 - 内容为 MSG_CONTENT 的消息，并且附加字段 from = JPush。
      */
     public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(
             String content) {
         return PushPayload.newBuilder()
                 .setPlatform(Platform.android_ios())
                 .setAudience(Audience.newBuilder()
                         .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                         .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
                         .build())
                 .setMessage(Message.newBuilder()
                         .setMsgContent(content)
                         .addExtra("from", "JPush")
                         .build())
                 .build();
     }
     /**
      *      构建推送对象：推送内容包含SMS信息
      */
     public static void testSendWithSMS(JPushClient client) {
         try {
             SMS sms = SMS.content("Test SMS", 10);
             client.sendNotificationAll("");
             PushResult result = client.sendAndroidMessageWithAlias
                     ("Test SMS", "test sms", sms, "alias1");
             //LOG.info("Got result - " + result);
         } catch (APIConnectionException e) {
             //LOG.error("Connection error. Should retry later. ", e);
         } catch (APIRequestException e) {
             /*LOG.error("Error response from JPush server. Should review and fix it. ", e);
             LOG.info("HTTP Status: " + e.getStatus());
             LOG.info("Error Code: " + e.getErrorCode());
             LOG.info("Error Message: " + e.getErrorMessage());*/
         }
     }
}
