package com.example.proyectoofertastrabajo.screens

data class Empresa(
    val id: Int,
    val nombre: String,
    val descripcion: String?,
    val direccion: String?,
    val telefono: String?,
    val email_contacto: String?,
    val sitio_web: String?,
    val sector: String?,
    val fecha_creacion: String?,
    val fecha_actualizacion: String?
)

data class Convocatorias(
    val id: Int,
    val titulo: String,
    val descripcion: String?,
    val fecha_inicio: String,
    val fecha_fin: String,
    val ubicacion: String?,
    val salario: String?,
    val requisitos: String?,
    val fecha_publicacion: String?,
    val estado: String,  // Abierta, Cerrada, En revisión
    val tipo_contrato: String,  // Tiempo completo, Medio tiempo, Por proyecto, Freelance
    val nivel_requerido: String,  // Básico, Intermedio, Avanzado
    val categoria: String?,
    val empresa_id: Int,  // Relación con la tabla empresas
    val contacto_email: String?,
    val fecha_actualizacion: String?,
    val empresa: Empresa?  // Información de la empresa relacionada
)
