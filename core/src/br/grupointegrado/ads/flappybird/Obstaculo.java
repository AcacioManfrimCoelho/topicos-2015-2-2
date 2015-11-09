package br.grupointegrado.ads.flappybird;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by ACÁCIO on 26/10/2015.
 */
public class Obstaculo {

    private World mundo;
    private OrthographicCamera camera;
    private Body corpoCima, corpoBaixo;
    private float posX;
    private float posYCima, posYBaixo;
    private float largura, altura;
    private boolean passou;

    private Obstaculo ultimoObstaculo;   //ultimo antes do atuapul

    public Obstaculo(World mundo, OrthographicCamera camera, Obstaculo ultimoObstaculo) {
        this.mundo = mundo;
        this.camera = camera;
        this.ultimoObstaculo = ultimoObstaculo;

        intPosicao();
        intCorpoCima();
        intCorpoBAixo();
    }

    private void intCorpoBAixo() {
         corpoBaixo = Util.criarCorpo(mundo, BodyDef.BodyType.StaticBody, posX, posYBaixo);
         PolygonShape shape = new PolygonShape();
         shape.setAsBox(largura / 2, altura /2);

         Util.criarForma(corpoBaixo, shape, "OBSTACULO_BAIXO");
         shape.dispose();

    }

    private void intCorpoCima() {
        corpoCima = Util.criarCorpo(mundo, BodyDef.BodyType.StaticBody, posX, posYCima);
        PolygonShape shepe = new PolygonShape();
        shepe.setAsBox(largura / 2, altura / 2);
        Util.criarForma(corpoCima, shepe, "OBSTACULO_CIMA");
        shepe.dispose();

    }

    private void intPosicao() {


    }

    private void initPosicao(){

        largura = 40 / Util.PIXEL_METRO;
        altura = camera.viewportHeight / Util.PIXEL_METRO;

        float xInicial = largura;
        if (ultimoObstaculo != null)
            xInicial = ultimoObstaculo.getX();

        posX = xInicial + 8;  //4mt é a distancia entre os obstaculos

        //quebrou o restante da tela em 6 partes para encontrar a posicao de y, obstaculo inferior
        float parcela = (altura - Util.ALTURA_CHAO) / 6;

        int multiplicador = MathUtils.random(1, 3); //gera numero entre 1 e 3;

        posYBaixo = Util.ALTURA_CHAO + (parcela * multiplicador) -(altura / 2);
        posYCima  = posYBaixo + altura + 2f; //2f =2mt espaço entre os canos;


    }

    public float getX(){
        return this.posX;
    }


    public void remover(){
        mundo.destroyBody(corpoCima);
        mundo.destroyBody(corpoBaixo);

    }


    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getLargura() {
        return largura;
    }

    public void setLargura(float largura) {
        this.largura = largura;
    }

    public boolean isPassou() {
        return passou;
    }

    public void setPassou(boolean passou) {
        this.passou = passou;
    }
}
