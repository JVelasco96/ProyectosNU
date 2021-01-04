package com.itc.form.app.models.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.itc.form.app.models.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente,String>{
	
	
	
}
