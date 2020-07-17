package com.yuanwiequan.common.repository.command;

import com.yuanwiequan.common.Builder;
import com.yuanwiequan.common.entity.Entity;

public abstract class Update<E extends Entity> extends CommandForEntity<E> implements Builder {

}
