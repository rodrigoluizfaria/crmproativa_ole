package com.proativaservicos.lazymodel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;

import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.UsuarioService;
import org.primefaces.model.SortMeta;

public class LazyUsuarioDataModel extends LazyDataModel<Usuario> implements Serializable {

	private UsuarioService service;

	private Usuario usuario;

	private static final long serialVersionUID = 1L;

	public LazyUsuarioDataModel(UsuarioService service,Usuario usuario) {

		this.service = service;
		this.usuario = usuario;
	}


	@Override
	public int count(Map<String, FilterMeta> map) {
		return 0;
	}

	@Override
	public List<Usuario> load(int i, int i1, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {
		return null;
	}
}
