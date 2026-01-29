import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        System.out.println("Sea Bienvenido al Conversor de monedas");
        boolean salir = false;
        do {
            System.out.println("""
                    1) Dólar => Peso argentino
                    2) Peso argentino => Dólar
                    3) Dólar => Real brasileño
                    4) Real brasileño => Dólar
                    5) Dólar => Peso uruguayo
                    6) Peso uruguayo => Dólar
                    7) Salir
                    Elija una opción válida:
                    """);
            try {
                var busqueda = Integer.valueOf(lectura.nextLine());
                Conversor consulta = new Conversor();
                if (busqueda == 7) {
                    salir = true;
                } else {
                    System.out.println("Ingrese el valor que desea convertir:");
                    float cantidad = Float.valueOf(lectura.nextLine());
                    float resultado = consulta.Convertir(busqueda, cantidad);
                    System.out.println(resultado);
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR, valor indebido");

            } catch (IllegalArgumentException e) {
                System.out.println("Opcion no disponible");
            }

        } while (!salir);
    }

}

