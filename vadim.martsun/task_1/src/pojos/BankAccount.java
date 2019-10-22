package pojos;

public class BankAccount implements Comparable{
    private final int accountId;
    private final String accountLoginName;
    private final int accountPasswordHash;

    public BankAccount(int accountId, String accountLoginName, int accountPasswordHash) {
        this.accountId = accountId;
        this.accountLoginName = accountLoginName;
        this.accountPasswordHash = accountPasswordHash;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getAccountLoginName() {
        return accountLoginName;
    }

    public int getAccountPasswordHash() {
        return accountPasswordHash;
    }

    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || (o.getClass() != this.getClass())) return false;
        BankAccount bankAccount = (BankAccount) o;
        return ((bankAccount.getAccountId() == this.accountId)
                && (bankAccount.accountLoginName.equals(this.accountLoginName))
                && (bankAccount.accountPasswordHash == this.accountPasswordHash));
    }

    @Override
    public int hashCode(){
        int result = Integer.hashCode(accountId);
        result = 31 * result + accountLoginName.hashCode();
        result = 31 * result + Integer.hashCode(accountPasswordHash);
        return result;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(this.getAccountId(), ((BankAccount) o).getAccountId());
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "Id=" + accountId +
                ", LoginName='" + accountLoginName + '\'' +
                ", PasswordHash=" + accountPasswordHash +
                '}';
    }
}