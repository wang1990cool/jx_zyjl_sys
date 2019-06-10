package io.admin.modules.auth.controller;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.admin.common.utils.R;
import io.admin.modules.ksbm.entity.KsbmBaseInfoVo;
import io.admin.modules.sys.entity.SysUserEntity;
import io.admin.modules.sys.service.SysUserService;
import io.admin.modules.sys.service.SysUserTokenService;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "/callback")
public class AuthReal {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;

/*
    //服务器ip
    String ip = "http://127.0.0.1:8080";
    //oauth clientId
    String clientId = "5cb7d6f774292964d41af700.abc";
    // oauth clientSecret
    String clientSecret = "m-zrtgOzQAG_4drfb92QigpPLvkWeHNxS589PfIO59E";

    String myscope ="all";
    String mystate = "default_state";
    String TiupURl = "https://tiup.tiup.cn";
*/

    //服务器ip
    // String ip = "http://127.0.0.1/zyjn";
        String ip = "http://172.17.1.48/zyjn";
    //oauth clientId
    String clientId = "5cb7d6f774292964d41af700.abc";
    // oauth clientSecret
    String clientSecret = "m-zrtgOzQAG_4drfb92QigpPLvkWeHNxS589PfIO59E";

    String myscope ="all datacenter";
    String mystate = "yourstate";
    String TiupURl = "https://i.abc.edu.cn";

    //oauth code 地址
    String codeUrl = TiupURl+"/oauth2/authorize?response_type=code&scope="+myscope+"&state="+mystate+"&client_id="+clientId+"&redirect_uri="+ip+"/callback/callbackCode";
    // 返回路径
//    String redirectUrl = ip+"/callback/accessToken";
    String redirectUrl = ip+"/static/index.html";
    String response_type = null;
    String code = null;
    //服务器端获取 Access Token
    //
    // token 地址
    String accessTokenUrl = TiupURl+"/oauth2/token";
    //获取用户数据的api
    String userInfoUrl = TiupURl+"/apis/oauth2/v1/profile";

    //获取用户基本信息的api
   String userBaseInfoUrl= TiupURl+ "/apis/datacenter/v2/user_baseinfos";

    private String myAccessToken;




    // 提交申请code的请求

    @ExceptionHandler(OAuthProblemException.class)
    @RequestMapping( value="/requestServerCode")
    public String requestServerFirst(HttpServletRequest request, HttpServletResponse response) throws OAuthProblemException,IOException {
        response_type = "code";
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        String redirectUrl = null;
        try {
            // 构建oauthd的请求。设置请求服务地址（accessTokenUrl）、clientId、response_type、redirectUrl
            OAuthClientRequest accessTokenRequest = OAuthClientRequest.authorizationLocation(codeUrl)
                    .setResponseType(response_type).setClientId(clientId).setRedirectURI(redirectUrl)
                    .buildQueryMessage();
            redirectUrl = accessTokenRequest.getLocationUri();
            response.sendRedirect(codeUrl);
//            System.out.println("redirectUrl：：："+redirectUrl);
        } catch (OAuthSystemException e) {
            e.printStackTrace();
        }
        return "redirect:"+redirectUrl;
    }

