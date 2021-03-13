CREATE TABLE java01_order_info_0(
    id bigint(20) not null AUTO_INCREMENT PRIMARY KEY comment '主键',
    order_id bigint(20) NOT NULL comment '订单ID',
    user_id bigint(20) not null comment '用户ID',
    express_id VARCHAR(32) NOT NULL comment '物流信息ID',
    order_date TIMESTAMP NOT NULL comment '订单时间',
    status int default 0 not null comment '订单状态',
    remark VARCHAR(200) comment '备注',
    created_by VARCHAR(100) not null comment '创建人',
    date_created TIMESTAMP not null default now() comment '创建时间',
    updated_by VARCHAR(100) not null comment '更新人',
    date_updated TIMESTAMP not null default now() comment '更新时间'
) comment '订单表分表00';

CREATE TABLE java01_order_info_1(
    id bigint(20) not null AUTO_INCREMENT PRIMARY KEY comment '主键',
    order_id bigint(20) NOT NULL comment '订单ID',
    user_id bigint(20) not null comment '用户ID',
    express_id VARCHAR(32) NOT NULL comment '物流信息ID',
    order_date TIMESTAMP NOT NULL comment '订单时间',
    status int default 0 not null comment '订单状态',
    remark VARCHAR(200) comment '备注',
    created_by VARCHAR(100) not null comment '创建人',
    date_created TIMESTAMP not null default now() comment '创建时间',
    updated_by VARCHAR(100) not null comment '更新人',
    date_updated TIMESTAMP not null default now() comment '更新时间'
) comment '订单表分表01';

CREATE TABLE java01_order_info_2(
    id bigint(20) not null AUTO_INCREMENT PRIMARY KEY comment '主键',
    order_id bigint(20) NOT NULL comment '订单ID',
    user_id bigint(20) not null comment '用户ID',
    express_id VARCHAR(32) NOT NULL comment '物流信息ID',
    order_date TIMESTAMP NOT NULL comment '订单时间',
    status int default 0 not null comment '订单状态',
    remark VARCHAR(200) comment '备注',
    created_by VARCHAR(100) not null comment '创建人',
    date_created TIMESTAMP not null default now() comment '创建时间',
    updated_by VARCHAR(100) not null comment '更新人',
    date_updated TIMESTAMP not null default now() comment '更新时间'
) comment '订单表分表02';

CREATE TABLE java01_order_info_3(
    id bigint(20) not null AUTO_INCREMENT PRIMARY KEY comment '主键',
    order_id bigint(20) NOT NULL comment '订单ID',
    user_id bigint(20) not null comment '用户ID',
    express_id VARCHAR(32) NOT NULL comment '物流信息ID',
    order_date TIMESTAMP NOT NULL comment '订单时间',
    status int default 0 not null comment '订单状态',
    remark VARCHAR(200) comment '备注',
    created_by VARCHAR(100) not null comment '创建人',
    date_created TIMESTAMP not null default now() comment '创建时间',
    updated_by VARCHAR(100) not null comment '更新人',
    date_updated TIMESTAMP not null default now() comment '更新时间'
) comment '订单表分表03';

CREATE TABLE java01_order_info_4(
    id bigint(20) not null AUTO_INCREMENT PRIMARY KEY comment '主键',
    order_id bigint(20) NOT NULL comment '订单ID',
    user_id bigint(20) not null comment '用户ID',
    express_id VARCHAR(32) NOT NULL comment '物流信息ID',
    order_date TIMESTAMP NOT NULL comment '订单时间',
    status int default 0 not null comment '订单状态',
    remark VARCHAR(200) comment '备注',
    created_by VARCHAR(100) not null comment '创建人',
    date_created TIMESTAMP not null default now() comment '创建时间',
    updated_by VARCHAR(100) not null comment '更新人',
    date_updated TIMESTAMP not null default now() comment '更新时间'
) comment '订单表分表04';

