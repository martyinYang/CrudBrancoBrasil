/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Endereco;
import br.com.senac.entidade.PessoaJuridica;
import static br.com.senac.util.GeradorUtil.*;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author silvio.junior
 */
public class PessoaJuridicaDaoImplTest {

    private PessoaJuridica pessoaJuridica;
    private PessoaJuridicaDao pessoaJuridicaDao;
    private Session sessao;

    public PessoaJuridicaDaoImplTest() {
        pessoaJuridicaDao = new PessoaJuridicaDaoImpl();
    }

//    @Test
    public void testSalvar() {
        System.out.println("salvar");
        pessoaJuridica = new PessoaJuridica("Empresa " + gerarNome(),
                gerarLogin() + "gmail.com", gerarCnpj(), gerarNumero(6));
        Endereco endereco = gerarEndereco();
        pessoaJuridica.setEndereco(endereco);
        endereco.setCliente(pessoaJuridica);
        
        sessao = HibernateUtil.abrirConexao();
        pessoaJuridicaDao.salvarOuAlterar(pessoaJuridica, sessao);
        sessao.close();
        assertNotNull(pessoaJuridica.getId());
    }
    
    private Endereco gerarEndereco(){
        Endereco end = new Endereco(
                "Rua das Flores",
                "Centro",
                gerarNumero(3),
                gerarCidade(),
                "SC",
                "casa",
                gerarCep()
        );
        return end;
    }

}
