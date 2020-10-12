package vn.zalopay.ducnm8.da;

import io.vertx.core.Future;
import vn.zalopay.ducnm8.model.Account;
import vn.zalopay.ducnm8.model.Balance;
import vn.zalopay.ducnm8.model.UserWithoutPassword;

import java.util.ArrayList;

public interface AccountDA {
  Executable<Account> insert(Account account);

  Future<Account> selectUserById(long id);

  Future<Account> selectUserByUsername(String Username);

  Future<ArrayList<UserWithoutPassword>> selectUserList(long id);

  Future<Balance> selectBalanceById(long id);

  Executable<Balance> updateBalanceByAmount(long id, long amount);


}
