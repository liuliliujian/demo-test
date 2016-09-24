package com.danson.demo.miscs;

import cn.eclicks.com.caucho.hessian.io.Hessian2Input;
import com.danson.api.remote.definition.AbstractApiResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.util.*;

/**
 * Created by dansonliu on 16/8/8.
 */
public class HessianDemo {

    public static void main(String[] args) throws IOException {
        byte[] bytes1 = IOUtils.toByteArray(new FileInputStream("/tmp/hessian_obj1"));
        byte[] bytes2 = IOUtils.toByteArray(new FileInputStream("/tmp/hessian_obj2"));
        System.out.println(ArrayUtils.isEquals(bytes1,bytes2));
        if (true) return;
        HessianCodec hessianCodec = new HessianCodec();
        GenericApiResponse2 response = new GenericApiResponse2();
        HashMap map = new HashMap();
        map.put("result", "message[kitty miao] is received");
        map.put("times", 10);
        response.result(map);
//        ArrayList list = new ArrayList(Arrays.asList("one", "two", "three", 3L));
//        response.result(list);
//        SubmitOrderApiRequest request = new SubmitOrderApiRequest();
//        request.setType(3);
//        request.setUserId(100);
//        request.setOrderItem("三亚五日游");
//        Customer customer = new Customer();
//        customer.setName("lucy");
//        customer.setAge(13);
//        customer.setGender(Gender.Female);
//        request.setCustomer(customer);
//        response.result(request);
//        response.result("nice");
//        response.result(new HashMap());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        hessianCodec.encode(response, output);
        GenericApiResponse2 result = (GenericApiResponse2) hessianCodec.decode(new ByteArrayInputStream(output.toByteArray()));
        if (result.getResult() != null) {
            System.out.println(result.getResult() + "   " + result.getResult().getClass());
        }
//        BException innerError = new BException("inner error", new RuntimeException("innermost error"));
//        byte[] bytes = SerializeUtil.hessianSerialize(new AException("error happened", innerError));
//        FileOutputStream output = new FileOutputStream("/tmp/demofile");
//        IOUtils.write(bytes, output);
//        IOUtils.closeQuietly(output);

//        FileInputStream input = new FileInputStream("/tmp/demofile");
//        Exception exception = (Exception) hessianDeserialize(IOUtils.toByteArray(input), Exception.class);
//        exception.printStackTrace();
    }

    public static Object hessianDeserialize(byte[] data, Class clazz) throws IOException {
        Object rv = null;
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        Hessian2Input hessian2Input = new Hessian2Input(bais);
        rv = hessian2Input.readObject(clazz);
        //place here not finally block, because byte array stream is not required to be closed
        hessian2Input.close();
        return rv;
    }

//    public static class AException extends Exception {
//
//        public AException(String message, Throwable cause) {
//            super(message, cause);
//        }
//
//    }

    public static class BException extends Exception {

        public BException(String message, Throwable cause) {
            super(message, cause);
        }

    }

    public static class GenericApiResponse2 extends AbstractApiResponse {
        private Serializable result;

        public GenericApiResponse2() {
        }

        public GenericApiResponse2(Serializable result) {
            this.result = result;
        }

        public GenericApiResponse2 result(Serializable result) {
            this.result = result;
            return this;
        }

        public GenericApiResponse2 result(String name, Serializable value) {
            if (result == null || !(result instanceof Map)) {
                result = new HashMap<String, Serializable>();
            }
            ((Map) result).put(name, value);
            return this;
        }

        public Serializable getResult() {
            return result;
        }

        public void setResult(Serializable result) {
            this.result = result;
        }
    }

}

