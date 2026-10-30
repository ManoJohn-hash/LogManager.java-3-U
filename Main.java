public class Main {
    public static void main(String[] args) {
        LogManager log = LogManager.getInstance();

        LogManager outro = LogManager.getInstance();
        System.out.println("Mesma instância? " + (log == outro));

        log.configDestino("console");
        log.registrar("Sistema iniciado", TipoLog.INFO);
        log.registrar("Memória quase cheia", TipoLog.WARNING);
        log.registrar("Falha ao conectar no banco", TipoLog.ERROR);
        log.registrar("Valor de x = 10", TipoLog.DEBUG);

        log.configDestino("arquivo");
        log.registrar("Mensagem gravada no arquivo", TipoLog.INFO);
        log.registrar("Outro aviso no arquivo", TipoLog.WARNING);
        System.out.println("Logs gravados em logs.txt");

        log.configDestino("banco");
    }
}