package pl.siek.tester;

import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by joe on 17.01.17.
 */
public class Log {
    private final ConfigurationStorage cfg;
    private final DateFormat dateFormat = new SimpleDateFormat("[yyyy/MM/dd HH:mm:ss] ");
    private final JFrame parent;

    Log(JFrame parent, ConfigurationStorage config) {
        this.cfg = config;
        this.parent = parent;
    }
    private boolean logIsOpen = false;
    private File logFile = null;
    private BufferedWriter logger = null;

    boolean open() {
        try {
            if (cfg.optionConfiguration.getConfig(ConfigurationDefaultOptions.SAVE_LOG).compareTo("on") == 0) {
                logFile = new File(cfg.pathConfiguration.getConfig(ConfigurationDefaultPath.LOG));
                logFile.createNewFile();
                logger = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFile, true)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            logIsOpen = (logFile != null && logger != null);
            if (!logIsOpen && logger != null) {
                try {
                    logger.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(
                                    parent,
                                    cfg.errorConfiguration.getConfig(ConfigurationDefaultErrors.ERROR_OPENING_LOG_FILE),
                                    cfg.errorConfiguration.getConfig(ConfigurationDefaultErrors.ERROR),
                                    JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return logIsOpen;
    }
    boolean close() {
        boolean result = false;
        try {
            if (logger != null)
                logger.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    parent,
                    cfg.errorConfiguration.getConfig(ConfigurationDefaultErrors.ERROR_CLOSING_LOG_FILE),
                    cfg.errorConfiguration.getConfig(ConfigurationDefaultErrors.ERROR),
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            logFile = null;
            logIsOpen = false;
            logger = null;
            result = true;
        }
        return result;
    }
    boolean send(String message) {
        if(logIsOpen) {
            try {
                logger.write(dateFormat.format(new Date()) + message + "\n");
                logger.flush();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(
                        parent,
                        cfg.errorConfiguration.getConfig(ConfigurationDefaultErrors.ERROR_WRITING_TO_LOG_FILE),
                        cfg.errorConfiguration.getConfig(ConfigurationDefaultErrors.ERROR),
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
}
