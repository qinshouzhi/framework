/*
 * Copyright (c)  2014, Newtouch
 * All rights reserved. 
 * 
 * $id: UserRealm.java 9552 2014年12月29日 下午5:30:07 WangLijun$
 */
package com.newtouch.lion.web.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.newtouch.lion.model.system.User;
import com.newtouch.lion.service.system.UserService;
import com.newtouch.lion.web.shiro.authc.CredentialExpiredException;
import com.newtouch.lion.web.shiro.authc.ExpiredAccountException;
 

/**
 * <p>
 * Title: 用户登录授权
 * </p>
 * <p>
 * Description: 用户登录授权
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class UserRealm extends AuthorizingRealm {
	/** 用户Service */
	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		/** 用户名 */
		String userName = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(this.getRoles());
		authorizationInfo.setObjectPermissions(null);
		return authorizationInfo;
	}
	
	private Set<String> getRoles(){
		Set<String> roles=new HashSet<String>();
		roles.add("admin");
		roles.add("sales");
		roles.add("account");
		return roles;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String userName = (String)token.getPrincipal();
		User user = userService.doFindByUserName(userName);
		//没找到帐号
        if(user == null) {
            throw new UnknownAccountException();
        }
        //帐号锁定
        if(Boolean.TRUE.equals(user.getAccountLocked())){
            throw new LockedAccountException();
        }
        //账户有效期验证
        if(Boolean.TRUE.equals(user.getAccountExpired())){
        	throw new ExpiredAccountException();
        }
        //密码有效期验证
        if(Boolean.TRUE.equals(user.getCredentialExpired())){
        	throw new CredentialExpiredException();
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getUsername()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
	}

	
	public static void main(String[] args) {
		DefaultHashService hashService = new DefaultHashService(); //默认算法SHA-512
		hashService.setHashAlgorithmName("SHA-1");
		hashService.setPrivateSalt(new SimpleByteSource("wanglijun")); //私盐，默认无
		hashService.setGeneratePublicSalt(true);//是否生成公盐，默认false
		hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//用于生成公盐。默认就这个
		hashService.setHashIterations(1); //生成Hash值的迭代次数

		HashRequest request = new HashRequest.Builder()
		            .setAlgorithmName("SHA-1").setSource(ByteSource.Util.bytes("111aaa"))
		            .setSalt(ByteSource.Util.bytes("wanglijun")).setIterations(2).build();
		
		String hex = hashService.computeHash(request).toHex(); 
		System.out.println(hex);
		String simpleHash = new SimpleHash("SHA-1","111aaa",ByteSource.Util.bytes("wanglijun"),2).toString();
		System.out.println(simpleHash.equals("c150916baa97eeccd1d99541aad26170761b41a9"));
		//14c2f0f861d1bfc7b92bdf78355045f019141ec9
		
		//c150916baa97eeccd1d99541aad26170761b41a9
	}
}
