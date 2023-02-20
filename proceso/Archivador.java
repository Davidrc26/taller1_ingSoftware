package proceso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class Archivador {
    private List<String> extensionesValidas;
    private boolean hayArchivos;
    public Archivador(){
        this.extensionesValidas = Arrays.asList("txt", "csv", "xml", "json");
        this.hayArchivos = true;
    }
    public boolean getHayArchivos(){
        return this.hayArchivos;
    }
    /**
     * Recorre y lee todos los archivos de una carpeta especifica que tengan extensiones txt, json, csv o xml
     * @param carpeta carpeta a recorrer como objeto File 
     * @param palabraBuscar Palabra buscada
     * @return un arreglo de enteros con el numero de veces que aparece la palabra en cada uno de los archivos y el total en la ultima posicion del arreglo
     */
    public int[] leerCarpeta(File carpeta, String palabraBuscar) {
        String extension = "";
        File[] archivos = carpeta.listFiles();
        int [] contadores = new int[archivos.length + 1];
        int index = 0;
        int total= 0;
        int contadorArchivos= 0;
        for (File archivo : archivos) {
            String nombreArchivo = archivo.getName();
            int i = nombreArchivo.lastIndexOf('.');
            if (i > 0) {
                extension = nombreArchivo.substring(i + 1);
            }
            if (extensionesValidas.contains(extension)){
                contadorArchivos++;
                try (BufferedReader lector = new BufferedReader(new FileReader(archivo));){
                    
                    String line = "";
                    while((line = lector.readLine()) != null){
                       String [] palabras = line.split("\\W+");
                       for (String palabra : palabras ) {
                            if (palabra.equalsIgnoreCase(palabraBuscar)){
                                contadores[index]++;
                            };      
                       }
                    }
                    lector.close();
                    total += contadores[index];
                }catch(Exception e){
                    System.out.println("Error leyendo el archivo, detalles: "+ e.getMessage());
                } 
            }
            index++;
        }
        if (contadorArchivos== 0){
            this.hayArchivos = false;
        }

        contadores[contadores.length-1]= total;
        return contadores;
    }

    
}