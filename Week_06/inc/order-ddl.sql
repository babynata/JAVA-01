CREATE TABLE java01_order_info(
    id bigint not null AUTO_INCREMENT PRIMARY KEY ,
    order_id VARCHAR(32) NOT NULL,
    user_id VARCHAR(32) not null,
    prd_snatshot json not null,
    express_id VARCHAR(32) NOT NULL,
    order_date TIMESTAMP NOT NULL ,
    status int default 0 not null,
    remark VARCHAR ,
    created_by VARCHAR(100) not null,
    date_created TIMESTAMP not null,
    updated_by VARCHAR(100) not null,
    date_updated TIMESTAMP not null
);
comment on table java01_order_info is '商品表';
comment on COLUMN java01_order_info.id is '主键';
comment on COLUMN java01_order_info.order_id is '订单ID';
comment on COLUMN java01_order_info.user_id is '用户ID';
comment on COLUMN java01_order_info.prd_snatshot is '商品快照';
comment on COLUMN java01_order_info.express_id is '物流信息ID';
comment on COLUMN java01_order_info.order_date is '订单时间';
comment on COLUMN java01_order_info.status is '订单状态';
comment on COLUMN java01_order_info.remark is '备注';
comment on COLUMN java01_order_info.created_by is '创建人';
comment on COLUMN java01_order_info.date_created is '创建时间';
comment on COLUMN java01_order_info.updated_by is '更新人';
comment on COLUMN java01_order_info.date_updated is '更新时间';