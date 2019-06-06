package com.blog.main.controller;

import com.blog.main.model.User;
import com.blog.main.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/app")
public class Login {

    @Autowired
    private UserService userService;

    protected final Logger logger = LoggerFactory.getLogger(Login.class);

    protected ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ObjectNode login(String userName, String password) {
        ObjectNode response = objectMapper.createObjectNode();
        try {
            logger.info("userName is {}", userName, password);
            response.put("status", 200);
            User user = userService.login(userName, password);
            logger.info("user if [{}]", user.toString());
            response.putPOJO("user", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
