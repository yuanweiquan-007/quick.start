package quick.start.repositorys.support;

/**
 * @author yuanweiquan
 */
public interface PageSupport {

     /**
      * limit
      *
      * @param pageNumber
      * @return
      */
     public PageSupport limit(Integer pageNumber);

     /**
      * limit
      *
      * @param pageSize
      * @param pageNumber
      * @return
      */
     public PageSupport limit(Integer pageSize, Integer pageNumber);

}
