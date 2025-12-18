package com.proativaservicos.dao.implemets;

import com.proativaservicos.dao.interfaces.DaoOrgaoInterface;
import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.Orgao;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Named
public class DaoOrgaoImp implements DaoOrgaoInterface {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Orgao e) throws Exception {
        entityManager.persist(e);

    }

    @Override
    @Transactional
    public void update(Orgao e) {
        // TODO Auto-generated method stub

    }

    @Override
    @Transactional
    public Orgao merge(Orgao e) throws Exception {
        // TODO Auto-generated method stub
        return entityManager.merge(e);
    }

    @Override
    @Transactional
    public void del(Orgao e) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Orgao> pesquisarCriteria(Orgao e) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Orgao findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Orgao> listAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Orgao> listAllByEmpresa(Empresa empresa) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    public Orgao findByNumero(String numero) {

        try {
            return entityManager.createQuery("select o from Orgao o where o.numero = :numero", Orgao.class)
                    .setParameter("numero", numero).getSingleResult();

        } catch (NoResultException e) {

            return null;
        }

    }

}
