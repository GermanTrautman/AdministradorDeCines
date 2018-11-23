package com.cine.modelo;

import com.cine.dao.FuncionPersistente;
import com.cine.utilidades.Estado;

import java.sql.Time;
import java.time.LocalDate;

import java.util.List;

public class Funcion {

    private Integer id = null;
    private Time horario;
    private Pelicula pelicula;
    private Sala sala;
    private LocalDate fecha;
    private AsientoVirtual[][] asientosVirtuales = new AsientoVirtual[AsientoVirtual.FILAS][AsientoVirtual.ASIENTOSPORFILA];
    private Estado estado;

    
    /**
	 * @param horario
	 * @param pelicula
	 * @param sala
	 * @param fecha
	 * @param asientosVirtuales
	 * @param estado
	 */
	public Funcion(Time horario, Pelicula pelicula, Sala sala, LocalDate fecha, Estado estado) {
		
		super();
		this.horario = horario;
		this.pelicula = pelicula;
		this.sala = sala;
		this.fecha = fecha;
		this.estado = estado;
	}

	public Funcion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<AsientoVirtual> mostrarAsientos() {
        return null;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return horario;
    }

    public void setHora(Time hora) {
        this.horario = hora;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public AsientoVirtual[][] getAsientoVirtual() {
        return asientosVirtuales;
    }

    public void setAsientoVirtual(AsientoVirtual[][] asientoVirtual) {
        this.asientosVirtuales = asientoVirtual;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    private void cargarAsientosVirtuales() {
        AsientoFisico[][] asientosSala = this.sala.getAsientosFisicos();
        for (int i = 0; i < asientosSala.length; i++) {
            for (int j = 0; j < asientosSala.length; j++) {
                if (asientosSala[i][j] != null && asientosSala[i][j].getEstado() != null
                        && asientosSala[i][j].getEstado().equals(Estado.ACTIVO)) {
                    this.asientosVirtuales[i][j] = new AsientoVirtual(asientosSala[i][j], this.getId());
                    this.asientosVirtuales[i][j].insertar();
                }
            }
        }

    }

    public void insertarFuncion() {
        FuncionPersistente.getInstance().insertar(this);
        //To-do this.setId(FuncionPersistente.getInstance().getIdFuncion(this.getFecha(), this.getSala(), this.getPelicula(), this.getHora()));
        this.setId(FuncionPersistente.getInstance().getIdFuncion(fecha, sala, pelicula, horario));
        this.cargarAsientosVirtuales();

    }

    public void eliminarFuncion() {
        FuncionPersistente.getInstance().borrar(this.getId());
    }

    public void actualizarFuncion(Integer id, LocalDate fecha, Sala sala, Pelicula pelicula, Estado estado, Time hora) {

    	Funcion f = new Funcion(hora,pelicula, sala, fecha,estado);//(id,fecha, sala, pelicula, estado, hora);
        f.setId(id);
        FuncionPersistente.getInstance().actualizar(f);
    }

    public Funcion buscarPeliculaPorDiaYHora(Integer idEstablecimiento, String nombrePelicula) {
        return FuncionPersistente.getInstance().buscarPeliculaPorDiaYHora(idEstablecimiento, nombrePelicula);
    }

}
