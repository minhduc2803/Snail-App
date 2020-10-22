package vn.zalopay.ducnm8.da.interact;

import io.vertx.core.Future;
import vn.zalopay.ducnm8.da.Executable;
import vn.zalopay.ducnm8.model.TransferHistory;

import java.util.ArrayList;

public interface TransferHistoryDA {
  Executable<TransferHistory> insert(TransferHistory transferHistory);

  Future<TransferHistory> insertOutSideTransaction(TransferHistory transferHistory);

  Future<ArrayList<TransferHistory>> getTransferHistoryByAccountId(long id, long offset);
}
