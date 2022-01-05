package quick.start.repositorys.support;

/**
 * @author yuanweiquan
 */
public interface PageSupport {

     /**
      * 限制
      * limit
      *
      * @param pageNumber 当前页数
      * @return {@link PageSupport}
      */
     public PageSupport limit(Integer pageNumber);

     /**
      * 限制
      * limit
      *
      * @param pageSize   每页大小
      * @param pageNumber 当前页数
      * @return {@link PageSupport}
      */
     public PageSupport limit(Integer pageSize, Integer pageNumber);

}
