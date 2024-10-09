package com.smallstudy.validator;

import org.springframework.validation.Errors;

import java.util.regex.Pattern;

public class GlobalValidationService {

    private GlobalValidationService() {}

    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,16}$");

    public static boolean emailValidate(String email) {
        return GlobalValidationService.EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean passwordValidate(String password) {
        return GlobalValidationService.PASSWORD_PATTERN.matcher(password).matches();
    }


}
