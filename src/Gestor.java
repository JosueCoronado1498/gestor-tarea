import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Gestor {

    private ArrayList<Tarea> tareas = new ArrayList<>();

    //metodos para agregar, listar, eliminar y marcar tarea como completada

    public void agregar(Tarea tarea) {
        if (buscarTarea(tarea.getTitulo()) == null) {
            tareas.add(tarea);
        } else {
            System.out.println("Ya existe esta tarea.");
        }
    }

    public Tarea buscarTarea(String titulo) {
        for (Tarea tarea : tareas) {
            if (tarea.getTitulo().equalsIgnoreCase(titulo)) {
                return tarea;
            }
        }
        return null;
    }

    public void mostrarTodo(){
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas pendientes.");
        } else {
            for (Tarea tarea : tareas) {
                System.out.println(tarea);
            }
        }
    }

    public boolean eliminar(String titulo) {
        Tarea tarea = buscarTarea(titulo);
        if (tarea != null) {
            tareas.remove(tarea);
            return true;
        }
        return false;
    }

    public boolean marcarComoCompletada(Long id) {
        for (Tarea tarea : tareas) {
            if (tarea.getId().equals(id)) {
                tarea.marcarComoCompletada();
                return true;
            }
        }
        return false;
    }


    public void guardarEnArchivo(String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            for (Tarea tarea : tareas) {
                String linea = tarea.getId() + ";" +
                        tarea.getTitulo() + ";" +
                        tarea.getDescripcion() + ";" +
                        tarea.getFechaLimite() + ";" +
                        tarea.isEstado() + "\n";
                writer.write(linea);
            }
            System.out.println("Tareas guardadas en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar archivo: " + e.getMessage());
        }
    }

    public void cargarDesdeArchivo(String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 5) {
                    Long id = Long.parseLong(partes[0]);
                    String titulo = partes[1];
                    String descripcion = partes[2];
                    String fechaLimite = partes[3];
                    boolean estado = Boolean.parseBoolean(partes[4]);
                    tareas.add(new Tarea(id, titulo, descripcion, fechaLimite, estado));
                }
            }
            System.out.println("Tareas cargadas desde " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Archivo no encontrado. Se empezará con una lista vacía.");
        }
    }

}
