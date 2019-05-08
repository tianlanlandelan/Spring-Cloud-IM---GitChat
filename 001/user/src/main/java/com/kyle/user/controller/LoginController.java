package com.kyle.user.controller;

import com.kyle.mycommon.response.MyResponse;
import com.kyle.mycommon.router.RouterAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yangkaile
 * @date 2019-04-18 19:53:43
 */
@RestController
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RouterAttribute(id=1,name = "",description = "")
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    ResponseEntity login(HttpServletRequest request){
        logger.info("Receive Request: url:"+ request.getRequestURI()
                + " addr:" + request.getRemoteAddr() + " port:" + request.getRemotePort()
                + " host:" + request.getRemoteHost());
        return MyResponse.ok();
    }
}
