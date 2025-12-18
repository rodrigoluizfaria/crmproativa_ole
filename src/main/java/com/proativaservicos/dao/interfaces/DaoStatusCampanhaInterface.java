package com.proativaservicos.dao.interfaces;

import com.proativaservicos.model.StatusCampanha;
import com.proativaservicos.util.constantes.AcaoCampanhaEnum;

public interface DaoStatusCampanhaInterface extends DaoGenericInterface<StatusCampanha> {

	StatusCampanha findByAcao(AcaoCampanhaEnum acao);
	
	
	
	
}
