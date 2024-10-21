import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*****************************************************************************************************

 M03_REP_04: Football Manager

 Description: This program simulates the management of a football team. Users can create and manage
 teams, sign players and coaches, train players, and play matches. The program also includes features
 for tracking team statistics, finances, and standings.

 Autor: Roger Alonso
 Cicle Formatiu Grau Superior DAM1A - Centre d'Estudis Politecnics 2023-2024

 ******************************************************************************************************/

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
        boolean game;
        ArrayList<Equipo> equipos = new ArrayList<>(); // Lista de equipos
        ArrayList<Persona> personas = new ArrayList<>(); // Lista de jugadores y entrenadores
        Liga liga = new Liga();

        equipos.add(new Equipo("FC Barcelona", 1899, "Barcelona", "Camp Nou", "Laporta"));
        equipos.add(new Equipo("Real Madrid CF", 1902, "Madrid", "Santiago Bernabeu", "Florentino"));

        personas.add(new Entrenador("Juan", "Antonio", "02/07/1999", 5, 32000, 2, false));

        personas.add(new Jugador("Roger", "Alonso", "07/03/1987", 5, 45000, 7, "MED", 10));
        personas.add(new Jugador("Alex", "Aznar", "23/08/1983", 5, 15000, 2, "POR", 8));
        personas.add(new Jugador("Pedro", "Martín", "17/05/1982", 5, 35000, 5, "DEF", 6));
        personas.add(new Entrenador("Pepe", "Garcia", "02/07/1999", 5, 22000, 5, true));


        Equipo eq = equipos.get(0); //FCBarcelona
        ArrayList<Jugador> juga = (ArrayList<Jugador>) eq.getJugadores(); //Jugadores FCBarcelona
        Jugador ju = (Jugador) personas.get(1); //Jugador Roger
        ju.setEquipo(eq); // Set FCBarcelona como equipo de Roger
        juga.add(ju); //Lista Aux de FCBarcelona

        Equipo eq1 = equipos.get(1); //Real Madrid
        List<Jugador> juga1 = eq1.getJugadores(); //Jugadores RealMadrid
        Jugador ju1 = (Jugador) personas.get(2); //Jugador Alex
        ju1.setEquipo(eq1); //Set Real Madrid como equipo de Alex
        juga1.add(ju1); //Lista Aux RealMadrid

        /*
        ju.transferirAEquip(eq);
        System.out.println(ju.getEquipo());
         */

        Entrenador entr = (Entrenador) personas.get(0); // Entrenador Juan
        entr.setEquipo(eq); //Set FCBarcelona como equipo de Juan

        eq.setEntrenador(entr);//FCBarcelona set Juan
        eq.setJugadores(juga); //FCBarcelona set Lista Aux de FCBarcelona
        //ju.setEquipo(equipos.get(0));


        game = true;
        do {
            switch (menu()) {
                case 0:
                    game = false; // Done
                    break;
                case 1:
                    mostrarClasificacion(liga.getEquipos(), liga); // Done, pulir ordenamiento de equipos
                    break;
                case 2:
                    gestionarEquipo(equipos, personas); // Done, arreglar errores de excepciones
                    break;
                case 3:
                    darAltaEquipo(equipos); // Done
                    break;
                case 4:
                    darAltaJugadorPersona(personas); // Done, arreglar errores de excepciones
                    break;
                case 5:
                    mostrarDatosEquipo(equipos, nombreEquipo()); // Done
                    break;
                case 6:
                    preguntarPorJugador(equipos); // Done
                    break;
                case 7:
                    crearLiga(equipos, liga); // Done, optimizar simulación de partidos
                    break;
                case 8: // Done
                    for (Persona persona : personas) {
                        persona.entrenar();
                    }
                    System.out.println();
                    break;
                case 9:
                    cargarDatos(equipos, personas);
                    break;
                case 10:
                    guardarDatos(equipos); // Done
                    break;
                case 11:
                    System.out.println(personas); // Opcion desarrollador ver personas
                    break;
                case 12:
                    System.out.println(equipos); // Opcion desarrollador ver equipos
                    break;
            }
        } while (game);
    }

    private static void cargarDatos(ArrayList<Equipo> equipos, ArrayList<Persona> personas) {
        BufferedReader br;
        String line, lineAux;
        String[] datos;
        ArrayList<Equipo> equiposCargados = new ArrayList<>();

        String nombreArchivo;
        System.out.println("Nombre del archivo que desea cargar: (sin la extensión .txt)");
        nombreArchivo = sc.nextLine() + ".txt";
        Path path = Paths.get("src/resources/" + nombreArchivo);

        if (Files.exists(path)) {
            try {
                br = new BufferedReader(new FileReader(String.valueOf(path)));

                while ((line = br.readLine()) != null) {
                    ArrayList<Jugador> jugadoresAux = new ArrayList<>();
                    datos = line.split(";");
                    //equiposCargados.add(new Equipo(datos[0], Integer.valueOf(datos[1]), datos[2], datos[3], datos[4]));
                    Equipo cargandoEquipo = new Equipo(datos[0], Integer.valueOf(datos[1]), datos[2], datos[3], datos[4]);

                    // Entrenador
                    line = br.readLine();
                    datos = line.split(";");
                    if (!datos[0].equalsIgnoreCase("null")) {
                        Entrenador entrenadorAux = new Entrenador(datos[0], datos[1], datos[2], Float.parseFloat(datos[4]),
                                Float.parseFloat(datos[5]), Integer.valueOf(datos[6]), Boolean.parseBoolean(datos[7]));
                        //comprobarSiExiste();
                        cargandoEquipo.setEntrenador(entrenadorAux);
                        personas.add(entrenadorAux);
                    } else {
                        cargandoEquipo.setEntrenador(null);
                    }

                    //Jugadores
                    line = br.readLine();
                    for (int i = 0; i < Integer.valueOf(line); i++) {
                        lineAux = br.readLine();
                        datos = lineAux.split(";");
                        Jugador jugadorAux = new Jugador(datos[0], datos[1], datos[3], Float.parseFloat(datos[4]),
                                Float.parseFloat(datos[5]), Integer.valueOf(datos[6]), datos[7], Float.parseFloat(datos[8]));
                        //comprobarSiExiste();
                        jugadoresAux.add(jugadorAux);
                        personas.add(jugadorAux);
                    }
                    cargandoEquipo.setJugadores(jugadoresAux);
                    equipos.add(cargandoEquipo);
                }

            } catch (FileNotFoundException e) {
                System.out.println("Error al leer");
            } catch (IOException e) {

            }


        } else {
            System.out.println("El archivo " + nombreArchivo + " no existe.");
        }
    }


    private static String nombreArchivo() {
        System.out.println("Nombre del archivo que desea guardar: ");
        return sc.nextLine();
    }

    private static void guardarDatos(ArrayList<Equipo> equipos) {
        char respuesta = 'n';
        String nombreArchivo = nombreArchivo(), info;
        Path path = Paths.get("src/resources/" + nombreArchivo + ".txt");
        File myFile = new File(nombreArchivo + ".txt");

        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                // Empty
            }
            // Write new file
            escribirDatosNuevos(equipos, path);

        } else {
            System.out.println(nombreArchivo + " ya existe. Desea sobreescribirlo? (S/N)");
            respuesta = sc.nextLine().charAt(0);

            if (respuesta == 's' || respuesta == 'S') {
                // Delete old file
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    // Empty
                }
                // Create new file
                try {
                    Files.createFile(path);
                } catch (IOException e) {
                    // Empty
                }
                // Write new file
                escribirDatosNuevos(equipos, path);
                System.out.println("Se ha sobreescrito " + nombreArchivo + ".txt");

            } else {
                System.out.println("No se ha guardado.");
            }
        }
    }

    private static void escribirDatosNuevos(ArrayList<Equipo> equipos, Path path) {
        String info;
        for (Equipo equipo : equipos) {
            info = equipo.datosParaEscribir();
            if (equipo.getEntrenador() == null) {
                info = info + "null\n";
            } else {
                info = info + equipo.getEntrenador().datosParaEscribir();
            }
            info = info + equipo.getJugadores().size() + "\n";

            for (Jugador jugador : equipo.getJugadores()) {
                info = info + jugador.datosParaEscribir();
            }

            try {
                Files.writeString(path, info, StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println("Error de escritura.");
            }
        }
    }

    private static void mostrarClasificacion(List<Equipo> equipos, Liga liga) {
        int indiceMax;
        System.out.println("\n\n\n\n########## - " + liga.getNombre() + " - ##########");
        for (int i = 0; i < equipos.size(); i++) {
            indiceMax = i;
            for (int j = i + 1; j < equipos.size(); j++) {
                if (equipos.get(j).puntuacion() > equipos.get(indiceMax).puntuacion()) {
                    indiceMax = j;
                }
            }
            if (indiceMax != i) {
                Equipo equipoTemporal = equipos.get(i);
                equipos.set(i, equipos.get(indiceMax));
                equipos.set(indiceMax, equipoTemporal);

            }
        }
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println(i + 1 + ".- " + equipos.get(i).getNombre() + " - " + equipos.get(i).puntuacion() + " puntos");
        }

        System.out.println("\n");

    }

    private static void crearLiga(ArrayList<Equipo> equipos, Liga liga) {
        String nombre;
        int cantidadEquipos, numero, contador = 1;
        boolean equiposEscogidos, encontrado;

        System.out.println("Nombre de la Liga: ");
        nombre = sc.nextLine();
        liga.setNombre(nombre);
        System.out.println("Numero de equipos (" + equipos.size() + " equipos): ");
        cantidadEquipos = sc.nextInt();
        sc.nextLine();

        ArrayList<Equipo> equiposLiga = new ArrayList<>();
        equiposEscogidos = true;
        do {
            if (equiposLiga.size() == cantidadEquipos) {
                equiposEscogidos = false;
            } else {
                for (Equipo equipo : equipos) {
                    encontrado = false;
                    for (Equipo equipo1 : equiposLiga) {
                        if (equipo.getNombre().equalsIgnoreCase(equipo1.getNombre())) {
                            encontrado = true;
                        }
                    }
                    if (!encontrado) {
                        System.out.println(" " + (contador) + "- " + equipo.getNombre());
                    }
                    contador++;
                }
                numero = devolverNumero("Que equipo quieres añadir a la liga? (Escribe el numero) ");

                equiposLiga.add(equipos.get(numero - 1));
            }
            contador = 1;
        } while (equiposEscogidos);

        liga.setEquipos(equiposLiga);

        // Disputar liga
        disputarLiga(equiposLiga);
    }

    private static void disputarLiga(ArrayList<Equipo> equipos) {
        int random;
        for (Equipo equipo1 : equipos) {
            for (Equipo equipo2 : equipos) {
                // Ganar 45 - Perder 35 - Empate 20
                if (!equipo1.equals(equipo2)) {
                    random = (int) (Math.random() * 100);

                    if (equipo1.estadisticaPartido() > equipo2.estadisticaPartido()) {
                        if (random < 45) {
                            System.out.println("Ha ganado " + equipo1.getNombre());
                            equipo1.setVictorias(equipo1.getVictorias() + 1);
                            equipo2.setDerrotas(equipo2.getDerrotas() + 1);
                        } else if (random > 45 && random < 80) {
                            System.out.println("Ha ganado " + equipo2.getNombre());
                            equipo2.setVictorias(equipo2.getVictorias() + 1);
                            equipo1.setDerrotas(equipo1.getDerrotas() + 1);
                        } else {
                            System.out.println(equipo1.getNombre() + " y " + equipo2.getNombre() + " han quedado en empate.");
                            equipo1.setEmpates(equipo1.getEmpates() + 1);
                            equipo2.setEmpates(equipo2.getEmpates() + 1);
                        }
                    } else {
                        if (random < 35) {
                            System.out.println("Ha ganado " + equipo1.getNombre());
                            equipo1.setVictorias(equipo1.getVictorias() + 1);
                            equipo2.setDerrotas(equipo2.getDerrotas() + 1);
                        } else if (random > 35 && random < 80) {
                            System.out.println("Ha ganado " + equipo2.getNombre());
                            equipo2.setVictorias(equipo2.getVictorias() + 1);
                            equipo1.setDerrotas(equipo1.getDerrotas() + 1);
                        } else {
                            System.out.println(equipo1.getNombre() + " y " + equipo2.getNombre() + " han quedado en empate.");
                            equipo1.setEmpates(equipo1.getEmpates() + 1);
                            equipo2.setEmpates(equipo2.getEmpates() + 1);
                        }
                    }
                }
            }
        }
    }

    private static void gestionarEquipo(ArrayList<Equipo> equipos, ArrayList<Persona> personas) {
        String nombreEquipo;
        Equipo equipo;
        System.out.println("Que equipo desea gestionar? ");
        nombreEquipo = sc.nextLine();
        if (existeEquipo(nombreEquipo, equipos)) {
            equipo = seleccionarEquipo(nombreEquipo, equipos);
            switch (menuEquipo()) {
                case 1:
                    darDeBajaEquipo(equipos, equipo.getNombre()); // Done
                    break;
                case 2:
                    modificarPresidente(equipo); // Done
                    break;
                case 3:
                    destituirEntrenador(equipo); // Done
                    break;
                case 4:
                    fichar(equipo, personas, equipos); // Done, arreglar excepciones de variables
                    break;
                case 5:
                    transferirJugador(equipo, personas, equipos); //Done, arreglar foreach y resto de ramas
                    break;
                case 0:
                    break;
            }
        } else {
            System.out.println(nombreEquipo + " no existe en la liste de equipos.");
        }
    }

    private static void fichar(Equipo equipo, ArrayList<Persona> personas, ArrayList<Equipo> equipos) {
        char respuesta;
        String nombre;
        int dorsal;
        boolean aux, fichado;

        aux = true;
        fichado = false;
        do {
            System.out.println("Que quieres fichar, ¿Jugador (J) o entrenador (E)?");
            respuesta = sc.nextLine().charAt(0);
            if (respuesta == 'j' || respuesta == 'J' || respuesta == 'e' || respuesta == 'E') {
                aux = false;
            } else {
                System.out.println("Escoje una opción correcta.");
            }
        } while (aux);

        if (respuesta == 'j' || respuesta == 'J') {
            for (Persona jugador : personas) {
                if (jugador instanceof Jugador) {
                    if (jugador.getEquipo() == null) {
                        System.out.println(jugador);
                    }
                }
            }
            System.out.println("Nombre del jugador: ");
            nombre = sc.nextLine();
            System.out.println("Dorsal del jugador: ");
            dorsal = sc.nextInt();
            sc.nextLine();

            for (Persona jugador : personas) {
                if (jugador instanceof Jugador) {
                    if (nombre.equalsIgnoreCase(jugador.getNombre()) && dorsal == ((Jugador) jugador).getDorsal()) {
                        System.out.println("Se ha transferido correctamente a " + jugador.getNombre() + " con dorsal " + ((Jugador) jugador).getDorsal() + " al equipo " + equipo.getNombre());
                        List<Jugador> listAux = equipo.getJugadores();
                        listAux.add((Jugador) jugador);
                        equipo.setJugadores((ArrayList<Jugador>) listAux);
                        jugador.setEquipo(equipo);
                        fichado = true;
                    }
                }
            }
        } else {
            System.out.println("Has elegido entrenador");
            for (Persona entrenador : personas) {
                if (entrenador instanceof Entrenador) {
                    if (entrenador.getEquipo() == null) {
                        System.out.println(entrenador);
                    }
                }
            }
            System.out.println("Nombre del entrenador: ");
            nombre = sc.nextLine();

            for (Persona entrenador : personas) {
                if (entrenador instanceof Entrenador) {
                    if (nombre.equalsIgnoreCase(entrenador.getNombre())) {
                        try {
                            equipo.getEntrenador().setEquipo(null);
                        } catch (Exception e) {
                        }
                        System.out.println("Se ha transferido correctamente a " + entrenador.getNombre() + " al equipo " + equipo.getNombre());
                        equipo.setEntrenador((Entrenador) entrenador);
                        entrenador.setEquipo(equipo);
                        fichado = true;
                    }
                }
            }

        }
        if (!fichado) {
            System.out.println("No se ha realizado el fichaje.");
        }
    }

    private static boolean existeEquipo(String nombre, ArrayList<Equipo> equipos) {
        boolean encontrado;

        encontrado = false;
        for (Equipo equipo : equipos) {
            if (nombre.equalsIgnoreCase(equipo.getNombre())) encontrado = true;
        }
        return encontrado;
    }

    private static Equipo seleccionarEquipo(String nombreAux, ArrayList<Equipo> equipos) {
        for (Equipo equipo : equipos) {
            if (nombreAux.equalsIgnoreCase(equipo.getNombre())) return equipo;
        }
        return null;
    }

    private static void transferirJugador(Equipo equipo, ArrayList<Persona> personas, ArrayList<Equipo> equipos) {
        char pertenece, respuesta;
        String equipoNombre = "", nombreJugador;
        int dorsalJugador, indice = 0;

        equipoNombre = nombreEquipo();

        System.out.println("Nombre del jugador: ");
        nombreJugador = sc.nextLine();
        System.out.println("Dorsal del jugador: ");
        dorsalJugador = sc.nextInt();
        sc.nextLine();

        for (Equipo equipoL : equipos) {
            if (equipoNombre.equalsIgnoreCase(equipoL.getNombre())) {
                for (Jugador jugador : equipoL.getJugadores()) {
                    if (jugador.getNombre().equalsIgnoreCase(nombreJugador) && jugador.getDorsal() == dorsalJugador) {
                        System.out.println("Desea fichar a " + jugador.getNombre() + " con dorsal " + jugador.getDorsal() + " del equipo " + equipoL.getNombre() + " al equipo " + equipo.getNombre() + "? (S/N)");
                        respuesta = sc.nextLine().charAt(0);
                        if (respuesta == 's' || respuesta == 'S') {
                            if (jugador.isTransferible()) {
                                jugador.transferirAEquip(equipo);

                                ArrayList<Jugador> listAuxNuevo = (ArrayList<Jugador>) equipo.getJugadores();
                                listAuxNuevo.add(jugador);
                                equipo.setJugadores(listAuxNuevo);

                                List<Jugador> listAuxViejo = equipoL.getJugadores();
                                for (int i = 0; i < listAuxViejo.size(); i++) {
                                    if (listAuxViejo.get(i).getNombre().equalsIgnoreCase(jugador.getNombre())) {
                                        indice = i;
                                    }
                                }
                                listAuxViejo.remove(indice);
                                equipoL.setJugadores((ArrayList<Jugador>) listAuxViejo);
                            } else {
                                System.out.println(jugador.getNombre() + " no es transferible todavía, vuelve a intentarlo más tarde.");
                            }
                        }
                    }
                }
            }
        }
    }

    private static void destituirEntrenador(Equipo equipo) {
        char respuesta;
        boolean aux;

        if (equipo.getEntrenador() == null) {
            System.out.println("Este equipo no tiene entrenador.");
        } else {
            System.out.println("El entrenador de " + equipo.getNombre() + " es " + equipo.getEntrenador().getNombre());
            aux = true;
            do {
                System.out.println("Quieres destituir al entrenador? (S/N)");
                respuesta = sc.nextLine().charAt(0);
                if (respuesta == 's' || respuesta == 'S') {
                    System.out.println("De acuerdo, el entrenador " + equipo.getEntrenador().getNombre() + " ha sido destituido de " + equipo.getNombre());
                    equipo.getEntrenador().setEquipo(null);
                    equipo.setEntrenador(null);
                    aux = false;
                } else if (respuesta == 'n' || respuesta == 'N') {
                    System.out.println("De acuerdo, el entrenador de " + equipo.getNombre() + " sigue siendo " + equipo.getEntrenador().getNombre());
                    aux = false;
                }
            } while (aux);

        }
    }

    private static void modificarPresidente(Equipo equipo) {
        if (equipo.getPresidente().isEmpty()) {
            System.out.println("Este equipo no tiene presidente\n");
        } else {
            System.out.println("El presidente del equipo " + equipo.getNombre() + " es " + equipo.getPresidente() + "\n");
        }
        System.out.println("Nombre del nuevo presidente: ");
        equipo.setPresidente(sc.nextLine());
    }

    private static void darDeBajaEquipo(ArrayList<Equipo> equipos, String nombre) {
        char respuesta;
        boolean aux;

        aux = true;
        for (int i = 0; i < equipos.size(); i++) {
            if (nombre.equalsIgnoreCase(equipos.get(i).getNombre())) {
                do {
                    System.out.println("Estas seguro de que quieres eliminar " + equipos.get(i).getNombre() + " de la lista de equipos? (S/N)");
                    respuesta = sc.nextLine().charAt(0);
                    if (respuesta == 's' || respuesta == 'S') {
                        System.out.println("Se ha eliminado " + equipos.get(i).getNombre() + " de la lista de equipos.\n");
                        equipos.remove(i);
                        aux = false;
                    } else if (respuesta == 'n' || respuesta == 'N') {
                        System.out.println("No se ha eliminado a " + equipos.get(i).getNombre() + " de la lista de equipos.\n");
                        aux = false;
                    } else {
                        System.out.println("Respuesta incorrecta.");
                    }
                } while (aux);
            }
        }
    }

    private static void darAltaJugadorPersona(ArrayList<Persona> personas) {

        char eleccion, respuesta;
        int motivacion = 5, sueldo, torneos = 0, dorsal = 0, calidad = 0;
        String nombre, apellido, any, posicion = null;
        boolean aux, aux2, aux3, nacional = false;


        aux = true;
        do {
            System.out.println("Quieres dar de alta un jugador (j) o un entrenador(e)? ");
            eleccion = sc.nextLine().charAt(0);
            if (eleccion == 'j' || eleccion == 'J') {
                aux = false;
                System.out.println("Has elegido jugador");
                System.out.println("===================");
            } else if (eleccion == 'e' || eleccion == 'E') {
                aux = false;
                System.out.println("Has elegido entrenador");
                System.out.println("======================");

            } else {
                System.out.println("Opcion incorrecta.");
            }
        } while (aux);

        System.out.println("Nombre: ");
        nombre = sc.nextLine();
        System.out.println("Apellido: ");
        apellido = sc.nextLine();
        System.out.println("Fecha de nacimiento (DD/MM/AAAA): ");
        any = sc.nextLine();
        sueldo = devolverNumero("Sueldo: ");


        if (eleccion == 'j' || eleccion == 'J') {
            dorsal = devolverNumero("Dorsal jugador: ");
            aux3 = true;
            do {
                System.out.println("Posición (POR, DEF, MED, DEL): ");
                posicion = sc.nextLine().toUpperCase();
                if (posicion.equalsIgnoreCase("POR") ||
                        posicion.equalsIgnoreCase("DEF") ||
                        posicion.equalsIgnoreCase("MED") ||
                        posicion.equalsIgnoreCase("DEL")) {
                    aux3 = false;
                } else {
                    System.out.println("Posicion incorrecta.");
                }
            } while (aux3);
            calidad = random.nextInt(71) + 30;
        } else {
            torneos = devolverNumero("Torneos ganados: ");
            aux2 = true;
            do {
                System.out.println("Seleccionador nacional (S/N): ");
                respuesta = sc.nextLine().charAt(0);
                if (respuesta == 's' || respuesta == 'S') {
                    nacional = true;
                    aux2 = false;
                } else if (respuesta == 'n' || respuesta == 'N') {
                    aux2 = false;
                } else {
                    System.out.println("Respuesta incorrecta.");
                }
            } while (aux2);
        }

        if (eleccion == 'j' || eleccion == 'J') {
            personas.add(new Jugador(nombre, apellido, any, motivacion, sueldo, dorsal, posicion, calidad));
        } else {
            personas.add(new Entrenador(nombre, apellido, any, motivacion, sueldo, torneos, nacional));
        }
    }

    private static int devolverNumero(String frase) {
        int numero;
        do {
            try {
                System.out.println(frase);
                numero = sc.nextInt();
                sc.nextLine();
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: introduce un numero.");
                sc.nextLine();
            }
        } while (true);
        return numero;
    }

    private static void darAltaEquipo(ArrayList<Equipo> equipos) {
        String nombre, ciudad, estadio, presidente;
        boolean aux;
        int any;

        do {
            aux = true;
            System.out.println("Nombre del equipo: ");
            nombre = sc.nextLine();
            for (Equipo equipo : equipos) {
                if (nombre.equalsIgnoreCase(equipo.getNombre())) {
                    System.out.println("\nEste nombre ya está utilizado.\n");
                    aux = false;
                }
            }
        } while (!aux);

        System.out.println("Año de fundación: ");
        any = sc.nextInt();
        sc.nextLine();

        System.out.println("Ciudad: ");
        ciudad = sc.nextLine();

        Equipo eqAux = new Equipo(nombre, any, ciudad);

        System.out.print("Quieres ponerle nombre al estadio? ");
        if (preguntaSiNo()) {
            System.out.println("Nombre del estadio: ");
            estadio = sc.nextLine();
            eqAux.setEstadio(estadio);
        }

        System.out.print("Quieres ponerle nombre al presidente? ");
        if (preguntaSiNo()) {
            System.out.println("Nombre del presidente: ");
            presidente = sc.nextLine();
            eqAux.setPresidente(presidente);
        }
        equipos.add(eqAux);
    }

    private static boolean preguntaSiNo() {
        char respuesta;
        boolean aux;

        System.out.println("(S/N)");
        aux = false;
        do {
            respuesta = sc.nextLine().charAt(0);
            if (respuesta == 's' || respuesta == 'S') {
                return true;
            } else if (respuesta == 'n' || respuesta == 'N') {
                aux = true;
            } else {
                System.out.println("Introduce una opcion correcta.");
            }
        } while (!aux);
        return false;
    }

    private static void preguntarPorJugador(ArrayList<Equipo> equipos) {
        String nombreEquipo, nombreJugador;
        int dorsarJugador;
        Equipo auxEq;
        ArrayList<Jugador> jugadoresEquipo;

        nombreEquipo = nombreEquipo();
        if (existeEquipo(nombreEquipo, equipos)) {
            auxEq = devolverEquipo(nombreEquipo, equipos);
            jugadoresEquipo = (ArrayList<Jugador>) auxEq.getJugadores();

            System.out.println("Nombre jugador: ");
            nombreJugador = sc.nextLine();
            do {
                try {
                    System.out.println("Dorsal jugador: ");
                    dorsarJugador = sc.nextInt();
                    sc.nextLine();
                    break;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Error: introduce un numero.");
                    sc.nextLine();
                }
            } while (true);

            for (Jugador jugador : jugadoresEquipo) {
                if (jugador.getNombre().equalsIgnoreCase(nombreJugador)) {
                    if (jugador.getDorsal() == dorsarJugador) {
                        System.out.println(jugador);
                    } else {
                        System.out.println(nombreJugador + " no existe con el dorsal " + dorsarJugador + " en el equipo " + auxEq.getNombre());
                    }
                } else {
                    System.out.println(nombreJugador + " no existe en " + auxEq.getNombre());
                }
            }
        } else {
            System.out.println("No existe el equipo " + nombreEquipo);
        }
        /*
        boolean aux1, aux2;
        String nombreJugador;
        int dorsarJugador;

        System.out.println("Nombre jugador: ");
        nombreJugador = sc.nextLine();
        System.out.println("Dorsal jugador: ");
        dorsarJugador = sc.nextInt();

        aux1 = true;
        aux2 = true;
        for (Equipo equipo:equipos){
            if (nombre.equalsIgnoreCase(equipo.getNombre())){
                for (Jugador jugador : equipo.getJugadores()){
                    if (nombreJugador.equalsIgnoreCase(jugador.getNombre()) && dorsarJugador == jugador.getDorsal()){
                        System.out.println("\n" + jugador + "\n");
                        aux2 = false;
                    }
                }
                aux1 = false;
            }
        }
        if (aux1){
            System.out.println(nombre + " no existe." + "\n");
        } else if (aux2) {
            System.out.println(nombreJugador + " no existe." + "\n");
        }
    */
    }

    private static Equipo devolverEquipo(String nombreEquipo, ArrayList<Equipo> equipos) {
        for (Equipo equipo : equipos) {
            if (nombreEquipo.equalsIgnoreCase(equipo.getNombre())) {
                return equipo;
            }
        }
        return null;
    }

    private static void mostrarDatosEquipo(ArrayList<Equipo> equipos, String nombre) {
        boolean aux;

        aux = true;
        for (Equipo equipo : equipos) {
            if (nombre.equalsIgnoreCase(equipo.getNombre())) {
                System.out.println("\n" + equipo + "\n");
                aux = false;
            }
        }
        if (aux) {
            System.out.println("\n" + nombre + " no existe." + "\n");
        }
    }

    private static String nombreEquipo() {
        System.out.println("Nombre del equipo: ");
        return sc.nextLine();
    }

    private static int menu() {
        int menu;

        do {
            try {
                System.out.println("Welcome to Politécnics Fotball Manager:");
                System.out.println("            1.- Ver clasificación liga");
                System.out.println("            2.- Gestionar equipo");
                System.out.println("            3.- Dar de alta equipo");
                System.out.println("            4.- Dar de alta jugador/a o entrenador/a");
                System.out.println("            5.- Consultar datos equipo");
                System.out.println("            6.- Consultar datos jugador/a");
                System.out.println("            7.- Disputar liga nueva");
                System.out.println("            8.- Realizar sesión entrenamiento");
                System.out.println("            9.- Cargar datos equipos");
                System.out.println("           10.- Guardar datos equipos");
                System.out.println("            0.- Salir");
                System.out.println("\n\n// 11. Mostrar Personas (Opcion de desarrollador)");
                System.out.println("// 12. Mostrar  Equipos (Opcion de desarrollador)");

                menu = sc.nextInt();
                sc.nextLine();

                if (menu >= 0 && menu <= 12) {
                    return menu;
                } else {
                    System.out.println("Error: Debes ingresar un número válido.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número entero.");
                sc.nextLine();
            }
        } while (true);
    }

    private static int menuEquipo() {
        int menu;

        do {
            try {
                System.out.println("Team Manager:");
                System.out.println("            1.- Dar de baja equipo");
                System.out.println("            2.- Modificar president/a");
                System.out.println("            3.- Destituir entrenador/a");
                System.out.println("            4.- Fichar jugador/a o entrenador/a");
                System.out.println("            5.- Transferir jugador/a");
                System.out.println("            0.- Salir");

                menu = sc.nextInt();
                sc.nextLine();

                if (menu >= 0 && menu <= 5) {
                    return menu;
                } else {
                    System.out.println("Error: Debes ingresar un número válido.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número entero.");
            }
        } while (true);
    }

}