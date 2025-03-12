package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.dao.EmpleadoDAO;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.model.Empleado;

@ExtendWith(MockitoExtension.class)
public class EmpleadoDAOTest {

    @Mock
    private EmpleadoDAO empleadoDAO;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    
    
    /**
     * Test de método que se encarga de buscar empleados registrados
     * 
     * @param id del empleado
     * @return Lista de empleados (List<Trabajador>)
     */
    @Test
    void testObetenerEmpleados() {
        // Datos de prueba
        Empleado e1 = new Empleado();
        e1.setNombre("Juan");
        
        Empleado e2 = new Empleado();
        e2.setNombre("Pablo");
        List<Empleado> empleados = Arrays.asList(e1, e2);

        // Simulación de la llamada al método
        when(empleadoDAO.obtenerEmpleados()).thenReturn(empleados);

        // Verificación de la interacción y aserciones
        List<Empleado> resultado = empleadoDAO.obtenerEmpleados();
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Juan", resultado.get(0).getNombre());
    }
}

