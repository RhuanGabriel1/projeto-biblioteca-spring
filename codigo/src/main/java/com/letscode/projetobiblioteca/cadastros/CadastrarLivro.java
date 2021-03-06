package com.letscode.projetobiblioteca.cadastros;

import com.letscode.projetobiblioteca.emprestimo.Livro;
import com.letscode.projetobiblioteca.interfaces.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarLivro implements com.letscode.projetobiblioteca.interfaces.Menus.MenuCadastrarLivro, com.letscode.projetobiblioteca.interfaces.Menus.DigitarDados {
    private Livro livro;
    private final LivroRepository livroRepository;



    @Override
    public void criacaoLivro() {
        System.out.println(this.livro.toString());
            this.livroRepository.save(livro);

    }

    @Override
    public void digitarDados() {
        this.livro = new Livro(
                ColetarDados.coletaString("Nome: "),
                ColetarDados.coletaInteger("Ano: ", -2000, 2022),
                ColetarDados.coletaString("Autor: "),
                ColetarDados.coletaString("Editora: "),
                ColetarDados.coletaInteger("Insira a quantidade de exemplares: ", 1, 100)
                );

    }

}