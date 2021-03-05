CREATE TABLE java01_order_info(
    id bigint not null AUTO_INCREMENT PRIMARY KEY comment '主键',
    order_id VARCHAR(32) NOT NULL comment '订单ID',
    user_id VARCHAR(32) not null comment '用户ID',
    prd_snatshot json not null comment '商品快照',
    express_id VARCHAR(32) NOT NULL comment '物流信息ID',
    order_date TIMESTAMP NOT NULL comment '订单时间',
    status int default 0 not null comment '订单状态',
    remark VARCHAR(200) comment '备注',
    created_by VARCHAR(100) not null comment '创建人',
    date_created TIMESTAMP not null default now() comment '创建时间',
    updated_by VARCHAR(100) not null comment '更新人',
    date_updated TIMESTAMP not null default now() comment '更新时间'
) comment '订单表';
