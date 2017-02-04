package com.bamaao.testtool;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

/**
 * Created by ZYC on 2016/12/29.
 */
public class HttpGetWithBody extends HttpEntityEnclosingRequestBase{

    private static final String METHOD_NAME = "GET";

    @Override
    public String getMethod() {
        return METHOD_NAME;
    }
}
