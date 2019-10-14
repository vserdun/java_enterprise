package com.company;

import java.util.Objects;

public class BankAccount
{
  private final long acc_id;
  private final String acc_login_name;
  private final String acc_password_hash;


  public BankAccount(long acc_id, String acc_login_name, String acc_password_hash)
  {
    this.acc_id = acc_id;
    this.acc_login_name = acc_login_name;
    this.acc_password_hash = acc_password_hash;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (o == null || getClass() != o.getClass())
    {
      return false;
    }
    BankAccount that = (BankAccount) o;
    return acc_id == that.acc_id &&
        acc_login_name.equals(that.acc_login_name) &&
        acc_password_hash.equals(that.acc_password_hash);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(acc_id, acc_login_name, acc_password_hash);
  }

  @Override
  public String toString()
  {
    return "BankAccount{" +
        "acc_id=" + acc_id +
        ", acc_login_name='" + acc_login_name + '\'' +
        ", acc_password_hash='" + acc_password_hash + '\'' +
        '}';
  }
}
