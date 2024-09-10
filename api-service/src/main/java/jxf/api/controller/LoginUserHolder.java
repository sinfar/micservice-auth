package jxf.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jxf.api.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 获取登录用户信息
 * Created by macro on 2020/6/17.
 */
@Component
public class LoginUserHolder {

    @Autowired
    private ObjectMapper objectMapper;

    public UserDTO getCurrentUser(){
        //从Header中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader("user");

        JsonNode userJsonNode = null;
        try {
            userJsonNode = objectMapper.readTree(userStr);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(userJsonNode.get("user_name").asText());
        userDTO.setId(userJsonNode.get("id").asLong());

        // Assuming 'authorities' is an array of strings in JSON
        userDTO.setRoles(objectMapper.convertValue(
                userJsonNode.get("authorities"),
                new TypeReference<List<String>>() {}
        ));

        return userDTO;
    }
}
