package quick.start.repositorys.support;

/**
 * @author yuanweiquan
 */
public interface SortSupport {

     /**
      * asc
      * 升序
      *
      * @param field 字段
      * @return {@link SortSupport}
      */
     public SortSupport asc(String field);

     /**
      * desc
      * 降序
      *
      * @param field 字段
      * @return {@link SortSupport}
      */
     public SortSupport desc(String field);

}
