package com.pododoserver.common.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Around("(execution(* com.pododoserver..controller..*Controller.*(..))"
            + " || execution(* com.pododoserver..service..*Service.*(..))"
            + " || execution(* com.pododoserver..repository..*Repository.*(..)))"
            + " && !execution(* com.pododoserver..service..JwtService.*(..))"
            + " && !execution(* com.pododoserver..*.*.get*())" // get 시작하는 모든 메서드 중 파라미터 없는 메서드 제외 -> @Getter
    )
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {

        String type;
        String requestUrl = "";
        boolean isReqLogging = false;
        boolean isController = false;
        String classNm = pjp.getSignature().getDeclaringTypeName();
        String methodNm = pjp.getSignature().getName();

        if (classNm.contains("Controller")) {
            type = " Controller - ";
            requestUrl = httpServletRequest.getMethod() + " " + httpServletRequest.getRequestURI();
            isController = true;
        } else if (classNm.contains("Service")) {
            type = " Service - ";
        } else if (classNm.contains("Repository")) {
            type = " Repository - ";
            isReqLogging = true;
        } else {
            type = "Etc - ";
        }


        if (isController) {
            logger.info("{}[START] : {} - '{}.{}()'", type, requestUrl, classNm, methodNm);
        } else {
            logger.info("{}[START] : '{}.{}()'", type, classNm, methodNm);
        }

        if (isReqLogging) {
            Object[] args = pjp.getArgs();
            List<Object> objList = new ArrayList<>();
            for (Object arg : args) {
                if (!(arg instanceof WebRequest)
                        && !(arg instanceof HttpSession)
                        && !(arg instanceof HttpRequest)
                        && !(arg instanceof HttpResponse)
                        && !(arg instanceof ServletRequest)
                        && !(arg instanceof ServletResponse)) {
                    objList.add(arg);
                }
            }
            logger.info("{}[ REQ ] : '{}()', Params={}", type, methodNm, getJsonStrPretty(objList));
        }

        Object result = pjp.proceed();
        logger.info("{}[ END ] : '{}.{}()'", type, classNm, methodNm);

        return result;
    }

    public static String getJsonStrPretty(Object object) {
        if (object == null) return null;

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception e) {
            logger.error("=========> LoggingAspect : getJsonStrPretty method error : ", e);
            return "error";
        }
    }
}