CREATE TABLE java01_order_info_5(
    id bigint(20) not null AUTO_INCREMENT PRIMARY KEY comment '主键',
    order_id bigint(20) NOT NULL comment '订单ID',
    user_id bigint(20) not null comment '用户ID',
    express_id VARCHAR(32) NOT NULL comment '物流信息ID',
    order_date TIMESTAMP NOT NULL comment '订单时间',
    status int default 0 not null comment '订单状态',
    remark VARCHAR(200) comment '备注',
    created_by VARCHAR(100) not null comment '创建人',
    date_created TIMESTAMP not null default now() comment '创建时间',
    updated_by VARCHAR(100) not null comment '更新人',
    date_updated TIMESTAMP not null default now() comment '更新时间'
) comment '订单表分表05';

CREATE TABLE java01_order_info_6(
    id bigint(20) not null AUTO_INCREMENT PRIMARY KEY comment '主键',
    order_id bigint(20) NOT NULL comment '订单ID',
    user_id bigint(20) not null comment '用户ID',
    express_id VARCHAR(32) NOT NULL comment '物流信息ID',
    order_date TIMESTAMP NOT NULL comment '订单时间',
    status int default 0 not null comment '订单状态',
    remark VARCHAR(200) comment '备注',
    created_by VARCHAR(100) not null comment '创建人',
    date_created TIMESTAMP not null default now() comment '创建时间',
    updated_by VARCHAR(100) not null comment '更新人',
    date_updated TIMESTAMP not null default now() comment '更新时间'
) comment '订单表分表06';

CREATE TABLE java01_order_info_7(
    id bigint not null AUTO_INCREMENT PRIMARY KEY comment '主键',
    order_id bigint(20) NOT NULL comment '订单ID',
    user_id bigint(20) not null comment '用户ID',
    express_id VARCHAR(32) NOT NULL comment '物流信息ID',
    order_date TIMESTAMP NOT NULL comment '订单时间',
    status int default 0 not null comment '订单状态',
    remark VARCHAR(200) comment '备注',
    created_by VARCHAR(100) not null comment '创建人',
    date_created TIMESTAMP not null default now() comment '创建时间',
    updated_by VARCHAR(100) not null comment '更新人',
    date_updated TIMESTAMP not null default now() comment '更新时间'
) comment '订单表分表07';

CREATE TABLE java01_order_info_8(
    id bigint(20) not null AUTO_INCREMENT PRIMARY KEY comment '主键',
    order_id bigint(20) NOT NULL comment '订单ID',
    user_id bigint(20) not null comment '用户ID',
    express_id VARCHAR(32) NOT NULL comment '物流信息ID',
    order_date TIMESTAMP NOT NULL comment '订单时间',
    status int default 0 not null comment '订单状态',
    remark VARCHAR(200) comment '备注',
    created_by VARCHAR(100) not null comment '创建人',
    date_created TIMESTAMP not null default now() comment '创建时间',
    updated_by VARCHAR(100) not null comment '更新人',
    date_updated TIMESTAMP not null default now() comment '更新时间'
) comment '订单表分表08';

CREATE TABLE java01_order_info_9(
    id bigint(20) not null AUTO_INCREMENT PRIMARY KEY comment '主键',
    order_id bigint(20) NOT NULL comment '订单ID',
    user_id bigint(20) not null comment '用户ID',
    express_id VARCHAR(32) NOT NULL comment '物流信息ID',
    order_date TIMESTAMP NOT NULL comment '订单时间',
    status int default 0 not null comment '订单状态',
    remark VARCHAR(200) comment '备注',
    created_by VARCHAR(100) not null comment '创建人',
    date_created TIMESTAMP not null default now() comment '创建时间',
    updated_by VARCHAR(100) not null comment '更新人',
    date_updated TIMESTAMP not null default now() comment '更新时间'
) comment '订单表分表09';

CREATE TABLE java01_order_info_10(
    id bigint(20) not null AUTO_INCREMENT PRIMARY KEY comment '主键',
    order_id bigint(20) NOT NULL comment '订单ID',
    user_id bigint(20) not null comment '用户ID',
    express_id VARCHAR(32) NOT NULL comment '物流信息ID',
    order_date TIMESTAMP NOT NULL comment '订单时间',
    status int default 0 not null comment '订单状态',
    remark VARCHAR(200) comment '备注',
    created_by VARCHAR(100) not null comment '创建人',
    date_created TIMESTAMP not null default now() comment '创建时间',
    updated_by VARCHAR(100) not null comment '更新人',
    date_updated TIMESTAMP not null default now() comment '更新时间'
) comment '订单表分表10';

