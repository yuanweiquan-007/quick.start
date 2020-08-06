package quick.start.repositorys.command;

import org.omg.CORBA.UNKNOWN;

/**
 * @author yuanweiquan
 */

public enum CommandType {
     /**
      * 未知类型
      */
     UNKNOWN,
     /**
      * insert语句
      */
     INSERT,
     /**
      * update语句
      */
     UPDATE,
     /**
      * delete语句
      */
     DELETE,
     /**
      * select语句
      */
     SELECT,
     /**
      * flush语句
      */
     FLUSH,
     /**
      * count语句
      */
     COUNT
}
