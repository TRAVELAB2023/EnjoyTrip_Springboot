package com.enjoytrip.auth.service;
import java.util.Map;

public interface JwtService {

    <T> String createAccessToken(String key, T data);
    <T> String createRefreshToken(String key, T data);
    <T> String create(String key, T data, String subject, long expir);
    Map<String, Object> get();
    String getUserId();
    boolean checkToken(String jwt);

}
