package controlador;


import modelo.Carro;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

/**
 * @author Estudiante
 */

public class LoopJuego extends AnimationTimer{
    private GraphicsContext gc; //Vista
    private Carro carro; //Modelo
    private Image fondo;
    private Image gato;
    private Image hombre;
    private int numero;

    public LoopJuego(GraphicsContext gc) {
        this.gc = gc;
        this.carro = new Carro(0, 200);
        this.fondo = new Image ("images/fondo.png");
        this.gato = new Image ("images/cats.gif");
        this.hombre = new Image ("images/down0.png");
    }
       
    @Override
    public void handle(long l) {
      //Borrando el tablero
      gc.clearRect(0, 0, 1024,512); 
      
      //Dibujando el fondo
      gc.drawImage(fondo, 0, 0);
      
      //Gato
      gc.drawImage(gato, 0, 20, 132*this.numero, 80, 20, 20, 132*this.numero, 80);
      
      //Hombre
      gc.drawImage(hombre, 0, 410);
      
      //Dibujando el escenario  
      gc.fillRect(this.carro.getX(),this.carro.getY(), 50, 50);
      gc.fillOval(200,this.carro.getY(), 50, 50);
      
      //Colisiones     
      Shape sRectangulo = new Rectangle (this.carro.getX(),this.carro.getY(), 50, 50);
      
      Shape sOvalo = new Rectangle (200,this.carro.getY(), 50, 50);
      
      Shape interseccion = SVGPath.intersect(sRectangulo, sOvalo);
      
      if(interseccion.getBoundsInLocal().getWidth() != -1){
          stop();
      }
      
      gc.strokeRect(200,this.carro.getY(), 50, 50);
      
      this.carro.mover();
      
      if(this.numero%5==0){
          if(this.numero==5){
              this.numero=0;
          }else{
              this.numero++;
          }
      }
    }
    
}
