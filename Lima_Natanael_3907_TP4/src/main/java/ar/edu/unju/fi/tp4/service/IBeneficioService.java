package ar.edu.unju.fi.tp4.service;

import java.util.List;
import java.util.Optional;

import ar.edu.unju.fi.tp4.model.Beneficio;

public interface IBeneficioService {
	
	public void guardarBeneficio (Beneficio beneficio);
	
	public List<Beneficio> obtenerBeneficios();
	
	public Beneficio buscarBeneficio(long id);
}
