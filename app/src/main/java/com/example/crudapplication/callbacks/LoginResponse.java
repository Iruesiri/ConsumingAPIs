package com.example.crudapplication.callbacks;

import java.io.Serializable;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;

public class LoginResponse extends ResponseBody implements Serializable {

    //@javax.annotation.Nullable
    @Override
    public MediaType contentType() {
        return null;
    }

    @Override
    public long contentLength() {
        return 0;
    }

    @Override
    public BufferedSource source() {
        return null;
    }
}
