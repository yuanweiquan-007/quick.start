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

     public long getPageSize() {
          return pageSize;
     }

     public long getPageNumber() {
          return pageNumber;
     }

     public long getPageTotal() {
          return pageTotal;
     }

     public Paginator<E> setPageTotal(long pageTotal) {
          this.pageTotal = pageTotal;
          return this;
     }

     public long getIndexStart() {
          return indexStart;
     }

     public long getIndexEnd() {
          return indexEnd;
     }

     public long getTotal() {
          return total;
     }

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

     public List<E> getResults() {
          return results;
     }

     public Paginator<E> setResults(List<E> results) {
          this.results = results;
          return this;
     }

     public Paginator<E> addResult(E result) {
          this.results.add(result);
          return this;
     }

     public int size() {
          return results.size();
     }

     public boolean isEmpty() {
          return results.isEmpty();
     }

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
