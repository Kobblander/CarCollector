package is.ru.app.CarCollector.utilities;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>LogToFile</h1>
 * <h2>is.ru.app.CarCollector.utilities</h2>
 * <p></p>
 * Created on 30.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class LogToFile {

    private static Logger logger = Logger.getLogger("MY_LOG");

    public static void log(Exception ex, String level, String msg){

        FileHandler fh = null;
        try {
            fh = new FileHandler("home/jakob/IdeaProjects/APP/CarCollector/log.xml",true);
            logger.addHandler(fh);
            if (level.equals("severe")) {
                logger.log(Level.SEVERE, msg, ex);

            } else if (level.equals("warning")) {
                logger.log(Level.WARNING, msg, ex);

            } else if (level.equals("info")) {
                logger.log(Level.INFO, msg, ex);

            } else if (level.equals("config")) {
                logger.log(Level.CONFIG, msg, ex);

            } else if (level.equals("fine")) {
                logger.log(Level.FINE, msg, ex);

            } else if (level.equals("finer")) {
                logger.log(Level.FINER, msg, ex);

            } else if (level.equals("finest")) {
                logger.log(Level.FINEST, msg, ex);

            } else {
                logger.log(Level.CONFIG, msg, ex);

            }
        } catch (IOException ex1) {
            logger.log(Level.SEVERE, null, ex1);
        } catch (SecurityException ex1) {
            logger.log(Level.SEVERE, null, ex1);
        } finally{
            if(fh!=null)fh.close();
        }
    }
}
