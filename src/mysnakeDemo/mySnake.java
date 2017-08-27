package mysnakeDemo;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class mySnake {
	public static void main(String[] args){
		JFrame jf = new JFrame();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		jf.setVisible(true);
		jf.setBounds((dim.width-800)/2, (dim.height-600)/2, 814, 635);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setTitle("MySnake");
		SnakePanel sp = new SnakePanel();
		jf.add(sp);
		//…Ë÷√πÿø®µÿÕº
		boolean[][] map = new boolean[40][30];
			for(int i = 0;i<map.length;i++)
				for(int j = 0;j<map[i].length;j++)
					if(i <= 0 ||i == map.length-1 || j==0 || j == map[i].length-1)
						map[i][j] = false;
					else
						map[i][j] = true;
		jf.addKeyListener(new KeyAdapter() {
	           @Override
	           public void keyReleased(KeyEvent e) {
	               int keyCode = e.getKeyCode();
	               switch (keyCode) {
	               case KeyEvent.VK_LEFT:                    
	                   sp.setDirection(-1);
	                   break;
	               case KeyEvent.VK_RIGHT:
	                   sp.setDirection(1);
	                   break;
	               case KeyEvent.VK_UP:
	                   sp.setDirection(2);
	                   break;
	               case KeyEvent.VK_DOWN:
	                   sp.setDirection(-2);
	                   break;
	               case KeyEvent.VK_SPACE:
	            	   sp.setisStarted();
//	            	   sp.createFood();
//	            	   sp.repaint();
	            	   break;
	               default:
	                   break;
	               }
	       	}
	       });
	
	}
	}
