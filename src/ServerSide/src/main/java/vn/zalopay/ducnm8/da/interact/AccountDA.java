package vn.zalopay.ducnm8.da.interact;

import io.vertx.core.Future;
import vn.zalopay.ducnm8.da.Executable;
import vn.zalopay.ducnm8.model.Account;
import vn.zalopay.ducnm8.model.Balance;
import vn.zalopay.ducnm8.model.User;
import vn.zalopay.ducnm8.model.UserWithoutPassword;

import java.util.ArrayList;

public interface AccountDA {
  Future<Account> insert(Account account);

  Future<Account> selectAccountById(long id);

  Future<User> selectUserByUsername(String Username);

  Future<ArrayList<UserWithoutPassword>> selectUserList(long id);

  Executable<ArrayList<Account>> selectForUpdateTwoAccount(long sender, long receiver);

  Executable<Account> selectAccountForUpdate(long id);

  Future<Balance> selectBalanceById(long id);

  Executable<Account> plusBalanceByAmount(long id, long amount, long lastTimeUpdate);

}
