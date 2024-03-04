import java.util.Scanner;
import java.sql.*;


class Admin{
	long Cnum;
	int Ccv,pin,vp;
	String Cname;
	
						//*************Update Method*************************// 
	
	void updateinfo()
	{
		int ch;
		Scanner sc=new Scanner(System.in);
		System.out.println("What you want update? \n1.CardName\n2.\nCardnumber\n3.CVV\nSelect One:");
		ch=sc.nextInt();
			if(ch==1) 
			{
						try 
						{
							System.out.println("Enter Update(New) Card Name : ");
							Cname=sc.next();
							System.out.println("Enter Your Card Number to (Update Your Card Name) : ");
							Cnum=sc.nextLong();
							Connection con =DriverManager.getConnection("jdbc:mysql://localhost/atm_machine","root","Asrar3001@");
							PreparedStatement ps =con.prepareStatement("update carddetails set C_Name=? where C_Number=?");
							ps.setString(1, Cname);
							ps.setLong(2,Cnum);
							int r=ps.executeUpdate();
							con.close();
								if(r!=0)
								{
									System.out.println(".....Data Updated.....");
								}
								else 
								{
									System.out.println("Invalid Values");
								}
						}
						catch (Exception e) 
						{
							System.out.println(e);
						}
			}
			else if(ch==2) 
			{
				try 
				{
					System.out.println("Enter Update(New) 16-Digit Card Number : ");
					Cnum=sc.nextLong();
					System.out.println("Enter Your Card CVV Number to (Update Your Card Number) : ");
					Ccv=sc.nextInt();
					Connection con =DriverManager.getConnection("jdbc:mysql://localhost/atm_machine","root","Asrar3001@");
					PreparedStatement ps =con.prepareStatement("update carddetails set C_Number=? where CVV=?");
					ps.setLong(1, Cnum);
					ps.setInt(2,Ccv);
					int r=ps.executeUpdate();
					con.close();
						if(r!=0)
						{
							System.out.println(".....Data Updated.....");
						}
						else 
						{
							System.out.println("Invalid Values");
						}
				}
				catch (Exception e) 
				{
					System.out.println(e);
				}
		}
		else if(ch==3) 
		{
			try
			{
				System.out.println("Enter Update(New) Ccv Number : ");
				 Ccv=sc.nextInt();
				System.out.println("Enter Your Card Number to (Update Your Ccv Number) : ");
				 Cnum=sc.nextLong();
				 
				 Connection con =DriverManager.getConnection("jdbc:mysql://localhost/atm_machine","root","Asrar3001@");
				PreparedStatement ps =con.prepareStatement("update carddetails set CVV=? where C_Number=?");
				ps.setInt(1, Ccv);
				ps.setLong(2,Cnum);
				int r=ps.executeUpdate();
				con.close();
					if(r!=0)
					{
						System.out.println(".....Updated.....");
					}
					else
					{
						System.out.println("Invalid Values");
					}
			}
			catch (Exception e)
				{
					System.out.println(e);
				}
		}
		else 
		{
			System.out.println("Invalid Option Give Crct Option");
		}
	}
	
					//*************Delete Method*************************// 
	
	void delete() {
		long Cnum;
		Scanner sc=new Scanner(System.in);
		System.out.println("Which Card you want Remove\n Enter Card Number : ");
		Cnum=sc.nextLong();
			try {
					Connection con =DriverManager.getConnection("jdbc:mysql://localhost/atm_machine","root","Asrar3001@");
					PreparedStatement ps =con.prepareStatement("delete from carddetails where C_Number=?");
					ps.setLong(1,Cnum);
					int r=ps.executeUpdate();
					con.close();
					System.out.println((r!=0)?"Card Removed Successfully":"Invalid");	
				}
				catch (Exception e) {
						System.out.println(e);
					}

				}
	
					//*************Create Method*************************//
	
