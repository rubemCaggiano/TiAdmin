package br.com.kadesh.mb;

import br.com.kadesh.dao.Dao;
import br.com.kadesh.dao.GenericDAO;
import br.com.kadesh.model.Pedido;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@ManagedBean
@ViewScoped
public class PedidoMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Dao<Pedido> pedidoDao = new GenericDAO<>(Pedido.class);

    private List<Pedido> pedidos = new ArrayList<>();

    private Pedido pedido = new Pedido();
    private Pedido pedidoSelecionado = new Pedido();

    private int id = 0;
    private boolean editavel;
    private boolean novo;

    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
        if (id != 0) {
            pedido = pedidoDao.buscarPorId(id);
            editavel = false;
            novo = false;
        } else {
            pedido = new Pedido();
            editavel = true;
            novo = true;
        }
    }

    public void salvar() {
        if (novo) {
            try {
                pedidoDao.salvar(pedido);
                Messages.addGlobalInfo("Pedido Cadastrado com sucesso");
                pedido = new Pedido();
                selectAll();
            } catch (Exception e) {
                Messages.addGlobalError("Falha ao cadastrar");
            }

        } else {
            try {
                pedidoDao.alterar(pedido);
                Messages.addGlobalInfo("Pedido Alterado com sucesso");
                pedido = new Pedido();
                selectAll();
            } catch (Exception e) {
                Messages.addGlobalError("Falha ao alterar");
            }
        }
    }

    public void excluir() {
        try {
            pedidoDao.excluir(pedidoSelecionado);
            pedidos.remove(pedidoSelecionado);
            Messages.addGlobalInfo("Pedido Excluido com sucesso");

        } catch (Exception e) {
            Messages.addGlobalError("Falha ao excluir");
        }

    }

    @PostConstruct
    public void selectAll() {
        pedidos = pedidoDao.buscarTodos(Pedido.class);
    }

    public void selecionar(Pedido c) {
        pedidoSelecionado = c;
    }

    public void deselecionar() {
        pedidoSelecionado = new Pedido();
    }

    public void prepEdicao() {
        editavel = true;
    }

    public void cancelaEditavel() {
        editavel = false;
    }
//    Getters and Setters
//    --------------------------------------------------------------------------

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pedido getPedidoSelecionado() {
        return pedidoSelecionado;
    }

    public void setPedidoSelecionado(Pedido pedidoSelecionado) {
        this.pedidoSelecionado = pedidoSelecionado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEditavel() {
        return editavel;
    }

    public void setEditavel(boolean editavel) {
        this.editavel = editavel;
    }

    public boolean isNovo() {
        return novo;
    }

    public void setNovo(boolean novo) {
        this.novo = novo;
    }

}
