package com.yjxxt.am.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.am.base.BaseService;
import com.yjxxt.am.bean.User;
import com.yjxxt.am.bean.UserRole;
import com.yjxxt.am.mapper.UserMapper;
import com.yjxxt.am.mapper.UserRoleMapper;
import com.yjxxt.am.model.UserModel;
import com.yjxxt.am.query.UserQuery;
import com.yjxxt.am.utils.AssertUtil;
import com.yjxxt.am.utils.Md5Util;
import com.yjxxt.am.utils.PhoneUtil;
import com.yjxxt.am.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService extends BaseService<User,Integer> {
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private UserRoleMapper userRoleMapper;


    /**
     * 用户登录
     */
    public UserModel userLogin(String userName, String userPwd) {
        // 1. 验证参数
        checkLoginParams(userName, userPwd);
        // 2. 根据用户名，查询用户对象
        User user = userMapper.queryUserByUserName(userName);
        // 3. 判断用户是否存在 (用户对象为空，记录不存在，方法结束)
        AssertUtil.isTrue(null == user, "用户不存在或已注销！");

        // 4. 判断密码是否正确（用户存在，校验密码。密码不正确，方法结束）
        checkLoginPwd(userPwd, user.getUserPwd());
        // 5. 密码正确（用户登录成功，返回用户的相关信息）
        return buildUserInfo(user);
    }
    /**
     * 用户登录--验证参数
     */
    private void checkLoginParams(String userName, String userPwd) {
        // 判断姓名
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户姓名不能为空！");
        // 判断密码
        AssertUtil.isTrue(StringUtils.isBlank(userPwd), "用户密码不能为空！");
    }
    /**
     * 用户登录--构建返回的用户信息
     * @param user
     * @return
     */
    private UserModel buildUserInfo(User user) {
        UserModel userModel = new UserModel();// 设置用户信息
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        userModel.setUserName(user.getUserName());
        userModel.setUserPwd(user.getUserPwd());
        return userModel;
    }
    /**
     * 用户登录--验证登录密码
     * @param userPwd 前台传递的密码
     * @param upwd 数据库中查询到的密码
     *    @return
     */
    private void checkLoginPwd(String userPwd, String upwd) {
        // 数据库中的密码是经过加密的，将前台传递的密码先加密，再与数据库中的密码作比较
        //userPwd = Md5Util.encode(userPwd);
        userPwd = userPwd;
        // 比较密码
        AssertUtil.isTrue(!userPwd.equals(upwd), "用户密码不正确！");
    }


    /**
     * 主页面右上角下拉框--密码修改
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @param confirmPwd
     */
    public void changeUserPwd(int userId, String oldPassword, String newPassword, String confirmPwd) {
        //用户登录了修改，userId
        User user = userMapper.selectByPrimaryKey(userId);
        //密码验证
        checkPasswordParams(user, oldPassword, newPassword, confirmPwd);
        //修改密码
        user.setUserPwd(newPassword);
        //确认密码修改是否成功
        AssertUtil.isTrue(userMapper.updateByPrimaryKey(user) < 1, "修改失败了");
    }
    /**
     * 密码修改--校验密码
     * @param user
     * @param oldPassword
     * @param newPassword
     * @param confirmPwd
     */
    private void checkPasswordParams(User user, String oldPassword, String newPassword, String confirmPwd) {
        AssertUtil.isTrue(user == null, "用户未登录或者不存在");
        //原始密码非空
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword), "请输入原始密码");
        //原始密码是否正确
        AssertUtil.isTrue(!(user.getUserPwd().equals(oldPassword)), "原始密码不正确");
        //新密码非空
        AssertUtil.isTrue(StringUtils.isBlank(newPassword), "新密码不能为空");
        //新密码不能和原始密码一致
        AssertUtil.isTrue(newPassword.equals(oldPassword), "新密码和原始密码不能相同");
        //确认密码非空
        AssertUtil.isTrue(StringUtils.isBlank(confirmPwd), "确认密码不能为空");
        //确认密码和新密码一致
        AssertUtil.isTrue(!confirmPwd.equals(newPassword), "确认密码和新密码要一致");
    }

    /**
     * 用户管理页面--查询数据
     * @param userQuery
     * @return
     */
    public Map<String, Object> findUserByParams(UserQuery userQuery) {
        //实例化map
        Map<String, Object> map = new HashMap<String, Object>();
        //初始化分页单位
        PageHelper.startPage(userQuery.getPage(), userQuery.getLimit());
        //开始分页
        PageInfo<User> plist = new PageInfo<User>(selectByParams(userQuery));

        //准备数据
        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", plist.getTotal());
        map.put("data", plist.getList());
        //返回目标map
        return map;
    }

    /**
     * 用户管理页面--删除用户
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeUserIds(Integer[] ids){
        //验证
        AssertUtil.isTrue(ids==null|| ids.length==0,"请选择删除数据");
        //遍历对象
        for (Integer userId: ids) {
            //统计当前用户有多少个角色
            int count=userRoleMapper.countUserRoleNum(userId);
            //删除当前用户的角色
            if(count>0){
                AssertUtil.isTrue( userRoleMapper.deleteUserRoleByUserId(userId)!=count,"用户角色删除失败");
            }
        }
        //判断删除成功与否
        AssertUtil.isTrue(userMapper.deleteBatch(ids)<1,"删除失败了");
    }

    /**
     * 用户管理页面--添加用户
     *
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user) {
        //验证
        checkUser(user.getUserName(), user.getEmail(), user.getPhone());
        //用户名唯一
        User temp = userMapper.selectUserByName(user.getUserName());
        AssertUtil.isTrue(temp != null, "用户名已经存在");
        //设定默认
        user.setIsValid(1);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        //密码加密
        user.setUserPwd(Md5Util.encode("123456"));
        //验证是否成功
        AssertUtil.isTrue(insertHasKey(user) < 1, "添加失败了");
        System.out.println(user.getId()+"<<<"+user.getRoleIds());
        /*//分配角色
        relaionUserRole(user.getId(),user.getRoleIds());*/
    }

    /**用户管理页面--添加用户--数据校验
     * @param userName
     * @param email
     * @param phone
     */
    private void checkUser(String userName, String email, String phone) {
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(email), "邮箱不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(phone), "手机号不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone), "输入合法的手机号");
    }

    /**
     * 操作中间表
     * 用户管理页面--添加用户--分配角色
     * 用户管理页面--修改用户--分配角色
     * @param userId
     * @param roleIds
     */
    private void relaionUserRole(Integer userId, String roleIds) {
        //准备集合存储对象
        List<UserRole> urlist=new ArrayList<UserRole>();
        //userId,roleId;
        AssertUtil.isTrue(StringUtils.isBlank(roleIds),"请选择角色信息");
        //统计当前用户有多少个角色
        int count=userRoleMapper.countUserRoleNum(userId);
        //删除当前用户的角色
        if(count>0){
            AssertUtil.isTrue( userRoleMapper.deleteUserRoleByUserId(userId)!=count,"用户角色删除失败");
        }

        //批量添加角色
        String[] RoleStrId = roleIds.split(",");
        //遍历
        for (String rid:RoleStrId) {
            //准备对象
            UserRole userRole=new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(Integer.parseInt(rid));
            userRole.setCreateDate(new Date());
            userRole.setUpdateDate(new Date());
            //存放到集合
            urlist.add(userRole);
        }
        //批量添加
        AssertUtil.isTrue(userRoleMapper.insertBatch(urlist)!=urlist.size(),"用户角色分配失败");
    }


    /**
     * 用户管理页面--修改用户
     *
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void changeUser(User user) {
        //根据ID获取用户的信息
        User temp = userMapper.selectByPrimaryKey(user.getId());
        //判断
        AssertUtil.isTrue(temp == null, "待修改的记录不存在");
        //验证参数
        checkUser(user.getUserName(), user.getEmail(), user.getPhone());
        //修改用户名已经存在问题
        User temp2 = userMapper.selectUserByName(user.getUserName());
        AssertUtil.isTrue(temp2 != null && !(temp2.getId().equals(user.getId())), "用户名称已经存在");
        //设定默认值
        user.setUpdateDate(new Date());
        //判断修改是否成功
        AssertUtil.isTrue(updateByPrimaryKeySelective(user) < 1, "修改失败了");
        //为用户分配角色
        relaionUserRole(user.getId(),user.getRoleIds());
    }
}
