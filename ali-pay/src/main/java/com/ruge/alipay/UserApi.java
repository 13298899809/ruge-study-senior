package com.ruge.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoAuthRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
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
//    public static final String serverUrl = "https://openapi.alipay.com/gateway.do";
//    public static final String appId = "2021001192621231";
//    public static final String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC3Z71xoalXXuJvIwcGGuYggRZ5W9LucvMxgZvgM+dFe03Ubd/e4XbBh9fOUerBuydobiQQ0/1P1D0CRAHPjTGcctqAmeE+G/ouAqR4JGvSXk6vN82sROQOrO+X4NnrOmW4N/D2clbSMb/FGnOukN2wS0sgS+Vk4EKMXfUARVSF/OvGdb3C+LNfTT0ADiQhJ5yF4oVw++Sfw/zkxsd3ommhrjo+IdtohZjreN8Q0a2HUNA7OVNMqrQK39VT2gPnbyTU4Kj1AdwIzrELf/zr3F0+ElRFHCs/GaFP1ImRN4jzAwDuNmsJ1Rexw8BKRXaKM+NKJASfTUjNXYAn1606nABJAgMBAAECggEAKnEpV+hvRbbBc7KJZ5JF+vOkfcUQFPoo6SXzKnkYZe3Y3vXmPq+ySarj9+zhBKAIyJPkaLiGADk/PQz71kn4VxoFEomJpFPZG1ilMuBKy03jq4ygXeM7W2INt/nitBq5e14GtyFSkiXNGRG7/4Oqqadi5UBq/2MeQ1li3wMsQi0XE37wVXaLxB/2hXNcCmpn4b25KayGp2fjxeAk+dg0MU5E0ix50uqNOVXlsYzqRV2EsC/fhcar/2ephnqj3t7lnGKZXMYL4rg8DBy4dM9cmlAD43xwTeOU5b2aVRxzWvRcDF1mZpM0z9CMn5/FqqO2vRWsrMqnTbcLoCI+aUEEAQKBgQDf3T6MSfSKw1CwRKjXCZMsEMyfnAUsAewkOep1pJUUCxA9SKAVyg+rYUGYeS0HI4Vn8Qd2AVNZ4Rz2YielAgC/1luFxKpevBFLRN7oUFmYkb938MkLWfLlpzs64mKOjyaAJ69MTFKUQ1t2u+IPYkuQePEwy12RcGn5JR2Ha4ZrwQKBgQDRu6xo7uPuXMr6w83Ly9DDTR7leIBJWH3Ssid0U7loMr+CZ1tJMN7IgeRg1T6QRcCAiMadW/tCbcJQpP3rRwSEELaIPgZAnuAOTHCXiR1l0FW6ElpJAkmx4TSZ0e3ImsJscJ5eG5IwMkIhpjdKfKemfnrxOYfepzd/MTdru9nWiQKBgDmCWMMpbI0JR/1AZi934jr7xdVq+1km1UcpKbY3JaUUgoN72MRAOG5KedN1rb+bvHXLUaNbC9KXJh2VcaHONesSYkinES1tGvQ/KRR9L8cmCg4B6WPlI6UjpO+noLIGzFk32Ky9Hqm/9As4kQZqtgbTSwLB+7OVMWQzyLY1RT8BAoGAC2IZQHS+0EAfEC8yaz47rW3xIFlC9TglVBsxQwIMTRh8BnLfpIBPhyhtocfRJnlyskc6KG5Gm9tjMYdS9MMc/qH9ljVvl3BIDt6dOcxKWS1OidPXLKFAKLRla3fWZQ3pQYN3RPpQG+eOaU8hLxzG6KVyvMb3leEThQbB0EoYz6kCgYEA2l6Kn/1ul0ZLtUXnketqZSMArLl2nzn8I+56/m6KthGetRG4z02WWjs1hPOY+G8BaGFGDvCrpMhO0ERVPfhyX4SyHrXN8POIFs1msQTduqD9N7gE7lHUSZ1tje1p5DPAdXb1L5/SbsAG1G1bACjuD/JgIGnSco9LOF4CcCm5vQQ=";
//    public static final String format = "json";
//    public static final String charset = "GBK";
//    public static final String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxboyhA/37mSy9Hhz79kNu9LmvHC6jtfQi7bVx1pNQWvkVCTWjsUzHF5iWaLo6bjfigpM3CRisc7yZn5fMs3QHyOkVfyk710GNbjGczTQT6CSFzFWSjkALqblnDK78Ykwf1mHAqrEHabokbVSZn5vfCBvdG442ErwkElZHIK8YJDzyQ0OAq5uhbgcr7ON2a9IMw/fomJ6rlty9IKzOp3Jc9taClq6Mkd+ekRpq4OP8IdIVWDgdj5hUF7uR2z0GyrBamrpWtqQCC0ok/qr3CEvk29o++uKBQni+67E34Jqh4K5ytk9LrjQh2Vc0cisRcn8QiWMTyI/zAC9v2HxC532cQIDAQAB";
//    public static final String signType = "RSA2";

    public static final String serverUrl = "https://openapi.alipay.com/gateway.do";
    public static final String appId = "2021001181604623";
    public static final String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDHWEpitMIf6tl4To6haZAutIWjFPqHVQcIyOXmQTnucqvlFrnIP7oY04uG/vbfr3hwbcBuiIM0BbuIp8dJtkVRzEbju5JIV6SM3ctgdl/fhO0+zEQpvf8PKm1MFsP6Sa5/yZRQtaRQhH84Uyn2Q1lQ/aw52ZYd32e22Oe51X6d2s4OowGjmkFRi5NHJqsM8hgUUZyVVs2zgDfVpTYP8/QrUgcCELrzGqT0kQpNNiEYHGiR/LJU+rrVXjRQzMrUKoL46Sd27yiEH9Rb2BjFxHX/lTO1FXvpbp7b/81T+n8/ZjrtDhTF7nHac7YEXL5jDUWJMNq9EHdv78tZYYKt5IsPAgMBAAECggEATu3xfgWuO28ErnMEXHrswOFvbHQZAk6zW1IYXUQESRqUXz/RTa2V5NCWFVyMqWLT4EhEgj+BG68NYv1zlgcaiVnF09PoBsbU+4WySj50lY8PVOcSMijtfbbB0ffNnyNfeW96Tz6skcj0inu7SKvACKZ4c+toWemkKizWC6mx2ZQfBH4BEGceM9Smm3DLSasuTCne0jnbGnfBfbWPvKMeeVHdEmQbcq1I+gLtgc50NxvWyJh/pGFgn1qiaN9l44ztMacIknXNNCxYe8SviZcqrhOlC5UvVlm9cHXY3ZJtMTJgCLYr4BFY5Pw4wkLIRb0k1faSTmrGxFnhfUYsBtCe0QKBgQDvPuUbtY48eTkpfJGC0ukZ6+0mXdGiAb6Dtlyp3Nw8uQAqIjYB+2yEOS93GYDB14vJeWPGC07UFxmtb2K0ubzZS6XVtiG7CmN71bDp6TmJ6t+In7Zc6Pg0nZm2i/h5kvn2hEfhB9HjExnVKGx4GfA/+vtniKTAHsPxag1gap8zpwKBgQDVThHCY+sP6iP2TglbEaanXha2PjjZDsUgmUUUmRz+WHqLVWFg3uQL2AB+SiwxJ67/mJ6GNeki8fhjIf7zBaI6jlAIpRYo0lysa06ueBXL1oi1wXaSe2zqprQ18TkdDHK6EwVvKQMQzYQQ1xbacMxV/25hlchZ8txMJXRDSGJ6WQKBgQCh8RfR9sXtpzyLGXfBbxQ76Jeg9bZ1i+qyaxk0HXyyWaOD4Z5fHxnoMnEvlAK0szs6yEFCB9t28p/95ryQin0LNQpuq5cPbUvDwfS7kJPTCDoA1+Lv5v5DVz558yrg2Bz8wbOJ3eozY3WD9JXfgec+15ufDD5AkVKeseOWFPXoVwKBgGB+K5yDdN/DLLeMf8D50CkgcCfLKA5RFy3zCAQUa8BHqFAFtMKMsh8FfNuklC88Y3buxK+0DZ4oVhQKWfn4D3Zr0JrIi6i12QSKmpJkcn++siN+vDPxr8jK1vUcBZk1e2Tfm/K4W9GrFtZDZuw0YMt6fKWai4LdUXkE7H5PbXUpAoGBAImKOkM39whxExbbyE12tuer6rndFzeSLwlWfNXV2R3FETduRZwGkX3kbFYzOzKMyTCAZTG39xmHJpqLQfezN5Rtssrpt9Jr1Vhe/Nx20ArutgSpwq1o9DKsWWQLcLCKNeC6uvRHt+yEmk7tBxXV2fut6KQsX7Q7tZoWIN1W/PvX";
    public static final String format = "json";
    public static final String charset = "GBK";
    public static final String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxboyhA/37mSy9Hhz79kNu9LmvHC6jtfQi7bVx1pNQWvkVCTWjsUzHF5iWaLo6bjfigpM3CRisc7yZn5fMs3QHyOkVfyk710GNbjGczTQT6CSFzFWSjkALqblnDK78Ykwf1mHAqrEHabokbVSZn5vfCBvdG442ErwkElZHIK8YJDzyQ0OAq5uhbgcr7ON2a9IMw/fomJ6rlty9IKzOp3Jc9taClq6Mkd+ekRpq4OP8IdIVWDgdj5hUF7uR2z0GyrBamrpWtqQCC0ok/qr3CEvk29o++uKBQni+67E34Jqh4K5ytk9LrjQh2Vc0cisRcn8QiWMTyI/zAC9v2HxC532cQIDAQAB";
    public static final String signType = "RSA2";

    public static final AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);

    /**
     * 步骤1 获取授权页面
     * https://opendocs.alipay.com/open/289/105656#%E7%AC%AC%E4%B8%80%E6%AD%A5%EF%BC%9AURL%E6%8B%BC%E6%8E%A5%E4%B8%8Escope%E8%AF%A6%E8%A7%A3
     * https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=2021001192621231&scope=auth_user&redirect_uri=http://cdpuat.faw-vw.com/user/public/oauth/login
     * https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=2021001181604623&scope=auth_user&redirect_uri=http://cdpuat.faw-vw.com/user/public/oauth/login
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

    /**
     * 第三步：auth_code换取access_token与user_id
     */
    @Test
    public void 换取授权访问令牌() {
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode("d18b9c6e489c4ba7a28b2b4c3dceXX59");
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
        public void 支付宝定制(){
        try {
             AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
            AlipayUserInfoShareResponse response = alipayClient.execute(request,"authusrB23fce143047e4ffdb53ca3ca4fc96X59");
            if(response.isSuccess()){
                System.out.println("调用成功");
                System.out.println(response.getCode());
                System.out.println(response.getMsg());
                System.out.println(response.getAddress());
                System.out.println(response.getArea());
                System.out.println(response.getAvatar());
                System.out.println(response.getCity());
                System.out.println(response.getEmail());
                System.out.println(response.getGender());
                System.out.println(response.getNickName());
                System.out.println(response.getMobile());
            } else {
                System.out.println("调用失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
    @Test
public void test2(){
        Long.parseLong("7448");
}
}
