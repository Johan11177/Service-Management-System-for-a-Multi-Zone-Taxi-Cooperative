# Diagramas de Secuencia — Sistema de Gestión de Taxis Multizona

---

## Diagrama 1: "Atender solicitud" (flujo exitoso)

```
Cliente      SystemUI    Operador    SolicitudEnEspera    GestorConductores    GestorConexionesRutas    Dijkstra    FabricaTarifas    Conductor
   │              │           │              │                     │                      │                  │              │               │
   │──solicitud──►│           │              │                     │                      │                  │              │               │
   │  (previa)    │           │              │                     │                      │                  │              │               │
   │              │           │              │                     │                      │                  │              │               │
   │              │ selecciona│              │                     │                      │                  │              │               │
   │              │"Atender   │              │                     │                      │                  │              │               │
   │              │siguiente" │              │                     │                      │                  │              │               │
   │              │──────────►│              │                     │                      │                  │              │               │
   │              │           │              │                     │                      │                  │              │               │
   │              │           │atenderSolici-│                     │                      │                  │              │               │
   │              │           │tud(nombre,id)│                     │                      │                  │              │               │
   │              │           │─────────────►│                     │                      │                  │              │               │
   │              │           │              │                     │                      │                  │              │               │
   │              │           │              │ cola.get(0)         │                      │                  │              │               │
   │              │           │              │─solicitud obtenida─►│                      │                  │              │               │
   │              │           │              │                     │                      │                  │              │               │
   │              │           │              │encontrarConductor   │                      │                  │              │               │
   │              │           │              │Disponible(tipo)     │                      │                  │              │               │
   │              │           │              │────────────────────►│                      │                  │              │               │
   │              │           │              │                     │ itera conductores    │                  │              │               │
   │              │           │              │                     │ disponible+tipo?     │                  │              │               │
   │              │           │              │◄── conductor ───────│                      │                  │              │               │
   │              │           │              │                     │                      │                  │              │               │
   │              │           │              │ new GrafoTiempo(gestorConexiones)          │                  │              │               │
   │              │           │              │────────────────────────────────────────────►                  │              │               │
   │              │           │              │◄── matriz[][] ─────────────────────────────                  │              │               │
   │              │           │              │                                                               │              │               │
   │              │           │              │ new Dijkstra(matriz)                                          │              │               │
   │              │           │              │───────────────────────────────────────────────────────────────►              │               │
   │              │           │              │                                                               │              │               │
   │              │           │              │ calcularRutaMasCorta(origenId, destinoId)                     │              │               │
   │              │           │              │───────────────────────────────────────────────────────────────►              │               │
   │              │           │              │◄── tiempoEstimado ────────────────────────────────────────────              │               │
   │              │           │              │                                                                              │               │
   │              │           │              │ crearTarifaPorTipo(tipo, dijkstra)                                           │               │
   │              │           │              │─────────────────────────────────────────────────────────────────────────────►               │
   │              │           │              │                                                                              │               │
   │              │           │              │◄── Tarifa (EstandaroBaulOMascotas) ─────────────────────────────────────────               │
   │              │           │              │                                                                                              │
   │              │           │              │ tarifa.CalcularTarifa(origenId, destinoId)                                                   │
   │              │           │              │─────────────────────────────────────────────────────────────────────────────────────────────►│
   │              │           │              │◄── costoEstimado ────────────────────────────────────────────────────────────────────────────│
   │              │           │              │                     │                                                                        │
   │              │           │              │ conductor.setDisponible(false)                                                               │
   │              │           │              │────────────────────►│                                                                        │
   │              │           │              │                                                                                              │
   │              │           │              │ solicitud.setEstado("ATENDIDA")                                                              │
   │              │           │              │ historial.add(solicitud)                                                                     │
   │              │           │              │ cola.remove(0)                                                                               │
   │              │           │              │                                                                                              │
   │              │           │◄─────────────│ println(placa, conductor, tarifa, tiempo)                                                   │
   │              │◄──────────│              │                                                                                              │
   │◄─────────────│           │              │                                                                                              │
```

---

## Diagrama 2: "Cerrar servicio / Cancelar solicitud"

### 2A — Cancelación por el cliente

