package resfullEstudiante.restFullEstudiante.modelos;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;	

@Entity
public class Estudiante implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    private long legajo; 
    
    private String nombre;
    private Double DNI; 
    private Date fecha_nacimiento;
    private Date fecha_ingreso;
    private String direccion;

    public Estudiante() {}

    // Getters y Setters
    public long getLegajo() {
        return legajo;
    }

    public void setLegajo(long legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getDNI() {
        return DNI;
    }

    public void setDNI(double dNI) {
        DNI = dNI;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}