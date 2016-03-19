/*
 lhgdialog.setting =
 {
 content: '<div class="ui_loading"><span>loading...</span></div>',
 title: '\u89C6\u7A97 ',     // 标题,默认'视窗'
 button: null,	     		// 自定义按钮
 ok: null,					// 确定按钮回调函数
 cancel: null,				// 取消按钮回调函数
 init: null,					// 对话框初始化后执行的函数
 close: null,				// 对话框关闭前执行的函数
 okVal: '\u786E\u5B9A',		// 确定按钮文本,默认'确定'
 cancelVal: '\u53D6\u6D88',	// 取消按钮文本,默认'取消'
 skin: '',					// 多皮肤共存预留接口
 esc: true,					// 是否支持Esc键关闭
 show: true,					// 初始化后是否显示对话框
 width: 'auto',				// 内容宽度
 height: 'auto',				// 内容高度
 icon: null,					// 消息图标名称
 path: _path,                // lhgdialog路径
 lock: false,				// 是否锁屏
 focus: true,                // 窗口是否自动获取焦点
 parent: null,               // 打开子窗口的父窗口对象，主要用于多层锁屏窗口
 padding: '10px',		    // 内容与边界填充距离
 fixed: false,				// 是否静止定位
 left: '50%',				// X轴坐标
 top: '38.2%',				// Y轴坐标
 max: true,                  // 是否显示最大化按钮
 min: true,                  // 是否显示最小化按钮
 zIndex: 1976,				// 对话框叠加高度值(重要：此值不能超过浏览器最大限制)
 resize: true,				// 是否允许用户调节尺寸
 drag: true, 				// 是否允许用户拖动位置
 cache: true,                // 是否缓存窗口内容页
 data: null,                 // 传递各种数据
 extendDrag: false           // 增加lhgdialog拖拽体验
 };
 */
/*系统弹框-个人页面？*/
$(document).on('click', '.add_note', function() {
	$.dialog({
		title: "添加笔记",
		id: 'dialogReg',
		zIndex: 1000,
		width: 1300,
		height: 660,
		lock: true,
		fixed: true,
		background: '#000000',
		opacity: .5,
		content: 'url:/html/admin/note/add.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '30px',
		left: '50%'
	});
});

/*系统弹框-审核列表*/
$(document).on('click', '.audit_list_pop', function() {
	$.dialog({
		title: "系统消息",
		id: 'dialogReg',
		zIndex: 1000,
		width: 1300,
		height: 660,
		lock: true,
		fixed: true,
		background: '#000000',
		opacity: .5,
		content: 'url:/html/head_user_center/head_user_center_list_audit.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '30px',
		left: '50%'
	});
});

/*
 菜单管理-Add（添加）
 */
$(document).on('click', '.outPage', function() {
	$.dialog({
		title: '子菜单添加',
		id: 'dialogReg',
		zIndex: 1000,
		fixed: true,
		width: 650,
		height: 400,
		lock: true,
		background: '#000000',
		opacity: .5,
		content: 'url:../html/resource/resource_add.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '100px',
		left: '50%',
		data: {
			parentId: $(this).prop("id")
		}
	});
});
/*
 菜单管理-Edit（编辑）
 */
$(document).on('click', '.outPageEdit', function() {
	$.dialog({
		title: '菜单编辑',
		id: 'dialogReg',
		zIndex: 1000,
		fixed: true,
		width: 650,
		height: 400,
		lock: true,
		background: '#000000',
		opacity: .5,
		content: 'url:../html/resource/resource_update.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '100px',
		left: '50%',
		data: {
			id: $(this).prop("id")
		}
	});
});
/*
 部门管理-Add（添加）
 */
$(document).on('click', '.add_department', function() {
	$.dialog({
		title: '添加部门',
		id: 'dialogReg',
		zIndex: 1000,
		fixed: true,
		width: 500,
		height: 215,
		lock: true,
		background: '#000000',
		opacity: .5,
		content: 'url:../html/department/department_add.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '100px',
		left: '50%'
	});
});
/*
 部门管理-Edit（编辑）
 */
