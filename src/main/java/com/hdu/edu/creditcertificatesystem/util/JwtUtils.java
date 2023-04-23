package com.hdu.edu.creditcertificatesystem.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hdu.edu.creditcertificatesystem.pojo.dto.TokenDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * jwt工具类
 *
 * @author Jackson
 * @date 2022/4/30 20:42
 */
public class JwtUtils {
    private static final String SING = "Authorization";

    private JwtUtils(){
    }

    /**
     * 创建token
     *
     * @param tokenDTO token中保存的实体
     * @return token字符串
     */
    public static String createToken(TokenDTO tokenDTO) {
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("account", tokenDTO.getAccount());
        builder.withClaim("role", tokenDTO.getRole());
        builder.withClaim("time", LocalDateTime.now().toString());
        return builder.sign(Algorithm.HMAC256(SING));
    }

    /**
     * 验证令牌是否通过
     *
     * @param token 令牌
     */
    public static boolean verifyToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 从token中获取信息
     *
     * @param token
     * @return
     */
    public static DecodedJWT getTokenInfo(String token) {
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }
}
