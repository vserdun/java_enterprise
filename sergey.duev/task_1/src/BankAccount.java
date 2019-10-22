import java.util.Objects;

public class BankAccount {

    public BankAccount(long acc_id, String acc_login_name, String acc_password_hash) {
        this.acc_id = acc_id;
        this.acc_login_name = acc_login_name;
        this.acc_password_hash = acc_password_hash;
    }

    private long acc_id;
    private String acc_login_name;
    private String acc_password_hash;

    public long getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(long acc_id) {
        this.acc_id = acc_id;
    }

    public String getAcc_login_name() {
        return acc_login_name;
    }

    public void setAcc_login_name(String acc_login_name) {
        this.acc_login_name = acc_login_name;
    }

    public String getAcc_password_hash() {
        return acc_password_hash;
    }

    public void setAcc_password_hash(String acc_password_hash) {
        this.acc_password_hash = acc_password_hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return acc_id == that.acc_id &&
                Objects.equals(acc_login_name, that.acc_login_name) &&
                Objects.equals(acc_password_hash, that.acc_password_hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acc_id, acc_login_name, acc_password_hash);
    }
}
