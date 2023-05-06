package com.wjd.nowcoder.event;

import com.alibaba.fastjson.JSONObject;
import com.wjd.nowcoder.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    // 处理事件
    public void fireEvent(Event event) {
        // 将事件发布到指定的主题，发送的content为event对象的序列化-JSON字符串
        kafkaTemplate.send(event.getTopic(), JSONObject.toJSONString(event));
    }

}
