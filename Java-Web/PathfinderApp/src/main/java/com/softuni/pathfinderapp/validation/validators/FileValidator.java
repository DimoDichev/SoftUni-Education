package com.softuni.pathfinderapp.validation.validators;

import com.softuni.pathfinderapp.validation.annotation.FileAnnotation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileValidator implements ConstraintValidator<FileAnnotation, MultipartFile> {

    private List<String> contentTypes;

    private long size;

    @Override
    public void initialize(FileAnnotation constraintAnnotation) {
        this.size = constraintAnnotation.size();
        this.contentTypes = Arrays.stream(constraintAnnotation.contentTypes()).collect(Collectors.toList());
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        String errorMsg = getErrorMsf(file);

        if (!errorMsg.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(errorMsg)
                    .addConstraintViolation();

            return false;
        }

        return true;
    }

    private String getErrorMsf(MultipartFile file) {
        if (file.isEmpty()) {
            return "File must be provided!";
        }

        if (file.getSize() > size) {
            return "Exceeded file size. Max size: " + size;
        }

        if (!contentTypes.contains(file.getContentType())) {
            return "Invalid file extension. Supported files: " + String.join(", ", contentTypes);
        }

        return "";
    }
}
