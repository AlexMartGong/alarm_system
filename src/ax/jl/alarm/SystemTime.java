package ax.jl.alarm;

import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SystemTime {

    private static int hour;
    private static int minute;

    public SystemTime() {
        startReloj();
    }

    private void startReloj() {

        Thread threadRelog = new Thread(() -> {
            while (true) {
                updateTime();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SystemTime.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        threadRelog.start();
    }

    // Método sincronizado para actualizar la hora y minutos
    private synchronized void updateTime() {
        LocalTime tiempoActual = LocalTime.now();
        this.hour = tiempoActual.getHour();
        this.minute = tiempoActual.getMinute();
    }

    // Métodos públicos para obtener la hora y minutos
    public synchronized int getHour() {
        return this.hour;
    }

    public synchronized int getMinute() {
        return this.minute;
    }
}