```
Cliente      MenuCliente    SolicitudEnEspera    MotivosCancelacion
   │               │                 │                    │
   │ ingresa ID    │                 │                    │
   │ y motivo      │                 │                    │
   │──────────────►│                 │                    │
   │               │                 │                    │
   │               │ new MotivosCancelacion(id,motivo,    │
   │               │   nombreCliente)                     │
   │               │──────────────────────────────────────►
   │               │                 │                    │
   │               │ cancelarSolicitud(id, motivoRegistro)│
   │               │────────────────►│                    │
   │               │                 │                    │
   │               │                 │ busca solicitud    │
   │               │                 │ en cola por ID     │
   │               │                 │──────┐             │
   │               │                 │      │             │
   │               │                 │◄─────┘ encontrada  │
   │               │                 │                    │
   │               │                 │ s.setEstado        │
   │               │                 │ ("CANCELADA")      │
   │               │                 │ s.setMotivo(...)   │
   │               │                 │ cola.remove(i)     │
   │               │                 │ motivos.add(       │
   │               │                 │   motivoRegistro)  │
   │               │                 │                    │
   │               │◄────────────────│ println confirmación│
   │◄──────────────│                 │                    │
```

### 2B — Finalización exitosa del servicio (por operador)

```
Operador    MenuOperedores    GestorConductores    Conductor
   │               │                 │                 │
   │ ingresa ID    │                 │                 │
   │ del conductor │                 │                 │
   │──────────────►│                 │                 │
   │               │                 │                 │
   │               │finalizarServicio│                 │
   │               │Conductor(idCond)│                 │
   │               │────────────────►│                 │
   │               │                 │                 │
   │               │                 │ busca conductor │
   │               │                 │ por ID          │
   │               │                 │────────────────►│
   │               │                 │◄── conductor ───│
   │               │                 │                 │
   │               │                 │conductor        │
   │               │                 │.setDisponible   │
   │               │                 │(true)           │
   │               │                 │────────────────►│
   │               │                 │                 │
   │               │◄────────────────│ println:        │
   │               │                 │ "Conductor X ya │
   │               │                 │  está disponible"
   │◄──────────────│                 │                 │
```

---

## Diagrama 3: "Guardar estado al cerrar" (persistencia)

```
Operador    SystemUI    GestorPersistencia    PersistenciaSolicitudes    PersistenciaRedVial    GestorConexiones
   │               │              │                    │                         │                    │
   │ selecciona    │              │                    │                         │                    │
   │ "4. Salir"    │              │                    │                         │                    │
   │──────────────►│              │                    │                         │                    │
   │               │              │                    │                         │                    │
   │               │guardarTodo(  │                    │                         │                    │
   │               │solicitudes,  │                    │                         │                    │
   │               │conexiones)   │                    │                         │                    │
   │               │─────────────►│                    │                         │                    │
   │               │              │                    │                         │                    │
   │               │              │guardarCola(cola)   │                         │                    │
   │               │              │───────────────────►│                         │                    │
   │               │              │                    │ escribe cola.txt        │                    │
   │               │              │                    │──────────┐              │                    │
   │               │              │                    │◄─────────┘              │                    │
   │               │              │                    │                         │                    │
   │               │              │guardarHistorial(   │                         │                    │
   │               │              │  historial)        │                         │                    │
   │               │              │───────────────────►│                         │                    │
   │               │              │                    │ escribe historial.txt   │                    │
   │               │              │                    │──────────┐              │                    │
   │               │              │                    │◄─────────┘              │                    │
   │               │              │                    │                         │                    │
   │               │              │guardarEstado(      │                         │                    │
   │               │              │  conexiones)       │                         │                    │
   │               │              │────────────────────────────────────────────►│                    │
   │               │              │                    │                         │ getConexiones()    │
   │               │              │                    │                         │───────────────────►│
   │               │              │                    │                         │◄── lista ──────────│
   │               │              │                    │                         │ escribe red_vial.txt
   │               │              │                    │                         │──────────┐         │
   │               │              │                    │                         │◄─────────┘         │
   │               │              │                    │                         │                    │
   │               │◄─────────────│ "[Sistema] Estado guardado correctamente."  │                    │
   │◄──────────────│              │                    │                         │                    │
```
