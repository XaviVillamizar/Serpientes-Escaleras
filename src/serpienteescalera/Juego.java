package serpienteescalera;

import java.util.*;
import javax.swing.JOptionPane;

public class Juego {

    private Tablero tablero;
    private List<Jugador> jugadores;
    private int turnoActual;
    private JuegoFrame juegoFrame;

    public Juego(int tamaño, int numJugadores) {
        tablero = new Tablero(tamaño);
        jugadores = new ArrayList<>();
        for (int i = 0; i < numJugadores; i++) {
            jugadores.add(new Jugador("Jugador " + (i + 1)));
        }
        turnoActual = 0;
    }

    public int tirarDado() {
        return (int) (Math.random() * 6) + 1;
        //return (int) (Math.random() * 6) + 1;
    }
    
    ////////////////////////////////////// jugador

    public void moverJugador(int resultado) {
        Jugador jugadorActual = jugadores.get(turnoActual);
        int nuevaPosicion = jugadorActual.getPosicion() + resultado;

        if (nuevaPosicion > tablero.getTamaño() * tablero.getTamaño()) {
            nuevaPosicion = tablero.getTamaño() * tablero.getTamaño();
        }

        nuevaPosicion = tablero.verificarCasilla(nuevaPosicion);
        jugadorActual.setPosicion(nuevaPosicion);
    }
    
    ////////////////////////////////////// juego

    public boolean esFinDelJuego() {
        return jugadores.stream().anyMatch(jugador -> jugador.getPosicion() == tablero.getTamaño() * tablero.getTamaño());
    }

    public void terminarJuego() {
    Jugador ganador = jugadores.stream().filter(jugador -> jugador.getPosicion() == tablero.getTamaño() * tablero.getTamaño()).findFirst().orElse(null);
    if (ganador != null) {
        JOptionPane.showMessageDialog(null, ganador.getNombre() + " ganó la partida!");
       
    }
}
    
    public String verificarSerpienteEscalera(int nuevaPosicion) {
        Jugador jugadorActual = getJugadorActual();
        Map<Integer, Integer> serpientes = tablero.getSerpientes();
        Map<Integer, Integer> escaleras = tablero.getEscaleras();

        // verificar si la nueva posición es una serpiente
        if (serpientes.containsKey(nuevaPosicion)) {
            int nuevaPosicionSerpiente = serpientes.get(nuevaPosicion);
            jugadorActual.setPosicion(nuevaPosicionSerpiente);
            return "el " + jugadorActual.getNombre() + " ha bajado por una serpiente desde la casilla " + nuevaPosicion + " hasta la casilla " + nuevaPosicionSerpiente;
        }

        // verificar si la nueva posición es una escalera
        if (escaleras.containsKey(nuevaPosicion)) {
            int nuevaPosicionEscalera = escaleras.get(nuevaPosicion);
            jugadorActual.setPosicion(nuevaPosicionEscalera);
            return "felicidades" + jugadorActual.getNombre() + " ha subido por una escalera desde la casilla " + nuevaPosicion + " hasta la casilla " + nuevaPosicionEscalera;
        }

        return null;
    }

    public void siguienteTurno() {
        turnoActual = (turnoActual + 1) % jugadores.size();
    }

    public Jugador getJugadorActual() {
        return jugadores.get(turnoActual);
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void agregarSerpiente(int inicio, int fin) {
        tablero.agregarSerpiente(inicio, fin);
    }

    public void agregarEscalera(int inicio, int fin) {
        tablero.agregarEscalera(inicio, fin);
    }
}

    