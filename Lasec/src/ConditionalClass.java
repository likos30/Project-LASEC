	import java.io.BufferedReader;
	import java.io.File;
	import java.io.FileReader;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.util.ArrayList;
import java.util.Collections;
	
public class ConditionalClass {

		
		public static void main(String[] args) throws IOException, IOException {
			calculateBlocksConditional(12);
	}
		public static int binaryToInteger(String binary) {
		    char[] numbers = binary.toCharArray();
		    int result = 0;
		    for(int i=numbers.length - 1; i>=0; i--)
		        if(numbers[i]=='1')
		            result += Math.pow(2, (numbers.length-i - 1));
		    return result;
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
		public static int calcublock(int[] i) {
			int sum = 0;
			for (int k=0;k<i.length;k++) {
				sum+=i[k];
			}
			return sum;
		}
		
		public static void calculateBlocksConditional(int block) throws IOException {

			//take a text with only lower case letters
			String text = readDoc(new File("cipherpotterd2.txt")).toLowerCase();
			
			//delete all the spaces
			String textNoSpace = text.replaceAll("\\s+","");
			//Scanner reader = new Scanner(System.in);
			int blockSize = block;
			//Ask a block size to the programmer
				//System.out.println("Choose a block size");
				//blockSize = reader.nextInt();
			int numberOfBlock = textNoSpace.length()-blockSize+1;
			
			ArrayList<ArrayList<String>> classes = new ArrayList<ArrayList<String>>();
			ArrayList<int[]> buffersCounter = new ArrayList<int[]>();
			for(int i=0;i < Math.pow(2, blockSize);i++) {
				ArrayList<String> init = new ArrayList<>();
				classes.add(init);
				int bufferCounter[] = new int[1024];
				buffersCounter.add(bufferCounter);
			}
			
			
			
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
				int index = binaryToInteger(mod2);
				if(classes.get(index).contains(select)){
					buffersCounter.get(index)[classes.get(index).indexOf(select)]++;
				}else {
					// if not add it and put counter to 1
					classes.get(index).add(select);
					buffersCounter.get(index)[classes.get(index).indexOf(select)]=1;
				}
				// just here to give the progression of the work
				if(i%1000==0){
				System.out.println((double)i/(textNoSpace.length()-blockSize+1)+  "% " + i + " block");
				}
			}
			System.out.println("finish to fill");
			double bestProba = 0;
			ArrayList<Double> bestProbaD = new ArrayList<>();
			int index = 0;
			double temp = 0;
			int maxBlock = 0;
			PrintWriter writer = new PrintWriter("occurenceblocksCondipotter"+blockSize+".txt","UTF-8");
			for(int i=0;i<classes.size();i++) {
				double probaCondi = 0;
				double probaClass = 0;
				int numOfBlockInClass[] =new int[classes.size()];
				for(int j=0;j<classes.get(i).size();j++) {
					numOfBlockInClass[i] = calcublock(buffersCounter.get(i));
					if(numOfBlockInClass[i]>1){
					probaClass = ((buffersCounter.get(i)[j] - 1 )/((double)numOfBlockInClass[i]-1));
					}else {
						probaClass=0;
					}
					probaCondi += Math.pow(probaClass,2);
					//writer.println(numOfBlockInClass + " before to see " + classes.get(i).get(j) +", "+buffersCounter.get(i)[j] + " and has a proba to be equal to another of same class " + probaClass);
					//System.out.println(classes.get(i).get(j) +", "+buffersCounter.get(i)[j]);
				}
				if((probaCondi*numOfBlockInClass[i])>bestProba) {
					bestProba=probaCondi*numOfBlockInClass[i];
					temp = probaCondi;
					index = i+1;
				}
				if(numOfBlockInClass[i]>maxBlock) {
					maxBlock = numOfBlockInClass[i];
				}
				if(bestProbaD.size()<2*blockSize+1) {
				bestProbaD.add(probaCondi);
				} else {
					Collections.sort(bestProbaD);
					if(bestProbaD.get(0)< probaCondi){
							bestProbaD.set(0,probaCondi);
				}
				}
				writer.println("proba conditional is= " + probaCondi + " blocksize " + numOfBlockInClass[i]);
				writer.println("nextclass");
			}
			writer.println("best is : " + temp + " at " + index +" where there is "+ numberOfBlock +" and the biggest is " + maxBlock);
			for(int i=0;i<bestProbaD.size();i++){
				writer.println(bestProbaD.get(i));
			}
			writer.close();
			System.out.println("FINISH");
			}
	
		
		
	}

