package com.example.shiro.shiro;

import com.example.shiro.entity.User;
import com.example.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("--回调了-----doGetAuthorizationInfo(授权)--方法---------");
		User user = (User) principals.getPrimaryPrincipal();
		       // List<String> permissions = new ArrayList<String>();
		        //Set<String> roles = new HashSet<String>();
		         // 查到权限数据，返回授权信息(要包括 上边的permissions)
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		         // 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
		Set<String> roles = new LinkedHashSet<>();
		user = userService.getUserByName(user.getName());
		roles.add(user.getRolename());
		authorizationInfo.setRoles(roles);
		        // authorizationInfo.setStringPermissions(loginService.findPermissions(logincode));  
		         //roles.add("user");
		         //authorizationInfo.addRoles(roles);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		System.out.println("--回调了-----doGetAuthenticationInfo（认证）--方法---------");

		String username = (String) token.getPrincipal();
//        System.out.println(token.getCredentials());
		//通过username从数据库中查找 User对象，如果找到，没找到.
		//实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		User userInfo = userService.getUserByName(username);
		if (userInfo == null) {
			return null;
		}

		//userInfo用户，ByteSource.Util.bytes(userInfo.getName())盐值为用户名
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				userInfo,
				userInfo.getPassword(),
				ByteSource.Util.bytes(userInfo.getName()),
				getName()
		);
		return authenticationInfo;

	}

}