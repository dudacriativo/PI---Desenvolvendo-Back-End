package com.pi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pi.models.Produto;
import com.pi.repository.ProdutoRepository;


@Controller
public class ProdutoController {
    
    @Autowired
    private ProdutoRepository pr;
    
    //Chama o form que cadastra o produto
    
    @RequestMapping("/cadastrarProduto")
    public String form() {
        return "produto/form-produto";
    }
    
    //Post que cadastra o produto
    @RequestMapping(value = "/cadastrarProduto", method = RequestMethod.POST)
    public String form(@Valid Produto produto, BindingResult result, RedirectAttributes attributes) {
        
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos...");
            return "redirect:/cadastrarProduto";
        }
        
        pr.save(produto);
        attributes.addFlashAttribute("mensagem", "Produto cadastrado com sucesso!");
        return "redirect:/cadastrarProduto";
    }
    
    // GET que lista os produtos
    @RequestMapping("/produtos")
    public ModelAndView listaProdutos() {
        ModelAndView mv = new ModelAndView("produto/lista-produto");
        Iterable<Produto> produtos = pr.findAll();
        mv.addObject("produtos", produtos);
        return mv;
    }
    
    // GET que mostra os detalhes do produto
    @RequestMapping("/produto/{codigo}")
    public ModelAndView detalhesProduto(@PathVariable("codigo") long codigo) {
        Produto produto = pr.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("produto/detalhes-produto");
        mv.addObject("produto", produto);

        return mv;
    }
    
    // GET que deleta o produto
    @RequestMapping("/deletarProduto")
    public String deletarProduto(long codigo) {
        Produto produto = pr.findByCodigo(codigo);
        pr.delete(produto);
        return "redirect:/produtos";
    }
    
	// Métodos que atualiza produto
	// GET que chama o formulário de edição do produto
	@RequestMapping("/editar-produto")
	public ModelAndView editarProduto(long codigo) {
		Produto produto = pr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("produto/update-produto");
		mv.addObject("produto", produto);
		return mv;
	}

	// POST do FORM que atualiza o produto
	@RequestMapping(value = "/editar-produto", method = RequestMethod.POST)
	public String updateProduto(@Valid Produto produto, BindingResult result, RedirectAttributes attributes) {
		pr.save(produto);
		attributes.addFlashAttribute("success", "Produto alterado com sucesso!");
		return "redirect:/produtos";
	}
}
