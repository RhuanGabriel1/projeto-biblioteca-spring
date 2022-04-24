package com.letscode.projetobiblioteca.cadastros;

import com.letscode.projetobiblioteca.emprestimo.Emprestimo;
import com.letscode.projetobiblioteca.emprestimo.Livro;
import com.letscode.projetobiblioteca.interfaces.BibliotecarioRepository;
import com.letscode.projetobiblioteca.interfaces.EmprestimoRepository;
import com.letscode.projetobiblioteca.interfaces.LivroRepository;
import com.letscode.projetobiblioteca.interfaces.UsuarioRepository;
import com.letscode.projetobiblioteca.usuarios.Bibliotecario;
import com.letscode.projetobiblioteca.usuarios.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Scanner;


@Service
@RequiredArgsConstructor
public class CadastrarEmprestimo implements com.letscode.projetobiblioteca.interfaces.Menus.MenuEmprestimo, com.letscode.projetobiblioteca.interfaces.Menus.DigitarDados {

    private Emprestimo emprestimo;
    private Bibliotecario bibliotecario;
    private Livro livro;
    private Usuario usuario;

    private final EmprestimoRepository emprestimoRepository;
    private final BibliotecarioRepository bibliotecarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;

    Scanner s = new Scanner(System.in);

    public void criacaoEmprestimo() {
        emprestar();

        // TODO: 10/04/2022 Arrumar this.emprestimoRepository.save
//        this.emprestimoRepository.save(emprestimo.getId(), emprestimo.getLivro().getNome(), emprestimo.getUsuario().getCpf(),emprestimo.getBibliotecario().getId(),
//                emprestimo.getDataRetirada(), emprestimo.getDataDevolucao(), emprestimo.getDataLimite());
        livroRepository.diminuirQuantidade(livro.getNome());
    }

    @Override
    public void emprestar(){
        LocalDate hoje = LocalDate.now();
        this.emprestimo = new Emprestimo(livro,usuario,bibliotecario,hoje,null,hoje.plusDays(7));
    }

    @Override
    public void digitarDados(){
        usuario = new Usuario();
        livro = new Livro();

        System.out.println("Digite o nome do Bibliotecário: ");
        String nomeBibliotecario = s.nextLine();

        this.bibliotecario = new Bibliotecario(this.bibliotecarioRepository.findBibliotecarioId(nomeBibliotecario),
                this.bibliotecarioRepository.findBibliotecarioEmail(nomeBibliotecario));

        System.out.println("Digite o CPF do usuário: ");
        String cpfUsuario = s.nextLine();

        if(this.usuarioRepository.existsById(cpfUsuario)){
            this.usuario.setCpf(cpfUsuario);

            if(usuarioRepository.findSupensao(cpfUsuario)==0){
                System.out.println("Nome do livro: ");
                String nomeLivro = s.nextLine();

                if(!this.livroRepository.existsById(nomeLivro)/*&& livroRepository.findQuantidade(nomeLivro)>=1*/){
                    this.livro.setNome(nomeLivro);
                }
                else {
                    System.out.println("Livro não existe ");
                }
            }
            else{
                System.out.println("Usuário suspenso temporariamente");
            }
        }
        else {
            System.out.println("Usuário não cadastrado");
        }
    }
}
