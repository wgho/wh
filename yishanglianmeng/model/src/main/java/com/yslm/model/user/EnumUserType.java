package com.yslm.model.user;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.EnumSet;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import com.yslm.util.enumutil.EnumUtil;



/**
 *
 */
public class EnumUserType<T extends Enum<T>> implements UserType {

    private static final int[] SQL_TYPES = { Types.INTEGER };
    private Class<T> type;

    public EnumUserType(Class<T> type) {
        this.type = type;
    }

    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    @Override
    public Class<T> returnedClass() {
        return type;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        if (o != null && o1 != null) {
            return o.equals(o1);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return o;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return (Serializable) o;
    }

    @Override
    public Object assemble(Serializable srlzbl, Object o) throws HibernateException {
        return srlzbl;
    }

    @Override
    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        return o;
    }

    @SuppressWarnings("unused")
    @Override
    public Object nullSafeGet(ResultSet rs, String[] strings, SessionImplementor arg2, Object arg3)
            throws HibernateException, SQLException {
        Integer value = rs.getInt(strings[0]);

        if (value == null) {
            return EnumSet.noneOf(type);
        } else {
            return EnumUtil.decode(type, value);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void nullSafeSet(PreparedStatement ps, Object o, int i, SessionImplementor arg3)
            throws HibernateException, SQLException {
        EnumSet<UserRole> roles = (EnumSet<UserRole>) o;
        ps.setInt(i, EnumUtil.encode(roles));
        
    }
}
