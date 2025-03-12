document.addEventListener('DOMContentLoaded', function() {
    // Cargar empleados al cargar la página
    cargarEmpleados();

    // Agregar nuevo empleado
    const form = document.getElementById('formAgregarEmpleado');
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        agregarEmpleado();
    });
});

// Función para cargar los empleados
function cargarEmpleados() {
    fetch('http://localhost:8080/nominaEmpleadosBack/api/empleados')  // Suponiendo que esta es la URL del endpoint para obtener empleados
        .then(response => response.json())
        .then(result => {
            const tabla = document.getElementById('tablaEmpleados').getElementsByTagName('tbody')[0];
            tabla.innerHTML = '';  // Limpiar la tabla antes de agregar nuevas filas
            result.data.forEach(empleado => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${empleado.id}</td>
                    <td>${empleado.nombre}</td>
                    <td>${empleado.apellido}</td>
                    <td>${empleado.rut.numero}-${empleado.rut.digitoVerificador}</td>
                    <td>${empleado.cargo}</td>
                    <td>${empleado.salarioBase}</td>
                    <td>${empleado.bono}</td>
                    <td>${empleado.descuento}</td>
                    <td>${empleado.salarioTotal}</td>
                    <td><button onclick="eliminarEmpleado(${empleado.id})">Eliminar</button></td>
                `;
                tabla.appendChild(tr);
            });
        })
        .catch(error => {
            const generalError = document.getElementById('generalError');
            generalError.textContent = "Error al procesar archivo: "+error.message;
            generalError.style.display = "inline";
        });
}

// Función para agregar un nuevo empleado
function agregarEmpleado() {
    if(validateForm()){
        const nombre = document.getElementById('nombreInput').value;
        const apellido = document.getElementById('apellidoInput').value;
        const rut = document.getElementById('rutInput').value.replace(/\./g, "").split('-');
        const numeroRut = rut[0];
        const digitoVerificador = rut[1];
        const cargo = document.getElementById('cargoInput').value;
        const salarioBase = document.getElementById('salarioBaseInput').value;
        const bono = document.getElementById('bonoInput').value;
        const descuento = document.getElementById('descuentoInput').value;

        const nuevoEmpleado = {
            nombre: nombre,
            apellido: apellido,
            rut: {
                numero: numeroRut,
                digitoVerificador: digitoVerificador
            },
            cargo: cargo,
            salarioBase: salarioBase,
            bono: bono,
            descuento: descuento
        };

        fetch('http://localhost:8080/nominaEmpleadosBack/api/empleados', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(nuevoEmpleado)
        })
        .then(response => response.json())
        .then(data => {
            console.log('Empleado agregado:', data);
            cargarEmpleados();  // Recargar la lista de empleados
        })
        .catch(error => {
            const generalError = document.getElementById('generalError');
            generalError.textContent = "Error al procesar archivo: "+error.message;
            generalError.style.display = "inline";
        });
    }
}

// Función para eliminar un empleado
function eliminarEmpleado(id) {
    fetch(`http://localhost:8080/nominaEmpleadosBack/api/empleados?id=${id}`, {
        method: 'DELETE',
    })
    .then(response => {
        if (response.ok) {
            console.log('Empleado eliminado');
            cargarEmpleados();  // Recargar la lista de empleados
        } else {
            console.error('Error al eliminar empleado');
        }
    })
    .catch(error => {
        const generalError = document.getElementById('generalError');
        generalError.textContent = "Error al procesar archivo: "+error.message;
        generalError.style.display = "inline";
    });
}

function validateRut(rut) {
    const rutPattern = /^(\d{1,3}(\.\d{3})*|\d+)-[0-9Kk]$/;
    const rutError = document.getElementById("rutError");
    const rutInput = document.getElementById("rutInput");
  
    if (!rutPattern.test(rut)) {
        rutError.textContent = "Rut debe ser válido. Ejm: 12345678-9";
        rutError.style.display = "inline";
        rutInput.classList.add("error");
        return false;
    } else {
        rutError.style.display = "none";
        rutInput.classList.remove("error");
        return true;
    }
}

function validateSalarioBase(salarioBase) {
    const salarioBaseError = document.getElementById("salarioBaseError");
    const salarioBaseInput = document.getElementById("salarioBaseInput");
  
    if (salarioBase<400000) {
        salarioBaseError.textContent = "El salario base debe ser mayor a los $400.000";
        salarioBaseError.style.display = "inline";
        salarioBaseInput.classList.add("error");
        return false;
    } else {
        salarioBaseError.style.display = "none";
        salarioBaseInput.classList.remove("error");
        return true;
    }
}

function validateBono(salarioBase, bono) {
    const bonoError = document.getElementById("bonoError");
    const bonoInput = document.getElementById("bonoInput");
  
    if (+bono > salarioBase*0.5) {
        bonoError.textContent = "El monto por bonos no debe ser mayor al 50% del salario base";
        bonoError.style.display = "inline";
        bonoInput.classList.add("error");
        return false;
    } else {
        bonoError.style.display = "none";
        bonoInput.classList.remove("error");
        return true;
    }
}

function validateDescuento(salarioBase, descuento) {
    const descuentoError = document.getElementById("descuentoError");
    const descuentoInput = document.getElementById("descuentoInput");
  
    if (+descuento > +salarioBase) {
        descuentoError.textContent = "El monto por descuentos no debe ser mayor al salario base";
        descuentoError.style.display = "inline";
        descuentoInput.classList.add("error");
        return false;
    } else {
        descuentoError.style.display = "none";
        descuentoInput.classList.remove("error");
        return true;
    }
}

function validateForm() {
    const rut = document.getElementById("rutInput").value;
    const salarioBase = document.getElementById("salarioBaseInput").value;
    const bono = document.getElementById("bonoInput").value;
    const descuento = document.getElementById("descuentoInput").value;
  
    const isRutValid = validateRut(rut);
    const isSalarioBaseValid = validateSalarioBase(salarioBase);
    const isBonoValid = validateBono(salarioBase, bono);
    const isDescuentoValid = validateDescuento(salarioBase, descuento);
  
    return isRutValid && isSalarioBaseValid && isBonoValid && isDescuentoValid;
}