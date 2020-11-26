CREATE TABLE t_user(
    id int NOT NULL AUTO_INCREMENT,
    username varchar(20) NOT NULL COMMENT '用户名',
    passwd varchar(128) NOT NULL COMMENT '密码',
    create_time DATE NULL COMMENT '创建时间',
    status char(1) NOT NULL COMMENT '是否有效 1：有效  0：锁定',
    PRIMARY KEY ( `id` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO user VALUES ('1', 'yutiy', 'd7152961c782c668f8aadf3d8257d919', '2020-11-25 10:52:48', '1');
INSERT INTO user VALUES ('2', 'test', '9f8a66acdc3eae03b93d6dafcca03138', '2020-11-24 17:20:21', '1');

CREATE TABLE t_role(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(32) NULL COMMENT '角色名称',
    memo varchar(32) NULL COMMENT '角色描述',
    PRIMARY KEY ( `id` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO role VALUES ('1', 'admin', '超级管理员');
INSERT INTO role VALUES ('2', 'test', '测试账户');

CREATE TABLE t_user_role(
    `user_id` int NULL COMMENT '用户id',
    `role_id` int NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO user_role VALUES ('1', '1');
INSERT INTO user_role VALUES ('2', '2');

CREATE TABLE t_permission(
    id int NOT NULL AUTO_INCREMENT,
    url varchar(256) NULL COMMENT 'url地址',
    name varchar(64) NULL COMMENT 'url描述',
    PRIMARY KEY ( `id` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO permission VALUES ('1', '/user', 'user:user');
INSERT INTO permission VALUES ('2', '/user/add', 'user:add');
INSERT INTO permission VALUES ('3', '/user/delete', 'user:delete');

CREATE TABLE t_role_permission(
    `role_id` int NULL COMMENT '角色id',
    `perm_id` int NULL COMMENT '权限id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO role_permission VALUES ('1', '1');
INSERT INTO role_permission VALUES ('1', '2');
INSERT INTO role_permission VALUES ('1', '3');
INSERT INTO role_permission VALUES ('2', '1');
