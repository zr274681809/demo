package com.example.demo.mapper;

import com.example.demo.entity.PersonDynamic;

import java.util.List;

/** @author lyn
 * @description //TODO 动态数据库查询
 * @date 2020/4/7 15:18 
*/
public interface PersonDynamicMapper {
    /** @description //TODO 通过用户和页码查询动态
     * @parme [userAccount, friendAccount, page]
     * @return java.util.List<com.example.demo.entity.PersonDynamic>
    */
    List<PersonDynamic> selectPageByAccount(String userAccount, String friendAccount, Integer page);
}
