package com.danson.demo.miscs;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import cn.eclicks.orion.util.net.NetUtil;

/**
 * Created by dansonliu on 16/8/4.
 */
public class MixAllDemo {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        String[] strings = StringUtils.splitByWholeSeparator("test1\n$$\ntest2", "\n$$\n");
        System.out.println(strings[0]);
        System.out.println(strings[1]);

        System.out.println(NetUtil.getLocalHost());

        Properties props = new Properties();
        props.setProperty("test1", "kick");
        props.setProperty("test2", "nice");
        props.setProperty("test3", "world");
        props.load(MixAllDemo.class.getResourceAsStream("/demo.properties"));
        System.out.println(props.getProperty("test1"));
        System.out.println(props.getProperty("test2"));
        System.out.println(props.getProperty("test3"));

        if (true)
            return;

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(new Order("p1", 3));
        orderList.add(new Order("p2", 4));
        orderList.add(new Order("p3", 5));
        System.out.println(orderList.contains(new Order("p2", 1)));

        Method[] methods = OrderService.class.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        System.out.println("===================");
        methods = OrderService.class.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        Method method = MixAllDemo.class.getMethod("doSomething");
        System.out.println(method.getReturnType() == Order.class);
        System.out.println(method.invoke(new MixAllDemo()));
        method = OrderService.class.getMethod("getOrder");
        System.out.println(method.getReturnType() == Order.class);
        method = OrderService.class.getMethod("echoOrder");
        System.out.println(method.getReturnType() == void.class); //void返回值的方法
        System.out.println(method.getParameterTypes().length); //没有参数的方法，parameterTypes为空数组

        System.out.println(new HashSet<String>(Arrays.asList(StringUtils.split(" ", ", "))).size());
        System.out.println(StringUtils.join(new TestEnum[] { TestEnum.Second, TestEnum.First }, ","));

        Order order = new Order("pro1", 3);
        Method submitMethod = OrderService.class.getMethod("submitOrder", Order.class);

        final OrderService orderService = new OrderServiceImpl();

        Object result = submitMethod.invoke(orderService, order);
        System.out.println("result: " + result);

        OrderService serviceProxy = (OrderService) Proxy.newProxyInstance(MixAllDemo.class.getClassLoader(), new Class[] { OrderService.class },
            new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    Object result = method.invoke(orderService, args);
                    return "too " + result;
                }
            });
        System.out.println("result: " + submitMethod.invoke(serviceProxy, order));
    }

    public static interface OrderService {
        String submitOrder(Order order);

        Order getOrder();

        void echoOrder();
    }

    public static class OrderServiceImpl implements OrderService {

        @Override
        public String submitOrder(Order order) {
            return "simple order with product: " + order.product + ", count: " + order.count + ".";
        }

        @Override
        public Order getOrder() {
            return null;
        }

        @Override
        public void echoOrder() {

        }
    }

    public static enum TestEnum {
        First, Second, Third;
    }

    public static class Order {
        String product;
        int    count;

        public Order(String product, int count) {
            this.product = product;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            Order order = (Order) o;

            if (product != null ? !product.equals(order.product) : order.product != null)
                return false;

            return true;
        }

        @Override
        public int hashCode() {
            return product != null ? product.hashCode() : 0;
        }
    }

    public boolean doSomething() {
        return MixAllDemo.errorResultValue(300, "code not found", boolean.class);
    }

    static <T> T errorResultValue(int code, String msg, Class<T> returnType) {
        if (returnType == byte.class) {
            return (T) NumberUtils.BYTE_ZERO;
        } else if (returnType == short.class) {
            return (T) NumberUtils.SHORT_ZERO;
        } else if (returnType == int.class) {
            return (T) NumberUtils.INTEGER_ZERO;
        } else if (returnType == long.class) {
            return (T) NumberUtils.LONG_ZERO;
        } else if (returnType == float.class) {
            return (T) NumberUtils.FLOAT_ZERO;
        } else if (returnType == double.class) {
            return (T) NumberUtils.DOUBLE_ZERO;
        } else if (returnType == boolean.class) {
            return (T) Boolean.TRUE;
        } else if (returnType == char.class) {
            return (T) Character.valueOf(' ');
        } else {
            return null;
        }
    }

}
