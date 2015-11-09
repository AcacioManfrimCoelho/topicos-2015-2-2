package br.grupointegrado.ads.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Joao Paulo on 28/09/2015.
 */
public class TelaJogo extends TelaBase {



    private OrthographicCamera camera; //camera do jogo
    private World mundo; // representa o mundo do Box2D
    private Body chao; // corpo do chao
    private Passaro passaro;
    private Array<Obstaculo> obstaculos = new Array<Obstaculo>();


    private Box2DDebugRenderer debug; //representa o mundo na tela para ajudar no desenvolvimento.
    private FreeType.BitmapFont  fontPontuacao;
    private Stage pauloInformacoes;
    private Label lbPontuacao;
    private ImageButton btnplyer;
    private ImageButton btngameover;
    private OrthographicCamera cameraInfo


    public TelaJogo(MainGame game) {
        super(game);

    }

    @Override
    public void show() {

        camera = new OrthographicCamera(Gdx.graphics.getWidth() / Util.ESCALA, Gdx.graphics.getHeight() / Util.ESCALA);
        debug = new Box2DDebugRenderer();
        mundo = new World(new Vector2(0, -9.8f), false);
        cameraInfo = new OrthographicCamera(GDX, gra)
        mundo.setContactListener(new ContactListener() {
            @Override//antes de tocar
            public void beginContact(Contact contact) {
                detectarColisao(contact.getFixtureA(), contact.getFixtureB());
            }

            @Override//quando tocar
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

        initChao();
        initPassaro();
        initFontes();
        initinformaçoes();

        new Obstaculo(mundo, camera, null);
    }

    private boolean gameOver = false;

    private void detectarColisao(Fixture fixtureA, Fixture fixtureB) {
        if ("PASSARO".equals(fixtureA.getUserData() || "PASSARO"fixtureB.getUserData()) ){
            
        }

    }

    private void initFontes(){
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontCameraator.fee;


        um monte de coisa;

        FreeTypeFontGenerator

        fontePontuacao
        gerador.dispose();
    }

    private void initinformaçoes(){
        palcoInformacoes = new Stage(new f falta coisa  )

      Gdx.input.setInputProcessor(pauloInformacoes);
      Label.LabelStyle mais coisa
      estilo.font = fontPontuacao;
      lbPontuacao = new Label("0", estilo);
      palcoInformacoes.addactor(lbPontuacao);
    }

    private void initChao() {

        chao = Util.criarCorpo(mundo, BodyDef.BodyType.StaticBody, 0, 0);
    }

    private void initPassaro() {
        passaro = new Passaro(mundo, camera, null);


    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);// limpa a tela e pinta a cor de fundo
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //mantem o buffer de cores

        capturaTeclas();

        atualizarObastaculos();
        atualizar(delta);
        renderizar(delta);


        debug.render(mundo, camera.combined.cpy().scl(Util.PIXEL_METRO));

    }


    private void atualizarObastaculos() {
        //enqundto a lista tiver menos do que 4
        while(obstaculos.size <4){
            Obstaculo ultimo = null;

            if (obstaculos.size > 0)
                ultimo = obstaculos.peek();


            Obstaculo o = new Obstaculo( mundo, camera, ultimo );
            obstaculos.add(o);
        }

        //verecida os obstaculos sairam a tela de jogo

        for(Obstaculo o : obstaculos){
            float iniciocamera = passaro.getCorpo().getPosition().x - (camera.viewportHeight/2/Util.PIXEL_METRO)- o.getLargura() ;

            if (iniciocamera > o.getPosX()){
                o.remover();;
                obstaculos.removeValue(o, true);


            }else if (!o.isPassou() && o.getPosX() < passaro.getCorpo().getPosition().x){
                o.setPassou(true);
                //calcular pontuacao


                //reproduzir som
                pontuacao ++;

            }

        }
    }

    private boolean pulando = false;

    private void capturaTeclas() {

        pulando = false;
        if (Gdx.input.justTouched()){
            pulando = true;
        }
    }

    /**
     * renderizar/desenhar as imagens
     * @param delta
     */
    private void renderizar(float delta) {
        pauloInformacoes.ad    tem coida aqui
    }

    /**
     * atualização e calculo dos corpos
     * @param delta
     */
    private void atualizar(float delta) {

        pauloInformacoes.act(delta);
        passaro.atualizar(delta);
        mundo.step(1f / 60f, 6, 2);

        atualizaInformacoes();
        atualizarCameta();
        atualizarChao();
        if (pulando){
            passaro.pular();

        }

    }

    private void atualizaInformacoes() {
        lbPontuacao.setText(pontuacao = "");
        lbPontuacao.setPosition(cameraInfo.viewportHeight/2 - lbPontuacao.getPrefWidth())/2;

    }

    private void atualizarCameta() {
        camera.position.x = (passaro.getCorpo().getPosition().x + 34 * Util.PIXEL_METRO) * Util.PIXEL_METRO;
        camera.update();
    }

    /**
     * Atualiza a posição do chao para acompanhar o passaro.
     */
    private void atualizarChao() {
        Vector2 posicao = passaro.getCorpo().getPosition();
        chao.setTransform(posicao.x, 0, 0);
    }


    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width / Util.ESCALA, height / Util.ESCALA);
        camera.update();
        redimensionaChao();

        cameraInfo mais coisa
        cameraInfo  mais coisa
    }

    /**
     * configura o tamanho do chão com o tamanho da tela.
     */
    private void redimensionaChao() {
        chao.getFixtureList().clear();
        float largura = camera.viewportWidth / Util.PIXEL_METRO;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(largura / 2, Util.ALTURA_CHAO / 2);
        Fixture forma = Util.criarForma(chao, shape, "CHAO");
        shape.dispose();
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
        pauloInformacoes.dispose();
        fontPontuacao.dispose();
    }
}
