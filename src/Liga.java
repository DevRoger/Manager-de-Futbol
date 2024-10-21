import java.util.List;

public class Liga {
    private String nombre;
    private int numeroEquipos;
    private List<Equipo> equipos;

    public Liga() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }


}
