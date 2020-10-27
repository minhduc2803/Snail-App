package vn.zalopay.ducnm8.da.interact;

import io.vertx.core.Future;
import vn.zalopay.ducnm8.common.mapper.EntityMapper;
import vn.zalopay.ducnm8.da.BaseTransactionDA;
import vn.zalopay.ducnm8.da.Executable;
import vn.zalopay.ducnm8.model.TransferHistory;
import vn.zalopay.ducnm8.utils.AsyncHandler;
import lombok.extern.log4j.Log4j2;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.ArrayList;

@Log4j2
public class TransferHistoryDAImpl extends BaseTransactionDA implements TransferHistoryDA {

  private final DataSource dataSource;
  private final AsyncHandler asyncHandler;
  private static final String INSERT_TRANSFER_HISTORY_STATEMENT =
      "INSERT INTO transfer_history (`transfer_id`,`user_id`,`partner_id`,`transfer_type`,`balance`) VALUES (?, ?, ?, ?, ?)";
  private static final String SELECT_TRANSFER_HISTORY_BY_ACCOUNT_ID =
      "SELECT H.id, H.transfer_id, H.user_id, H.partner_id, H.transfer_type, H.balance,\n" +
          "T.amount, T.amount, T.message, T.transfer_time,\n" +
          "C.user_name, C.full_name\n" +
          "FROM transfer_history as H\n" +
          "INNER JOIN transfer as T\n" +
          "ON H.transfer_id = T.id\n" +
          "INNER JOIN account as C\n" +
          "ON H.partner_id = C.id\n" +
          "WHERE H.user_id = ?\n" +
          "ORDER BY H.id DESC\n" +
          "LIMIT ?, 30;";

  public TransferHistoryDAImpl(DataSource dataSource, AsyncHandler asyncHandler) {
    this.dataSource = dataSource;
    this.asyncHandler = asyncHandler;
  }

  @Override
  public Executable<TransferHistory> insertInsideTransaction(TransferHistory transferHistory) {
    log.info("insert a new transfer history, userId = {} partnerId = {}", transferHistory.getUserId(), transferHistory.getPartnerId());
    return connection -> {
      Future<TransferHistory> future = Future.future();
      asyncHandler.run(
          () -> {
            Object[] params = {transferHistory.getTransferId(), transferHistory.getUserId(), transferHistory.getPartnerId(), transferHistory.getTransferType(), transferHistory.getBalance()};
            try {
              long id = executeWithParamsAndGetId(connection.unwrap(), INSERT_TRANSFER_HISTORY_STATEMENT, params, "insertTransferHistory", true);
              transferHistory.setId(id);
              future.complete(transferHistory);

            } catch (Exception e) {
              future.fail(e.getMessage());
              String reason = String.format("cannot insert a transfer history, userId = {} partnerId = {}~ cause: %s", transferHistory.getUserId(), transferHistory.getPartnerId(), e.getMessage());
              log.error(reason);
            }
          });
      return future;
    };
  }

  @Override
  public Future<ArrayList<TransferHistory>> getTransferHistoryByAccountId(long id, long offset) {
    log.info("select a list transfer history, accountId = {} offset = {}", id, offset);
    Future<ArrayList<TransferHistory>> future = Future.future();
    asyncHandler.run(
        () -> {
          Object[] params = {id, offset};
          queryEntity(
              "queryTransferHistory",
              future,
              SELECT_TRANSFER_HISTORY_BY_ACCOUNT_ID,
              params,
              this::mapRs2EntityTransferHistory,
              dataSource::getConnection,
              false);
        });

    return future;
  }

  private ArrayList<TransferHistory> mapRs2EntityTransferHistory(ResultSet resultSet) throws Exception {
    TransferHistory transferHistory = null;
    ArrayList<TransferHistory> historyList = new ArrayList<>();
    while (resultSet.next()) {
      transferHistory = new TransferHistory();
      EntityMapper.getInstance().loadResultSetIntoObject(resultSet, transferHistory);
      historyList.add(transferHistory);
    }

    return historyList;
  }
}
