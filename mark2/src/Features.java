import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import java.util.stream.IntStream;


public class Features {

	static String fpdata = "C:\\Mark2\\data\\";
	static String filepath = "C:\\Mark2\\data\\history.csv";
	static String fpoutput = "C:\\Mark2\\data\\output.csv";
	
	static ArrayList<int[]> history;
	static ArrayList<int[]> matchLast;
	
    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        //System.out.println("Hello, World");	
    	history = loadData(filepath);
    	writeData(fpoutput, history);
    	//print_nCr(49, 6, fpdata + "combination.csv");
    	matchLast(history, 1);
    	matchLast(history, 2);
    	matchLast(history, 3);
    	
    	//int[] a = history.get(0);
    	//int i = 0;
    	//for (int[] b: history) {
    		
    		//if (i != 0) {
    			//match(a, b);
    			//a = b;
    		//}
    		//i++;
    			
    	//}
    }
    
    public static ArrayList<int[]> loadData(String fileName) {
    	ArrayList<int[]> list = new ArrayList<int[]>();	  
    	try {
    		File file = new File(fileName);
    		Scanner scanner = new Scanner(file);
    		while (scanner.hasNext()) {
    			String s = scanner.next();
    			int[] numbers = Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray();
    			list.add(numbers);
    			//System.out.println(Arrays.toString(numbers));
    		}
    		scanner.close();
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}
    	return list;
    }
    
    public static void writeData(String filename, ArrayList<int[]> list) {
    	try {
	    	RandomAccessFile stream = new RandomAccessFile(filename, "rw");
	    	FileChannel channel = stream.getChannel();
	    	
	    	for(int[] value: list) {
	    		byte[] strBytes = (Arrays.toString(value) + "\n").getBytes();
			    ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
			    buffer.put(strBytes);
			    buffer.flip();
			    channel.write(buffer);
	    	}
	    	
		    //String value = "Hello";
		    //byte[] strBytes = value.getBytes();
		    //ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
		    //buffer.put(strBytes);
		    //buffer.flip();
		    //channel.write(buffer);
		    stream.close();
		    channel.close();    	
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}  catch (IOException e) {
    		e.printStackTrace();
    	}
    }    
    
    
	public static final void print_nCr(final int n, final int r, String filename) {
	    int[] res = new int[r];
	    for (int i = 0; i < res.length; i++) {
	        res[i] = i + 1;
	    }
	    boolean done = false;
	    
    	try {
	    	RandomAccessFile stream = new RandomAccessFile(filename, "rw");
	    	FileChannel channel = stream.getChannel();

		    while (!done) {
		    	byte[] strBytes = (Arrays.toString(res) + "\n").getBytes();
			    ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
			    buffer.put(strBytes);
			    buffer.flip();
			    channel.write(buffer);
		    	done = getNext(res, n, r);
		    }	    	
		    stream.close();
		    channel.close();    	
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}  catch (IOException e) {
    		e.printStackTrace();
    	}
	    	
	}

	/////////

	public static final boolean getNext(final int[] num, final int n, final int r) {
	    int target = r - 1;
	    num[target]++;
	    if (num[target] > ((n - (r - target)) + 1)) {
	        // Carry the One
	        while (num[target] > ((n - (r - target)))) {
	            target--;
	            if (target < 0) {
	                break;
	            }
	        }
	        if (target < 0) {
	            return true;
	        }
	        num[target]++;
	        for (int i = target + 1; i < num.length; i++) {
	            num[i] = num[i - 1] + 1;
	        }
	    }
	    return false;
	}
	
	public static void match(int[] a, int[] b) {
		//int a[] = {1,2,3,4,5};
		//int b[] = {1,2,3,6};

		//int[] uniqueEntries = IntStream.concat(IntStream.of(a), IntStream.of(b))
		  //  .filter(x -> !IntStream.of(a).anyMatch(y -> y == x) || !IntStream.of(b).anyMatch(z -> z == x))
		   // .toArray();

		int[] m = IntStream.of(a).filter(x -> IntStream.of(b).anyMatch(y -> y == x)).toArray();
		//System.out.println(Arrays.toString(a) + "=" + Arrays.toString(b) + "?" + Arrays.toString(m) + ";" + m.length + "/" + a.length + "=" + (double)((double)m.length / (double)a.length) );
		
		
		System.out.println(Arrays.toString(a) + "|" + Arrays.toString(m) + "|" + m.length + "/" + a.length + "|" + (double)((double)m.length / (double)a.length) );
		
	}
	
	public static void matchLast(ArrayList<int[]> list, int n) {
		System.out.println("skip " + n);
    	int[] a = list.get(0);
    	//int i = 0;
    	for (int i = 0; i< list.size(); i = i + n) {
    		int[] b = list.get(i);
    		if (i != 0) {
    			match(a, b);
    			a = b;
    		}
    		
    	}
		
	}
	
}






