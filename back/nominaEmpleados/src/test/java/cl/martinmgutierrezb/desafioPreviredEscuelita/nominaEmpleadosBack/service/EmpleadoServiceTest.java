package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.service;

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

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.model.Empleado;

@ExtendWith(MockitoExtension.class)
public class EmpleadoServiceTest {

    @Mock
    private EmpleadoServiceImpl empleadoService;  // Instanciamos el servicio con el repositorio mockeado


    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerEmpleados() {
    	Empleado e1 = new Empleado();
        e1.setNombre("Juan");
        
        Empleado e2 = new Empleado();
        e2.setNombre("Pablo");
        List<Empleado> empleados = Arrays.asList(e1, e2);

        // Simulación de la llamada al método
        when(empleadoService.obtenerEmpleados()).thenReturn(empleados);

        // Cuando se llama al servicio para obtener la empleado
        List<Empleado> resultado = empleadoService.obtenerEmpleados();

        // Entonces, verificamos que la empleado obtenido sea el correcto
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Juan", resultado.get(0).getNombre());
    }

    @Test
    void testEliminarEmpleado() {
    	Long id = 1L;
    	
        when(empleadoService.eliminarEmpleado(id)).thenReturn(Boolean.TRUE);
        
        Boolean resultado = empleadoService.eliminarEmpleado(id);

        assertNotNull(resultado);
        assertEquals(Boolean.TRUE, resultado);
    }
}
