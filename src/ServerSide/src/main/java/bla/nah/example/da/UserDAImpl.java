package bla.nah.example.da;

import bla.nah.example.common.mapper.EntityMapper;
import bla.nah.example.model.User;
import bla.nah.example.utils.AsyncHandler;
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
      "INSERT INTO chatapp.User (`Username`,`Fullname`,`Password`) VALUES (?, ?, ?)";
  private static final String SELECT_USER_BY_ID = "SELECT * FROM chatapp.User WHERE UserID = ?";
  private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM chatapp.User WHERE Username = ?";
  private static final String SELECT_USER_LIST = "SELECT * FROM chatapp.User WHERE UserID != ?";

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
    public Future<ArrayList<User>> selectUserList(int id) {
        Future<ArrayList<User>> future = Future.future();
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
    private ArrayList<User> mapRs2EntityUserList(ResultSet resultSet) throws Exception {
        User user = null;
        ArrayList<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            user = new User();
            EntityMapper.getInstance().loadResultSetIntoObject(resultSet, user);
            userList.add(user);
        }

        return userList;
    }
}
