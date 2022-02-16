
package first;

import java.awt.event.KeyEvent;
import processing.core.PApplet;

public class Snake extends PApplet {
    Array list;
    boolean right = true;
    boolean left = false;
    boolean up = false;
    boolean down = false;
    
    public abstract class Element {
        public abstract int getX();
        public abstract int getX1();
        public abstract int getY();
        public abstract int getY1();
        public abstract void erase();
        public abstract void paint();
    }

    class Head extends Element{
        private int x,x1;
        private int y,y1;

        public Head(int x,int y){
            this.x=x;
            this.y=y;
        }

        public int getX1() {
            return x1;
        }

        public int getY1() {
            return y1;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public void erase(){
            fill(0,0,0);
            ellipse(x,y,15,15);
        }

        @Override
        public void paint(){
            fill(255,0,0);
            
            x1=x; y1=y;
            if(right){
                x=x+15;
                ellipse(x,y,15,15);
            }
            if(left){
                x=x-15;
                ellipse(x,y,15,15);
            }
            if(up){
                y=y-15;
                ellipse(x,y,15,15);
            }
            if(down){
                y=y+15;
                ellipse(x,y,15,15);
            }
        }
    }

    class Node extends Element{
        private int x,x1;
        private int y,y1;
        private final Element parrent;

        public Node(Element e){
            this.parrent=e;
            x=parrent.getX()-30;
            y=parrent.getY();
//            fill(255,0,0);
//            ellipse(x,y,15,15);
        }

        @Override
        public int getX() {
            return x;
        }

        @Override
        public int getY() {
            return y;
        }

        @Override
        public int getX1() {
            return x1;
        }

        @Override
        public int getY1() {
            return y1;
        }

          @Override
        public void erase(){
            fill(0,0,0);
            ellipse(x,y,15,15);
        }

        @Override
        public void paint(){
            fill(255,0,0);
            x1=x; y1=y;
            x=parrent.getX1();
            y=parrent.getY1();
        }
    }
    
    public class Array {
    Head h;
    int count;
    Element e;
    Element[] list;
    
    public Array(int x, int y, int quantity){
        h=new Head(x,y);
        list = new Element[quantity];
    }
    
    public Element get(int i){
        return list[i];
    }
    public void add(){
        switch (count) {
            case 0:
                list[0]=h;
                count++;
                break;
            case 1:
                e = new Node(h);
                list[count]=e;
                count++;
                break;
            default:
                e = new Node(e);
                list[count]=e;
                count++;
                break;
        }
    }
    
    public int size(){
        return count;
    }
}
    
    @Override
    public void setup(){
        size(800,600);
        background(0,0,0);
        list = new Array(this.width/2,this.height/2,100);
        for (int i = 0; i < 15; i++) {
            list.add();  
        }
    }
    
    @Override
    public void draw(){
            for (int i=0; i<list.size();i++) {
                try {
                    Element e1 = list.get(i);
                    
                    if(i==list.size()-1) e1.erase();
                    Thread.sleep(10);
                    e1.paint();
                } catch (InterruptedException ex) {/*NOP*/}
            }
    }
    
    public static void main(String[] args) {
        PApplet.main("first.Snake");
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        char key = e.getKeyChar();
        if(key=='d'&& left==false){
            right=true;up=false;down=false;System.out.println("right");
        }
        if(key=='a'&& right==false){
            left=true;up=false;down=false;System.out.println("left");
        }
        if(key=='w'&& down==false){
            up=true;right=false;left=false;System.out.println("up");
        }
        if(key=='s'&& up==false){
            down=true;right=false;left=false;System.out.println("down");
        }
    }
    
}

