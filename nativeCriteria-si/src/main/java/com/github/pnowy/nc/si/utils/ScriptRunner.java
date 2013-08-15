package com.github.pnowy.nc.si.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.LineNumberReader;
import java.io.Reader;

/**
 * Simple tool to run database scripts from files using JDBCTemplate.
 */
public class ScriptRunner
{
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final String DEFAULT_DELIMITER = ";";
    private String delimiter = DEFAULT_DELIMITER;
    private JdbcTemplate jdbcTemplate;

    /**
     * Default constructor
     */
    public ScriptRunner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Runs an SQL script (read in using the Reader parameter)
     *
     * @param reader - the source of the script
     */
    public void runScript(Reader reader) {
        try {
            runScript(jdbcTemplate, reader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void runScript(JdbcTemplate jdbcTemplate, Reader reader) throws Exception {
        StringBuilder command = null;
        try {
            LineNumberReader lineReader = new LineNumberReader(reader);
            String line;
            while ((line = lineReader.readLine()) != null) {
                if (command == null) {
                    command = new StringBuilder();
                }
                String trimmedLine = line.trim();
                if (trimmedLine.startsWith("--")) {
                    log.trace(trimmedLine);
                } else if (trimmedLine.endsWith(getDelimiter())) {
                    command.append(line.substring(0, line.lastIndexOf(getDelimiter())));
                    command.append(" ");

                    log.debug("command: {}", command);

                    jdbcTemplate.execute(command.toString());

                    command = null;
                } else {
                    command.append(line);
                    command.append(" ");
                }
            }
        } catch (Exception e) {
            log.error("", e);
            throw e;
        }
    }

    private String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

}