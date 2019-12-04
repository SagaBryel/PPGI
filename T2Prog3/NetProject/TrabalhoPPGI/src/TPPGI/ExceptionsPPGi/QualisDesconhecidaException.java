package TPPGI.ExceptionsPPGi;

/**Exception para Qualis desconhecida em regras/qualificação
 *
 * @author Gabriel Paschoal
 * @author Hiuri Liberato
 */
public class QualisDesconhecidaException extends ExceptionsPPGI{
    
    /**Construtor da exception para regras
     * 
     * @param data
     * @param qualis 
     */
    public QualisDesconhecidaException(String data, String qualis){
            super("Qualis desconhecido para regras de " + data + ": " + qualis + ".");
    //Exception de qualificação
    
    /** Construtor da exception para qualificação
     * @param sigla
     * @param ano
     * @param qualis
     */
    }public QualisDesconhecidaException(String sigla, int ano, String qualis){
            super("Qualis desconhecido para qualificação do veiculo " + sigla + " no ano " + ano + ": " + qualis + ".");
    }
    
    
}
