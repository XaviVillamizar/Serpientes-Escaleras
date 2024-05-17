package serpienteescalera;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class JuegoFrame extends JFrame {

    private Juego juego;
    private JPanel tableroPanel;
    private JPanel infoPanel;
    private JLabel turnoLabel;
    private JLabel dadoLabel;
    private JButton lanzarDadoButton;
    private JTextArea registroEventos;
    private JButton reiniciarButton;
    private JFrame mainFrame;
    private int tamañoTablero;
    private int numJugadores;
    private Color[] coloresJugadores = {new Color(255, 153, 153), new Color(153, 204, 255), new Color(204, 255, 204), new Color(255, 204, 255)};  // Colores para los jugadores

    public JuegoFrame(int tamañoTablero, int numJugadores) {
        this.tamañoTablero = tamañoTablero;
        this.numJugadores = numJugadores;
        juego = new Juego(tamañoTablero, numJugadores);
        initComponents();
        this.setLocationRelativeTo(null);
        configurarTablero();

    }

    private void initComponents() {
        setTitle("Serpientes y Escaleras");
        setSize(1414, 805);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setResizable(false);
        setLayout(new BorderLayout());

        tableroPanel = new JPanel(new GridLayout(tamañoTablero, tamañoTablero)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarSerpientesYEscaleras(g);
                tableroPanel.setBackground(Color.WHITE);
                tableroPanel.setOpaque(true);
            }
        };
        
        ////////////////////////////////////// registro jtextfield

        
        
        infoPanel = new JPanel(new GridLayout(4, 1));
        registroEventos = new JTextArea();
        registroEventos.setEditable(false);
        registroEventos.setColumns(25);
        registroEventos.setLineWrap(true);
        registroEventos.setWrapStyleWord(true);
        infoPanel.setBackground(new Color(109, 45, 161));
        infoPanel.setOpaque(true);

        turnoLabel = new JLabel("Turno: " + juego.getJugadorActual().getNombre());
        dadoLabel = new JLabel("Dado: ");

        ////////////////////////////////////// label turnos juagodres y dado
        
        Font labelFont = turnoLabel.getFont();
        turnoLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 24)); // Cambia el tamaño a 24
        dadoLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 24)); // Cambia el tamaño a 24
        turnoLabel.setForeground(Color.WHITE);
        dadoLabel.setForeground(Color.WHITE);
        turnoLabel.setHorizontalAlignment(JLabel.CENTER);
        dadoLabel.setHorizontalAlignment(JLabel.CENTER);
        lanzarDadoButton = new JButton("Lanzar Dado");

        lanzarDadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resultado = juego.tirarDado();
                dadoLabel.setText("Dado: " + resultado);
                juego.moverJugador(resultado);
                actualizarTablero();
                registrarEvento(juego.getJugadorActual().getNombre() + " lanzó un " + resultado + " y está en la posición " + juego.getJugadorActual().getPosicion());

                if (juego.esFinDelJuego()) {
                    juego.terminarJuego();
                    lanzarDadoButton.setEnabled(false);
                    reiniciarButton.setEnabled(true); // Habilita el botón de reinicio cuando termine el juego
                } else {
                    juego.siguienteTurno();
                    turnoLabel.setText("Turno: " + juego.getJugadorActual().getNombre());
                }
            }
        });
        
        //////////////////////////////////////ir al menu al momento de que haya un ganador

        reiniciarButton = new JButton("Volver al Menú Principal");
        reiniciarButton.setEnabled(false); // Deshabilitado inicialmente
        reiniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAlMenuPrincipal();
            }
        });

        infoPanel.add(turnoLabel);
        infoPanel.add(dadoLabel);
        infoPanel.add(lanzarDadoButton);
        infoPanel.add(reiniciarButton);
        infoPanel.add(new JScrollPane(registroEventos));

        add(tableroPanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.EAST);
    }

    private void volverAlMenuPrincipal() {
        this.dispose();
        Main mainJ = new Main();
        if (mainJ != null) {
            mainJ.setVisible(true);
        }
    }
    
    //////////////////////////////////////Configuraciones tablero

    private void configurarTablero() {
        tableroPanel.setLayout(new GridLayout(tamañoTablero, tamañoTablero));

        for (int fila = tamañoTablero - 1; fila >= 0; fila--) {
            for (int columna = 0; columna < tamañoTablero; columna++) {
                int numeroCasilla;
                if (fila % 2 == 0) {
                    numeroCasilla = (fila * tamañoTablero) + (columna + 1);
                } else {
                    numeroCasilla = (fila * tamañoTablero) + (tamañoTablero - columna);
                }
                // diseño
                JPanel casilla = new JPanel(new BorderLayout());
                casilla.setOpaque(false);
                JLabel numeroLabel = new JLabel(String.valueOf(numeroCasilla));
                numeroLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                numeroLabel.setVerticalAlignment(SwingConstants.TOP);
                casilla.add(numeroLabel, BorderLayout.EAST);
                casilla.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tableroPanel.add(casilla);
            }
        }
    }

    private void actualizarTablero() {
        tableroPanel.removeAll();
        configurarTablero();

        for (int i = 0; i < juego.getJugadores().size(); i++) {
            Jugador jugador = juego.getJugadores().get(i);
            int fila = (jugador.getPosicion() - 1) / tamañoTablero;
            int columna;
            if (fila % 2 == 0) {
                columna = (jugador.getPosicion() - 1) % tamañoTablero;
            } else {
                columna = tamañoTablero - 1 - ((jugador.getPosicion() - 1) % tamañoTablero);
            }
            int posicion = (tamañoTablero - fila - 1) * tamañoTablero + columna;

            if (posicion >= 0 && posicion < tamañoTablero * tamañoTablero) {
                JPanel casilla = (JPanel) tableroPanel.getComponent(posicion);

                JPanel jugadorPanel = new JPanel();
                jugadorPanel.setBackground(coloresJugadores[i]);

                switch (i) {
                    case 0:
                        jugadorPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
                        jugadorPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
                        jugadorPanel.setSize(new Dimension(5, 5));
                        break;
                    case 1:
                        jugadorPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        jugadorPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
                        jugadorPanel.setSize(new Dimension(5, 5));
                        break;
                    case 2:
                        jugadorPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                        jugadorPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
                        jugadorPanel.setSize(new Dimension(5, 5));
                        break;
                    case 3:
                        jugadorPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        jugadorPanel.setAlignmentY(Component.TOP_ALIGNMENT);

                        break;
                    default:
                        break;
                }

                OverlayLayout overlayLayout = new OverlayLayout(casilla);
                casilla.setLayout(overlayLayout);

                casilla.add(jugadorPanel);
            }
        }

        tableroPanel.revalidate();
        tableroPanel.repaint();
    }
    
    //////////////////////////////////////eventos

    private void registrarEvento(String evento) {
        registroEventos.append(evento + "\n");
    }
    
    //////////////////////////////////////serpientes y escaleras

    private void dibujarSerpientesYEscaleras(Graphics g) {
        Map<Integer, Integer> serpientes = juego.getTablero().getSerpientes();
        Map<Integer, Integer> escaleras = juego.getTablero().getEscaleras();

        for (Map.Entry<Integer, Integer> entry : serpientes.entrySet()) {
            dibujarLinea(g, entry.getKey(), entry.getValue(), Color.GREEN, Color.BLACK, 4, true); // true indica que es serpiente
        }

        for (Map.Entry<Integer, Integer> entry : escaleras.entrySet()) {
            dibujarLinea(g, entry.getKey(), entry.getValue(), new Color(139, 69, 19), Color.BLACK, 4, false); // false indica que es escalera
        }
    }

    private void dibujarLinea(Graphics g, int inicio, int fin, Color color, Color borderColor, int grosor, boolean esSerpiente) {
        int tamCasilla = tableroPanel.getWidth() / tamañoTablero;

        int xInicio = calcularCoordenadaX(inicio, tamCasilla);
        int yInicio = calcularCoordenadaY(inicio, tamCasilla);
        int xFin = calcularCoordenadaX(fin, tamCasilla);
        int yFin = calcularCoordenadaY(fin, tamCasilla);

        System.out.println("Dibujando linea de " + inicio + " a " + fin + ": (" + xInicio + ", " + yInicio + ") -> (" + xFin + ", " + yFin + ")");

        Graphics2D g2 = (Graphics2D) g;

        ////////////////////////////////////// línea 
        g2.setColor(color);
        g2.setStroke(new BasicStroke(grosor));
        g2.drawLine(xInicio, yInicio, xFin, yFin);
    }

    private int calcularCoordenadaX(int posicion, int tamCasilla) {
        int fila = (posicion - 1) / tamañoTablero;
        int columna = (posicion - 1) % tamañoTablero;
        if (fila % 2 == 0) {
            return columna * tamCasilla + tamCasilla / 2;
        } else {
            return (tamañoTablero - 1 - columna) * tamCasilla + tamCasilla / 2;
        }
    }

    private int calcularCoordenadaY(int posicion, int tamCasilla) {
        int fila = (posicion - 1) / tamañoTablero;
        return (tamañoTablero - 1 - fila) * tamCasilla + tamCasilla / 2;
    }
}
