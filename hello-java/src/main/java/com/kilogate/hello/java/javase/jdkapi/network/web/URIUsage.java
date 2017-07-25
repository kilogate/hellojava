package com.kilogate.hello.java.javase.jdkapi.network.web;

import java.net.URI;

/**
 * URI 用法
 *
 * @author fengquanwei
 * @create 2017/7/24 22:12
 **/
public class URIUsage {
    public static void main(String[] args) {
        // URI 规范
        // [scheme:]schemeSpecificPart[#fragment]
        // [scheme:][//authority][path][?query][#fragment]
        // [scheme:][//[user-info@]host[:port]][path][?query][#fragment]
        URI uri = URI.create("https://fengquanwei@www.kilogate.com:8080/api/user/info?id=123#tail");
        String scheme = uri.getScheme();
        String schemeSpecificPart = uri.getSchemeSpecificPart();
        String authority = uri.getAuthority();
        String userInfo = uri.getUserInfo();
        String host = uri.getHost();
        int port = uri.getPort();
        String path = uri.getPath();
        String query = uri.getQuery();
        String fragment = uri.getFragment();

        // 解析 & 相对化
        URI baseUri = URI.create("http://www.kilogate.com/");
        URI combinedUri = URI.create("http://www.kilogate.com/api/user/info");
        URI relativizeUri = baseUri.relativize(combinedUri);
        URI resolveUri = baseUri.resolve(relativizeUri);
    }
}
