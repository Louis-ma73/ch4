public class FormatDate {
	
	public static void printAmerican(String s, String day, String month, int date, int year){
		System.out.println(s+ day+ ", "+ month + ", " + date +", "+ year);
	}
	
	public static void printEuropean(String s, String day, int date, String month, int year){
		System.out.println(s+ day+ " "+ date+ " " + month+" " + year);
	}
	
	public static void main(String[] args){
	int year = 2026 ;
	String month = "September" ;
	int date = 14 ;
	String day = "Monday" ;
	printAmerican("American Format: ", day, month, date, year) ;
	printEuropean("European Format: ", day, date, month, year) ;

	}
}
