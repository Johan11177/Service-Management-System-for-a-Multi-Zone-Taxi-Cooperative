# Sistema de Gestión de Servicios para una Cooperativa de Taxis Multizona

**Curso:** Programación Orientada a Objetos — 2026-I  
**Universidad del Magdalena**

---

## Descripción del problema y alcance

Una cooperativa de servicio público de taxis requiere un sistema para **recibir, gestionar y asignar solicitudes de servicio** considerando:

- Zonas geográficas de origen y destino conectadas por una red vial.
- Tipos de servicio: Taxi Estándar, Taxi con Baúl y Taxi para Mascotas.
- Disponibilidad de conductores habilitados por tipo de servicio.
- Condiciones dinámicas: las conexiones viales pueden habilitarse o deshabilitarse.
- Cálculo automático de tarifa y tiempo estimado usando el algoritmo de Dijkstra.

El sistema persiste su estado entre sesiones (conductores, solicitudes, historial y red vial).

---

## Requisitos funcionales implementados

| # | Requisito | Estado |
|---|-----------|--------|
| 1 | Recepción de solicitudes con zona origen/destino, tipo de servicio, fecha/hora y estado | ✅ |
| 2 | Cola de espera FIFO — listar, atender siguiente, cancelar con motivo | ✅ |
| 3 | Asignación automática: conductor disponible + tipo habilitado + conectividad vial | ✅ |
| 4 | Mostrar placa, nombre, ID conductor, tarifa estimada y tiempo estimado de llegada | ✅ |
| 5 | Cierre de servicio: cancelación con motivo o finalización exitosa | ✅ |
| 6 | Historial de solicitudes (estado final, conductor, tiempos, tarifa) | ✅ |
| 7 | Habilitar / deshabilitar conexiones viales | ✅ |
| 8 | Registro y persistencia de reportes de conductores | ✅ |
| 9 | Persistencia completa al iniciar y cerrar (conductores, cola, historial, red vial) | ✅ |
| 10 | Manejo de excepciones personalizadas | ✅ |

### Zonas del sistema (Santa Marta)

```
Aeropuerto ──15min──► Rodadero ──10min──► Centro Histórico ──8min──► Universidad ──6min──► Mamatoco
                                               │                                                │
                                             15min                                           18min
                                               ▼                                                ▼
                                            Taganga ◄──────────────────────────────────────────
```

### Tarifas

| Tipo | Base | Costo/km | Adicional |
|------|------|----------|-----------|
| Estándar | $5 000 | $1 000/km | — |
| Con Baúl | $5 000 | $1 000/km | +$2 000 |
| Mascotas | $5 000 | $1 000/km | +$3 000 |

---

## Arquitectura y principios de diseño

### Paquetes

```
pooproyect/
├── Conductores/        Conductor, GestorConductores, PersistenciaConductores
├── Excepciones/        Excepciones personalizadas
├── HistorialDeSolicitud/
├── Main/               EntradaUsuario (Singleton), Main
├── Menus/              Interfaces de usuario por rol
├── Persistencia/       PersistenciaSolicitudes, PersistenciaRedVial, GestorPersistencia
├── Reportes/
├── Servicio/
├── Solicitudes/        Solicitudes, SolicitudEnEspera, CrearSolicitud
├── Tarifas/            Interfaz Tarifa + Factory Method
├── TipoDeServicio/     Taxi (abstracto) + subclases
├── Usuario/            Usuario, Cliente
├── Vehiculo/
├── Zona/               Zonas, Conexiones, Grafo, Dijkstra, BFS
└── operador/
```

### Principios POO

| Principio | Aplicación |
|-----------|-----------|
| Abstracción | `Taxi`, `Grafo`, `FabricaTarifas` (clases abstractas); `Tarifa`, `InterfaceMenu` (interfaces) |
| Encapsulación | Todos los atributos `private` con getters/setters |
| Herencia | `TaxiEstandar/ConBaul/Mascotas` ← `Taxi`; `Conductor/Cliente` ← `Usuario`; `GrafoTiempo/GrafoKilometros` ← `Grafo` |
| Polimorfismo | `FabricaTarifas.crearTarifa()` resuelve en tiempo de ejecución a la tarifa correcta |

