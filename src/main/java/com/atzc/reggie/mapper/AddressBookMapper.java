package com.atzc.reggie.mapper;

import com.atzc.reggie.entity.AddressBook;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 张驰
* @description 针对表【address_book(地址管理)】的数据库操作Mapper
* @createDate 2022-12-19 16:46:54
* @Entity com.atzc.reggie.entity.AddressBook
*/
@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {

}




