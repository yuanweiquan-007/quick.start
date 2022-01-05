package quick.start.collection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuanweiquan
 */
public class Paginator<E> implements Serializable {

     private static final long serialVersionUID = 1L;

     private long pageSize;

     private long pageNumber;

     private long pageTotal;

     private long indexStart;

     private long indexEnd;

     private long total;

     private List<E> results = new ArrayList<>();

     /**
      * paginator
      *
      * @param pageSize   每页大小
      * @param pageNumber 当前页数
      */
     public Paginator(long pageSize, long pageNumber) {
          if (pageSize > 0) {
               if (pageNumber < 0) {
                    pageNumber = 1;
               }
               this.pageSize = pageSize;
               this.pageNumber = pageNumber;
               this.indexStart = pageSize * (pageNumber - 1);
               this.indexEnd = this.indexStart + pageSize;
          }
     }

     /**
      * 获取页面大小
      *
      * @return long
      */
     public long getPageSize() {
          return pageSize;
     }

     /**
      * 当前页
      *
      * @return long
      */
     public long getPageNumber() {
          return pageNumber;
     }

     /**
      * 获取总数
      *
      * @return long
      */
     public long getPageTotal() {
          return pageTotal;
     }

     /**
      * 设置总数
      *
      * @param pageTotal 页面总
      * @return {@link Paginator}
      */
     public Paginator<E> setPageTotal(long pageTotal) {
          this.pageTotal = pageTotal;
          return this;
     }

     /**
      * 开始
      *
      * @return long
      */
     public long getIndexStart() {
          return indexStart;
     }

     /**
      * 结束
      *
      * @return long
      */
     public long getIndexEnd() {
          return indexEnd;
     }

     /**
      * 得到总
      *
      * @return long
      */
     public long getTotal() {
          return total;
     }

     /**
      * 设置总数
      *
      * @param total 总计
      * @return {@link Paginator}
      */
     public Paginator<E> setTotal(long total) {
          this.total = 0;
          this.pageTotal = 0;
          if (total > 0) {
               this.total = total;
               if (this.pageSize > 0) {
                    this.pageTotal = calculatePageTotal(total, pageSize);
                    if (total < this.indexEnd) {
                         this.indexEnd = total;
                    }
               }
          }
          return this;
     }

     /**
      * 得到结果
      *
      * @return {@link List}
      */
     public List<E> getResults() {
          return results;
     }

     /**
      * 设置结果
      *
      * @param results 结果
      * @return {@link Paginator}
      */
     public Paginator<E> setResults(List<E> results) {
          this.results = results;
          return this;
     }

     /**
      * 添加结果
      *
      * @param result 结果
      * @return {@link Paginator}
      */
     public Paginator<E> addResult(E result) {
          this.results.add(result);
          return this;
     }

     /**
      * 大小
      *
      * @return int
      */
     public int size() {
          return results.size();
     }

     /**
      * 是否为空
      *
      * @return boolean
      */
     public boolean isEmpty() {
          return results.isEmpty();
     }

     /**
      * calculatePageTotal
      *
      * @param total    总计
      * @param pageSize 每页大小
      * @return long
      */
     public static long calculatePageTotal(long total, long pageSize) {
          if (total <= 0 || pageSize <= 0) {
               return 0;
          }
          return (long) Math.ceil((double) total / (double) pageSize);
     }

     @Override
     public String toString() {
          return String.format("{page: {size: %d, number: %d, total: %d}, record: {start: %d, end: %d, total: %d}}", pageSize, pageNumber, pageTotal, indexStart, indexEnd, total);
     }

}
