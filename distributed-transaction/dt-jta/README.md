## 搭建环境
1.mysql

##sql


```sql
#trade库
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` VARCHAR(32) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `commodity_code` varchar(255) DEFAULT NULL,
  `num` int(11) DEFAULT 0,
  `money` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#member库
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
  `id` VARCHAR (32) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `money` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```