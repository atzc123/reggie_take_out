package com.atzc.reggie.service.impl;

import com.atzc.reggie.entity.User;
import com.atzc.reggie.mapper.UserMapper;
import com.atzc.reggie.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
