package com.yslm.model.user;



/**
 * 用户角色集合的配置类，用于hibernate映射
 */
public class UserRoleEnumUserType extends EnumUserType<UserRole> {

    public UserRoleEnumUserType() {
        super(UserRole.class);
    }
}
