package com.alex.util;

import com.alex.model.User;
import com.alex.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alexandr Chesnokov
 * @project CarShowRoomApp
 */

@Component
public class UserInfo {

    final
    JwtTokenProvider jwtTokenProvider;

    public UserInfo(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String getUserEmail() {
       User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getEmail();
    }

    public String getJwtUserEmail(HttpServletRequest req) {
        return jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req));
    }


}
