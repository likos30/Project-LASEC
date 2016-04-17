import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BlockOccurence {
	
	public static void main(String[] args) throws IOException, IOException {
		for(int i=1;i<15;i++){
		calculateBlocks(i);
	}
}
	public static String readDoc(File f) {
		String text = "";
		int read, N = 1024 * 1024;
		char[] buffer = new char[N];

		try {
		    FileReader fr = new FileReader(f);
		    BufferedReader br = new BufferedReader(fr);

		    while(true) {
		        read = br.read(buffer, 0, N);
		        text += new String(buffer, 0, read);

		        if(read < N) {
		            break;
		        }
		    }
		} catch(Exception ex) {
		    ex.printStackTrace();
		}

		return text;
		}
	
	public static void calculateBlocks(int block) throws FileNotFoundException, UnsupportedEncodingException {
		int N = 1024*1024;
		
		//take a text with only lower case letters
		String text = readDoc(new File("test")).toLowerCase();
		
		//delete all the spaces
		String textNoSpace = text.replaceAll("\\s+","");
		//Scanner reader = new Scanner(System.in);
		int blockSize = block;
		//Ask a block size to the programmer
			//System.out.println("Choose a block size");
			//blockSize = reader.nextInt();
		//calculate the number of block of your text
		int numberOfBlock = textNoSpace.length()-blockSize+1;
		
		//the arraylist that will contains all the different blocks
		ArrayList<String> letter = new ArrayList<String>();
		//the counter linked to the arraylist , at each poisition you get the number of time the block at position i in the arraylist appears
		int bufferCounter[] = new int[N];
		//the arraylist that will contains the blocks modulo 2
		ArrayList<String> letterMod2 = new ArrayList<String>();
		// the counter  at each poisition you get the number of time the block at position i in the arraylist appears.
		int counterBinary[] = new int[N];
		for(int i=0;i<(textNoSpace.length()-blockSize+1);i++){
			//get the block of the size you want
			String select = textNoSpace.substring(i,i+blockSize);
			String mod2 = select;
			for(int j=0;j<select.length();j++){
				//convert it modulo 2
				if(mod2.charAt(j)!='0' && mod2.charAt(j)!='1') {
				mod2 = mod2.replace(mod2.charAt(j),(char) ((char) ((mod2.charAt(j)+1)%2)+48));
				}
			}
			//for block modulo 2
			// if its in the list , just increment counter
			if(letterMod2.contains(mod2)){
				counterBinary[letterMod2.indexOf(mod2)]++;
			}else { //else add it and put counter to 1
				letterMod2.add(mod2);
				bufferCounter[letterMod2.indexOf(mod2)]=1;
			}
			//for block modulo 26
			//if in list , increment counter too
			if(letter.contains(select)){
				bufferCounter[letter.indexOf(select)]++;
			}else {
				// if not add it and put counter to 1
				letter.add(select);
				bufferCounter[letter.indexOf(select)]=1;
			}
			// just here to give the progression of the work
			System.out.println((double)i/(textNoSpace.length()-blockSize+1)+  "% " + i + " block");
		}
		System.out.println("finish to fill");
		
		PrintWriter writer = new PrintWriter("occurenceblocks"+blockSize+".txt","UTF-8");
		double sumBadMatching=0;
		double sumGoodMatching=0;
		int numberOfBadMatching = 0;
		int numberOfGoodMatching = 0;
		
		// computation for good matching
		for(int i=0;i<letter.size();i++){
			writer.println("le block " + letter.get(i) + " apparait "+ bufferCounter[i] + "fois");
			double proba = (double)bufferCounter[i]/numberOfBlock;
			numberOfGoodMatching+=bufferCounter[i]-1; // 		
			sumGoodMatching+=(Math.pow(proba, 2));
		}
		//computation for bad matching
		for(int i=0;i<letterMod2.size();i++){
			writer.println("le block " + letterMod2.get(i) + " apparait "+ counterBinary[i] + "fois");
			numberOfBadMatching+=counterBinary[i]-1;
			double probaBadMatching = (double)counterBinary[i]/numberOfBlock;
			sumBadMatching+=Math.pow(probaBadMatching, 2);
		}
		writer.println("For Block Size "+ blockSize + ", we have "+ numberOfBlock +", thus the probabilty that 2 blocks are equals mod 2 is "+ sumBadMatching + " and modulo 26 is "+sumGoodMatching);
		writer.println("There are "+numberOfGoodMatching+ "good matching and they are " +numberOfBadMatching + " bad matching , the ratio is "+ (double)numberOfGoodMatching/(numberOfBadMatching+numberOfGoodMatching));
		writer.close();
		System.out.println("FINISH");
		}
}

