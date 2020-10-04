package vn.zalopay.ducnm8.da;

import vn.zalopay.ducnm8.common.mapper.EntityMapper;
import vn.zalopay.ducnm8.model.User;
import vn.zalopay.ducnm8.model.UserWithoutPassword;
import vn.zalopay.ducnm8.utils.AsyncHandler;
import io.vertx.core.Future;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class UserDAImpl extends BaseTransactionDA implements UserDA {
  private final DataSource dataSource;
  private final AsyncHandler asyncHandler;
  private static final String INSERT_USER_STATEMENT =
      "INSERT INTO user (`user_name`,`full_name`,`password`) VALUES (?, ?, ?)";
  private static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
  private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM user WHERE user_name = ?";
  private static final String SELECT_USER_LIST = "SELECT * FROM user WHERE id != ?";

  public UserDAImpl(DataSource dataSource, AsyncHandler asyncHandler) {
    super();
    this.dataSource = dataSource;
    this.asyncHandler = asyncHandler;
  }


    @Override
    public Executable<User> insert(User user) {
      log.info("MYSQL: INSERTING A USER");
        return connection -> {
            Future<User> future = Future.future();
            Future<Void> temp = Future.future();
            asyncHandler.run(
                    () -> {
                        Object[] params = {user.getUsername(), user.getFullname(), user.getPassword()};
                        try {
                            boolean isSuccess = executeWithParams(
                                    temp, connection.unwrap(), INSERT_USER_STATEMENT, params, "insertUser");
                            if(isSuccess){
                                future.complete(user);
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
  public Future<User> selectUserById(int id) {
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
  public Future<User> selectUserByUsername(String Username){
    log.info("MYSQL: SELECTING A USER BY USERNAME");
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
    public Future<ArrayList<UserWithoutPassword>> selectUserList(int id) {
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
}
