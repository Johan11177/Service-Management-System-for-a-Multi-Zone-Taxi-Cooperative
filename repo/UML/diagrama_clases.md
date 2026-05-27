# Diagrama de Clases — Sistema de Gestión de Taxis Multizona

## Notación utilizada
```
[Clase]          clase concreta
[<<abstracta>>]  clase abstracta
[<<interfaz>>]   interfaz
─────────────    herencia (extends)
- - - - - - -    implementación (implements)
──────────────►  asociación (usa / conoce)
◄────────────    dependencia
◆────────────    composición
```

---

## Diagrama completo (PlantUML / texto)

```
┌─────────────────────────────────────────────────────────────────────────────────┐
│                           CAPA DE DOMINIO / MODELO                              │
└─────────────────────────────────────────────────────────────────────────────────┘

╔══════════════════╗
║  <<abstracta>>   ║
║    Usuario       ║
╠══════════════════╣
║ -nombre: String  ║
║ -ID: String      ║
╠══════════════════╣
║ +getNombre()     ║
║ +getID()         ║
║ +setNombre()     ║
║ +setID()         ║
╚══════════════════╝
         △
         │  herencia
   ┌─────┴──────┐
   │            │
╔══╧═══════╗  ╔═╧══════════╗
║ Cliente  ║  ║ Conductor  ║
╠══════════╣  ╠════════════╣
║          ║  ║ -vehiculo  ║
╠══════════╣  ║ -disponible║
║+Cancelar-║  ║ -servicios ║
║ Servicio ║  ╠════════════╣
╚══════════╝  ║+puedeAtend.║
              ║+agregarSer.║
              ║+isDisponib.║
              ╚════════════╝
                    ◆ composición
                    │  1..1
              ╔═════╧══════╗
              ║  Vehiculo  ║
              ╠════════════╣
              ║ -marca     ║
              ║ -placa     ║
              ╚════════════╝

              ╔══════════════════╗
              ║  <<abstracta>>   ║
              ║      Taxi        ║
              ╠══════════════════╣
              ║ -tipoServicio    ║
              ╠══════════════════╣
              ║ +getTipoServicio ║
              ╚══════════════════╝
                       △
          ┌────────────┼────────────┐
          │            │            │
  ╔═══════╧═══╗ ╔══════╧═════╗ ╔══╧══════════════════╗
  ║TaxiEstand.║ ║TaxiConBaul ║ ║TaxiTransporteMascot.║
  ╚═══════════╝ ╚════════════╝ ╚═════════════════════╝


╔═══════════════════════╗
║     Solicitudes       ║
╠═══════════════════════╣
║ -IDsolicitud: int     ║
║ -zonaDeorigen: String ║
║ -ZonaDestino: String  ║
║ -TipoDeServicio       ║
║ -Hora, Fecha          ║
║ -EstadoSolicitud      ║
║ -nombreCliente        ║
║ -nombreConductor      ║
║ -idConductor          ║
║ -placaVehiculo        ║
║ -tarifaEstimada:double║
║ -tiempoEstimado: int  ║
║ -motivoCancelacion    ║
╠═══════════════════════╣
║ +getters / setters    ║
╚═══════════════════════╝

╔══════════════════════════╗
║   MotivosCancelacion     ║
╠══════════════════════════╣
║ -idSolicitud: int        ║
║ -motivo: String          ║
║ -fechaCancelacion        ║
║ -horaCancelacion         ║
║ -nombreCliente           ║
╠══════════════════════════╣
║ +getters                 ║
╚══════════════════════════╝

╔═══════════════╗
║    Zonas      ║
╠═══════════════╣
║ -idZona: int  ║
║ -nombreZona   ║
╠═══════════════╣
║ +getters      ║
║ +setters      ║
╚═══════════════╝

╔══════════════════════════╗
║       Conexiones         ║
╠══════════════════════════╣
║ -origen: Zonas           ║
║ -destino: Zonas          ║
║ -Habilitada: boolean     ║
║ -tiempoEstimado: int     ║
║ -kiloMetros: double      ║
╠══════════════════════════╣
║ +isHabilitada()          ║
║ +setHabilitada()         ║
║ +getters / setters       ║
╚══════════════════════════╝

┌─────────────────────────────────────────────────────────────────────────────────┐
│                         CAPA DE TARIFAS (FACTORY METHOD)                        │
└─────────────────────────────────────────────────────────────────────────────────┘

╔═══════════════════╗
║   <<interfaz>>    ║
║     Tarifa        ║
╠═══════════════════╣
║+CalcularTarifa(   ║
║  origen: int,     ║
║  destino: int):   ║
║  double           ║
╚═══════════════════╝
         △
    ─────┼──────
    │    │     │
╔═══╧╗ ╔═╧══╗ ╔═╧════════╗
║Est.║ ║Baul║ ║ Mascotas ║
╠════╣ ╠════╣ ╠══════════╣
║-   ║ ║-   ║ ║-CostoAdi.║
║Cos-║ ║Cos-║ ╠══════════╣
║to- ║ ║to- ║ ║+Calcular-║
║Base║ ║Base║ ║ Tarifa() ║
╠════╣ ╠════╣ ╚══════════╝
║+Ca.║ ║+Ca.║
╚════╝ ╚════╝

╔════════════════════╗
║  <<abstracta>>     ║
║  FabricaTarifas    ║
╠════════════════════╣
║+crearTarifa(tipo): ║
║  Tarifa            ║
╚════════════════════╝
          △
     ─────┴─────
     │          │
╔════╧═══════╗ ╔╧═══════════════╗
║FabricaNorm.║ ║FabricaEspecial ║
╠════════════╣ ╠════════════════╣
║+crearTarifa║ ║+crearTarifa    ║
║ →Estandar  ║ ║ →Baul          ║
╚════════════╝ ║ →Mascotas      ║
               ╚════════════════╝

┌─────────────────────────────────────────────────────────────────────────────────┐
│                         CAPA DE LÓGICA / GESTORES                               │
└─────────────────────────────────────────────────────────────────────────────────┘

╔══════════════════════════════╗     ╔════════════════════════╗
║     SolicitudEnEspera        ║     ║   GestorConductores    ║
╠══════════════════════════════╣     ╠════════════════════════╣
║ -cola: ArrayList<Solicitudes>║     ║ -conductores: ArrayList║
║ -historial: ArrayList        ║     ╠════════════════════════╣
║ -motivos: ArrayList          ║     ║+agregarConductor()     ║
║ -gestorConductores           ║◄────║+encontrarDisponible()  ║
║ -gestorConexiones            ║     ║+habilitarServicio()    ║
║ -gestorZonas                 ║     ║+finalizarServicio()    ║
╠══════════════════════════════╣     ╚════════════════════════╝
║+agregarSolicitud()           ║
║+atenderSolicitud()           ║     ╔════════════════════════╗
║+cancelarSolicitud()          ║     ║    GestorConexiones    ║
║+mostrarHistorial()           ║◄────║    Rutas               ║
║+getHistorial()               ║     ╠════════════════════════╣
║+agregarAlHistorial()         ║     ║ -conexiones: ArrayList ║
╚══════════════════════════════╝     ║ -gestorZonas           ║
                                     ╠════════════════════════╣
╔══════════════════════╗             ║+AgregarConexiones()    ║
║      Operador        ║             ║+HabilitarDeshabilitar()║
╠══════════════════════╣             ╚════════════════════════╝
║ -nombre: String      ║
║ -id: int             ║             ╔════════════════════════╗
║ -solicitudesEnEspera ║────────────►║    GestorZonas         ║
╠══════════════════════╣             ╠════════════════════════╣
║+AtenderSiguiente()   ║             ║ -zonas: ArrayList      ║
║+CancelarSolicitud()  ║             ╠════════════════════════╣
║+ListarEnEspera()     ║             ║+AgregarZonas()         ║
╚══════════════════════╝             ║+MostrarZonas()         ║
                                     ╚════════════════════════╝

┌─────────────────────────────────────────────────────────────────────────────────┐
│                         CAPA DE ALGORITMOS (ZONA)                               │
└─────────────────────────────────────────────────────────────────────────────────┘

╔═══════════════════════╗
║   <<abstracta>>       ║
║       Grafo           ║
╠═══════════════════════╣
║ #matriz: int[][]      ║
║ #rutas: GestorConex.  ║
╠═══════════════════════╣
║ +construirGrafo()     ║ ◄── abstracto
║ +getMatriz()          ║
╚═══════════════════════╝
          △
     ─────┴──────
     │           │
╔════╧═════╗  ╔══╧══════════╗
║GrafoTiem.║  ║GrafoKilom.  ║
╠══════════╣  ╠═════════════╣
║+construir║  ║+construirGr.║
╚══════════╝  ╚═════════════╝

╔══════════════╗     ╔════════════════╗
║   Dijkstra   ║     ║      BFS       ║
╠══════════════╣     ╠════════════════╣
║ -matriz[][]  ║     ║ -matriz[][]    ║
╠══════════════╣     ╠════════════════╣
║+calcularRuta ║     ║ +hayConexion() ║
║  MasCorta()  ║     ╚════════════════╝
╚══════════════╝

┌─────────────────────────────────────────────────────────────────────────────────┐
│                         CAPA DE PERSISTENCIA                                    │
└─────────────────────────────────────────────────────────────────────────────────┘

╔══════════════════════════╗   ╔═══════════════════════════╗   ╔══════════════════╗
║ PersistenciaConductores  ║   ║  PersistenciaSolicitudes  ║   ║PersistenciaRedVial║
╠══════════════════════════╣   ╠═══════════════════════════╣   ╠══════════════════╣
║ +guardar(conductores)    ║   ║ +guardarHistorial()       ║   ║+guardarEstado()  ║
║ +cargar():ArrayList      ║   ║ +cargarHistorial()        ║   ║+cargarEstado()   ║
╚══════════════════════════╝   ║ +guardarCola()            ║   ╚══════════════════╝
                               ║ +cargarCola()             ║
                               ╚═══════════════════════════╝

╔═══════════════════════════╗
║    GestorPersistencia     ║
╠═══════════════════════════╣
║ +cargarTodo()             ║
║ +guardarTodo()            ║
╚═══════════════════════════╝

┌─────────────────────────────────────────────────────────────────────────────────┐
│                        CAPA DE EXCEPCIONES                                      │
└─────────────────────────────────────────────────────────────────────────────────┘

ZonaInexistenteException
ConductorNoHabilitadoException
SinConectividadVialException
SolicitudInvalidaException
CancelacionIndebidaException
DatosIncompletosException
  │── todas extienden Exception

┌─────────────────────────────────────────────────────────────────────────────────┐
│                        CAPA DE INTERFAZ DE USUARIO                              │
└─────────────────────────────────────────────────────────────────────────────────┘

╔══════════════════╗
║ <<interfaz>>     ║
║ InterfaceMenu    ║
╠══════════════════╣
║ +CrearMenu()     ║
╚══════════════════╝
         △
    ─────┼──────────────────────────
    │         │          │         │
╔═══╧════╗ ╔══╧═════╗ ╔══╧═════╗ ╔╧═══════════════╗
║MenuCli.║ ║MenuCon.║ ║MenuOpe.║ ║MenuGestionCond.║
╚════════╝ ╚════════╝ ╚════════╝ ╚════════════════╝
                                         +MenuGestionVial

╔══════════════╗
║  SystemUI    ║ ◄── punto de entrada, crea y conecta todos los menús
╠══════════════╣
║ +iniciar()   ║
╚══════════════╝
```

## Cardinalidades clave

| Relación | Cardinalidad |
|----------|-------------|
| Conductor → Vehiculo | 1 .. 1 (composición) |
| Conductor → Taxi (servicios) | 1 .. * |
| SolicitudEnEspera → Solicitudes (cola) | 0 .. * |
| SolicitudEnEspera → Solicitudes (historial) | 0 .. * |
| GestorConexionesRutas → Conexiones | 1 .. * |
| Conexiones → Zonas (origen/destino) | 2 .. 2 |
| GestorZonas → Zonas | 1 .. * |
| GestorConductores → Conductor | 0 .. * |
