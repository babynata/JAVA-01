CREATE TABLE java01_user_info(
    id bigint not null AUTO_INCREMENT PRIMARY KEY ,
    user_id VARCHAR(32) NOT NULL,
    user_name VARCHAR(20) not null,
    user_pwd VARCHAR(20) not null,
    status int default 0 not null,
    remark VARCHAR ,
    created_by VARCHAR(100) not null,
    date_created TIMESTAMP not null,
    updated_by VARCHAR(100) not null,
    date_updated TIMESTAMP not null
);
comment on table java01_user_info is '用户表';
comment on COLUMN java01_user_info.id is '主键';
comment on COLUMN java01_user_info.user_id is '用户ID';
comment on COLUMN java01_user_info.user_name is '用户名';
comment on COLUMN java01_user_info.user_pwd is '用户密码';
comment on COLUMN java01_user_info.status is '用户状态';
comment on COLUMN java01_user_info.remark is '备注';
comment on COLUMN java01_user_info.created_by is '创建人';
comment on COLUMN java01_user_info.date_created is '创建时间';
comment on COLUMN java01_user_info.updated_by is '更新人';
comment on COLUMN java01_user_info.date_updated is '更新时间';