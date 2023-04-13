package application;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movie {
	
public static void IterateTicket(String findTitle) throws IOException {
		
		LineNumberReader currM = new LineNumberReader(new FileReader("CurrentMovies.txt"));
		LineNumberReader upcomM = new LineNumberReader(new FileReader("UpcomingMovies.txt"));
		
		ArrayList<String> savesLines = new ArrayList<String>();
		
		final Pattern lastIntPattern = Pattern.compile("[^0-9]+([0-9]+)$");
		String strCurrentLine;
		int found = 0;

		
		savesLines.clear();
		while ((strCurrentLine = currM.readLine()) != null) {
			
		    if (strCurrentLine.equals("Movie Name: " + findTitle)) { // Finds movie
		    	found = ((LineNumberReader) currM).getLineNumber();
		    }
		    
		    if (((LineNumberReader) currM).getLineNumber() == found+4 && found != 0) {
		    	// FINDS NUMBER
		    	Matcher matcher = lastIntPattern.matcher(strCurrentLine);
		    	if (matcher.find()) {
		    	    String someNumberStr = matcher.group(1);
		    	    int lastNumberInt = Integer.parseInt(someNumberStr);
		    	    lastNumberInt++; // increments number at the end of string
		    	    //REPLACE
		    	    String newStrCurrentLine = "Tickets Bought: " + lastNumberInt; // new line to replace		    	    
		    	    savesLines.add(newStrCurrentLine); //append new line 
		    	}
		    	continue;
		    	//append    	
		    }
		    savesLines.add(strCurrentLine);
		}
		
		// Print
		FileWriter writerCurr = new FileWriter("CurrentMovies.txt"); 
		   if (!(savesLines.get(savesLines.size() - 1).contains("Tickets Bought:"))) {
		    	  savesLines.remove(savesLines.size() - 1);
		    }
			
			int currentPosition = 0;
			
			for(String str: savesLines) {
				writerCurr.write(str);
				currentPosition++;
				if(currentPosition != (savesLines.size())) {
					writerCurr.write(System.lineSeparator());
				}
			}
			
			currM.close();
			upcomM.close();
			writerCurr.close();
	}
	
	public static int getCode(String movieName)
	{
		try {
            BufferedReader reader = new BufferedReader(new FileReader("CurrentMovies.txt"));
            BufferedReader reader2 = new BufferedReader(new FileReader("UpcomingMovies.txt"));
            String line;
            String finVal = "INVALID";

           
            while ((line = reader.readLine()) != null) 
                if (line.equals("Movie Name: " + movieName))                 	
                	return 0;
            while ((line = reader2.readLine()) != null)
            	if (line.equals("Movie Name: " + movieName)) 
                	return 1;

            reader.close();
            reader2.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		return -1;
	}
	
    /******USER******/
    public static String viewMovie(String movieName) {
    	try {
            BufferedReader reader = new BufferedReader(new FileReader("CurrentMovies.txt"));
            BufferedReader reader2 = new BufferedReader(new FileReader("UpcomingMovies.txt"));
            String line;
            String finVal = "INVALID";

           
            while ((line = reader.readLine()) != null) 
            {
                //locate movie
                if (line.equals("Movie Name: " + movieName)) 
                {
                	finVal = line + "\n";

                    for (int lineNumber = 0; lineNumber <= 2; lineNumber++) 
                    {
                        finVal += reader.readLine() + "\n";
                    }
                }
            }
            
            while ((line = reader2.readLine()) != null) 
            {
                //locate movie
                if (line.equals("Movie Name: " + movieName)) 
                {
                	finVal = line + "\n";

                    for (int lineNumber = 0; lineNumber <= 2; lineNumber++) 
                    {
                        finVal += reader2.readLine() + "\n";
                    }
                }
            }
              
            reader.close();
            reader2.close();
            return finVal;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public static String searchAndDisplayMovie(String movieName) {
        //movieName is the movie to search, display all movies
        try {
            BufferedReader reader = new BufferedReader(new FileReader("CurrentMovies.txt"));
            BufferedReader reader2 = new BufferedReader(new FileReader("UpcomingMovies.txt"));
            String line;
            String finVal = "INVALID";

           
            while ((line = reader.readLine()) != null) 
            {
                //locate movie
                if (line.equals("Movie Name: " + movieName)) 
                {
                    //print movie block
                    for (int lineNumber = 0; lineNumber <= 1; lineNumber++) 
                    {
                        finVal = "At Theaters:\t" + line.substring(12);
                    }
                }
            }
            
            while ((line = reader2.readLine()) != null) 
            {
                //locate movie
                if (line.equals("Movie Name: " + movieName)) 
                {
                    //print movie block
                    for (int lineNumber = 0; lineNumber <= 1; lineNumber++) 
                    {
                        finVal = "Coming Soon:\t" + line.substring(12);
                    }
                }
            }
              
            reader.close();
            reader.close();
            
            return finVal;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public static String displayCurrentMovies() {
        //Outputs current movie information except ticket info
        try 
        {
            BufferedReader reader = new BufferedReader(new FileReader("CurrentMovies.txt"));
            String line;
            StringBuilder finalVal = new StringBuilder();
            while ((line = reader.readLine()) != null) 
                if(line.contains("Movie Name: "))
                	finalVal.append("At Theaters:\t" + line.substring(12) + "\n");
            reader.close();
            if(finalVal.isEmpty())
            	return "No Movies Available!";
            else
            	return finalVal.toString();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String displayUpcomingMovies() {
        //Outputs upcoming movie information except ticket info
    	try 
        {
            BufferedReader reader = new BufferedReader(new FileReader("UpcomingMovies.txt"));
            String line;
            StringBuilder finalVal = new StringBuilder();
            while ((line = reader.readLine()) != null) 
                if(line.contains("Movie Name: "))
                	finalVal.append("Coming Soon:\t" + line.substring(12) + "\n");
            reader.close();
            if(finalVal.isEmpty())
            	return "No Upcoming Movies Available!";
            else
            	return finalVal.toString();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return null;
    }

    /******ADMIN******/
    public static String generateReport() 
    {
    	StringBuilder fin = new StringBuilder();
    	String line;
        try 
        {
            BufferedReader reader = new BufferedReader(new FileReader("CurrentMovies.txt"));
            
            while ((line = reader.readLine()) != null) 
            {
                //synaposis, cast name, runtime
                if (line.startsWith("Synaposis: "))
                {
                    for (int i = 0; i < 2; i++) 
                    {
                        reader.readLine();
                        reader.lines().skip(1);
                    }
                } 
                else 
                {
                	fin.append(line + "\n");        	
                }
            }
            reader.close();
            return fin.toString();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void addMovie(int currentOrUpcoming, String movieName, String synopsis, String castNames, String runtime) {
      //specify which file to modify
      //0 for CURRENT, 1 for UPCOMING
        String movieFile = null;
        if (currentOrUpcoming == 0) {
            movieFile = "CurrentMovies.txt";
        } else if (currentOrUpcoming == 1) {
            movieFile = "UpcomingMovies.txt";
        } else {
            System.out.println("Error: Specify if the movie is upcoming or current");
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(movieFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(movieFile, true));
            //while (reader.readLine() != null) {
            writer.newLine();
            writer.newLine();
            writer.write("Movie Name: " + movieName);
            writer.newLine();
            writer.write("Synaposis: " + synopsis);
            writer.newLine();
            writer.write("Cast Name: " + castNames);
            writer.newLine();
            writer.write("Runtime: " + runtime);
            writer.newLine();
            writer.write("Tickets Bought: " + 0);
            writer.flush();
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void removeMovie(String findTitle) throws IOException {
		List<String> savesLines = new ArrayList<String>();
		LineNumberReader currM = new LineNumberReader(new FileReader("CurrentMovies.txt"));
		LineNumberReader upcomM = new LineNumberReader(new FileReader("UpcomingMovies.txt"));
		
		String strCurrentLine;
		
		// CHECKS CURRENT
		
		int found = 0;
		int count = 0;
		while ((strCurrentLine = currM.readLine()) != null) {
		    if (strCurrentLine.equals(findTitle)) { // Finds movie
		    	found = ((LineNumberReader) currM).getLineNumber();
		    }
		    		    
		    if (count != 0) {
		    	count--;
		    	continue;
		    }
		    
		    if (((LineNumberReader) currM).getLineNumber() == (found)){ 
		    	count = 5; // ignores 5 lines
		    	continue;
		    }
		    
		    savesLines.add(strCurrentLine);
		    
		}

		// WRITES RESULT
		
		FileWriter writerCurr = new FileWriter("CurrentMovies.txt"); 
		int currentPosition = 0;
		
		if (!(savesLines.get(savesLines.size() - 1).contains("Tickets Bought:"))) {
            savesLines.remove(savesLines.size() - 1);
        }
		
		for(String str: savesLines) {
			writerCurr.write(str);
			currentPosition++;
			if(currentPosition != (savesLines.size())) {
				writerCurr.write(System.lineSeparator());
			}
		}
		writerCurr.close();
		
		// CHECKS UPCOMING
		
		savesLines.clear();
		found = 0;
		count = 0;
		while ((strCurrentLine = upcomM.readLine()) != null) {
		    if (strCurrentLine.equals(findTitle)) { // Finds movie
		    	found = ((LineNumberReader) upcomM).getLineNumber();
		    }
		    		    
		    if (count != 0) {
		    	count--;
		    	continue;
		    }
		    
		    if (((LineNumberReader) upcomM).getLineNumber() == (found)) {
		    	count = 5; // ignores 5 lines
		    	continue;
		    }
		    
		    savesLines.add(strCurrentLine);
		    
		}
		
		// WRITES RESULT
		
		FileWriter writerUpcom = new FileWriter("UpcomingMovies.txt"); 
		currentPosition = 0;
		
		if (!(savesLines.get(savesLines.size() - 1).contains("Tickets Bought:"))) {
            savesLines.remove(savesLines.size() - 1);
        }
		
		for(String str: savesLines) {
			writerUpcom.write(str);
			currentPosition++;
			if(currentPosition != (savesLines.size())) {
				writerUpcom.write(System.lineSeparator());
			}
		}
		writerUpcom.close();
	}
    
   // REVIEWS
    
    public static ArrayList<String> reviewsForMovie(String findTitle) {		
		
		ArrayList<String> list = new ArrayList<String>();
		
		try{
		// MAIN
			LineNumberReader fileObj = new LineNumberReader(new FileReader("Review.txt"));
			String strCurrentLine;
			int found = 0;
			// Goes through every line finding given string
			while ((strCurrentLine = fileObj.readLine()) != null) {
				//Gets instance of found movie
			    if (strCurrentLine.equals("Movie: " + findTitle)) { // compareTo alternative (not so strict(might cause problems))
			    	//System.out.println("Found in line: " + ((LineNumberReader) fileObj).getLineNumber());
			    	found = ((LineNumberReader) fileObj).getLineNumber(); // Line number of review title (will not retrieve other reviews until this one completes)
			    }	/// LOGIC TO RETRIVE A USER
			    if (((LineNumberReader) fileObj).getLineNumber() == found+1 && found != 0) {
			    	list.add(strCurrentLine + "\n"); // Assign to variable
			    }	/// LOGIC TO RETRIVE A RATING
			    if (((LineNumberReader) fileObj).getLineNumber() == found+2 && found != 0) {
			    	list.add("Rating: " + strCurrentLine + "\n"); // Assign to variable
			    }	/// LOGIC TO RETRIVE A TEXT
			    if (((LineNumberReader) fileObj).getLineNumber() == found+3 && found != 0) {
			    	list.add(strCurrentLine + "\n\n"); // Assign to variable
			    }
			}		
			
			fileObj.close();
		// CATCH
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}// END OF CATCH		
		return list;
	}
    
   public static double findRating(String findTitle) throws IOException {
		ArrayList<Double> scores = new ArrayList<Double>();
		LineNumberReader fileObj = new LineNumberReader(new FileReader("Review.txt"));
		String strCurrentLine;
		int found = 0;
		double sum = 0;
		
		while ((strCurrentLine = fileObj.readLine()) != null) {
		    if (strCurrentLine.equals("Movie: " + findTitle)) {
		    	 found = ((LineNumberReader) fileObj).getLineNumber(); // Line number of review title (will not retrieve other reviews until this one completes)
		    }
			if (((LineNumberReader) fileObj).getLineNumber() == found+2 && found != 0) { 
				scores.add(Double.parseDouble(strCurrentLine));  // String to double array_list
		    }
		}
		
		for (double d : scores) {
			sum += d;
		}
		fileObj.close();
		return (sum / scores.size());
	}
    
   	//adds a review for a movie (make sure logged in USER + certain MOVIE)
 	public static void writeReview(String userTitle, String userName, String userScore, String userText) throws IOException {
 		
 		FileWriter fw = new FileWriter("Review.txt", true);
 	    BufferedWriter bw = new BufferedWriter(fw);
 	    
 	    bw.newLine(); // this new line just for easier readability of text file
 	    bw.write("Movie: " + userTitle);
 	    bw.newLine();
 	    bw.write("User: " + userName);
 	    bw.newLine();
 	    bw.write(userScore);
 	    bw.newLine();
 	    bw.write("Text: " + userText);
 	    bw.newLine();
 	    bw.close();
 	}
 	
 	public static boolean dupReview(String userName, String movieName)
 	{
 		BufferedReader quick = null;
 		try
 		{
 			quick = new BufferedReader(new FileReader(new File("Review.txt")));
 			String str;
 			while((str = quick.readLine()) != null)
 				if(str.contains(movieName))
 				{
 					if((str = quick.readLine()).contains(userName))
 					{
 						quick.close();
 						return false;
 					}		
 				}	
 		}
 		catch(Exception e) {e.printStackTrace();}
 		return true;
 	}
    
}
