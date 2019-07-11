package com.lzqs.zhangyushu.safety;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by JinZhicheng on 2019/1/24 10:40
 */
public class JwtUtil {

    /**
     * 过期时间30分钟
     */
    private static final long EXPIRE_TIME = 30 * 60 * 1000;
    /**
     * token私钥
     * UUID.randomUUID().toString()
     */
    private static final String TOKEN_SECRET = "LiangZiQiShi";


    /**
     * 生成签名
     *
     * @return
     */
    public static String sign( String userId) {
        // 过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        // 私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        // 设置头部信息
        Map<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        // 附带用户信息，生成签名
        return JWT.create()
                .withHeader(header)  // header
                .withClaim("userId", userId)
                .withClaim("suijishu", System.currentTimeMillis())
//                .withExpiresAt(date)  // 设置过期时间，过期时间要大于签发时间
//                .withIssuedAt(date)  // 设置签发时间
                .sign(algorithm); // 加密
    }

    /**
     * 解密Token
     */
    public static Map<String, Claim> verifyToken(String token) throws Exception {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(TOKEN_SECRET);
        } catch (Exception e) {
            throw new RuntimeException("登录凭证已过期，请重新登录");
        }
        return jwt.getClaims();
    }

    /**
     * 校验token是否正确
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的openId
     */
    public static String getMobile(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("mobile").asString();
    }

    /**
     * @return token中包含的用户Id
     */
    public static String getUserId(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("userId").asString();
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的openId
     */
    public static String getObject(String token, String name) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("name").asString();
    }

    /**
     * 产生一个32位的GUID
     *
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    public static void main(String[] args) {

//        String sign = sign("18801417651", "4d5d8a902fea41dbb567b21efc65da13");

//        String sign = sign("18801417651", "4d5d8a902fea41dbb567b21efc65da13");

    }

}
