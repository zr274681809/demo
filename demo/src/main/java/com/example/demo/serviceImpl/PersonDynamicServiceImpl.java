package com.example.demo.serviceImpl;

import com.example.demo.entity.PersonDynamic;
import com.example.demo.mapper.PersonDynamicMapper;
import com.example.demo.service.PersonDynamicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/** @author lyn
 * @description //TODO 动态服务实现
 * @date 2020/4/7 15:18 
*/
@Service
public class PersonDynamicServiceImpl implements PersonDynamicService {
    @Resource
    PersonDynamicMapper personDynamicMapper;
    @Override
    public List<PersonDynamic> selectDynamicByAccount(String userAccount, String friendAccount, Integer pageNumber) {
        if (pageNumber<=0){
            pageNumber=1;
        }
        return personDynamicMapper.selectPageByAccount(userAccount,friendAccount,(pageNumber-1)*3);
    }
}
