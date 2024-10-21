import java.util.ArrayList;

public class Equipo {
    //Atributos
    private String nombre;
    private int annoFundacion;
    private String ciudad;
    private String estadio;
    private String presidente;
    private Entrenador entrenador;
    private ArrayList<Jugador> jugadores;
    private int victorias;
    private int derrotas;
    private int empates;

    //Constructors

    public Equipo(String nombre, int annoFundacion, String ciudad, String estadio, String presidente) {
        this.nombre = nombre;
        this.annoFundacion = annoFundacion;
        this.ciudad = ciudad;
        this.estadio = estadio;
        this.presidente = presidente;
        this.jugadores = new ArrayList<>();
    }

    public Equipo(String nombre, int annoFundacion, String ciudad) {
        this.nombre = nombre;
        this.annoFundacion = annoFundacion;
        this.ciudad = ciudad;
        this.jugadores = new ArrayList<>();
    }

    //Getters and Setters
    public String getNombre() {
        return this.nombre;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getPresidente() {
        return this.presidente;
    }

    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }

    public Entrenador getEntrenador() {
        return this.entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public ArrayList<Jugador> getJugadores() {
        return this.jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }
    //Metodos

    public int estadisticaPartido(){
        float motivacionTotal = 0, calidadTotal = 0, contadorJugadores = 0;

        for (Jugador jugador:this.jugadores){
            contadorJugadores ++;
            motivacionTotal = motivacionTotal+ jugador.getMotivacion();
            calidadTotal = calidadTotal + jugador.getCalidad();
        }

        return (int) (((motivacionTotal/contadorJugadores) + (calidadTotal/contadorJugadores)) / 2);
    }
    public int puntuacion(){
        return (this.victorias*3) + (this.empates);
    }

    public String datosParaEscribir(){
        return this.nombre + ";" + this.annoFundacion + ";" + this.ciudad + ";"
                + this.estadio + ";" + this.presidente + ";" +
                ";" + this.victorias + ";" + this.derrotas + ";" + this.empates + "\n";
    }

    @Override
    public String toString() {
        if (getEntrenador() == null) {
            return "Equipo\n" +
                    "nombre = " + this.nombre + '\n' +
                    "annoFundacion = " + this.annoFundacion + '\n' +
                    "ciudad = " + this.ciudad + '\n' +
                    "estadio = " + this.estadio + '\n' +
                    "presidente = " + this.presidente + '\n' +
                    "entrenador = " + "Sin entrenador\n" +
                    "jugadores = " + this.jugadores;
        } else {
            return "Equipo\n" +
                    "nombre = " + this.nombre + '\n' +
                    "annoFundacion = " + this.annoFundacion + '\n' +
                    "ciudad = " + this.ciudad + '\n' +
                    "estadio = " + this.estadio + '\n' +
                    "presidente = " + this.presidente + '\n' +
                    "entrenador = " + this.entrenador.getNombre() + '\n' +
                    "jugadores = " + this.jugadores;
        }
    }
}
