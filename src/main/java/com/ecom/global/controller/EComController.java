package com.ecom.global.controller;

import com.ecom.global.bo.UserBOImpl;
import com.ecom.global.vo.WebRequest;
import com.ecom.global.vo.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Slf4j
public class EComController {

    @Autowired
    private UserBOImpl userBO;

    @PostMapping("/register")
    public WebResponse saveUser(@RequestBody WebRequest webRequest) {
        WebResponse webResponse = new WebResponse();
        try {
            webResponse = userBO.saveUser(webRequest);
        }catch (Exception ex){
            log.error("Exception in saving user - "+ex);
        }
        return webResponse;
    }

    @GetMapping("/checkUser")
    public List<WebResponse> checkUser() {
        List<WebResponse> webResponse = new ArrayList<>();
        try {
            webResponse = userBO.listUsers();
        }catch (Exception ex){
            log.error("Exception in saving user - "+ex);
        }
        return webResponse;
    }

    @PostMapping("/login")
    public WebResponse checkLogin(@RequestBody WebRequest request){
        WebResponse webResponse = new WebResponse();
        try {
            webResponse = userBO.checkLogin(request);
        }catch (Exception ex){
            log.error("Exception in checking login - "+ex);
        }
        return webResponse;
    }

}
