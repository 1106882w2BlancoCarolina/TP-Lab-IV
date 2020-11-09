/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Actividad;
import Modelo.DtoActividadGanancia;
import Modelo.Inscripcion;
import Modelo.Pago;
import Modelo.Publicacion;
import Modelo.Socio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Carolina
 */
public class GestorBD {

    private Connection con;

    private void abrirConexion() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-C9TS6P1\\SQLEXPRESS;databaseName=ClubDeportivo", "sa", "Holis1234");

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private void cerrarConexion() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    ////////////////////////////
    //// Area Actividades //////
    ////////////////////////////
    public void agregarActividad(Actividad actividad) {
        try {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Actividades (nombre, descripcion, costoMensual) VALUES (?, ?, ?)");

            ps.setString(1, actividad.getNombre());
            ps.setString(2, actividad.getDescripcion());
            ps.setDouble(3, actividad.getCostoMensual());
            ps.executeUpdate();

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void actualizarActividad(Actividad actividad) {
        try {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE Actividades SET nombre = ?, descripcion = ?, costoMensual = ? WHERE id = ?");
            ps.setString(1, actividad.getNombre());
            ps.setString(2, actividad.getDescripcion());
            ps.setDouble(3, actividad.getCostoMensual());
            ps.setInt(4, actividad.getId());
            ps.executeUpdate();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Actividad> obtenerActividades() {
        ArrayList<Actividad> lista = new ArrayList<Actividad>();
        try {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Actividades WHERE eliminado IS NULL ORDER BY nombre");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double cuotaMensual = rs.getDouble("costoMensual");

                Actividad actividad = new Actividad(id, nombre, descripcion, cuotaMensual);
                lista.add(actividad);
            }
            rs.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return lista;
    }

    public Actividad obtenerActividadPorId(int id) {
        Actividad actividad = new Actividad();
        try {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Actividades WHERE id = " + id);
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double cuotaMensual = rs.getDouble("costoMensual");

                actividad = new Actividad(id, nombre, descripcion, cuotaMensual);
            }
            rs.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return actividad;
    }

    public ArrayList<Actividad> obtenerActividadesDeSocioId(int idSocio) {

        ArrayList<Actividad> lista = new ArrayList<Actividad>();

        try {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT a.id, a.nombre, a.costoMensual FROM Inscripciones i JOIN Actividades a ON i.idActividad = a.id WHERE idSocio = " + idSocio);
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                double costoMensual = rs.getDouble("costoMensual");

                Actividad actividad = new Actividad(id, nombre, "", costoMensual);

                lista.add(actividad);
            }
            rs.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return lista;
    }
    
    public ArrayList<Socio> obtenerSociosPorActividadId(int idActividad) {

        ArrayList<Socio> lista = new ArrayList<Socio>();

        try {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Socios s JOIN Inscripciones i ON i.idSocio = s.id WHERE i.idActividad = " + idActividad);
            while (rs.next()) {
              int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int dni = rs.getInt("dni");
                Date fechaNacimiento = rs.getDate("fechaNacimiento");
                String genero = rs.getString("genero");
                String telefono = rs.getString("telefono");

                Socio socio = new Socio(id, nombre, apellido, dni, fechaNacimiento, genero, telefono);
                lista.add(socio);
            }
            rs.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return lista;
    }
    //// FIN Area Actividades

    
    /////////////////////////////
    //// Area Publicaciones /////
    ////////////////////////////
    public ArrayList<Publicacion> obtenerPublicaciones() {
        ArrayList<Publicacion> lista = new ArrayList<Publicacion>();
        try {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Publicaciones p JOIN Actividades a ON p.idActividad = a.id WHERE p.eliminado IS NULL ORDER by p.fecha");
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String contenido = rs.getString("contenido");
                String urlImagen = rs.getString("urlImagen");
                Date fecha = rs.getDate("fecha");
                boolean activa = rs.getBoolean("activa");

                int idActividad = rs.getInt("idActividad");
                String nombreActividad = rs.getString("nombre");
                String descripcionActividad = rs.getString("descripcion");
                Double costoMensual = rs.getDouble("costoMensual");

                Actividad actividad = new Actividad(idActividad, nombreActividad, descripcionActividad, costoMensual);
                Publicacion publicacion = new Publicacion(id, titulo, contenido, urlImagen, actividad, fecha, activa);

                lista.add(publicacion);
            }
            rs.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return lista;
    }

    public void agregarPublicacion(Publicacion publicacion) {
        try {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Publicaciones "
                    + "(titulo, contenido, urlImagen, idActividad, fecha, activa) VALUES (?, ?, ?, ?, ? ,?)");

            ps.setString(1, publicacion.getTitulo());
            ps.setString(2, publicacion.getContenido());
            ps.setString(3, publicacion.getUrlImagen());
            ps.setInt(4, publicacion.getActividad().getId());
            ps.setDate(5, new java.sql.Date(publicacion.getFecha().getTime()));
            ps.setBoolean(6, publicacion.isActiva());
            ps.executeUpdate();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void actualizarPublicacion(Publicacion publicacion) {
        try {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE Publicaciones SET titulo = ?, "
                    + "contenido = ?, urlImagen = ?, idActividad = ?, fecha = ?, activa = ? WHERE id = ?");
            ps.setString(1, publicacion.getTitulo());
            ps.setString(2, publicacion.getContenido());
            ps.setString(3, publicacion.getUrlImagen());
            ps.setInt(4, publicacion.getActividad().getId());
            ps.setDate(5, new java.sql.Date(publicacion.getFecha().getTime()));
            ps.setBoolean(6, publicacion.isActiva());
            ps.setInt(7, publicacion.getId());
            ps.executeUpdate();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public Publicacion obtenerPublicacionPorId(int id) {

        Publicacion publicacion = new Publicacion();
        try {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Publicaciones p JOIN Actividades a ON p.idActividad = a.id WHERE p.id = " + id);
            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String contenido = rs.getString("contenido");
                String urlImagen = rs.getString("urlImagen");
                Date fecha = rs.getDate("fecha");
                boolean activa = rs.getBoolean("activa");

                int idActividad = rs.getInt("idActividad");
                String nombreActividad = rs.getString("nombre");
                String descripcionActividad = rs.getString("descripcion");
                Double costoMensual = rs.getDouble("costoMensual");

                Actividad actividad = new Actividad(idActividad, nombreActividad, descripcionActividad, costoMensual);
                publicacion = new Publicacion(id, titulo, contenido, urlImagen, actividad, fecha, activa);
            }
            rs.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return publicacion;
    }
    //// FIN AREA Publicaciones

    
    ///////////////////////
    //// Area Socios /////
    /////////////////////
    public ArrayList<Socio> obtenerSocios() {
        ArrayList<Socio> lista = new ArrayList<Socio>();
        try {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Socios WHERE eliminado IS NULL");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int dni = rs.getInt("dni");
                Date fechaNacimiento = rs.getDate("fechaNacimiento");
                String genero = rs.getString("genero");
                String telefono = rs.getString("telefono");

                Socio socio = new Socio(id, nombre, apellido, dni, fechaNacimiento, genero, telefono);
                lista.add(socio);
            }
            rs.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return lista;
    }

    public void agregarSocio(Socio socio) {
        try {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Socios "
                    + "(nombre, apellido, dni, fechaNacimiento, genero, telefono) VALUES (?, ?, ?, ?, ? , ?)");

            ps.setString(1, socio.getNombre());
            ps.setString(2, socio.getApellido());
            ps.setInt(3, socio.getDni());
            ps.setDate(4, new java.sql.Date(socio.getFechaNacimiento().getTime()));
            ps.setString(5, socio.getGenero());
            ps.setString(6, socio.getTelefono());
            ps.executeUpdate();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void actualizarSocio(Socio socio) {
        try {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE Socios SET nombre = ?, "
                    + "apellido = ?, dni = ?, fechaNacimiento = ?, genero = ?, telefono = ? WHERE id = ?");
            ps.setString(1, socio.getNombre());
            ps.setString(2, socio.getApellido());
            ps.setInt(3, socio.getDni());
            ps.setDate(4, new java.sql.Date(socio.getFechaNacimiento().getTime()));
            ps.setString(5, socio.getGenero());
            ps.setString(6, socio.getTelefono());
            ps.setInt(7, socio.getId());
            ps.executeUpdate();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public Socio obtenerSocioPorId(int id) {
        Socio socio = new Socio();
        try {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Socios WHERE id = " + id);
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int dni = rs.getInt("dni");
                Date fechaNacimiento = rs.getDate("fechaNacimiento");
                String genero = rs.getString("genero");
                String telefono = rs.getString("telefono");

                socio = new Socio(id, nombre, apellido, dni, fechaNacimiento, genero, telefono);
            }
            rs.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return socio;

    }
    //// FIN Area Socios

    /////////////////////
    //// Area Pagos ////
    ///////////////////
    public ArrayList<Pago> obtenerPagosPorSocioId(int idSocio) {
        ArrayList<Pago> lista = new ArrayList<Pago>();

        try {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Pagos WHERE idSocio = " + idSocio);
            while (rs.next()) {
                int id = rs.getInt("id");
                String referencia = rs.getString("referencia");
                double monto = rs.getDouble("monto");
                Date fecha = rs.getDate("fecha");

                Pago pago = new Pago(id, monto, idSocio, fecha, referencia);
                lista.add(pago);
            }
            rs.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return lista;
    }
    
    public void agregarPago(Pago pago, ArrayList<Actividad> actividades) {
        try {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Pagos "
                    + "(referencia, idSocio, monto, fecha) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, pago.getReferencia());
            ps.setInt(2, pago.getIdSocio());
            ps.setDouble(3, pago.getMonto());
            ps.setDate(4, new java.sql.Date(pago.getFecha().getTime()));
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            
            for (Actividad actividad : actividades) {
                agregarDetallePago(generatedKey, actividad.getId());
            }
             
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }
    
    public void agregarDetallePago(int idPago, int idActividad) {
        try {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO DetallePagos "
                    + "(idActividad, idPago) VALUES (?, ?)");

            ps.setInt(1, idActividad);
            ps.setInt(2, idPago);
            ps.executeUpdate();
            
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }
    //// FIN Area Pagos
    
    ////////////////////////////
    //// Area Inscripciones ////
    ////////////////////////////
    public ArrayList<Inscripcion> obtenerInscripcionesDeSocioId(int idSocio) {

        ArrayList<Inscripcion> lista = new ArrayList<Inscripcion>();

        try {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT *, i.id as inscripcionId, a.nombre as nombreActividad, s.nombre as nombreSocio "
                    + "FROM Inscripciones i JOIN Actividades a ON i.idActividad = a.id JOIN Socios s ON i.idSocio = s.id "
                    + "WHERE idSocio = " + idSocio + " ORDER BY a.nombre");
            while (rs.next()) {
                int id = rs.getInt("inscripcionId");
                int idActividad = rs.getInt("idActividad");
                Date fecha = rs.getDate("fecha");
                
                String nombreActividad = rs.getString("nombre");
                String descripcionActividad = rs.getString("descripcion");
                Double costoActividad = rs.getDouble("costoMensual");
                Actividad actividad = new Actividad(idActividad, nombreActividad, descripcionActividad, costoActividad);
                
                String nombreSocio = rs.getString("nombre");
                String apellidoSocio = rs.getString("apellido");
                int dni = rs.getInt("dni");
                
                Socio socio = new Socio(idSocio, nombreSocio, apellidoSocio, dni);

                Inscripcion inscripcion = new Inscripcion(id, socio, actividad, fecha);

                lista.add(inscripcion);
            }
            rs.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return lista;
    }
    
    public Inscripcion obtenerInscripcionPorId(int id) {
        Inscripcion inscripcion = new Inscripcion();

        try {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT *,a.nombre as nombreActividad "
                    + "FROM Inscripciones i JOIN Actividades a ON i.idActividad = a.id "
                    + "WHERE i.id = " + id);
            while (rs.next()) {
                int idActividad = rs.getInt("idActividad");
                int idSocio = rs.getInt("idSocio");
                Date fecha = rs.getDate("fecha");
                
                String nombreActividad = rs.getString("nombre");
                String descripcionActividad = rs.getString("descripcion");
                Double costoActividad = rs.getDouble("costoMensual");
                
                Actividad actividad = new Actividad(idActividad, nombreActividad, descripcionActividad, costoActividad);
                Socio socio = new Socio();
                socio.setId(idSocio);

                inscripcion = new Inscripcion(id, socio, actividad, fecha);
            }
            rs.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return inscripcion;
    }
    
    public void agregarInscripcion(Inscripcion inscripcion) {
        try {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Inscripciones "
                    + "(idSocio, idActividad, fecha) VALUES (?, ?, GETDATE())");

            ps.setInt(1, inscripcion.getSocio().getId());
            ps.setInt(2, inscripcion.getActividad().getId());
            ps.executeUpdate();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }
    /// Fin Area Inscripciones
    
    
    ////////////////////////////
    //// Area Reportes ////////
    ///////////////////////////
    public double obtenerTotalGanancias() {

        double totalGanancias = 0;

        try {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(monto) as total FROM Pagos ");
            while (rs.next()) {
                totalGanancias = rs.getDouble("total");
            }
            rs.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return totalGanancias;
    }
    
    public ArrayList<DtoActividadGanancia> obtenerActividadesMayorGanancias() {

        ArrayList<DtoActividadGanancia> resultados = new ArrayList<DtoActividadGanancia>();

        try {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT a.nombre, SUM(a.costoMensual) as total "
                    + "FROM DetallePagos dp JOIN Actividades a ON dp.idActividad = a.id "
                    + "GROUP BY a.nombre ORDER BY total desc");
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                double total = rs.getDouble("total");
                
                DtoActividadGanancia resultado = new DtoActividadGanancia(nombre, total);
                resultados.add(resultado);
            }
            rs.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return resultados;
    }
    
    //////////////////////////////////////
    //// Area Portal Publicaciones ////////
    //////////////////////////////////////
    public ArrayList<Publicacion> obtenerPublicacionesActivasPorActividadId(int idActividad) {

        ArrayList<Publicacion> lista = new ArrayList<Publicacion>();
        try {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(" SELECT *, p.id as idPublicacion " +
                " FROM Publicaciones p JOIN Actividades a ON p.idActividad = a.id " +
                " WHERE p.activa = 1 AND p.eliminado IS NULL AND a.id = " + idActividad);
            while (rs.next()) {
                int idPublicacion = rs.getInt("idPublicacion");
                String titulo = rs.getString("titulo");
                String contenido = rs.getString("contenido");
                String urlImagen = rs.getString("urlImagen");
                Date fecha = rs.getDate("fecha");
                boolean activa = rs.getBoolean("activa");

                String nombreActividad = rs.getString("nombre");
                String descripcionActividad = rs.getString("descripcion");
                Double costoMensual = rs.getDouble("costoMensual");

                Actividad actividad = new Actividad(idActividad, nombreActividad, descripcionActividad, costoMensual);
                actividad.setId(idActividad);

                Publicacion publicacion = new Publicacion(idPublicacion, titulo, contenido, urlImagen, actividad, fecha, activa);
                
                lista.add(publicacion);
            }
            rs.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return lista;
    }
    
    public ArrayList<Publicacion> obtenerPublicacionesActivas() {

        ArrayList<Publicacion> lista = new ArrayList<Publicacion>();
        try {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(" SELECT *, p.id as idPublicacion, a.id as idActividad " +
                " FROM Publicaciones p JOIN Actividades a ON p.idActividad = a.id " +
                " WHERE p.activa = 1 AND p.eliminado IS NULL");
            while (rs.next()) {
                int idPublicacion = rs.getInt("idPublicacion");
                String titulo = rs.getString("titulo");
                String contenido = rs.getString("contenido");
                String urlImagen = rs.getString("urlImagen");
                Date fecha = rs.getDate("fecha");
                boolean activa = rs.getBoolean("activa");

                int idActividad = rs.getInt("idActividad");
                String nombreActividad = rs.getString("nombre");
                String descripcionActividad = rs.getString("descripcion");
                Double costoMensual = rs.getDouble("costoMensual");

                Actividad actividad = new Actividad(idActividad, nombreActividad, descripcionActividad, costoMensual);
                actividad.setId(idActividad);
                
                Publicacion publicacion = new Publicacion(idPublicacion, titulo, contenido, urlImagen, actividad, fecha, activa);
                
                lista.add(publicacion);
            }
            rs.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return lista;
    }
    
    public void eliminarRegistro(int id, String tabla, boolean isLogico) {
        try {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE " + tabla + " SET fechaEliminacion = GETDATE(), eliminado = 1 WHERE id = ?");
            if (!isLogico) {
                ps = con.prepareStatement("DELETE " + tabla + " WHERE id = ?");
            }
            
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }
}
