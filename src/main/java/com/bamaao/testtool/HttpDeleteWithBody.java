package com.bamaao.testtool;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

/**
 * Created by ZYC on 2016/12/29.
 */
public class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {

    private static final String METHOD_NAME = "DELETE";

    @Override
    public String getMethod() {
        return METHOD_NAME;
    }
}
