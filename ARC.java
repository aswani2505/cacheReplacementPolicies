
import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class ARC {

// Initialise four Linked Lists.
    public static LinkedList<Integer> T1 = new LinkedList<Integer>();
    public static LinkedList<Integer> T2 = new LinkedList<Integer>();
    public static LinkedList<Integer> B1 = new LinkedList<Integer>();
    public static LinkedList<Integer> B2 = new LinkedList<Integer>();


// Function for Adaptation.     
    public static double Adaptation1(){
        double d;
        if(B1.size()>=B2.size()){
            d=1;
	}
        else{
            d=(B2.size()/B1.size());
	}
        return d;
    }

// Second Adaptation function.
    public static double Adaptation2(){
        double d;
        if(B2.size()>=B1.size()){
            d=1;
	}
	else{
            d=(B1.size()/B2.size());
	}
        return d;
    }

// Replace function.
    public static void Replace(int sb1, double p){
        if((T1.size()!=0)&&((T1.size()>p)||(B2.contains(sb1)&&(T1.size()==p)))){
            int last=T1.getLast();
            T1.removeLast();
            B1.addFirst(last);
	}
	else {
            int last=T2.getLast();
            T2.removeLast();
            B2.addFirst(last);
	}
    }

// Define the main function.
    public static void main(String[] args) throws IOException{
        double hit = 0;
        double miss = 0;
        double mem_acc = 0;
        double hit_ratio = 0;
        double p = 0;
        
        // Take input from user.
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the cache size");
        int size = sc.nextInt();
        System.out.println("Please enter the file name");
        String file_name = sc.next();
        
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
                
// Case 1:                
                if(T1.contains(i)){
                    hit++;
                    int index = T1.indexOf(i);
                    T1.remove(index);
                    T2.addFirst(i);					
		}
		else if(T2.contains(i)){
                    hit++;
                    int index = T2.indexOf(i);
                    T2.remove(index);
                    T2.addFirst(i);					
		}

// Case 2:                
                else if(B1.contains(i)){
                    miss++;
                    double d1 = Adaptation1();
                    if(p+d1<size){
                        p=p+d1;
                    }
                    else{
                        p=size;
                    }
                    Replace(i,p);

                    int index = B1.indexOf(i);
                    B1.remove(index);
                    T2.addFirst(i);
                }
                
// Case 3:                
                else if(B2.contains(i)){
                    miss++;
                    double d2 = Adaptation2();
                    if(p-d2>0){
                        p=p-d2;
                    }
                    else{
                        p=0;
                    }

                    Replace(i,p);

                    int index = B2.indexOf(i);
                    B2.remove(index);
                    T2.addFirst(i);						

                }                
                else{
                    miss++;
                    if(T1.size()+B1.size()==size){
			if(T1.size()<size){
                            B1.removeLast();
                            Replace(i,p);
			}

			else{
                            T1.removeLast();
			}
                    }

// Case 4:
                    else if((T1.size()+B1.size())<size){

			int Totalcache= (T1.size()+T2.size()+B1.size()+B2.size());

			if(Totalcache>=size){

                            if((T1.size()+T2.size()+B1.size()+B2.size())==(2*size)){
				B2.removeLast();
                            }
                            Replace(i,p);
			}
                    }
                    T1.addFirst(i);
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
