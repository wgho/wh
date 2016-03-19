package com.yslm.util.log;

import org.apache.log4j.Logger;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LogUtil implements Serializable{

    /**
     * log4j driver
     */
    private Logger logger = null;

    private <T> LogUtil(Class<T> c) {
        this.logger = Logger.getLogger(c);
    }

    /**
     * instance of LogUtil
     *
     * @return
     */
    public static <T> LogUtil instance(Class<T> c) {
        return new LogUtil(c);
    }

    /**
     * log4j LEVEL:INFO
     *
     * @param log
     */
    public final void infoLog(final String log) {
        try {
            logger.info(log);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * log4j LEVEL:INFO
     *
     * @param log
     * @param t
     */
    public final void infoLog(final String log, final Throwable t) {
        try {
            logger.info(log, t);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable t1) {
            t1.printStackTrace();
        }
    }

    /**
     * log4j LEVEL:DEBUG
     *
     * @param log
     */
    public final void debugLog(final String log) {
        try {
            logger.debug(log);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * log4j LEVEL:DEBUG
     *
     * @param log
     * @param t
     */
    public final void debugLog(final String log, final Throwable t) {
        try {
            logger.debug(log, t);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable t1) {
            t1.printStackTrace();
        }
    }

    /**
     * log4j LEVEL:WARN
     *
     * @param log
     */
    public final void warnLog(final String log) {
        try {
            logger.warn(log);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * log4j LEVEL:WARN
     *
     * @param log
     * @param t
     */
    public final void warnLog(final String log, final Throwable t) {
        try {
            logger.warn(log, t);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable t1) {
            t1.printStackTrace();
        }
    }

    /**
     * log4j LEVEL:ERROR
     *
     * @param log
     */
    public final void errorLog(final String log) {
        try {
            logger.error(log);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * log4j LEVEL:ERROR
     *
     * @param log
     * @param t
     */
    public final void errorLog(final String log, final Throwable t) {
        try {
            logger.error(log, t);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable t1) {
            t1.printStackTrace();
        }
    }

}
