package com.wojo.Vulcanus.Service;

public interface ImgToBase64Service {

    String getBase64FromImage(String url);

    byte[] getByteArrayFromImageURL(String url);

    String encodeImage(byte[] imageByteArray);
}
