package com.service.imp.user;

import com.common.model.MemberInfo;
import com.common.model.ServiceException;
import com.common.utils.*;
import com.mapper.position.PositionMapper;
import com.mapper.user.CourtyardMapper;
import com.mapper.user.CourtyardUserMapper;
import com.mapper.user.UserMapper;
import com.mapper.user.UserPositionMapper;
import com.model.dto.user.*;
import com.model.entity.position.Position;
import com.model.entity.user.Courtyard;
import com.model.entity.user.CourtyardUser;
import com.model.entity.user.User;
import com.model.entity.user.UserPosition;
import com.model.vo.position.PositionListVO;
import com.model.vo.user.CourtyardListVO;
import com.model.vo.user.IdCardCheckVO;
import com.model.vo.user.UserListVO;
import com.service.inf.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Value("${ID_CARD_API_HOST}")
    private String apiHost;
    @Value("${ID_CARD_API_PATH}")
    private String apiPath;
    @Value("${ID_CARD_API_CODE}")
    private String apiCode;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CourtyardMapper courtyardMapper;

    @Autowired
    private CourtyardUserMapper courtyardUserMapper;

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private UserPositionMapper userPositionMapper;

    @Override
    public IdCardCheckVO idCardCheck(String realName, String idCard) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "APPCODE " + apiCode);
        MultiValueMap<String, String> bodys = new LinkedMultiValueMap<>();
        bodys.add("idNo", idCard);
        bodys.add("name", realName);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(bodys, headers);
        try {
            ResponseEntity<String> responseEntity = SendHttpRequestUtils.SendPostRequest(apiHost + apiPath, request, String.class);
            IdCardCheckVO idCardCheckVO = JSONUtils.jsonToObj(responseEntity.getBody(), IdCardCheckVO.class);
            return idCardCheckVO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void signUp(SignUpDTO signUpDTO)throws Exception {
        if (signUpDTO==null){
            return;
        }
        String account = signUpDTO.getAccount();
        if (account==null||account.length()<5||account.length()>50){
            throw new ServiceException("账号长度必须大于5小于50");
        }
        User userAccount = userMapper.selectByAccount(account);
        if (userAccount!=null){
            throw new ServiceException("账号已经存在");
        }
        String password = signUpDTO.getPassword();
        if (password==null||password.length()<5||password.length()>50){
            throw new ServiceException("密码长度必须大于5小于50");
        }
        String gender = signUpDTO.getGender();
        if (gender!=null){
            if (gender.length()>3){
                throw new ServiceException("性别长度必须小于等于3");
            }
        }
        String md5 = MD5Utils.MD5Encode(password, null);
        signUpDTO.setPassword(md5);
        String nickname = signUpDTO.getNickname();
        if (nickname==null){
            return;
        }
        User userNickName = userMapper.selectByNickName(nickname);
        if (userNickName!=null){
            throw new ServiceException("昵称已经存在");
        }
        User user=new User();
        BeanUtils.copyProperties(signUpDTO,user);
        userMapper.insert(user);
    }

    @Override
    public String signIn(SignInDTO signInDTO) {
        if (signInDTO==null){
            return null;
        }
        if (signInDTO.getAccount()==null||signInDTO.getPassword()==null){
            return null;
        }
        String s = MD5Utils.MD5Encode(signInDTO.getPassword(), null);
        User user = userMapper.selectByAccountAndPassword(signInDTO.getAccount(), s);
        if (user==null){
            return null;
        }
        MemberInfo memberInfo=new MemberInfo();
        BeanUtils.copyProperties(user,memberInfo);
        return MemberUtils.setMemberInfo(memberInfo);
    }

    @Override
    public void updateUser(UpdateUserDTO updateUserDTO) {
        if (updateUserDTO==null||updateUserDTO.getPassword()==null||updateUserDTO.getPassword().isEmpty()){
            return;
        }
        User user = userMapper.selectByPrimaryKey(MemberUtils.getMemberInfo().getId());
        updateUserDTO.setPassword(MD5Utils.MD5Encode(updateUserDTO.getPassword(),""));
        BeanUtils.copyProperties(updateUserDTO,user);
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void addCourtyard(AddCourtyardDTO addCourtyardDTO)throws Exception {
        Date date = new Date(System.currentTimeMillis());
        if (ProUtil.isEmpty(addCourtyardDTO)){
            return;
        }
        Courtyard courtyard = courtyardMapper.selectByContent(addCourtyardDTO.getContent());
        if (courtyard!=null){
            throw new ServiceException("该院已存在");
        }
        courtyard=new Courtyard();
        BeanUtils.copyProperties(addCourtyardDTO,courtyard);
        courtyard.setState(0);
        courtyard.setCreateTime(date);
        courtyardMapper.insert(courtyard);
    }

    @Override
    public void setCourtyardAdmin(SetCourtyardAdminDTO setCourtyardAdminDTO)throws Exception {
        if (ProUtil.isEmpty(setCourtyardAdminDTO)){
            return;
        }
        Courtyard courtyard = courtyardMapper.selectByPrimaryKey(setCourtyardAdminDTO.getCourtyardId());
        if (courtyard==null){
            throw new ServiceException("院不存在");
        }
        User user = userMapper.selectByPrimaryKey(setCourtyardAdminDTO.getUserId());
        if (user==null){
            throw new ServiceException("用户不存在");
        }
        courtyard.setAdminId(user.getId());
        courtyardMapper.updateByPrimaryKey(courtyard);
    }


    @Override
    public List<CourtyardListVO> courtyardList() {
        return courtyardMapper.courtyardList();
    }

    @Override
    public List<UserListVO> userList(String nickName,Integer id) {
        return userMapper.userList(nickName,id);
    }

    @Override
    public void addCourtyardUser(AddCourtyardUserDTO addCourtyardUserDTO) {
        Date date = new Date(System.currentTimeMillis());
        if (ProUtil.isEmpty(addCourtyardUserDTO)){
            throw new ServiceException("sbhjj");
        }
        Courtyard courtyard = courtyardMapper.selectByPrimaryKey(addCourtyardUserDTO.getCourtyardId());
        if (courtyard==null){
            throw new ServiceException("院不存在");
        }
        List<CourtyardUserDTO> list = addCourtyardUserDTO.getList();
        if(list.isEmpty()){
            return;
        }
        for (CourtyardUserDTO dto:list){
            CourtyardUser courtyardUser=new CourtyardUser();
            BeanUtils.copyProperties(dto,courtyardUser);
            courtyardUser.setCourtyardId(courtyard.getId());
            courtyardUser.setCreateTime(date);
            courtyardUser.setType(0);
            courtyardUserMapper.insert(courtyardUser);
        }
    }

    @Override
    public void addPosition(AddPositionListDTO addpositionListDTO) {
        if (ProUtil.isEmpty(addpositionListDTO)){
            throw new ServiceException("sbhjj");
        }
        List<AddPositionDTO> list = addpositionListDTO.getList();
        if (list.isEmpty()){
            throw new ServiceException("sbhjj");
        }
        for (AddPositionDTO dto:list){
            if (ProUtil.isEmpty(dto)){
                continue;
            }
            Position position = positionMapper.selectByPrimaryKey(dto.getPositionId());
            if (position==null){
                continue;
            }
            UserPosition userPosition = userPositionMapper.selectByUserPosition(MemberUtils.getMemberInfo().getId(), dto.getPositionId());
            if (userPosition!=null){
                continue;
            }
            userPosition=new UserPosition();
            userPosition.setPosition(dto.getPositionId());
            userPosition.setUser(MemberUtils.getMemberInfo().getId());
            userPositionMapper.insert(userPosition);
        }
    }

    @Override
    public List<PositionListVO> userPositionList(Integer userId) {
        if (userId==null){
            throw new ServiceException("sbhjj");
        }
        return userPositionMapper.userPositionList(userId);
    }

    @Override
    public boolean isAdmin(Integer userId) {
        if (userId==null){
            return false;
        }
        int i = courtyardMapper.selectByAdmin(userId);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }
}
