package com.mapper.user;

import com.model.entity.user.User;
import com.model.vo.user.UserListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByNickName(String nickName);

    User selectByAccount(String account);

    User selectByAccountAndPassword(@Param("account") String account,@Param("password") String password);

    List<UserListVO> userList(@Param("nickname") String nickname,@Param("id") Integer id);
}