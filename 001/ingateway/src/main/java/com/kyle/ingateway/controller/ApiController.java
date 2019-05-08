package com.kyle.ingateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/application")
/**
 * @author yangkaile
 * @date 2019-05-08 16:25:17
 * 用于测试的ApiController
 */
public class ApiController {
    /**
     * 导入DiscoveryClient用于发现Consul注册的服务
     */
    @Autowired
    DiscoveryClient discoveryClient;

    /**
     * 导入LoadBalancerClient用于从Consul注册的服务中选择一个实例
     */
    @Autowired
    private LoadBalancerClient loadBalancer;

    /**
     * 导入RestTemplate用于访问RESUFul API
     */
    @Resource
    RestTemplate restTemplate;

    /**
     * 获取Consul注册的所有服务
     */
    @RequestMapping(value = "/services",method = RequestMethod.GET)
    public Object services() {
        return discoveryClient.getServices();
    }


    /**
     * 从Consul注册的服务中选择一个实例，发送RESTFul请求
     * @return
     */
    @RequestMapping(value = "/relayTest")
    public ResponseEntity relayTest(){
        ServiceInstance serviceInstance = loadBalancer.choose("User");
        System.out.println("服务地址：" + serviceInstance.getUri());
        System.out.println("服务名称：" + serviceInstance.getServiceId());

        return restTemplate.getForEntity(
                serviceInstance.getUri().toString() + "/login", String.class);
    }


    @RequestMapping("/home")
    public String home() {
        return "Hello World";
    }

}
