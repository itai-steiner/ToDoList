//Name: Itai Steiner
import java.io.Serializable;

public class ToDoDate implements Serializable{

	private static final long serialVersionUID = 1L;
	private int _year, _month, _day;
	public static final int MIN_YEAR = 2000;

	public ToDoDate(int year, int month, int day)
	{
		if (year < MIN_YEAR)
			_year = MIN_YEAR;
		else _year = year;

		if (month < 1 || month > 12)
			_month = 1;
		else _month = month;

		if (day == 31 && (month == 2 || month == 4 || month == 6 || month == 9 || month == 12)){
			_day = 30;
			if (month == 2 && day > 28){
				if(year%4 == 0 && year%100!=0 || year%400==0 && year%100!=0) //Leap year.
					_day = 29;
				else _day = 28;
			}
		}
		else _day = day;
	}

	public ToDoDate(String year, String month, String day){
		this(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
	}

	public long getYear(){
		return _year;
	}

	public int getMonth(){
		return _month;
	}

	public int getDay(){
		return _day;
	}

	public String toString(){
		return ""+this.getYear()+" "+this.getMonth()+" "+this.getDay();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _day;
		result = prime * result + _month;
		result = prime * result + _year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToDoDate other = (ToDoDate) obj;
		if (_day != other._day)
			return false;
		if (_month != other._month)
			return false;
		if (_year != other._year)
			return false;
		return true;
	}

}