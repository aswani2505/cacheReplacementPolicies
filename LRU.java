
import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

class LRU{
	public static void main(String [] args) throws IOException{
		double hit = 0;
                double miss = 0;
                double mem_acc = 0;
                double hit_ratio = 0;
                
		// Take input from user.
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the cache size");
		int size = sc.nextInt();
		System.out.println("Please enter the file name");
		String file_name = sc.next();
		
		// Declare the Linked List.
		LinkedList list = new LinkedList();
		
		// Open File.
		FileInputStream fstream = new FileInputStream(file_name);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String line;
		String space = " ";
		// Read File line by line.
		while((line = br.readLine()) != null){
			String[] sb = line.split(space);
			int sb1 = Integer.parseInt(sb[0]);
			int sb2 = Integer.parseInt(sb[1]);
			//System.out.println(sb1+" "+sb2 ); 
			
			for(int i = sb1; i<(sb1+sb2); i++){
				if(list.contains(i)){
					hit++;
					list.remove(list.indexOf(i));
					list.addFirst(i);
				}
                                else{
                                    if(list.size()>=size){
					list.removeLast();
					list.addFirst(i);
                                    }
                                    else{
					list.addFirst(i);
                                    }
				miss++;
                                }
                        }
                }
		br.close();                
                mem_acc = hit+miss;                
		hit_ratio = (((hit) / (mem_acc))*100);
               
                // Rounding the values.
                DecimalFormat df = new DecimalFormat("#.##");
               
                // Printing the results.
                System.out.println("Number of Cache Hits:"+hit);
                System.out.println("Number of Cache Miss:"+miss); 
                System.out.println("Numebr of Memory access:"+mem_acc);
                System.out.println("Hit Ratio:"+hit_ratio+"%");
                System.out.println("Hit Ratio after rounding:"+df.format(hit_ratio)+"%");
                
		
	}
}
