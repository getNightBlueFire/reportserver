package net.datenwerke.rs.base.service.dbhelper.db;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;

public class Vertica extends DatabaseHelper {

   public static final String DB_NAME = "Vertica";
   public static final String DB_DRIVER = "com.vertica.jdbc.Driver";
   public static final String DB_DESCRIPTOR = "DBHelper_Vertica";
   public static final String DB_DESCRIPTION = "Vertica JDBC driver";

   @Override
   public String getDescriptor() {
      return DB_DESCRIPTOR;
   }

   @Override
   public String getDriver() {
      return DB_DRIVER;
   }

   @Override
   public String getName() {
      return DB_NAME;
   }

   @Override
   public String getDescription() {
      return DB_DESCRIPTION;
   }
}
