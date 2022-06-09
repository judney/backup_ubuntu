
package Controler;

import IrradiacaoNormalDireta;
import java.util.ArrayList;
import java.util.List;

public class IrradiacaoDao {
    public boolean criar(IrradiacaoDao p) {
        return true;
    }

    public boolean update(IrradiacaoDao p) {
        return true;
    }

    public boolean delete(IrradiacaoDao p) {
        return true;
    }

    public IrradiacaoDao lerPorId(int id) {
        IrradiacaoDao p = new IrradiacaoDao();
        return p;
    }

    public List<IrradiacaoDao> lerTodas() {
        List<IrradiacaoDao> listaDeIrradiacaoDaos = new ArrayList<>();
        return listaDeIrradiacaoDaos;
    }
}