### Principios SOLID

1. **SRP:** `PersistenciaConductores`, `PersistenciaSolicitudes` y `PersistenciaRedVial` tienen una única responsabilidad de I/O cada una.
2. **OCP:** Añadir un tipo de taxi nuevo solo requiere crear una subclase de `Taxi` y una `Tarifa` — sin modificar código existente.
3. **LSP:** Las fábricas de tarifas y los tipos de taxi son sustituibles donde se espera su tipo base.

### Patrón de diseño: Factory Method

- **Problema:** Crear la tarifa correcta según el tipo de servicio sin acoplar `SolicitudEnEspera` a las implementaciones concretas.
- **Implementación:** `FabricaTarifas` (abstracta) → `FabricaTarifasNormal` / `FabricaTarifasEspecial`.
- **Justificación:** Respeta OCP — nuevos tipos de servicio no requieren modificar el código de asignación existente.

---

## Excepciones personalizadas

| Excepción | Cuándo se lanza |
|-----------|----------------|
| `ZonaInexistenteException` | Zona no registrada en el sistema |
| `ConductorNoHabilitadoException` | No hay conductor disponible para el tipo de servicio |
| `SinConectividadVialException` | No existe ruta habilitada entre origen y destino |
| `SolicitudInvalidaException` | Datos de solicitud incorrectos o incompletos |
| `CancelacionIndebidaException` | Intento de cancelar solicitud inexistente o ya procesada |
| `DatosIncompletosException` | Campo obligatorio vacío |

---

## Instrucciones de ejecución

### Prerrequisitos

- Java 17 o superior
- Maven 3.6+

### Compilar y ejecutar

```bash
cd poopr1
mvn compile
mvn exec:java -Dexec.mainClass="pooproyect.Main.Main"
```

O desde IDE (IntelliJ / Eclipse / VSCode): ejecutar `Main.java`.

### Archivos de datos generados

| Archivo | Contenido |
|---------|-----------|
| `conductores.txt` | Conductores y tipos habilitados |
| `historial.txt` | Solicitudes finalizadas o canceladas |
| `cola.txt` | Solicitudes pendientes al cerrar |
| `red_vial.txt` | Estado de habilitación de conexiones |
| `reportes.txt` | Reportes de conductores |

---

## Evidencias de prueba

### Flujo 1 — Atención exitosa de solicitud

```
[Cliente] Solicitud registrada con ID: 1 | Taxi Estandar | Aeropuerto → Universidad

[Operador] Atender siguiente solicitud...
--- SOLICITUD ATENDIDA ---
Operador: Operador Principal (ID 1)
Placa del vehículo: ABC-123
Nombre del conductor: Carlos Pérez
Identificación del conductor: C-1
Tarifa estimada del servicio: $33000.0
Tiempo estimado de llegada: 33 minutos
```

### Flujo 2 — Rechazo por conectividad deshabilitada

```
[Operador] Deshabilitar conexión Rodadero <-> Centro Histórico
[Cliente] Solicitud: Aeropuerto → Mamatoco
[Sistema] No existe conectividad vial habilitada entre 'Aeropuerto' y 'Mamatoco'.
```

### Flujo 3 — Persistencia entre sesiones

```
[Sesión 1] Registrar conductor C-1, registrar solicitud ID:1, cerrar sistema
[Sistema] Estado guardado correctamente.

[Sesión 2] Iniciar sistema
[Sistema] Estado anterior restaurado correctamente.
[Operador] Solicitudes en espera: ID:1 ...  ← la solicitud persiste
```

---

## Estructura del repositorio

```
/
├── poopr1/                         Proyecto Maven
│   └── src/main/java/pooproyect/
├── UML/
│   ├── diagrama_clases.md          Diagrama de clases completo
│   ├── casos_de_uso.md             Casos de uso con actores y flujos
│   └── diagramas_secuencia.md      Secuencias: Atender solicitud y Cerrar servicio
└── README.md
```

---

## Equipo de desarrollo

Proyecto final — Programación Orientada a Objetos 2026-I  
Universidad del Magdalena