$(document).on('click', '.edit_department', function() {
	$.dialog({
		title: '编辑部门',
		id: 'dialogReg',
		zIndex: 1000,
		fixed: true,
		width: 500,
		height: 220,
		lock: true,
		background: '#000000',
		opacity: .5,
		content: 'url:../html/department/department_update.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '100px',
		left: '50%',
		data: {
			"deptId": $(this).attr("id")
		}
	});
});
/*
 机构管理-Add（添加）
 */
$(document).on('click', '.add_organization', function() {
	$.dialog({
		title: '添加机构',
		id: 'dialogReg',
		zIndex: 1000,
		fixed: true,
		width: 480,
		height: 655,
		lock: true,
		background: '#000000',
		opacity: .5,
		content: 'url:../html/organization/organization_add.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '30px',
		left: '50%'
	});
});
/*
 机构管理-Edit（编辑）
 */
$(document).on('click', '.edit_organization', function() {
	$.dialog({
		title: '编辑机构',
		id: 'dialogReg',
		zIndex: 1000,
		fixed: true,
		width: 480,
		height: 655,
		lock: true,
		background: '#000000',
		opacity: .5,
		content: 'url:../html/organization/organization_update.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '30px',
		left: '50%',
		data: {
			"orgId": $(this).attr("id")
		}
	});
});
/*
 菜单管理-Add父级别菜单（添加/编辑）
 */
$(document).on('click', '#add_menu', function() {
	$.dialog({
		title: '菜单添加',
		id: 'dialogReg',
		zIndex: 1000,
		fixed: true,
		width: 650,
		height: 400,
		lock: true,
		background: '#000000',
		opacity: .5,
		content: 'url:../html/resource/resource_add.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '100px',
		left: '50%',
		data: {
			parentId: 0
		}
	});
});

/*
 菜单管理-Forbidden（禁用）
 */
$(document).on('click', '.close_Page', function() {
	$.dialog({
		title: "停用",
		id: 'dialogReg',
		zIndex: 1000,
		fixed: true,
		width: 410,
		height: 125,
		lock: true,
		background: '#000000',
		opacity: .5,
		content: 'url:../html/V1-72-cdgl-close.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '100px',
		left: '50%',
		button: [{
			name: '确定',
			callback: function() {
				alert('确定');
			}
		}, {
			name: '取消'
		}]
	});
});
/*
 角色管理-allotMenu（分配菜单）
 */
$(document).on('click', '.allotMenu', function() {
	$.dialog({
		title: "分配菜单",
		id: 'dialogReg',
		bgiframe: true,
		zIndex: 1000,
		width: 500,
		height: 560,
		lock: true,
		fixed: true,
		background: '#000000',
		opacity: .5,
		content: 'url:../html/role/role_resource_list.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '30px',
		left: '50%',
		data: {
			'roleId': $(this).attr("id")
		}
	});
});
/*
 角色管理-Add_js（添加）
 */
$(document).on('click', '.add_js', function() {

	$.dialog({
		title: "添加角色",
		id: 'dialogReg',
		fixed: true,
		zIndex: 1000,
		width: 550,
		height: 360,
		lock: true,
		background: '#000000',
		opacity: .5,
		content: 'url:../html/role/role_add.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '100px',
		left: '50%'
	});
});
/*
 角色管理-compile（编辑）
 */
$(document).on('click', '.compile', function() {

	$.dialog({
		title: "编辑角色",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 550,
		height: 360,
		lock: true,
		background: '#000000',
		opacity: .5,
		content: 'url:../html/role/role_update.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '100px',
		left: '50%',
		data: {
			'roleId': $(this).attr("id")
		}
	});
});
/*
 角色管理-look（查看）
 */
$(document).on('click', '.look', function() {

	$.dialog({
		title: "查看角色",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 450,
		height: 430,
		lock: true,
		background: '#000000',
		opacity: .5,
		content: 'url:../html/role/role_look.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '100px',
		left: '50%',
		data: {
			'roleId': $(this).attr("id")
		}
	});
});
/*
 角色管理-close_jscd（停用）
 */
