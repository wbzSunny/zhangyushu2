//package com.lzqs.zhangyushu.config;
//
//import com.lzqs.acbsp.entity.Account;
//import com.lzqs.acbsp.entity.AccountPerm;
//import com.lzqs.acbsp.entity.AccountRole;
//import com.lzqs.acbsp.service.AccountPermService;
//import com.lzqs.acbsp.service.AccountRoleService;
//import com.lzqs.acbsp.service.AccountService;
//import org.apache.shiro.authz.AuthorizationException;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//
//import javax.annotation.Resource;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
///**
// * 这个类是参照JDBCRealm写的，主要是自定义了如何查询用户信息，如何查询用户的角色和权限，如何校验密码等逻辑
// */
//public class CustomRealm extends AuthorizingRealm {
//
//    @Resource
//    private AccountService accountService;
//    @Resource
//    private AccountRoleService roleService;
//    @Resource
//    private AccountPermService permService;
//
//
//    /**
//     * 定义如何获取用户的角色和权限的逻辑，给shiro做权限判断
//     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        //null usernames are invalid
//        if (principals == null) {
//            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
//        }
//
//        Account account = (Account) getAvailablePrincipal(principals);
//
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setRoles(account.getRoles());
//        info.setStringPermissions(account.getPerms());
//        return info;
//    }
//
//    /**
//     * 定义如何获取用户信息的业务逻辑，给shiro做登录
//     *
//     * @param authenticationToken
//     * @return
//     * @throws AuthenticationException
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        String account = token.getUsername();
//        String password = String.valueOf(token.getPassword());
//        // Null username is invalid
//        if (account == null) {
//            throw new AccountException("Null usernames are not allowed by this realm.");
//        }
//        Account adminDB = accountService.getUserByAccount(account);
//
//        if (adminDB == null) {
//            throw new UnknownAccountException("No account found for admin [" + account + "]");
//        }
//
//        // 查询用户的角色和权限存到SimpleAuthenticationInfo中，这样在其它地方
//        // SecurityUtils.getSubject().getPrincipal() 就能拿出用户的所有信息，包括角色和权限
//        AccountRole accountRole = roleService.getById(adminDB.getRoleId());
//        Set<String> roles = new HashSet<>();
//        roles.add(accountRole.getRoleName());
//        List<AccountPerm> permList = permService.getPermListByRoleId(adminDB.getRoleId());
//        Set<String> perms = new HashSet<>();
//        for (AccountPerm perm : permList) {
//            perms.add(perm.getPermName());
//        }
//        adminDB.setRoles(roles);
//        adminDB.setPerms(perms);
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(adminDB, adminDB.getPassword(), getName());
//        return info;
//    }
//
//}