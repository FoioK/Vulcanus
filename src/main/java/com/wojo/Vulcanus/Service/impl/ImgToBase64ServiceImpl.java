package com.wojo.Vulcanus.Service.impl;

import com.wojo.Vulcanus.Service.ImgToBase64Service;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
public class ImgToBase64ServiceImpl implements ImgToBase64Service {

    @Override
    public String getBase64FromImage(String url) {
        return this.encodeImage(this.getByteArrayFromImageURL(url));
    }

    @Override
    public byte[] getByteArrayFromImageURL(String url) {
        InputStream in;
        byte[] bytes = new byte[0];
        try {
            in = new URL(url).openStream();
            bytes = IOUtils.toByteArray(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    @Override
    public String encodeImage(byte[] imageByteArray) {
        return Base64.encodeBase64URLSafeString(imageByteArray);
    }
}
