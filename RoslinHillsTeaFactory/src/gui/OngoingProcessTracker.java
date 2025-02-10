/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author Kavindu Anupama
 */
public class OngoingProcessTracker {
    
    private static final AtomicBoolean ongoingProcess = new AtomicBoolean(false);

    public static boolean hasOngoingProcess() {
        return ongoingProcess.get();
    }

    public static void setOngoingProcess(boolean value) {
        ongoingProcess.set(value);
    }
    
}
