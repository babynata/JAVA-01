CREATE TABLE java01_product_info(
    id bigint not null AUTO_INCREMENT PRIMARY KEY comment '主键',
    prd_id VARCHAR(32) NOT NULL comment '商品ID',
    prd_name VARCHAR(20) not null comment '商品名称',
    prd_price NUMERIC(20,7) not null comment '商品价格',
    status int default 0 not null comment '商品状态',
    remark VARCHAR(200) comment '备注',
    created_by VARCHAR(100) not null comment '创建人',
    date_created TIMESTAMP not null default now() comment '创建时间',
    updated_by VARCHAR(100) not null comment '更新人',
    date_updated TIMESTAMP not null default now() comment '更新时间'
) comment '商品表';