	void create() {
		String Cname,Hname;
		long Cnum,phon,bal;
		int Cvv,pin;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Card Name : ");
		Cname =sc.next();
		System.out.println("Enter Card Number :");
		Cnum=sc.nextLong();
		System.out.println("Enter Card Holder Name :");
		Hname=sc.next();
		System.out.println("Enter CVV Number :");
		Cvv=sc.nextInt();
		System.out.println("Enter Phone Number :");
		phon=sc.nextLong();
		System.out.println("Enter Card Pin :");
		pin=sc.nextInt();
		System.out.println("Available Balance :");
		bal=sc.nextInt();
			try {
					Connection con =DriverManager.getConnection("jdbc:mysql://localhost/atm_machine","root","Asrar3001@");
					PreparedStatement ps =con.prepareStatement("insert into carddetails values(?,?,?,?,?,?,?)");
					ps.setString(1,Cname);
					ps.setLong(2,Cnum);
					ps.setString(3,Hname);
					ps.setInt(4,Cvv);
					ps.setLong(5,phon);
					ps.setInt(6,pin);
					ps.setLong(7,bal);
					ps.executeUpdate();
					con.close();
					System.out.println("Card Successfully Added");	
				}
				catch (Exception e) {
						System.out.println(e);
				}

	}
}
public class customer extends Admin{

