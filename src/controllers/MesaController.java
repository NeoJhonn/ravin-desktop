package controllers;

import builders.Builder;
import enums.Disponibilidade;
import enums.StatusMesa;
import models.Cliente;
import models.Funcionario;
import models.Mesa;
import repositories.MesaRepository;

import java.util.List;

public class MesaController {

    private MesaRepository repository;

    public MesaController() {

        repository = new MesaRepository();
        Builder.seedMesas(repository.listarTodos());
    }

    public void cadastrar(Mesa entidade) {
        repository.salvar(entidade);
    }

    public void alterar(Mesa entidade) {
        // REGRAS DE NEGÓCIO
        repository.salvar(entidade);
    }

    public void excluir(int id) {
        Mesa mesaBuscada = repository.buscarPorId(id);

        if (mesaBuscada != null) {
            repository.excluir(mesaBuscada);
        }
    }

    public Mesa consultar(int id) {

        return repository.buscarPorId(id);
    }

    public List<Mesa> listarTodos() {

        return repository.listarTodos();
    }

    public void reservarMesa(Cliente cliente, Mesa mesa, Funcionario garcom) {
        if (!cliente.equals(null) && !mesa.equals(null) && !garcom.equals(null)) {
            mesa.setStatusMesa(StatusMesa.Reservada);
            mesa.setCliente(cliente);
            garcom.setDisponibilidade(Disponibilidade.OCUPADO);
            mesa.setFuncionario(garcom);
        }
    }

    public List<Mesa> listarMesasDisponiveis() {
        List<Mesa> mesasDisponiveis = repository.listarTodos().stream().filter(m -> m.getStatusMesa() == StatusMesa.Livre).toList();

        return mesasDisponiveis;
    }

    public List<Mesa> listarMesasPorStatus(StatusMesa statusMesa) {
        List<Mesa> mesasDisponiveis = repository.listarTodos().stream().filter(m -> m.getStatusMesa() == statusMesa).toList();

        return mesasDisponiveis;
    }
}
