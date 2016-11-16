/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.common;

/**
 * <p>系统常量类。<p>
 * 
 * 创建日期 2013-7-27<br>
 * @author li_ming<br>
 */
public class Constants {
	// 用户默认密码(123456使用des加密)
	public static final String USER_DEFAULT_PWD = "lTXhvSj4mzk=";
	// 用户默认密码(123456使用Md5加密)
	public static final String MANAGER_DEFAULT_PWD = "e10adc3949ba59abbe56e057f20f883e";
	//登录名验证
	public static final String USER_LOGIN_REGEX = "[A-Za-z0-9_]+";
	//显示名验证
	public static final String USER_DISPLAYNAME_REGEX = "[a-zA-Z0-9\u4e00-\u9fa5_]+";
	//手机号验证
	public static final String USER_PHONE_NUMBER_REGEX = "[0-9]+";
	
	/** 版本 **/
	// 通用
	public static final String OEM_COMMON = "0";
	// 公安
	public static final String OEM_GONGAN = "1";
	
	/** 数据 **/
	// 所属运营商
	public static final String[] DATA_SERVICE_PROVIDER = new String[] { "移动", "联通", "电信", "其他" };
	
	/** 异常相关常量 **/
	// 数据库操作异常
	public static final String ERROR_DATA_OPERATE = "error_data_operate";
	// 版权文件被篡改
	public static final String ERROR_LICENSE_TAMPERED = "error_license_tampered";
	// 非法的主机
	public static final String ERROR_LICENSE_ILLEGAL_HOST = "error_license_illegal_host";
	// 版权文件已过期
	public static final String ERROR_LICENSE_EXPIRED = "error_license_expired";
	// 版权文件不存在
	public static final String ERROR_LICENSE_NOT_EXIST = "error_license_not_exist";
	// 用户数量超过限制
	public static final String ERROR_USER_NUM_OVER_LIMIT = "error_user_num_over_limit";
	//版权文件错误，版权验证失败(通用版权错误提示)
	public static final String ERROR_LICENCE_COMMON_ERROR = "error_licence_common_error";
	// 用户已存在
	public static final String ERROR_USER_ALREADY_EXISTED = "error_user_already_existed";
	// 用户已存在
	public static final String ERROR_USER_ALREADY_EXISTED_EN = "error_user_already_existed_en";
	// 用户组已存在
	public static final String ERROR_USER_GROUP_ALREADY_EXISTED = "error_user_group_already_existed";
	// 应用已存在
	public static final String ERROR_APP_ALREADY_EXISTED = "error_app_already_existed";
	// 名称重复
	public static final String ERROR_DUPLICATE_NAME = "error_duplicate_name";
	// 角色已经存在
	public static final String ERROR_ROLE_ALREADY_EXISTED = "error_role_already_existed";
	// 应用组已存在
	public static final String ERROR_APP_GROUP_ALREADY_EXISTED = "error_app_group_already_existed";
	// 部门已存在
	public static final String ERROR_DEPT_ALREADY_EXISTED = "error_dept_already_existed";
	// 加密错误
	public static final String ERROR_MESSAGE_ENCRYPT = "error_message_encrypt";
	// 用户存储目录创建失败
	public static final String ERROR_USER_STORAGE_CREATE_ERRORS = "error_user_storage_create_errors";
	// 密码重置失败
	public static final String ERROR_USER_PWD_RESET_ERRORS = "error_user_pwd_reset_errors";
	// 图标文件过大
	public static final String ERROR_ICON_FILE_TOO_LARGE = "error_icon_file_too_large";
	// 加密出错
	public static final String ERROR_ENCRYPT = "error_encrypt";
	//存在同名部门
	public static final String ERROR_DEPT_SAME_NAME_DEPT = "error_dept_same_name_dept";
	//部门名称与同一级别内的其他部门冲突
	public static final String ERROR_DEPT_SAME_NAME_WITHIN_DEPT = "error_dept_same_name_within_dept";
	//部门名称与新部门的子部门冲突
	public static final String ERROR_DEPT_SAME_NAME_IN_NEW_DEPT = "error_dept_same_name_in_new_dept";
	//不能删除顶级部门
	public static final String ERROR_DEPT_ROOT_DEPT = "error_dept_root_dept";
	//不能添加顶级部门
	public static final String ERROR_DEPT_ROOT_ALREADY_EXISTED = "error_dept_root_dept_already_existed";
	//登录名格式错误
	public static final String ERROR_LOGIN_FORMAT= "error_login_format";
	//导入excel中用户错误:标题行被修改,正确的顺序是: '登录名	显示名	部门	角色	是否与设备绑定	手机号'
	public static final String ERROR_EXCEL_TITLE= "error_excel_title";
	//导入excel中用户错误:文件中没有有效数据
	public static final String ERROR_FILE_EMPTY= "error_excel_empty_valid_rows";
	//导入excel中用户错误:登录名格式
	public static final String ERROR_LOGIN_EXCEL_EB = "error_login_excel_eb"; 
	//导入excel中用户错误:登录名不是1-16位字符
	public static final String ERROR_LOGIN_EXCEL_ONE_SIXTH = "error_login_excel_one_sixth";
	//导入excel中用户错误:登录名在数据库中已经存在
	public static final String ERROR_LOGIN_EXCEL_EXISTED_DB = "error_login_excel_existed_db";
	//组织机构名称在数据库中已经存在
	public static final String ERROR_ORGANIZATION_EXCEL_EXISTED_DB = "error_organization_excel_existed_db";
	//导入excel中用户错误:登录名为空
	public static final String ERROR_LOGIN_EXCEL_EMPTY = "error_login_excel_empty";
	//导入excel中用户错误:显示名为空
	public static final String ERROR_DISPLAY_NAME_EXCEL_EMPTY = "error_display_name_excel_empty";
	//导入excel中用户错误:显示名不是 1-16位字母、数字、下划线或汉字
	public static final String ERROR_DISPLAY_NAME_EXCEL_REGEX = "error_display_name_excel_regex";
	//导入excel中用户错误:部门不存在
	public static final String ERROR_DEPT_EXCEL_NOT_EXISTED_DB = "error_dept_excel_not_existed_db";
	//导入excel中用户错误:角色不存在
	public static final String ERROR_ROLE_EXCEL_NOT_EXISTED_DB = "error_role_excel_not_existed_db";
	//导入excel中用户错误:'是否绑定'填写错误
	public static final String ERROR_BINDABLE_EXCEL_FORMAT = "error_bindable_excel_format";
	//导入excel中用户错误:手机号填写错误
	public static final String ERROR_PHONE_NUMBER_EXCEL_FORMAT = "error_phone_number_excel_format";
	//导入excel中用户错误:手机号填写错误
	public static final String ERROR_PHONE_NUMBER_EXCEL_DIGITS = "error_phone_number_excel_digits";
	//读取excel错误:未找到工作表
	public static final String ERROR_EXCEL_SHEET_NOT_FOUND = "error_excel_sheet_not_found";
	//读取excel错误:登录名重复
	public static final String ERROR_EXCEL_DUPLICATED_LOGINS_IN_EXCEL = "error_excel_duplicated_logins_in_excel";
	// 连接超时
	public static final String ERROR_TEST_MAIL_PARAM_TIMEOUT = "error_test_mail_param_timeout";
	// 认证失败
	public static final String ERROR_TEST_MAIL_PARAM_AUTHFAILED = "error_test_mail_param_authfailed";
	// 测试失败
	public static final String ERROR_TEST_MAIL_PARAM_FAILED = "error_test_mail_param_failed";
	//终端已经存在
	public static final String ERROR_TERMINAL_ALREADY_EXISTED = "error_terminal_already_existed";
	//备份文件不正确
	public static final String ERROR_BACKUP_FILE_ERROR= "error_backup_file_error";
	//备份名称重复
	public static final String ERROR_BACKUP_NAME_EXISTED_ERROR = "error_backup_name_existed_error";
	
