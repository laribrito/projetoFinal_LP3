package socketServidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Random;

import org.json.JSONObject;

class ServidorThread extends Thread {
    static private Random gerador = new Random();

    private Socket cliente;

    public ServidorThread(Socket cliente) {
        this.cliente = cliente;
    }

    public void run() {
        try {
            // Streams para comunicação
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream saida = new DataOutputStream(cliente.getOutputStream());

            // Lê o nome enviado pelo cliente
            String nome = entrada.readUTF();
            System.out.println("Recebido do cliente: " + nome);

            // Envia o mesmo nome de volta para o cliente
            saida.writeUTF("Servidor recebeu: " + nome);

            entrada.close();
            saida.close();
            cliente.close();
        } catch (Exception e) {
            System.out.println("Erro na thread do servidor: " + e.getMessage());
        }
    }

    static public long novoInteiro(long min, long max){
        return (long)(gerador.nextDouble() * max-min)+min;
    }
}
