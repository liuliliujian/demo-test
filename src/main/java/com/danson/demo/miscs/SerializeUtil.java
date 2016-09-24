package com.danson.demo.miscs;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import cn.eclicks.com.caucho.hessian.io.Hessian2Input;
//import cn.eclicks.com.caucho.hessian.io.Hessian2Output;

/**
 * Created by dansonliu on 15/3/10.
 */
public class SerializeUtil {

    private static final Logger logger = LoggerFactory.getLogger(SerializeUtil.class);

    public static byte[] hessianSerialize(Object o) throws IOException {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        Hessian2Output hessian2Output = new Hessian2Output(baos);
//        hessian2Output.writeObject(o);
//        place here not finally block, because byte array stream is not required to be closed
//        hessian2Output.close();
//        return baos.toByteArray();
        return null;
    }

    public static Object hessianDeserialize(byte[] data) throws IOException {
        Object rv = null;
//        ByteArrayInputStream bais = new ByteArrayInputStream(data);
//        Hessian2Input hessian2Input = new Hessian2Input(bais);
//        rv = hessian2Input.readObject();
//        place here not finally block, because byte array stream is not required to be closed
//        hessian2Input.close();
        return rv;
    }

    public static <T extends Serializable> T clone(final T object) throws IOException {
        if (object == null) {
            return null;
        }
        return (T) hessianDeserialize(hessianSerialize(object));
    }

}
