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
    private static final String SELECT_USER_BY_ID =
            "SELECT * FROM account WHERE id = ?";
    private static final String SELECT_USER_BY_USERNAME =
            "SELECT id, user_name, full_name, password FROM account WHERE user_name = ?";
    private static final String SELECT_USER_LIST =
            "SELECT * FROM account WHERE id != ?";
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
    public Executable<Account> insert(Account account) {
        log.info("insert a user");
        return connection -> {
            Future<Account> future = Future.future();
            asyncHandler.run(
                    () -> {
                        Object[] params = {account.getUsername(), account.getFullName(), account.getPassword(),
                                account.getBalance(), account.getLastTimeUpdateBalance(), account.getNumberNotification()};
                        try {
                            long id = executeWithParamsAndGetId(connection.unwrap(), INSERT_USER_STATEMENT, params, "insertUser");
                            account.setId(id);
                            future.complete(account);
                        } catch (SQLException e) {
                            future.fail(e);
                        }
                    });
            return future;
        };
    }

    @Override
    public Future<User> selectUserById(long id) {
        Future<User> future = Future.future();
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
                            executeWithParamsAndGetId(connection.unwrap(), UPDATE_BALANCE_BY_AMOUNT, params, "updateBalance");
                            Account account = Account.builder().id(id).build();
                            future.complete(account);
                        } catch (SQLException e) {
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
                            executeWithParamsAndGetId(connection.unwrap(), UPDATE_NUMBER_NOTIFICATION, params, "updateNumberNotification");
                            Account account = Account.builder().id(id).build();
                            future.complete(account);
                        } catch (SQLException e) {
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

    private User mapRs2EntityUser(ResultSet resultSet) throws Exception {
        User user = null;

        while (resultSet.next()) {
            user = new User();
            EntityMapper.getInstance().loadResultSetIntoObject(resultSet, user);
        }

        return user;
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

    private Balance mapRs2EntityBalance(ResultSet resultSet) throws Exception {
        Balance balance = null;

        while (resultSet.next()) {
            balance = new Balance();
            EntityMapper.getInstance().loadResultSetIntoObject(resultSet, balance);
        }

        return balance;
    }

}
