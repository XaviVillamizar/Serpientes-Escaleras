package serpienteescalera;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Tablero {

    private int tamaño;
    private Map<Integer, Integer> serpientes;
    private Map<Integer, Integer> escaleras;
    private Random random;

    public Tablero(int tamaño) {
        this.tamaño = tamaño;
        serpientes = new HashMap<>();
        escaleras = new HashMap<>();
        random = new Random();
        generarSerpientesYEscaleras();
    }
    
    ////////////////////////////////////// casillas serp y esc

    public void agregarSerpiente(int inicio, int fin) {
        serpientes.put(inicio, fin);
    }

    public void agregarEscalera(int inicio, int fin) {
        escaleras.put(inicio, fin);
    }

    public int getTamaño() {
        return tamaño;
    }

    public int verificarCasilla(int posicion) {
        if (serpientes.containsKey(posicion)) {
            return serpientes.get(posicion);
        } else if (escaleras.containsKey(posicion)) {
            return escaleras.get(posicion);
        }
        return posicion;
    }

    public Map<Integer, Integer> getSerpientes() {
        return serpientes;
    }

    public Map<Integer, Integer> getEscaleras() {
        return escaleras;
    }
    
    ////////////////////////////////////// generar serpientes y escaleras

    private void generarSerpientesYEscaleras() {
        int[][] serpientes = {
            {24, 10}, {56, 28}, {60, 14}, {78, 34}, {84, 66}, {94, 58}, {110, 82}, {118, 74}, {124, 100}, {138, 110},
            {144, 106}, {156, 116}, {166, 122}, {176, 108}, {188, 126}, {202, 190}, {208, 170}, {216, 182}, {224, 198}
        };

        int[][] escaleras = {
            {8, 38}, {20, 50}, {34, 78}, {60, 86}, {68, 98}, {104, 128}, {114, 140},
            {124, 148}, {134, 160}, {142, 172}, {152, 184}, {162, 194}, {172, 206}, {182, 216}, {192, 224}, {198, 234}
        };

        for (int[] serpiente : serpientes) {
            serpiente[0] = Math.min(serpiente[0], tamaño * tamaño);
            serpiente[1] = Math.min(serpiente[1], tamaño * tamaño);
        }

        for (int[] escalera : escaleras) {
            escalera[0] = Math.min(escalera[0], tamaño * tamaño);
            escalera[1] = Math.min(escalera[1], tamaño * tamaño);
        }

        //////////////////////////////////////Agregar serpientes al tablero
        for (int[] serpiente : serpientes) {
            agregarSerpiente(serpiente[0], serpiente[1]);
        }

        ////////////////////////////////////// Agregar escaleras al tablero
        for (int[] escalera : escaleras) {
            agregarEscalera(escalera[0], escalera[1]);
        }
    }
}

        ////////////////////////////////////// GENERACION ALEATORIA DE SERPIENTES Y ESCALERAS EN LOS TABLEROS

/*private void generarSerpientesYEscaleras() {
        int numCasillas = tamaño * tamaño;

        double factorSerpientes = 0.03;
        double factorEscaleras = 0.03;

        int numSerpientes = (int) Math.ceil(numCasillas * factorSerpientes);
        int numEscaleras = (int) Math.ceil(numCasillas * factorEscaleras);

        Set<Integer> posicionesUsadas = new HashSet<>();

        // generar serpientes
        for (int i = 0; i < numSerpientes; i++) {
            int inicio, fin;
            do {
                inicio = random.nextInt(numCasillas - 1) + 2;
                fin = random.nextInt(inicio - 1) + 1;
            } while (posicionesUsadas.contains(inicio) || posicionesUsadas.contains(fin) || inicio == fin);
            posicionesUsadas.add(inicio);
            posicionesUsadas.add(fin);
            agregarSerpiente(inicio, fin);
        }

        // generar escaleras
        for (int i = 0; i < numEscaleras; i++) {
            int inicio, fin;
            do {
                inicio = random.nextInt(numCasillas - 1) + 1;
                fin = random.nextInt(numCasillas - inicio) + inicio + 1;
            } while (posicionesUsadas.contains(inicio) || posicionesUsadas.contains(fin) || inicio == fin);
            posicionesUsadas.add(inicio);
            posicionesUsadas.add(fin);
            agregarEscalera(inicio, fin);
 */
