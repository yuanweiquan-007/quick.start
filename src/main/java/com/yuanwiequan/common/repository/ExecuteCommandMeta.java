package com.yuanwiequan.common.repository;

import lombok.Data;

/**
 * 基础数据
 */
@Data
public class ExecuteCommandMeta {
     //执行语句的类型
     protected CommandType type;
     //执行语句的内容
     protected String command;
     //条件
     protected Conditions conditions;
}