CREATE TABLE java01_order_info_11(
    id bigint(20) not null AUTO_INCREMENT PRIMARY KEY comment '主键',
    order_id bigint(20) NOT NULL comment '订单ID',
    user_id bigint(20) not null comment '用户ID',
    express_id VARCHAR(32) NOT NULL comment '物流信息ID',
    order_date TIMESTAMP NOT NULL comment '订单时间',
    status int default 0 not null comment '订单状态',
    remark VARCHAR(200) comment '备注',
    created_by VARCHAR(100) not null comment '创建人',
    date_created TIMESTAMP not null default now() comment '创建时间',
    updated_by VARCHAR(100) not null comment '更新人',
    date_updated TIMESTAMP not null default now() comment '更新时间'
) comment '订单表分表11';

CREATE TABLE java01_order_info_12(
    id bigint(20) not null AUTO_INCREMENT PRIMARY KEY comment '主键',
    order_id bigint(20) NOT NULL comment '订单ID',
    user_id bigint(20) not null comment '用户ID',
    express_id VARCHAR(32) NOT NULL comment '物流信息ID',
    order_date TIMESTAMP NOT NULL comment '订单时间',
    status int default 0 not null comment '订单状态',
    remark VARCHAR(200) comment '备注',
    created_by VARCHAR(100) not null comment '创建人',
    date_created TIMESTAMP not null default now() comment '创建时间',
    updated_by VARCHAR(100) not null comment '更新人',
    date_updated TIMESTAMP not null default now() comment '更新时间'
) comment '订单表分表12';

CREATE TABLE java01_order_info_13(
    id bigint(20) not null AUTO_INCREMENT PRIMARY KEY comment '主键',
    order_id bigint(20) NOT NULL comment '订单ID',
    user_id bigint(20) not null comment '用户ID',
    express_id VARCHAR(32) NOT NULL comment '物流信息ID',
    order_date TIMESTAMP NOT NULL comment '订单时间',
    status int default 0 not null comment '订单状态',
    remark VARCHAR(200) comment '备注',
    created_by VARCHAR(100) not null comment '创建人',
    date_created TIMESTAMP not null default now() comment '创建时间',
    updated_by VARCHAR(100) not null comment '更新人',
    date_updated TIMESTAMP not null default now() comment '更新时间'
) comment '订单表分表13';

CREATE TABLE java01_order_info_14(
    id bigint(20) not null AUTO_INCREMENT PRIMARY KEY comment '主键',
    order_id bigint(20) NOT NULL comment '订单ID',
    user_id bigint(20) not null comment '用户ID',
    express_id VARCHAR(32) NOT NULL comment '物流信息ID',
    order_date TIMESTAMP NOT NULL comment '订单时间',
    status int default 0 not null comment '订单状态',
    remark VARCHAR(200) comment '备注',
    created_by VARCHAR(100) not null comment '创建人',
    date_created TIMESTAMP not null default now() comment '创建时间',
    updated_by VARCHAR(100) not null comment '更新人',
    date_updated TIMESTAMP not null default now() comment '更新时间'
) comment '订单表分表14';

CREATE TABLE java01_order_info_15(
    id bigint(20) not null AUTO_INCREMENT PRIMARY KEY comment '主键',
    order_id bigint(20) NOT NULL comment '订单ID',
    user_id bigint(20) not null comment '用户ID',
    express_id VARCHAR(32) NOT NULL comment '物流信息ID',
    order_date TIMESTAMP NOT NULL comment '订单时间',
    status int default 0 not null comment '订单状态',
    remark VARCHAR(200) comment '备注',
    created_by VARCHAR(100) not null comment '创建人',
    date_created TIMESTAMP not null default now() comment '创建时间',
    updated_by VARCHAR(100) not null comment '更新人',
    date_updated TIMESTAMP not null default now() comment '更新时间'
) comment '订单表分表15';

