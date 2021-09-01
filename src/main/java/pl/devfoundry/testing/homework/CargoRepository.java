package pl.devfoundry.testing.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CargoRepository {

    private List<Cargo> cargoList;

    CargoRepository() {
        this.cargoList = new ArrayList<>();
    }

    public void addCargo(Cargo cargo) {
        cargoList.add(cargo);
    }

    public void removeCargo(Cargo cargo) {
        cargoList.remove(cargo);
    }

    public Optional<Cargo> findCargoByName(String name) {
        return cargoList.stream().filter(cargo -> cargo.getName().equals(name)).findFirst();
    }

}