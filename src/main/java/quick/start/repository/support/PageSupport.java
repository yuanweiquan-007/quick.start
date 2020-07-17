package quick.start.repository.support;

public interface PageSupport {

     public PageSupport limit(Integer pageNumber);

     public PageSupport limit(Integer pageSize, Integer pageNumber);

}
