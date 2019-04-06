package com.yuepang.yuepang.control;

import android.text.TextUtils;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.activity.BaseActivity;

import java.io.UnsupportedEncodingException;

public class CheckManage {


    /**
     * 提供静态方法用于检查表单格式数据 /
     */

    /**
     * 检查登录和密码的格式
     *
     * @param loginName 登录名
     * @param pwd       密码
     * @return true 用户名密码通过检查； false用户名密码未通过检查
     */
    public static boolean checklogin(String loginName, String pwd, BaseActivity mActivity) {
        if (TextUtils.isEmpty(loginName)) {
            mActivity.showToastSafe("请输入账号");
            return false;
        }
        for (char c : "'\"%,<>".toCharArray()) {
            if (loginName.contains(c + "")) {
                mActivity.showToastSafe(mActivity.getString(R.string.special_string));
                return false;
            }
        }
        if (TextUtils.isEmpty(pwd)) {
            mActivity.showToastSafe("请输入密码");
            return false;
        }
        return true;
    }

    /**
     * 检查手机号
     *
     * @param telNum
     * @return
     */
    public static boolean checkTel(String telNum, BaseActivity mActivity) {
        if (TextUtils.isEmpty(telNum)) {
            mActivity.showToastSafe("请输入手机号");
            return false;
        } else if ((!telNum.startsWith("1")) || (telNum.length() != 11)) {
            mActivity.showToastSafe("手机号格式不正确");
            return false;
        }
        return true;
    }

    public static boolean checkPwd(String pwd1, String pwd2, BaseActivity page) {
        if (TextUtils.isEmpty(pwd1)) {
            page.showToastSafe("请输入密码");
            return false;
        }
        if (TextUtils.isEmpty(pwd2)) {
            page.showToastSafe("请输入密码");
            return false;
        }

        if (pwd1.getBytes().length < 6 || pwd1.getBytes().length > 16) {
            page.showToastSafe("请输入6-16位新密码");
            return false;
        }
        if (!pwd1.equals(pwd2)) {
            page.showToastSafe("两次新密码输入不一致");
            return false;
        }
        return true;
    }


    /**
     * 重置密码的表单校验方法
     */
    public static boolean resetPwd(String tel, String code, String pwd1, String pwd2, BaseActivity activity) {
        return checkPwd(pwd1, pwd2, activity) && checkTelRg(tel, code, pwd1, activity);
    }


    /**
     * 手机注册表单检查
     *
     * @param tel
     * @param password
     * @param code
     * @param activity
     * @return
     */
    public static boolean checkTelRg(String tel, String password, String code, BaseActivity activity) {
        if (!checkTel(tel, activity)) {
            return false;
        }
        if (!checkCode(code, activity)) {
            return false;
        }
        return checkPwd(password, activity);
    }


    /**
     * 检查验证码
     *
     * @param code
     * @return
     */
    public static boolean checkCode(String code, BaseActivity mActivity) {
        if (TextUtils.isEmpty(code)) {
            mActivity.showToastSafe("请输入验证码");
            return false;
        }
        if (code.length() != 6) {
            mActivity.showToastSafe("验证码格式不正确");
            return false;
        }
        return true;
    }


    /**
     * 检查密码格式是否正确
     *
     * @param pwd1
     * @return
     */
    public static boolean checkPwd(String pwd1, BaseActivity mActivity) {
        if (TextUtils.isEmpty(pwd1)) {
            mActivity.showToastSafe("请输入密码");
            return false;
        }

        for (char c : "%,<>".toCharArray()) {
            if (pwd1.contains(c + "")) {
                mActivity.showToastSafe(mActivity.getString(R.string.special_string));
                return false;
            }
        }
        if (pwd1.getBytes().length < 6 || pwd1.getBytes().length > 16) {
            mActivity.showToastSafe("密码为6-16位字符");
            return false;
        }
        return true;
    }



