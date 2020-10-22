package vn.zalopay.ducnm8.da.interact;

import io.vertx.core.Future;
import org.apache.logging.log4j.Logger;
import vn.zalopay.ducnm8.common.mapper.EntityMapper;
import vn.zalopay.ducnm8.da.BaseTransactionDA;
import vn.zalopay.ducnm8.da.Executable;
import vn.zalopay.ducnm8.model.Transfer;
import vn.zalopay.ducnm8.utils.AsyncHandler;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TransferDAImpl extends BaseTransactionDA implements TransferDA {
  private final DataSource dataSource;
  private final AsyncHandler asyncHandler;
  private static final String INSERT_TRANSFER_STATEMENT =
      "INSERT INTO transfer (`sender_id`,`receiver_id`,`amount`,`message`,`transfer_time`) VALUES (?, ?, ?, ?, ?);";
  private static final String SELECT_TRANSFER_BY_ID =
      "SELECT * FROM transfer WHERE id = ?";

  public TransferDAImpl(DataSource dataSource, AsyncHandler asyncHandler) {
    super();
    this.dataSource = dataSource;
    this.asyncHandler = asyncHandler;
  }

  @Override
  public Executable<Transfer> insert(Transfer transfer) {
    log.info("insert a transfer");
    return connection -> {
      Future<Transfer> future = Future.future();
      asyncHandler.run(
          () -> {
            Object[] params = {transfer.getSenderId(), transfer.getReceiverId(), transfer.getAmount(), transfer.getMessage(), transfer.getTransferTime()};
            try {
              long id = executeWithParamsAndGetId(connection.unwrap(), INSERT_TRANSFER_STATEMENT, params, "insertTransfer");
              transfer.setId(id);
              future.complete(transfer);

              log.info("insert a transfer successfully");
            } catch (Exception e) {
              log.error(e.getMessage());
              future.fail(e.getMessage());
            }
          });
      return future;
    };
  }

  @Override
  public Future<Transfer> selectTransferById(long id) {
    Future<Transfer> future = Future.future();
    asyncHandler.run(
        () -> {
          Object[] params = {id};
          queryEntity(
              "queryTransfer",
              future,
              SELECT_TRANSFER_BY_ID,
              params,
              this::mapRs2EntityTransfer,
              dataSource::getConnection,
              false);
        });

    return future;
  }

  private Transfer mapRs2EntityTransfer(ResultSet resultSet) throws Exception {
    Transfer transfer = null;

    while (resultSet.next()) {
      transfer = new Transfer();
      EntityMapper.getInstance().loadResultSetIntoObject(resultSet, transfer);
    }

    return transfer;
  }
}