    @RequestMapping("/callbackCode")
    public Map<String, Object> toLogin(HttpServletRequest request,HttpServletResponse response)throws Exception {
        System.out.println("-----------客户端/callbackCode--------------------------------------------------------------------------------");
/*
        System.out.println("accessTokenUrl：：："+accessTokenUrl);
        System.out.println("codeUrl：：："+codeUrl);
*/
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        code = httpRequest.getParameter("code");
/*        System.out.println(code);*/
        OAuthClient oAuthClient =new OAuthClient(new URLConnectionClient());
        try {
            OAuthClientRequest accessTokenRequest = OAuthClientRequest
                    .tokenLocation(accessTokenUrl)
                    .setGrantType(GrantType.AUTHORIZATION_CODE)
                    .setClientId(clientId)
                    .setClientSecret(clientSecret)
                    .setCode(code)
                    .setRedirectURI(redirectUrl)
                    .buildQueryMessage();
            //去服务端请求access token，并返回响应
            accessTokenRequest.addHeader("Content-Type", "application/x-www-form-urlencoded");
            OAuthAccessTokenResponse oAuthResponse =oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST);
            //获取服务端返回过来的access token
            String accessToken = oAuthResponse.getAccessToken();
            myAccessToken=accessToken;
            //查看access token是否过期
            Long expiresIn =oAuthResponse.getExpiresIn();


            System.out.println("客户端/callbackCode方法的token：：："+accessToken);

            return accessToken(accessToken,response);
        } catch (OAuthSystemException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/accessToken")
    public Map<String, Object> accessToken(String accessToken,HttpServletResponse response) throws Exception{
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        try {
            System.out.println("-------获取Token----------------------------------------------------------------------------------");
            OAuthClientRequest userInfoRequest = new OAuthBearerClientRequest(userInfoUrl).setAccessToken(accessToken).buildQueryMessage();
            OAuthResourceResponse resourceResponse = oAuthClient.resource(userInfoRequest, OAuth.HttpMethod.GET,OAuthResourceResponse.class);
            String username = resourceResponse.getBody();
/*            System.out.println(1213);
            System.out.println(username);*/
            JsonParser jp = new JsonParser();
            //将json字符串转化成json对象
            JsonObject jo = jp.parse(username).getAsJsonObject();

            //sid学号或工号
            String sid="";
            JsonArray profilesjson=jo.get("profiles").getAsJsonArray();
            for(int i =0;i<profilesjson.size();i++){
                JsonObject job = profilesjson.get(i).getAsJsonObject();
                if(job.get("isprimary").getAsBoolean()){
                    sid=job.get("stno").getAsString();
                    System.out.println("stno=" + sid);
                    break;
                }
            }

            response.sendRedirect("http://172.17.1.48/zyjn/static/index.html#/authlogin?sid="+sid);
            // 获取返回信息
            //返回 调用登录方法
//            return r;  //调用方法 loginconn.login(sid);
        } catch (OAuthSystemException e) {
            e.printStackTrace();
        } catch (OAuthProblemException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping( value="/auth")
    public R oauth(@RequestParam("sid") String sid ) throws OAuthProblemException,Exception {

        SysUserEntity user = sysUserService.queryByUserName(sid);
        //账号不存在、密码错误
        if(user == null ) {
            return R.error("账号或密码不正确");
        }

        //账号锁定
        if(user.getStatus() == 0){
            return R.error("账号已被锁定,请联系管理员");
        }
        //生成token，并保存到数据库
        R r = sysUserTokenService.createToken(user.getUserId());
        return r;  //调用方法 loginconn.login(sid);
    }

    private String getUid(){
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        try {
            System.out.println("-------获取Token----------------------------------------------------------------------------------");
            OAuthClientRequest userInfoRequest = new OAuthBearerClientRequest(userInfoUrl).setAccessToken(myAccessToken).buildQueryMessage();
            OAuthResourceResponse resourceResponse = oAuthClient.resource(userInfoRequest, OAuth.HttpMethod.GET,OAuthResourceResponse.class);
            String username = resourceResponse.getBody();
            JsonParser jp = new JsonParser();
            //将json字符串转化成json对象
            JsonObject jo = jp.parse(username).getAsJsonObject();

            //uid学号或工号
            String uid=jo.get("uid").getAsString();
             return uid;
        } catch (OAuthSystemException e) {
            e.printStackTrace();
        } catch (OAuthProblemException e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping("/getUserBaseInfo")
    private R getUserBaseInfo(){
        System.out.println("-------获取Uid-----------------");
        String uid =this.getUid();
        System.out.println("Uid=" + uid);
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        try {
            System.out.println("-------获取Token----------------------------------------------------------------------------------");
            OAuthClientRequest userInfoRequest = new OAuthBearerClientRequest(userBaseInfoUrl+"/"+uid).setAccessToken(myAccessToken).buildQueryMessage();
            OAuthResourceResponse resourceResponse = oAuthClient.resource(userInfoRequest, OAuth.HttpMethod.GET,OAuthResourceResponse.class);
            String username = resourceResponse.getBody();
            JsonParser jp = new JsonParser();
            //将json字符串转化成json对象
            JsonObject jo = jp.parse(username).getAsJsonObject();
            System.out.println("-------获取userbaseinfo-----------------"+ jo );
            JsonObject userinfo=jo.get("data").getAsJsonObject();
            String idCard=userinfo.get("idcard").getAsString();
            String mobile=userinfo.get("mobile").getAsString();
            String email="";
/*
            System.out.println("email=" + userinfo.get("email"));
            System.out.println(userinfo.get("email")!=null);
            System.out.println(userinfo.get("email").isJsonNull());
*/
            if(!userinfo.get("email").isJsonNull() ){
                email=userinfo.get("email").getAsString();
            }
            String gender=userinfo.get("gender").getAsString();

            String birthday=userinfo.get("birthday").getAsString();
            KsbmBaseInfoVo ksbaseinfovo=new KsbmBaseInfoVo();
            ksbaseinfovo.setIdCard(idCard);
            ksbaseinfovo.setMobile(mobile);
            ksbaseinfovo.setEmail(email);
            ksbaseinfovo.setBirthday(birthday);
            ksbaseinfovo.setGender(gender);
            //返回 调用登录方法
            return R.ok().put("ksbminfo",ksbaseinfovo);
        } catch (OAuthSystemException e) {
            e.printStackTrace();
        } catch (OAuthProblemException e) {
            e.printStackTrace();
        }
        return null;
    }





}
