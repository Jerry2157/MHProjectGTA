package com.mh.itesm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 * Created by gerardomagdaleno on 20/11/17.
 */

public class PlayerStevenFinal extends PersonajeStevenFinal
{
    // Variables para el salto
    private final float G = 98.1f;  // Gravedad
    private final float velocidadInicial = 250;  // Velocidad de salida (hacia arriba)
    private float ymax;     // Altura máxima
    private float tiempoVuelo;  // Tiempo de vuelo TOTAL

    private float alturaVolando;  // La posición actual cuando está saltando
    private float tiempoVolando;    // El tiempo que ha transcurrido desde que inició el salto
    private float yInicial;     // Posición donde inicia el salto

    private EstadoSalto estadoSalto = EstadoSalto.EN_PISO;

    public PlayerStevenFinal(Texture textura, float x, float y) {
        super(textura, x, y);
        setEstadoMovimiento(EstadoMovimiento.MOV_DERECHA);
    }

    public void actualizar(float delta) {

        // Calcula la nueva posición (por ahora cuando está saltando)
        if ( estadoSalto == EstadoSalto.SALTANDO ) {
            tiempoVolando += delta*5;   // El factor DES/ACELERA
            alturaVolando = velocidadInicial*tiempoVolando-0.5f*G*tiempoVolando*tiempoVolando;
            if (tiempoVolando<tiempoVuelo) {
                //Sigue en el aire
                sprite.setY(yInicial+alturaVolando);
            } else {
                // Termina el salto
                sprite.setY(yInicial);
                estadoSalto = EstadoSalto.EN_PISO;
            }
        }
    }
    public void actualizar(float delta, TiledMap map) {
        switch (estadoMovimiento) {
            case MOV_DERECHA:
                moverHorizontal(map);
                break;
            case MOV_IZQUIERDA:
                moverHorizontal(map);
                break;
            case MOV_ABAJO:
                moverVertical(map);
                break;
            case MOV_ARRIBA:
                moverVertical(map);
                break;
        }
        switch (estadoSalto) {
            case SUBIENDO:
            case BAJANDO:
                moverVertical(map);
                break;
        }


        // Calcula la nueva posición (por ahora cuando está saltando)
        if ( estadoSalto == EstadoSalto.SALTANDO ) {
            tiempoVolando += delta*5;   // El factor DES/ACELERA
            alturaVolando = velocidadInicial*tiempoVolando-0.5f*G*tiempoVolando*tiempoVolando;
            if (tiempoVolando<tiempoVuelo) {
                //Sigue en el aire
                sprite.setY(yInicial+alturaVolando);
            } else {
                // Termina el salto
                sprite.setY(yInicial);
                estadoSalto = EstadoSalto.EN_PISO;
            }
        }
    }

    @Override
    public void saltar() {
        if (estadoSalto!= EstadoSalto.SALTANDO) {    // No puede saltar mientras está en el aire
            // Iniciar el salto
            ymax = (velocidadInicial * velocidadInicial) / (2 * G);
            tiempoVuelo = (2 * velocidadInicial) / G;
            alturaVolando = 0;    // Inicia en el piso
            tiempoVolando = 0;
            yInicial = sprite.getY();
            estadoSalto = EstadoSalto.SALTANDO;
            Gdx.app.log("saltar", "ymax=" + ymax + ", tiempoV=" + tiempoVuelo + ", y0=" + yInicial);
        }
    }
}
