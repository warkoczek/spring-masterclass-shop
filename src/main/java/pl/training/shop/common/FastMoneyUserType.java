package pl.training.shop.common;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;
import org.javamoney.moneta.FastMoney;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FastMoneyUserType implements CompositeUserType {

    public String[] getPropertyNames() {
        return new String[]{"currency", "amount"};
    }

    public Type[] getPropertyTypes() {
        return new Type[]{StringType.INSTANCE, LongType.INSTANCE};
    }

    @Override
    public Object getPropertyValue(Object component, int propertyIndex) throws HibernateException {
        if(component == null){
            return null;
        }
        FastMoney fastMoney = (FastMoney) component;
        switch (propertyIndex){
            case 0:
                return fastMoney.getCurrency().getCurrencyCode();
            case 1:
                return fastMoney.getNumber().numberValue(Long.class);
            default:
                throw new HibernateException("Invalid property index [" + propertyIndex + " ]");
        }
    }

    @Override
    public void setPropertyValue(Object component, int propertyIndex, Object value) throws HibernateException {
        if(component == null){
            return;
        }
        throw new HibernateException("Called setPropertyValue on an immutable type {" + component.getClass() +"}");
    }

    @Override
    public Class returnedClass() {
        return FastMoney.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return false;
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return 0;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
        assert names.length == 2;
        FastMoney money = null;
        String currency = rs.getString(names[0]);
        if(!rs.wasNull()){
            Long amount = rs.getLong(names[1]);
            money = FastMoney.of(amount, currency);
        }
        return money;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int property, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        if(null == value){
            st.setNull(property, StringType.INSTANCE.sqlType());
            st.setNull(property + 1, StringType.INSTANCE.sqlType());
        }else{
            FastMoney amount = (FastMoney) value;
            st.setString(property, amount.getCurrency().getCurrencyCode());
            st.setLong(property + 1, amount.getNumber().numberValue(Long.class));
        }
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return null;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value, SharedSessionContractImplementor session) throws HibernateException {
        return null;
    }

    @Override
    public Object assemble(Serializable cached, SharedSessionContractImplementor session, Object owner) throws HibernateException {
        return null;
    }

    @Override
    public Object replace(Object original, Object target, SharedSessionContractImplementor session, Object owner) throws HibernateException {
        return null;
    }
}
