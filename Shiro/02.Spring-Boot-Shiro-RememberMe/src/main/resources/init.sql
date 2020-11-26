CREATE TABLE t_user(
    id int NOT NULL AUTO_INCREMENT,
    username varchar(20) NOT NULL COMMENT '用户名',
    passwd varchar(128) NOT NULL COMMENT '密码',
    create_time DATE NULL COMMENT '创建时间',
    status char(1) NOT NULL COMMENT '是否有效 1：有效  0：锁定',
    PRIMARY KEY ( `id` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO t_user VALUES ('1', 'yutiy', '9f8a66acdc3eae03b93d6dafcca03138', '2020-11-25 10:52:48', '1');
INSERT INTO t_user VALUES ('2', 'test', '9f8a66acdc3eae03b93d6dafcca03138', '2020-11-24 17:20:21', '0');
