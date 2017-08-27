package mysnakeDemo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakePanel extends JPanel implements ActionListener{
	//构造函数
	public SnakePanel(){
		setFocusable(true);
		this.initmap();
		this.initsnake();
		this.createFood();
		time.start();
		
	}
	
	private static final long serialVersionUID = 1L;
	public static final int X_Width = 40;
	public static final int Y_Height = 30;
	public static final int cell_width = 20;  //当前单元格的宽
	public static final int cell_height = 20;  //当前单元格的高
	public static final int RIGHT = 1; 
	public static final int LEFT = -1;
	public static final int UP = 2;
	public static final int DOWN = -2;
	int currentDirection = RIGHT;	
	boolean[][] map = new boolean[Y_Height][X_Width];
	boolean isStarted = false;
	public static int score = 0;
	public int difficulty = 1;
	Point food;
	LinkedList<Point> snake = new LinkedList<Point>();
	Timer time=new Timer(200, this);
	
	//初始化map
	public void initmap(){
		for(int i = 0;i<map.length;i++)
			for(int j = 0;j<map[i].length;j++)
				if(i == 0 ||i == map.length-1 || j==0 || j == map[i].length-1)
					map[i][j] = true;
				else
					map[i][j] = false;
	}
	//初始化蛇
	public void initsnake(){
		snake.removeAll(snake);
		Random random = new Random();
		int x = random.nextInt(X_Width-2);
        int y = random.nextInt(Y_Height);
        if(!map[y][x])
        for (int i = 0; i < 3; i++) {
            snake.add(new Point(x-i,y));
        }        
	}
	//判断开始和结束
	public void setisStarted() {
		isStarted = !isStarted;
		repaint();
	}
	//蛇移动
	public void move(){
		Point snakeHead = snake.getFirst();
		if(isStarted){
			switch(currentDirection){
			case LEFT:
	            snake.addFirst(new Point(snakeHead.x-1,snakeHead.y));
	            break;
	        case RIGHT:
	            snake.addFirst(new Point(snakeHead.x+1,snakeHead.y));   
	            break;
	        case UP:
	            snake.addFirst(new Point(snakeHead.x,snakeHead.y-1));        
	            break;
	        case DOWN:
	            snake.addFirst(new Point(snakeHead.x,snakeHead.y+1));        
	            break;
			default:
				break;
			}
			if(isAte())
				createFood();
			else
				snake.removeLast();
			repaint();
		}
	}
	//生成食物
	 public void createFood(){
	        Random random = new Random();
	        while (true) {
	            int x = random.nextInt(X_Width);
	            int y = random.nextInt(Y_Height);
	            if (!map[y][x]) {
	                food = new Point(x, y);
	                break;
	            }
	        }
	    }
	//判断是否吃到了事物
	public boolean isAte(){
	        Point snakeHead = snake.getFirst();
	        boolean isEat = false;
	        //如果吃到了食物
	        if(snakeHead.x == food.x&& snakeHead.y == food.y){  
	        	score++;
	        	repaint();
	        	isEat = true;
	        }
	        return isEat;
	    }
	//方向
	public void setDirection(int newDirection){
		if(currentDirection + newDirection != 0 && isStarted)
			currentDirection = newDirection;
	}
	
	public void paint(Graphics g){
		//画地图
		for(int i = 0;i<map.length;i++)
			for(int j = 0;j<map[i].length;j++){
				if(map[i][j])
					g.setColor(Color.DARK_GRAY);
				else
					g.setColor(Color.WHITE);
				g.fill3DRect(j*cell_width, i*cell_height, cell_width, cell_height, true);
			}
		//画蛇头
		Point snakehead = snake.getFirst();
		g.setColor(Color.RED);
		g.fill3DRect(snakehead.x*cell_width, snakehead.y*cell_height, cell_width, cell_height, true);
		//画蛇身
		g.setColor(Color.orange);
		for(int i = 1;i< snake.size();i++){
			Point snakebody = snake.get(i);
			g.fill3DRect(snakebody.x*cell_width, snakebody.y*cell_height, cell_width, cell_height, true);
		}
		//画暂停开始
		if(!isStarted){
       	 g.setColor(Color.BLACK);
       	 g.setFont(new Font("arial",Font.BOLD,30));
       	 g.drawString("Press Space to Start/Pause", 210, 300);
        }
		//画食物
		g.setColor(Color.PINK);
        g.fill3DRect(food.x*cell_width, food.y*cell_height, cell_width, cell_height, true);
        //画分数
        g.setFont(new Font("arial",Font.BOLD,20));
        g.setColor(Color.BLUE);
        g.drawString("Score:"+score, 675, 50);
        //画难度
        g.setFont(new Font("arial",Font.BOLD,20));
        g.setColor(Color.BLUE);
        g.drawString("Level:"+difficulty, 675, 75);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		time.start();
		if(areudied())
			move();
	}
	private boolean areudied() {
		Point snakeHead = snake.getFirst();
       	if(map[snakeHead.y][snakeHead.x] || snake.lastIndexOf(snakeHead) != 0 ){
       		String message = "GameOver!";
            JOptionPane.showMessageDialog(this, message);
            initsnake();
            setisStarted();
            createFood();
            currentDirection = 1;
            score = 0;
            difficulty = 1;
            repaint();
            return false;
       	}
       	else
       		return true;
	}
	
}
