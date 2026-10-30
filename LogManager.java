import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

enum TipoLog {
    ERROR, WARNING, INFO, DEBUG
}

public class LogManager {
    private static LogManager instance;
    private String destino = "console";
    private String nomeArquivo = "logs.txt";
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private LogManager() {} // construtor privado

    public static synchronized LogManager getInstance() {
        if (instance == null) {
            instance = new LogManager();
        }
        return instance;
    }

    public void configDestino(String destino) {
        if (destino.equalsIgnoreCase("arquivo") ||
            destino.equalsIgnoreCase("console")) {
            this.destino = destino.toLowerCase();
        } else {
            System.out.println("Destino inválido");
        }
    }

    public void registrar(String msg, TipoLog tipo) {
        String dataHora = LocalDateTime.now().format(formatter);
        String linha = "[" + dataHora + "] [" + tipo + "] " + msg;

        if (destino.equals("arquivo")) {
            gravarEmArquivo(linha);
        } else {
            System.out.println(linha);
        }
    }

    public void gravarEmArquivo(String mensagem) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nomeArquivo, true))) {
            pw.println(mensagem);
        } catch (IOException e) {
            System.out.println("Erro ao gravar no arquivo: " + e.getMessage());
        }
    }
}