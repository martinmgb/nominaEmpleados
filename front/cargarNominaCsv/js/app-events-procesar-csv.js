document.addEventListener('DOMContentLoaded', function() {

    // Procesar CSV
    const form = document.getElementById('formProcesarCsv');
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        procesarCsv();
    });
});

// Función para agregar un nuevo empleado
function procesarCsv() {
    const generalMensaje = document.getElementById('generalMensaje');
    generalMensaje.textContent = 'Procesando...';
    generalMensaje.style.display = "inline";
    // Obtener el archivo CSV del input
    const fileInput = document.getElementById('csvFile');
    const fileError = document.getElementById('csvFileError');
    const file = fileInput.files[0];

    // Comprobar si hay un archivo seleccionado
    if (!file) {
        fileError.textContent = "Debe seleccionar archivo csv para procesar.";
        fileError.style.display = "inline";
        fileInput.classList.add("error");
        return;
    }

    // Crear un objeto FormData
    const formData = new FormData();
    formData.append('csvFile', file); // 'csvFile' es el nombre del campo que recibirá el archivo en el servidor

    fetch('http://localhost:8080/nominaEmpleadosBack/api/nominas/calcular', {
            method: 'POST',
            body: formData
        })
        .then(response => response.json())
        .then(result => {
            const divTabla = document.getElementById('empleados');
            divTabla.style.display = "block";
            const tabla = document.getElementById('tablaEmpleados').getElementsByTagName('tbody')[0];
            tabla.innerHTML = '';  // Limpiar la tabla antes de agregar nuevas filas
            result.data.forEach(empleado => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${empleado.nombre}</td>
                    <td>${empleado.apellido}</td>
                    <td>${empleado.rut.numero}-${empleado.rut.digitoVerificador}</td>
                    <td>${empleado.cargo}</td>
                    <td>${empleado.salarioBase}</td>
                    <td>${empleado.bono}</td>
                    <td>${empleado.descuento}</td>
                    <td>${empleado.salarioTotal}</td>
                    <td>${empleado.estatus}</td>
                    <td>${empleado.observacion}</td>
                `;
                tabla.appendChild(tr);
            });
            generalMensaje.textContent = 'Carga Terminada';
        })
        .catch(error => {
            const generalError = document.getElementById('generalError');
            generalError.textContent = "Error al procesar archivo: "+error.message;
            generalError.style.display = "inline";
        });
}