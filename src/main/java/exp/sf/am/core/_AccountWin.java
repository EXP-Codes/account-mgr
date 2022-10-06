package exp.sf.am.core;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import exp.libs.ui.BeautyEyeUtils;
import exp.libs.ui.SwingUtils;
import exp.libs.ui.cpt.win.PopChildWindow;
import exp.libs.ui.layout.VFlowLayout;
import exp.libs.utils.str.StrUtils;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI.NormalColor;

import exp.libs.utils.os.OSUtils;
import exp.sf.am.bean.TAccount;

/**
 * <PRE>
 * 单个帐密展示界面.
 * </PRE>
 * <br/><B>PROJECT : </B> account-mgr
 * <br/><B>SUPPORT : </B> <a href="http://www.exp-blog.com" target="_blank">www.exp-blog.com</a> 
 * @version   2017-07-11
 * @author    EXP: 272629724@qq.com
 * @since     jdk版本：jdk1.6
 */
class _AccountWin extends PopChildWindow {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 8372296143631208035L;

	protected final static Integer TYPE_ADD = 1;
	
	protected final static Integer TYPE_EDIT = 2;
	
	protected final static Integer TYPE_DETAIL = 3;
	
	private Integer type;
	
	private JTextField appName;

    private JTextField url;

    private JTextField loginUsername;

    private JTextField loginPassword;

    private JTextField queryPassword;

    private JTextField atmPassword;

    private JTextField payPassword;

    private JTextField servicePassword;

    private JTextField email;

    private JTextField phone;

    private JTextField idcardNum;

    private JTextField idcardName;

    private JTextField question1;

    private JTextField answer1;

    private JTextField question2;

    private JTextField answer2;

    private JTextField question3;

    private JTextField answer3;

    private JTextArea remark;
	
	private JButton okBtn;
	
	private JButton offBtn;
	
	private TAccount account;
	
	private boolean edited;
	
	protected _AccountWin(TAccount account, Integer type) {
		super((type == TYPE_ADD ? "添加帐密" : 
			(type == TYPE_EDIT ? "编辑帐密" : "查看详情")), 
			600, 580, false, account, type);
	}
	
	@Override
	protected void initComponents(Object... args) {
		this.account = (TAccount) args[0];
		this.type = (Integer) args[1];
		initAccountField();
		this.edited = false;
	}
	
	private void initAccountField() {
		boolean edit = (this.type != TYPE_DETAIL);
		this.appName = new JTextField(); { appName.setEditable(edit); }
		this.url = new JTextField(); { url.setEditable(edit); }
		this.loginUsername = new JTextField(); { loginUsername.setEditable(edit); }
		this.loginPassword = new JTextField(); { loginPassword.setEditable(edit); }
		this.queryPassword = new JTextField(); { queryPassword.setEditable(edit); }
		this.atmPassword = new JTextField(); { atmPassword.setEditable(edit); }
		this.payPassword = new JTextField(); { payPassword.setEditable(edit); }
		this.servicePassword = new JTextField(); { servicePassword.setEditable(edit); }
		this.email = new JTextField(); { email.setEditable(edit); }
		this.phone = new JTextField(); { phone.setEditable(edit); }
		this.idcardNum = new JTextField(); { idcardNum.setEditable(edit); }
		this.idcardName = new JTextField(); { idcardName.setEditable(edit); }
		this.question1 = new JTextField(); { question1.setEditable(edit); }
		this.answer1 = new JTextField(); { answer1.setEditable(edit); }
		this.question2 = new JTextField(); { question2.setEditable(edit); }
		this.answer2 = new JTextField(); { answer2.setEditable(edit); }
		this.question3 = new JTextField(); { question3.setEditable(edit); }
		this.answer3 = new JTextField(); { answer3.setEditable(edit); }
		this.remark = new JTextArea(4, 8); { remark.setEditable(edit); }
		
		this.okBtn = new JButton(edit ? "保存" : "复制");
		this.offBtn = new JButton("关闭");
		BeautyEyeUtils.setButtonStyle(NormalColor.lightBlue, okBtn, offBtn);
		
		if(this.type == TYPE_DETAIL || this.type == TYPE_EDIT) {
			setValueToUI();
		}
	}

	private boolean getValueFromUI() {
		boolean isOk = StrUtils.isNotTrimEmpty(appName.getText());
		if(isOk == true) {
			account.encodeAppName(appName.getText());
			account.encodeUrl(url.getText());
			account.encodeLoginUsername(loginUsername.getText());
			account.encodeLoginPassword(loginPassword.getText());
			account.encodeQueryPassword(queryPassword.getText());
			account.encodeAtmPassword(atmPassword.getText());
			account.encodePayPassword(payPassword.getText());
			account.encodeServicePassword(servicePassword.getText());
			account.encodeEmail(email.getText());
			account.encodePhone(phone.getText());
			account.encodeIdcardNum(idcardNum.getText());
			account.encodeIdcardName(idcardName.getText());
			account.encodeQuestion1(question1.getText());
			account.encodeAnswer1(answer1.getText());
			account.encodeQuestion2(question2.getText());
			account.encodeAnswer2(answer2.getText());
			account.encodeQuestion3(question3.getText());
			account.encodeAnswer3(answer3.getText());
			account.encodeRemark(remark.getText());
		}
		return isOk;
	}
	
