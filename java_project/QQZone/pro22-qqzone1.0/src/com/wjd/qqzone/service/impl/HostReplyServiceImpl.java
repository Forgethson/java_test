package com.wjd.qqzone.service.impl;

import com.wjd.qqzone.dao.HostReplyDAO;
import com.wjd.qqzone.pojo.HostReply;
import com.wjd.qqzone.service.HostReplyService;

public class HostReplyServiceImpl implements HostReplyService {

    private HostReplyDAO hostReplyDAO ;

    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return hostReplyDAO.getHostReplyByReplyId(replyId);
    }

    @Override
    public void delHostReply(Integer id) {
        hostReplyDAO.delHostReply(id);
    }
}
