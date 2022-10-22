package exp.sf.am.core;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

import exp.libs.ui.SwingUtils;
import exp.libs.ui.cpt.tbl.NormTable;
import exp.libs.ui.cpt.win.MainWindow;
import exp.libs.utils.os.OSUtils;
import exp.libs.utils.str.StrUtils;
import exp.sf.am.bean.TAccount;
import exp.sf.am.bean.TUser;

/**
 * <PRE>
 * 帐密列表界面.
 * </PRE>
 * <br/><B>PROJECT : </B> account-mgr
 * <br/><B>SUPPORT : </B> <a href="http://www.exp-blog.com" target="_blank">www.exp-blog.com</a> 
 * @version   2017-07-11
 * @author    EXP: 272629724@qq.com
 * @since     jdk版本：jdk1.8
 */
public class _AccountListWin extends MainWindow {

	private static final long serialVersionUID = -3227397290475968153L;

	private final static String[] HEADER = {
		"相关应用", "相关网址", "登陆账号", "登陆密码", "绑定邮箱", "绑定手机", "最后修改时间"
	};
	
	private JTabbedPane tabPanel;
	
	private TUser user;
	
	private AccountTable accTable;
	
	private List<TAccount> accounts;
	
	protected _AccountListWin(TUser user) {
		super("账密管理", 1024, 600, false, user);
	}
	
	@Override
	protected void initComponents(Object... args) {
		this.user = (TUser) args[0];
		this.accTable = new AccountTable();
		this.accounts = new LinkedList<TAccount>();
		updateAccountTable();
	}
	
	@Override
	protected void setComponentsLayout(JPanel rootPanel) {
		rootPanel.add(initTabPanel(), BorderLayout.CENTER);
	}
	
	private Component initTabPanel() {
		JPanel panel = new JPanel(new BorderLayout()); {
			this.tabPanel = new JTabbedPane(); {
				tabPanel.addTab("帐密列表", SwingUtils.addAutoScroll(accTable));
				tabPanel.addTab("搜索帐密", null);
				tabPanel.addTab("添加帐密", null);
			}
			panel.add(tabPanel, BorderLayout.CENTER);
		}
		return SwingUtils.addScroll(panel);
	}

	@Override
	protected void setComponentsListener(JPanel rootPanel) {
		tabPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Rectangle searchBtn = tabPanel.getBoundsAt(1);
				Rectangle addBtn = tabPanel.getBoundsAt(2);
				
				// 搜索帐密
				if(searchBtn.contains(e.getX(), e.getY())) {
					String keyword = SwingUtils.input("请输入搜索关键字:");
					if(StrUtils.isNotTrimEmpty(keyword)) {
						updateAccountTable(keyword);
						SwingUtils.info(StrUtils.concat("找到 [", accounts.size(), "] 条相关记录"));
					}
					
				// 添加帐密
				} else if(addBtn.contains(e.getX(), e.getY())) {
					TAccount account = new TAccount(user.getId());
					editAccount(account, true);
				}
			}
		});
	}
	
	private void editAccount(TAccount account, boolean add) {
		if(account == null) {
			return;
		}
		
		final _AccountWin win = new _AccountWin(account, 
				(add ? _AccountWin.TYPE_ADD : _AccountWin.TYPE_EDIT));
		win.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				if(win.isEdited()) {
					updateAccountTable();
				}
			}
		});
		win._view();
	}
	
	private void updateAccountTable() {
		updateAccountTable(null);
	}
	
	private void updateAccountTable(String keyword) {
		accounts.clear();
		accounts.addAll(DBMgr.queryAccounts(user, keyword));
		accTable.reflash(_toTableDatas(accounts));
	}

	private List<List<String>> _toTableDatas(List<TAccount> accounts){
		List<List<String>> datas = new LinkedList<List<String>>();
		for(TAccount account : accounts) {
			List<String> row = new LinkedList<String>();
			row.add(account.getAppName());
			row.add(account.getUrl());
			row.add(account.getLoginUsername());
			row.add(account.getLoginPassword());
			row.add(account.getEmail());
			row.add(account.getPhone());
			row.add(account.getUpdateTime());
			datas.add(row);
		}
		return datas;
	}
	
	private TAccount getAccount(int rowId) {
		TAccount account = null;
		if(rowId >= 0 && rowId < accounts.size()) {
			account = accounts.get(rowId);
		}
		return account;
	}
	
	
///////////////////////////////////////////////////////////////////////////////
	
	private class AccountTable extends NormTable {

		/** serialVersionUID */
		private static final long serialVersionUID = -2194275100301409161L;
		
		public AccountTable() {
			super(HEADER, 100);
		}

		@Override
		protected void initRightBtnPopMenu(JPopupMenu popMenu) {
			JMenuItem reflash = new JMenuItem("刷新列表");
			JMenuItem detail = new JMenuItem("查看详情");
			JMenuItem copy = new JMenuItem("复制到剪贴板");
			JMenuItem modify = new JMenuItem("修改");
			JMenuItem delete = new JMenuItem("删除");
			popMenu.add(reflash);
			popMenu.add(detail);
			popMenu.add(copy);
			popMenu.add(modify);
			popMenu.add(delete);
			
			
			reflash.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					updateAccountTable();
				}
			});

			detail.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int rowId = getCurRow();
					TAccount account = getAccount(rowId);
					if(account != null) {
						new _AccountWin(account, _AccountWin.TYPE_DETAIL)._view();
					}
				}
			});
			
			copy.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int rowId = getCurRow();
					TAccount account = getAccount(rowId);
					if(account != null) {
						OSUtils.copyToClipboard(account.toInfo());
						SwingUtils.info("复制到剪贴板成功");
					}
				}
			});

			modify.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int rowId = getCurRow();
					TAccount account = getAccount(rowId);
					editAccount(account, false);
				}
			});

			delete.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int rowId = getCurRow();
					TAccount account = getAccount(rowId);
					if(account != null) {
						if(!SwingUtils.confirm("要删除 [".concat(
									account.getAppName()).concat("] 吗?"))) {
							return;
						}
						
						if(DBMgr.delete(account)) {
							updateAccountTable();
							SwingUtils.info("删除 [".concat(
									account.getAppName()).concat("] 成功"));
						} else {
							SwingUtils.info("删除 [".concat(
									account.getAppName()).concat("] 失败"));
						}
					}
				}
			});
		}
		
	}

	@Override
	protected void beforeExit() {
		// TODO Auto-generated method stub
		
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
