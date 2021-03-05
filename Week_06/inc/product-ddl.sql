CREATE TABLE java01_product_info(
    id bigint not null AUTO_INCREMENT PRIMARY KEY ,
    prd_id VARCHAR(32) NOT NULL,
    prd_name VARCHAR(20) not null,
    prd_price NUMERIC(20,7) not null,
    status int default 0 not null,
    remark VARCHAR ,
    created_by VARCHAR(100) not null,
    date_created TIMESTAMP not null,
    updated_by VARCHAR(100) not null,
    date_updated TIMESTAMP not null
);
comment on table java01_product_info is '商品表';
comment on COLUMN java01_product_info.id is '主键';
comment on COLUMN java01_product_info.prd_id is '商品ID';
comment on COLUMN java01_product_info.prd_name is '商品名称';
comment on COLUMN java01_product_info.prd_price is '商品价格';
comment on COLUMN java01_product_info.status is '商品状态';
comment on COLUMN java01_product_info.remark is '备注';
comment on COLUMN java01_product_info.created_by is '创建人';
comment on COLUMN java01_product_info.date_created is '创建时间';
comment on COLUMN java01_product_info.updated_by is '更新人';
comment on COLUMN java01_product_info.date_updated is '更新时间';