$(document).on('click', '.close_jscd', function() {
	$.dialog({
		title: "停用",
		id: 'dialogReg',
		zIndex: 1000,
		width: 410,
		height: 125,
		fixed: true,
		lock: true,
		background: '#000000',
		opacity: .5,
		content: 'url:../html/V1-71-jsgl-close_jscd.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '100px',
		left: '50%',
		button: [{
			name: '确定',
			callback: function() {
				alert('确定');
			}
		}, {
			name: '取消'
		}]
	});
});
/*
 工作台-借款人详情
 */
$(document).on('click', '.content_4_wldh_ul_1_khxx', function() {
	$.dialog({
		title: "借款人详情",
		id: 'dialogReg',
		fixed: true,
		zIndex: 1000,
		width: 1000,
		height: 600,
		lock: true,
		background: '#000000',
		opacity: .5,
		content: 'url:/html/workbench/workbench_credituser_info.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			'creditUserId': $.url.extractParamByStr($url, 'id'),
			'closeRatio': $('#closeRatio').val(),
			'receivablesAmount': $('#receivablesAmount').val(),
			'proceedsAmount': $('#proceedsAmount').val(),
			'pendingAmount': $('#pendingAmount').val(),
			'repaymentRatio': $('#repaymentRatio').val(),
			'contactNumber': $('#contactNumber').val(),
			'remainingDays': $('#remainingDays').val()
		}
	});
});
/*
 任务管理-显示详情
 */
$(document).on('click', '.content_4_wldh_ul_1_xsxq_credit_task', function() {
	$.dialog({
		title: "显示详情",
		id: 'dialogReg',
		fixed: true,
		zIndex: 1000,
		width: 1000,
		height: 600,
		lock: true,
		background: '#000000',
		opacity: .5,
		content: 'url:../html/workbench/workbench_credituser_info.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			'creditUserId': $('#taskId').val()
		}
	});
});
/*
 工作台-新增记录
 */
$(document).on('click', '.content_4_wldh_ul_4_xzjl', function() {
	$.dialog({
		title: "新增记录",
		id: 'dialogReg',
		fixed: true,
		zIndex: 1000,
		width: 530,
		height: 260,
		lock: true,
		background: 'black',
		opacity: .5,
		content: 'url:/html/workbench/workbench_add_log.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			'creditUserId': $.url.extractParamByStr($url, 'id')
		}
	});
});
/*
 工作台-操作记录
 */
$(document).on('click', '.content_4_wldh_ul_1_czjl', function() {
	$.dialog({
		title: "操作记录",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 700,
		height: 347,
		lock: true,
		background: 'black',
		opacity: .5,
		content: 'url:../html/workbench/workbench_operation_log.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			'creditUserId': $.url.extractParamByStr($url, 'id')
		}
	});
});
/*
 工作台-账单明细
 */
$(document).on('click', '.content_4_wldh_ul_l_zdmx', function() {
	$.dialog({
		title: "账户明细",
		id: 'dialogReg',
		fixed: true,
		zIndex: 1000,
		width: 1000,
		height: 604,
		lock: true,
		background: 'black',
		opacity: .5,
		content: 'url:../html/workbench/workbench_account_details.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			'creditUserId': $.url.extractParamByStr($url, 'id'),
			'receivablesAmount': $('#receivablesAmount').val()
		}
	});
});
/*
 工作台-标签管理
 */
$(document).on('click', '.content_4_wldh_ul_l_bqgl', function() {
	$.dialog({
		title: "标签管理",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 1000,
		height: 600,
		lock: true,
		background: 'black',
		opacity: .5,
		content: 'url:../html/workbench/workbench_label.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			'creditUserId': $.url.extractParamByStr($url, 'id')
		}
	});
});

/*
 工作台-减免申请
 */
$(document).on('click', '.content_4_wldh_ul_l_jm', function() {
	$.dialog({
		title: "减免申请",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 810,
		height: 471,
		lock: true,
		background: 'black',
		opacity: .5,
		content: 'url:../html/workbench/workbench_derate.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			'creditUserId': $.url.extractParamByStr($url, 'id')
		}

	});
});
/*
 工作台-结案
 */
