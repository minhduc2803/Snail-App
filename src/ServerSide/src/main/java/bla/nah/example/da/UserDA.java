package bla.nah.example.da;

import bla.nah.example.model.User;
import bla.nah.example.model.UserWithoutPassword;
import io.vertx.core.Future;

import java.util.ArrayList;

public interface UserDA {
  Executable<User> insert(User user);

  Future<User> selectUserById(int id);

  Future<User> selectUserByUsername(String Username);

  Future<ArrayList<UserWithoutPassword>> selectUserList(int id);
}
