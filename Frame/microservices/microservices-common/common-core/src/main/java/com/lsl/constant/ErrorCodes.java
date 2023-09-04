package com.lsl.constant;

/**
 * 错误编码
 *
 * @author lengleng
 * @date 2022/3/30
 */
public interface ErrorCodes {

    /**
     * 系统编码错误
     */
    String PARAM_CONFIG_ERROR = "param.config.error";

    /**
     * 系统内置参数不能删除
     */
    String PARAM_DELETE_SYSTEM = "param.delete.system";

    /**
     * 用户已存在
     */
    String USER_EXISTING = "user.existing";

    /**
     * 用户名已存在
     */
    String USER_USERNAME_EXISTING = "user.username.existing";

    /**
     * 用户原密码错误，修改失败
     */
    String USER_UPDATE_PASSWORDERROR = "user.update.passwordError";

    /**
     * 用户信息为空
     */
    String USER_USERINFO_EMPTY = "user.userInfo.empty";

    /**
     * 获取当前用户信息失败
     */
    String USER_QUERY_ERROR = "user.query.error";

    /**
     * 部门名称不存在
     */
    String DEPT_DEPTNAME_INEXISTENCE = "dept.deptName.inexistence";

    /**
     * 角色名称不存在
     */
    String ROLE_ROLENAME_INEXISTENCE = "role.roleName.inexistence";

    /**
     * 角色名或角色编码已经存在
     */
    String ROLE_NAMEORCODE_EXISTING = "role.nameOrCode.existing";

    /**
     * 菜单存在下级节点 删除失败
     */
    String SYS_MENU_DELETE_EXISTING = "menu.delete.existing";

    /**
     * 手机号未注册
     */
    String SYS_APP_PHONE_UNREGISTERED = "phone.unregistered";

}
