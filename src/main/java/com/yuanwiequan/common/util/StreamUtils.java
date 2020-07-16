package com.yuanwiequan.common.util;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamUtils {

     public static <T> Stream<T> of(Iterator<T> iterator, boolean parallel) {
          return of(() -> iterator, parallel);
     }

     public static <T> Stream<T> of(Iterator<T> iterator) {
          return of(iterator, false);
     }

     public static <T> Stream<T> of(Iterable<T> iterable, boolean parallel) {
          return StreamSupport.stream(iterable.spliterator(), parallel);
     }

     public static <T> Stream<T> of(Iterable<T> iterable) {
          return of(iterable, false);
     }

}
