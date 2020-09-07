package com.ruge.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoAuthRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoAuthResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import org.junit.Test;

/**
 * @author ruge.wu
 * @version 0.0.1
 * @ClassName UserApi
 * @date 2020.08.31 14:03
 */
public class UserApi {
    public static final String serverUrl = "https://openapi.alipay.com/gateway.do";
    public static final String appId = "2021001192621231";
    public static final String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC3Z71xoalXXuJvIwcGGuYggRZ5W9LucvMxgZvgM+dFe03Ubd/e4XbBh9fOUerBuydobiQQ0/1P1D0CRAHPjTGcctqAmeE+G/ouAqR4JGvSXk6vN82sROQOrO+X4NnrOmW4N/D2clbSMb/FGnOukN2wS0sgS+Vk4EKMXfUARVSF/OvGdb3C+LNfTT0ADiQhJ5yF4oVw++Sfw/zkxsd3ommhrjo+IdtohZjreN8Q0a2HUNA7OVNMqrQK39VT2gPnbyTU4Kj1AdwIzrELf/zr3F0+ElRFHCs/GaFP1ImRN4jzAwDuNmsJ1Rexw8BKRXaKM+NKJASfTUjNXYAn1606nABJAgMBAAECggEAKnEpV+hvRbbBc7KJZ5JF+vOkfcUQFPoo6SXzKnkYZe3Y3vXmPq+ySarj9+zhBKAIyJPkaLiGADk/PQz71kn4VxoFEomJpFPZG1ilMuBKy03jq4ygXeM7W2INt/nitBq5e14GtyFSkiXNGRG7/4Oqqadi5UBq/2MeQ1li3wMsQi0XE37wVXaLxB/2hXNcCmpn4b25KayGp2fjxeAk+dg0MU5E0ix50uqNOVXlsYzqRV2EsC/fhcar/2ephnqj3t7lnGKZXMYL4rg8DBy4dM9cmlAD43xwTeOU5b2aVRxzWvRcDF1mZpM0z9CMn5/FqqO2vRWsrMqnTbcLoCI+aUEEAQKBgQDf3T6MSfSKw1CwRKjXCZMsEMyfnAUsAewkOep1pJUUCxA9SKAVyg+rYUGYeS0HI4Vn8Qd2AVNZ4Rz2YielAgC/1luFxKpevBFLRN7oUFmYkb938MkLWfLlpzs64mKOjyaAJ69MTFKUQ1t2u+IPYkuQePEwy12RcGn5JR2Ha4ZrwQKBgQDRu6xo7uPuXMr6w83Ly9DDTR7leIBJWH3Ssid0U7loMr+CZ1tJMN7IgeRg1T6QRcCAiMadW/tCbcJQpP3rRwSEELaIPgZAnuAOTHCXiR1l0FW6ElpJAkmx4TSZ0e3ImsJscJ5eG5IwMkIhpjdKfKemfnrxOYfepzd/MTdru9nWiQKBgDmCWMMpbI0JR/1AZi934jr7xdVq+1km1UcpKbY3JaUUgoN72MRAOG5KedN1rb+bvHXLUaNbC9KXJh2VcaHONesSYkinES1tGvQ/KRR9L8cmCg4B6WPlI6UjpO+noLIGzFk32Ky9Hqm/9As4kQZqtgbTSwLB+7OVMWQzyLY1RT8BAoGAC2IZQHS+0EAfEC8yaz47rW3xIFlC9TglVBsxQwIMTRh8BnLfpIBPhyhtocfRJnlyskc6KG5Gm9tjMYdS9MMc/qH9ljVvl3BIDt6dOcxKWS1OidPXLKFAKLRla3fWZQ3pQYN3RPpQG+eOaU8hLxzG6KVyvMb3leEThQbB0EoYz6kCgYEA2l6Kn/1ul0ZLtUXnketqZSMArLl2nzn8I+56/m6KthGetRG4z02WWjs1hPOY+G8BaGFGDvCrpMhO0ERVPfhyX4SyHrXN8POIFs1msQTduqD9N7gE7lHUSZ1tje1p5DPAdXb1L5/SbsAG1G1bACjuD/JgIGnSco9LOF4CcCm5vQQ=";
    public static final String format = "json";
    public static final String charset = "GBK";
    public static final String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxboyhA/37mSy9Hhz79kNu9LmvHC6jtfQi7bVx1pNQWvkVCTWjsUzHF5iWaLo6bjfigpM3CRisc7yZn5fMs3QHyOkVfyk710GNbjGczTQT6CSFzFWSjkALqblnDK78Ykwf1mHAqrEHabokbVSZn5vfCBvdG442ErwkElZHIK8YJDzyQ0OAq5uhbgcr7ON2a9IMw/fomJ6rlty9IKzOp3Jc9taClq6Mkd+ekRpq4OP8IdIVWDgdj5hUF7uR2z0GyrBamrpWtqQCC0ok/qr3CEvk29o++uKBQni+67E34Jqh4K5ytk9LrjQh2Vc0cisRcn8QiWMTyI/zAC9v2HxC532cQIDAQAB";
    public static final String signType = "RSA2";

