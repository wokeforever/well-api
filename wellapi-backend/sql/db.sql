-- 接口信息
create table if not exists wellapi.`interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `name` varchar(256) not null comment '接口名称',
    `description` varchar(256) not null comment '描述',
    `url` varchar(256) not null comment '接口地址',
    `requestHeader` text not null comment '请求头',
    `responseHeader` text not null comment '响应头',
    `status` int default 0 not null comment '接口状态（0-关闭，1-开启）',
    `method` varchar(256) not null comment '请求类型',
    `userId` bigint not null comment '创建人',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDeleted` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '接口信息';

insert into wellapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('钱鑫磊', '苏弘文', '204.91.241.220', '谭鸿煊', '陆越泽', 0, '金鹭洋', 311);
insert into wellapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('史博超', '秦烨霖', '77.95.16.226', '洪志泽', '蔡锦程', 0, '周烨霖', 5115);
insert into wellapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('崔煜祺', '毛睿渊', '69.43.98.165', '傅晓博', '阎浩然', 0, '吴伟宸', 1879613);
insert into wellapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('张瑞霖', '王致远', '219.5.12.154', '熊越泽', '崔烨霖', 0, '江致远', 92);
insert into wellapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('许文轩', '姜天宇', '30.98.129.221', '莫耀杰', '彭弘文', 0, '胡子轩', 315);
insert into wellapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('唐凯瑞', '彭展鹏', '83.211.2.112', '彭子涵', '郭锦程', 0, '石天翊', 35147);
insert into wellapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('邱浩宇', '陆明', '119.126.185.219', '吕伟诚', '廖凯瑞', 0, '谢旭尧', 755032599);
insert into wellapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('袁擎宇', '蔡智宸', '8.174.61.120', '谢峻熙', '沈擎苍', 0, '徐天宇', 9622);
insert into wellapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('龙烨霖', '冯明哲', '103.95.88.172', '史明哲', '田子轩', 0, '周明哲', 6);
insert into wellapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('陈伟诚', '高雨泽', '21.11.159.137', '钱志强', '江修洁', 0, '马瑾瑜', 8);
insert into wellapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('徐博文', '梁聪健', '175.87.249.189', '汪远航', '邹苑博', 0, '于鹤轩', 2);
insert into wellapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('吕懿轩', '黄思', '245.159.191.30', '蒋彬', '顾俊驰', 0, '杨文博', 2441186219);
insert into wellapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('许烨磊', '沈烨霖', '206.149.7.41', '莫致远', '魏琪', 0, '曹文轩', 6883463898);
insert into wellapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('钱致远', '吴金鑫', '194.79.249.47', '于擎宇', '罗伟宸', 0, '杜文昊', 722161937);
insert into wellapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('孔雪松', '陆智宸', '90.240.39.112', '梁哲瀚', '蔡子默', 0, '熊梓晨', 499518509);
insert into wellapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('钟鹭洋', '蒋熠彤', '190.61.191.210', '谭弘文', '王语堂', 0, '孟浩宇', 83);
insert into wellapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('孔思源', '秦哲瀚', '211.221.178.83', '朱鹏煊', '白思远', 0, '丁烨霖', 665623);
insert into wellapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('武正豪', '陶修杰', '44.160.188.194', '江苑博', '江晓博', 0, '吕天宇', 342);
insert into wellapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('龙志泽', '戴展鹏', '106.148.7.110', '阎鸿煊', '郭梓晨', 0, '郝昊焱', 6);
insert into wellapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('高驰', '孟明哲', '130.234.49.92', '潘嘉熙', '马思', 0, '金子默', 0);