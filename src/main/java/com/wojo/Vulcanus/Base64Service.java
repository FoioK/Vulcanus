package com.wojo.Vulcanus;

import java.util.List;

public interface Base64Service {

    String getBase64(String url);

    List<String> getBase64SingleRequest(List<String> urls);

    List<String> getBase64(List<String> urls);
}