	/** Web相关常量 **/
	// 默认每页显示的数据条数
	public static final int DEFAULT_PAGE_SIZE = 20;
	// 图片不存在时候,设置的默认图标的名字
	public static final String DEFAULT_IMG_NAME = "default-app";

	/** ldap相关常量 **/
	public static final String LDAP_CN = "CN";
	public static final String LDAP_TOP = "top";
	public static final String LDAP_PERSON = "person";
	public static final String LDAP_GROUP = "group";
	public static final String LDAP_USER = "user";
	public static final String LDAP_ORGANIZATIONAL_PERSON = "organizationalPerson";
	// ldap用户操作属性：包含成员
	public static final String LDAP_MEMBER = "member";
	// ldap用户操作属性：属于成员
	public static final String LDAP_MEMBER_OF = "memberOf";
	// ldap用户操作属性：对象类型
	public static final String LDAP_OBJECTCLASS = "objectclass";
	// ldap用户操作属性：用户标识（唯一）
	public static final String LDAP_SAM_ACCOUNT_NAME = "sAMAccountName";
	// ldap用户属性：显示名
	public static final String LDAP_DISPLAY_NAME = "displayName";
	// ldap用户属性：密码
	public static String LDAP_UNICODE_PWD = "unicodePwd";

	public static long MAX_LICENSE_SIZE = 9999999999L;
	// ldap用户属性参数：默认密码
	public static final String LDAP_DEFAULT_PWD = "123456";
	// ldap用户属性：描述
	public static final String LDAP_DISCRIPTION = "description";
	// ldap用户属性：账户权限
	public static final String LDAP_USER_ACCOUNT_CONTROL = "userAccountControl";
	// ldap用户属性：存储路径盘符，其值如：Y:
	public static final String LDAP_HOME_DRIVE = "homeDrive";
	// ldap用户属性：存储路径，其值如：//127.0.0.1/files
	public static final String LDAP_HOME_DIRECTORY = "homeDirectory";
	// ldap用户属性：用户信息文件路径，其值如：//127.0.0.1/profiles
	public static final String LDAP_PROFILE_PATH = "profilePath";
	public static final String LDAP_USER_ACCOUNT_CONTROL_VALUE = "66048";
	// ldap用户组：指定的域管理员
	public static final String LDAP_DOMAIN_ADMINS = "Domain Admins";
	// ldap用户组：所有域用户
	public static final String LDAP_DOMAIN_USERS = "Domain Users";
	// 用户最大空闲时间
	public static final String LDAP_MSTSMAXIDLETIME = "lDAPServerIdleTimeout";
	// 用户查询过滤器
	public static final String SEARCH_USER_FILTER = "(objectClass=person)";
	// 用户组查询过滤器
	public static final String SEARCH_USERGROUP_FILTER = "(objectclass=group)";

