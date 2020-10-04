package vn.zalopay.ducnm8.da;

import vn.zalopay.ducnm8.model.User;
import vn.zalopay.ducnm8.model.UserWithoutPassword;
import io.vertx.core.Future;

import java.util.ArrayList;

public interface UserDA {
  Executable<User> insert(User user);

  Future<User> selectUserById(int id);

  Future<User> selectUserByUsername(String Username);

  Future<ArrayList<UserWithoutPassword>> selectUserList(int id);
}
