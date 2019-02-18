import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.lang.Math;

public class Polygon extends JFrame implements ActionListener
{
    Dimension FrameSize;
    JLabel Text;
    JTextField inputTxt;
    JButton Btn;
    JPanel jp;
    Graphics g;
    drawpoint[] DrawPoint = new drawpoint[10000];
    int point;

    public Polygon()
    {
        Text = new JLabel("點數：");
        inputTxt = new JTextField(10);
        Btn = new JButton("輸入");
        jp = new JPanel();
        jp.add(Text);
        jp.add(inputTxt);
        jp.add(Btn);
        add(jp,BorderLayout.NORTH);
        Btn.addActionListener(this);

        inputTxt.addKeyListener(new KeyListener()
        {
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode()==10)
                {
                    BtnAction();
                }
            }
            public void keyTyped(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });

        setSize(800,800);
        setResizable(false);
        FrameSize = getSize();
        setLocation(10,10); //在螢幕左上顯示。
        setTitle("多邊形");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //關閉鈕
        setVisible(true); //顯示Frame

        g = getGraphics();
        g.setClip(40,40, 800, 800);
        g.setColor(Color.BLACK);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==Btn)
        {
            BtnAction();
        }
    }

    public void BtnAction()
    {
        update(g); //消除圖形
        int point = Integer.parseInt(inputTxt.getText());
        System.out.println(""+point);
        for(int i=0;i<point;i++)
        {
            Random ran = new Random();
            int x = ran.nextInt(700)+60;
            int y = ran.nextInt(700)+60 ;
            DrawPoint[i] = new drawpoint(x,y,i,i);
            System.out.println("X:"+ DrawPoint[i].getX() +" ,Y:"+ DrawPoint[i].getY());
        }

        for(int i=0;i < point;i++)	//畫點
        {
            g.drawLine(DrawPoint[i].getX()+1,DrawPoint[i].getY()+1 ,DrawPoint[i].getX()-1,DrawPoint[i].getY()+1);
            g.drawLine(DrawPoint[i].getX()+1,DrawPoint[i].getY()+1 ,DrawPoint[i].getX()-1,DrawPoint[i].getY()-1);
            g.drawLine(DrawPoint[i].getX()+1,DrawPoint[i].getY()+1 ,DrawPoint[i].getX()+1,DrawPoint[i].getY()-1);
            g.drawLine(DrawPoint[i].getX()-1,DrawPoint[i].getY()-1 ,DrawPoint[i].getX()+1,DrawPoint[i].getY()-1);
            g.drawLine(DrawPoint[i].getX()-1,DrawPoint[i].getY()-1 ,DrawPoint[i].getX()-1,DrawPoint[i].getY()+1);
            g.drawLine(DrawPoint[i].getX()+1,DrawPoint[i].getY()-1 ,DrawPoint[i].getX()-1,DrawPoint[i].getY()+1);
            g.drawLine(DrawPoint[i].getX()+1,DrawPoint[i].getY() ,DrawPoint[i].getX()-1,DrawPoint[i].getY());
            g.drawLine(DrawPoint[i].getX(),DrawPoint[i].getY()+1 ,DrawPoint[i].getX(),DrawPoint[i].getY()-1);
        }
        /***************   畫圖(1)  找最左點   ********************/
        g.setColor(Color.RED);
        int left_point=0,left_num=DrawPoint[0].getX();	//預設最左點為第一點
        for(int i=1;i < point;i++)
        {
            if(left_num>DrawPoint[i].getX())
            {
                left_point=i;
                left_num=DrawPoint[i].getX();
            }
        }
        System.out.println("最左邊點 : X:"+ DrawPoint[left_point].getX() +" ,Y:"+ DrawPoint[left_point].getY());

        /***************   畫圖(2)  找下一點   ********************/
        int now_point=0,next_point=0,tempX=0,tempY=1,top=0; //暫存下點資訊 , 下點計算向量
        int nowX=0,nowY=0,nextX=0,nextY=0 ;
        double cos=-3 ,cosTemp=-2 ,bottomNum=0;
        now_point=left_point;
        boolean Ok=false;
        while(true)
        {
            for(int i=0;i<point ;i++)
            {

                if(!(i==now_point) )
                {
                    /** 角度計算 **/
                    nowX=DrawPoint[now_point].getX();nowY=DrawPoint[now_point].getY();
                    nextX=DrawPoint[i].getX();nextY=DrawPoint[i].getY();
                    top = tempX * (nextX-nowX) +  tempY * (nextY-nowY);
                    bottomNum = Math.sqrt(tempX*tempX + tempY*tempY);
                    bottomNum = bottomNum *Math.sqrt((nextX-nowX)*(nextX-nowX) + (nextY-nowY)*(nextY-nowY) );
                    cos = (double)top/bottomNum;
                    System.out.println("cos : "+cos);
                }
                if(cosTemp<cos && !(i==now_point))
                {
                    cosTemp=cos;
                    next_point=i;
                    Ok=true;
                }
            }
            if(Ok)
            {
                nextX=DrawPoint[next_point].getX();nextY=DrawPoint[next_point].getY();
                if(next_point==left_point)	//是否回起點
                {
                    g.drawLine(nowX,nowY,nextX,nextY);
                    break;
                }
                DrawPoint[now_point].setNext(next_point);
                g.drawLine(nowX,nowY,nextX,nextY);
                tempX = nextX - nowX;
                tempY = nextY - nowY;
                now_point=next_point;
                Ok=false;
                cos=-3;cosTemp=-2;bottomNum=0;
            }
        }

        /*****   兩點連線 ****
         for(int i=0;i < point-1;i++)
         g.drawLine(DrawPoint[i].getX(),DrawPoint[i].getY(),DrawPoint[i+1].getX(),DrawPoint[i+1].getY());
         *****/
    }
    public static void main(String[] args)
    {
        new Polygon();
    }
}