	/** liaison相关常量 **/
	// 应用组类型
	public static final String LIAISON_APPLICATION_GROUP = "AppsGroup";
	// 代理类型
	public static final String LIAISON_APPLICATION_SERVER = "ApplicationServer";
	// 应用组与用户组映射关系
	public static final String LIAISON_USERSGROUP_APPLICATIONSGROUP = "UsersGroupApplicationsGroup";
	// 应用类型
	public static final String LIAISON_APPLICATION_MIMIE_TYPE = "ApplicationMimeType";
	// 版权证书 内容类型-html
	public static final String CONTENT_TYPE_ORIGINAL = "application/octet-stream";
	// 记录用户组与应用组关联关系时组成用户组描述
	public static final String LIAISON_LDAP_STATIC = "static_";
	// 系统默认ip
	public static final String SYSTEM_DEFAULT_IP = "127.0.0.1";

	/** 应用相关常量 **/
	// windows类型应用
	public static final String APP_TYPE_WINDOWS = "windows";
	// linux类型应用
	public static final String APP_TYPE_LINUX = "linux";
	// native类型应用
	public static final String APP_TYPE_NATIVE = "native";
	// 应用发布状态-未发布
	public static final int APP_INPUBLISHED = 0;
	// 应用发布状态-已发布
	public static final int APP_PUBLISHED = 1;
	// 非静态应用
	public static final int APP_INSTATIC = 0;
	// 静态应用
	public static final int APP_STATIC = 1;
	// 本地应用
	public static final int APP_NATIVE = 2;
	// WEB应用
	public static final int APP_WEB = 3;
	// 应用修订状态-未修订
	public static final int APP_INREVISED = 0;
	// 应用修订状态-已修订
	public static final int APP_REVISED = 1;
	// 应用使用状态-未使用
	public static final int APP_UNUSABLE = 0;
	// 应用使用状态-已使用
	public static final int APP_USABLE = 1;
	// 应用使用状态-已批准
	public static final int APP_APPROVAL = 0;
	// 应用使用状态-已定制
	public static final int APP_SUBSCRIBE = 1;
	// 应用使用状态-申请中
	public static final int APP_APPLYING = 2;

	/** 日期格式转换相关常量 **/
	public static final String FORMAT_DATE_YMD_CN = "yyyy年MM月dd日";
	// 年月日转换格式标准
	public static final String FORMAT_DATE_YMD = "yyyy-MM-dd";
	// 年月日时分转换格式标准
	public static final String FORMAT_DATE_YMDHM = "yyyy-MM-dd HH-mm";
	// 年月日时分秒转换格式标准
	public static final String FORMAT_DATE_YMDHMS = "yyyy-MM-dd HH:mm:ss";
	
	//从
	public static final String LOG_FROM = "log_from";
	//开始
	public static final String LOG_START ="log_start";
	//到
	public static final String LOG_TO ="log_to";
	//截至
	public static final String LOG_END ="log_end";
	//结果
	public static final String LOG_RESULT ="log_result";
	//无条件删除
	public static final String LOG_DELETE_NO_CON ="log_delte_no_con";
	//登录名
	public static final String LOG_LOGIN ="log_login";

