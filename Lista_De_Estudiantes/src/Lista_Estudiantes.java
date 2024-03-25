import java.io.*;
import java.util.*;

public class Lista_Estudiantes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashSet<String> conjuntoEstudiantes = new HashSet<>();
        LinkedList<String> listaEstudiantes = new LinkedList<>();

        //Punto a) Cargar la Lista de un fichero y almacenar en un HashSet
        cargarListaDesdeArchivo("C:\\Users\\usuario\\Downloads\\Programación II (1).txt", conjuntoEstudiantes);
        listaEstudiantes.addAll(conjuntoEstudiantes);
        System.out.println("Lista de estudiantes cargada.");


        boolean continuar = true;
        while (continuar) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Mostrar lista de estudiantes");
            System.out.println("2. Agregar nuevo estudiante");
            System.out.println("3. Remover estudiante");
            System.out.println("4. Editar estudiante");
            System.out.println("5. Buscar estudiante");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    // Punto b) Ordenar de forma ascendente la lista por nombre de estudiante y mostrar la lista ordenada
                    mostrarListaEstudiantesOrdenada(listaEstudiantes);
                    break;
                case 2:
                    // Punto c) Agregar un nuevo estudiante al final o al inicio
                    agregarNuevoEstudiante(scanner, conjuntoEstudiantes, 
                            listaEstudiantes);
                    break;
                case 3:
                    // Punto d) Remover estudiante
                    removerEstudiante(scanner, conjuntoEstudiantes, 
                            listaEstudiantes);
                    break;
                case 4:
                    // Punto d) Editar estudiante
                    editarEstudiante(scanner, conjuntoEstudiantes, 
                            listaEstudiantes);
                    break;
                case 5:
                    // Punto d) Buscar estudiante
                    buscarEstudiante(scanner, conjuntoEstudiantes);
                    break;
                case 6:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, "
                            + "seleccione una opción válida.");
            }
        }

    } 
    
    public static void cargarListaDesdeArchivo(String nombreArchivo, 
            HashSet<String> conjunto) {
        try (BufferedReader br = new BufferedReader(new FileReader
        (nombreArchivo))) {
            
            
            String linea;
            while ((linea = br.readLine()) != null) {
                conjunto.add(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo:" + e.getMessage());
        }
    }

    public static void mostrarListaEstudiantesOrdenada(LinkedList<String> 
            lista) {
        Collections.sort(lista);
        System.out.println("Lista de estudiantes:");
        for (String estudiante : lista) {
            System.out.println(estudiante);
        }
    }
//graga los estudiates  ls lista segun el carnet
    public static void agregarNuevoEstudiante(Scanner scanner, HashSet<String> 
            conjunto, LinkedList<String> lista) {
    System.out.println("Ingrese el nombre del nuevo estudiante:");
    String nuevoEstudiante = scanner.nextLine();
    
    // Verificar si el nombre ya está en la lista
    if (conjunto.contains(nuevoEstudiante)) {
        System.out.println("El estudiante ya está en la lista. No se agregará.");
        return;
    }
       lista.addFirst(nuevoEstudiante);   
}


    public static void removerEstudiante(Scanner scanner, HashSet<String> 
            conjunto, LinkedList<String> lista) {
        System.out.println("Ingrese el nombre completo con toda informacion "
                + "del estudiante a remover:");
        String estudianteARemover = scanner.nextLine();
        if (conjunto.remove(estudianteARemover)) {
            lista.remove(estudianteARemover);
            System.out.println("El estudiante fue removido");
        } else {
            System.out.println("El estudiante no fue encontrado en la lista.");
        }
    }
    
    public static void editarEstudiante(Scanner scanner, HashSet<String> 
            conjunto, LinkedList<String> lista) {
        System.out.println("Ingrese el nombre del estudiante al cual desea"
                + " editar la informcion");
        String estudianteAEditar = scanner.nextLine();
        System.out.println("Ingrese el nuevo nombre del estudiante:");
        String nuevoNombre = scanner.nextLine();
        if (conjunto.remove(estudianteAEditar)) {
            lista.remove(estudianteAEditar);
            conjunto.add(nuevoNombre);
            lista.add(nuevoNombre);
            System.out.println("Informacion de estudiante editada correctamente");
        } else {
            System.out.println("El estudiante no fue encontrado");
        }
    }

    public static void buscarEstudiante(Scanner scanner, HashSet<String> 
            conjunto) {
        System.out.println("Ingrese el nombre del estudiante que desea buscar:");
        String buscarEstudiante = scanner.nextLine().toLowerCase();
        boolean encontrado = false;
        for (String estudiante : conjunto) {
            if (estudiante.toLowerCase().contains(buscarEstudiante)) {
                System.out.println("Estudiante encontrado: " + estudiante);
                encontrado = true;
            }
        }
        if (!encontrado) {
           System.out.println("No se encontraron estudiantes con la información"
                    + " proporcionada.");
        }
    }

    public static void guardarListaEnArchivo(String nombreArchivo, 
            HashSet<String> conjunto) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter
        (nombreArchivo))) {
            for (String estudiante : conjunto) {
                bw.write(estudiante);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar en el archivo: " + 
                    e.getMessage());
        }
    }
}
