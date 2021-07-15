package com.example.demo.validator;

import com.alibaba.druid.util.StringUtils;
import com.example.demo.util.ValidatorUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileValidator implements ConstraintValidator<IsMobile,String> {

    private boolean require = false;

    @Override
    public void initialize(IsMobile isMobile) {
        require = isMobile.required() ;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(require){
            return ValidatorUtil.isMobile(s) ;
        }else{
            if(StringUtils.isEmpty(s)){
                return true ;
            }else {
                return ValidatorUtil.isMobile(s) ;
            }
        }
    }


}