$(document).on('click', '.content_4_wldh_ul_l_ja', function() {
	$.dialog({
		title: "结案申请",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 830,
		height: 503,
		lock: true,
		background: 'black',
		opacity: .5,
		content: 'url:../html/workbench/workbench_finish.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '20px',
		left: '50%',
		data: {
			'creditUserId': $.url.extractParamByStr($url, 'id')
		}
	});
});


//移出弹出框--黑名单
$(document).on('click', '.shift_out', function() {
	$.dialog({
		title: "黑名单移出提示",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 500,
		height: 125,
		lock: true,
		background: '#000000',
		opacity: .5,
		content: 'url:../html/V1-36-blackmenu-close.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '100px',
		left: '50%',
		button: [{
			name: '确认',
			callback: function() {
				removeBlacklist();
			}
		}, {
			name: '取消'
		}]
	});
});

//催收任务未达标 请及时催收
$(document).on('click', '.cswdb', function() {
	$.dialog({
		title: "催收任务未达标",
		id: 'dialogReg',
		zIndex: 1000,
		width: 500,
		height: 300,
		lock: true,
		background: '#000000',
		opacity: .5,
		content: 'url:../html/V1-37-cswdb-close.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '100px',
		left: '50%',
		button: [{
			name: '确认',
			callback: function() {
				alert('确认');
			}
		}, {
			name: '取消'
		}]
	});
});

//有未保存的内容 是否保存
$(document).on('click', '.keepcontent', function() {
	$.dialog({
		title: "操作信息",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 500,
		height: 300,
		lock: true,
		background: '#000000',
		opacity: .5,
		content: 'url:../html/V1-38-keepcontent-close.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '100px',
		left: '50%',
		button: [{
			name: '返回处理',
			callback: function() {
				alert('返回处理');
			}
		}, {
			name: '直接提交',
			callback: function() {
				alert('直接提交');
			}
		}, {
			name: '放弃'
		}]
	});
});

/*
 工作台-申请转出
 */
$(document).on('click', '.content_4_wldh_ul_l_sqzc', function() {
	$.dialog({
		title: "申请转出",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 830,
		height: 510,
		lock: true,
		background: 'black',
		opacity: .5,
		content: 'url:../html/workbench/workbench_rolloff.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			'creditUserId': $.url.extractParamByStr($url, 'id')
		}
	});
});
/*
 工作台-发送短信
 */
$(document).on('click', '.content_4_wldh_ul_l_fsdx', function() {
	$.dialog({
		title: "发送短信",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 600,
		height: 550,
		lock: true,
		background: 'black',
		opacity: .5,
		content: 'url:../html/workbench/workbench_message.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			'creditUserId': $.url.extractParamByStr($url, 'id')
		}
	});
});


//新建用户1
$(document).on('click', '.newad', function() {
	$.dialog({
		title: "新建用户",
		fixed: true,
		id: 'dialogReg',
		zIndex: 9999,
		width: 670,
		height: 670,
		lock: true,
		background: '#000000',
		opacity: .5,
		content: 'url:../html/user/user_add.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '30px',
		left: '50%',
		data: {
			roleSuper: gm.sessionUser.userSuperior
		}
	});
});

//新建用户-编辑
$(document).on('click', '.newad-edit', function() {
	$.dialog({
		title: "编辑用户",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 670,
		height: 670,
		lock: true,
		background: '#000000',
		opacity: .5,
		content: 'url:../html/user/user_update.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '30px',
		left: '50%',
		data: {
			id: $(this).prop("id"),
			roleSuper: gm.sessionUser.userSuperior
		}
	});
});


/*
    批量发送
 */
