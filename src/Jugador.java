import java.util.Random;

public class Jugador extends Persona implements Transferible {
    //Atributos
    private int dorsal;
    private String posicion;
    private float calidad;

    //Constructors
    public Jugador(String nombre, String apellido, String fechaNacimiento, float motivacion, float sueldo, int dorsal, String posicion, float calidad) {
        super(nombre, apellido, fechaNacimiento, motivacion, sueldo);
        this.dorsal = dorsal;
        this.posicion = posicion;
        this.calidad = calidad;
    }

    //Getters and Setters
    public int getDorsal() {
        return this.dorsal;
    }
    public float getCalidad() {
        return this.calidad;
    }

    public void setCalidad(float calidad) {
        if (calidad > 10) {
            this.calidad = 10;
        } else {
            this.calidad = calidad;
        }
    }


    //Metodos
    @Override
    public String toString() {
        if (getEquipo() != null) {
            return "\n" +
                    "Jugador\n" +
                    "Nombre = " + getNombre() + '\n' +
                    "Apellido = " + getApellido() + '\n' +
                    "Fecha Nacimiento = " + getFechaNacimiento() + '\n' +
                    "Equipo = " + getEquipo().getNombre() + '\n' +
                    "Motivacion = " + getMotivacion() + '\n' +
                    "Sueldo = " + getSueldo() + '\n' +
                    "Dorsal = " + this.dorsal + '\n' +
                    "Posicion = " + this.posicion + '\n' +
                    "Calidad = " + this.calidad + "\n";
        } else {
            return "\n" +
                    "Jugador\n" +
                    "Nombre = " + getNombre() + '\n' +
                    "Apellido = " + getApellido() + '\n' +
                    "Fecha Nacimiento = " + getFechaNacimiento() + '\n' +
                    "Equipo = Sin equipo" + '\n' +
                    "Motivacion = " + getMotivacion() + '\n' +
                    "Sueldo = " + getSueldo() + '\n' +
                    "Dorsal = " + this.dorsal + '\n' +
                    "Posicion = " + this.posicion + '\n' +
                    "Calidad = " + this.calidad + "\n";
        }
    }

    public String datosParaEscribir(){
        return getNombre() + ";" + getApellido() + ";" + getEquipo().getNombre() + ";" + getFechaNacimiento() + ";" +
                getMotivacion() + ";" + getSueldo() + ";" + this.dorsal + ";" + this.posicion + ";" + this.calidad + "\n";
    }

    @Override
    public void transferirAEquip(Equipo equipo) {
        this.setEquipo(equipo);
        System.out.println(this.getNombre() + " ha sido transferido a " + this.getEquipo().getNombre());
    }

    @Override
    public boolean isTransferible() {
        int numeroAleatorio = (int) (Math.random() * 10) + 1;
        return numeroAleatorio >= 4;
    }

    @Override
    public void entrenar() {
        int numeroAleatorio, rango1, rango2, rango3, resultado;
        int[] probabilidades = {70, 20, 10};
        Random random = new Random();


        numeroAleatorio = random.nextInt(100);

        rango1 = probabilidades[0];
        rango2 = rango1 + probabilidades[1];
        rango3 = rango2 + probabilidades[2];

        if (numeroAleatorio < probabilidades[0]) {
            this.setCalidad((float) (this.calidad + 0.1));
        } else if (numeroAleatorio < probabilidades[0] + probabilidades[1]) {
            this.setCalidad((float) (this.calidad + 0.2));
        } else {
            this.setCalidad((float) (this.calidad + 0.3));
        }
        this.calidad = Math.round(calidad * 100) / 100.0f; // Redondeo
        super.entrenar();
        System.out.println("- Calidad: " + this.calidad);
        cambioPosicion();
    }

    public void cambioPosicion() {
        int posibilidad;
        boolean cambiado;
        String[] posiciones = {"POR", "DEF", "MED", "DEL"};

        posibilidad = (int) (Math.random() * 100) + 1;
        if (posibilidad >= 1 && posibilidad <= 5) {
            this.setCalidad(this.calidad + 1);
            cambiado = false;
            do {
                switch ((int) (Math.random() * 5) + 1) {
                    case 1:
                        if (!this.posicion.equalsIgnoreCase(posiciones[0])) {
                            System.out.println("La posición del jugador a pasado de " + this.posicion + " a " + posiciones[0]);
                            this.posicion = posiciones[0];
                            cambiado = true;
                        }
                        break;
                    case 2:
                        if (!this.posicion.equalsIgnoreCase(posiciones[1])) {
                            System.out.println("La posición del jugador a pasado de " + this.posicion + " a " + posiciones[1]);
                            this.posicion = posiciones[1];
                            cambiado = true;
                        }
                        break;
                    case 3:
                        if (!this.posicion.equalsIgnoreCase(posiciones[2])) {
                            System.out.println("La posición del jugador a pasado de " + this.posicion + " a " + posiciones[2]);
                            this.posicion = posiciones[2];
                            cambiado = true;
                        }
                        break;
                    case 4:
                        if (!this.posicion.equalsIgnoreCase(posiciones[3])) {
                            System.out.println("La posición del jugador a pasado de " + this.posicion + " a " + posiciones[3]);
                            this.posicion = posiciones[3];
                            cambiado = true;
                        }
                        break;
                }
            } while (!cambiado);


        }

    }
}
