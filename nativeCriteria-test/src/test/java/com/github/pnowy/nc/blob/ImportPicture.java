package com.github.pnowy.nc.blob;

import liquibase.change.custom.CustomSqlChange;
import liquibase.database.Database;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.CustomChangeException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;
import liquibase.statement.SqlStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.PreparedStatement;

/**
 * Import picture to database.
 */
public class ImportPicture implements CustomSqlChange {
    private static final Logger log = LoggerFactory.getLogger(ImportPicture.class);

    private InputStream png;

    @Override
    public SqlStatement[] generateStatements(Database database) throws CustomChangeException {
        JdbcConnection connection = (JdbcConnection) database.getConnection();
        final String sql = "INSERT INTO IMAGE(NAME, CONTENT) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "pn.png");
            ps.setBlob(2, png);
            ps.executeUpdate();
        } catch (Exception e) {
            log.error("", e);
            throw new CustomChangeException("Cannot insert image data!", e);
        } finally {
            try {
                png.close();
            } catch (Exception ignore) {}
        }
        return new SqlStatement[0];
    }

    @Override
    public void setFileOpener(ResourceAccessor resourceAccessor) {
        png = resourceAccessor.toClassLoader().getResourceAsStream("liquibase/data/pn.png");
    }

    @Override
    public String getConfirmationMessage() {
        return null;
    }

    @Override
    public void setUp() throws SetupException {

    }

    @Override
    public ValidationErrors validate(Database database) {
        return null;
    }

}
