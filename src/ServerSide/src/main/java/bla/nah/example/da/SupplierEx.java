package bla.nah.example.da;

/** Created by thinhda. Date: 2019-11-08 */
@FunctionalInterface
public interface SupplierEx<T> {
  T get() throws Exception;
}
