package ar.edu.unju.fi.tp4.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tp4.model.Beneficio;
import ar.edu.unju.fi.tp4.model.Cliente;
import ar.edu.unju.fi.tp4.service.IClienteService;
import ar.edu.unju.fi.tp4.service.ICuentaService;
import ar.edu.unju.fi.tp4.service.imp.BeneficioServiceImp;

@Controller
public class ClienteController {

	@Autowired
	private Cliente cliente;
	
	@Autowired
	@Qualifier("tableBeneficio")
	private BeneficioServiceImp beneficioService;

	@Autowired
	@Qualifier("tableClienteRepository")
	private IClienteService clienteService;

	@Autowired
	@Qualifier("tableCuentaRepository")
	private ICuentaService cuentaService;

	@GetMapping("/index/cliente")
	public String getPageMain(Model model) {
		model.addAttribute(cliente);
		model.addAttribute("beneficios",beneficioService.obtenerBeneficios());
		return "nuevocliente";
	}

	@PostMapping("/index/guardarcliente")
	public ModelAndView getProcesoGuardar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result) {
		ModelAndView model;
		if(result.hasErrors()) {
			model= new ModelAndView("nuevocliente");
			model.addObject(cliente);
			model.addObject("beneficios",beneficioService.obtenerBeneficios());
			
			return model;
		}
		else {
			List<Beneficio> aux_bene=new ArrayList<Beneficio>();
		    for(Beneficio ben: cliente.getBeneficios()) {
		    	
		    	aux_bene.add(beneficioService.buscarBeneficio(ben.getId()));
		    	
		    }
		    cliente.setBeneficios(aux_bene);
			clienteService.agregarCliente(cliente);
			model= new ModelAndView("mostrarclientes");
			model.addObject("clientes", clienteService.obtenerClientes());
			model.addObject("cuentas", cuentaService.obtenerCuentas());
			return model;
		}
		
	}

	@GetMapping("/index/listado")
	public ModelAndView getProcesoListado() {
		ModelAndView model = new ModelAndView("mostrarclientes");

		model.addObject("clientes", clienteService.obtenerClientes());
		model.addObject("cuentas", cuentaService.obtenerCuentas());
		return model;
	}

	// --------------------- TP7 ---------------------

	// Controller Cliente
	@GetMapping("/index/eliminar/{id}")
	public ModelAndView getEliminarCliente(@PathVariable(value = "id") int param) {
		ModelAndView model = new ModelAndView("mostrarclientes");
		clienteService.eliminarCliente(param);
		model.addObject("clientes", clienteService.obtenerClientes());
		return model;
	}

	@GetMapping("/index/modificar/{id}")
	public ModelAndView getModificarCliente(@PathVariable(value = "id") int param) {
		ModelAndView model = new ModelAndView("nuevocliente");
		Optional<Cliente> cliente = clienteService.buscarCliente(param);
		
		model.addObject("cliente", cliente);
		model.addObject("beneficios",beneficioService.obtenerBeneficios());
		return model;
	}
	
	// ------------------ TP9 ----------------
	

}
