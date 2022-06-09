package entities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Controler.IrradiacaoNormalDireta;

public class carregaTabelaIrradiacao {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String dir="/home/ney/Downloads/Solar/DIRECT_NORMAL"; 
		String arq=dir+"/direct_normal_means.csv";
		String[] vet = new String[16]; 
		String result = null ; 
		//IrradiacaoNormalDireta file = new IrradiacaoNormalDireta(vet) ; 
		
		// Begin 
		
		BufferedReader buffRead = new BufferedReader(new FileReader(arq));
		String linha = "";
		while (true) {
			//if (linha != null) {
			//	System.out.println(linha);
            //System.out.println("Linha");
				linha = buffRead.readLine();
			if ( linha != null ) 
			{
				vet=linha.split(";");
			     //System.out.println(vet[1]);
				IrradiacaoNormalDireta file = new IrradiacaoNormalDireta(vet);
				//file(vet) ; 
				System.out.println(file.toString());
			}
		   else
		   {
			   break;
		   }
		}
		buffRead.close();
		
		//END 
        //System.out.println(arq );
	}

	private static void file(String[] vet) {
		// TODO Auto-generated method stub
		
	}

}
