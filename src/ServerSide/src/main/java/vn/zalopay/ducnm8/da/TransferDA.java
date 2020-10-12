package vn.zalopay.ducnm8.da;

import io.vertx.core.Future;
import vn.zalopay.ducnm8.model.Transfer;

public interface TransferDA {
    Executable<Transfer> insert(Transfer transfer);

    Future<Transfer> selectTransferById(long id);
}
