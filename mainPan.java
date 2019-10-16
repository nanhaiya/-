package btnGame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import net.miginfocom.swing.MigLayout;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

@SuppressWarnings("serial")
public class mainPan extends JFrame {

	private JPanel myJrame;
	JPanel mypanel ;
	
	Thread tr=null;
	Thread th=null;
	DoDowm dd;   //游戏线程
	SecendTime st;  //时间线程
	
	myKeyListener mk;
	
	JLabel lab_fenshu;//分数
	JLabel lab_time;//时间
	GridLayout gl;
	int rows=6;  //行
	int cols=4;   //列
	int gameTime=0;//游戏时间
	
	int source=0; //得分
	boolean trueDrop=true;//判断游戏结束 结束为false
	
	Random rd = new Random();
	JLabel jl[][] =new JLabel[rows][cols];  //标签
	int rand; //一行随机黑块数
	int livel=800;//延时毫秒，设置难度
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainPan frame = new mainPan();
					frame.setTitle("莫踩白块");
					frame.setLocationRelativeTo(frame);
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public mainPan() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Exit();
			}
		});
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 617, 633);
		myJrame = new JPanel();
		myJrame.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(myJrame);
		myJrame.setLayout(null);
		
		
		
		mypanel = new JPanel();
		mypanel.setBounds(0, 0, 384, 588);
		mypanel.setBorder(BorderFactory.createLoweredBevelBorder() );
		myJrame.add(mypanel);
		mypanel.setLayout(new MigLayout("", "[]", "[]"));
		
		JLabel lbld = new JLabel("1-\"D\"");
		lbld.setBounds(415, 295, 72, 18);
		myJrame.add(lbld);
		
		JLabel lblf = new JLabel("2-\"F\"");
		lblf.setBounds(415, 326, 72, 18);
		myJrame.add(lblf);
		
		JLabel lblj = new JLabel("3-\"J\"");
		lblj.setBounds(415, 357, 72, 18);
		myJrame.add(lblj);
		
		JLabel lblk = new JLabel("4-\"K\"");
		lblk.setBounds(415, 388, 72, 18);
		myJrame.add(lblk);
		
		JLabel lbl_restart = new JLabel("重新开始-R");
		lbl_restart.setBounds(415, 419, 72, 18);
		myJrame.add(lbl_restart);
		
		JLabel lbl_exit = new JLabel("退出游戏-Q");
		lbl_exit.setBounds(415, 450, 72, 18);
		myJrame.add(lbl_exit);
		
		JLabel label = new JLabel("\u6309\u952E\u8BF4\u660E\uFF1A");
		label.setBounds(415, 264, 108, 18);
		myJrame.add(label);
		
		JLabel lblM = new JLabel("开始游戏-P");
		lblM.setBounds(415, 481, 72, 18);
		myJrame.add(lblM);
		
		
		
		//分数标签
		lab_fenshu = new JLabel("得分：0");
		lab_fenshu.setBounds(415, 43, 72, 18);
		myJrame.add(lab_fenshu);
		
		//时间标签
		lab_time = new JLabel("时间：0s");
		lab_time.setBounds(415, 82, 72, 18);
		myJrame.add(lab_time);
		
		gl=new GridLayout(rows, cols);
		mypanel.setLayout(gl);
		
		mk=new myKeyListener();
		addKeyListener(mk);
		
		NewMap();
		
	}
	


	public void NewMap() {
		//实例化面板
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				jl[i][j]=new JLabel();
				jl[i][j].setOpaque(true);
				jl[i][j].setBackground(Color.WHITE);
				jl[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));
				mypanel.add(jl[i][j]);
			}
		}
	}
	

	//按键事件
	public class  myKeyListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			System.out.println(e.getKeyChar());
			int heikuai[]=heikuai();
			//第一键
			if(e.getKeyCode()==KeyEvent.VK_D) {
				System.out.println(heikuai[0]+""+heikuai[1]);
				if(heikuai[1]==0) {
					source+=2;
					lab_fenshu.setText("得分:"+source);
					jl[heikuai[0]][heikuai[1]].setBackground(Color.WHITE);
				}else {
					trueDrop=false;
					Fail_option();
				}
			}
			//第二键
			if(e.getKeyCode()==KeyEvent.VK_F) {
				if(heikuai[1]==1) {
					source+=2;
					lab_fenshu.setText("得分:"+source);
					jl[heikuai[0]][heikuai[1]].setBackground(Color.WHITE);
				}else {
					trueDrop=false;
					Fail_option();
				}
			}
			//第三键
			if(e.getKeyCode()==KeyEvent.VK_J) {
				if(heikuai[1]==2) {
					source+=2;
					lab_fenshu.setText("得分:"+source);
					jl[heikuai[0]][heikuai[1]].setBackground(Color.WHITE);
				}else {
					trueDrop=false;
					Fail_option();
				}
			}
			//第四键
			if(e.getKeyCode()==KeyEvent.VK_K) {
				if(heikuai[1]==3) {
					source+=2;
					lab_fenshu.setText("得分:"+source);
					jl[heikuai[0]][heikuai[1]].setBackground(Color.WHITE);
				}else {
					trueDrop=false;
					Fail_option();
				}
			}
			if(e.getKeyCode()==KeyEvent.VK_Q) {
				
				Exit();
			}
			if(e.getKeyCode()==KeyEvent.VK_R) {
				Restart();
			}
			if(e.getKeyCode()==KeyEvent.VK_P) {
				trueDrop=true;
				//线程实现游戏效果
				dd=new DoDowm();
			
				//线程计时
				st=new SecendTime();

		        if(tr==null||tr.isAlive()==false) {
		        	tr=new Thread(dd);
		        	tr.start();
		        }
		        if(th==null||th.isAlive()==false) {
		        	th=new Thread(st);
		        	th.start();
		        }
				
				
			}
		}
		
	}

	
	//绘图、刷新面板功能
	public void DrawMap() {
		System.out.println("刷新面板");
		for(int i=rows-1;i>=0;i--) {
			for(int j=0;j<cols;j++) {
				//除最后一行整体下移
				if(i<=rows-2) {
				if(jl[i][j].getBackground()==Color.BLACK) {
					jl[i+1][j].setBackground(Color.black);
					jl[i][j].setBackground(Color.WHITE);

				}}
				//删除最后一行白块
				if(i==rows-1) {
					if(jl[i][j].getBackground()==Color.BLACK) {
						jl[i][j].setBackground(Color.WHITE);
					}
				}
			}
		}
		//第一行随机生成黑块
		rand=rd.nextInt(4);
		jl[0][rand].setBackground(Color.black);
	}
	
	
	//线程实现下落,主游戏加判断
	public class DoDowm implements Runnable{

		@Override
		public void run() {
			int drop[];

			while(trueDrop) {
				 drop= heikuai();
				 
				 try {
					   Thread.sleep(livel);      //延时，设置难度
					   DrawMap();
					} catch (InterruptedException e) {
						System.out.println("延迟错误");
						e.printStackTrace();
					}
				 //判断是否方块到了最后一行，是则结束游戏
				 if(drop[0]==(rows-1)) {
					 trueDrop=false; 
					 Fail_option();
				 }
			 }
			
		}
		
	}
	
	
	//时间线程	
	//int gap=1;
	public class SecendTime implements Runnable{

		@Override
		public void run() {
			while(trueDrop) {
			 try {
					Thread.sleep(1000);      
					lab_time.setText("时间："+(gameTime++)+"s");
					
					if(gameTime%5==0) {
						livel-=30;           //每五秒加快下落30毫秒，可自行设置
					}
					//gap++;
					
				} catch (InterruptedException e) {
					System.out.println("延迟错误");
					e.printStackTrace();
				}
			}	
		}	
	}
	
	
	//失败
	public void Fail_option() {
		int res=JOptionPane.showConfirmDialog(null, "你得了"+source+"分！\n用时："+gameTime+"s\n是否重新开始？", "游戏结束！", JOptionPane.YES_NO_OPTION); 
		if(res==JOptionPane.YES_OPTION) {Restart();}
		else {newPan() ;}
	}
	
	//重新开始
	public void Restart() {
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				source=0;
				jl[i][j].setBackground(Color.WHITE);
			}
		}
		livel=800;
		gameTime=0;
		lab_fenshu.setText("得分:0");
		lab_time.setText("时间：0s");
		trueDrop=true;
        System.out.println(tr.isAlive());
        if(tr.isAlive()==false) {
        	tr=new Thread(dd);
        	tr.start();
        }
        if(th.isAlive()==false) {
        	th=new Thread(st);
        	th.start();
        }
	}
	
	//退出
	public void Exit() {
		trueDrop=false;
		int res=JOptionPane.showConfirmDialog(null, "确定要退出？", "是否继续", JOptionPane.YES_NO_OPTION); 
		if(res==JOptionPane.YES_OPTION) {System.exit(0);}
		else{
//			newPan() ;
			trueDrop=true;
			 if(tr.isAlive()==false) {
		        	tr=new Thread(dd);
		        	tr.start();
		        }
		        if(th.isAlive()==false) {
		        	th=new Thread(st);
		        	th.start();
		        }
		}
		
	}
	
	//刷新面板
	public void newPan() {
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				source=0;
				jl[i][j].setBackground(Color.WHITE);
			}
		}
		livel=800;
		gameTime=0;
		lab_fenshu.setText("得分:0");
		lab_time.setText("时间：0s");
	}
	
	
	//返回最下黑块的编码 横heikuai[0]纵heikuai[1]坐标
	public int[] heikuai(){
		int heikuai[]=new int[2];
		for(int i=rows-1;i>=0;i--) {
			   if(jl[i][0].getBackground()==Color.BLACK) {
				   heikuai[0]=i;heikuai[1]=0;
				   break;
			   }if(jl[i][1].getBackground()==Color.BLACK) {
				   heikuai[0]=i;heikuai[1]=1;
				   break;
			   }if(jl[i][2].getBackground()==Color.BLACK) {
				   heikuai[0]=i;heikuai[1]=2;
				   break;
			   }if(jl[i][3].getBackground()==Color.BLACK) {
				   heikuai[0]=i;heikuai[1]=3;
				   break;
			   }
		   }	
		return heikuai;
	}
}
