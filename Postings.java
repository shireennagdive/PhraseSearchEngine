import java.io.*;
import java.util.*;


public class Postings {

	static HashMap<String, ArrayList<Integer>> vocab = new HashMap<String, ArrayList<Integer>>();
	Postings() throws Exception
	   {
	      
	      //System.out.println("enter the files");

	      readFile("input1.txt");
	      //readFile("input2.txt");
	         print();
	      }
	   
	 
	 public static void createPosting(String s,Integer count)
	 {
		 Set<String> temp=vocab.keySet();
		 Iterator i= temp.iterator();
		 int flag=0;
		 while(i.hasNext())
		 {
			 String val=(String)i.next();
			 if(s.compareTo(val)==0)
			 {
				 int j=vocab.get(s).size();
				 vocab.get(s).add(j,count);
				 flag=1;
			 }
		 }
		 if(flag==0)
		 {

			 vocab.put(s, new ArrayList<Integer>());
			 vocab.get(s).add(0,count);
		 
		 }
	 }
	 
	 public static void print()
	 {
		 Set<String> term=vocab.keySet();
		 Iterator i= term.iterator();
		 while(i.hasNext())
		 {
			 String val=(String)i.next();
			 System.out.println(val+"  "+ vocab.get(val).toString());
		 }
	 }
	 
	 public static void readFile(String filename) throws Exception
	 {

		 FileReader in = null;
	      FileWriter out = null;
	      try {
	         in = new FileReader(filename);
	         //out = new FileWriter("output.txt");
	         
	         int c,count=0;
	         String s=new String();
	         while ((c = in.read()) != -1) {
	            //out.write(c);
	        	 if(c!=' ')
	        	 { 
	        		 s+=(char)c;
	        	 }
	        	 else
	        	 {
	        		 count++;
	        		 createPosting(s,count);
	        		 s="";
	        	 }
	         }
	      }finally {
	         if (in != null) {
	            in.close();
	         }
	         /*if (out != null) {
	            out.close();
	         }*/
	      }
		 
	 }
}