$(document).on('click', '.content_4_wldh_ul_l_plfs', function() {
	$.dialog({
		title: "批量发送",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 1300,
		height: 682,
		lock: true,
		background: '#000000',
		opacity: .5,
		content: 'url:../html/workbench/workbench_batch_send.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '10px',
		left: '50%',
		close: function() {
			reloadGridData($.url.extractParamByStr($url, 'id'));
		},
		close: function() {
			reloadGridData($.url.extractParamByStr($url, 'id'));
		}
	});
});


/*
 工作台-发送邮件
 */
$(document).on('click', '.content_4_wldh_ul_l_fsj', function() {
	$.dialog({
		title: "电子邮件",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 600,
		height: 552,
		lock: true,
		background: 'black',
		opacity: .5,
		content: 'url:../html/workbench/workbench_email.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			'creditUserId': $.url.extractParamByStr($url, 'id')
		}
	});
});

/*
 工作台-打印
 */
$(document).on('click', '.content_4_wldh_ul_l_dyzl', function() {
	$.dialog({
		title: "打印资料",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 550,
		height: 550,
		lock: true,
		background: 'black',
		opacity: .5,
		content: 'url:../html/workbench/workbench_print.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			'creditUserId': $.url.extractParamByStr($url, 'id')
		},
		close: function() {
			reloadGridData($.url.extractParamByStr($url, 'id'));
		}
	});
});
/*
 工作台-预约计划
 */
$(document).on('click', '.content_4_wldh_ul_l_yyjh', function() {
	$.dialog({
		title: "预约计划",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 1100,
		height: 600,
		lock: true,
		background: 'black',
		opacity: .5,
		content: 'url:../html/workbench/workbench_subscribe.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			'creditUserId': $.url.extractParamByStr($url, 'id')
		}
	});
});


/*
 工作台-申请扣款
 */
$(document).on('click', '.content_4_wldh_ul_l_kk', function() {
	$.dialog({
		title: "扣款申请",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 1000,
		height: 509,
		lock: true,
		background: 'black',
		opacity: .5,
		content: 'url:../html/workbench/workbench_proceeds.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			'creditUserId': $.url.extractParamByStr($url, 'id')
		}
	});
});


//数据更新-更新***

$(document).on('click', '.update', function() {
	$.dialog({
		title: "数据更新",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 600,
		height: 450,
		lock: true,
		background: 'black',
		opacity: .5,
		content: 'url:../html/credit_update/credit_update_info.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			'id': $(this).attr("id")
		}
	});
});

//项目管理-第一资产详情***
$(document).on('click', '.xmgl-look', function() {
	$.dialog({
		title: "资产详情",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 1300,
		height: 420,
		lock: true,
		background: 'black',
		opacity: .5,
		content: 'url:../html/project/project_credit_info.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			'id': $(this).attr("id")
		}
	});
});
/*
 工作台-上传资料
 */
$(document).on('click', '.content_4_wldh_ul_1_khzl', function() {
	$.dialog({
		title: "上传资料",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 1000,
		height: 600,
		lock: true,
		background: '#000000',
		opacity: .5,
		content: 'url:../html/workbench/workbench_credituser_data.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			'creditUserId': $.url.extractParamByStr($url, 'id')
		}
	});
});

//人员管理-查看
$(document).on('click', '.person_look', function() {
	$.dialog({
		title: "查看",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 550,
		height: 670,
		lock: true,
		background: '#333333',
		opacity: .5,
		content: 'url:../html/user/user_detail.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			id: $(this).prop("id"),
			roleSuper: gm.sessionUser.userSuperior,
			name: $(this).prop("title")
		}
	});
});

//人员管理-分配角色
$(document).on('click', '.person_fpjs', function() {
	$.dialog({
		title: "分配角色",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 1000,
		height: 600,
		lock: true,
		background: '#333333',
		opacity: .5,
		content: 'url:../html/user/user_role_list.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			id: $(this).prop("id"),
			roleSuper: gm.sessionUser.userSuperior
		}
	});
});

//标签管理-添加
$(document).on('click', '.label_add', function() {
	$.dialog({
		title: "添加标签",
		fixed: true,
		id: 'dialogLableAdd',
		zIndex: 1000,
		width: 500,
		height: 280,
		lock: true,
		background: '#333333',
		opacity: .5,
		content: 'url:../html/label/label_add.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%'
	});
});

