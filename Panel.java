import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements MouseListener, Runnable
{
Panel pan;
Thread t;
Kugel tmpKug;
private int x, y, delta;
ArrayList<Kugel> kugels = new ArrayList<>();

	public Panel()
	{		
		delta = 3;	
	setBounds(110, 0, 1050, 640);
	setBackground(Color.LIGHT_GRAY);
	addMouseListener(this);		
	}
	
public static Panel instance;		
	public static Panel getInstance()
	{
		if (instance == null)
		{
			instance = new Panel();
		}		
		return instance;
	}
	
//	class MyThread extends Thread{
//
//		@Override
//		public void run() {
//		
//		while (true) {
//			repaint();
//			try {
//				Thread.sleep(50);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}		
//		}
//	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Thread th = new Thread(new Runnable() {
		@Override
		public void run() {		
		x = e.getX();
		y = e.getY();
		int diametr =  _randomInt (20, 20);
		int dx = delta*_randomIntNotZero (-1, 1);
		int dy = delta*_randomIntNotZero (-1, 1);
		Kugel kgl = new Kugel(x, y, diametr, dx, dy, (new Color(_randomInt(0, 255), _randomInt(0, 255), _randomInt(0 ,255))));
		kugels.add(kgl);			
		}
	
		});
		th.start();
		paint(this.getGraphics());
		
	}
	
	private static int _randomInt (int max, int min)
	{
		 int randomInt =  (int)Math.round(Math.random()*(max-min)+min);
		 return randomInt;
	}
	
	private static int _randomIntNotZero (int max, int min)
	{
		int	randomInt;
		do {
		  randomInt =  (int)Math.round(Math.random()*(max-min)+min);
		} while (randomInt==0);
		 return randomInt;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	
		
	}
	
	
	public  void paint (Graphics g) 
	{		
		synchronized (kugels) {
		super.paint(g);
		Graphics2D  gg = (Graphics2D) g;
		for (Kugel k: kugels)
		{	
			
			if (k.diametr<=3 /*|| k.tm<=0*/) {
				kugels.remove(k);
			}
			else if (k.tm <= 1 /* && k.diametr/2>3*/) {
				if (k.diametr/2>3) {
			tmpKug = k;
			kugels.remove(k);
			Thread th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				createKugels(tmpKug);
				}		
			});
			th.start();
			try {
				th.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		//	if (k.tm)
			else {
			k.draw(gg, k.color );
			k.move();
			}
		}
		}
	}
	private void createKugels(Kugel k) {	
		if (k.diametr/2>3) {
			synchronized (kugels) {
				Thread th = new Thread(new Runnable() {
					public void run() {
		//for (int i = 0; i < 8; i++) {
		//int diametr =  _randomInt (4, 50);
		Kugel kgl = new Kugel(k.x, k.y, k.diametr/2, 0, 5, (new Color(_randomInt(0, 255), _randomInt(0, 255), _randomInt(0 ,255))));
		Kugel kgl2 = new Kugel(k.x, k.y, k.diametr/2, 0, -5, (new Color(_randomInt(0, 255), _randomInt(0, 255), _randomInt(0 ,255))));
		Kugel kgl3 = new Kugel(k.x, k.y, k.diametr/2, 5, 5, (new Color(_randomInt(0, 255), _randomInt(0, 255), _randomInt(0 ,255))));
		Kugel kgl4 = new Kugel(k.x, k.y, k.diametr/2, 5, -5, (new Color(_randomInt(0, 255), _randomInt(0, 255), _randomInt(0 ,255))));
		Kugel kgl5 = new Kugel(k.x, k.y, k.diametr/2, -5, 5, (new Color(_randomInt(0, 255), _randomInt(0, 255), _randomInt(0 ,255))));
		Kugel kgl6 = new Kugel(k.x, k.y, k.diametr/2, -5, 0, (new Color(_randomInt(0, 255), _randomInt(0, 255), _randomInt(0 ,255))));
		Kugel kgl7 = new Kugel(k.x, k.y, k.diametr/2, 5, 0, (new Color(_randomInt(0, 255), _randomInt(0, 255), _randomInt(0 ,255))));
		Kugel kgl8 = new Kugel(k.x, k.y, k.diametr/2, -5, -5, (new Color(_randomInt(0, 255), _randomInt(0, 255), _randomInt(0 ,255))));
		
	
		kugels.add(kgl);
		kugels.add(kgl2);
		kugels.add(kgl3);
		kugels.add(kgl4);
		kugels.add(kgl5);
		kugels.add(kgl6);
		kugels.add(kgl7);
		kugels.add(kgl8);
					}
					});
				th.start();
				try {
					th.sleep(15);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		}
		
	}

	@Override
	public void run() {
		
		while (true) {
			repaint();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
	}

}
