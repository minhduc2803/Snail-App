package vn.zalopay.ducnm8.da.interact;

import io.vertx.core.Future;
import org.apache.logging.log4j.Logger;
import vn.zalopay.ducnm8.common.mapper.EntityMapper;
import vn.zalopay.ducnm8.da.BaseTransactionDA;
import vn.zalopay.ducnm8.da.Executable;
import vn.zalopay.ducnm8.model.Account;
import vn.zalopay.ducnm8.model.Balance;
import vn.zalopay.ducnm8.model.User;
import vn.zalopay.ducnm8.model.UserWithoutPassword;
import vn.zalopay.ducnm8.utils.AsyncHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDAImpl extends BaseTransactionDA implements AccountDA {
  private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(AccountDAImpl.class);
  private final DataSource dataSource;
  private final AsyncHandler asyncHandler;
  private static final String INSERT_USER_STATEMENT =
      "INSERT INTO account (`user_name`,`full_name`,`password`," +
          "`balance`,`last_time_update_balance`,`number_notification`) " +
          "VALUES (?, ?, ?, ?, ?, ?)";
  private static final String SELECT_ACCOUNT_BY_ID =
      "SELECT * FROM account WHERE id = ?";
  private static final String SELECT_USER_BY_USERNAME =
      "SELECT id, user_name, full_name, password FROM account WHERE user_name = ?";
  private static final String SELECT_USER_LIST =
      "SELECT * FROM account WHERE id != ?";
  private static final String SELECT_FOR_UPDATE_TWO_ACCOUNTS =
      "SELECT * FROM account WHERE id = ? or id = ? FOR UPDATE";
  private static final String SELECT_TWO_ACCOUNT =
      "SELECT * FROM account WHERE id = ? or id = ?";
  private static final String SELECT_BALANCE_BY_ID =
      "SELECT balance, last_time_update_balance, number_notification FROM account WHERE id = ?";
  private static final String UPDATE_BALANCE_BY_AMOUNT =
      "UPDATE account \n" +
          "SET balance = balance + ?, last_time_update_balance = ?\n" +
          "WHERE id = ?;";
  private static final String UPDATE_NUMBER_NOTIFICATION =
      "UPDATE account SET number_notification = ? WHERE id = ?";

  public AccountDAImpl(DataSource dataSource, AsyncHandler asyncHandler) {
    super();
    this.dataSource = dataSource;
    this.asyncHandler = asyncHandler;
  }


  @Override
  public Future<Account> insert(Account account) {
    log.info("insert a user");

    Future<Account> future = Future.future();
    asyncHandler.run(
        () -> {
          Object[] params = {account.getUsername(), account.getFullName(), account.getPassword(),
              account.getBalance(), account.getLastTimeUpdateBalance(), account.getNumberNotification()};
          try {
            long id = executeWithParamsAndGetId(dataSource::getConnection, INSERT_USER_STATEMENT, params, "insertUser");
            account.setId(id);
            future.complete(account);
          } catch (Exception e) {
            future.fail(e);
          }
        });
    return future;

  }

  @Override
  public Future<Account> selectAccountById(long id) {
    log.info("select account by id {}", id);
    Future<Account> future = Future.future();
    asyncHandler.run(
        () -> {
          Object[] params = {id};
          queryEntity(
              "queryUser",
              future,
              SELECT_ACCOUNT_BY_ID,
              params,
              this::mapRs2EntityAccount,
              dataSource::getConnection,
              false);
        });

    return future;
  }

  @Override
  public Executable<ArrayList<Account>> selectTwoAccountsInsideTransaction(long sender, long receiver) {
    log.info("Select account by id inside transaction");
    return connection -> {
      Future<ArrayList<Account>> future = Future.future();
      asyncHandler.run(
          () -> {
            Object[] params = {sender, receiver};
            queryEntity(
                "queryUser",
                future,
                SELECT_TWO_ACCOUNT,
                params,
                this::mapRs2EntityAccountList,
                (Connection) connection.unwrap(),
                true);
          });
      return future;
    };
  }


  @Override
  public Future<User> selectUserByUsername(String Username) {
    log.info("select a user by username: {}", Username);
    Future<User> future = Future.future();
    asyncHandler.run(
        () -> {
          Object[] params = {Username};
          queryEntity(
              "queryUser",
              future,
              SELECT_USER_BY_USERNAME,
              params,
              this::mapRs2EntityUser,
              dataSource::getConnection,
              false);
        });

    return future;
  }

  @Override
  public Future<Balance> selectBalanceById(long id) {
    log.info("select balance by id: {}", id);
    Future<Balance> future = Future.future();
    asyncHandler.run(
        () -> {
          Object[] params = {id};
          queryEntity(
              "queryBalance",
              future,
              SELECT_BALANCE_BY_ID,
              params,
              this::mapRs2EntityBalance,
              dataSource::getConnection,
              false);
        });

    return future;
  }

  @Override
  public Executable<Account> plusBalanceByAmount(long id, long amount, long lastTimeUpdate) {
    log.info("update balance: (id={},amount={})", id, amount);
    return connection -> {
      Future<Account> future = Future.future();
      asyncHandler.run(
          () -> {
            Object[] params = {amount, lastTimeUpdate, id};
            try {
              executeWithParamsAndGetId(connection.unwrap(), UPDATE_BALANCE_BY_AMOUNT, params, "updateBalance", true);
              Account account = Account.builder().id(id).build();
              future.complete(account);
            } catch (Exception e) {
              future.fail(e);
            }
          });
      return future;
    };
  }

  @Override
  public Executable<Account> updateNumberNotification(long id, int number) {
    log.info("update number notifications: account_id={}, number={}", id, number);
    return connection -> {
      Future<Account> future = Future.future();
      asyncHandler.run(
          () -> {
            Object[] params = {number, id};
            try {
              executeWithParamsAndGetId(connection.unwrap(), UPDATE_NUMBER_NOTIFICATION, params, "updateNumberNotification", false);
              Account account = Account.builder().id(id).build();
              future.complete(account);
            } catch (Exception e) {
              future.fail(e);
            }
          });
      return future;
    };
  }

  @Override
  public Future<ArrayList<UserWithoutPassword>> selectUserList(long id) {
    log.info("select list user except one user by id: {}", id);
    Future<ArrayList<UserWithoutPassword>> future = Future.future();
    asyncHandler.run(
        () -> {
          Object[] params = {id};
          queryEntity(
              "queryUserList",
              future,
              SELECT_USER_LIST,
              params,
              this::mapRs2EntityUserList,
              dataSource::getConnection,
              false);
        });

    return future;
  }

  @Override
  public Executable<ArrayList<Account>> selectForUpdateTwoAccount(long sender, long receiver) {
    log.info("select for update. sender = {}, receiver = {}", sender, receiver);
    return connection -> {
      Future<ArrayList<Account>> future = Future.future();
      asyncHandler.run(
          () -> {
            Object[] params = {sender, receiver};
            queryEntity(
                "queryAccounts",
                future,
                SELECT_FOR_UPDATE_TWO_ACCOUNTS,
                params,
                this::mapRs2EntityAccountList,
                (Connection) connection.unwrap(),
                true);
          });
      return future;
    };
  }

  private User mapRs2EntityUser(ResultSet resultSet) throws Exception {
    User user = null;

    while (resultSet.next()) {
      user = new User();
      EntityMapper.getInstance().loadResultSetIntoObject(resultSet, user);
    }

    return user;
  }

  private Account mapRs2EntityAccount(ResultSet resultSet) throws Exception {
    Account account = null;

    while (resultSet.next()) {
      account = new Account();
      EntityMapper.getInstance().loadResultSetIntoObject(resultSet, account);
    }

    return account;
  }

  private ArrayList<UserWithoutPassword> mapRs2EntityUserList(ResultSet resultSet) throws Exception {
    UserWithoutPassword user = null;
    ArrayList<UserWithoutPassword> userList = new ArrayList<>();
    while (resultSet.next()) {
      user = new UserWithoutPassword();
      EntityMapper.getInstance().loadResultSetIntoObject(resultSet, user);
      userList.add(user);
    }

    return userList;
  }

  private ArrayList<Account> mapRs2EntityAccountList(ResultSet resultSet) throws Exception {
    Account account = null;
    ArrayList<Account> accountList = new ArrayList<>();
    while (resultSet.next()) {
      account = new Account();
      EntityMapper.getInstance().loadResultSetIntoObject(resultSet, account);
      accountList.add(account);
    }

    return accountList;
  }

  private Balance mapRs2EntityBalance(ResultSet resultSet) throws Exception {
    Balance balance = null;

    while (resultSet.next()) {
      balance = new Balance();
      EntityMapper.getInstance().loadResultSetIntoObject(resultSet, balance);
    }

    return balance;
  }

}
