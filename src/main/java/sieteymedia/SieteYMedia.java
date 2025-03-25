package sieteymedia;

import recursos.Baraja;
import recursos.Carta;

public class SieteYMedia {

    public Baraja baraja;
    public Carta[] cartasJugador;
    public Carta[] cartasBanca;

    public SieteYMedia() {
        baraja = new Baraja();
        baraja.barajar();
        // se van pidiendo cartas al jugar pero matemáticamente a partir de 15 siempre
        // nos pasamos
        // hay 12 cartas de medio puntos, si sacara estas 12 luego cartas con valor 1
        // vemos que a partir de 15 cartas siempre se pasas
        cartasJugador = new Carta[15];
        cartasBanca = new Carta[15];
    }

    public void turnoJugador() {
        Carta c = baraja.darCartas(1)[0];
        // insertamos c en las cartas del jugador
        insertarCartaEnArray(cartasJugador, c);
    }

    public void turnoBanca() {
        // lo primero es consultar el valor que alcanzó el jugador en su turno
        double valorCartasJugador = valorCartas(cartasJugador);

        // juega hasta empatar o superar
        while (valorCartas(cartasBanca) < valorCartasJugador) {
            Carta c = baraja.darCartas(1)[0];
            insertarCartaEnArray(cartasBanca, c);
        }
    }

    public double valorCartas(Carta[] cartas) {
        double total = 0.0;
        int val;
        int i = 0;
        while (cartas[i] != null) {
            val = cartas[i].getNumero();
            total += (val > 7) ? 0.5 : val;
            i++;
        }

        return total;
    }

    public void insertarCartaEnArray(Carta[] cartas, Carta c) {
        // inserta al final detectando el primer null
        int i = 0;
        while (cartas[i] != null) {
            i++;
        }
        cartas[i] = c;

    }

    public String mostrarCartas(Carta[] cartas) {
        StringBuilder resultado = new StringBuilder();
        int i = 0;

        while (i < cartas.length && cartas[i] != null) {
            resultado.append(" ").append(cartas[i]);
            i++;
        }

        return resultado.toString();
    }


}
