//Name: Itai Steiner

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class MainUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextArea _textArea;
	private JButton _load, _save, _saveTasks;
	public static final int MAX_YEARS = 101, MAX_MONTHS = 13, MAX_DAYS = 32, STARTING_YEAR = 1999;
	private JComboBox<String> _year, _month, _day;
	private String[] _strYear, _strMonth, _strDay;
	private File _file;
	private Hashtable<ToDoDate, String> _hash;

	public MainUI(){
		super("To Do List");
		addLowerPanel();
		addUpperPanel();
		addTextArea();
		setSize(500, 400);
		setVisible(true);
		_hash = new Hashtable<ToDoDate, String>();
	}

	public static void main(String[] args) {
		MainUI frame = new MainUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == _load){
//			loadHashFile();
			_load.setText("BULLS");
		}
		if (e.getSource() == _save){
			try {
				saveHashFile(_hash);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == _saveTasks){
			if(validDate()){
				_hash.put(createDate(), _textArea.getText());;
			}
			else dateError();
		}
		System.out.println(_year.getSelectedItem());
		if(validDate()){
			ToDoDate tempDate = createDate();
			_textArea.setText(_hash.get(tempDate));
		}
	}

	private void addLowerPanel(){
		JPanel lowerPanel = new JPanel();
		lowerPanel.setLayout(new GridLayout(1,4));
		lowerPanel.add(_saveTasks = new JButton("SAVE TASK"));
		lowerPanel.add(_save = new JButton("SAVE FILE"));
		lowerPanel.add(_load = new JButton("LOAD"));
		_save.addActionListener(this);
		_saveTasks.addActionListener(this);
		_load.addActionListener(this);
		add(lowerPanel,BorderLayout.SOUTH);
	}

	private void addUpperPanel(){
		JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new GridLayout(1,3));
		createDatesForCombobox();
		upperPanel.add(_year = new JComboBox<String>(_strYear));
		upperPanel.add(_month = new JComboBox<String>(_strMonth));
		upperPanel.add(_day = new JComboBox<String>(_strDay));
		_year.addActionListener(this);
		_month.addActionListener(this);
		_day.addActionListener(this);
		add(upperPanel, BorderLayout.NORTH);
	}

	private void addTextArea(){
		_textArea = new JTextArea();
		add(_textArea,BorderLayout.CENTER);
	}

	@SuppressWarnings("unchecked")
	private void loadHashFile(){
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(chooser);
			chooser.showOpenDialog(chooser);
		_file = chooser.getSelectedFile();
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream(_file));
			Object loadedObj = ois.readObject();
			_hash = (Hashtable<ToDoDate, String>) loadedObj;
			ois.close();
		} catch (IOException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	private void saveHashFile(Hashtable<ToDoDate, String> h) throws FileNotFoundException, IOException{
		JFileChooser chooser = new JFileChooser();
		chooser.setSelectedFile(new File("tasks.txt"));
		chooser.showSaveDialog(this);
		_file = chooser.getSelectedFile();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(_file));
		oos.writeObject(_hash);
		oos.close();
	}

	private boolean validDate(){ //method just to make the code a little shorter.
		return (!_year.getSelectedItem().equals("Year") && !_month.getSelectedItem().equals("Month") && !_day.getSelectedItem().equals("Day"));
	}

	private ToDoDate createDate(){ //method just to make the code a little shorter.
		return (new ToDoDate((String)_year.getSelectedItem(), (String)_month.getSelectedItem(), (String)_day.getSelectedItem()));
	}

	private void dateError(){ //method just to make the code a little shorter.
		_textArea.setText("\n\n\n\n\n\n\t ERROR, NO VALID DATE HAS BEEN CHOSEN.");
	}

	private void createDatesForCombobox(){ //method just to make the code a little shorter.
		_strYear = new String[MAX_YEARS];
		_strMonth = new String[MAX_MONTHS];
		_strDay = new String[MAX_DAYS];
		_strYear[0] = "Year";
		_strMonth[0] = "Month";
		_strDay[0] = "Day";
		for (int i=1; i<MAX_YEARS; i++)
			_strYear[i] = (""+(i+STARTING_YEAR));
		for (int j=1; j<MAX_MONTHS; j++)
			_strMonth[j] = (""+j);
		for (int k=1; k<MAX_DAYS; k++)
			_strDay[k] = (""+k);	
	}
}