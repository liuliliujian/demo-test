package com.danson.demo.pegasus;

import cn.eclicks.pegasus.client.rpc.proxy.ProxyBeanFactory;
import com.danson.api.example.remote.service.ExampleRemoteService;

/**
 * Created by dansonliu on 16/9/1.
 */
public class PegasusDemo {

    public static void main(String[] args) {
        ProxyBeanFactory<ExampleRemoteService> proxyBeanFactory = new ProxyBeanFactory<ExampleRemoteService>();
        proxyBeanFactory.setServiceName("com.danson.api.example.remote.service.ExampleRemoteService_1.0.0");
        proxyBeanFactory.setInterfaceClazz(ExampleRemoteService.class);
        proxyBeanFactory.setAddresses("127.0.0.1:4000");
        proxyBeanFactory.init();
        ExampleRemoteService exampleRemoteService = proxyBeanFactory.getStub();
        String result = exampleRemoteService.hello("kitty");
        System.out.println("hello result1: " + result);
        result = exampleRemoteService.hello("kitty", 2);
        System.out.println("hello result2: " + result);
        result = exampleRemoteService.hello("kitty");
        System.out.println("hello result3: " + result);
        result = exampleRemoteService.hello("kitty", 3);
        System.out.println("hello result4: " + result);
    }

}
