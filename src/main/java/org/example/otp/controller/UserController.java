package org.example.otp.controller;

import org.example.otp.controller.dto.OtpRequest;
import org.example.otp.controller.dto.OtpValidationRequest;
import org.example.otp.service.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final OtpService otpService;

    @PostMapping("/generate-otp")
    public String generateOtp(@RequestBody OtpRequest request) {
        log.info("Запрос на генерацию OTP: operationId={}", request);
        String otp = otpService.initiateOtpSequence(request);
        log.info("OTP успешно сгенерирован для operationId={}", request);
        return otp;
    }

    @PostMapping("/validate-otp")
    public boolean validateOtp(@RequestBody OtpValidationRequest request) {
        log.info("Запрос на валидацию OTP: operationId={}", request);
        boolean isValid = otpService.verifyOtp(request);
        if (isValid) {
            log.info("OTP успешно верифицирован: operationId={}", request);
        } else {
            log.warn("Ошибка верификации OTP: operationId={}", request);
        }
        return isValid;
    }
}