package com.yuanwiequan.common.repository;

import com.yuanwiequan.common.entity.Entity;

public interface Repository<E extends Entity> extends Writeable<E>, Readable<E> {

}
