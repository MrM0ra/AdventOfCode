import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Day2 {
    
	public static ArrayList<ArrayList<Integer>> matrix(ArrayList<Integer> arr) {
		ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
		ArrayList<Integer> result = new ArrayList<Integer>();
		ArrayList<Integer> result2 = new ArrayList<Integer>();
		int dec=0;
		int inc=0;
		int equ=0;
		int dif=0;
		int indexDec = -99;
		int indexInc = -99;
		int indexEqu = -99;
		int indexDif = -99;
		for(int i=0;i<=arr.size()-2;i++) {
				if(Math.abs(arr.get(i) - arr.get(i+1)) > 3) {
					dif++;
					indexDif = i==0?0:i+1;
				}else if(arr.get(i) == arr.get(i+1)) {
					equ++;
					indexEqu = i;
				}else if(arr.get(i) < arr.get(i+1)) {
					inc++;
					indexInc = i;
				}else if(arr.get(i) > arr.get(i+1)) {
					dec++;
					indexDec = i+1;
				}
		}
		result.add(dec);
		result.add(inc);
		result.add(equ);
		result.add(dif);
		result2.add(indexDec);
		result2.add(indexInc);
		result2.add(indexEqu);
		result2.add(indexDif);
		matrix.add(result);
		matrix.add(result2);
		return matrix;
	}
	
	public static ArrayList<Integer> fixReport(ArrayList<Integer> arr, int pos){
		arr.remove(pos);
		return arr;
	}
	
	public static int countOcurrences(String chain, String chara) {
		int posicion, contador = 0;
        posicion = chain.indexOf(chara);
        while (posicion != -1) {
            contador++;
            posicion = chain.indexOf(chara, posicion + 1);
        }
        return contador;
	}
	
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> reports = new ArrayList<>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("input2.txt"));
			String report = reader.readLine();
			while(report!=null) {
				ArrayList<Integer> reportArrayList = Arrays.stream(report.split(" ")).map(
						e -> Integer.parseInt(e)).collect(Collectors.toCollection(ArrayList::new));
				ArrayList<ArrayList<Integer>> result = Day2.matrix(reportArrayList);
				System.out.println(result);
				System.out.println("---------"+reportArrayList+"---------");
				if(result.get(0).contains(reportArrayList.size()-1)) {
					reports.add(reportArrayList);
				}else if(
						Collections.frequency(result.get(0), (Integer)0)==2 && 
						Collections.frequency(result.get(0), (Integer)1)==1) {
					int errorIndex = result.get(1).get(result.get(0).indexOf( (Integer) 1));
					ArrayList<Integer> removedError = Day2.fixReport(reportArrayList, errorIndex);
					ArrayList<ArrayList<Integer>> result2 = Day2.matrix(removedError);
					System.out.println("Removed Error: "+removedError);
					System.out.println("Result2: "+result2);
					if(result2.get(0).contains(removedError.size()-1)) {
						reports.add(removedError);
					}
				}
				report = reader.readLine();
			}
			System.out.println(reports);
			System.out.println(reports.size());
			reader.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
    }
}
