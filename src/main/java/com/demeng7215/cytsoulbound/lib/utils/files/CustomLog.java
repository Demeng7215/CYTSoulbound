package com.demeng7215.cytsoulbound.lib.utils.files;

import com.demeng7215.cytsoulbound.lib.DemLib;
import lombok.Getter;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Creates a log / plain text (.txt) file for your plugin.
 * Gives you the option to add the system date and time with the data you are logging.
 */
public class CustomLog {

    /**
     * Returns that text/log file, if you need it for whatever reason...
     * Log data using #log(...).
     *
     * @see #log(String, boolean)
     */
    @Getter
    private File logFile;

    /**
     * Creates a new log file.
     *
     * @param logName The name of the log file. Must have the extension ".txt".
     */
    public CustomLog(String logName) throws IOException {

        final Plugin i = DemLib.getPlugin();

        File dataFolder = i.getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        this.logFile = new File(i.getDataFolder(), logName);
        if (!logFile.exists()) {
            logFile.createNewFile();
        }
    }

    /**
     * Logs the information into the log file.
     *
     * @param info            The information you are logging into the file
     * @param includeDateTime Should the log information have the current system date and time in front of it?
     */
    public void log(String info, boolean includeDateTime) throws IOException {

        FileWriter fw = new FileWriter(logFile, true);
        PrintWriter pw = new PrintWriter(fw);

        if (includeDateTime) {
            Date now = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            pw.println("[" + format.format(now) + "] " + info);

        } else {
            pw.println(info);
        }

        pw.flush();
        pw.close();
    }
}
