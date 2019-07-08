package com.lzqs.zhangyushu.common;

import org.springframework.stereotype.Component;

/**
 * Created by JinZhicheng on 2018/11/6 17:44
 */

@Component
public class ErrorMsg {

    /**
     * 操作类
     */
    public static final String ADD_FAILED = "添加失败";
    public static final String EDIT_FAILED = "修改失败";
    public static final String DELETE_FAILED = "删除失败";

    public static final String PARAMETER_IS_INVALID_MSG = "参数不合法";

    public static final String ID_NOT_NULL = "id不能为空";
    public static final String ID_IS_NOT_EXIST = "id不存在";

    public static final String TYPE_NOT_NULL = "类型不能为空";

    /**
     * 学院类
     */
    public static final String COLLEGE_IS_NOT_EXIST = "学院不存在";
    public static final String COLLEGE_ID_NOT_NULL = "学院id不能为空";
    public static final String COLLEGE_DESCRIBE_NOT_NULL = "学院描述不能为空";
    /**
     * 专业类
     */
    public static final String SPECIALTY_IS_NOT_EXIST = "专业不存在";
    public static final String SPECIALTY_ID_NOT_NULL = "专业id不能为空";
    public static final String SPECIALTY_DESCRIBE_NOT_NULL = "专业描述不能为空";

    /**
     * 人员类
     */
    public static final String STUDENT_ID_NOT_NULL = "学生id不能为空";


    public static final String ACCOUNT_ID_NOT_NULL = "人员id不能为空";


    public static final int ACCOUNT_ALREADY_EXISTS_CODE = 201;
    public static final String ACCOUNT_ALREADY_EXISTS_MSG = "账号已存在";

    public static final int ACCOUNT_DOES_NOT_EXIST_CODE = 202;
    public static final String ACCOUNT_DOES_NOT_EXIST_MSG = "账号不存在，或已被禁用";

    public static final int MOBILE_DOES_NOT_EXIST_CODE = 203;
    public static final String MOBILE_DOES_NOT_EXIST_MSG = "手机号不存在";


    public static final int ORDER_INSERT_ERROR_CODE = 209;
    public static final String ORDER_INSERT_ERROR_MSG = "生成订单失败";

    public static final int USER_UPDATE_ERROR_CODE = 210;
    public static final String USER_UPDATE_ERROR_MSG = "修改放映员信息失败";


    public static final int NOT_Login_CODE = 333;
    public static final String NOT_Login_MSG = "未登录,请登录";

    public static final int permissionDenied_CODE = 211;
    public static final String permissionDenied_MSG = "权限认证失败";

    public static final int PASSWORD_ERROR_CODE = 212;
    public static final String PASSWORD_ERROR_MSG = "密码错误";

    public static final int PASSWORD_IS_Different_CODE = 213;
    public static final String PASSWORD_IS_Different_MSG = "两次输入密码不一致";


    public static final int userNotExist_CODE = 214;
    public static final String userNotExist_MSG = "用户不存在";

    public static final int createAccountFail_CODE = 215;
    public static final String createAccountFail_MSG = "创建账号失败";

    public static final int ADMINACCOUNT_LOCALED_CODE = 216;
    public static final String ADMINACCOUNT_LOCALED_MSG = "账号被锁定";

    public static final int ADD_AdminAccount_ERROR_CODE = 217;
    public static final String ADD_AdminAccount_ERROR_MSG = "添加用户失败";


    public static final int ADD_ROLE_ERROR_EXIST_CODE = 218;
    public static final String ADD_ROLE_ERROR_EXIST_MSG = "角色已存在";

    public static final int roleNotExist_CODE = 219;
    public static final String roleNotExist_MSG = "角色不存在";

    public static final int UPDATE_AdminAccount_ERROR_CODE = 220;
    public static final String UPDATE_AdminAccount_ERROR_MSG = "更新用户失败";


    public static final int UPDATE_ROLE_ERROR_CODE = 221;
    public static final String UPDATE_ROLE_ERROR_MSG = "更新角色失败";

    public static final int ADD_ROLE_ERROR_CODE = 222;
    public static final String ADD_ROLE_ERROR_MSG = "添加角色失败";

    public static final int SYSTEM_Error_CODE = 223;
    public static final String SYSTEM_Error_MSG = "系统异常";

    public static final int LOGIN_FAIL_CODE = 224;
    public static final String LOGIN_FAIL_MSG = "登录失败";

    public static final int DOWNORDER_SHOW_ERROR_CODE = 300;
    public static final String DOWNORDER_SHOW_ERROR_MSG = "显示下载订单失败";

    public static final int DOWNLOADSTATUS_UPDATE_ERROR_CODE = 301;
    public static final String DOWNLOADSTATUS_UPDATE_ERROR_MSG = "更改下载订单状态失败";

    public static final int SHOW_MOVIES_ERROR_CODE = 302;
    public static final String SHOW_MOVIES_ERROR_MSG = "显示电影信息失败";

    public static final int ORDER_CANCEL_ERROR_CODE = 303;
    public static final String ORDER_CANCEL_ERROR_MSG = "取消订单操作错误";

    public static final int TOTAL_SHOW_ERROR_CODE = 304;
    public static final String TOTAL_SHOW_ERROR_MSG = "返回总额错误";

    public static final int ORDER_SHOW_ERROR_CODE = 305;
    public static final String ORDER_SHOW_ERROR_MSG = "显示订单失败";

    public static final int QRCODE_GENERATE_ERROR_CODE = 306;
    public static final String QRCODE_GENERATE_ERROR_MSG = "生成二维码败";

    public static final int USER_LOGIN_ERROR_CODE = 307;
    public static final String USER_LOGIN_ERROR_MSG = "用户登录错误";

    public static final int USER_ADD_ERROR_CODE = 308;
    public static final String USER_ADD_ERROR_MSG = "添加用户失败";

    public static final int USER_SELECT_ERROR_CODE = 309;
    public static final String USER_SELECT_ERROR_MSG = "查询用户错误";

    /**
     * sign错误
     */
    public static final int SIGN_ERROR_CODE = 310;
    public static final String SIGN_ERROR_MSG = "发送验证码失败";

    public static final int INCORRECT_VERIFICATION_CODE = 312;
    public static final String INCORRECT_VERIFICATION_MSG = "验证码输入不正确,请等待60s重新输入";

}
