package vn.zalopay.ducnm8.da;

/**
 * Created by thinhda. Date: 2019-11-08
 */
@FunctionalInterface
public interface FunctionEx<T, R> {
  R apply(T t) throws Exception;
}
