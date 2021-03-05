CREATE TABLE java01_user_info(
    id bigint not null AUTO_INCREMENT PRIMARY KEY comment '主键',
    user_id VARCHAR(32) NOT NULL comment '用户ID',
    user_name VARCHAR(20) not null comment '用户名',
    user_pwd VARCHAR(20) not null comment '用户密码',
    status int default 0 not null comment '用户状态',
    remark VARCHAR(200) comment '备注',
    created_by VARCHAR(100) not null comment '创建人',
    date_created TIMESTAMP not null default now() comment '创建时间',
    updated_by VARCHAR(100) not null comment '更新人',
    date_updated TIMESTAMP not null default now() comment '更新时间'
) comment '用户表';

