package org.springcat.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springcat.dao.UserDao;
import org.springcat.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {


    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ApiResponses(value = {

            @ApiResponse(code =  200, message =  "更新成功"),

            @ApiResponse (code =  404, message =  "Not Found"),

            @ApiResponse (code =  500, message =  "内部报错")}

    )

    @ApiImplicitParams(
            {
                @ApiImplicitParam(name="sort"),
                @ApiImplicitParam(name="page",defaultValue="0"),
                @ApiImplicitParam(name="size",defaultValue="5")
            }
    )
    public List<User> get(@PageableDefault Pageable pageable) {
        Page<User> userPage = userDao.findAll(pageable);
        List<User> users = userPage.getContent();
      return users;
    }

    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public Long add(@RequestBody @Valid UserVO userVO) {
        User user = UserVO.toUser(userVO);
        user.setCreateTime(System.currentTimeMillis());
        userDao.save(user);
        return user.getId();
    }


    @RequestMapping(value = "/",method = RequestMethod.POST)
    public User put(String key,String value){
        return userDao.findOne(11L);
    }
}