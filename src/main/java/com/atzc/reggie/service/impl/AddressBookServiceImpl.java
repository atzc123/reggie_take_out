package com.atzc.reggie.service.impl;

import com.atzc.reggie.entity.AddressBook;
import com.atzc.reggie.mapper.AddressBookMapper;
import com.atzc.reggie.service.AddressBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author 张驰
* @description 针对表【address_book(地址管理)】的数据库操作Service实现
* @createDate 2022-12-19 16:46:54
*/
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook>
    implements AddressBookService{

}




