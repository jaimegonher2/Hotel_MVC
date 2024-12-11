
public class Habitacion {
    private int id;
    private String tipo; // individual, doble, suite
    private double precioPorNoche;

    public Habitacion(int id, String tipo, double precioPorNoche) {
        this.id = id;
        this.tipo = tipo;
        this.precioPorNoche = precioPorNoche;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", precioPorNoche=" + precioPorNoche +
                '}';
    }
}
