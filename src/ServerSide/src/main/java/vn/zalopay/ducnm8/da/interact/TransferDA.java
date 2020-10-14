package vn.zalopay.ducnm8.da.interact;

import io.vertx.core.Future;
import vn.zalopay.ducnm8.da.Executable;
import vn.zalopay.ducnm8.model.Transfer;

public interface TransferDA {
    Executable<Transfer> insert(Transfer transfer);

    Future<Transfer> selectTransferById(long id);
}