	/** 管理员日志 **/
	// 成功
	public static final int LOG_SUCCESS = 1;
	// 失败
	public static final int LOG_FAILED = 0;
	// 成功
	public static final String LOG_SUCCESS_MSG = "log_success_msg";
	// 失败
	public static final String LOG_FAILED_MSG = "log_failed_msg";
	// 添加
	public static final String OPERATE_ADD = "operate_add";
	//创建用户到组
	public static final String OPERATE_ADD_WITH_GROUP = "operate_add_with_group";
	// 修改
	public static final String OPERATE_UPDATE = "operate_update";
	// 删除
	public static final String OPERATE_DELETE = "operate_delete";
	// 绑定
	public static final String OPERATE_BOUND = "operate_bound";
	// 解除绑定
	public static final String OPERATE_UNBOUND = "operate_unbound";
	// 设置
	public static final String OPERATE_SET = "operate_set";
	// 重置
	public static final String OPERATE_RESET = "operate_reset";
	// 挂失
	public static final String OPERATE_LOST = "operate_lost";
	// 找回
	public static final String OPERATE_RECOVER = "operate_recover";
	// 移除孤立程序
	public static final String OPERATE_REMOVE_INVALID_APP = "operate_remove_invalid_app";
	// 启用
	public static final String OPERATE_START = "operate_start";
	// 禁用
	public static final String OPERATE_FORBID = "operate_forbid";
	// 重命名
	public static final String OPERATE_RENAME = "operate_rename";
	// 从组中删除应用
	public static final String OPERATE_REMOVE_APP_FROM_GROUP = "operate_remove_app_from_group";
	// 从组织机构中删除应用
	public static final String OPERATE_REMOVE_APP_FROM_ORGANIZATION = "operate_remove_app_from_organization";
	// 上传
	public static final String OPERATE_UPLOAD = "operate_upload";
	// 下载
	public static final String OPERATE_DOWNLOAD = "operate_download";
	//还原
	public static final String OPERATE_RESTORE = "operate_restore";
	//下载版权文件
	public static final String OPERATE_DOWNLOAD_LICENCE = "operate_download_licence";
	//下载XLS导入用户的模板文件
	public static final String OPERATE_DOWNLOAD_XLSTMPL = "operate_download_xlstmpl";
	//备份
	public static final String OPERATE_BACKUP = "operate_backup";
	//下载备份文件
	public static final String OPERATE_DOWNLOAD_BACKUP = "operate_download_backup";
	// 批准
	public static final String OPERATE_APPROVE = "operate_approve";
	// 拒绝
	public static final String OPERATE_REFUSE = "operate_refuse";
	// 申请
	public static final String OPERATE_APPLY = "operate_apply";
	// 发布
	public static final String OPERATE_PUBLISH = "operate_publish";
	// 撤销发布
	public static final String OPERATE_REVOKE_PUBLISH = "operate_revoke_publish";
	// 切换代理服务器模式
	public static final String OPERATE_CHANGE_AGENT = "operate_change_agent";
	// 批量导入
	public static final String OPERATE_LEADIN = "operate_leadin";
	// 导入操作时 ，记入日志
	public static final String OPERATE_IMPORT = "operate_import";
	// 注销
	public static final String OPERATE_LOGOUT = "operate_logout";
	// 从
	public static final String OPERATE_FROM = "operate_from";
	// 到
	public static final String OPERATE_TO = "operate_to";
	// 的
	public static final String OPERATE_S = "operate_s";
	// 用户
	public static final String OPERATE_USER = "operate_user";
	//管理员
	public static final String OPERATE_MANAGER = "operate_manager";
	//角色
	public static final String OPERATE_ROLE = "operate_role";
	//组织机构
	public static final String OPERATE_ORGANIZATION = "operate_organization";
	//部门
	public static final String OPERATE_DEPT = "operate_dept";
	// 应用
	public static final String OPERATE_APPLICATION = "operate_application";
	// 终端
	public static final String TERMINALID = "operate_terminal";
	// 服务器
	public static final String OPERATE_SERVER = "operate_server";
	// 组
	public static final String OPERATE_GROUP = "operate_group";
	// 用户组
	public static final String OPERATE_USERGROUP = "operate_usergroup";
	// 密码
	public static final String OPERATE_PASSWORD = "operate_password";
	// 系统配置
	public static final String OPERATE_SYSTEM_PARAM = "operate_system_param";
	// 静态应用
	public static final String OPERATE_APP = "operate_app";
	// 静态应用
	public static final String OPERATE_STATIC_APP = "operate_static_app";
	// 本地应用
	public static final String OPERATE_NATIVE_APP = "operate_native_app";
	// 本地应用
	public static final String OPERATE_WEB_APP = "operate_web_app";
	// 信息
	public static final String OPERATE_INFO = "operate_info";
	// 配置
	public static final String OPERATE_CONFIG = "operate_config";
	// 图标
	public static final String OPERATE_ICON = "operate_icon";
	// 域控用户
	public static final String OPERATE_LDAPUSER = "operate_ldapuser";
	// 域用户组
	public static final String OPERATE_LDAP_USER_GROUP = "operate_ldap_user_group";
	// 会话
	public static final String OPERATE_SESSION = "operate_session";
	// 更新服务器
	public static final String OPERATE_UPDATE_AGENT = "operate_update_agent";
	// 删除服务器
	public static final String OPERATE_DELETE_AGENT = "operate_delete_agent";
	// 终端
	public static final String OPERATE_TERMINAL = "operate_terminal";
	// 上传版权文件
	public static final String OPERATE_UPLOAD_LICENSE = "operate_upload_license";
	// 删除用户日志
	public static final String OPERATE_DELETE_USERLOG = "operate_delete_userLog";
	// 更改管理员密码
	public static final String OPERATE_UPDATE_MANAGER = "operate_update_manager";
	// 添加应用组
	public static final String OPERATE_ADD_APP_GROUP = "operate_add_app_group";
	// 添加角色
	public static final String OPERATE_ADD_ROLE = "operate_add_role";
	// 修改应用组
	public static final String OPERATE_UPDATE_APP_GROUP = "operate_update_app_group";
	// 修改角色
	public static final String OPERATE_UPDATE_ROLE = "operate_update_role";
	//修改应用适应的
	public static final String OPERATE_UPDATE_APP_TERMINAL_TYPE = "operate_update_app_terminal_type";
	// 删除应用组
	public static final String OPERATE_DELETE_APP_GROUP = "operate_delete_app_group";
	// 移除应用组
	public static final String OPERATE_REMOVE_APP_GROUP = "operate_remove_app_group";
	
