package com.danson.demo.miscs;


import cn.eclicks.com.caucho.hessian.io.Hessian2Input;
import cn.eclicks.com.caucho.hessian.io.Hessian2Output;
import cn.eclicks.com.caucho.hessian.io.SerializerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by dansonliu on 16/8/23.
 */
public class HessianCodec {

    private static final SerializerFactory SERIALIZER_FACTORY = new SerializerFactory();

    /**
     * @see cn.eclicks.pegasus.common.remoting.Codec#encode(java.lang.Object, java.io.OutputStream)
     */
    public void encode(Object object, OutputStream output) throws IOException {
        Hessian2Output h2out = new Hessian2Output(output);
        try {
            //注意这里复用serializer factory，实例化它非常耗时
            h2out.setSerializerFactory(SERIALIZER_FACTORY);
            h2out.writeObject(object);
            h2out.flush();
        } finally {
            h2out.close();
        }
    }

    /**
     * @see cn.eclicks.pegasus.common.remoting.Codec#decode(java.io.InputStream)
     */
    public Object decode(InputStream input) throws IOException {
        Hessian2Input h2in = new Hessian2Input(input);
        try {
            //注意这里复用serializer factory，实例化它非常耗时
            h2in.setSerializerFactory(SERIALIZER_FACTORY);
            return h2in.readObject();
        } finally {
            h2in.close();
        }
    }

}
