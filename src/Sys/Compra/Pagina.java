package Sys.Compra;


import java.util.List;

public class Pagina {
    private int indx_inicial = 0;
    private int indx_final = 5;
    private List<Livro> pagina;
    private List<LivroCarrinho> paginaCarrinho;

    public void setPaginaCarrinho(List<LivroCarrinho> paginaCarrinho) {
        this.paginaCarrinho = paginaCarrinho;
    }

    public int getIndx_inicial() {
        return indx_inicial;
    }
    public void setIndx_inicial(int indx_inicial) {
        this.indx_inicial = indx_inicial;
    }
    public int getIndx_final() {
        return indx_final;
    }
    public void setIndx_final(int indx_final) {
        this.indx_final = indx_final;
    }

    public Pagina(List<Livro> pagina){
        this.pagina = pagina;
    }
    public Pagina(){
    }

    public void avancar(){
        if (getIndx_final()+5 > pagina.size()) {
            setIndx_inicial(getIndx_final() + 1);
            setIndx_final(getIndx_final() + 5);
        }
    }
    public void voltar(){
        try {
            setIndx_inicial(getIndx_final()-1);
            setIndx_final(getIndx_final()-5);
        }catch (IndexOutOfBoundsException index){
            setIndx_inicial(0);
            setIndx_final(5);
        }
    }

    public void imp(){
        for (int i = getIndx_inicial(); i < getIndx_final() ; i++) {
            try {
                System.out.println(this.pagina.get(i).toString());
                System.out.println("");
            }catch (IndexOutOfBoundsException index){
                break;
            }
        }
    }
    public void imp(List<LivroCarrinho> livrosCarrinho){
        for (int i = getIndx_inicial(); i < getIndx_final() ; i++) {
            try {
                System.out.println(livrosCarrinho.get(i).toString());
                System.out.println("");
            }catch (IndexOutOfBoundsException index){
                break;
            }
        }
    }


}
