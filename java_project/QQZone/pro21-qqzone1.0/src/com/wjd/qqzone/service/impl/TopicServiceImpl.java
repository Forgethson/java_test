package com.wjd.qqzone.service.impl;

import com.wjd.qqzone.dao.TopicDAO;
import com.wjd.qqzone.pojo.Topic;
import com.wjd.qqzone.pojo.UserBasic;
import com.wjd.qqzone.service.TopicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO = null ;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }
}
