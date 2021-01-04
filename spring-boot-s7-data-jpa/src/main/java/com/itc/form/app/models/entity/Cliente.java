package com.itc.form.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	@Id
	@NotEmpty
	@Pattern(regexp="[a-zA-Z]{4}[0-9]{6}[a-zA-Z\\d]{3}", message="Formato de rfc no permitido")	
	private String RFC;
	
	@NotEmpty
	private String nombre;
	
	@NotNull
	private Integer edad;
	
	@Column(name = "id_ciudad")
	@Size(min=1,max=2, message="Valor exedido")
	@NotEmpty
	@Pattern(regexp="[0-9]{2}||[0-9]{1}", message="Deben ser solo numeros")
	private String idCiudad;
	private static final long serialVersionUID = 1L;

	public String getRFC() {
		return RFC;
	}
	
	public Integer incrementa() {
		return edad+1;
	}

	public void setRFC(String rFC) {
		RFC = rFC;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(String idCiudad) {
		this.idCiudad = idCiudad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
