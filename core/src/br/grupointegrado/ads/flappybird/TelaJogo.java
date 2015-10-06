package br.grupointegrado.ads.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

/**
 * acacio.
 */
public class TelaJogo extends TelaBase {



    private OrthographicCamera camera; //camera do jogo      +_ 0 mundo é o jogo em movimento
    private World mundo; // representa o mundo do Box2D

    private Box2DDebugRenderer debug; //representa o mundo na tela para ajudar no desenvolvimento.
    private Passaro passaro;

    public TelaJogo(MainGame game) {
        super(game);

    }

    @Override
    public void show() {

        camera = new OrthographicCamera(Gdx.graphics.getWidth() / ESCALA, Gdx.graphics.getHeight() / ESCALA);
        debug = new Box2DDebugRenderer();
        mundo = new World(new Vector2(0, -9.8f), false);

        initChao();
        initPassaro();
    }


    private void initChao(){
        chao = Uitl.criarCorpo(mundo, BodyDef.BodyType.StaticBody, 0 , 0);
    }
    private void initPassaro() {
        passaro = new Passaro(mundo);
    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);// limpa a tela e pinta a cor de fundo
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //mantem o buffer de cores



        //desenhar as imagens
        private void renderizar (float){


        }

        // atualizar calculos do corpo
        private void atualizar(){
            mundo.step(1f, 60f, 6, 2)
            autalizarchao();
        }

        //atualiza a posicao do chao para acompanhar a posicao do passaro
        private void autalizarchao(){
            float largura = camera  = camera.viewportWindth / Util.PIXEL_METRO;
            vetor2 posicao = chao.getPosition();
            posicao.x = largura / 2;
            chao.transform(posicao, 0);

        }

        debug.render(mundo, camera.combined.CPY().scl(PIXEL_METRO));

    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width / ESCALA, height / ESCALA );
        camera.update();
        redimensionaChao();
    }

    //configura o tamanho do chao de acordo com o tamanho da tela.
    private void redimensionaChao(){
        chao.getFixtureList().clear();
        float largura = camera.viewportWidth / Uitl.PIXEL_METRO;
        PoligonShape shepe = new PolygonShape();
        shepe.setAsBox(largura /2 , Util.ALTURA_CHAO / 2);
        Fixture forma = Util.criarForma(chao, shape, "CHAO");
        shepe.dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

        debug.dispose();
        mundo.dispose();
    }
}
