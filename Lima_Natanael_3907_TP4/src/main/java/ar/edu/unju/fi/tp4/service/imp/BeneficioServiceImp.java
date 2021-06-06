package ar.edu.unju.fi.tp4.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp4.model.Beneficio;
import ar.edu.unju.fi.tp4.repository.IBeneficioRepository;
import ar.edu.unju.fi.tp4.service.IBeneficioService;

@Service("tableBeneficio")
public class BeneficioServiceImp implements IBeneficioService{

	@Autowired
	IBeneficioRepository beneficioRepository;
	
	@Override
	public void guardarBeneficio(Beneficio beneficio) {
		// TODO Auto-generated method stub
		beneficioRepository.save(beneficio);
	}

}
