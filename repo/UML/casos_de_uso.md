# Casos de Uso — Sistema de Gestión de Taxis Multizona

## Actores

| Actor | Descripción |
|-------|-------------|
| **Cliente** | Usuario que solicita un servicio de taxi |
| **Operador** | Empleado de la cooperativa que gestiona solicitudes |
| **Conductor** | Taxista que presta el servicio; puede enviar reportes |
| **Sistema** | Actor secundario — ejecuta lógica automática (asignación, cálculo de tarifa) |

---

## Diagrama de casos de uso (texto)

```
╔══════════════════════════════════════════════════════════════════════╗
║              SISTEMA DE GESTIÓN DE TAXIS MULTIZONA                   ║
║                                                                      ║
║  [Cliente]──────────────► (Hacer solicitud de taxi)                 ║
║                                    │                                 ║
║                           <<include>>│                               ║
║                                    ▼                                 ║
║                           (Seleccionar tipo de servicio)             ║
║                           (Seleccionar zona origen/destino)          ║
║                                                                      ║
║  [Cliente]──────────────► (Cancelar solicitud propia)               ║
║  [Cliente]──────────────► (Ver solicitudes atendidas)               ║
║                                                                      ║
║  [Operador]─────────────► (Listar solicitudes en espera)            ║
║  [Operador]─────────────► (Atender siguiente solicitud)             ║
║                                    │                                 ║
║                           <<include>>│                               ║
║                                    ▼                                 ║
║                           (Verificar conductor disponible)           ║
║                           (Verificar conectividad vial)              ║
║                           (Calcular tarifa y tiempo)                 ║
║                                                                      ║
║  [Operador]─────────────► (Cancelar solicitud de cliente)           ║
║  [Operador]─────────────► (Finalizar servicio activo)               ║
║  [Operador]─────────────► (Ver historial de solicitudes)            ║
║  [Operador]─────────────► (Gestionar conductores)                   ║
║                                    │                                 ║
║                   ┌────────────────┼────────────────┐               ║
║                   ▼                ▼                ▼               ║
║           (Agregar cond.) (Habilitar servicio) (Ver cond.)          ║
║                                                                      ║
║  [Operador]─────────────► (Gestionar red vial)                      ║
║                                    │                                 ║
║                   ┌────────────────┼─────────┐                      ║
║                   ▼                ▼         ▼                      ║
║           (Ver zonas) (Ver conexiones) (Hab/Desh. conexión)         ║
║                                                                      ║
║  [Conductor]────────────► (Enviar reporte de vía)                   ║
║                                                                      ║
╚══════════════════════════════════════════════════════════════════════╝
```

---

## Descripción textual de casos de uso

---

### CU-01: Hacer solicitud de taxi

**Actor principal:** Cliente  
**Actores secundarios:** Sistema  
**Precondiciones:** El sistema está en ejecución. El cliente accede al menú de clientes.  
**Postcondiciones:** La solicitud queda registrada en la cola de espera con estado "En espera".

**Flujo principal:**
1. El cliente selecciona "Hacer Solicitud" en el menú.
2. El sistema solicita el nombre del cliente.
3. El sistema muestra los tipos de servicio disponibles (Estándar, Baúl, Mascotas).
4. El cliente selecciona el tipo de servicio.
5. El sistema muestra las zonas disponibles.
6. El cliente selecciona la zona de origen.
7. El cliente selecciona la zona de destino.
8. El sistema genera un ID de solicitud y registra fecha/hora.
9. El sistema agrega la solicitud a la cola con estado "En espera".
10. El sistema confirma el registro mostrando el ID asignado.

**Flujos alternos:**
- *4a.* El cliente ingresa una opción inválida → el sistema muestra el error y vuelve al paso 3.
- *6a / 7a.* El cliente ingresa un número de zona inválido → el sistema muestra el error y repite el paso.

---

### CU-02: Atender siguiente solicitud

**Actor principal:** Operador  
**Actores secundarios:** Sistema  
**Precondiciones:** Existe al menos una solicitud en la cola de espera.  
**Postcondiciones:** La solicitud pasa a estado "ATENDIDA"; el conductor queda como no disponible.

**Flujo principal:**
1. El operador selecciona "Atender siguiente solicitud".
2. El sistema toma la primera solicitud de la cola (FIFO).
3. El sistema obtiene el tipo de servicio requerido.
4. El sistema busca un conductor disponible habilitado para ese tipo.
5. El sistema verifica que exista conectividad vial entre la zona de origen y la zona del conductor.
6. El sistema calcula la tarifa estimada usando Dijkstra (kilómetros) y el Factory Method de tarifas.
7. El sistema calcula el tiempo estimado usando Dijkstra (tiempo).
8. El sistema asigna el conductor y actualiza su estado a no disponible.
9. El sistema muestra: placa, nombre, ID del conductor, tarifa estimada y tiempo estimado.
10. La solicitud se mueve al historial con estado "ATENDIDA".

