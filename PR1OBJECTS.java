import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PR1OBJECTS {
    public static void main(String[] args) {
        File file = new File("./Medellin Agosto 2022.csv");

        CSVAnalyzer analyzer = new CSVAnalyzer(file);
        analyzer.analyze();
    }

        
}


class CSVAnalyzer {
    private String[][] data;
    private File file;

    public CSVAnalyzer(File file) {
        this.file = file;
        this.data = loadData();
    }

    public void analyze() {
        imprimir(data);
        System.out.println("<----------------------------------------------------------->");
        for(int e = 2;e<data[0].length;e++) {
            System.out.println(contarNulosYVacios(e, data));
        }
        System.out.println("<----------------------------------------------------------->");
        for(int e = 3;e<data[0].length;e++) {
            System.out.println(maxPerColumn(e, data));
        }
        System.out.println("<----------------------------------------------------------->");
        
    }

    private String[][] loadData() {
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

        return aloj;
    }

    private int contarColumnas(File archivo){
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

    private int contarLineas(File archivo){
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


    private void imprimir(String[][] miMatriz){
        for(int i =0; i<miMatriz.length; i++){
            for(int j=2; j<miMatriz[i].length; j++) // el length de la fila actual.
            {
                System.out.print(miMatriz[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private String contarNulosYVacios(int columna, String[][] matriz) {
        int variable = 0;
        int contadorVac = 0;
        for (int i = 0;i<matriz.length;i++) {
            if (matriz[i][columna] == null) {
                variable+=1;
            }
            else if (matriz[i][columna].isEmpty() || matriz[i][columna].equals("")) {
                contadorVac+=1;
            }
        }
        return "La columna " + matriz[0][columna] + " tiene " + variable + " nulos"+ " y tiene " + contadorVac + " vacios.";
    }

    private String maxPerColumn(int columna, String[][] matriz){
        float max = 0;
        
        for (int i = 1;i<matriz.length;i++) {
            if(matriz[i][columna] != null && !matriz[i][columna].isEmpty() && !matriz[i][columna].equals("")){
                String datostring = matriz[i][columna];
                String numstring = "";
                for (int j = 0; j < datostring.length(); j++) {
                    char c = datostring.charAt(j);
                    if ((c >= '0' && c <= '9') || c == '.') {
                        numstring += c;
                    }           
                }
                if (numstring.length() > 0) {
                    float numActual = Float.parseFloat(numstring);

                    if (numActual > max) {
                        max = numActual;
                    }
                }
                
            }
        }
        float count = max;
        float min = 0;
        for (int i = 1;i<matriz.length;i++) {
            if(matriz[i][columna] != null && !matriz[i][columna].isEmpty() && !matriz[i][columna].equals("")){
                String datostring = matriz[i][columna];
                String numstring = "";
                for (int j = 0; j < datostring.length(); j++) {
                    char c = datostring.charAt(j);
                    if ((c >= '0' && c <= '9') || c == '.') {
                        numstring += c;
                    }           
                }
                if (numstring.length() > 0) {
                    float numActual = Float.parseFloat(numstring);

                    if (numActual < count) {
                        min = numActual;
                        count = numActual;
                    }
                }
                
                
            }
        }
        return "La columna " + matriz[0][columna] + " tiene como número máximo " + max + " y " + min + " como número mínimo.";

        
    }
}
