
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorHabitaciones gestorHabitaciones = new GestorHabitaciones();
        GestorClientes gestorClientes = new GestorClientes();
        GestorReservas gestorReservas = new GestorReservas();

        Scanner sc = new Scanner(System.in);

        int opcionPrincipal;
        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Gestionar Clientes");
            System.out.println("2. Gestionar Habitaciones");
            System.out.println("3. Gestionar Reservas");
            System.out.println("4. Consultar ingresos totales");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcionPrincipal = sc.nextInt();

            switch (opcionPrincipal) {
                case 1 -> gestionarClientes(gestorClientes, sc, gestorReservas);
                case 2 -> gestionarHabitaciones(gestorHabitaciones, sc, gestorReservas);
                case 3 -> gestionarReservas(gestorClientes, gestorHabitaciones, gestorReservas, sc);
                case 4 -> System.out.println("Ingresos totales: " + gestorReservas.calcularIngresos());
                case 5 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcionPrincipal != 5);

        sc.close();
    }

    private static void gestionarClientes(GestorClientes gestorClientes, Scanner sc, GestorReservas gestorReservas) {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Clientes ---");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Eliminar cliente");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.print("ID del cliente: ");
                    int id = sc.nextInt();
                    System.out.print("Nombre: ");
                    sc.nextLine();
                    String nombre = sc.nextLine();
                    System.out.print("Número de documento: ");
                    String documento = sc.nextLine();
                    gestorClientes.registrarCliente(new Cliente(id, nombre, documento));
                }
                case 2 -> gestorClientes.listarClientes();
                case 3 -> {
                    System.out.print("ID del cliente a eliminar: ");
                    int id = sc.nextInt();
                    if (gestorReservas.clienteTieneReservas(id)) {
                        System.out.println("No se puede eliminar el cliente, tiene reservas activas.");
                    } else {
                        gestorClientes.eliminarCliente(id);
                    }
                }
                case 4 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    private static void gestionarHabitaciones(GestorHabitaciones gestorHabitaciones, Scanner sc, GestorReservas gestorReservas) {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Habitaciones ---");
            System.out.println("1. Añadir habitación");
            System.out.println("2. Listar habitaciones");
            System.out.println("3. Eliminar habitación");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.print("ID de la habitación: ");
                    int id = sc.nextInt();
                    System.out.print("Tipo (individual, doble, suite): ");
                    sc.nextLine();
                    String tipo = sc.nextLine();
                    System.out.print("Precio por noche: ");
                    double precio = sc.nextDouble();
                    gestorHabitaciones.agregarHabitacion(new Habitacion(id, tipo, precio));
                }
                case 2 -> gestorHabitaciones.listarHabitaciones();
                case 3 -> {
                    System.out.print("ID de la habitación a eliminar: ");
                    int id = sc.nextInt();
                    if (gestorReservas.habitacionTieneReservas(id)) {
                        System.out.println("No se puede eliminar la habitación, tiene reservas activas.");
                    } else {
                        gestorHabitaciones.eliminarHabitacion(id);
                    }
                }
                case 4 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    private static void gestionarReservas(GestorClientes gestorClientes, GestorHabitaciones gestorHabitaciones, GestorReservas gestorReservas, Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Reservas ---");
            System.out.println("1. Crear reserva");
            System.out.println("2. Listar reservas");
            System.out.println("3. Cancelar reserva");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.print("ID de la reserva: ");
                    int idReserva = sc.nextInt();
                    System.out.print("ID del cliente: ");
                    int idCliente = sc.nextInt();
                    System.out.print("ID de la habitación: ");
                    int idHabitacion = sc.nextInt();
                    System.out.print("Fecha de entrada (YYYY-MM-DD): ");
                    String entrada = sc.next();
                    System.out.print("Fecha de salida (YYYY-MM-DD): ");
                    String salida = sc.next();

                    Cliente cliente = gestorClientes.buscarCliente(idCliente);
                    Habitacion habitacion = gestorHabitaciones.buscarHabitacion(idHabitacion);

                    if (cliente != null && habitacion != null) {
                        Reserva reserva = new Reserva(idReserva, habitacion, cliente, LocalDate.parse(entrada), LocalDate.parse(salida));
                        gestorReservas.crearReserva(reserva);
                    } else {
                        System.out.println("Cliente o habitación no encontrados.");
                    }
                }
                case 2 -> gestorReservas.listarReservas();
                case 3 -> {
                    System.out.print("ID de la reserva a cancelar: ");
                    int idReserva = sc.nextInt();
                    gestorReservas.cancelarReserva(idReserva);
                }
                case 4 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }
}