    public static final AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);

    /**
     * 步骤1 获取授权页面
     * https://opendocs.alipay.com/open/289/105656#%E7%AC%AC%E4%B8%80%E6%AD%A5%EF%BC%9AURL%E6%8B%BC%E6%8E%A5%E4%B8%8Escope%E8%AF%A6%E8%A7%A3
     * https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=2021001192621231&scope=auth_user&redirect_uri=http://cdpuat.faw-vw.com/user/public/oauth/login
     * <p>
     * 步骤2 获取 auth_code
     * {"GET":{"scheme":"http","host":"cdpuat.faw-vw.com","filename":"/user/public/oauth/login","query":{"app_id":"2021001192621231","source":"alipay_wallet","userOutputs":"auth_user","scope":"auth_user","alipay_token":"","auth_code":"6a964b9505854a468a9f21c058a6SX86"},"remote":{"地址":"219.149.223.125:80"}}}
     * https://opendocs.alipay.com/open/289/105656#%E7%AC%AC%E4%BA%8C%E6%AD%A5%EF%BC%9A%E8%8E%B7%E5%8F%96auth_code
     * <p>
     * 步骤3 auth_code换取access_token与user_id
     */

    @Test
    public void 用户登录授权() throws AlipayApiException {
        AlipayUserInfoAuthRequest request = new AlipayUserInfoAuthRequest();
        request.setBizContent("{" +
                "      \"scopes\":[" +
                "        \"auth_base\"" +
                "      ]," +
                "\"state\":\"init\"" +
                "  }");
        AlipayUserInfoAuthResponse response = alipayClient.pageExecute(request);
        System.out.println(response);
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }

    @Test
    public void 换取授权访问令牌() throws AlipayApiException {
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode("c91fd93a87734a1890ff494f4e6bSX86");
        request.setGrantType("authorization_code");
        try {
            AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(request);
            System.out.println(oauthTokenResponse.getBody());
            System.out.println(oauthTokenResponse.getAccessToken());
            System.out.println(oauthTokenResponse.getUserId());
        } catch (AlipayApiException e) {
            //处理异常
            e.printStackTrace();
        }
    }

    @Test
    public void 换取应用授权令牌() throws AlipayApiException {
        AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
        request.setBizContent("{" +
                "\"grant_type\":\"authorization_code或者refresh_token\"," +
                "\"code\":\"1cc19911172e4f8aaa509c8fb5d12F56\"" +
                "  }");
        AlipayOpenAuthTokenAppResponse response = alipayClient.execute(request);
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }
    @Test
        public void 支付宝定制(){
        try {
            AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
            String accessToken="";
            AlipayUserInfoShareResponse response = alipayClient.execute(request,accessToken);
            if(response.isSuccess()){
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }

}
