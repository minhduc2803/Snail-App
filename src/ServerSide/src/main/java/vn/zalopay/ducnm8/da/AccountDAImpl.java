package vn.zalopay.ducnm8.da;

import io.vertx.core.Future;
import org.apache.logging.log4j.Logger;
import vn.zalopay.ducnm8.common.mapper.EntityMapper;
import vn.zalopay.ducnm8.model.Account;
import vn.zalopay.ducnm8.model.UserWithoutPassword;
import vn.zalopay.ducnm8.utils.AsyncHandler;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDAImpl extends BaseTransactionDA implements AccountDA {
    private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(AccountDAImpl.class);
    private final DataSource dataSource;
  private final AsyncHandler asyncHandler;
  private static final String INSERT_USER_STATEMENT =
      "INSERT INTO account (`user_name`,`full_name`,`password`,`balance`) VALUES (?, ?, ?, ?)";
  private static final String SELECT_USER_BY_ID = "SELECT * FROM account WHERE id = ?";
  private static final String SELECT_USER_BY_USERNAME = "SELECT id, user_name, full_name, password, balance FROM account WHERE user_name = ?";
  private static final String SELECT_USER_LIST = "SELECT * FROM account WHERE id != ?";

  public AccountDAImpl(DataSource dataSource, AsyncHandler asyncHandler) {
    super();
    this.dataSource = dataSource;
    this.asyncHandler = asyncHandler;
  }


    @Override
    public Executable<Account> insert(Account account) {
      log.info("MYSQL: INSERTING A USER");
        return connection -> {
            Future<Account> future = Future.future();
            Future<Void> temp = Future.future();
            asyncHandler.run(
                    () -> {
                        Object[] params = {account.getUsername(), account.getFullname(), account.getPassword(), account.getBalance()};
                        try {
                            boolean isSuccess = executeWithParams(
                                    temp, connection.unwrap(), INSERT_USER_STATEMENT, params, "insertUser");
                            if(isSuccess){
                                future.complete(account);
                            }else{
                                future.fail("Wrong Insert Statement");
                            }
                        } catch (SQLException e) {
                            future.fail(e);
                        }
                    });
            return future;
        };
    }

  @Override
  public Future<Account> selectUserById(long id) {
    Future<Account> future = Future.future();
    asyncHandler.run(
        () -> {
          Object[] params = {id};
          queryEntity(
              "queryUser",
              future,
              SELECT_USER_BY_ID,
              params,
              this::mapRs2EntityUser,
              dataSource::getConnection,
              false);
        });

    return future;
  }



  @Override
  public Future<Account> selectUserByUsername(String Username){
    log.info("select a user by username: {}",Username);
    Future<Account> future = Future.future();
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
    public Future<ArrayList<UserWithoutPassword>> selectUserList(long id) {
        log.info("select list user except one user by id: {}",id);
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

  private Account mapRs2EntityUser(ResultSet resultSet) throws Exception {
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
}
