	import java.io.BufferedReader;
	import java.io.File;
	import java.io.FileReader;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.util.ArrayList;
	
public class ConditionalClass {

		
		public static void main(String[] args) throws IOException, IOException {
			calculateBlocksConditional(9);

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
			String text = readDoc(new File("test")).toLowerCase();
			
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
				int bufferCounter[] = new int[1024*10];
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
			for(int i=0;i<classes.size();i++) {
				for(int j=0;j<classes.get(i).size();j++) {
					
				}
			}
			System.out.println("finish to fill");
			double bestProba = 0;
			int index = 0;
			PrintWriter writer = new PrintWriter("occurenceblocksCondi"+blockSize+".txt","UTF-8");
			for(int i=0;i<classes.size();i++) {
				double probaCondi = 0;
				double probaClass = 0;
				int numOfBlockInClass =0;
				for(int j=0;j<classes.get(i).size();j++) {
					numOfBlockInClass = calcublock(buffersCounter.get(i));
					probaClass = ((buffersCounter.get(i)[j] - 1 )/(double)numOfBlockInClass);
					probaCondi += Math.pow(probaClass,2);
					//writer.println(numOfBlockInClass + " before to see " + classes.get(i).get(j) +", "+buffersCounter.get(i)[j] + " and has a proba to be equal to another of same class " + probaClass);
					
					//System.out.println(classes.get(i).get(j) +", "+buffersCounter.get(i)[j]);
				}
				if(probaCondi>bestProba) {
					bestProba = probaCondi;
					index = i;
				}
				writer.println("proba conditional is= " + probaCondi);
				writer.println("nextclass");
			}
			writer.println("best is : " + bestProba + " at " + index);
			writer.close();
			System.out.println("FINISH");
			}
	
		
		
	}
