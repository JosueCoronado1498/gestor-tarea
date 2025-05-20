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

    public boolean marcarComoCompletada(String titulo) {
        Tarea tarea = buscarTarea(titulo);
        if (tarea != null && !tarea.isEstado()) {
            tarea.marcarComoCompletada();
            return true;
        }
        return false;
    }

}
