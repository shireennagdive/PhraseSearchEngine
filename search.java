import java.io.IOException;
import java.util.*;

	public class search 
	{
	
		static Postings postingList;
		
		public static void main(String a[]) throws Exception
		{
		
		/*vocab.put("alin", new ArrayList<Integer>());
		vocab.put("bhavana", new ArrayList<Integer>());
		vocab.get("alin").add(0,2);
		vocab.get("alin").add(1,4);
		vocab.get("alin").add(2,6);
		vocab.get("alin").add(3,10);
		vocab.get("bhavana").add(0,3);
		vocab.get("bhavana").add(1,7);
		vocab.get("bhavana").add(2,8);
		vocab.get("bhavana").add(3,14);
		System.out.println("value = "+Next("alin",4));
		System.out.println("checking for prev value is = "+Prev("bhavana",3));
		System.out.println(vocab.get("alin"));
		System.out.println(vocab.get("bhavana"));*/
			
		postingList=new Postings();
		Integer [] p=NextPhrase("i am not",Integer.MIN_VALUE);
		System.out.println("calling phrase search "+p[0]+" "+p[1]);
		
		}
		
		public static Integer Next(String t,Integer current){
			ArrayList<Integer> temp=postingList.vocab.get(t);
			int l=temp.size();
			//int a[];
			if(l==0 || temp.get(l-1)<=current){
				return Integer.MAX_VALUE;
			}
			if(temp.get(0)>current){
				return temp.get(0);
			}
			int i=BinarySearchNext(temp,0,l-1,current);
			//System.out.println("Next: i = "+i);
			//int i= Arrays.binarySearch(temp.toArray(a),0,l-1,current);
			return temp.get(i);
		}
		public static int BinarySearchNext(ArrayList<Integer>temp, int low, int high, Integer current)
		{
			while((high-low)>1)
			{
				int mid=(low+high)/2;
				if(temp.get(mid)<=current)
					low=mid;
				else
					high=mid;
			}
			return high;
		}
		public static Integer Prev(String t,Integer current){
			ArrayList<Integer> temp=postingList.vocab.get(t);
			int l=temp.size();
			//int a[];
			if(l==0 || temp.get(0)>current){
				return Integer.MIN_VALUE;
			}
			if(temp.get(l-1)<=current){
				return temp.get(l-1);
			}
			int i=BinarySearchPrev(temp,0,l-1,current);
			//System.out.println("Prev: i = "+i);
			//int i= Arrays.binarySearch(temp.toArray(a),0,l-1,current);
			return temp.get(i);
		}
		public static int BinarySearchPrev(ArrayList<Integer>temp, int low, int high, Integer current)
		{
			while((high-low)>1)
			{
				int mid=(low+high)/2;
				if(temp.get(mid)>current)
					high=mid;
				else
					low=mid;
				System.out.println("high = "+high+" low = "+low+" mid = "+mid);
			}
			return low;
		}
		
		public static Integer[] NextPhrase(String s, Integer pos)
		{
			Integer [] position=new Integer[2];
			String[] terms= s.split(" ");
			Integer v=pos;
			for(int i=0;i<terms.length;i++)
				v=Next(terms[i],v);
			if(v==Integer.MAX_VALUE)
			{
				position[0]=position[1]=Integer.MAX_VALUE;
				return position;
			}
			Integer u=v;
			for(int i=terms.length-1;i>=0;i--)
				u=Prev(terms[i],u);
			if((v-u)==terms.length-1)
			{
				position[0]=u;
				position[1]=v;
				return position;
			}
			else
				return NextPhrase(s,u);
		}
	


}
