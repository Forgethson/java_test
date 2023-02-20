package com.wjd.qqzone.dao.impl;

import com.wjd.myssm.basedao.BaseDAO;
import com.wjd.qqzone.dao.TopicDAO;
import com.wjd.qqzone.pojo.Topic;
import com.wjd.qqzone.pojo.UserBasic;

import java.util.List;

public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return super.executeQuery("select * from t_topic where author = ? " , userBasic.getId());
    }

    @Override
    public void addTopic(Topic topic) {

    }

    @Override
    public void delTopic(Topic topic) {

    }

    @Override
    public Topic getTopic(Integer id) {
        return null;
    }
}
