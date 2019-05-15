
CREATE TABLE `t_evaluate` (
  `FID` int(11)  PRIMARY KEY AUTO_INCREMENT,
  `FDeptID` int(11) DEFAULT NULL,
  `FDeptName` varchar(255) DEFAULT NULL,
  `FWindowId`  varchar(50) DEFAULT NULL,
  `FCenterId`  varchar(50) DEFAULT NULL,
  `FStaffID` varchar(20) DEFAULT NULL COMMENT '员工工号',
  `FStaffName` varchar(255) DEFAULT NULL COMMENT '员工姓名',
  `FCustName` varchar(255) DEFAULT NULL COMMENT '办事人员姓名',
  `FCustMobile` varchar(255) DEFAULT NULL COMMENT '办事人员手机号',
  `FItemID` int(11) DEFAULT NULL COMMENT '事项ID',
  `FItemName` varchar(255) DEFAULT NULL COMMENT '事项说明',
  `FCreateDate` date DEFAULT NULL,
  `FBeginTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `FEndTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `FDuration` int(255) DEFAULT NULL COMMENT '时长  单位 秒',
  `FQuestion` varchar(255) DEFAULT NULL COMMENT '评价提问',
  `FAnswer` int(11) DEFAULT NULL COMMENT '回答 1 是  2否',
  `FEvaluate` int(11) DEFAULT NULL COMMENT '1 非常不满意 2不满意 3基本满意 4满意 5非常满意'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;


CREATE TABLE `t_evaluate_label` (
  `FID` int(11)  PRIMARY KEY AUTO_INCREMENT,
  `FEvaluateID` int(11) DEFAULT NULL COMMENT '评价表ID',
  `FLabel` varchar(255) DEFAULT NULL COMMENT '标签'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;


CREATE TABLE `t_user` (
  `FID` int(11)  PRIMARY KEY AUTO_INCREMENT,
  `FName` varchar(20) DEFAULT NULL COMMENT '账户',
  `FPassWord` varchar(32) DEFAULT NULL COMMENT '密码',
  `FEnable` int(11) NOT NULL DEFAULT '1' COMMENT '1启用 2禁用'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;


CREATE TABLE `t_question` (
`FID`  int(11)  PRIMARY KEY AUTO_INCREMENT,
`FQuestion`  varchar(255) NULL COMMENT '问题描述' ,
`FCreateTime`  datetime NULL ON UPDATE CURRENT_TIMESTAMP ,
`FEnable`  int NULL COMMENT '1启用 2禁用' 
)ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
