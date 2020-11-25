CREATE TABLE sys_log (
	id INT ( 20 ) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR ( 50 ) NULL COMMENT '用户名',
	operation VARCHAR ( 50 ) NULL COMMENT '用户操作',
	time INT ( 11 ) NULL COMMENT '响应时间',
	method VARCHAR ( 200 ) NULL COMMENT '请求方法',
	params VARCHAR ( 500 ) NULL COMMENT '请求参数',
	ip VARCHAR ( 64 ) NULL COMMENT 'IP地址',
	create_time date NULL COMMENT '创建时间'
);