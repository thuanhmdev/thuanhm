package com.domain.blog.config;


import com.domain.blog.entity.Permission;
import com.domain.blog.service.JwtService;
import com.domain.blog.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import java.nio.file.AccessDeniedException;
import java.util.List;

public class
PermissionInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;


    @Override
    @Transactional
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        String path = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        String requestURI = request.getRequestURI();
        String httpMethod = request.getMethod();

        System.out.println(">> path= " + path);
        System.out.println(">> requestURI= " + requestURI);
        System.out.println(">> httpMethod= " + httpMethod);

        String username = jwtService.getCurrentUserLogin().isPresent() ? jwtService.getCurrentUserLogin().get() : "";
        if(username.isEmpty() || username.equals("anonymousUser")){
            throw new AccessDeniedException("Bạn không có quyền thực hiện chức năng này");
        }

        List<Permission> permissions = userService.getPermissionFromUserRole(username);

        boolean isAllow = permissions.stream().anyMatch(item -> item.getMethod().equals(httpMethod) && item.getApiPath().equals(path));
        if(!isAllow){
            throw new AccessDeniedException("Bạn không có quyền thực hiện chức năng này");
        }
        return true;
    }
}
