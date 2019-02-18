import javax.swing.text.StyledEditorKit.BoldAction;


public class drawpoint
{
    private int X ;			// X 座標
    private int Y ;			// Y 座標
    private Boolean Connect ;	// 是否為多邊形外邊
    private int CreatPoint ;	// 第幾個創點
    private int Next ;			// 連結下一點

    public Boolean getConnect() {
        return Connect;
    }
    public void setConnect(Boolean connect) {
        Connect = connect;
    }
    public int getCreatPoint() {
        return CreatPoint;
    }
    public void setCreatPoint(int creatPoint) {
        CreatPoint = creatPoint;
    }
    public int getNext() {
        return Next;
    }
    public void setNext(int next) {
        Next = next;
    }
    public drawpoint(int x, int y ,int CP ,int next)
    {
        X=x;
        Y=y;
        CreatPoint = CP;
        Next=next;
    }
    public int getX()
    {
        return X;
    }
    public void setX(int x)
    {
        X = x;
    }
    public int getY()
    {
        return Y;
    }
    public void setY(int y)
    {
        Y = y;
    }

}
