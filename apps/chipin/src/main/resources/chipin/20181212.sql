
DROP TABLE IF EXISTS `chipin_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chipin_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `lottery_user_id` bigint(20) DEFAULT NULL COMMENT '投注用户ID',
  `lottery_user_name` varchar(255) DEFAULT NULL COMMENT '投注用户名',
  `task_id` bigint(20) DEFAULT NULL COMMENT '任务ID',
  `bets` varchar(5000) DEFAULT NULL COMMENT '投注内容',
  `bet_money` varchar(10) DEFAULT NULL COMMENT '单注金额',
  `total_bet_money` varchar(10) DEFAULT NULL COMMENT '全部金额',
  `bet_count` varchar(10) DEFAULT NULL COMMENT '投注码数量',
  `period_no` varchar(20) DEFAULT NULL COMMENT '期数',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `results` varchar(255) DEFAULT '' COMMENT '结果',
  `account_balance` varchar(50) DEFAULT '' COMMENT '账户余额',
  `execute_rule_name` varchar(50) DEFAULT '' COMMENT '执行规则名称',
  `bets_content` tinyint(4) DEFAULT NULL COMMENT '投注内容',
  `bets_size` tinyint(4) DEFAULT NULL COMMENT '投注长度',
  `chipin_time_scope` varchar(100) DEFAULT '' COMMENT '时间范围',
  `next_chipin_time` varchar(100) DEFAULT '' COMMENT '下次投注时间',
  `visibility` tinyint(4) DEFAULT '1' COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;