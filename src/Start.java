import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Scanner;

public class Start {

	
	//Funcion Leer
	public static String LeerFicheroTexto (String url) 
	{
		//leer el fichero y mostrar
				String lineaTextoFichero="";
				String totalLineas="";
				File f2=null;
				FileReader fr=null;
				BufferedReader br=null;
				
				f2 = new File(url);
				
				try {
					fr=new FileReader(f2);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				br= new BufferedReader(fr);
			
				try {
					while ( (lineaTextoFichero= br.readLine()) !=null) {			
						totalLineas = totalLineas + "\n"+ lineaTextoFichero;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return totalLineas;
	
	}
	
	
	//Funcion Escribir
	public static void EscribirFicheroTexto (String url, String texto, boolean tipo) 
	{
		//Escribir y crear el fichero
		FileWriter f=null;
		PrintWriter pw=null;
		
		try {
			f = new FileWriter(url,tipo);//f = new FileWriter("./src/fichero.txt,true"); es en modo añadir mas texto 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		pw = new PrintWriter(f);
		pw.println(texto);
	
		try {
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Ruta absoluta del fichero
		//C:\Users\MR. Robot\eclipse-workspace\GestionFicheros_Texto_java\src\Ejemplo.txt
	
		//Ruta relativa
		//./src/Ejemplo.txt
		
		Scanner sc = new Scanner(System.in);
		
		File[] listaficheros;
		String operacion="";
		String ruta="";
		String textoAEscribir="";
		String textoLeido="";
		boolean tipo=false;
		int i=0;
		
		do {
			System.out.println("---------------------------------------------");
			System.out.println("-------------GESTIÓN DE FICHEROS-------------");
			System.out.println("---------------------------------------------\n");
			System.out.println("Listado de operaciones\n");
			
			System.out.println("P) Listar directorio:\n");
			
			System.out.println("C) Crear una carpeta");
			System.out.println("D) Borrar una carpeta\n");
			
			System.out.println("E) Escribir un fichero:");
			System.out.println("L) Leer un fichero:");
			System.out.println("B) Borrar un fichero:\n");
			
			System.out.println("S) Salir del programa:\n");
			System.out.println("Seleccione la operacion a realizar:");
			operacion = sc.next();
			
			if(operacion.equals("c"))
			{
				//Crear carpeta
				System.out.println("Indique la ubicacion de la carpeta a crear (ruta):");
				ruta = sc.next();
				
				File carpeta =new File(ruta);
				
				if (carpeta.exists()) 
				{
					System.out.println("*******La carpeta ya existe*******\n");
				}
				else
				{
					if  (carpeta.mkdir())
					{
						System.out.println("*******La carpeta se ha creado correctamente*******\n");
					}
					else
					{
						System.out.println("*******Error al crear la carpeta*******\n");
					}
				}
			}
			
			else if(operacion.equals("p"))
			{
				System.out.println("Indique la ubicacion de la carpeta a crear (ruta):");
				ruta = sc.next();

				File carpeta =new File(ruta);
				
				if (carpeta.exists()) 
				{
					listaficheros = carpeta.listFiles();
					for (i=0; i< listaficheros.length; i++)
					{
						if (listaficheros[i].isDirectory())
						{
							System.out.println("Directorio )");
						}
						else
						{
							System.out.println("Fichero )");
						}
						System.out.println(listaficheros[i].getName()+"\n");
					}
					System.out.println("*******Fin listado*******\n");
				}
				else
				{
					System.out.println("*******La carpeta no existe*******\n");
				}
			}
			
			else if(operacion.equals("d"))
			{
				//borrar carpeta
				System.out.println("Indique la ubicacion de la carpeta a borrar (ruta):");
				ruta = sc.next();
				
				File carpeta =new File(ruta);
				
				if (carpeta.exists()) 
				{
					if (carpeta.delete())
					{
						System.out.println("*******La carpeta se ha borrado correctamente*******\n");
					}
					else
					{
						System.out.println("*******No se ha podido borrar la carpeta*******\n");
					}
				}
					
				else
				{
					System.out.println("*******La carpeta no existe*******\n");
				}
			}
			
			else if(operacion.equals("b"))
			{
				System.out.println("Indique la ubicacion del fichero (ruta):");
				ruta = sc.next();
				
				File f =new File(ruta);
				
				if (!f.exists()) 
				{
					System.out.println("*******El fichero no existe*******\n");
				}
				else
				{
					if (f.delete())
					{	
						System.out.println("*******Fichero borrado correctamente*******\n");
					}
					else
					{
						System.out.println("*******Fichero no se ha podido borrar*******\n");
					}
				}
					
			}
			
			else if(operacion.equals("e"))
			{
				System.out.println("Opciones de escritura");
				System.out.println("M) Machacar:");
				System.out.println("A) Añadir:\n");
				System.out.println("Seleccione la operacion a realizar:");
				operacion = sc.next();
				
				System.out.println("Indique la ubicacion del fichero (ruta):");
				ruta = sc.next();
				
				System.out.println("Introduzca el texto a escribir:");
				sc.nextLine();
				textoAEscribir=sc.nextLine();
				
				if(operacion.equals("a"))
				{
					tipo=true;
				}
				
				//Existe el fichero??
				File f =new File(ruta);
				
				if (!f.exists()) 
				{
					System.out.println("*******Fichero Creado*******\n");
				}
				EscribirFicheroTexto(ruta, textoAEscribir, tipo);
				if(operacion.equals("a"))
				{
					System.out.println("*******Texto añadido correctamente*******\n");
				}
				else
				{
					System.out.println("*******Texto machacado correctamente*******\n");	
				}
			}
					
				
				
			else if (operacion.equals("l"))
			{
				System.out.println("Indique la ubicacion del fichero (ruta):");
				ruta = sc.next();
				
				//Existe el fichero??
				File f =new File(ruta);
				
				if (f.exists()) {
				
				textoLeido =LeerFicheroTexto (ruta);
				System.out.println("El texto leido del fichero es: ");
				System.out.println(textoLeido+"\n");
				System.out.println("*******Fin del fichero*******\n");
				}
				else
				{
					System.out.println("*******El fichero no existe*******\n");
				}
			}
			else if (!operacion.equals("s"))
			{
				
				System.out.println("*******Operación errónea*******\n");
			}
			
			
		} while (!operacion.equals("s"));
		
		System.out.println("Salió del programa");
		
	
	}	
	
}
