package com.smallstudy.validator;

import com.smallstudy.dto.CategoryItemDTO;
import com.smallstudy.dto.ProfileDTO;
import com.smallstudy.dto.RegionDTO;
import com.smallstudy.dto.SignupDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProfileValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProfileDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ProfileDTO dto = (ProfileDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nickname", "required");
        if(errors.hasErrors())
            return;

        int one = dto.getNickname().length();
        if(one < 4 || one > 12)
            errors.rejectValue("nickname", "invalid");

        int two = dto.getMessage().length();
        if(two > 50)
            errors.rejectValue("message", "invalid");

    }
}