//标签管理-编辑
$(document).on('click', '.label_edit', function() {
	$.dialog({
		title: "编辑标签",
		fixed: true,
		id: 'dialogLableEdit',
		zIndex: 1000,
		width: 500,
		height: 280,
		lock: true,
		background: '#fff',
		opacity: .5,
		content: 'url:../html/label/label_update.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			id: $(this).prop("id")
		}
	});
});


/*
添加数据过滤级别
*/
$(document).on('click', '#add_filter_data', function() {
	$.dialog({
		title: '添加数据过滤级别',
		id: 'dialogReg',
		zIndex: 1000,
		fixed: true,
		width: 620,
		height: 480,
		lock: true,
		background: '#000000',
		opacity: .5,
		content: 'url:../html/filter_data/filter_data_add.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%'
	});
});

/*
 * 更新数据过滤级别
 */
$(document).on('click', '.update_filter_data', function() {
	$.dialog({
		title: "更新数据过滤级别",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 620,
		height: 480,
		lock: true,
		background: 'black',
		opacity: .5,
		content: 'url:../html/filter_data/filter_data_update.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			id: $(this).prop("id")
		}
	});
});

//扩展标签管理-添加
$(document).on('click', '.label_check_add', function() {
	$.dialog({
		title: "添加扩展标签",
		fixed: true,
		id: 'dialogLablecheckAdd',
		zIndex: 1000,
		width: 500,
		height: 230,
		lock: true,
		background: '#fff',
		opacity: .5,
		content: 'url:/html/label_check/label_check_add.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			labelId: $(this).prop("labelId")
		}
	});
});

//扩展标签管理-编辑
$(document).on('click', '.label_check_edit', function() {
	$.dialog({
		title: "编辑扩展标签",
		fixed: true,
		id: 'dialogLablecheckEdit',
		zIndex: 1000,
		width: 500,
		height: 230,
		lock: true,
		background: '#fff',
		opacity: .5,
		content: 'url:/html/label_check/label_check_update.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			id: $(this).prop("id")
		}
	});
});

/*
 定时任务-添加
 */
$(document).on('click', '.job_add', function() {
	$.dialog({
		title: "添加定时任务",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 630,
		height: 440,
		lock: true,
		background: 'black',
		opacity: .5,
		content: 'url:../html/schedule_job/schedule_job_add.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%'
	});
});

/*
 定时任务-更新
 */
$(document).on('click', '.task_edit', function() {
	$.dialog({
		title: "更新定时任务",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 630,
		height: 440,
		lock: true,
		background: 'black',
		opacity: .5,
		content: 'url:../html/schedule_job/schedule_job_update.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			id: $(this).prop("id")
		}
	});
});


/*
 更新
 */
$(document).on('click', '.template_edit', function() {
	$.dialog({
		title: "编辑模版",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 600,
		height: 600,
		lock: true,
		background: 'black',
		opacity: .5,
		content: 'url:../html/template/template_update.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '30px',
		left: '50%',
		data: {
			id: $(this).attr("id")
		}
	});
});

/*
 添加
 */
$(document).on('click', '.template_add', function() {
	$.dialog({
		title: "新增模版",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 600,
		height: 600,
		lock: true,
		background: 'black',
		opacity: .5,
		content: 'url:../html/template/template_add.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '30px',
		left: '50%',
		data: {
			id: $(this).attr("id")
		}
	});
});
/*
	 修改密码
 */
$(document).on('click', '.epm_z_3_1_head1', function() {
	$.dialog({
		title: "修改密码",
		fixed: true,
		id: 'dialogReg',
		zIndex: 1000,
		width: 480,
		height: 250,
		lock: true,
		background: 'black',
		opacity: .5,
		content: 'url:../html/head_user_center/update_password.html',
		min: false,
		max: false,
		padding: 0,
		margin: 0,
		resize: false,
		top: '50px',
		left: '50%',
		data: {
			id: gm.sessionUser.id
		}
	});
});