/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.PessoaFisica;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author silvio.junior
 */
public class PessoaFisicaDaoImpl extends BaseDaoImpl<PessoaFisica, Long> 
                                          implements PessoaFisicaDao, Serializable{

    @Override
    public PessoaFisica pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return sessao.find(PessoaFisica.class, id);
    }

    @Override
    public List<PessoaFisica> pesquisarPorNome(String nome, Session sessao) 
                                                         throws HibernateException {
        Query<PessoaFisica> consulta = sessao
                      .createQuery("from PessoaFisica pf where pf.nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.getResultList();
    }
    
}
