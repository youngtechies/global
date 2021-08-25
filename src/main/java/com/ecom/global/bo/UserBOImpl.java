package com.ecom.global.bo;

import com.ecom.global.entity.UserEntity;
import com.ecom.global.repository.UserRepository;
import com.ecom.global.vo.WebRequest;
import com.ecom.global.vo.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserBOImpl {

    @Autowired
    private UserRepository userRepository;

    public WebResponse saveUser(WebRequest webRequest) {
        WebResponse webResponse = new WebResponse();
        UserEntity savedUser = new UserEntity();
        UserEntity userEntity = new UserEntity();
        if (webRequest != null && webRequest.getName() != null && webRequest.getEmail() != null && webRequest.getPassword() != null) {
            userEntity.setName(webRequest.getName());
            userEntity.setEmail(webRequest.getEmail());
            userEntity.setPassword(webRequest.getPassword());
        }
        if(checkUser(userEntity.getEmail(), userEntity.getPassword()) != null){
            return webResponse;
        }
        savedUser = userRepository.save(userEntity);
        if (savedUser != null) {
            webResponse.setEmail(savedUser.getEmail());
            webResponse.setName(savedUser.getName());
        }
        return webResponse;
    }

    public List<WebResponse> listUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        List<WebResponse> webResponseList = new ArrayList<>();
        userEntityList.stream().forEach( element -> {
            WebResponse webResponse = new WebResponse();
            webResponse.setName(element.getName());
            webResponse.setEmail(element.getEmail());
            webResponseList.add(webResponse);
        });
        return webResponseList;
    }

    private UserEntity checkUser(String email, String password) {
        UserEntity userEntity = userRepository.findByEmailAndPassword(email, password);
        return (userEntity != null) ? userEntity : null;
    }

    public WebResponse checkLogin(WebRequest request) {
        WebResponse webResponse = new WebResponse();
        UserEntity userEntity = checkUser(request.getEmail(), request.getPassword());
        Optional.ofNullable(userEntity).ifPresent( element -> {
            webResponse.setEmail(element.getEmail());
            webResponse.setName(element.getName());
        });
        return webResponse;
    }
}
