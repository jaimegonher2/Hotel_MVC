
import java.util.ArrayList;
import java.util.List;

public class GestorHabitaciones {
    private List<Habitacion> habitaciones = new ArrayList<>();

    public void agregarHabitacion(Habitacion habitacion) {
        habitaciones.add(habitacion);
    }

    public void listarHabitaciones() {
        if (habitaciones.isEmpty()) {
            System.out.println("No hay habitaciones disponibles.");
        } else {
            habitaciones.forEach(System.out::println);
        }
    }

    public void eliminarHabitacion(int id) {
        habitaciones.removeIf(h -> h.getId() == id);
    }

    public Habitacion buscarHabitacion(int id) {
        return habitaciones.stream().filter(h -> h.getId() == id).findFirst().orElse(null);
    }
}