	/**用户关联操作**/
	//添加私有应用到用户
	public static final String OPERATE_ADD_APP_TO_USR = "operate_add_app_to_usr";
	//关联用户到角色
	public static final String OPERATE_ADD_USR_TO_ROLE = "operate_add_usr_to_role";
	//取消用户与角色的关联
	public static final String OPERATE_REMOVE_USR_FROM_ROLE = "operate_remove_usr_from_role";
	//取消用户与部门的关联
	public static final String OPERATE_REMOVE_USR_FROM_DEPT = "operate_remove_usr_from_dept";
	//关联用户到部门
	public static final String OPERATE_ADD_USR_TO_DEPT = "operate_add_usr_to_dept";
	// 删除角色
	public static final String OPERATE_DELETE_ROLE = "operate_delete_role";
	//发布应用组到角色
	public static final String OPERATE_ADD_APPGROUP_TO_ROLE = "operate_add_appgroup_to_role";
	//解除应用组与角色的关联
	public static final String OPERATE_REMOVE_APPGROUP_FROM_ROLE = "operate_remove_appgroup_from_role";
	//添加下级部门
	public static final String OPERATE_ADD_GROUP_WITH_GROUP = "operate_add_group_with_group";
	// 批量删除应用组
	public static final String OPERATE_BATCH_DELETE_APP_GROUP = "operate_batch_delete_app_group";
	// 批量删除角色
	public static final String OPERATE_BATCH_DELETE_ROLE = "operate_batch_delete_role";
	// 向用户组
	public static final String OPERATE_TO_USER_GROUP = "operate_to_user_group";
	// 向角色
	public static final String OPERATE_TO_ROLE = "operate_to_role";
	// 从用户组
	public static final String OPERATE_FROM_USER_GROUP = "operate_from_user_group";
	// 从角色
	public static final String OPERATE_FROM_ROLE = "operate_from_role";
	// 向应用组
	public static final String OPERATE_TO_APP_GROUP = "operate_to_app_group";
	// 从应用组
	public static final String OPERATE_FROM_APP_GROUP = "operate_from_app_group";
	// 添加应用
	public static final String OPERATE_ADD_APP = "operate_add_app";
	// 移除应用
	public static final String OPERATE_REMOVE_APP = "operate_remove_app";
	// 应用申请
	public static final String OPERATE_APP_APPLY = "operate_app_apply";
	// 批准
	public static final String OPERATE_ACCEPT = "operate_accept";
	// 拒绝
	public static final String OPERATE_REJECT = "operate_reject";
	// 无
	public static final String OPERATE_NONE = "operate_none";
	// 应用组
	public static final String OPERATE_APP_GROUP = "operate_app_group";
	// 启用应用
	public static final String OPERATE_START_APP = "operate_start_app";
	// 禁用应用
	public static final String OPERATE_FORBID_APP = "operate_forbid_app";
	// 修改应用名称
	public static final String OPERATE_UPDATE_APP_NAME = "operate_update_app_name";
	// 修改应用
	public static final String OPERATE_UPDATE_APP = "operate_update_app";
	// 从服务器
	public static final String OPERATE_FROM_SERVER = "operate_from_server";
	// 向服务器
	public static final String OPERATE_TO_SERVER = "operate_to_server";
	// 批量删除
	public static final String OPERATE_BATCH_DELETE = "operate_batch_delete";
	// 移除
	public static final String OPERATE_REMOVE = "operate_remove";
	// 修改应用属性
	public static final String OPERATE_UPDATE_APP_ATTRIBUTE = "operate_update_app_attribute";
	// 删除管理日志
	public static final String OPERATE_DELETE_LOG = "operate_delete_log";
	//导入用户到选定部门
	public static final String OPERATE_IMPORT_USR_TO_DEPT = "operate_import_user_to_dept";
	//手动导入
	public static final String OPERATE_XLS_IMPORT_MANUALLY="operate_xls_import_manually";
	//成功导入了
	public static final String OPERATE_SUCCESSFULLY_IMPORTED="operate_successfully_imported";
	//位用户
	public static final String OPERATE_IMPORT_COUNT="operate_import_count";
	//顺序
	public static final String OPERATE_ORDER="operate_order";
	//代理历史数据
	public static final String OPERATE_AGENT_HISTORY_DATA = "operate_agent_history_data";
	//前
	public static final String OPERATE_BEFORE = "operate_before";
	//全部
	public static final String OPERATE_ALL = "operate_all";
	//永久
//	public static final String OPERATE_FOREVER="operate_forever";
	/** file/saving constants ***/
	// 备份文件时,缓冲区大小
	public static final int BUFFER_SIZE_BACKUP = 2048;
	// 文件存储时候,缓冲区大小
	public static final int BUFFER_SIZE = 1024;
	// 内容类型-png
	public static final String CONTENT_TYPE_PNG = "image/png";
	// png文件后缀
	public static final String FILE_SUFFIX_PNG = ".png";
	// 匿名上传者
	public static final String ANONYMOUS_UPLOADER = "匿名";
	// 上传版权文件
	public static final String OPERATE_UPLOAD_LICENCE = "operate_upload_licence";
	// 内容类型-html
	public static final String CONTENT_TYPE_HTML = "text/html";
	// 终端状态-存在-用于国际化
	public static final String TERMINAL_STATUS_EXSIT = "terminal_status_exist";
	// 终端状态-丢失-用于国际化
	public static final String TERMINAL_STATUS_LOST = "terminal_status_lost";
	// 终端状态-离线
	public static final String SESSION_STATUS_OFFLINE = "terminal_status_offline";
	// 终端状态-丢失
	public static final int TERMINAL_LOST = 0;
	// 终端状态-未丢失(存在)
	public static final int TERMINAL_EXIST = 1;
	//用户导入xls文件时的存储路径(在web项目的根路径下面)
	public static final String XLS_PATH = "xls";
	//用户导入xls文件时的存储路径(在web项目的根路径下面)
	public static final String XLS_PRE_NAME = "import-user-";
	//用户导入xls文件时的存储路径(在web项目的根路径下面)
	public static final String XLS_SHEET_NAME_USER_IMPORT= "xls_sheet_name_user_import";

