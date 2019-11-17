package com.herman.scaffold.service.impl;

import com.herman.scaffold.model.UserInfo;
import com.herman.scaffold.dao.mapper.UserInfoMapper;
import com.herman.scaffold.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author herman
 * @since 2019-11-13
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
