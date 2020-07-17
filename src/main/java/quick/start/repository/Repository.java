package quick.start.repository;

import quick.start.entity.Entity;

public interface Repository<E extends Entity> extends Writeable<E>, Readable<E> {

}
