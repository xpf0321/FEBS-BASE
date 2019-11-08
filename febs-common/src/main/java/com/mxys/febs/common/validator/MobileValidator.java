package com.mxys.febs.common.validator;

import com.mxys.febs.common.annotation.IsMobile;
import com.mxys.febs.common.entity.RegexpConstant;
import com.mxys.febs.common.utils.FebsUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileValidator implements ConstraintValidator<IsMobile,String> {
    @Override
    public void initialize(IsMobile constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.isBlank(s)) {
                return true;
            } else {
                String regex = RegexpConstant.MOBILE_REG;
                return FebsUtil.match(regex, s);
            }
        } catch (Exception e) {
            return false;
        }
    }
}