	//用户导入xls文件时的 有效值的开始行[1,含有标题行]
	public static final int XLS_START_ROW_USER_IMPORT= 1;
	//用户导入xls文件时的 开始列
	public static final int XLS_START_INDEX_USER_IMPORT= 1;
	//用户导入xls文件时 有效列数
	public static final int XLS_NUM_COLS_USER_IMPORT= 6;
	//用户导入xls文件时  的标题
	public static final String[] LINEHEADS= new String[]{"登录名","显示名","部门","角色","是否与设备绑定","手机号"};
	
	/** 应用属性参数 **/
	// android应用启动类名
	public static final String APPLICATION_PROPERTY_ANDROID_CLASS_NAME = "android_class_name";
	// android应用启动包名
	public static final String APPLICATION_PROPERTY_ANDROID_PACKAGE_NAME = "android_package_name";
	// android应用下载地址
	public static final String APPLICATION_PROPERTY_ANDROID_DOWNLOAD_URL = "android_download_url";
	// ios应用启动地址
	public static final String APPLICATION_PROPERTY_IOS_START_URL = "ios_start_url";
	// ios应用商店地址
	public static final String APPLICATION_PROPERTY_IOS_DOWNLOAD_URL = "ios_download_url";
	//应用适应的终端类型是不是手机
	public static final String APPLICATION_PROPERTY_TERMINAL_TYPE_PHONE = "terminal_type_phone";
	//应用适应的终端类型是不是平板
	public static final String APPLICATION_PROPERTY_TERMINAL_TYPE_PAD = "terminal_type_pad";
	//应用适应的终端类型是不是Latop
	public static final String APPLICATION_PROPERTY_TERMINAL_TYPE_LAPTOP = "terminal_type_laptop";

