package com.atzc.reggie.service.impl;

import com.atzc.reggie.entity.Employee;
import com.atzc.reggie.mapper.EmployeeMapper;
import com.atzc.reggie.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
