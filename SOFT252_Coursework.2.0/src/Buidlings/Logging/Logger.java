/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buidlings.Logging;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Logger Class is used to write provided text to a log file.
 * Logger Class Constructor  instantiates Logger parameters.
 * Singleton pattern (Only allows one file to be used)
 *
 */
public class Logger {

    private String FileName;
    private String FileLocation;
    private static Logger instance = null;
    private List<String> entires;

    /**
     * Logger sets default log file name and instantiates entires array list
     */
    protected Logger() {
        this.FileName = "AccessLogText.txt";
        this.entires = new ArrayList<>();
    }

    /**
     * getInstance checks if an Instance already exists, returning a new or existing instance.
     * @return 
     */
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }

        return instance;
    }
    /**
     * WriteToAccessLogFile writes the given paramater to the previously instantiated file.
     * @param textLine
     * @throws IOException 
     */
    public void WriteToAccessLogFile(String textLine) throws IOException {
        try {
            // adds new log to the access log entries
            this.entires.add(textLine);

            FileWriter fileWriter = new FileWriter(this.FileName, true);
            fileWriter.write(textLine + System.getProperty("line.separator"));
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

}
