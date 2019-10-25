import java.util.*;

public class BankAccount {
    private String acc_id;
    private String acc_login_name;
    private String acc_password_hash;

    public String getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(String acc_id) {
        this.acc_id = acc_id;
    }

    public String getAcc_login_name() {
        return acc_login_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(acc_id, that.acc_id) &&
                Objects.equals(acc_login_name, that.acc_login_name) &&
                Objects.equals(acc_password_hash, that.acc_password_hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acc_id, acc_login_name, acc_password_hash);
    }

    public static List<BankAccount> findDuplicates(Collection<BankAccount> collA, Collection<BankAccount> collB) {
        Set<BankAccount> set = new HashSet<>(collA);
        Set<BankAccount> setResult = new HashSet<>();
        for (BankAccount b : collB) {
            if (!set.add(b))
                setResult.add(b);
        }
        return new ArrayList<>(setResult);
    }
}