	/** 代理服务器状态 **/
	// 维护状态
	public static final String STATUS_MAINTAIN = "status_maintain";
	// 已损坏状态
	public static final String STATUS_BROKEN = "status_broken";
	// 在线状态
	public static final String STATUS_READY = "status_ready";
	// 挂起状态
	public static final String STATUS_PENDING = "status_pending";
	// 未知状态
	public static final String STATUS_UNKNOWN = "status_unknown";

	/** 代理服务器属性参数 **/
	// 最大会话数
	public static final String AGENT_PROPERTY_MAX_SESSIONS = "max_sessions";
	// 重定向名称
	public static final String AGENT_PROPERTY_EXTERNAL_NAME = "external_name";
	// 内网网段
	public static final String AGENT_PROPERTY_INTRANET_SEGMENT = "intranet_segment";
	// 外网端口
	public static final String AGENT_PROPERTY_INTERNET_PORT = "internet_port";
	
	/** 用户属性名称 **/
	// 手机号
	public static final String USER_PROPERTY_PHONE_NUMBER = "phone_number";
	// 不与设备绑定
	public static final String USER_PROPERTY_IS_BINDABLE = "is_bindable";
	//用户创建时间
	public static final String USER_PROPERTY_USER_CREATED_TIME = "created_time";
	// 所属运营商
	public static final String USER_PROPERTY_SERVICE_PROVIDER = "service_provider";
	
	//需要忽略的用户的logins (BUG #838 导入域用户时过滤掉“administrator、Guest、krbtgt”等用户 )
	public static final String[] USERLOGINSOMIT = new String[]{"Administrator","Guest","krbtgt"};
	// 文件限定大小 -- 图标大小
	public static final int MAX_APPICON_SIZE = 1024 * 1024;

	// 设备名没有重复
	public static final String TERMINAL_NAME_AVAILABLE = "terminal_name_available";
	// 手动绑定(日志)
	public static final String OPERATE_BIND_MANUALLY = "operate_bind_manually";

	/** 会话状态--与数据库同步 **/
	// 未知
	public static final String SESSION_STATUS_UNKNOWN_EN = "unknown";
	// 异常
	public static final String SESSION_STATUS_ERROR_EN = "error";
	// 创建中
	public static final String SESSION_STATUS_CREATING_EN = "creating";
	// 已创建
	public static final String SESSION_STATUS_CREATED_EN = "created";
	// 初始化
	public static final String SESSION_STATUS_INIT_EN = "init";
	// 就绪
	public static final String SESSION_STATUS_READY_EN = "ready";
	// 就绪
	public static final String SESSION_STATUS_BROKEN_EN = "broken";
	// 已登录
	public static final String SESSION_STATUS_ACTIVE_EN = "logged";
	// 已断开
	public static final String SESSION_STATUS_INACTIVE_EN = "disconnected";
	// 等待注销
	public static final String SESSION_STATUS_WAIT_DESTROY_EN = "wait_destroy";
	// 正在注销
	public static final String SESSION_STATUS_DESTROYING_EN = "destroying";
	// 已注销
	public static final String SESSION_STATUS_DESTROYED_EN = "destroyed";

	/** 会话状态--用于国际化 **/
	// 未知
	public static final String SESSION_STATUS_UNKNOWN = "session_status_unknown";
	// 异常
	public static final String SESSION_STATUS_ERROR = "session_status_error";
	// 创建中
	public static final String SESSION_STATUS_CREATING = "session_status_creating";
	// 已创建
	public static final String SESSION_STATUS_CREATED = "session_status_created";
	// 初始化
	public static final String SESSION_STATUS_INIT = "session_status_init";
	// 就绪
	public static final String SESSION_STATUS_READY = "session_status_ready";
	// 已登录
	public static final String SESSION_STATUS_ACTIVE = "session_status_logged";
	// 已断开
	public static final String SESSION_STATUS_INACTIVE = "session_status_disconnected";
	// 等待注销
	public static final String SESSION_STATUS_WAIT_DESTROY = "session_status_wait_destroy";
	// 正在注销
	public static final String SESSION_STATUS_DESTROYING = "session_status_destroying";
	// 已注销
	public static final String SESSION_STATUS_DESTROYED = "session_status_destroyed";
	// 删除日志审计
	public static final String OPERATE_DELETE_SYS_LOG = "operate_delete_sys_log";
	/** 用户通知信息 **/
	// 通知忽略状态-未忽略
	public static final int NOTICE_IGNORE_STATUS = 0;
	// 通知忽略状态-已忽略
	public static final int NOTICE_IGNORED_STATUS = 1;
	// 批准应用申请通知-公共部分第一部分
	public static final String NOTICE_APPROVE_APP_TEXT_1 = "您申请使用的应用[";
	// 批准应用申请通知-公共部分第二部分
	public static final String NOTICE_APPROVE_APP_TEXT_2 = "]已被管理员批准。";
	// 拒绝应用申请通知-公共部分第一部分
	public static final String NOTICE_REFUSE_APP_TEXT_1 = "您申请使用的应用[";
	// 拒绝应用申请通知-公共部分第二部分
	public static final String NOTICE_REFUSE_APP_TEXT_2 = "]已被管理员驳回，原因是：";

