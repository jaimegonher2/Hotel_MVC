
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorReservas {
    private List<Reserva> reservas = new ArrayList<>();

    public void crearReserva(Reserva reserva) {
        boolean conflicto = reservas.stream().anyMatch(r ->
                r.getHabitacion().getId() == reserva.getHabitacion().getId() &&
                        (r.getFechaEntrada().isBefore(reserva.getFechaSalida()) &&
                                reserva.getFechaEntrada().isBefore(r.getFechaSalida())));

        if (conflicto) {
            System.out.println("La habitación ya está reservada en el período seleccionado.");
            return;
        }

        reservas.add(reserva);
        System.out.println("Reserva creada exitosamente.");
    }

    public void listarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
        } else {
            reservas.forEach(System.out::println);
        }
    }

    public void cancelarReserva(int id) {
        reservas.removeIf(r -> r.getIdReserva() == id);
    }

    public double calcularIngresos() {
        return reservas.stream().mapToDouble(Reserva::getTotal).sum();
    }

    public List<Reserva> buscarReservasPorCliente(int idCliente) {
        return reservas.stream()
                .filter(r -> r.getCliente().getId() == idCliente)
                .collect(Collectors.toList());
    }

    public List<Reserva> buscarReservasPorHabitacion(int idHabitacion) {
        return reservas.stream()
                .filter(r -> r.getHabitacion().getId() == idHabitacion)
                .collect(Collectors.toList());
    }

    public boolean habitacionTieneReservas(int idHabitacion) {
        return reservas.stream().anyMatch(r -> r.getHabitacion().getId() == idHabitacion);
    }

    public boolean clienteTieneReservas(int idCliente) {
        return reservas.stream().anyMatch(r -> r.getCliente().getId() == idCliente);
    }
}
