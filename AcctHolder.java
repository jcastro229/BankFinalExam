package finalExamJC;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class AcctHolder {
	
	
	public final String fname;
	public final String lname;
	public final String dateOfBirth;
	public final String SSN;
	public String Address;
	public static final String PAT = "MM-dd-yy";
	
	public AcctHolder(String f,String l, String dob, String SSN) {
		super();
		this.fname=f;
		this.lname=l;
		this.dateOfBirth = dob;
		this.SSN = SSN;
		//this.Address = add;
	}
	
	
	public String getName() {
		return fname+" "+lname;
	}
	
	public String getDOB() {
		return dateOfBirth;
	}
	
	public String getAddress() {
		return Address;
	}
	
	public static long getAgeInYears(String d) throws Exception{
		SimpleDateFormat df=new SimpleDateFormat(PAT);
		Date present=new Date();//present
		Date dob=df.parse(d);//Users dob
		
		long dobInMS = dob.getTime();
		long tdInMS = present.getTime();
		long diff = tdInMS - dobInMS;
		long age = (diff/1000/60/60/24/365);
		
		return age;
	}
	@Override
	public String toString() {
		return ""+ fname+" : "+lname + " : " + SSN+" ";
	}
		
}
