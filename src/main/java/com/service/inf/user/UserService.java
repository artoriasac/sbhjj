package com.service.inf.user;

import com.model.dto.user.*;
import com.model.vo.position.PositionListVO;
import com.model.vo.user.CourtyardListVO;
import com.model.vo.user.IdCardCheckVO;
import com.model.vo.user.UserListVO;

import java.util.List;

public interface UserService {
    IdCardCheckVO idCardCheck(String realName, String idCard);

    void signUp(SignUpDTO signUpDTO)throws Exception;

    String signIn(SignInDTO signInDTO);

    void updateUser(UpdateUserDTO updateUserDTO);

    void addCourtyard(AddCourtyardDTO addCourtyardDTO)throws Exception;

    void setCourtyardAdmin(SetCourtyardAdminDTO setCourtyardAdminDTO)throws Exception;

    List<CourtyardListVO> courtyardList();

    List<UserListVO> userList(String nickName,Integer id);

    void addCourtyardUser(AddCourtyardUserDTO addCourtyardUserDTO);

    boolean isAdmin(Integer userId);

    void addPosition(AddPositionListDTO addpositionListDTO);

    List<PositionListVO> userPositionList(Integer userId);
}
