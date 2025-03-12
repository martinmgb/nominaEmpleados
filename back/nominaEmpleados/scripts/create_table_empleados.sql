CREATE TABLE empleados (
    id SERIAL PRIMARY KEY,            -- id autogenerado
    nombre VARCHAR(100) NOT NULL,     -- nombre del empleado
    apellido VARCHAR(100) NOT NULL,   -- apellido del empleado
    numero_rut BIGINT UNIQUE NOT NULL,   -- número de RUT unico
    digitoVerificador CHAR(1) NOT NULL,    -- digito verificador del RUT (1 carácter)
    cargo VARCHAR(100) NOT NULL,      -- cargo del empleado
    salario_base DECIMAL(10, 2) NOT NULL,  -- salario base del empleado
    bono DECIMAL(10, 2) DEFAULT 0.00,  -- bono del empleado
    descuento DECIMAL(10, 2) DEFAULT 0.00  -- descuento aplicado al salario
);