package quick.start.repository.command;

import quick.start.Builder;
import quick.start.entity.Entity;

public abstract class Count<E extends Entity> extends CommandForEntity<E> implements Builder {


}
