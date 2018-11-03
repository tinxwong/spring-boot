package com.tinx.java.common.response.status;


import com.tinx.java.common.response.status.interfaces.StatusCode;

/**
 * 全局错误码
 */
public enum ResponseCode implements StatusCode {
    SUCCESS (0, "操作成功"),
    ERROR(1, "操作失败"),
    EXCEPTION(3, "异常"),
    INVALID_PARAM(4, "参数错误"),
    UNKNOWN_EXCEPTION('썑', "未知异常"),
    NETWORK_EXCEPTION('썒', "网络异常"),
    TIMEOUT_EXCEPTION('썓', "请求超时"),
    BIZ_EXCEPTION('썔', "业务异常"),
    FORBIDDEN_EXCEPTION('썕', "请求禁止"),
    SERIALIZATION_EXCEPTION('썖', "序列化异常"),
    REMOTING_EXCEPTION('썗', "远程通信异常"),
    UPLOAD_FILE_EXCEPTION('\uea61', "文件上传失败"),
    DELETE_FILE_EXCEPTION('\uea62', "文件删除失败"),
    DOWNLOAD_FILE_EXCEPTION('\uea63', "文件下载失败"),
    TOKEN_EXPIRED_OR_INVALID('\uea64', "访问令牌过期或无效"),
    DECRYPT_ERROR(1001, "解密失败"),
    USER_NOT_EXIST(1002, "用户不存在"),


    PARAMS_INVALID(10000001, "参数不正确"),
    PARAMS_NOT_NULL(10000002, "参数不能为空"),
    PARAMS_MODULE_ID_NOT_NULL(10000003, "moduleId不能为空"),
    PARAMS_MODULE_NAME_NOT_NULL(10000004, "moduleName不能为空"),
    PARAMS_SYS_NAME_NOT_NULL(10000005, "sysName不能为空"),
    PARAMS_UID_NOT_NULL(10000006, "uid不能为空"),
    PARAMS_ID_NOT_NULL(10000007, "id不能为空"),
    PARAMS_DATA_ATTRIBUTION_NAME_NOT_NULL(10000008, "字典归属不能为空"),
    PARAMS_DATA_ATTRIBUTION_ID_NOT_NULL(10000009, "字典归属id不能为空"),
    PARAMS_DATA_ATTRIBUTION_CODE_NOT_NULL(10000010, "字典归属code不能为空"),
    PARAMS_DATA_DICTIONARY_NAME_NOT_NULL(10000011, "数据字典值不能为空"),
    PARAMS_DATA_DICTIONARY_CODE_NOT_NULL(10000012, "数据字典code值不能为空"),
    PARAMS_AREA_ID_NOT_NULL(10000013, "地区id不能为空"),
    PARAMS_AREA_CODE_NOT_NULL(10000014, "地区code不能为空"),
    PARAMS_AREA_NAME_NOT_NULL(10000015, "地区name不能为空"),
    PARAMS_AREA_LEVEL_NOT_NULL(10000016, "地区level不能为空"),
    PARAMS_AREA_PARENT_ID_NOT_NULL(10000017, "地区父地区id不能为空"),
    LOKC_CAN_NOT_APPLY_AT_THE_SAME_TIME(10000018, "用户操作中，请勿重复操作"),
    NULL_DEPT(10000101, "此部门不存在"),
    NULL_EMP(10000102, "此职员不存在"),
    USER_NOT_VOTE(10000103, "没有收到投票邀请"),
    USER_NOT_VOTE_NUM(10000104, "当前周期内的投票次数已用完"),
    MUC_USER_CREATE_FAILED(10000105, "推送muc失败"),


    TOKEN_ANALYSIS_ERROR(10000200, "token解析错误"),
    TOKEN_ISNULL(10000201, "token为空"),
    USER_NOT_JOIN_VOTE(10000105, "用户没有可以参与的投票"),
    USER_NOT_JOINED_VOTE(10000106, "用户没有参与过投票"),
    APPKEY_NOT_NULL(10000107, "appKey不能为空"),
    VOTEID_NOT_NULL(10000108, "voteId不能为空"),
    USERID_NOT_NULL(10000109, "userId不能为空"),
    OPTION_USER_NULL(10000110, "该选项下没有用户选择"),
    POSITION_CHECK_IN_ID_NOT_NULL(10000111, "id不能为空"),
    SCENE_TYPE_NOT_NULL(10000112, "场景类型不能为空"),
    SCENE_ID_NOT_NULL(10000112, "场景ID不能为空"),
    SIGN_CHANGED(10000113, "证书被修改"),
    REPEAT_JOIN(10000114, "请勿重复报名"),
    REPEAT_CHECKIN(10000115, "请勿重复签到"),
    POSITION_CHECKIN_ID_NOT_NULL(10000116, "positionCheckinId不能为空"),
    POSITION_CHECKIN_FAIL(10000116, "签到失败，系统错误"),
    //推送code 10000200 -10000500 start
    PUSH_MX_METINFO_ERROR(10000200, "推送到美信服务号异常"),
    PUSH_MX_IMAGETEXT_ERROR(10000201, "调用服务号单图文推送异常"),
    PUSH_MX_SERVER_EXIST_ERROR(10000202, "服务号已经推送过此投票"),
    PUSH_ERROR(10000203, "推送错误"),
    USERNAME_UNIQUE_ERROR(10000204, "用户名已存在"),
    USER_LOTTERY_EXIST(10000204, "用户已关联该网站"),
    //推送code 10000200 -10000500 end
    ;

    public int getCode() {
        return errorCode;
    }

    public String getMsg() {
        return errorMsg;
    }


    ResponseCode(int code, String msg) {
        errorCode = code;
        errorMsg = msg;
    }



    private int errorCode;
    private String errorMsg;

    @Override
    public int code() {
        return errorCode;
    }

    @Override
    public String msg() {
        return errorMsg;
    }

}
