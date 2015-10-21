package com.hallocasa.helpers;

import com.hallocasa.commons.vo.CountryVO;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.commons.vo.helpers.GenericVOEntityConverter;
import com.hallocasa.commons.vo.helpers.GenericVOParser;
import com.hallocasa.commons.vo.interfaces.HallocasaEntity;
import com.hallocasa.commons.vo.interfaces.ValueObject;
import com.hallocasa.dataentities.app.Country;
import com.hallocasa.dataentities.app.User;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.BooleanConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.LongConverter;

public class HallocasaVOParser<T extends HallocasaEntity, U extends ValueObject>
        extends GenericVOParser<T, U> {

    // TODO: Check if this could be static or not. Worry about thread-safe
    private static final BeanUtilsBean beanUtilsBean;
    private static final ConvertUtilsBean convertUtilsBean;

    /* static fields */
    static {
        convertUtilsBean = new ConvertUtilsBean();

        // account - account VO converter
        registerConverter(User.class, UserVO.class, UserVOParser.class);
        registerConverter(Country.class, CountryVO.class, CountryVOParser.class);

        // Date (use null as null)
        DateConverter dateConverter = new DateConverter(null);
        convertUtilsBean.register(dateConverter, Date.class);
        // Long (use null as null, not zero)
        LongConverter converter = new LongConverter(null);
        convertUtilsBean.register(converter, Long.class);
        // Boolean (use null as null, not false)
        BooleanConverter booleanConverter = new BooleanConverter(null);
        convertUtilsBean.register(booleanConverter, Boolean.class);

        // creates the bean utils bean
        beanUtilsBean = new BeanUtilsBean(convertUtilsBean);
    }

    /**
     * @param <T>
     * @param <U>
     * @param <R>
     * @param entityClass
     * @param voClass
     * @param parserClass
     */
    private static <T extends HallocasaEntity, U extends ValueObject, R extends GenericVOParser<T, U>> void registerConverter(
            Class<T> entityClass, Class<U> voClass, Class<R> parserClass) {
        GenericVOEntityConverter<T, U, R> converter = new GenericVOEntityConverter<>(
                parserClass, entityClass, voClass);
        convertUtilsBean.register(converter, entityClass);
        convertUtilsBean.register(converter, voClass);
    }

    /**
     * Constructor
     */
    public HallocasaVOParser() {
    }

    /* static fields */

    /* instance variables */

    /* constructors */

    /* Methods */

    /* Getters & Setters */
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.mobiera.social.commons.vo.helpers.GenericVOParser#getBeanUtilsBean()
     */
    @Override
    public BeanUtilsBean getBeanUtilsBean() {
        return beanUtilsBean;
    }
}
