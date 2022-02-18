package dynamic_beat_6;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame{
	
	private Image screenImage;
	private Graphics screenGraphic;
	//더블버퍼링을 위해 전체 화면에 대한 이미지를 담음
	
	//introBackground->background
	private Image background=new ImageIcon(Main.class.
			getResource("../images/introBackground(Title).jpg")).getImage(); 
	//메뉴바
	private JLabel menuBar=new JLabel(new ImageIcon(Main.class.
			getResource("../images/menuBar.png")));
	
	//종료 버튼 이미지
	private ImageIcon exitButtonBasicImage=new ImageIcon(Main.class.
			getResource("../images/exitButtonBasic.png"));
	private ImageIcon exitButtonEnteredImage=new ImageIcon(Main.class.
			getResource("../images/exitButtonEntered.png"));
	
	//시작 버튼 이미지
	private ImageIcon startButtonEnteredImage=new ImageIcon(Main.class.
			getResource("../images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage=new ImageIcon(Main.class.
			getResource("../images/startButtonBasic.png"));
	
	//그만하기 버튼 이미지
	private ImageIcon quitButtonEnteredImage=new ImageIcon(Main.class.
			getResource("../images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage=new ImageIcon(Main.class.
			getResource("../images/quitButtonBasic.png"));
	
	//곡 시작 이미지
	private Image selectedImage=new ImageIcon(Main.class.
			getResource("../images/Mighty Love Start Image.png")).getImage();
	
	//곡 타이틀 이미지
	private Image titleImage=new ImageIcon(Main.class.
			getResource("../images/Mighty Love Title Image.png")).getImage();
	
	//왼쪽 버튼
	private ImageIcon leftButtonEnteredImage=new ImageIcon(Main.class.
			getResource("../images/leftButtonBasicBasic.png"));
	private ImageIcon leftButtonBasicImage=new ImageIcon(Main.class.
			getResource("../images/leftButtonBasic.png"));
	
	//오른쪽 버튼
	private ImageIcon rightButtonEnteredImage=new ImageIcon(Main.class.
			getResource("../images/rightButtonEntered.png"));
	private ImageIcon rightButtonBasicImage=new ImageIcon(Main.class.
			getResource("../images/rightButtonBasic.png"));
	
	
	private int mouseX, mouseY; //프로그램 안에서 마우스 안의 좌표
	
	private boolean isMainScreen=false; //시작 화면으로 시작, 메인으로 바뀜

	//버튼
	private JButton exitButton=new JButton(exitButtonBasicImage);
	private JButton startButton=new JButton(startButtonBasicImage);
	private JButton quitButton=new JButton(quitButtonBasicImage);
	private JButton leftButton=new JButton(leftButtonBasicImage);
	private JButton rightButton=new JButton(rightButtonBasicImage);

	
	public DynamicBeat() { 
		setUndecorated(true); //실행했을 때 메뉴바가 보이지 않음
		
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); //게임 창 사이즈는 고정
		setLocationRelativeTo(null); //실행 시 게임창이 컴퓨터 정중앙
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //종료 시 프로그램 전체 종료
		setVisible(true); //정상 출력되도록-기본값 false
		
		setBackground(new Color(0, 0, 0, 0)); //white
		setLayout(null);
		
		
		//종료 버튼
		exitButton.setBounds(1245, 0, 30, 30); //메뉴바의 오른쪽
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage); //마우스가 올라가면 이미지를 바꿈
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic=new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage); //마우스가 내려감
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic=new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				//지연 시간으로 소리가 나오도록 함
				try {
					Thread.sleep(1000);
				}
				catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0); //누르면 종료
			}
		});
		add(exitButton);
		//메뉴바 위에 오도록 순서 조정
		
		
		//시작 버튼
		startButton.setBounds(40, 200, 400, 100); //버튼마다 위치를 다르게
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage); //마우스가 올라가면 이미지를 바꿈
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic=new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage); //마우스가 내려감
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic=new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				//다른 버튼과의 기능 차이
				//게임 시작 이벤트
				startButton.setVisible(false);
				quitButton.setVisible(false);
				leftButton.setVisible(true);
				rightButton.setVisible(true);
				background=new ImageIcon(Main.class.
						getResource("../images/mainBackground.jpg")).getImage(); 
				isMainScreen=true; //메인 스크린으로 전환
			}
		});
		add(startButton);
		
		
		//그만하기 버튼
		quitButton.setBounds(40, 330, 400, 100); //start와 y 좌표만 다르게 크기는 같음
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage); //마우스가 올라가면 이미지를 바꿈
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic=new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage); //마우스가 내려감
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic=new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				//exit와 같은 기능
				try {
					Thread.sleep(1000);
				}
				catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0); //누르면 종료
			}
		});
		add(quitButton);
		

		//왼쪽 버튼
		leftButton.setVisible(false); //처음에는 보이지 않음
		leftButton.setBounds(140, 310, 60, 60); //start와 y 좌표만 다르게 크기는 같음
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage); //마우스가 올라가면 이미지를 바꿈
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic=new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage); //마우스가 내려감
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic=new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				//왼쪽 버튼 이벤트
				
			}
		});
		add(leftButton);
		
		
		//오른쪽 버튼
		rightButton.setVisible(false); //처음에는 보이지 않음
		rightButton.setBounds(1080, 330, 400, 100); //start와 y 좌표만 다르게 크기는 같음
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage); //마우스가 올라가면 이미지를 바꿈
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic=new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage); //마우스가 내려감
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic=new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				//오른쪽 버튼 이벤트
			}
		});
		add(rightButton);
		
		
		
		//메뉴바
		menuBar.setBounds(0, 0, 1280, 30); //위치, 크기 정하기
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX=e.getX();
				mouseY=e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x=e.getXOnScreen();
				int y=e.getYOnScreen();
				setLocation(x-mouseX, y-mouseY); //게임창의 위치 바꾸기
			}
		});
		add(menuBar); //JFrame에 메뉴바 추가
		
		//음악
		Music introMusic=new Music("IntroMusic.mp3", true); //시작 화면에서 음악 무한반복
		introMusic.start();
	}
	
	
	//그리기
	public void paint(Graphics g) {
		screenImage=createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); //이미지 생성
		screenGraphic=screenImage.getGraphics();
		screenDraw(screenGraphic); //그림을 그림
		g.drawImage(screenImage, 0, 0, null); //스크린 이미지가 그려짐
	}
	
	
	//스크린 그리기
	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null); //단순한 이미지 출력
		if (isMainScreen) {
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		paintComponents(g); //메인 프레임에 추가된 요소를 보여줌-add()
		this.repaint();
	}
}


/*곡 하나에 두 가지 이미지-곡을 소개하는 메인 이미지, 시작 이미지
 * 6가지 이미지를 준비
 * start, game, title
 * 
 * 역동적인 이미지는 주로 drawImage()를 사용
 * 
 * 시작하기 버튼을 누른 후 메인 스크린으로 넘어오면 selectedImage, titleImage, 왼쪽/오른쪽 버튼 가시화
 */
 