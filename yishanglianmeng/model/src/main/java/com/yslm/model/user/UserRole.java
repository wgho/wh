package com.yslm.model.user;

/**
 * 用户角色
 *
 */
public enum UserRole {
	
	NONMEMBER {
        @Override
        public String toString() {
            return "非会员";
        }
    },
	MEMBER {
        @Override
        public String toString() {
            return "会员";
        }
    }
}