	private void setValueToUI() {
		appName.setText(account.getAppName());
		url.setText(account.getUrl());
		loginUsername.setText(account.getLoginUsername());
		loginPassword.setText(account.getLoginPassword());
		queryPassword.setText(account.getQueryPassword());
		atmPassword.setText(account.getAtmPassword());
		payPassword.setText(account.getPayPassword());
		servicePassword.setText(account.getServicePassword());
		email.setText(account.getEmail());
		phone.setText(account.getPhone());
		idcardNum.setText(account.getIdcardNum());
		idcardName.setText(account.getIdcardName());
		question1.setText(account.getQuestion1());
		answer1.setText(account.getAnswer1());
		question2.setText(account.getQuestion2());
		answer2.setText(account.getAnswer2());
		question3.setText(account.getQuestion3());
		answer3.setText(account.getAnswer3());
		remark.setText(account.getRemark());
	}
	
	@Override
	protected void setComponentsLayout(JPanel rootPanel) {
		rootPanel.add(toEditPanel(), BorderLayout.CENTER);
	}
	
	private JPanel toEditPanel() {
		JPanel panel = new JPanel(new VFlowLayout()); {
			panel.add(new JLabel());
			panel.add(getWEBorderPanel("应用名称", appName));
			panel.add(new JLabel());
			panel.add(getWEBorderPanel("相关网址", url));
			panel.add(new JLabel());
			panel.add(SwingUtils.getHGridPanel(
					getWEBorderPanel("登陆账号", loginUsername), 
					getWEBorderPanel("登陆密码", loginPassword)));
			panel.add(new JLabel());
			panel.add(SwingUtils.getHGridPanel(
					getWEBorderPanel("查询密码", queryPassword), 
					getWEBorderPanel("取款密码", atmPassword)));
			panel.add(new JLabel());
			panel.add(SwingUtils.getHGridPanel(
					getWEBorderPanel("支付密码", payPassword), 
					getWEBorderPanel("服务密码", servicePassword)));
			panel.add(new JLabel());
			panel.add(SwingUtils.getHGridPanel(
					getWEBorderPanel("绑定邮箱", email), 
					getWEBorderPanel("绑定手机", phone)));
			panel.add(new JLabel());
			panel.add(SwingUtils.getHGridPanel(
					getWEBorderPanel("身份证号", idcardNum), 
					getWEBorderPanel("身份证名", idcardName)));
			panel.add(new JLabel());
			panel.add(SwingUtils.getHGridPanel(
					getWEBorderPanel("密码提示问题1", question1), 
					getWEBorderPanel("密码提示答案1", answer1)));
			panel.add(new JLabel());
			panel.add(SwingUtils.getHGridPanel(
					getWEBorderPanel("密码提示问题2", question2), 
					getWEBorderPanel("密码提示答案2", answer2)));
			panel.add(new JLabel());
			panel.add(SwingUtils.getHGridPanel(
					getWEBorderPanel("密码提示问题3", question3), 
					getWEBorderPanel("密码提示答案3", answer3)));
			panel.add(new JLabel());
			panel.add(getWEBorderPanel("备注", SwingUtils.addScroll(remark)));
			panel.add(toBtnPanel());
			panel.add(new JLabel());
		} SwingUtils.addBorder(panel);
		return panel;
	}
	
	private JPanel getWEBorderPanel(String key, Component val) {
		return SwingUtils.getWEBorderPanel(
				new JLabel("  [".concat(key).concat("] :  ")), 
				val, new JLabel("   "));
	}

	private JPanel toBtnPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2)); {
			panel.add(SwingUtils.getEBorderPanel(new JLabel(), 
					SwingUtils.getWBorderPanel(new JLabel("   "), okBtn)), 0);
			panel.add(SwingUtils.getWBorderPanel(new JLabel(), 
					SwingUtils.getEBorderPanel(new JLabel("   "), offBtn)), 1);
		}
		return panel;
	}
	
	@Override
	protected void setComponentsListener(JPanel rootPanel) {
		okBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(type == TYPE_DETAIL) {
					OSUtils.copyToClipboard(account.toInfo());
					SwingUtils.info("复制到剪贴板成功");
					
				} else {
					if(getValueFromUI()) {
						edited = DBMgr.edit(account);
						if(edited == true) {
							SwingUtils.info("保存成功");
							_hide();
							
						} else {
							SwingUtils.info("保存失败");
						}
					} else {
						SwingUtils.info("[应用名称] 不能为空");
					}
				}
			}
		});
		
		offBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				_hide();
			}
		});
	}
	
	protected boolean isEdited() {
		return edited;
	}

	@Override
	protected void AfterView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void beforeHide() {
		// TODO Auto-generated method stub
		
	}

}
