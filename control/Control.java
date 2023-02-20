package control;

import java.io.File;
import java.util.Scanner;

import proceso.Archivador;

class Control {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("ingrese la ruta de la carpeta:");
        String rutaCarpeta = sc.nextLine();
        System.out.println("ingrese la palabra buscar");
        String palabra = sc.nextLine();
        Archivador contadorPalabra = new Archivador();
        sc.close();
        File carpeta = new File(rutaCarpeta);
        if (carpeta.exists()) {
            int [] resultados = contadorPalabra.leerCarpeta(carpeta, palabra);
            if (contadorPalabra.getHayArchivos()){
                for (int i = 0; i < resultados.length-1; i++) {
                    System.out.println("total apariciones en el archivo "+ (i+1)+ ": "+ resultados[i]);
                }
                System.out.println("Total apariciones en carpeta: "+ resultados[resultados.length-1]);
            }else {
                System.out.println("La carpeta no contiene archivos de texto");
            }
        } else {
            System.out.println("la carpeta especificada en la ruta no existe");
        }

    }
}