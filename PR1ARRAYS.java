import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class PR1ARRAYS {
    public static void main(String[] args){
        File file = new File("./Medellin Agosto 2022.csv");
        int columnas = contarColumnas(file);
        int filas = contarLineas(file);
        String[][] aloj = new String[filas][columnas];

        Scanner scanner;
        try {
            int fila = 0;
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                    for(int i=0; i<fields.length; i++){
                            aloj[fila][i] = fields[i];
                    }
                fila++;
            }

            scanner.close();
            
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

    imprimir(aloj);

    for(int e = 2;e<aloj.length;e++) {
        System.out.println(contarVacios(e, aloj));
    }
    for(int e = 2;e<aloj.length;e++) {
        System.out.println(contarNulos(e, aloj));
    }
    
    System.out.println("El mayor número de la columna 6 es " + getMaxValueInColumn(aloj,4));

}
public static float getMaxValueInColumn(String[][] matrix, float column) {
    float maxValue = 0;

    for (int i = 1; i < matrix.length; i++) {
        String valueString = matrix[i][(int)column];
        if (valueString != null && !valueString.isEmpty()) {
            float value = Float.parseFloat(valueString);
            if (value > maxValue) {
                maxValue = value;
            }
        }
    }

    return maxValue;
}

    public static int contarColumnas(File archivo){
        Scanner scanner;
        int columnas = 0;
        try {
            scanner = new Scanner(archivo);
            columnas = 0;
            if (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                columnas = fields.length;
            }
            scanner.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return columnas;
    }

    public static int contarLineas(File archivo){
        Scanner scanner;
        int totalLineas = 0;
        try {
            scanner = new Scanner(archivo);
            totalLineas = 0;
            while (scanner.hasNextLine()){
                totalLineas = totalLineas+ 1;
                scanner.nextLine(); // le decimos que siga a la sgnte linea
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return totalLineas;
    }


    public static void imprimir(String[][] miMatriz){
      for(int i =0; i<miMatriz.length; i++){
        for(int j=2; j<miMatriz[i].length; j++) // el length de la fila actual.
        {
          System.out.print(miMatriz[i][j] + "\t");
        }
        System.out.println();
      }
    }

    public static String contarNulos(int columna, String[][] matriz) {
        int variable = 0;
        for (int i = 0;i<matriz.length;i++) {
            if (matriz[i][columna] == null) {
                variable+=1;
            }
        }
        return "La columna " + matriz[0][columna] + " tiene " + variable + " nulos.";
    }

    public static String contarVacios(int columna, String[][] matriz) {
        int contadorVac = 0;
        for (int i = 0;i<matriz.length;i++) {
            if (matriz[i][columna].isEmpty() || matriz[i][columna].equals("")) {
                contadorVac+=1;
            }
        }
        return "La columna " + matriz[0][columna] + " tiene " + contadorVac + " vacios."; 
    
    }
}
