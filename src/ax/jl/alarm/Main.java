package ax.jl.alarm;

public class Main {

    public static void main(String[] args) {
        SystemTime reloj = new SystemTime();
        // Bucle para imprimir la hora y minutos cada minuto
        while (true) {
            System.out.println("Hora: " + reloj.getHour() + " Minutos: " + reloj.getMinute());
            try {
                Thread.sleep(1000); // Espera 60 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
