public abstract class Persona {
    //Atributos
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private float motivacion;
    private float sueldo;
    private Equipo equipo;

    //Constructors

    public Persona(String nombre, String apellido, String fechaNacimiento, float motivacion, float sueldo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.motivacion = motivacion;
        this.sueldo = sueldo;
    }


    //Getters and Setters
    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public String getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public float getMotivacion() {
        return this.motivacion;
    }

    public void setMotivacion(float motivacion) {
        if (motivacion > 10) {
            this.motivacion = 10;
        } else {
            this.motivacion = motivacion;
        }
    }

    public float getSueldo() {
        return this.sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    public Equipo getEquipo() {
        return this.equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", motivacion=" + motivacion + '\'' +
                ", sueldo=" + sueldo + '\'' +
                ", equipo=" + equipo + '\'' +
                '}';
    }

    public void entrenar() {
        this.setMotivacion(this.getMotivacion() + 0.2F);
        this.motivacion = Math.round(motivacion * 100) / 100.0f; // Redondeo
        System.out.println("Nombre: " + this.nombre + "\n- Motivación: " + this.motivacion);
    }
}
