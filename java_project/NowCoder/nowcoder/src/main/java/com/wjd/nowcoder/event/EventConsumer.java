package com.wjd.nowcoder.event;

import com.alibaba.fastjson.JSONObject;

import com.wjd.nowcoder.entity.DiscussPost;
import com.wjd.nowcoder.entity.Event;
import com.wjd.nowcoder.entity.Message;
import com.wjd.nowcoder.service.DiscussPostService;
import com.wjd.nowcoder.service.ElasticsearchService;
import com.wjd.nowcoder.service.MessageService;
import com.wjd.nowcoder.util.CommunityConstant;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class EventConsumer implements CommunityConstant {

    private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);

    @Autowired
    private MessageService messageService;

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private ElasticsearchService elasticsearchService;

    // 处理系统消息
    @KafkaListener(topics = {TOPIC_COMMENT, TOPIC_LIKE, TOPIC_FOLLOW})
    public void handleCommentMessage(ConsumerRecord record) {
        if (record == null || record.value() == null) {
            logger.error("消息的内容为空!");
            return;
        }

        // 将record.value()从JSON格式转为Event对象，得到event
        Event event = JSONObject.parseObject(record.value().toString(), Event.class);
        if (event == null) {
            logger.error("消息格式错误!");
            return;
        }

        // 发送站内通知（假设发送者的id固定为1，同时conversationId固定为comment，content为需要拼接内容的JSON字符串）
        Message message = new Message();
        message.setFromId(SYSTEM_USER_ID);
        message.setToId(event.getEntityUserId()); // 目标用户id
        message.setConversationId(event.getTopic());
        message.setCreateTime(new Date());

        // 拼接message的内容（原本message的内容就是字符串文本，这里是系统通知，内容是固定的几种，便于复用，拼接后的map加入到content）
        Map<String, Object> content = new HashMap<>();
        content.put("userId", event.getUserId()); // 触发用户id
        content.put("entityType", event.getEntityType()); // 实体类型
        content.put("entityId", event.getEntityId()); // 实体id
        // 如果event的data属性不为空，则往content中添加之
        if (!event.getData().isEmpty()) {
            for (Map.Entry<String, Object> entry : event.getData().entrySet()) {
                content.put(entry.getKey(), entry.getValue());
            }
        }
        // 将content转为JSON字符串再设置到message对象的内容字段
        message.setContent(JSONObject.toJSONString(content));
        messageService.addMessage(message);
    }

    // 消费发帖事件
    @KafkaListener(topics = {TOPIC_PUBLISH})
    public void handlePublishMessage(ConsumerRecord record) {
        if (record == null || record.value() == null) {
            logger.error("消息的内容为空!");
            return;
        }

        Event event = JSONObject.parseObject(record.value().toString(), Event.class);
        if (event == null) {
            logger.error("消息格式错误!");
            return;
        }

        DiscussPost post = discussPostService.findDiscussPostById(event.getEntityId());
        // 加入到ES服务器
        elasticsearchService.saveDiscussPost(post);
    }
}
