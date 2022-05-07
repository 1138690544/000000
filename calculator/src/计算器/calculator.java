package 计算器;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class calculator extends JFrame implements ActionListener{
	/*北面控件*/
	private JPanel jp_north=new JPanel();
	private JTextField input_text=new JTextField();
	private JButton c_Btn=new JButton("c");
	
	/*中间的控件*/
	private JPanel jp_center= new JPanel();
	
	public calculator() throws HeadlessException {
		this.init();
		this.addNorthComponent();
		this.addCenterButton();
	}
	//初始化的方法
	public void init() {
		this.setTitle("计算器");//标题
		this.setSize( 300,300);//窗口大小
		this.setLayout( new BorderLayout());//
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	//添加北面控件
	public void addNorthComponent() {
		this.input_text.setPreferredSize(new Dimension(230,30));//设置输入框大小
		jp_north.add(input_text);//添加输入框
		jp_north.add(c_Btn);//添加按钮c
		
		c_Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {//清空
			    input_text.setText("");	
			}
		});
		this.add(jp_north,BorderLayout.NORTH);
	}
	
	//添加中间的按钮
	public void addCenterButton() {
		String btn_text="123+456-789*0.=/";
		this.jp_center.setLayout(new GridLayout(4,4));//设置4*4的按钮
		for(int i=0;i<16;i++) {
			String temp =btn_text.substring(i,i+1);//截取一个字符
			JButton btn =new JButton();
			btn.setText(temp);
			btn.addActionListener((ActionListener) this);//监听器
			jp_center.add(btn);
		}
		this.add(jp_center,BorderLayout.CENTER);//将按钮居中
	}
	
	public static void main(String[] args) {
		calculator calculator = new calculator();
		calculator.setVisible(true);
	}
	
	private String firstInput=null;
	private String operator= null;
	@Override
	public void actionPerformed(ActionEvent e) {
		String clickStr=e.getActionCommand();
		if(".0123456789".indexOf(clickStr)!=-1) {
			this.input_text.setText(input_text.getText()+clickStr);//将点击的数字输入到text里
		}else if(clickStr.matches("[\\+\\-*/]{1}")) {
			    operator=clickStr;
				firstInput = this.input_text.getText();//获取第一次输入
				this.input_text.setText("");//输入运算符后清空
		}else if(clickStr.equals("=")) {
			Double a= Double.valueOf(firstInput);
			Double b= Double.valueOf(this.input_text.getText());
			Double result=null;
			switch(operator){
				case"+":
					result=a+b;
					break;
				case"-":
					result =a-b;
					break;
				case"*":
					result =a*b;
					break;
				case"/":
					if(b!=0) {
					result =a/b;	
			}
			break;
		}
		    this.input_text.setText(result.toString());
		}
	}
}