package com.yuanwiequan.common.repository.command;

import lombok.Data;

import java.util.List;

/**
 * 基础数据
 */
@Data
public class ExecuteCommandMeta {
     //执行语句的内容
     protected String command;
     //执行参数
     protected List<Object> parames;
}
