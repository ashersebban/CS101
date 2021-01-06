package edu.nyu.cs.as10518;

import java.io.*;
import java.util.*;


public class Assignment4 {
	
	public static void main(String[] args) {
		
		//materials
		String fileName = "src/ny_babynames.csv";
		String link = "https://data.cityofnewyork.us/Health/Popular-Baby-Names/25th-nujf";
		
		//introductory messages
		System.out.println("Welcome to the Popular NY Baby Name App.");
		System.out.println("This app mines data from: "+link);
		System.out.println("We show you the most popular NY baby name(s) based on year, gender, and ethnicity.");
		//warning
		System.out.println("\nDisclaimer: This dataset contains errors. Duplicate or conflicting output values may arrise.");
		
		
		String[][] data = readFromFile(fileName);
		
		String[] user_input = getUserInput(); // [Ethnic,year,Gender]
		
		
		int total_rows = 0;
		boolean matches = false;
		Scanner scan = new Scanner(System.in);
		
		
		for(String[] line:data) {
			String year = line[0]; 
			String gender = line[1]; 
			String ethnicity = line[2]; 
			String e = ethnicity.substring(0,1);
			String babyname = line[3];
			String count = line[4];
			String rank = line[5];
			
			
			//NOT HEADER
			if(e.equalsIgnoreCase(user_input[0])|| user_input[0].equals("")){
				if(year.equals(user_input[1])|| user_input[1].equals("")){
					if(gender.equalsIgnoreCase(user_input[2])||user_input[2].equals("")) {
						if(rank.equals("1")) {
							total_rows++;
							matches = true;
							if(total_rows%5==0) {
								System.out.println("...hit enter to see the next 5...");
								scan.nextLine();
							}	
							System.out.printf("The most popular %s %s baby name in %s was %s. (total: %s)\n",
									gender.toLowerCase(),ethnicity.toLowerCase(),year,babyname.toUpperCase(),count);
						}
					}
				}
			}
		}
		if(matches == false)System.out.println("No matches found.");
		else System.out.println("That's all folks!");
		
	}

	private static String[] getUserInput() {
		// TODO Auto-generated method stub
		
		String[] user_input = new String[3];
		
		//Used to read input
		Scanner scan = new Scanner(System.in);
		
		//these boolean variables will allow users to display ALL of the available data if desired
		//boolean all_ethnicities = false;
		//boolean all_years = false;
		//boolean all_genders = false;
		//this will be used to make sure user inputs a valid response
		
		boolean is_valid;
		
		
		//ETHNICITY
		//this section with display the options for the user to choose from
		System.out.println("\nCHOOSE ETHNICITY");
		System.out.println("1. White Non Hispanic");
		System.out.println("2. Black Non Hispanic");
		System.out.println("3. Hispanic");
		System.out.println("4. Asain and Pacific Islander");
		System.out.println("Press [Enter] to see all ethnicities...");
		
		//this section will handle the choice made by the user
		//set default: input not valid
		is_valid = false;
	    while(is_valid == false) {
	    	System.out.println("Enter a number 1-4: ");
	    	String user_ethnicity = scan.nextLine();
	    	
	    	is_valid = true;
	    	if(user_ethnicity.equals("1"))user_input[0]  = "W";
	    	else if(user_ethnicity.equals("2"))user_input[0] = "B";
	    	else if(user_ethnicity.equals("3"))user_input[0] = "H";
	    	else if(user_ethnicity.equals("4"))user_input[0]  = "A";
	    	else if(user_ethnicity.equals(""))user_input[0]  = "";
	    	else{
	    		System.out.println("Invalid response.");
	    		is_valid = false;
	    	}
	    }
	    
	    //YEAR
	    //this section with display the options for the user to choose from
	    System.out.println("\nCHOOSE YEAR:");
	    System.out.println("Enter a year between 2011 and 2016:");
	    System.out.println("Press [Enter] to see all years...");
	    
	    //this section will handle the choice made by the user
	    //reset default: input not valid
		is_valid = false;//default: input not valid
	    while(is_valid == false) {
	    	System.out.println("Enter a valid year: ");
	    	String user_year= scan.nextLine();
	    	
	    	is_valid = true;
	    	if(user_year.equals("2011")||user_year.equals("2012")||user_year.equals("2013")
	    			||user_year.equals("2014")||user_year.equals("2015")
	    			||user_year.equals("2016")||user_year.equals(""))user_input[1] = user_year;
	    	else{
	    		is_valid = false;
	    		System.out.println("Invalid response.");
	    	}
	    }
	 
	    //GENDER
	    //this section with display the options for the user to choose from
	    System.out.println("\nCHOOSE GENDER:");
	    System.out.println("1. Male");
	    System.out.println("2. Female");
	    System.out.println("Press [Enter] to include both...");
	   
	    //this section will handle the choice made by the user
	    //reset default: input not valid
		is_valid = false;//default: input not valid
	    
	    while(is_valid == false) {
	    	System.out.println("Enter a number 1 or 2: ");
	    	String user_gender = scan.nextLine();
	    	is_valid = true;
	    	if(user_gender.equals("1"))user_input[2] = "MALE";
	    	else if(user_gender.equals("2"))user_input[2] = "FEMALE";
	    	else if(user_gender.equals(""))user_input[2] = "";	
	    	else{
	    		is_valid = false;
	    		System.out.println("Invalid response.");
	    	}
	    }
		return user_input;
	}

	private static String[][] readFromFile(String fileName) {
		// TODO Auto-generated method stub
		String[][]data = null;
		
		try{
			//Used to read file
			Scanner scan = new Scanner(new File (fileName));
			//scan.useDelimiter("[\n]");
			
			//Get total rows
			int row_counter = 0;
			while(scan.hasNextLine()) {
				row_counter++;
				scan.nextLine();
			}
			
			//Get total columns
			Scanner scan1 = new Scanner(new File (fileName));
			//scan1.useDelimiter("[\n]");
			String[] line = scan1.nextLine().split(",");
			
			//Instantiate Array
			data = new String[row_counter-1][line.length];
			
			//Populate Array
			for(int i = 0;i<row_counter-1;i++){
				String str = scan1.nextLine();
				data[i] = str.split(",");
			}
			//System.out.println(row_counter);
		}
		catch(FileNotFoundException e){
			System.out.println("File not found.");
		}
		return data;
	}
		

}
