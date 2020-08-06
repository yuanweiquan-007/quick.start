package quick.start.repositorys.support;

/**
 * @author yuanweiquan
 */
public interface SortSupport {

     /**
      * 升序
      *
      * @param field
      * @return
      */
     public SortSupport asc(String field);

     /**
      * 降序
      *
      * @param field
      * @return
      */
     public SortSupport desc(String field);

}
