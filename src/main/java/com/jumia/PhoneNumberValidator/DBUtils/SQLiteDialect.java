package com.jumia.PhoneNumberValidator.DBUtils;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.*;
import org.hibernate.dialect.identity.IdentityColumnSupport;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Types;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        registerColumnType(Types.BIT, "integer");
        registerColumnType(Types.TINYINT, "tinyint");
        registerColumnType(Types.SMALLINT, "smallint");
        registerColumnType(Types.INTEGER, "integer");
        registerColumnType(Types.BIGINT, "bigint");
        registerColumnType(Types.FLOAT, "float");
        registerColumnType(Types.REAL, "real");
        registerColumnType(Types.DOUBLE, "double");
        registerColumnType(Types.NUMERIC, "numeric");
        registerColumnType(Types.DECIMAL, "decimal");
        registerColumnType(Types.CHAR, "char");
        registerColumnType(Types.VARCHAR, "varchar");
        registerColumnType(Types.LONGVARCHAR, "longvarchar");
        registerColumnType(Types.DATE, "date");
        registerColumnType(Types.TIME, "time");
        registerColumnType(Types.TIMESTAMP, "timestamp");
        registerColumnType(Types.BINARY, "blob");
        registerColumnType(Types.VARBINARY, "blob");
        registerColumnType(Types.LONGVARBINARY, "blob");
        // registerColumnType(Types.NULL, "null");
        registerColumnType(Types.BLOB, "blob");
        registerColumnType(Types.CLOB, "clob");
        registerColumnType(Types.BOOLEAN, "boolean");

        registerFunction( "concat", new VarArgsSQLFunction(StandardBasicTypes.STRING, "", "||", "") );
        registerFunction( "mod", new SQLFunctionTemplate(StandardBasicTypes.INTEGER, "?1 % ?2" ) );
        registerFunction( "quote", new StandardSQLFunction("quote", StandardBasicTypes.STRING) );
        registerFunction( "random", new NoArgSQLFunction("random", StandardBasicTypes.INTEGER) );
        registerFunction( "round", new StandardSQLFunction("round") );
        registerFunction( "substr", new StandardSQLFunction("substr", StandardBasicTypes.STRING) );
        registerFunction( "trim", new AbstractAnsiTrimEmulationFunction() {
            protected SQLFunction resolveBothSpaceTrimFunction() {
                return new SQLFunctionTemplate(StandardBasicTypes.STRING, "trim(?1)");
            }

            protected SQLFunction resolveBothSpaceTrimFromFunction() {
                return new SQLFunctionTemplate(StandardBasicTypes.STRING, "trim(?2)");
            }

            protected SQLFunction resolveLeadingSpaceTrimFunction() {
                return new SQLFunctionTemplate(StandardBasicTypes.STRING, "ltrim(?1)");
            }

            protected SQLFunction resolveTrailingSpaceTrimFunction() {
                return new SQLFunctionTemplate(StandardBasicTypes.STRING, "rtrim(?1)");
            }

            protected SQLFunction resolveBothTrimFunction() {
                return new SQLFunctionTemplate(StandardBasicTypes.STRING, "trim(?1, ?2)");
            }

            protected SQLFunction resolveLeadingTrimFunction() {
                return new SQLFunctionTemplate(StandardBasicTypes.STRING, "ltrim(?1, ?2)");
            }

            protected SQLFunction resolveTrailingTrimFunction() {
                return new SQLFunctionTemplate(StandardBasicTypes.STRING, "rtrim(?1, ?2)");
            }
        } );
    }
    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return new SQLiteIdentityColumnSupport();
    }

    @Autowired
    Environment env;

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("driverClassName"));

        dataSource.setUrl(env.getProperty("url"));
        dataSource.setUsername(env.getProperty("user"));
        dataSource.setPassword(env.getProperty("password"));
        return dataSource;
    }
    public boolean supportsIdentityColumns() {
        return true;
    }

  /*
  public boolean supportsInsertSelectIdentity() {
    return true; // As specify in NHibernate dialect
  }
  */

    public boolean hasDataTypeInIdentityColumn() {
        return false; // As specify in NHibernate dialect
    }

  /*
  public String appendIdentitySelectToInsert(String insertString) {
    return new StringBuffer(insertString.length()+30). // As specify in NHibernate dialect
      append(insertString).
      append("; ").append(getIdentitySelectString()).
      toString();
  }
  */

    public String getIdentityColumnString() {
        // return "integer primary key autoincrement";
        return "integer";
    }

    public String getIdentitySelectString() {
        return "select last_insert_rowid()";
    }

    public boolean supportsLimit() {
        return true;
    }

    protected String getLimitString(String query, boolean hasOffset) {
        return new StringBuffer(query.length()+20).
                append(query).
                append(hasOffset ? " limit ? offset ?" : " limit ?").
                toString();
    }

    public boolean supportsTemporaryTables() {
        return true;
    }

    public String getCreateTemporaryTableString() {
        return "create temporary table if not exists";
    }

    public boolean dropTemporaryTableAfterUse() {
        return false;
    }

    public boolean supportsCurrentTimestampSelection() {
        return true;
    }

    public boolean isCurrentTimestampSelectStringCallable() {
        return false;
    }

    public String getCurrentTimestampSelectString() {
        return "select current_timestamp";
    }

    public boolean supportsUnionAll() {
        return true;
    }

    public boolean hasAlterTable() {
        return false; // As specify in NHibernate dialect
    }

    public boolean dropConstraints() {
        return false;
    }

    public String getAddColumnString() {
        return "add column";
    }

    public String getForUpdateString() {
        return "";
    }

    public boolean supportsOuterJoinForUpdate() {
        return false;
    }

    public String getDropForeignKeyString() {
        throw new UnsupportedOperationException("No drop foreign key syntax supported by SQLiteDialect");
    }

    public String getAddForeignKeyConstraintString(String constraintName,
                                                   String[] foreignKey, String referencedTable, String[] primaryKey,
                                                   boolean referencesPrimaryKey) {
        throw new UnsupportedOperationException("No add foreign key syntax supported by SQLiteDialect");
    }

    public String getAddPrimaryKeyConstraintString(String constraintName) {
        throw new UnsupportedOperationException("No add primary key syntax supported by SQLiteDialect");
    }

    public boolean supportsIfExistsBeforeTableName() {
        return true;
    }

    public boolean supportsCascadeDelete() {
        return false;
    }
}