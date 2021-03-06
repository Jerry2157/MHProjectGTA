package com.mh.itesm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by gerardomagdaleno on 01/04/17.
 */

public class Hongo extends Objeto
{
    private final float VELOCIDAD_X = -200;      // Velocidad horizontal (a la izquierda)

    private Animation<TextureRegion> spriteAnimado;         // Animación caminando
    private float timerAnimacion;                           // Tiempo para cambiar frames de la animación

    // Recibe una imagen con varios frames (ver enemigo.png)
    public Hongo(Texture textura, float x, float y) {
        // Lee la textura como región
        TextureRegion texturaCompleta = new TextureRegion(textura);
        // La divide en 2 frames de 32x64 (ver enemigo.png)
        //TextureRegion[][] texturaPersonaje = texturaCompleta.split(32,32);
        TextureRegion[][] texturaPersonaje = texturaCompleta.split(40,40);
        // Crea la animación con tiempo de 0.25 segundos entre frames.
        //spriteAnimado = new Animation(0.1f, texturaPersonaje[0][0], texturaPersonaje[0][1]);
        spriteAnimado = new Animation(0.1f, texturaPersonaje[0][0]);
        // Animación infinita
        spriteAnimado.setPlayMode(Animation.PlayMode.LOOP);
        // Inicia el timer que contará tiempo para saber qué frame se dibuja
        timerAnimacion = 0;
        // Crea el sprite
        sprite = new Sprite(texturaPersonaje[0][0]);
        sprite.setPosition(x,y);    // Posición inicial
    }

    // Dibuja el hongo
    public void dibujar(SpriteBatch batch) {
        // Dibuja el personaje dependiendo del estadoMovimiento
        timerAnimacion += Gdx.graphics.getDeltaTime();
        // Frame que se dibujará
        TextureRegion region = spriteAnimado.getKeyFrame(timerAnimacion);
        batch.draw(region, sprite.getX(), sprite.getY());
    }

    // Mueve el personaje a la izquierda
    public void mover(float delta) {
        float distancia = VELOCIDAD_X*delta;
        sprite.setX(sprite.getX()+distancia);
    }

    public boolean chocaCon(StevenCats steven) {
        /*boolean toco = false;
        if ((sprite.getX()-steven.sprite.getX())<10 && (sprite.getY()-steven.sprite.getY())<5){
            toco = true;
        }else{
            toco = false;
        }*/
        return sprite.getBoundingRectangle().overlaps(steven.sprite.getBoundingRectangle());
        //return toco;
    }
    public boolean chocaCon(PlayerStevenFinal steven) {
        /*boolean toco = false;
        if ((sprite.getX()-steven.sprite.getX())<10 && (sprite.getY()-steven.sprite.getY())<5){
            toco = true;
        }else{
            toco = false;
        }*/
        return sprite.getBoundingRectangle().overlaps(steven.sprite.getBoundingRectangle());
        //return toco;
    }
    public boolean chocaCon() {
        return false;
    }
}
