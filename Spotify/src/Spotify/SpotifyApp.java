import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Musica {
    private String titulo;
    private String genero;
    private int duracao;

    public Musica(String titulo, String genero, int duracao) {
        this.titulo = titulo;
        this.genero = genero;
        this.duracao = duracao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public int getDuracao() {
        return duracao;
    }


    public String toString() {
        return String.format("Título: %s, Gênero: %s, Duração: %ds", titulo, genero, duracao);
    }
}

class MusicaPop extends Musica {
    public MusicaPop(String titulo, int duracao) {
        super(titulo, "Pop", duracao);
    }
}

class MusicaRock extends Musica {
    public MusicaRock(String titulo, int duracao) {
        super(titulo, "Rock", duracao);
    }
}

class Artista {
    private String nome;
    private List<Musica> musicas;

    public Artista(String nome) {
        this.nome = nome;
        this.musicas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void adicionarMusica(Musica musica) {
        musicas.add(musica);
    }

    public List<Musica> getMusicas() {
        return musicas;
    }


    public String toString() {
        return "Artista: " + nome;
    }
}

public class SpotifyApp {
    private List<Artista> artistas;
    private Scanner scanner;

    public SpotifyApp() {
        artistas = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void executar() {
        int opcao;
        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    adicionarMusica();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);
    }

    private void exibirMenu() {
        System.out.println("=== Menu Spotify para Artistas ===");
        System.out.println("1. Cadastrar Artista");
        System.out.println("2. Adicionar Música");
        System.out.println("3. Listar Músicas de um Artista");
        System.out.println("4. Sair");
    }

    private void cadastrarArtista() {
        System.out.print("Digite o nome do artista: ");
        String nome = scanner.nextLine();
        Artista artista = new Artista(nome);
        artistas.add(artista);
        System.out.println("Artista cadastrado com sucesso!");
    }

    private void adicionarMusica() {
        if (artistas.isEmpty()) {
            System.out.println("Nenhum artista cadastrado. Cadastre um artista primeiro.");
            return;
        }

        System.out.print("Digite o nome do artista: ");
        String nomeArtista = scanner.nextLine();
        Artista artista = encontrarArtista(nomeArtista);

        if (artista == null) {
            System.out.println("Artista não encontrado.");
            return;
        }

        System.out.print("Digite o título da música: ");
        String titulo = scanner.nextLine();
        System.out.print("Escolha o gênero da música (1 - Pop, 2 - Rock): ");
        int generoOpcao = scanner.nextInt();
        System.out.print("Digite a duração da música (em segundos): ");
        int duracao = scanner.nextInt();
        scanner.nextLine();

        Musica musica;
        if (generoOpcao == 1) {
            musica = new MusicaPop(titulo, duracao);
        } else if (generoOpcao == 2) {
            musica = new MusicaRock(titulo, duracao);
        } else {
            System.out.println("Gênero inválido. Música não adicionada.");
            return;
        }

        artista.adicionarMusica(musica);
        System.out.println("Música adicionada com sucesso!");
    }

    private void listarMusicas() {
        if (artistas.isEmpty()) {
            System.out.println("Nenhum artista cadastrado. Cadastre um artista primeiro.");
            return;
        }

        System.out.print("Digite o nome do artista: ");
        String nomeArtista = scanner.nextLine();
        Artista artista = encontrarArtista(nomeArtista);

        if (artista == null) {
            System.out.println("Artista não encontrado.");
            return;
        }

        List<Musica> musicas = artista.getMusicas();
        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada para este artista.");
        } else {
            System.out.println("Músicas de " + artista.getNome() + ":");
            for (Musica musica : musicas) {
                System.out.println(musica);
            }
        }
    }

    private Artista encontrarArtista(String nome) {
        for (Artista artista : artistas) {
            if (artista.getNome().equalsIgnoreCase(nome)) {
                return artista;
            }
        }
        return null;
    }

    }