	/** 用户行为统计 */
	// 百分比
	public static final String STAT_PERCENTAGE = "stat_percentage";
	// 次
	public static final String STAT_TIMES = "stat_times";
	// 未知应用
	public static final String STAT_UNKNOWN = "stat_unknown";
	// 其他
	public static final String STAT_OTHER = "stat_other";
	// 全部
	public static final String STAT_ALL = "stat_all";
	// 登录
	public static final String STAT_LOGIN = "stat_login";
	// 启动应用
	public static final String STAT_OPEN_APP = "stat_open_app";
	// 请选择
	public static final String STAT_CHOOSE_ALL = "stat_choose_all";
	// 点
	public static final String STAT_POINT = "stat_point";
	/***组织架构**/
	public static final int ROOT_GROUP_PID = 0;
	public static final int ROOT_DEPARTMENT_PID = -1;
	/** MySQL函数名 */
	// 根据rootId获取全部部门ID
	public static final String MYSQL_GET_DEPT_IDS_BY_ROOT_ID = "getDeptIdsByRootId";
	//用户注册
	public static final String OPERATE_ADD_WITH_GROUP_SESSION = "operate_add_with_group_session";
	//是
	public static final String COMMON_YES = "common_yes";
	//否
	public static final String COMMON_NO = "common_no";
	//第
	public static final String COMMON_THE_ORDER = "common_the_order";
	//行:
	public static final String COMMON_THE_ROW = "common_the_row";
	//行
	public static final String COMMON_THE_ROW_CLEAR  = "common_the_row_clear";
	/**系统备份还原功能**/
	public static final String VERIFYFILE = "/usr/massclouds/session/runtime/backup/file/verify";
	//备份文件存储的路径
	public static final String BACKUPPATH = "/usr/massclouds/session/runtime/backup/file";
	public static final String BACKUPPATHTMP = "/usr/massclouds/session/runtime/backup/tmp";
	
	public static final String PATH_TMP = "tmp";
	//需要备份的数据库名称
	public static final String[] DBNAMES = new String[]{"massclouds","storage"};
	//中心服务器中的配置文件 列表
	public static final String[] CONFIGFILES = new String[]{
		"/usr/massclouds/session/runtime/spool/application_about.xml",			
		"/usr/massclouds/session/runtime/spool/application_media_about.xml",
		"/usr/massclouds/console/webapps/console/WEB-INF/classes/config.properties"
		};
	//云盘配置文件列表
	public static final String[] STORAGEFILES = new String[]{
		"/usr/massclouds/portal/cStorage/www/.htaccess",
		"/usr/massclouds/portal/cStorage/www/config/config.php",
   		"/usr/massclouds/portal/cStorage/www/data/storage/mount.json"//这是一个路径
		};
	//应用的图片存储的路径
	public static final String APPICONPATH = "/usr/massclouds/session/runtime/spool/cache/image/application";
	public static final String DOMAIN_ADMINS = "Domain Admins";
	public static final String INSTALL_SYNC = "install_sync";
	public static final String STOP_SYNC = "stop_sync";
	//public static final String ROOT = "http://192.168.5.33:8080/";
	public static final String ROOT = "http://112.33.3.134:8081/";
	
	//备份系统
	public static final String OPERATE_BACKUP_SYSTEM = "operate_backup_system";
	
	// 上传下载 上访信息图像 路径  
	public static final String SERVER_NAME = "http://192.168.5.30:8080/";
	public static final String TMP_TRACK_PATH = "ImageFileTmp";
	public static final String REAL_TRACK_PATH = "ImageFile";
	public static final long UPLOAD_FILE_MAX_SIZE = 10*1024*1024;
	public static final int UNIT_FILE_SIZE = 1024;
	
	// 自动比对重点关注人员活动轨迹刷新间隔
	public static final int REFRESH_INTERVAL_MINUTE = 2*60;// 刷新间隔分钟数  当前两小时
	public static final long REFRESH_INTERVAL = REFRESH_INTERVAL_MINUTE*60*1000;// 刷新间隔毫秒
	
	//短信接口关联参数
	public static final String ENDPOINT = "http://101.200.228.238/smsport/default.asmx";
	public static final String USER = "jmhl01";
	public static final String PASSWORD = "123456";
	public static final String LONGNUM = "9961";
	
	//图片真实路径
	public static final String REAL_UPLAOD_PATH="C:\\pic";
	
}