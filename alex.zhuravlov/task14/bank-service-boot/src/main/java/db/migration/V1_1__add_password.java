package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.Statement;

public class V1_1__add_password extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {

        try (Statement statement = context.getConnection().createStatement()) {
            String sql ="ALTER TABLE `users` ADD COLUMN `password` varchar(255) DEFAULT NULL";
            statement.execute(sql);

        }
    }
}
