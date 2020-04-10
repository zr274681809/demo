package com.example.demo.serviceImpl;

import com.example.demo.entity.UserRelation;
import com.example.demo.service.UserRelationService;
import org.springframework.stereotype.Service;

import java.util.List;

/** @author lyn
 * @description //TODO 好友服务实现
 * @date 2020/4/10 15:22
*/
@Service
public class UserRelationServiceImpl implements UserRelationService {
    @Override
    public String deleteFriend(String friendAccount) {
        return null;
    }

    @Override
    public List<UserRelation> getFriends() {
        return null;
    }
}