**Flujos alternos:**
- *2a.* La cola está vacía → el sistema informa "No hay solicitudes en espera".
- *4a.* No hay conductores disponibles para el tipo de servicio → el sistema informa el error y no atiende la solicitud.
- *5a.* No existe conectividad vial habilitada → el sistema informa el error y no atiende la solicitud.

---

### CU-03: Cancelar solicitud

**Actor principal:** Cliente u Operador  
**Actores secundarios:** Sistema  
**Precondiciones:** La solicitud existe en la cola con estado "En espera".  
**Postcondiciones:** La solicitud se elimina de la cola; el motivo queda registrado.

**Flujo principal:**
1. El actor ingresa el ID de la solicitud a cancelar.
2. El actor ingresa el motivo de cancelación.
3. El sistema busca la solicitud en la cola por ID.
4. El sistema cambia el estado a "CANCELADA" y registra el motivo.
5. El sistema elimina la solicitud de la cola.
6. El sistema confirma la cancelación.

**Flujos alternos:**
- *3a.* La solicitud no existe o ya fue procesada → el sistema informa "No se encontró la solicitud".

---

### CU-04: Finalizar servicio activo

**Actor principal:** Operador  
**Actores secundarios:** Sistema  
**Precondiciones:** El conductor está en estado no disponible (en servicio).  
**Postcondiciones:** El conductor queda disponible nuevamente.

**Flujo principal:**
1. El operador selecciona "Finalizar servicio".
2. El operador ingresa el ID del conductor.
3. El sistema busca el conductor por ID.
4. El sistema cambia el estado del conductor a disponible.
5. El sistema confirma la liberación del conductor.

**Flujos alternos:**
- *3a.* No existe conductor con ese ID → el sistema informa el error.

---

### CU-05: Habilitar / deshabilitar conexión vial

**Actor principal:** Operador  
**Actores secundarios:** Sistema  
**Precondiciones:** El menú de gestión vial está activo.  
**Postcondiciones:** El estado de la conexión cambia; el cambio se persiste.

**Flujo principal:**
1. El operador selecciona "Habilitar/Deshabilitar Conexión".
2. El sistema muestra la lista de conexiones con su estado actual.
3. El operador selecciona el número de la conexión.
4. El sistema invierte el estado (habilitada ↔ deshabilitada).
5. El sistema informa el nuevo estado de la conexión.

**Flujos alternos:**
- *3a.* El operador ingresa un índice fuera de rango → el sistema informa "Índice inválido".

---

### CU-06: Gestionar conductores

**Actor principal:** Operador  
**Actores secundarios:** Sistema  
**Precondiciones:** El menú de gestión de conductores está activo.  
**Postcondiciones:** El conductor queda registrado o actualizado y se persiste.

**Sub-casos:**

**CU-06a — Agregar conductor:**
1. El operador ingresa nombre, marca y placa del vehículo.
2. El sistema genera un ID automático (C-N).
3. El sistema guarda el conductor en `conductores.txt`.

**CU-06b — Habilitar servicio a conductor:**
1. El operador selecciona el conductor por número de lista.
2. El operador selecciona el tipo de servicio a habilitar.
3. El sistema agrega el tipo al conductor y persiste los cambios.

---

### CU-07: Enviar reporte de vía

**Actor principal:** Conductor  
**Actores secundarios:** Sistema  
**Precondiciones:** El conductor accede al menú de conductores.  
**Postcondiciones:** El reporte queda guardado en `reportes.txt`.

**Flujo principal:**
1. El conductor selecciona "Hacer Reporte".
2. El conductor escribe el motivo del reporte.
3. El sistema guarda el reporte en el archivo de persistencia.
4. El sistema confirma el envío.

---

### CU-08: Ver historial de solicitudes

**Actor principal:** Operador  
**Actores secundarios:** Sistema  
**Precondiciones:** El sistema tiene solicitudes en el historial.  
**Postcondiciones:** —

**Flujo principal:**
1. El operador selecciona "Ver historial".
2. El sistema recupera todas las solicitudes del historial (cargadas desde archivo y en memoria).
3. El sistema muestra: ID, cliente, origen → destino, tipo, conductor, placa, tarifa, tiempo, estado.

**Flujos alternos:**
- *2a.* El historial está vacío → el sistema informa "No hay solicitudes atendidas en el historial".
