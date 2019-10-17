import java.util.*;

public class BankAccount {

    private final String acc_id;
    private final String acc_login_name;
    private final String acc_password_hash;

    public BankAccount(String acc_id, String acc_login_name, String acc_password_hash) {
        this.acc_id = acc_id;
        this.acc_login_name = acc_login_name;
        this.acc_password_hash = acc_password_hash;
    }

    public String getAcc_id() {
        return acc_id;
    }

    public String getAcc_login_name() {
        return acc_login_name;
    }

    public String getAcc_password_hash() {
        return acc_password_hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAccount)) return false;
        BankAccount that = (BankAccount) o;
        return  Objects.equals(getAcc_id(), that.getAcc_id()) &&
                Objects.equals(getAcc_login_name(), that.getAcc_login_name()) &&
                Objects.equals(getAcc_password_hash(), that.getAcc_password_hash());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAcc_id(), getAcc_login_name(), getAcc_password_hash());
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "acc_id='" + acc_id + '\'' +
                ", acc_login_name='" + acc_login_name + '\'' +
                ", acc_password_hash='" + acc_password_hash + '\'' +
                '}';
    }

    public static List<BankAccount> findDuplicates(Collection<BankAccount> collA, Collection<BankAccount> collB) {
        if (collA == null || collB == null) {
            return new ArrayList<>();
        }

        Set<BankAccount> repeatedAccs = new HashSet<>();
        Set<BankAccount> seen = new HashSet<>();

        for (BankAccount account : collA) {
            if (!seen.add(account)) {
                repeatedAccs.add(account);
            }
        }
        for (BankAccount account : collB) {
            if (!seen.add(account)) {
                repeatedAccs.add(account);
            }
        }

        return new ArrayList<>(repeatedAccs);
    }

}
