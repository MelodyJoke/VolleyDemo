package com.teamsolo.base.template;

import android.os.Process;

import com.teamsolo.base.utility.file.FileManager;
import com.teamsolo.base.utility.log.LogUtility;

import java.io.PrintWriter;

/**
 * description: uncaught exception handler
 * author: Melody
 * date: 2016/6/4
 * version: 0.0.0.1
 * <p/>
 * 0.0.0.1: The base uncaught exception handler.
 */
@SuppressWarnings("WeakerAccess, unused")
public abstract class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        PrintWriter writer = FileManager.getLogPrintWriter();
        if (writer != null) {
            ex.printStackTrace(writer);
            writer.flush();
            writer.close();
        }

        ex.printStackTrace();

        LogUtility.e("UEHandler", "thread: id=" + thread.getId() +
                " name=" + thread.getName() +
                " state=" + thread.getState().name() +
                "\n" + ex.toString());

        subPerform();
        Process.killProcess(Process.myPid());
    }

    /**
     * sub perform after catch exception
     */
    protected abstract void subPerform();
}
