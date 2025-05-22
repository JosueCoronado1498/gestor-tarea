import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Gestor gestor = new Gestor();
        gestor.cargarDesdeArchivo("tareas.txt");
        Scanner teclado = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n===== GESTOR DE TAREAS =====");
            System.out.println("\n1. Agregar tarea\n2. Mostrar tareas\n3. Buscar tarea\n" +
                    "4. Eliminar tarea\n5. Marcar tarea como completada\n0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = teclado.nextInt();
            teclado.nextLine(); //Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.println("Id: ");
                    Long id = teclado.nextLong();
                    teclado.nextLine(); //Limpio el buffer despues del Long
                    System.out.println("Titulo: ");
                    String titulo = teclado.nextLine();
                    System.out.println("Descripcion: ");
                    String descripcion = teclado.nextLine();
                    System.out.println("Fecha limite: ");
                    String fechaLimite = teclado.nextLine();
                    gestor.agregar(new Tarea(id,titulo, descripcion, fechaLimite, false));
                    break;
                case 2:
                    gestor.mostrarTodo();
                    break;
                case 3:
                    System.out.println("Buscar por nombre: ");
                    Tarea encontrada = gestor.buscarTarea(teclado.nextLine());
                    System.out.println(encontrada != null ? encontrada : "No encontrada");
                    break;
                case 4:
                    System.out.println("Tarea a eliminar: ");
                    boolean eliminada = gestor.eliminar(teclado.nextLine());
                    System.out.println(eliminada ? "Eliminada." : "No encontrada");
                    break;
                case 5:
                    System.out.println("Ingrese ID de tarea a marcar como completada: ");
                    Long idCompletar = teclado.nextLong();
                    teclado.nextLine();
                    boolean completada = gestor.marcarComoCompletada(idCompletar);
                    System.out.println(completada ? "Completada." : "No encontrada");
                    break;
            }

        } while (opcion != 0);

        teclado.close();

        gestor.guardarEnArchivo("tareas.txt");
        System.out.println("Cerrando programa...");

    }
}