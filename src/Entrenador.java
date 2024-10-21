public class Entrenador extends Persona {
    //Atributos
    private int torneosGanados;
    private boolean nacional;

    //Constructor
    public Entrenador(String nombre, String apellido, String fechaNacimiento, float motivacion, float sueldo, int torneosGanados, boolean nacional) {
        super(nombre, apellido, fechaNacimiento, motivacion, sueldo);
        this.torneosGanados = torneosGanados;
        this.nacional = nacional;
    }


    //Getters and Setters
    public int getTorneosGanados() {
        return this.torneosGanados;
    }

    //Metodos
    @Override
    public String toString() {
        if (getEquipo() != null) {
            return "\n\n" +
                    "Entrenador\n" +
                    "Nombre = " + getNombre() + '\n' +
                    "Apellido = " + getApellido() + '\n' +
                    "Equipo = " + getEquipo().getNombre() + '\n' +
                    "Fecha Nacimiento = " + getFechaNacimiento() + '\n' +
                    "Motivacion = " + getMotivacion() + '\n' +
                    "Sueldo = " + getSueldo() + '\n' +
                    "Torneos Ganados = " + this.torneosGanados + '\n' +
                    "Nacional = " + this.nacional + '\n';
        } else {
            return "\n\n" +
                    "Entrenador\n" +
                    "Nombre = " + getNombre() + '\n' +
                    "Apellido = " + getApellido() + '\n' +
                    "Equipo = Sin equipo" + '\n' +
                    "Fecha Nacimiento = " + getFechaNacimiento() + '\n' +
                    "Motivacion = " + getMotivacion() + '\n' +
                    "Sueldo = " + getSueldo() + '\n' +
                    "Torneos Ganados = " + this.torneosGanados + '\n' +
                    "Nacional = " + this.nacional + '\n';
        }
    }

    public String datosParaEscribir(){
        return getNombre() + ";" + getApellido() + ";" + getEquipo().getNombre() + ";" + getFechaNacimiento() + ";" +
                getMotivacion() + ";" + getSueldo() + ";" + getTorneosGanados() + ";" + this.nacional + ";" + this.torneosGanados + "\n";
    }

    @Override
    public void entrenar(){
        if (this.nacional){
            this.setMotivacion((float) Math.round((this.getMotivacion() + 0.3) * 100) / 100.0f);
        } else {
            this.setMotivacion((float) Math.round((this.getMotivacion() + 0.15) * 100) / 100.0f);
        }
        System.out.println("Nombre: " + this.getNombre() + "\n- Motivación: " + this.getMotivacion());

    }
}
