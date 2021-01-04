package com.itc.form.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.itc.form.app.models.entity.Cliente;
import com.itc.form.app.models.service.IClienteService;
import com.itc.form.app.util.paging.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	
	@RequestMapping(value={"/", "/index"}, method=RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("titulo", "Proyecto usando Spring boot");
		return "index";
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name="page", defaultValue="0") int page,Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 4);
		
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		
		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
		
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		
		return "listar";
	}

	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de cliente");
		return "form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model,SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de cliente");
			return "form";
		}
		
		clienteService.save(cliente);
		status.setComplete();
		return "redirect:/listar";
	}

	@RequestMapping(value = "/form/{RFC}")
	public String editar(@PathVariable(value = "RFC") String RFC, Map<String, Object> model) {
		Cliente cliente = null;
		
		if (!RFC.isEmpty()) {
			cliente = clienteService.findOne(RFC);
		} else {
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		return "form";
	}

	@RequestMapping(value = "/eliminar/{RFC}")
	public String eliminar(@PathVariable(value = "RFC") String RFC) {
		if (!RFC.isEmpty()) {
			clienteService.delete(RFC);
		}
		return "redirect:/listar";
	}
	
	@RequestMapping(value = "/clienteform")
	public String clienteform(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "busqueda";
	}
	
	@RequestMapping(value = "/RFC")
	public String buscarPorRFC(@RequestParam String RFC, Model model, @ModelAttribute("cliente") Cliente cliente) {
		model.addAttribute("clientePorRFC", clienteService.findOne(RFC));
		return "busqueda";
	}
	
	
}