    /**
     * 判断密码长度，密码位数为6-16
     */
    public static boolean checkPwd(String pwd) {
        return checkSize(pwd, 16, 6);
    }
//
//    /**
//     * 判断昵称长度，密码位数为1-16
//     */
//    public static boolean checkNick(String pwd) {
//        return checkSize(pwd, 16, 1);
//    }
//
    /**
     * 判断一个字符串的区间
     *
     * @param string 所需判断的字符串
     * @param max    最大长度
     * @param min    最小长度
     */
    public static boolean checkSize(String string, int max, int min) {
        if (TextUtils.isEmpty(string) || min > max || min < 0) {// 如果判断值为空或者最小值 大于最大 则返回为空
            return false;
        }
        return !(getLength(string) > max || getLength(string) < min);
    }
//
    /**
     * 获得字符串的长度
     */
    public static int getLength(String string) {
        if (TextUtils.isEmpty(string)) {
            return 0;
        } else {
            try {
                return string.getBytes("GB2312").length;
            } catch (UnsupportedEncodingException e) {
                LogUtils.e(e);
                return 0;
            }
        }
    }
//
//
//    /**
//     * 绑定手机表单检查
//     *
//     * @param tel
//     * @param code
//     * @param mActivity
//     * @return
//     */
//    public static boolean bindTelCheck(String tel, String code, ActionBarPage mActivity) {
//        if (!checkTel(tel, mActivity)) {
//            return false;
//        }
//        return checkCode(code, mActivity);
//    }
//

//
//    /**
//     * 手机注册表单检查
//     *
//     * @param tel
//     * @param password
//     * @param code
//     * @param activity
//     * @return
//     */
//    public static boolean checkTelRg(String tel, String password, String code, ActionBarPage activity, boolean isgetCode) {
//        if (!checkTel(tel, activity)) {
//            return false;
//        }
//        if (!isgetCode) {
//            activity.showToastSafe("请先获取验证码");
//            return false;
//        }
//
//        if (!checkCode(code, activity)) {
//            return false;
//        }
//        return checkPwd(password, activity);
//    }
//
//
//    /**
//     * 手机注册表单检查
//     *
//     * @param tel
//     * @param password
//     * @param code
//     * @param activity
//     * @return
//     */
//    public static String checkTelRgSt(String tel, String password, String code, ActionBarPage activity,
//                                      boolean isgetCode) {
//        if (TextUtils.isEmpty(tel)) {
//            activity.showToastSafe("请输入手机号", 0);
//            return "请输入手机号";
//        } else if ((!tel.startsWith("1")) || (tel.length() != 11)) {
//            activity.showToastSafe("手机号格式不正确", 0);
//            return "手机号格式不正确";
//        }
//
//        if (!isgetCode) {
//            activity.showToastSafe("请先获取验证码");
//            return "请先获取验证码";
//        }
//
//        if (TextUtils.isEmpty(code)) {
//            activity.showToastSafe("请输入验证码");
//            return "请输入验证码";
//        }
//        if (code.length() != 6) {
//            activity.showToastSafe("验证码格式不正确");
//            return "请输入验证码";
//        }
//        if (TextUtils.isEmpty(password)) {
//            activity.showToastSafe("请输入密码");
//            return "请输入密码";
//        }
//
//        for (char c : "%,<>".toCharArray()) {
//            if (password.contains(c + "")) {
//                activity.showToastSafe(activity.getString(R.string.anzhi_user_pwd_error2), 0);
//                return activity.getString(R.string.anzhi_user_pwd_error2);
//            }
//        }
//        if (password.getBytes().length < 6 || password.getBytes().length > 16) {
//            activity.showToastSafe("密码为6-16位字符");
//            return "密码为6-16位字符";
//        }
//        return null;
//    }
//
//    /**
//     * 用户名注册表单检查
//     *
//     * @param loginName
//     * @param pwd
//     * @param mActivity
//     * @return
//     */
//    public static boolean checkNameRg(String loginName, String pwd, ActionBarPage mActivity) {
//        if (TextUtils.isEmpty(loginName)) {
//            mActivity.showToastSafe("请输入用户名", 0);
//            return false;
//        }
//        if (TextUtils.isEmpty(pwd)) {
//            mActivity.showToastSafe("请输入密码", 0);
//            return false;
//        }
//
//        if (loginName.getBytes().length < 6 || loginName.getBytes().length > 16) {
//            mActivity.showToastSafe("用户名为6-16位字母或数字组合", 0);
//            return false;
//        }
//
//        for (char c : "*%,”<>".toCharArray()) {
//            if (loginName.contains(c + "")) {
//                mActivity.showToastSafe("用户名为6-16位字母或数字组合", 0);
//                return false;
//            }
//        }
//
//        if (pwd.getBytes().length < 6 || pwd.getBytes().length > 16) {
//            mActivity.showToastSafe("密码为6-16位字符", 0);
//            return false;
//        }
//
//        return true;
//    }
//
//    /**
//     * 用户名注册表单检查 当返回值为空时表示通过检查
//     *
//     * @param loginName
//     * @param pwd
//     * @param mActivity
//     * @return
//     */
//    public static String checkNameRgSt(String loginName, String pwd, ActionBarPage mActivity) {
//        if (TextUtils.isEmpty(loginName)) {
//            mActivity.showToastSafe("请输入用户名", 0);
//            return "请输入用户名";
//        }
//        if (TextUtils.isEmpty(pwd)) {
//            mActivity.showToastSafe("请输入密码", 0);
//            return "请输入密码";
//        }
//
//        if (loginName.getBytes().length < 6 || loginName.getBytes().length > 16) {
//            mActivity.showToastSafe("用户名为6-16位字母或数字组合", 0);
//            return "用户名为6-16位字母或数字组合";
//        }
//
//        for (char c : "*%,”<>".toCharArray()) {
//            if (loginName.contains(c + "")) {
//                mActivity.showToastSafe("用户名为6-16位字母或数字组合", 0);
//                return "用户名为6-16位字母或数字组合";
//            }
//        }
//
//        if (pwd.getBytes().length < 6 || pwd.getBytes().length > 16) {
//            mActivity.showToastSafe("密码为6-16位字符", 0);
//            return "密码为6-16位字符";
//        }
//        return null;
//    }
//


//
//    /**
//     * 判断url 是否为论坛的
//     */
//    public static boolean checkUrlToBss(Context mContext, String url) {
//        if (TextUtils.isEmpty(url)) {
//            return false;
//        }
//        String urls = DataControl.getInstance(mContext).getBbsUrls();
//        if (TextUtils.isEmpty(urls)) {
//            return false;
//        }
//        try {
//            JSONArray array = new JSONArray(urls);
//            if (array != null && array.length() > 0) {
//                for (int i = 0; i < array.length(); i++) {
//                    if (url.contains(array.getString(i))) {
//                        return true;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            return false;
//        }
//        return false;
//    }
//
//    public static boolean checkWeixin(ActionBarPage mActivity) {
//        if (!AppManager.getInstance(mActivity.getActivity()).isInstalled("com.tencent.mm")) {
//            mActivity.showToastSafe("您尚未安装微信，请安装后重试", 0);
//            return false;
//        }
//
//        if (!AppManager.getInstance(mActivity.getActivity()).isInstalled("com.tencent.mm", 520, true)) {
//            mActivity.showToastSafe("您安装的微信版本过低", 0);
//            return false;
//        }
//        return true;
//    }
//
//    public static boolean checkQQ(ActionBarPage mActivity) {
//        if (AppManager.getInstance(mActivity.getActivity()).isInstalled("com.tencent.mobileqq")
//                || AppManager.getInstance(mActivity.getActivity()).isInstalled("com.tencent.minihd.qq")) {
//            return true;
//        } else {
//            mActivity.showToastSafe("您尚未安装QQ，请安装后重试", 0);
//            return false;
//        }
//
//    }
//
//    public static boolean checkAliPay(ActionBarPage mActivity) {
//
//        if (AppManager.getInstance(mActivity.getActivity()).isInstalled("com.eg.android.AlipayGphone")
//                || AppManager.getInstance(mActivity.getActivity()).isInstalled("com.eg.android.AlipayGphone")) {
//            return true;
//        } else {
////            mActivity.showToastSafe("您尚未安装支付宝，请安装后重试", 0);
//            return false;
//        }
//    }
//
//    public static String CheckUrl(String url) {
//        if (TextUtils.isEmpty(url)) {
//            return null;
//        }
//
//        if (url.startsWith("http://") || url.startsWith("https://")) {
//            return url;
//        }
//
//        return null;
//    }
//
//    public static boolean checkpwd(char c) {
//        String digits = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//        if (TextUtils.isEmpty(c + "")) {
//            return false;
//        }
//
//        for (char c1 : digits.toCharArray()) {
//            if (c1 == c) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    public static boolean checkRealName(String realName, ActionBarPage mActivity) {
//        if (realName.isEmpty()) {
//            mActivity.showToastSafe("请输入您身份证上的对应姓名");
//            return false;
//        }
//        if (realName.length() < 2) {
//            mActivity.showToastSafe("身份证认证失败，请输入正确信息");
//            return false;
//        }
//        return true;
//
//    }
//
//    public static boolean checkIdentityCode(String identityCode, ActionBarPage mActivity) {
//        if (identityCode.isEmpty()) {
//            mActivity.showToastSafe("请输入您的18位身份证号码");
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * 判断是否字母或者数字的组合
//     *
//     * @param key
//     * @return
//     */
//    public static boolean checkGiftCertificateString(String key) {
//        return key != null && key.matches("[A-Za-z0-9]+");
//    }
}
