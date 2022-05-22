/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Profissao;
import java.util.List;
import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static br.com.senac.util.GeradorUtil.*;
import com.github.javafaker.Faker;
import org.hibernate.query.Query;

/**
 *
 * @author martyin.okuma
 */
public class ProfissaoDaoImplTest {

    private Profissao profissao;
    private ProfissaoDao profissaoDao;
    private Session sessao;

    public ProfissaoDaoImplTest() {
        profissaoDao = new ProfissaoDaoImpl();
    }

//    @Test
    public void testSalvar() {
        System.out.println("salvar");
        Faker conteudo = new Faker();
        profissao = new Profissao(gerarProfissao(), conteudo.lorem().sentence());
        sessao = HibernateUtil.abrirConexao();
        profissaoDao.salvarOuAlterar(profissao, sessao);
        sessao.close();
        assertNotNull(profissao.getId());

    }

    //@Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        buscarProfissaoBd();
        sessao = HibernateUtil.abrirConexao();
        Profissao profissaoRetorno = profissaoDao
                .pesquisarPorId(profissao.getId(), sessao);
        sessao.close();
        assertNotNull(profissaoRetorno);

    }

    //@Test
    public void testPesquisaPorNome() {
        System.out.println("pesquisarPorNome");
        buscarProfissaoBd();
        sessao = HibernateUtil.abrirConexao();
        List<Profissao> profissoes = profissaoDao
                .pesquisaPorNome(profissao.getNome(), sessao);
        sessao.close();
        assertTrue(!profissoes.isEmpty());

    }

    //@Test
    public void testExcluir() {
        System.out.println("Excluir");
        buscarProfissaoBd();
        sessao = HibernateUtil.abrirConexao();
        profissaoDao.excluir(profissao, sessao);

        Profissao profissaoExcluida = profissaoDao
                .pesquisarPorId(profissao.getId(), sessao);
        sessao.close();
        assertNull(profissaoExcluida);
    }

    //    @Test
    public void testAlterar() {
        System.out.println("alterar");
        Faker conteudo = new Faker();
        buscarProfissaoBd();
        profissao.setNome(gerarNome());
        profissao.setDescricao(conteudo.lorem().sentence());
        sessao = HibernateUtil.abrirConexao();
        profissaoDao.salvarOuAlterar(profissao, sessao);
        sessao.close();

        sessao = HibernateUtil.abrirConexao();
        Profissao profissaoAlterada = profissaoDao
                .pesquisarPorId(profissao.getId(), sessao);
        sessao.close();
        assertEquals(profissao.getNome(), profissaoAlterada.getNome());
        assertEquals(profissao.getDescricao(), profissaoAlterada.getDescricao());
    }

    public Profissao buscarProfissaoBd() {
        sessao = HibernateUtil.abrirConexao();
        Query<Profissao> consulta = sessao.createQuery("from Profissao p");
        List<Profissao> profissoes = consulta.list();
        sessao.close();
        if (profissoes.isEmpty()) {
            testSalvar();
        } else {
            profissao = profissoes.get(0);
        }
        return profissao;
    }

}
