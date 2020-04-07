package com.example.demo.service;

import com.example.demo.entity.PersonDynamic;

import java.util.List;

/** @author lyn
 * @description //TODO 动态服务接口
 * @date 2020/4/7 15:18
*/
public interface PersonDynamicService {
    /** @description //TODO 通过用户名和页码查询动态
     * @parme [userAccount, friendAccount, pageNumber]
     * @return java.util.List<com.example.demo.entity.PersonDynamic>
    */
    List<PersonDynamic> selectDynamicByAccount(String userAccount, String friendAccount, Integer pageNumber);
}
