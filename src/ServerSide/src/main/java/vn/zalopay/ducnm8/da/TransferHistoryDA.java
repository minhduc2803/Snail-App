package vn.zalopay.ducnm8.da;

import io.vertx.core.Future;
import vn.zalopay.ducnm8.model.TransferHistory;

import java.util.ArrayList;

public interface TransferHistoryDA {
    Executable<TransferHistory> insert(TransferHistory transferHistory);

    Future<ArrayList<TransferHistory>> selectTransferHistoryByAccountId(long id);
}
