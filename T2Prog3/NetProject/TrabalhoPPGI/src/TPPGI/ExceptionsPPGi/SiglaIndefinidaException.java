package TPPGI.ExceptionsPPGi;

/**Exception para Sigla Indefinida em publicação/qualificação
 * 
 * @author Gabriel Paschoal
 * @author Hiuri Liberato
 */
public class SiglaIndefinidaException extends ExceptionsPPGI {

    /**Construtor da exception de veiculo indefinido para publicação, que lança a mensagem de erro
     * 
     * @param titulo
     * @param sigla 
     */
    public SiglaIndefinidaException(String titulo, String sigla){
        super("Sigla de veículo não definida usada na publicaco " + titulo + ": " + sigla + ".");
    }
    // Exception de qualis
    /**Construtor da exception de veiculo indefinido para qualis, que lança a mensagem de erro
     * 
     * @param ano
     * @param sigla 
     */
    public SiglaIndefinidaException(int ano, String sigla){
        super("Sigla de veículo não definida usada na qualificacao do ano" + ano + ": " + sigla + ".");
    }
}