	//*************User-Entry Method*************************//
	void userinfo() {
			try {
				Scanner sc=new Scanner(System.in);
				System.out.println("Enter Your 16-Digit Card Number : ");
				Cnum=sc.nextLong();
				System.out.println("Enter Your CVV : ");
				Ccv=sc.nextInt();
				 
				Connection con =DriverManager.getConnection("jdbc:mysql://localhost/atm_machine","root","Asrar3001@");
				PreparedStatement ps =con.prepareStatement("select * from carddetails where C_Number=? && CVV=?");
				ps.setLong(1,Cnum);
				ps.setInt(2, Ccv);
				ResultSet rs=ps.executeQuery();
				int count=0;
					while(rs.next()) {
						System.out.println("......Your Card Details.....");
						System.out.println("Your Card Name :"+rs.getString(1));
						System.out.println("Your Card Number :"+rs.getLong(2));
						System.out.println("Card Holder Name :"+rs.getString(3));
						System.out.println("Phone Number :"+rs.getLong(5));
						count++;
						}
							if(count==0) {
									System.out.println("Please Give Valid Card Number & Cvv");
								}
							con.close();
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
	
	//*************User-Verify Method*************************//
	
		void verify() {
			Scanner sc=new Scanner(System.in);
			try {
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost/atm_machine","root","Asrar3001@");
			PreparedStatement ps =con.prepareStatement("select * from carddetails where C_Number=? && CVV=?");
			ps.setLong(1,Cnum);
			ps.setInt(2, Ccv);
			ResultSet rs=ps.executeQuery();
			int count2=0;
			while(rs.next()) {
				System.out.println("Card Number :"+rs.getLong(2));
				System.out.println("Enter Your Pin :");
				pin =sc.nextInt();
				if(pin==rs.getInt(6)) {
					System.out.println("Select Option :\n1.Withdraw\n2.Deposit\n3.BalanceCheck\n4.PrintReceipt");
					int ch=sc.nextInt();
						if(ch==1) 
						{
							System.out.println("Enter Amount To Withdraw :");
							int amt=sc.nextInt();
							long balamt=rs.getInt(7);
									if(amt<=balamt) {
											balamt -= amt;
											System.out.println("Withdraw Successfully");
											PreparedStatement ps3 =con.prepareStatement("update carddetails set Available_Balance =? where CVV=?");
											ps3.setLong(1,balamt);
											ps3.setLong(2,Ccv);
											ps3.executeUpdate();
											PreparedStatement ps2 =con.prepareStatement("insert into user2 values(?,?,?,?)");
											ps2.setLong(1,Cnum);
											ps2.setInt(2, Ccv);
											ps2.setString(3,"Withdraw");
											ps2.setLong(4, amt);
											ps2.executeUpdate();
										}
										else
										{
											System.out.println("No more amount in your account!!\nGive Available amount Greater than your amount ");
										}
									
						}
						else if(ch==2){
							System.out.println("Enter Amount To Deposite :");
							int amt=sc.nextInt();
							long balamt=rs.getInt(7);
								if(amt>0) {
										balamt += amt;
										System.out.println("Deposite Successfully");
										PreparedStatement ps3 =con.prepareStatement("update carddetails set Available_Balance =? where CVV=?");
										ps3.setLong(1,balamt);
										ps3.setLong(2,Ccv);
										ps3.executeUpdate();
										PreparedStatement ps2 =con.prepareStatement("insert into user2 values(?,?,?,?)");
										ps2.setLong(1,Cnum);
										ps2.setInt(2, Ccv);
										ps2.setString(3,"Deposite");
										ps2.setLong(4, amt);
										ps2.executeUpdate();
								}
								else {
									System.out.println("Give minimum 1rs Amount to Deposite it! ");
								}
						}
						else if(ch==3) {
								long balamt=rs.getLong(7);
								System.out.println("Your Availabe Balance is :"+balamt);
								PreparedStatement ps2 =con.prepareStatement("insert into user2 values(?,?,?,?)");
								ps2.setLong(1,Cnum);
								ps2.setInt(2, Ccv);
								ps2.setString(3,"Checking");
								ps2.setLong(4, balamt);
								ps2.executeUpdate();
						}
						else if(ch==4) {
								System.out.println("Your Account Receipt :");
								long balamt=rs.getLong(7);
								String Cname=rs.getString(1);
								long Cnum =rs.getLong(2);
								String Hname=rs.getString(3);
								long phon=rs.getLong(5);
								System.out.println("Card Name :"+Cname);
								System.out.println("Card Number :"+Cnum);
								System.out.println("Holder Name :"+Hname);
								System.out.println("Phone no :"+phon);
								System.out.println("Balance Amount :"+balamt);
								PreparedStatement ps2 =con.prepareStatement("insert into user2 values(?,?,?,?)");
								ps2.setLong(1,Cnum);
								ps2.setInt(2, Ccv);
								ps2.setString(3,"Receipt Print");
								ps2.setLong(4, balamt);
								ps2.executeUpdate();
								System.out.println(".......Thank You.....!.....Visit Again.....");
							}
							else {
							System.out.println("Invalid Choices");
							}
						
				}
				else {
					System.out.println("Incorrect Pin!!");
				}
				count2++;
				}
			if(count2==0) {
				System.out.println("Invalid Operation");
				}
			con.close();
			}
			catch (Exception e) {
				System.out.println(e);
				}
	
		}	
public static void main(String[] args){
			customer cs=new customer();
			Admin adm=new Admin();
			int hm,ch;
			Scanner sc=new Scanner(System.in);
				while(true) {
						System.out.println("Welcome to this Atm\n1.Admin\n2.User\n3.Exit");
						hm=sc.nextInt();
							if(hm==2) {
									cs.userinfo();
									cs.verify();
							}
							else if(hm==1) {
								System.out.println("*******************************LOGIN PAGE*******************************");
								System.out.println("UserName :");
								String un=sc.next();
								System.out.println("Password :");
								String pwd=sc.next();
								String p="";
								try {
								Connection con =DriverManager.getConnection("jdbc:mysql://localhost/atm_machine","root","Asrar3001@");
								PreparedStatement ps2 =con.prepareStatement("select * from Admlog where UserName=?");
								ps2.setString(1,un);
								ResultSet rs=ps2.executeQuery();
								int count=0;
									while(rs.next()) {
										p=rs.getString(3);
										count++;
										}
											if(count==0) {
													System.out.println("Are U Not Admin!\n....GET LOST....");
												}
											con.close();
								}
								catch(Exception e) {
									System.out.println(e);
								}
								if(pwd.equals(p)) {
								System.out.println("What You Want\n1.AnyModify(or)Update\n2.Remove Card\n3.Add Card\n4.Exit\nSelect One:");
								ch=sc.nextInt();
									if(ch==1) {
											adm.updateinfo();
										}
									else if(ch==2){
											adm.delete();
										}
									else if(ch==3){
											adm.create();
										}
									else if(ch==4){
										System.out.println("Thank You");
										return;
										}
									else {
										System.out.println("Invalid Option!\nGive Crct Option");
									}
								}
								else {
									System.out.println("Incorrect Password");
								}
							}
							else if(hm==3){
								System.out.println("Thank You");
								break;
							}
					else {
						System.out.println("Invalid Option!\nGive Crct Option");
					}	
				}
		}
}
