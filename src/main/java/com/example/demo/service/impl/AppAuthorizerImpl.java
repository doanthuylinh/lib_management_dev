/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service.impl;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ResolvableType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.AppAuthorizer;

/**
 * [OVERVIEW] AppAuthorizerImpl.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/08      LinhDT       	  Create new
*/
@Service("appAuthorizer")
public class AppAuthorizerImpl implements AppAuthorizer {

    private static final Logger LOGGER = LogManager.getLogger(AppAuthorizerImpl.class);

    @Override
    public boolean authorize(Authentication authentication, String action, Object callerObj) {
        LOGGER.info("----------authorize START----------");
        String securedPath = extractSecuredPath(callerObj);
        if (securedPath == null || "".equals(securedPath.trim())) {//login, logout
            return true;
        }
        String menuCode = securedPath.substring(1);//Bỏ dấu "/" ở đầu Path
        boolean isAllow = true;
        try {
//            UsernamePasswordAuthenticationToken user = (UsernamePasswordAuthenticationToken) authentication;
//            if (user == null) {
//                return isAllow;
//            }
//            String userId = (String) user.getPrincipal();
//            if (userId == null || "".equals(userId.trim())) {
//                return isAllow;
//            }
//            //Truy vấn vào CSDL theo userId + menuCode + action
//            //Nếu có quyền thì
//            {
//                isAllow = true;
//            }
        } catch (Exception e) {
            LOGGER.error(e.toString(), e);
            throw e;
        }
        LOGGER.info("----------authorize END----------");
        return isAllow;
    }

    // Lay ra securedPath duoc Annotate RequestMapping trong Controller
    private String extractSecuredPath(Object callerObj) {
        LOGGER.info("----------extractSecuredPath START----------");
        Class<?> clazz = ResolvableType.forClass(callerObj.getClass()).getRawClass();
        Optional<Annotation> annotation = Arrays.asList(clazz.getAnnotations()).stream().filter((ann) -> {
            return ann instanceof RequestMapping;
        }).findFirst();
        LOGGER.debug("FOUND CALLER CLASS: {}", ResolvableType.forClass(callerObj.getClass()).getType().getTypeName());
        if (annotation.isPresent()) {
            return ((RequestMapping) annotation.get()).value()[0];
        }
        LOGGER.info("----------extractSecuredPath END----------");
        return null;
    }
}
